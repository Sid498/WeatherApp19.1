package com.example.sid.weatherapp191;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

/**
 * Created by SID on 9/14/2017.
 */

public class DataProcessing extends AsyncTask<Void, Void, String> {

    private DataListener dataListener;
    private Context mcontext;
    private String URL;

    public DataProcessing(Context context, String url, DataListener listener) {
        dataListener=listener;
        mcontext=context;
        URL=url;
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(120,TimeUnit.SECONDS);

        Request request =new Request.Builder().url(URL).build();
        String responseData = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                responseData=response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        try {
            dataListener.getData(aVoid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
