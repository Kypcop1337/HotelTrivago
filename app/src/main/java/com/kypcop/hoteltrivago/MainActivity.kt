package com.kypcop.hoteltrivago

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Memory.start(this)
        changeFragment(MainFragment(), true, false)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentByTag(NewClientFragment::class.java.name) != null ||
                supportFragmentManager.findFragmentByTag(SeeClientsFragment::class.java.name) != null) super.onBackPressed()
        else buildMessageQuit()

    }

    private fun buildMessageQuit() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this)
                .setMessage(R.string.Quit)
                .setPositiveButton("Да") { _, _ -> finish() }
                .setNegativeButton("Нет", null)
                .setCancelable(true).setTitle("Выйти?").create()
        alertDialog.show()
    }

    fun changeFragment(frag: Fragment, saveInBackStack: Boolean, animate: Boolean) {
        val backStateName = frag.javaClass.name
        try {
            val manager: FragmentManager = supportFragmentManager
            val fragmentPopped: Boolean = manager.popBackStackImmediate(backStateName, 0)
            if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
                //fragment not in back stack, create it.
                val transaction: FragmentTransaction = manager.beginTransaction()
                if (animate) {
                    Log.d(TAG, "Change Fragment: animate")
                    //transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                }
                transaction.replace(R.id.fragment_container, frag, backStateName)
                if (saveInBackStack) {
                    Log.d(TAG, "Change Fragment: addToBackTack $backStateName")
                    transaction.addToBackStack(backStateName)
                } else {
                    Log.d(TAG, "Change Fragment: NO addToBackTack")
                }
                transaction.commit()
            } else {
                // custom effect if fragment is already instanciated
            }
        } catch (exception: IllegalStateException) {
            Log.w(
                    TAG,
                    "Unable to commit fragment, could be activity as been killed in background. $exception"
            )
        }
    }
}