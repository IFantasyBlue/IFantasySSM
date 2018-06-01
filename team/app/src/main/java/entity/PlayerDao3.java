package entity;

import android.util.Log;

import com.example.xd.team.PlayerConf;

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
 * Created by xd on 18-4-24.
 */

public class PlayerDao3 {
    public JSONObject teamInit(int user_id,int player_in_id,int player_out_id,String url) throws Exception{
        JSONObject json = null;
        HttpClient httpClient = new DefaultHttpClient();
        List<NameValuePair> qparams = new ArrayList<>();
        //qparams.add(new BasicNameValuePair("method", "getDetail"));
        qparams.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
        qparams.add(new BasicNameValuePair("player_in_id", String.valueOf(player_in_id)));
        qparams.add(new BasicNameValuePair("player_out_id", String.valueOf(player_out_id)));
        URI uri = URIUtils.createURI("http", PlayerConf.IP,
                PlayerConf.PORT,
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
        Log.i("tag","abc"+json.toString());
        return json;
    }
}
