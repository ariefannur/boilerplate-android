package com.twentyonedaysapp.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        setUpVariable()
        initDependency()
    }

    abstract fun setLayout():Int
    abstract fun setUpVariable()
    abstract fun initDependency()
}


