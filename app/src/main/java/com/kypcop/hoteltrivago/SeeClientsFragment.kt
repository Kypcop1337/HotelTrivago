package com.kypcop.hoteltrivago

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.LinearLayout
import android.widget.ListView
import androidx.fragment.app.Fragment


class SeeClientsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    lateinit var adapter: ClientAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_seeclients, LinearLayout(mainActivity))
        val listView = view.findViewById<ListView>(R.id.clients_list)

        adapter = ClientAdapter(mainActivity, Memory.clientList)
        listView.adapter = adapter
        registerForContextMenu(listView)

        return view

    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = mainActivity.menuInflater
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Memory.deleteClient((item.menuInfo as AdapterContextMenuInfo).position)
        adapter.notifyDataSetChanged()
        return true
    }
}