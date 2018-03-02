package by.bsuir.parser.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shaliou_AG
 */
public class Table {
    private String id;

    private List<String> headerList;
    private Map<String, String> contentMap;

    public Table() {
        headerList = new ArrayList<>();
        contentMap = new HashMap<>();
    }

    public Table(List<String> headerList, Map<String, String> contentMap) {
        this.headerList = headerList;
        this.contentMap = contentMap;
    }

    public List<String> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    public Map<String, String> getContentMap() {
        return contentMap;
    }

    public void setContentMap(Map<String, String> contentMap) {
        this.contentMap = contentMap;
    }
}
