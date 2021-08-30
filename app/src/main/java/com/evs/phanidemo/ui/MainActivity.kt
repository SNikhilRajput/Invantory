package com.evs.phanidemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.evs.phanidemo.R
import com.evs.phanidemo.model.InvantoryModel
import com.evs.phanidemo.viewModel.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.invantory.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModels: viewModel
    var lisSku = ArrayList<String>()
    var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModels = ViewModelProvider(this@MainActivity).get(viewModel::class.java)

        btn_save.setOnClickListener(View.OnClickListener {
            validates()

        })

        viewModels.getInvantory(this)!!.observe(this, Observer {
            if (it != null) {
                Log.e("data", "" + it.size)
                for (model: InvantoryModel in it) {
                    lisSku.add(model.sku!!)
                }
            }
        })

        btn_search.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, Invantorys::class.java))
        })


        var bundel = intent.extras
        if (bundel != null) {
            id = bundel.getInt("id")
            btn_save.text = "Update"
            getData()
        }
        viewModels.getManufacture(this)!!.observe(this, Observer {
            if (it != null) {
                Log.e("manu", "" + it.size)
                for (mam:String in it){
                    Log.e("manu", "" + mam)
                }
            }
        })
    }

    private fun getData() {
        viewModels.getInventory(this@MainActivity, id!!)!!.observe(this, Observer {
            if (it != null) {
                et_itemName.setText(it.name)
                et_menufacture.setText(it.manufacture)
                et_sku.setText(it.sku)
                et_qun.setText(it.qun)
                et_unit.setText(it.unit)
            }
        })


    }

    private fun validates() {
        if (et_itemName.text.toString().isEmpty()) {
            et_itemName.error = "Enter Product Name !!"
        } else if (et_menufacture.text.toString().isEmpty()) {
            et_menufacture.error = "Enter Product Manufacture !!"
        } else if (et_sku.text.toString().isEmpty()) {
            et_sku.error = "Enter Product SKU !!"
        } else if (et_qun.text.toString().isEmpty()) {
            et_qun.error = "Enter Product Quantity !!"
        } else if (et_unit.text.toString().isEmpty()) {
            et_unit.error = "Enter Product Quantity Unit  !!"
        } else {
            if (id == null) {
                if (!lisSku.contains(et_sku.text.toString())) {
                    viewModels.insertData(
                        this,
                        et_itemName.text.toString(),
                        et_sku.text.toString(),
                        et_qun.text.toString(),
                        et_unit.text.toString(),
                        et_menufacture.text.toString()
                    )
                    Toast.makeText(this, "Inventory saved SuccessFully", Toast.LENGTH_LONG).show()
                    et_itemName.setText("")
                    et_menufacture.setText("")
                    et_sku.setText("")
                    et_qun.setText("")
                    et_unit.setText("")
                } else Toast.makeText(this, "Product Sku is not Unique", Toast.LENGTH_LONG).show()
            } else {
                var obj = InvantoryModel(
                    et_itemName.text.toString(),
                    et_sku.text.toString(),
                    et_qun.text.toString(),
                    et_unit.text.toString(),
                    et_menufacture.text.toString()
                )
                obj.Id = id
                viewModels.updateData(this, obj)
                Toast.makeText(this, "Inventory Update SuccessFully", Toast.LENGTH_LONG).show()
                et_itemName.setText("")
                et_menufacture.setText("")
                et_sku.setText("")
                et_qun.setText("")
                et_unit.setText("")
            }


        }
    }
}