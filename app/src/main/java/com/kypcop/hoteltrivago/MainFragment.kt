package com.kypcop.hoteltrivago

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, LinearLayout(mainActivity))
        val newClientButton = view.findViewById<Button>(R.id.new_client_button)
        val seeClientsButton = view.findViewById<Button>(R.id.see_clients_button)
        newClientButton.setOnClickListener {
            mainActivity.changeFragment(NewClientFragment(), true, false)
        }
        seeClientsButton.setOnClickListener {
            mainActivity.changeFragment(SeeClientsFragment(), true, false)
        }
        return view

    }
}