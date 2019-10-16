package com.example.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.gitapp.R
import com.example.gitapp.model.GrRepository
import com.example.gitapp.model.entity.GR
import com.example.gitapp.model.entity.Object
import com.example.gitapp.retrofit.RetrofitInitializer
import com.example.gitapp.viewmodel.GrViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callGr = RetrofitInitializer().grService()!!.getGrs("language:kotlin","stars",1)

        callGr.clone().enqueue(object : Callback <Object>{
            override fun onResponse(call: Call<Object>, response: Response<Object>) {
                Log.d("Tamojunto", "success")
                response.body().let {
                GrRepository(application).createAll(response.body()!!.items)
                }
            }

            override fun onFailure(call: Call<Object>, t: Throwable) {
                Log.d("Tamojunto", "fail")
            }

        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = LinearLayoutManager(this)

        GrViewModel(application).allGrs.observe(this, Observer { grs->
            recyclerView.adapter = RepositoryAdapter(this,grs)
        })
    }
}
