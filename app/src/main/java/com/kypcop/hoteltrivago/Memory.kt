package com.kypcop.hoteltrivago

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Memory {


    const val PREF = "pref"
    const val LIST = "list"
    val gson = Gson()


    lateinit var sharedPref: SharedPreferences
    lateinit var clientList: ArrayList<Client>

    fun start(context: Context){
        initPref(context)
        val myType = object : TypeToken<ArrayList<Client>>() {}.type
        val string = sharedPref.getString(LIST, "")
        clientList = if(string == ""){
            ArrayList<Client>()
        } else{
            gson.fromJson(string, myType)
        }
    }

    private fun initPref(context: Context){
        sharedPref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
    }

    fun saveClient(client: Client){
        clientList.add(client)
        save()
    }

    fun deleteClient(index: Int){
        clientList.removeAt(index)
        save()
    }

    private fun save(){
        val editor = sharedPref.edit()
        editor.putString(LIST, gson.toJson(clientList))
        editor.apply()
    }

}