package view.utils;

import java.net.URI;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {

    public static String httpGet(URI uri) throws Exception {
        Logger.getLogger(HttpUtil.class).debug("requesting: " + uri);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpParams paras = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(paras, 10000);

        HttpGet httpGet = new HttpGet(uri);
        HttpResponse response = httpclient.execute(httpGet);

        StringBuilder sb = new StringBuilder(100);
        try {
            HttpEntity entity = response.getEntity();
            Scanner scan = new Scanner(entity.getContent());

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }

            Logger.getLogger(HttpUtil.class).debug("receive: " + sb.toString());

            EntityUtils.consume(entity);
            scan.close();
        } finally {
            httpGet.releaseConnection();
        }
        return sb.toString();
    }

    public static String httpPost(URI uri, List<NameValuePair> params) throws Exception {
        Logger.getLogger(HttpUtil.class).debug("requesting: " + uri);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpParams paras = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(paras, 10000);

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = httpclient.execute(httpPost);

        StringBuilder sb = new StringBuilder(100);
        try {
            HttpEntity entity = response.getEntity();
            Scanner scan = new Scanner(entity.getContent());

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }

            Logger.getLogger(HttpUtil.class).debug("receive: " + sb.toString());

            EntityUtils.consume(entity);
            scan.close();
        } finally {
            httpPost.releaseConnection();
        }
        return sb.toString();
    }

    private HttpUtil() {
    }
}
