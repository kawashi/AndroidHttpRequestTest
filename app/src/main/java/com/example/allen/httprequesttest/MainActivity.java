package com.example.allen.httprequesttest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.util.Log;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressWarnings("Convert2MethodRef")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // HTTP通信
        findViewById(R.id.sendButton).setOnClickListener((v) -> {
             new Thread(() -> {
                 sendRequest();
             }).start();
        });

        // SharedPreference
        findViewById(R.id.saveButton).setOnClickListener((v) -> {
            SharedPreferences pref = getSharedPreferences("userData", MODE_PRIVATE);
            EditText editText = (EditText)findViewById(R.id.editText);

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("name", editText.getText().toString());
            editor.commit();
        });

        // SharedPreference
        findViewById(R.id.updateButton).setOnClickListener((v) -> {
            SharedPreferences pref = getSharedPreferences("userData", MODE_PRIVATE);
            TextView nameView = (TextView)findViewById(R.id.nameView);
            nameView.setText(pref.getString("name", "none"));
        });
    }

    private void sendRequest(){
        HttpClient client = new HttpClient("http://www.drk7.jp/weather/xml/27.xml");
        Handler handler   = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                TextView textView = (TextView)findViewById(R.id.result);
                textView.setText((String) msg.obj);
            }
        };

        handler.sendMessage(handler.obtainMessage(1, client.fetch()));
    }
}


class HttpClient{
    private HttpURLConnection client;

    HttpClient(String urlStr){
        try{
            URL url = new URL(urlStr);
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String fetch(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            String allStr;
            String buffer = reader.readLine();
            allStr = buffer;
            while( buffer != null ) {
                allStr = allStr + "\n" + buffer;
                Log.d("test", buffer);
                buffer = reader.readLine();
            }
            reader.close();
            return "あはは！";
        } catch (Exception e) {
            e.printStackTrace();
            return "エラー";
        }
    }
}
