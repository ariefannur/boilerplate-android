package com.github.ariefannur.boilerplate.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(){

    protected fun Disposable.track() {
        Constants.compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        Constants.compositeDisposable.clear()
    }
}