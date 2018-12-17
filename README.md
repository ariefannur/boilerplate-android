# Ireng Jambon Boilerplate Android

<img src="asset/ireng-jambon.png" alt="icon ireng jambon" width="80" height="80">
Ireng Jambon Android boilerplate

## Specification
- Dagger
- Kotlin
- Retrofit/Okhttp
- Picaso
- RxJava
- Android Architecture Room, LiveData, ViewModel
- MVVM Pattern
- Proguard Rule


## Usage
very easy to use just as a simple create view
- Create ViewModel and Provide it in the modul 
```
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
```


License
=======

    Copyright 2018 Arief Maffrudin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
