package com.example.addlesson_37_retrofit.ui

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.User
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.app_bar_main.*


class SecondActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.liveDataUser.observe(this, {
            startActivities(it)
        })


        val navController = findNavController(R.id.nav_user_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_user,  R.id.nav_albums,R.id.nav_photos,
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    private fun startActivities(it: User?) {
        if (it == null) return
        val intent = Intent(this, MainActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt(USER_ID, it.id).apply()
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_user_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}