package com.ehsn.autocomplete.fighting;

import com.ehsn.autocomplete.BaseData;

public class ECData extends BaseData {
    private static final String EC_NAME = "EC_NAME";
    private static final String URL = "EC_AUTO_COMPLETE_URL";
    private static final String CONNECTION_METHOD = "CONNECTION_METHOD";
    private static final String REQUEST_ACCEPT = "REQ_ACCEPT";
    private static final String REFERER = "REFERER";


    public void setECName(String ecname) {
        this.put(EC_NAME, ecname);
    }

    public String getECName() {
        return this.string(EC_NAME);
    }

    public void setUrl(String url) {
        this.put(URL, url);
    }

    public String getUrl() {
        return this.string(URL);
    }

    public void setMethod(String method) {
        this.put(CONNECTION_METHOD, method);
    }

    public String getMethod() {
        return this.string(CONNECTION_METHOD);
    }


    public void setAccept(String accept) {
        this.put(REQUEST_ACCEPT, accept);
    }

    public String getAccept() {
        return this.string(REQUEST_ACCEPT);
    }

    public void setReferer(String ref) {
        this.put(REFERER, ref);

    }

    public String getReferer() {
        return this.string(REFERER);
    }

}
