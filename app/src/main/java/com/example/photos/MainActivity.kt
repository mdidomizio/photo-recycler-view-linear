package com.example.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.photos.API.api
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView
    private lateinit var buttonItem: Button
    private lateinit var buttonList: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonItem = findViewById(R.id.buttonLoad)
        text = findViewById(R.id.text)
        buttonList = findViewById(R.id.buttonLoadList)

        buttonItem.setOnClickListener(){
            clickedButtonItem()
        }

        buttonList.setOnClickListener(){
            clickedButtonList()
        }
    }
    private fun clickedButtonItem (){

        api.getFirstProduct().enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                val product = response.body()
                text.text = "The item name is ${product?.title},\n it costs ${product?.price}€ and is from brand ${product?.brand}"

            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong with the item" ,
                    Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun clickedButtonList(){
        api.getProductsList().enqueue(object : Callback<ProductsList>{
            override fun onResponse(call: Call<ProductsList>, response: Response<ProductsList>) {
                val productsList = response.body()
                val products = productsList?.products?.map{ "\nName: ${it.title}; Price: ${it.price}"}?.joinToString ("\n")

                /*
                var titleList : String = ""
                var priceList: Int = -1

                if (productsList != null) {
                    for (item in productsList.products) {
                        titleList += item.title
                        priceList = item.price
                    }
                } */



                text.text = products
            }

            override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                Toast.makeText(applicationContext, "Something went wrong with the list" ,
                    Toast.LENGTH_SHORT).show()
            }
        })

    }
}