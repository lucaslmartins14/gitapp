package com.example.gitapp

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.example.gitapp.model.GrDAO
import com.example.gitapp.model.entity.GR
import com.example.gitapp.model.entity.Owner
import com.example.gitapp.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class GrDatabaseTest {
    @get:Rule
    var activity = ActivityTestRule<MainActivity>(MainActivity::class.java)
    var grDao: GrDAO? = null
    var grDatabase: ProjectDatabase? = null
    @Before
    fun setup() {
        grDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext<Context>(), ProjectDatabase::class.java
        ).build()
        grDao = grDatabase!!.grDao()
    }

    @After
    @Throws(IOException::class)
    fun after() {
        grDatabase!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertGr() {
        val owner = Owner(0, "test.com", "owner")
        val gr = GR(0, "reponame", 999, 999, owner)
        activity.activity.lifecycleScope.launch(Dispatchers.IO) {
            grDao!!.insert(gr)
            val test = grDao!!.listGrs.get(0)
            Assert.assertEquals(gr.name, test.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertListGr() {
        val owners = arrayListOf<Owner>()
        val grs = arrayListOf<GR>()
        owners.add(Owner(0, "test.com", "owner"))
        owners.add(Owner(1, "test1.com", "owner1"))
        owners.add(Owner(2, "test2.com", "owner2"))
        grs.add(GR(0, "reponame", 999, 999, owners.get(0)))
        grs.add(GR(1, "reponame", 2323, 9199, owners.get(1)))
        grs.add(GR(2, "reponame", 99129, 99, owners.get(2)))
        activity.activity.lifecycleScope.launch(Dispatchers.IO) {
            grDao!!.insertAll(grs)
            val test = grDao!!.listGrs
            Assert.assertEquals(grs, test)
        }
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetInOrder() {
        val owners = arrayListOf<Owner>()
        val grs = arrayListOf<GR>()
        val grsOrdered = arrayListOf<GR>()
        owners.add(Owner(0, "test.com", "owner"))
        owners.add(Owner(1, "test1.com", "owner1"))
        owners.add(Owner(2, "test2.com", "owner2"))
        grs.add(GR(0, "reponame", 999, 999, owners.get(0)))
        grs.add(GR(1, "reponame", 999999, 9199, owners.get(1)))
        grs.add(GR(2, "reponame", 99129, 99, owners.get(2)))

        grsOrdered.add(GR(1, "reponame", 999999, 9199, owners.get(1)))
        grsOrdered.add(GR(2, "reponame", 99129, 99, owners.get(2)))
        grsOrdered.add(GR(0, "reponame", 999, 999, owners.get(0)))

        activity.activity.lifecycleScope.launch(Dispatchers.IO) {
            grDao!!.insertAll(grs)
            val test = grDao!!.listGrsByOrder
            Assert.assertEquals(grsOrdered, test)
        }
    }
}