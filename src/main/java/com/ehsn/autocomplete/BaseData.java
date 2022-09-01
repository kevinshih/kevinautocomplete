package com.ehsn.autocomplete;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 混行型態資料結構，在強型別(Data Bean)跟弱型別（Map<>）之間的平衡，只保留重要的有getter/setter，其他額外想擴充也可以直接存放
 */
public class BaseData {
    private Map<String, Object> data = new HashMap<>();

    public Date date(String key) {
        if (data.containsKey(key)) {
            return (Date) data.get(key);
        }
        return null;
    }

    public String string(String key) {
        if (data.containsKey(key)) {
            return (String) data.get(key);
        }
        return null;
    }

    public void put(String key, Object obj) {
        data.put(key, obj);
    }

    public Object object(String key) {
        return data.get(key);
    }

    public int intOf(String key) {
        if (data.containsKey(key)) {
            return (int) data.get(key);
        }
        return 0;
    }

    public double doubleOf(String key) {
        if (data.containsKey(key)) {
            return (double) data.get(key);
        }
        return 0.0d;
    }


    public void setData(Map<String, Object> map) {
        this.data = map;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

}
