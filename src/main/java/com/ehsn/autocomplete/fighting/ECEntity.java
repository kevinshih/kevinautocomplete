package com.ehsn.autocomplete.fighting;

import com.ehsn.autocomplete.utils.HttpClient;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ECEntity {

    private ECData ecData;

    public ECEntity(ECData ecData) {
        this.ecData = ecData;
    }

    public ECData getEcData() {
        return ecData;
    }

    public String keywordPreprocess(String keyword) throws Exception {
        return keyword;
    }

    public abstract List<String> parse(String returnContent);

    public List<String> query(String keyword) throws Exception {
        String url = ecData.getUrl();

        keyword = keywordPreprocess(keyword);

        keyword = URLEncoder.encode(keyword, "UTF-8");
        url = url.replace("${KEYWORD}", keyword);
        url = url.replace("${TIMESTAMP}", String.valueOf(Calendar.getInstance().getTimeInMillis()));

        HttpClient client = new HttpClient();

        client.setRequestMethod(ecData.getMethod());
        client.setAccept(ecData.getAccept());
        client.setReferer(ecData.getReferer());
        if (ecData.getMethod().equals("POST")) {
            Map<String, String> map = new HashMap<>();
            map.put("keyword", keyword);
            client.setPostData(map);
        }
        System.out.println("url:"+url);
        client.open(url);

        return parse(client.getReturnContent());

    }
}
