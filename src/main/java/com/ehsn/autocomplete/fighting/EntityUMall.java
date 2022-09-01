package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityUMall extends ECEntity {

    public static void main(String[] args) {

        ECEntity uMall = EntityFactory.getInstance().lookupEntity("umall");

        try {
            List<String> result = uMall.query("n");
            for (String str : result) {
                System.out.println(str);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public EntityUMall(ECData ecData) {
        super(ecData);
    }

    @Override
    public List<String> parse(String returnContent) {
        System.out.println(returnContent);
        List<String> result = new ArrayList<>();

        Gson gson = new Gson();

        Map<String, Object> map = new LinkedTreeMap<>();

        map = gson.fromJson(returnContent, map.getClass());

        int counter = 0;

        if (map.containsKey("root")) {
            Map<String, Object> root = (Map<String, Object>) map.get("root");

            if (root.containsKey("children")) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) root.get("children");


                for (Map<String, Object> child : children) {
                    Map<String, Object> fields = (Map<String, Object>) child.get("fields");
                    result.add((String) fields.get("term"));
                    counter++;
                    if (counter >= 10) {
                        break;
                    }

                }
            }

        }


        return result;
    }
}
