package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityUDN extends ECEntity {

    public EntityUDN(ECData ecData) {
        super(ecData);
    }

    @Override
    public List<String> parse(String returnContent) {
        System.out.println(returnContent);
        List<String> result = new ArrayList<>();

        Gson gson = new Gson();

        List<Map<String, Object>> map = new ArrayList<>();

        map = gson.fromJson(returnContent, new TypeToken<List<Map<String, Object>>>() {
        }.getType());

        for (Map<String, Object> m : map) {
            if (m.containsKey("name")) {
                String keyword = (String) m.get("name");

                if (keyword.length() == 0) {
                    break;
                }

                result.add(keyword);
            }

        }


        return result;
    }

}
