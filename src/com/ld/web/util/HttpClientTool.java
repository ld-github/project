package com.ld.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * <p>Title: HttpClientTool </p>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p>Description: HttpClient工具类 </p>
 * 
 * @author LD
 * 
 * @date 2015-08-06
 */
public class HttpClientTool {

    private static Logger logger = Logger.getLogger(HttpClientTool.class);

    /**
     * Http Post
     * 
     * @param url
     * @param header
     * @param params
     * @throws Exception
     * @return
     */
    public static String post(String url, Map<String, String> header, Map<String, String> params) throws Exception {
        logger.info(String.format("Httpclient send data: %s", JsonMapper.getInstance().toJson(params)));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            if (null != header && !header.isEmpty()) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httppost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            if (null != params && !params.isEmpty()) {
                httppost.setEntity(new UrlEncodedFormEntity(getNameValuePair(params), "utf-8"));
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error(String.format("Httpclient close exception: %s", e.getMessage()), e);
                }
            }
        }
    }

    /**
     * Http Post
     * 
     * @param url
     * @param json
     * @throws Exception
     * @return
     */
    public static String post(String url, String json) throws Exception {
        logger.info(String.format("Httpclient send data: %s", json));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(url);

            if (!StringUtil.isEmpty(json)) {
                StringEntity entity = new StringEntity(json, "utf-8");
                entity.setContentEncoding("utf-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httppost.setEntity(entity);
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            if (null != httpclient) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    logger.error(String.format("Httpclient close exception: %s", e.getMessage()), e);
                }
            }
        }
    }

    /**
     * Convert Map<String, String> to List<NameValuePair>
     * 
     * @param params
     * @return
     */
    private static List<NameValuePair> getNameValuePair(Map<String, String> params) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : params.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }
}
