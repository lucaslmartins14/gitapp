package com.example.gitapp.ui

import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.gitapp.R
import com.example.gitapp.model.GrRepository
import com.example.gitapp.model.entity.Object
import com.example.gitapp.retrofit.RetrofitInitializer
import com.example.gitapp.utils.GenericCallback
import com.example.gitapp.viewmodel.GrViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val PREFS = "com.example.gitapp"
    val PRIVATE_MODE = 0
    lateinit var prefs : SharedPreferences
    var page = 1
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RepositoryAdapter
    var loading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = this.getSharedPreferences(PREFS, PRIVATE_MODE)
            if (GrRepository(application).listGrs().isEmpty()){
                this.getSharedPreferences(PREFS, PRIVATE_MODE).edit().clear().apply()
                this.getSharedPreferences(PREFS, PRIVATE_MODE).edit()
                    .putInt("page", 1).apply()
            }
        page = prefs.getInt("page", 1)
        layoutManager = LinearLayoutManager(this)
        (recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recyclerView.layoutManager = layoutManager
        adapter = RepositoryAdapter(this)
        recyclerView.adapter = adapter
        GrViewModel(application).allGrs.observe(this, Observer { grs ->
            adapter.setGrs(grs)
        })
        val callback = object : GenericCallback<String>(){
            override fun call(result: String) {
                if (this.success || !this.success){
                    loading = false
                    progressBar.visibility = View.GONE
                }

            }

        }
        callGrs(callback)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val pastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val total = recyclerView.layoutManager?.itemCount
                    if ((pastVisibleItem + 1) == (total) && (!loading)) {
                        page++
                        callGrs(callback)
                    }
            }
        })
    }

    fun callGrs(callback: GenericCallback<String>) {
        loading = true
        progressBar.visibility = View.VISIBLE
        val callGr =
            RetrofitInitializer().grService()!!.getGrs("language:kotlin", "stars", page)
        lifecycleScope.launch(Dispatchers.IO) {
            delay(500)
            callGr.clone().enqueue(object : Callback<Object> {
                override fun onResponse(call: Call<Object>, response: Response<Object>) {
                    if (response.code() == 200){
                        callback.success = true
                        callback.call("success")
                        Log.d("Tamojunto", "success" + response.message())
                        response.body()?.let {
                            GrRepository(application).createAll(response.body()!!.items)
                            this@MainActivity.getSharedPreferences(PREFS, PRIVATE_MODE).edit()
                                .putInt("page", page).apply()
                        }
                    }else{
                        callback.success = false
                        callback.call("error")
                    }

                }
                override fun onFailure(call: Call<Object>, t: Throwable) {
                    callback.success = false
                    callback.call("error")
                    Log.d("Tamojunto", "fail" + t.toString())
                }
            })
        }
    }
}
