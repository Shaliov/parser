package by.bsuir.parser.service;

import by.bsuir.parser.model.Table;

import java.io.File;

/**
 * @author Shaliou_AG
 */
public interface ParserService {
    Table parse(File file);
}
