package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student

class DetailViewModel(application: Application) : AndroidViewModel(application)  {
    val studentLiveData = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson(it,Student::class.java)
                studentLiveData.value = result
                Log.d("showvolley",it)
            },
            {
                Log.d("errorvolley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)

//        studentLiveData.value = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
    }
}