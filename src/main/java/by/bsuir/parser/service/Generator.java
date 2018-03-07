package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shaliou_AG
 */
public class Generator {

    public Generator() {

    }

    public String view(Map<String, StringBuilder> templateMap, Table table) {
        return getContentFirstRow(templateMap, table);
    }

    public boolean generate(Map<String, StringBuilder> templateMap, Table table, File dir) {
        List<StringBuilder> content = getContent(templateMap, table);
        if(!content.stream().allMatch((it) -> it.toString().equals(""))) {
            int i = 0;
            for (StringBuilder entry : content) {
                if (!entry.toString().equals("")) {
                    String word = table.getContentList().get(i)[0] + "_";
                    String fileName = Translit.getInstance().translate(word) + UUID.randomUUID() + EXTENSION;
                    try (FileWriter fileWriter = new FileWriter(new File(dir.getPath() + File.separator + fileName))) {
                        fileWriter.write(entry.toString());
                        fileWriter.flush();
                    } catch (IOException e) {
                        return false;
                    }
                }
            }
            return true;
        }
      return false;
    }

    private String getContentFirstRow(Map<String, StringBuilder> templateMap, Table table) {
        String[] row = table.getContentList().get(0);
        List<String> headers = table.getHeaderList();
        Pattern columnNamePattern = Pattern.compile(REGEX_COLUMN_NAME);
        StringBuilder result = getContentFromRow(templateMap, headers, columnNamePattern, row);
        return result.toString();
    }

    private List<StringBuilder> getContent(Map<String, StringBuilder> templateMap, Table table) {
        List<StringBuilder> resultList = new ArrayList();
        List<String[]> content = table.getContentList();
        List<String> headers = table.getHeaderList();
        Pattern columnNamePattern = Pattern.compile(REGEX_COLUMN_NAME);
        for (String[] row : content) {
            StringBuilder result = getContentFromRow(templateMap, headers, columnNamePattern, row);
            resultList.add(result);
        }
        return resultList;
    }

    private StringBuilder getContentFromRow(Map<String, StringBuilder> templateMap, List<String> headers, Pattern columnNamePattern, String[] row) {
        StringBuilder result = new StringBuilder("");
        StringBuilder tempResult = new StringBuilder("");
        Matcher matcher;
        for (int i = 0; i < row.length; i++) {
            StringBuilder temp = new StringBuilder(templateMap.get(headers.get(i)));
            matcher = columnNamePattern.matcher(temp);
            while (matcher.find()) {
                String columnName = matcher.group().substring(2, matcher.group().length() - 1);
                for (int j = 0; j < headers.size(); j++) {
                    if (headers.get(j).equals(columnName)) {
                        temp = new StringBuilder(temp.toString().replaceAll("\\$\\{" + columnName + "\\}", row[j]));
                        break;
                    }
                }
            }
            result.append(temp);
        }
        return result;
    }

    private final String REGEX_COLUMN_NAME = "(\\$\\{((\\s)*(\\w|[а-я]|[А-Я])+(\\s)*)+\\})";
    private final String EXTENSION = ".scs";

}
