package com.shop.denisa.shop;

import com.shop.denisa.shop.domain.ShoppingItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Denisa on 06.02.2017.
 */

public interface ServiceInterface {
    public final String SERVICE_ENDPOINT = "http://192.168.0.4:3000";

    @GET("/items")
    Call<List<ShoppingItem>> getGoods();

    @POST("/add")
    Call<ShoppingItem> postUser(@Body ShoppingItem good);

    @POST("/buy")
    Call<ShoppingItem> buy(@Body ShoppingItem good);
}
