package com.example.gitapp.model

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gitapp.ProjectDatabase
import com.example.gitapp.model.entity.GR

class GrRepository constructor(application: Application) {

    private val dao: GrDAO
    internal val allGrs: LiveData<List<GR>>

    init {
        val db = ProjectDatabase.getDatabase(application)
        dao = db!!.grDao()
        allGrs = dao.allGrs
    }

    fun listGrs(): ArrayList<GR> {
        return dao.listGrs as ArrayList<GR>
    }

    fun getGrs() : LiveData<List<GR>> {
        return dao.getGrs()
    }

    fun createAll(grs: List<GR>) {
        createAllAsyncTask(dao).execute(grs)
    }

    private class createAllAsyncTask internal constructor(private val mAsyncTaskDao: GrDAO) :
        AsyncTask<List<GR>, Void, Void>() {

        override fun doInBackground(vararg params: List<GR>): Void? {
            mAsyncTaskDao.insertAll(params[0])
            Log.e("Lucas", "Te amo lek " + mAsyncTaskDao.allGrs + " oloco meu" + params[0])
            return null
        }

    }

    fun update(gr: GR) {
        udateAsyncTask(dao).execute(gr)
    }

    private class udateAsyncTask internal constructor(private val mAsyncTaskDao: GrDAO) :
        AsyncTask<GR, Void, Void>() {

        override fun doInBackground(vararg params: GR): Void? {
            mAsyncTaskDao.updateItem(gr = params[0])
            return null
        }
    }

    fun insert(gr: GR) {
        insertAsyncTask(dao).execute(gr)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: GrDAO) :
        AsyncTask<GR, Void, Void>() {

        override fun doInBackground(vararg params: GR): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }

    }

}