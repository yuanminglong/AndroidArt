package com.yuan.androidart.ui.net;

import android.os.SystemClock;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpMethod {
    private static final String TAG = HttpMethod.class.getSimpleName();
    private static HttpMethod instance;
    private OkHttpClient client;
    private HttpMethod(){
        client = new OkHttpClient.Builder().build();
    }
    public static HttpMethod getHttpMethod(){
        if (instance==null){
            synchronized(HttpMethod.class){
                if (instance==null){
                    instance = new HttpMethod();
                }
            }
        }
        return instance;
    }

    public String getTop(){
        String respons = "";
        Map<String,String> param = new HashMap();
        param.put("type","top");
        param.put("key","3ec462f44f40dffa8899dbe97f99c4f0");
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://v.juhe.cn/toutiao/index").newBuilder();
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.setType(MultipartBody.FORM);
        Log.d(TAG,urlBuilder.toString());
        if (param!=null){
            for (String key : param.keySet() ) {
                urlBuilder.setQueryParameter(key,param.get(key));
            }
        }
        Request rq = new Request.Builder().url(urlBuilder.build()).get().build();
        Log.d(TAG,urlBuilder.toString());
        try {
           respons = client.newCall(rq).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            respons = e.toString();
        }
        return respons;
    }
    public String post(){
        String respons = "";
        Map<String,String> param = new HashMap();
        param.put("type","top");
        param.put("key","3ec462f44f40dffa8899dbe97f99c4f0");
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.setType(MultipartBody.FORM);
        if (param!=null){
            for (String key : param.keySet() ) {
                multipartBuilder.addFormDataPart(key,param.get(key));
            }
        }
        Request rq = new Request.Builder().url("http://v.juhe.cn/toutiao/index").post(multipartBuilder.build()).build();
        try {
            respons = client.newCall(rq).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            respons = e.toString();
        }
        return respons;
    }

    public void postFile(){


        HashMap<String,String> files= new HashMap<>();
        if (files != null) {
            MultipartBody.Builder multipartBodyBuilder =new MultipartBody.Builder();
            for (String key : files.keySet()) {
                //重点：RequestBody create(MediaType contentType, final File file)构造文件请求体RequestBody
                multipartBodyBuilder.addFormDataPart(key, "", RequestBody.create(MediaType.parse("multipart/form-data"), files.get(key)));
            }

        }
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread.currentThread().interrupt();
    }

}
