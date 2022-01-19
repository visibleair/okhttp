package com.example.okhttpproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var okHttpClient = OkHttpClient()
    private lateinit var request: Request

    var filter = ""
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filter = "new"
        url = "http://cinema.areas.su/movies?filter=$filter"


        request = Request.Builder()
            .url(url)
            .build()

        okHttpClient.newCall(request).enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("tag", "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code == 200) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "200", Toast.LENGTH_SHORT).show()
                        Log.i("tag", "onResponse: ${response.body!!.string()}")
                    }
                } else {
                    runOnUiThread { Toast.makeText(this@MainActivity, "401", Toast.LENGTH_SHORT).show() }
                }
            }

        })
    }


}