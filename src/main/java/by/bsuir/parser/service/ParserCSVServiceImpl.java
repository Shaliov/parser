package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shaliou_AG
 */
public class ParserCSVServiceImpl implements ParserService {
    @Override
    public Table parse(File file) {
        CSVReader csvReader = null;
        Table table = new Table();
        try {
            csvReader = new CSVReader(new FileReader(file));
            String[] headers = csvReader.readNext();
            List<String[]> content = csvReader.readAll();
            table.setHeaderList(Arrays.asList(headers));
            table.setContentList(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }
}
