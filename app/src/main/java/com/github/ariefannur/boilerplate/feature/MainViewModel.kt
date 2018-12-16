package com.github.ariefannur.boilerplate.feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ariefannur.boilerplate.core.base.State
import com.github.ariefannur.boilerplate.core.base.asyncRxExecutor
import com.github.ariefannur.boilerplate.core.model.Contributor
import com.github.ariefannur.boilerplate.core.network.Api
import javax.inject.Inject

class MainViewModel @Inject constructor(val api:Api) :ViewModel() {

    public var state:MutableLiveData<State> = MutableLiveData()
    public var data:MutableLiveData<Pair<List<Contributor>?, Throwable>> = MutableLiveData()

    public fun getData(){
        asyncRxExecutor(api.getContributor(), state, {
            data.postValue(
                Pair(it, Throwable("null"))
            )
        }, {
            data.postValue(
                Pair(null, it)
            )
        })
    }

}