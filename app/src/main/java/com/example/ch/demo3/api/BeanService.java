package com.example.ch.demo3.api;

import com.example.ch.demo3.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BeanService {
    String url = "http://gank.io/api/";

    @GET("data/福利/10/1")
    Observable<Bean> getData();
}
