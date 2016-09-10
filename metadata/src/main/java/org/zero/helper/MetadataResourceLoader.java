package org.zero.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by rfang on 2016/9/8.
 */
@Slf4j
@Component
public class MetadataResourceLoader {

    private String rootDir;

    @Value("${metadata.resource.root.dir:}")
    public void setRootDir(final String rootDir) {

        if (StringUtils.isBlank(rootDir)) {
            log.info("No metadata root directory set, only load metadata from classpath");
            return;
        }

    }

    public InputStream getResourceInputStream(final String resourceName) {

        if (rootDir == null) {
            return getStreamFromClassPath(resourceName);
        }

        File file = new File(rootDir + resourceName);

        if (!file.exists()) {
            return getStreamFromClassPath(resourceName);
        }

        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find Metadata file '" + resourceName + "' in [" + rootDir + "']");
        }
    }

    private InputStream getStreamFromClassPath(String resourceName) {
        return getClass().getResourceAsStream("/metadata/" + resourceName);
    }
}
