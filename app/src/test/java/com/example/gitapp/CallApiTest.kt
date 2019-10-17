package com.example.gitapp

import com.example.gitapp.retrofit.RetrofitInitializer
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.IOException


class CallApiTest {
    @Test
    fun callSuccess() {

        val apiCall = RetrofitInitializer().grService()!!.getGrs("language:kotlin", "stars", 1)


        try {
            val response = apiCall.execute()
            assertTrue(response.isSuccessful())

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}