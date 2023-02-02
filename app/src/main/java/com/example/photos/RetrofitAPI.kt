package com.example.photos

import retrofit2.Call
import retrofit2.http.GET

interface DummyAPI{
    @GET("products/1")
    fun getFirstProduct(): Call<Product>

    @GET("products")
    fun getProductsList(): Call<ProductsList>


}