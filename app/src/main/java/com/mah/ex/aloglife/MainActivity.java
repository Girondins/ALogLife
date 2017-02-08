package com.mah.ex.aloglife;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private WebView wb;
    private static final String CALLBACK_URL = "https://localhost";
    private final String CLIENT_ID ="daacd362-05d2-4d15-ab2c-ed07848469d4";
    private Controller cont;
    private ExecuteThread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
        wb = (WebView) findViewById(R.id.authWebID);
        wb.clearCache(true);
        cont = new Controller();
        thread = new ExecuteThread();
        thread.start();

        wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("localhost")) {
                    Log.d("code",url);
                    Uri uri =  Uri.parse( url );;
                    final String codice=(uri.getQueryParameter("code").toString());
                    Log.d("codice", codice);
                    cont.setAuthCode(codice);
          //          thread.execute(new GetUserToken(codice));
                    return true;
                }

                Log.d("My Webview ", url);

                // return true; //Indicates WebView to NOT load the url;
                return false; //Allow WebView to load url
            }
        });
        wb.loadUrl("https://platform.lifelog.sonymobile.com/oauth/2/authorize?client_id="+CLIENT_ID+ "&scope=lifelog.profile.read+lifelog.activities.read+lifelog.locations.read");
    }



}
