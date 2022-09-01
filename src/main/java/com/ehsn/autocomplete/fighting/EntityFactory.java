package com.ehsn.autocomplete.fighting;

import java.util.HashMap;
import java.util.Map;

/**
 * 負責創建 Entity 物件
 * Singleton
 */
public class EntityFactory {

    private static volatile EntityFactory instance = null;
    private static final Integer LOCK = Integer.valueOf(0);

    private Map<String, ECEntity> entities = new HashMap<>();

    private EntityFactory() {
        ECData ecData = new ECData();
        ecData.setECName("umall");
        ecData.setUrl("http://falcon02.ugo.et2o/api/v1/search/AutoComplete/?query=term:${KEYWORD}*&queryProfile=UmallAutoComplete");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*");
        ecData.setReferer("http://172.21.2.17:8080/");

        EntityUMall uMall = new EntityUMall(ecData);
        entities.put(ecData.getECName(), uMall);

        ecData = new ECData();
        ecData.setECName("umallori");
        ecData.setUrl("http://vespa.lab.etzone.net/api/v1/search/AutoComplete/?query=term:${KEYWORD}*&queryProfile=Umall&rules.off=true");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*");
        ecData.setReferer("http://172.21.2.17:8080/");

        EntityUMallOri uMallori = new EntityUMallOri(ecData);
        entities.put(ecData.getECName(), uMallori);


        ecData = new ECData();
        ecData.setECName("momo");
        ecData.setUrl("https://www.momoshop.com.tw/servlet/KeywordAutoCompleteServlet?_=${TIMESTAMP}&kw=${KEYWORD}");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*");
        ecData.setReferer("https://www.momoshop.com.tw/main/Main.jsp");
        EntityMomo momo = new EntityMomo(ecData);
        entities.put(ecData.getECName(), momo);


        ecData = new ECData();
        ecData.setECName("pchome");
        ecData.setUrl("https://ecshweb.pchome.com.tw/search/v3.3/all/suggestwords?q=${KEYWORD}&limit=10&timestamp=${TIMESTAMP}");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*; q=0.01");
        ecData.setReferer("https://shopping.pchome.com.tw/");
        EntityPCHome pchome = new EntityPCHome(ecData);
        entities.put(ecData.getECName(), pchome);


        ecData = new ECData();
        ecData.setECName("etmall");
        ecData.setUrl("https://www.etmall.com.tw/Search/GetSuggestKeywords");
        ecData.setMethod("POST");
        ecData.setAccept("application/json, text/javascript, */*");
        ecData.setReferer("https://www.etmall.com.tw/");
        EntityETMall etmall = new EntityETMall(ecData);
        entities.put(ecData.getECName(), etmall);


        ecData = new ECData();
        ecData.setECName("yahoo");
        ecData.setUrl("https://tw.search.buy.yahoo.com/search/srp/searchbar?property=sas&p=${KEYWORD}&cid=0");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*; q=0.01");
        ecData.setReferer("https://tw.buy.yahoo.com/");

        EntityYahoo yahoo = new EntityYahoo(ecData);
        entities.put(ecData.getECName(), yahoo);

        ecData = new ECData();
        ecData.setECName("shopee");
        //ecData.setUrl("https://shopee.tw/api/v2/search_hint/get?keyword=${KEYWORD}&search_type=0");
        ecData.setUrl("https://shopee.tw/api/v4/search/search_hint?historical_query=${KEYWORD}&keyword=ip&search_type=0&version=1");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*; q=0.01");
        ecData.setReferer("https://shopee.tw/");
        EntityShopee shopee = new EntityShopee(ecData);
        entities.put(ecData.getECName(), shopee);

        ecData = new ECData();
        ecData.setECName("udn");
        ecData.setUrl("https://shopping.udn.com/mall/cus/search/SearchAjaxService.ajax?method=searchAutoCompleteAdvance&keyword=${KEYWORD}");
        ecData.setMethod("GET");
        ecData.setAccept("application/json, text/javascript, */*; q=0.01");
        ecData.setReferer("https://shopping.udn.com/mall/Cc1a00.do");
        EntityUDN udn = new EntityUDN(ecData);
        entities.put(ecData.getECName(), udn);
    }

    public static EntityFactory getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new EntityFactory();
                }
            }
        }

        return instance;
    }


    public ECEntity lookupEntity(String name) {
        return entities.get(name);
    }


}
