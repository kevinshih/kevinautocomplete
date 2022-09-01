package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityShopee extends ECEntity {

    public EntityShopee(ECData ecData) {
        super(ecData);
    }


    @Override
    public List<String> parse(String returnContent) {
        System.out.println(returnContent);
        List<String> result = new ArrayList<>();

        Gson gson = new Gson();

        Map<String, Object> map = new LinkedTreeMap<>();

        map = gson.fromJson(returnContent, map.getClass());

        if (map.containsKey("keywords")) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("keywords");

            for (Map<String, Object> entry : results) {
                if (entry.containsKey("keyword")) {
                    String keyword = (String) entry.get("keyword");

                    if (entry.containsKey("cat_display_name") && entry.get("cat_display_name") != null) {
                        keyword += " (" + entry.get("cat_display_name") + ")";
                    }

                    result.add(keyword);
                }
            }

        }


        return result;
    }
}
