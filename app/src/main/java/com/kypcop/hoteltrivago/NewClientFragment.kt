package com.kypcop.hoteltrivago

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.collections.ArrayList

class NewClientFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_newclient, LinearLayout(mainActivity))
        var sex: Client.SEX? = null

        val fnameEditText = view.findViewById<EditText>(R.id.client_fname_et)
        val lnameEditText = view.findViewById<EditText>(R.id.client_lname_et)
        val ageEditText = view.findViewById<EditText>(R.id.client_age_et)
        val sexSpinner = view.findViewById<Spinner>(R.id.client_sex_spinner)
        val roomEditText = view.findViewById<EditText>(R.id.client_room_et)
        val readyButton = view.findViewById<Button>(R.id.ready_button)

        sexSpinner.adapter = ArrayAdapter<String>(mainActivity, android.R.layout.simple_spinner_item,
                arrayListOf(Client.SEX.MALE.getString(mainActivity), Client.SEX.FEMALE.getString(mainActivity)))
        sexSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sex = Client.SEX.values()[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                sex = null
            }
        }
        readyButton.setOnClickListener {
            if(fnameEditText.text.isNotEmpty() && lnameEditText.text.isNotEmpty()
                    && ageEditText.text.isNotEmpty() && sex != null && roomEditText.text.isNotEmpty()){
                val fname = fnameEditText.text.toString()
                val lname = lnameEditText.text.toString()
                val age = ageEditText.text.toString().toInt()
                val room = roomEditText.text.toString().toInt()
                Memory.saveClient(Client(fname, lname, age, sex!!, room))
                mainActivity.onBackPressed()
            } else{
                Toast.makeText(mainActivity, R.string.fill_the_gaps, Toast.LENGTH_SHORT).show()
            }
        }


        return view

    }
}