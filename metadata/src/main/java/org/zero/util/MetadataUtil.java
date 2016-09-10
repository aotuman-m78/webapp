package org.zero.util;

import org.springframework.util.StringUtils;

/**
 * Created by rfang on 2016/9/9.
 */
public class MetadataUtil {

    static public String removeSpaceAndTab(String s) {
        String resStr = StringUtils.trimWhitespace(s);
        return resStr.replaceAll("\\s*,\\s*", ",");
    }

    static public String fixInputLine(String line) {
        line = removeSpaceAndTab(line);

        while(line.contains(",,")) {
            line = line.replace(",,", ",\"\",");
        }

        if (line.endsWith(",")) {
            line += "\"\"";
        }
        return line;
    }
}
