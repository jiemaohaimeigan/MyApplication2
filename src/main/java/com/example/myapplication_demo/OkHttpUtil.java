package com.example.myapplication_demo;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    private static Map<String, List<Cookie>> cookieStore = new HashMap<>();
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar()
            {
                @Override
                public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list)
                {
                    cookieStore.put(httpUrl.host(), list);
                }

                @Override
                public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl)
                {
                    List<Cookie> cookies = cookieStore.get(httpUrl.host());
                    return cookies == null ? new ArrayList<>() : cookies;
                }
            }).build();
    //1.新建线程池
    static ExecutorService theradpool = Executors.newFixedThreadPool(30);

    public static String getRequest(final String url) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                //2.差创建request
                Request request = new Request.Builder()
                        .url(url) //把url参数传递进来
                        .build() ;  //调用build()来执行

                //3.获取服务器返回的数据
                Response response = okHttpClient.newCall(request).execute() ;

                //4.对response进行预处理,判断response真的是服务器返回的数据而且不为空
                if (response.isSuccessful() && response.body() != null){
                    //把服务器返回的数据传递过来的数据转化为String类型的数据
                    return  response.body().string();  }
                    else {
                        return null ;
                    }
                }
        });
        //把futureTask放到线程池中去执行
        theradpool.execute(futureTask);
        return futureTask.get() ;  //需要抛出异常
    }

    public static String postRequest(final String url, Map<String,String> map) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(new Callable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public Object call() throws Exception {

                FormBody.Builder builder = new FormBody.Builder() ;
                //由于不知道map中有什么数据，先进行遍历
                map.forEach(builder::add); //把add里面的数据一一添加到map中
                FormBody body = builder.build();
                //2.差创建request
                Request request = new Request.Builder()
                        .url(url) //把url参数传递进来
                        .post(body)
                        .build() ;  //调用build()来执行

                //3.获取服务器返回的数据
                Response response = okHttpClient.newCall(request).execute() ;

                //4.对response进行预处理,判断response真的是服务器返回的数据而且不为空
                if (response.isSuccessful() && response.body() != null){
                    //把服务器返回的数据传递过来的数据转化为String类型的数据
                    return  response.body().string();  }
                else {
                    return null ;
                }
            }
        });
        //把futureTask放到线程池中去执行
        theradpool.execute(futureTask);
        return futureTask.get() ;  //需要抛出异常
    }
}
