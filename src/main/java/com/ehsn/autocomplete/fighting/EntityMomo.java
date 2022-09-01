package com.ehsn.autocomplete.fighting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityMomo extends ECEntity {

    public static void main(String[] args) {
        ECEntity momo = EntityFactory.getInstance().lookupEntity("momo");

        try {
            List<String> result = momo.query("掃");
            for (String str : result) {
                System.out.println(str);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    @Override
    public String keywordPreprocess(String keyword) throws Exception {
        int[] codePoints = keyword.codePoints().toArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codePoints.length; i++) {
            if (codePoints[i] <= 127) {
                sb.append(String.valueOf(Character.toChars(codePoints[i])));
            } else {
                sb.append("&#" + codePoints[i] + ";");
            }
        }
        return sb.toString();

    }

    public EntityMomo(ECData ecData) {
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
            if (m.containsKey("text")) {
                result.add((String) m.get("text"));
            } else if (m.containsKey("cateName")) {
                result.add((m.get("cateName")) + " (" + m.get("wording") + ")");
            }
        }


        return result;
    }
}
