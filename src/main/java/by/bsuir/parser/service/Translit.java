package by.bsuir.parser.service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shaliou_AG
 */
public class Translit {
    private Map<Integer, Integer[]> translitMap = new HashMap<>();
    private static Translit instance = new Translit();

    private Translit() {
        initData();

    }

    private void initData() {
        translitMap.put(40, new Integer[]{95});
        translitMap.put(1072, new Integer[]{97});
        translitMap.put(1073, new Integer[]{98});
        translitMap.put(1074, new Integer[]{118});
        translitMap.put(1075, new Integer[]{103});
        translitMap.put(1076, new Integer[]{100});
        translitMap.put(1077, new Integer[]{101});
        translitMap.put(1078, new Integer[]{122, 104});
        translitMap.put(1079, new Integer[]{122});
        translitMap.put(1080, new Integer[]{105});
        translitMap.put(1081, new Integer[]{121, 111});
        translitMap.put(1082, new Integer[]{107});
        translitMap.put(1083, new Integer[]{108});
        translitMap.put(1084, new Integer[]{109});
        translitMap.put(1085, new Integer[]{110});
        translitMap.put(1086, new Integer[]{111});
        translitMap.put(1087, new Integer[]{112});
        translitMap.put(1088, new Integer[]{114});
        translitMap.put(1089, new Integer[]{115});
        translitMap.put(1090, new Integer[]{116});
        translitMap.put(1091, new Integer[]{117});
        translitMap.put(1092, new Integer[]{102});
        translitMap.put(1093, new Integer[]{104});
        translitMap.put(1094, new Integer[]{116, 115});
        translitMap.put(1095, new Integer[]{99, 104});
        translitMap.put(1096, new Integer[]{115, 104});
        translitMap.put(1097, new Integer[]{115, 104, 99, 104});
        translitMap.put(1098, new Integer[]{});
        translitMap.put(1099, new Integer[]{});
        translitMap.put(1100, new Integer[]{});
        translitMap.put(1101, new Integer[]{101});
        translitMap.put(1102, new Integer[]{121, 117});
        translitMap.put(1103, new Integer[]{121, 97});
    }

    public String translate(String word) {
        StringBuilder result = new StringBuilder("");
        char[] characters = new char[word.length()];
        word.toLowerCase().getChars(0, word.length(), characters, 0);
        for (Character character : characters) {
            int asci = character.hashCode();
            Integer[] codes = translitMap.get(asci);
            if (codes != null) {
                for (int code : codes) {
                    result.append((char) code);
                }
            }
        }
        return result.toString();
    }

    public static Translit getInstance() {
        if (instance == null) {
            instance = new Translit();
        }
        return instance;
    }
}
