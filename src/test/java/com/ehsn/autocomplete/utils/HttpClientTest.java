package com.ehsn.autocomplete.utils;

//import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

//import org.junit.Test;

public class HttpClientTest {
	//timeout time
    public static int timeoutInMillis = 60 * 1000;  // 1 minute
    public static URLConnection urlConnection = null;
	public static void main(String args[]) {
		try {
            for (int i = 0; i < 4; i++) {
            	String url = "https://www.momoshop.com.tw/servlet/KeywordAutoCompleteServlet?_=1659601150301&kw=iphone";
            	//String url = "https://ecshweb.pchome.com.tw/search/v3.3/all/suggestwords?q=iphone&limit=10";
            	System.setProperty("javax.net.ssl.trustStore", "C:/Program Files/Java/jre1.8.0_333/lib/security/cacerts");
            	System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            	URL obj = new URL(url);

                urlConnection = obj.openConnection();
                if ("http".equals(obj.getProtocol())) {
                	System.out.println("http");
                    ((HttpURLConnection) urlConnection).setRequestMethod("GET");
                    ((HttpURLConnection) urlConnection).setInstanceFollowRedirects(false);
                    HttpURLConnection.setFollowRedirects(false);
                } else if ("https".equals(obj.getProtocol())) {
                	System.out.println("https");
                    ((HttpsURLConnection) urlConnection).setRequestMethod("GET");
                    ((HttpsURLConnection) urlConnection).setInstanceFollowRedirects(false);
                    HttpsURLConnection.setFollowRedirects(false);
                } else {
                    throw new IllegalStateException("url not valid=>" + url);
                }
                
//                urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
                urlConnection.setConnectTimeout(timeoutInMillis);
                urlConnection.setReadTimeout(timeoutInMillis);
//                urlConnection.setRequestProperty("Accept", accept);
                urlConnection.setRequestProperty("Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6");
                urlConnection.setRequestProperty("Accept-Language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6");
                urlConnection.setRequestProperty("User-Agent", userAgent);
                urlConnection.setRequestProperty("Cache-Control", "no-cache");

                //urlConnection.setRequestProperty("cookie", "wshop=wshop_web_c_29; firstTimeOpenShop=forever; ARK_ID=JS57876463e7b177ff7f8cdc72a1d04e475787; _gcl_au=1.1.1357497305.1659590852; _ga=GA1.1.100438385.1659590852; appier_utmz=%7B%7D; _atrk_siteuid=Mrv52RHihOwPefQM; bid=a7b303ebacb1749b2ed939b325a840f8; isBI=1; _mwa_uniVisitorInfo=1659590853030953142.1659590853030.1.1659590853032; _mwa_uniCampaignInfo=1659590853031518212.1659590853031; TN=undefined; CN=undefined; CM=undefined; cto_bundle=K4votl9TT2FsR01KaFFJeVA1QlRTcTNyMnBjbzNibFdPa2NxYnMlMkJVOW1XTVN4RE5rcEw3b1k5ZjFMbHlXcVQ1SElycldhYVBQSXh5R2hia2hNemZqRDI1bjVtOWhkQzF1Y2lYNnhMZjdtRkVDUkVKQnklMkYyeSUyRnZPVGNDdGNRbXBaUFV0aDROd2hmOTdrZUxsayUyQkQlMkJLZWl2NVh3JTNEJTNE; _ga_ZF0L1038WJ=GS1.1.1659599460.2.0.1659599460.0; JSESSIONID=563DBEA1B8E10D1444B2FE6DD7B70FAC-m1.c1-shop29");
                
                if (referer == null) {
                    urlConnection.setRequestProperty("Referer", url);
                } else {
                    urlConnection.setRequestProperty("Referer", referer);
                }

                urlConnection.setRequestProperty("Host", obj.getHost());
                urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

//                System.out.println("userAgent: "+userAgent);
//                System.out.println("referer: " + referer);
//                System.out.println(obj.getHost());

//                urlConnection.setRequestProperty("Cookie", "ccsession=201902231523501c183c0a07b0567e; NSC_MC-xxx.npnptipq.dpn.ux*80=ffffffff0934060d45525d5f4f58455e445a4a423660; JSESSIONID=dKRy4TdER9JjAKsUvKRq4lcDeQ4wAKSy4lE1hz4EJ50L93d9Xk1O7480yQUMzuBsqUKGd0.0000000.ecap01_servlet_mom28");
                urlConnection.setRequestProperty("Pragma", "no-cache");
                urlConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                
                //System.setProperty("https.protocols", "TLSv1.1");
                //System.setProperty("javax.net.ssl.trustStore", "C:/server.jks");
                //System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
                //statusCode = extractStatusCode(urlConnection, urlConnection.getHeaderField(0));
                String out = readAsString(urlConnection.getInputStream(), urlConnection.getContentEncoding());
                System.out.println(out);
                
                
                statusCode = extractStatusCode(urlConnection.getHeaderField(0));

                if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                        || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                        || statusCode == HttpURLConnection.HTTP_SEE_OTHER
                        //https://zh.wikipedia.org/wiki/HTTP_307
                        || statusCode == 307
                        || statusCode == 500
                        || statusCode == 504) {
                    System.out.println("retry: " + i + ", " + url);
//                    String out = readAsString(urlConnection.getInputStream(), urlConnection.getContentEncoding());
//                    System.out.println(out);
                    continue;
                } else if (statusCode == HttpURLConnection.HTTP_OK) {
                    //keep response as binary data


//                    System.out.println("htmlbyte.size: "+htmlByte.length);
                    //


                    returnContent = readAsString(urlConnection.getInputStream(), urlConnection.getContentEncoding());
//                    System.out.println("return content size: "+returnContent.length()+", "+returnContent);

                } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    throw new FileNotFoundException("http status: " + statusCode);
                } else {
                    throw new IllegalStateException("unhandle http status: " + statusCode);
                }
                break;

            }
        } catch (Exception err) {
            err.printStackTrace();
        }
	}

	

    private static int statusCode = -1;

    private static String returnContent = null;

    private byte[] htmlByte = new byte[1024];

    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";


    //    private String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    private static String accept = "application/json, text/javascript, */*";

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    private Map<String, String> postData = new HashMap<>();

    public Map<String, String> getPostData() {
        return postData;
    }

    public void setPostData(Map<String, String> postData) {
        this.postData = postData;
    }

    public static String referer = null;

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public void open(String url) throws Exception {
        System.out.println("request: "+url);

        

    }

    public static String readAsString(InputStream inputStream, String encoding) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader reader = null;

        System.out.println("encoding"+encoding);

        if ("gzip".equals(encoding)) {
            reader = new InputStreamReader(new GZIPInputStream(inputStream), "UTF-8");
        } else {
            reader = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
        }

        for (; ; ) {
            int rsz = reader.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        System.out.println("out:"+out.toString());
        return out.toString();
    }

    /**
     * Read HTTP document text.
     *
     * @param is
     * @throws IOException
     */
    private void readAsByte(InputStream is) throws IOException {
        int len = 1024;
        byte[] buff = new byte[len];
        int r1;
        int currentIdx = 0;

        try {
            while ((r1 = is.read(buff)) != -1) {
                if (currentIdx + r1 >= this.htmlByte.length) {
                    this.htmlByte = Arrays.copyOf(this.htmlByte, this.htmlByte.length * 2);
                }

                System.arraycopy(buff, 0, this.htmlByte, currentIdx, r1);
                currentIdx += r1;
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


    /**
     * Extract status code.
     *
     * @param header
     * @return
     */
    private int extractStatusCode(URLConnection urlConnection, String header) {
        try {
            if (header.startsWith("HTTP/1.")) {
                String[] values = header.split(" ");
                return Integer.parseInt(values[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Extract status code.
     *
     * @param header
     * @return
     */
    public static int extractStatusCode(String header) {
    	System.out.println("header:"+header);
        try {
        	if (header.startsWith("HTTP/1.")) {
                String[] values = header.split(" ");
                return Integer.parseInt(values[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Extract document charset.
     *
     * @param responseCharset
     * @return
     */
    private String extractCharset(String responseCharset) {
        return extractCharset(responseCharset, null);
    }

    /**
     * Extract document charset.
     *
     * @param responseCharset
     * @param defaultCharset
     * @return
     */
    private String extractCharset(String responseCharset, String defaultCharset) {
        try {
            String[] rc = responseCharset.split(";");

            if (rc != null) {
                for (int i = 0, size = rc.length; i < size; i++) {
                    if (rc[i].indexOf("charset") > -1) {
                        String charset = rc[i].toLowerCase();
                        charset = charset.replaceAll("charset=", "");
                        charset = charset.trim();
                        Charset charsetObj = null;

                        try {
                            charsetObj = Charset.forName(charset);
                        } catch (IllegalCharsetNameException e) {
                            e.printStackTrace();
                        }
                        return charsetObj.name();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultCharset;
    }

}
