package com.example.xd.tactics;

import android.util.Log;

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
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 18-4-23.
 */

public class Internet {

    public JSONObject tacticsInit(int user_id) throws Exception
    {
        JSONObject object = null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("id", String.valueOf(user_id)));
        URI uri = URIUtils.createURI("http", TacticsConf.IP,
                TacticsConf.PORT,
                TacticsConf.TACTICSINIT,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:"+uri.toString());
        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            object = new JSONObject(contentString);
        }
        httpclient.getConnectionManager().shutdown();
        return object;
    }

    public JSONObject oTacticsEquip(int user_id, int otactics_id) throws Exception
    {
        JSONObject object = null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("otactics_id", String.valueOf(otactics_id)));
        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
        URI uri = URIUtils.createURI("http", TacticsConf.IP,
                TacticsConf.PORT,
                TacticsConf.OTACTICSEQUIP,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:"+uri.toString());
        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            object = new JSONObject(contentString);
        }
        httpclient.getConnectionManager().shutdown();
        return object;
    }

    public JSONObject dTacticsEquip(int user_id, int dtactics_id) throws Exception {
        JSONObject object = null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        qparams.add(new BasicNameValuePair("dtactics_id", String.valueOf(dtactics_id)));
        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
        URI uri = URIUtils.createURI("http", TacticsConf.IP,
                TacticsConf.PORT,
                TacticsConf.DTACTICSEQUIP,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:" + uri.toString());
        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            object = new JSONObject(contentString);
        }
        httpclient.getConnectionManager().shutdown();
        return object;
    }
}

