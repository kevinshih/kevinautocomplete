package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityYahoo extends ECEntity {

    public static void main(String[] args) {
        ECEntity yahoo = EntityFactory.getInstance().lookupEntity("yahoo");

        try {
            List<String> result = yahoo.query("中");
            for (String str : result) {
                System.out.println(str + ", ");
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public EntityYahoo(ECData ecData) {
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
            if (m.containsKey("title")) {
                String str = (String) m.get("title");

                if (m.containsKey("prev")) {
                    str += " " + m.get("prev");
                }

                if (m.containsKey("exstr")) {
                    str += " (" + m.get("exstr") + ")";
                }

                result.add(str);
            }

        }


        return result;
    }
}
