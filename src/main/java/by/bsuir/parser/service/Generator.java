package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Shaliou_AG
 */
public class Generator {


    public Generator() {

    }

    public String view(Map<String, StringBuilder> templateMap, Table table) {
        String content = getContent(templateMap, table);
        String result =  content.replaceAll(REGEX_ENTER,"");
        if (!result.equals("")) {
            return content;
        } else {
           return result;
        }
    }

    private String getContent(Map<String, StringBuilder> templateMap, Table table) {
        StringBuilder result = new StringBuilder("");
        List<String[]> content = table.getContentList();
        List<String> headers = table.getHeaderList();
        for (String[] row : content) {
            for (int i = 0; i < row.length; i++) {
                String cell = row[i];
                String temp = templateMap.get(headers.get(i)).toString().replaceAll("test", cell);
                result.append(temp);
                result.append(REGEX_ENTER);
            }

            result.append(REGEX_ENTER);
            result.append(REGEX_ENTER);
        }
        return result.toString();
    }

    private final String REGEX_ENTER = "\n";

}
