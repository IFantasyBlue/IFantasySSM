package com.teamblue.xd.ifantasy_blue;

/**
 * Created by xd on 18-4-10.
 */

import android.util.Log;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

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
import org.json.JSONObject;

import entity.User;


public class UserModel {

    private String token;

    public UserModel(String token){
        this.token = token;
    }

    public JSONObject userlogin(String mobile, int akey) throws Exception
    {
        User user = null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("phone",mobile));
        qparams.add(new BasicNameValuePair("akey",String.valueOf(akey)));
        URI uri = URIUtils.createURI("http", ServerConfiguration.IP,
                ServerConfiguration.PORT,
                ServerConfiguration.USERLOGINSERVICEURI,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:"+uri.toString());
        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        JSONObject o = null;
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            o = new JSONObject(contentString);
        }

        httpclient.getConnectionManager().shutdown();
        return o;
    }

    public SendSmsResponse sendAKey(String mobile) throws Exception
    {
        SendSmsResponse ssr = null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("phone",mobile));
        URI uri = URIUtils.createURI("http", ServerConfiguration.IP,
                ServerConfiguration.PORT,
                ServerConfiguration.USERSENDAKEYSERVICEURI,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:"+uri.toString());
        HttpGet httpget = new HttpGet(uri);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        JSONObject o = null;
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            o = new JSONObject(contentString);
            int status =o.getInt("status");

            Log.i("tag","status:"+ status);
            JSONObject s = o.getJSONObject("ssr");
            ssr = new SendSmsResponse();
            ssr.setCode(s.getString("code"));
            Log.i("tag","SendsmsResponse:"+ ssr.getCode());
        }
        httpclient.getConnectionManager().shutdown();
        return ssr;
    }

    public JSONObject userSign(String mobile, String name, String team_id) throws Exception
    {
        JSONObject o =null;
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("phone",mobile));
        qparams.add(new BasicNameValuePair("name",name));
        qparams.add(new BasicNameValuePair("team_id",team_id));
        URI uri = URIUtils.createURI("http", ServerConfiguration.IP,
                ServerConfiguration.PORT,
                ServerConfiguration.USERSIGNSERVICEURI,
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
            o = new JSONObject(contentString);
            int status =o.getInt("status");
            Log.i("tag","status:"+ status);
        }
        httpclient.getConnectionManager().shutdown();
        return o;
    }

    public JSONObject getRank(int id) throws Exception
    {
        HttpClient httpclient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("id",String.valueOf(id)));
        URI uri = URIUtils.createURI("http", ServerConfiguration.IP,
                ServerConfiguration.PORT,
                ServerConfiguration.RANKPOWERTOP20,
                URLEncodedUtils.format(qparams, "UTF-8"), null);
        Log.i("tag", "uri:"+uri.toString());

        HttpGet httpget = new HttpGet(uri);
        httpget.addHeader("token",token);
        //HttpPost httpget=new HttpPost(uri);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        JSONObject o = null;
        if (entity != null)
        {
            String contentString = EntityUtils.toString(entity);
            Log.i("tag", contentString);
            o = new JSONObject(contentString);
        }

        httpclient.getConnectionManager().shutdown();
        return o;
    }

}
