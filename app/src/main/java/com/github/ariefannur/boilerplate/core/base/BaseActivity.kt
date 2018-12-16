package com.github.ariefannur.boilerplate.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ariefannur.boilerplate.core.network.Api
import com.github.ariefannur.boilerplate.feature.MainViewModel
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var api: Api
    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).appComponent.inject(this)
        setContentView(setLayout())
        setUpVariable()

    }

    abstract fun setLayout():Int
    abstract fun setUpVariable()
}


