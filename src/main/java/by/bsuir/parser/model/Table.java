package by.bsuir.parser.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaliou_AG
 */
public class Table {

    private List<String> headerList;
    private List<String[]> contentList;

    public Table() {
        headerList = new ArrayList<>();
        contentList = new ArrayList<>();
    }

    public Table(List<String> headerList,  List<String[]> contentList) {
        this.headerList = headerList;
        this.contentList = contentList;
    }

    public List<String> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    public  List<String[]> getContentList() {
        return contentList;
    }

    public void setContentList(List<String[]> contentList) {
        this.contentList = contentList;
    }
}
