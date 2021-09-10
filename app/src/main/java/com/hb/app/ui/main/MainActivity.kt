package com.hb.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hb.app.R

class MainActivity : AppCompatActivity(R.layout.basic_activity) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, MainFragment())
        .commitNow()
    }
  }
}