package com.example.xd.demo;

/**
 * Created by xd on 18-4-18.
 */
import android.util.Log;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class recruitModel {
    public JSONObject luckilyPost(int user_id,String url) throws Exception{
        JSONObject json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));

        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONObject(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
    public JSONArray Post(int user_id,String url) throws Exception{
        JSONArray json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));

        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONArray(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
    public JSONObject playerInfoGet(String playerName,String url) throws Exception{
        JSONObject json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("playerName", String.valueOf(playerName)));

        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONObject(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
    public JSONObject addPlayer(int user_id,String playerName,String url) throws Exception{
        JSONObject json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("playerName", String.valueOf(playerName)));
        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONObject(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
    public JSONArray showPost(int user_id, String url) throws Exception{
        JSONArray json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));

        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONArray(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
    public JSONArray directPost(int user_id,String position,String url) throws Exception{
        JSONArray json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));

        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
        qparams.add(new BasicNameValuePair("position", String.valueOf(position)));
        URI uri = URIUtils.createURI("http", serverConfiguration.IP,
                serverConfiguration.PORT,
                url,
                URLEncodedUtils.format(qparams, "UTF-8"), null);

        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        Log.i("tag", "uri:"+uri.toString());
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            json = new JSONArray(contentString);

        }
        httpClient.getConnectionManager().shutdown();
        return json;
    }
}