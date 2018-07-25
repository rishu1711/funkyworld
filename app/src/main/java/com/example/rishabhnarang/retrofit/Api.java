package com.example.rishabhnarang.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL ="https://funkyworld.in/wp-json/wc/v2/";

    @GET("products?consumer_key=ck_fef0cba6ad9375d009c7f264f7d2176935d01ab9&consumer_secret=cs_08ce17479dee8a322534dc1ec5c4459556d1f578")
    Call<List<Product>> getProduct();

}
