package com.example.homework11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<Model>>().apply {
        mutableListOf<Model>()
    }

    val _itemsLiveData : MutableLiveData<List<Model>> = itemsLiveData

    private val loading = MutableLiveData<Boolean>()
    val _loading : MutableLiveData<Boolean> = loading

    fun init(){
        CoroutineScope(Dispatchers.IO).launch {
            getItems()
        }
    }

    private suspend fun  getItems(){
        loading.postValue(true)


        val itemsList = RetrofitService.RetrofitService().getCountryList()

        if (itemsList.isSuccessful){
            val items = itemsList.body()
            itemsLiveData.postValue(items)
        }else {
            itemsList.code()
        }
        loading.postValue(false)

    }

}