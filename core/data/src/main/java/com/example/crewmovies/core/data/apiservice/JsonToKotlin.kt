package com.example.crewmovies.core.data.apiservice

import android.os.Build
import android.service.autofill.UserData
import androidx.annotation.RequiresApi
import com.google.gson.Gson

class JsonToKotlin {
    val myJson = """
	{
	    "user_name": "john123",
	    "email": "john@example.com",
	    "name": "John Doe"
	}
	""".trimIndent()

    val gson = Gson()
    @RequiresApi(Build.VERSION_CODES.P)
    var mUser = gson.fromJson(myJson, UserData::class.java)
    //println(mUser.userName)
}

