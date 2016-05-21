package com.buses;

import android.content.Context;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by federico on 20/05/2016.
 */
public class ApiClient {

    private static String API_URL = "http://care.xanthops.com/api/v1/";
    private  static busesApiInterface mInterface;


    public static  busesApiInterface getServiceClient(){


        if(mInterface == null){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);



            Retrofit retrofit = new  Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();


         mInterface =  retrofit.create(busesApiInterface.class);
        }

        return mInterface;



    }

    public interface busesApiInterface{
        @GET("pathology")
        Call<List<Pathology>> getPathologies();


        @GET("pathology")
        Observable<List<Pathology>> getPathologiesRx();

    }
}


