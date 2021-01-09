package com.kypcop.hoteltrivago

import android.content.Context
import java.util.*

class Client(val fname: String,
             val lname: String,
             val age: Int,
             val sex: SEX,
             val room: Int) {
    enum class SEX {
        MALE, FEMALE;

        fun getString(context: Context): String{
            return if(this == MALE) context.getString(R.string.male)
            else context.getString(R.string.female)
        }
    }
}