package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Shaliou_AG
 */
public class Generator {

    public Generator() {

    }

    public String view(Map<String, StringBuilder> templateMap, Table table) {
        List<StringBuilder> content = getContent(templateMap, table);
        StringBuilder result = new StringBuilder("");
        if(!content.stream().allMatch((it) -> it.toString().equals(""))) {
            for (StringBuilder entry : content) {
                result.append(entry);
                result.append(REGEX_ENTER);
            }
        }
        return result.toString();
    }

    public boolean generate(Map<String, StringBuilder> templateMap, Table table, File dir) {
        List<StringBuilder> content = getContent(templateMap, table);
        if(!content.stream().allMatch((it) -> it.toString().equals(""))) {
            int i = 0;  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            for (StringBuilder entry : content) {
                if (!entry.toString().equals("")) {
                    String fileName = Translit.getInstance().translate(entry.toString().substring(0, entry.indexOf(" "))) + String.valueOf(i++) + EXTENSION; ///////как генерить будем?
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

    private List<StringBuilder> getContent(Map<String, StringBuilder> templateMap, Table table) {
        List<StringBuilder> resultList = new ArrayList();
        List<String[]> content = table.getContentList();
        List<String> headers = table.getHeaderList();
        Pattern columnNamePattern = Pattern.compile(REGEX_COLUMN_NAME);
        Matcher matcher = null;
        for (String[] row : content) {
            StringBuilder result = new StringBuilder("");
            for (int i = 0; i < row.length; i++) {
                StringBuilder temp = templateMap.get(headers.get(i));
                matcher = columnNamePattern.matcher(temp);
                 while (matcher.find()) {
                    String columnName = matcher.group().substring(2, matcher.group().length() - 1);
                    for (int j = 0; j < headers.size(); j++) {
                        if (headers.get(j).equals(columnName)) {
                            temp.replace(0, temp.length(), temp.toString().replaceAll("\\$\\{" + columnName + "\\}", row[j]));
                            break;
                        }
                    }
                }
                result.append(temp);
            }
            resultList.add(result);
        }
        return resultList;
    }

    private final String REGEX_ENTER = "\n";
    private final String REGEX_COLUMN_NAME = "(\\$\\{\\w+\\})";
    private final String EXTENSION = ".scs";

}
