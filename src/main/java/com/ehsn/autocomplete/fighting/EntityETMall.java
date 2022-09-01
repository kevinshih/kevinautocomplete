package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityETMall extends ECEntity {

    public static void main(String[] args) {

        ECEntity etmall = EntityFactory.getInstance().lookupEntity("etmall");

        try {
            List<String> result = etmall.query("n");
            for (String str : result) {
                System.out.println(str);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }


    public EntityETMall(ECData ecData) {
        super(ecData);
    }


    @Override
    public List<String> parse(String returnContent) {
        System.out.println(returnContent);
        List<String> result = new ArrayList<>();

        Gson gson = new Gson();

        Map<String, Object> map = new LinkedTreeMap<>();

        map = gson.fromJson(returnContent, map.getClass());

        if (map.containsKey("Results")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("Results");

            for (Map<String, Object> entry : results) {
                if (entry.containsKey("Keyword")) {
                    result.add((String) entry.get("Keyword"));
                }
            }

        }


        return result;
    }
}
