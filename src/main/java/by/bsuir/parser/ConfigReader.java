package by.bsuir.parser;


import by.bsuir.parser.controller.MainController;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Shaliou_AG
 */
public class ConfigReader {
    public ConfigReader() {

    }

    public static String getExtensions()  {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(getConfigString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj.get("allowable.extensions").toString();
    }


    private static String getConfigString(){
        StringBuilder string = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(MainController.class.getResourceAsStream("/config.json")));
        while (true){
            try {
                String str = reader.readLine();
                if (str == null || str.isEmpty()){
                    break;
                }
                string.append(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }
}
