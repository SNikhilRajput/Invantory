package com.evs.phanidemo.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.evs.phanidemo.R
import com.evs.phanidemo.model.InvantoryModel
import com.evs.phanidemo.viewModel.viewModel
import kotlinx.android.synthetic.main.invantory.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import java.io.Serializable


class Invantorys :AppCompatActivity() ,ItemClick<InvantoryModel>{
    lateinit var viewModels: viewModel
     var objecct: InvantoryModel?=null
    var list=ArrayList<String>()
    var invaontorys=ArrayList<InvantoryModel>()
    var filterdData=ArrayList<InvantoryModel>()
    var adaterss:InvantoryAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invantory)

        rc_list.setHasFixedSize(false)
        rc_list.layoutManager=LinearLayoutManager(this)
        viewModels= ViewModelProvider(this@Invantorys).get(viewModel::class.java)

        adaterss= InvantoryAdapter(this,filterdData,this)
        rc_list.adapter=adaterss
        viewModels.getInvantory(this)!!.observe(this, Observer {
            if(it!=null){
                Log.e("data",""+it.size)
                invaontorys.addAll(it)
                filterdData.addAll(it)
                list.add("Manufacture")
                for (model:InvantoryModel in it){
                    list.add(model.manufacture!!)
                }
                val menufactureAdater= ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, list)

                menufactureAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sp_manu.setAdapter(menufactureAdater)
                adaterss!!.notifyDataSetChanged()
            }
        })

        btn_update.setOnClickListener(View.OnClickListener {
            if(objecct!=null){
                Log.e("data",""+objecct!!.Id)
              var intent=Intent(this,MainActivity::class.java)
                intent.putExtra("id",objecct!!.Id)
                startActivity(intent)
                finish()
        }else Toast.makeText(this,"Please Select Invantorys  Item",Toast.LENGTH_LONG).show()
        })



        sp_manu.onItemSelectedListener=object :OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if(position>0) {
                   filterdData.clear()
                   for (model: InvantoryModel in invaontorys) {
                       if (model.manufacture!!.toLowerCase().contains(list.get(position).toLowerCase())) {
                           filterdData.add(model)
                       }
                   }
                   adaterss!!.notifyDataSetChanged()
               }else{
                   filterdData.clear()
                   filterdData.addAll(invaontorys)
                   adaterss!!.notifyDataSetChanged()
               }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }







        et_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               if(s.length>0){
                   filterdData.clear()
                   for (model:InvantoryModel in invaontorys){
                           if (model.name!!.toLowerCase().contains(s.toString().toLowerCase())) {
                               filterdData.add(model)
                           }
                   }
               }else{
                   filterdData.clear()
                   filterdData.addAll(invaontorys)
               }
                adaterss!!.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {

            }
        })



    }

    override fun onItemClick(objecct: InvantoryModel) {
        this.objecct=objecct

    }
}