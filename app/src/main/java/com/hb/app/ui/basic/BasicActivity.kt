package com.hb.app.ui.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hb.app.R
import com.hb.base.extension.enableFullScreenMode

abstract class BasicActivity<F : Fragment> :
    AppCompatActivity(R.layout.basic_activity) {

    abstract fun createFragment(): F

    override fun onCreate(savedInstanceState: Bundle?) {
        enableFullScreenMode()
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                createFragment().apply {
                    arguments = intent.extras
                }
            )
            .commitNow()
    }
}
