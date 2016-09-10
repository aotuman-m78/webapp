package org.zero.metadata;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.zero.core.role.Role;
import org.zero.core.role.RoleRight;
import org.zero.helper.MetadataResourceLoader;
import org.zero.util.MetadataUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rfang on 2016/9/8.
 */
@Component
public class MetadataRoleFileParser implements InitializingBean {

    @Autowired private MetadataResourceLoader resourceLoader;
    @Autowired private PlatformTransactionManager txMgr;
    @Autowired private MetadataRoleRepository rep;

    private HeaderUnit headerUnit;
    private final List<ContentUnit> contentList = new ArrayList<>();

    final static private String INPUT_FILE = "role.csv";

    @Override
    public void afterPropertiesSet() throws Exception {
        parseAndProcess();
    }

    private void parseAndProcess() throws UnsupportedEncodingException {
        parse();
        process();
    }

    private void process() {
        processContentUnit();
    }

    private void processContentUnit() {
        final List<MetadataRole> props = new ArrayList<>();

        for (ContentUnit unit : contentList) {
            for (Map.Entry<Integer, String> entry : headerUnit.indexRole.entrySet()) {
                MetadataRole role = new MetadataRole();
                role.setRole(Role.valueOf(entry.getValue()));
                role.setRoleRight(RoleRight.valueOf(unit.name));
                role.setValue(unit.values[entry.getKey()]);
                props.add(role);
            }
        }

        TransactionTemplate tt = new TransactionTemplate(txMgr);
        tt.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                for (MetadataRole p : props) {
                    rep.save(p);
                }
                return null;
            }
        });
    }

    private void parse() throws UnsupportedEncodingException {
        InputStream is = resourceLoader.getResourceInputStream(INPUT_FILE);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                line = MetadataUtil.fixInputLine(line);
                String[] tokens = line.split(",");

                if (headerUnit == null) {
                    headerUnit = parseHeaderLine(tokens);
                    continue;
                }

                contentList.add(parseContentLine(tokens));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ContentUnit parseContentLine(String[] tokens) {
        ContentUnit unit = new ContentUnit();
        unit.name = tokens[headerUnit.indexControl];
        unit.values = new String[tokens.length];
        System.arraycopy(tokens, 2, unit.values, 2, tokens.length - 2);
        return unit;
    }

    private HeaderUnit parseHeaderLine(String[] tokens) {
        HeaderUnit headerUnit = new HeaderUnit();

        for (int i = 0; i < tokens.length; i ++) {
            if ("Control".equals(tokens[i])) {
                headerUnit.indexControl = i;
            } else {
                headerUnit.indexRole.put(i, tokens[i]);
            }
        }
        return headerUnit;
    }

    private static class HeaderUnit {
        private int indexControl;
        private final Map<Integer, String> indexRole = new HashMap<>();
    }

    private static class ContentUnit {
        private String name;
        private String[] values;
    }
}
