package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;

import java.io.File;

/**
 * @author Shaliou_AG
 */
public class ParserFabric {
    private static ParserFabric instance = new ParserFabric();

    private ParserFabric() {

    }

    public Table doParse(File file) {

        if (file.getPath().endsWith(EXTENSION_CSV)) {
           return parseCsvFile(file);
        } else if (file.getPath().endsWith(EXTENSION_XLSX)) {
           return parseXlsxFile(file);
        } else {
            return null;
        }
    }

    private Table parseCsvFile(File file) {
        ReaderService readerService = new ReaderCSVServiceImpl();
        return readerService.parse(file);
    }

    private Table parseXlsxFile(File file) {
        return null;
    }


    public static ParserFabric getInstance() {
        if (instance == null) {
            instance = new ParserFabric();
        }
        return instance;
    }

    private final String EXTENSION_CSV = "csv";
    private final String EXTENSION_XLSX = "xlsx";
}
