package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityPCHome extends ECEntity {

    public static void main(String[] args) {
        ECEntity pchome = EntityFactory.getInstance().lookupEntity("pchome");

        try {
            List<String> result = pchome.query("中");
            for (String str : result) {
                System.out.println(str + ", ");
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public EntityPCHome(ECData ecData) {
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
                result.add((String) m.get("name"));
            }

        }


        return result;
    }
}
