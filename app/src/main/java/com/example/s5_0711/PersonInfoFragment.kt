package com.example.s5_0711

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.widget.addTextChangedListener
import com.example.s5_0711.databinding.FragmentPersonInfoBinding


class PersonInfoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var b=FragmentPersonInfoBinding.inflate(layoutInflater)

        b.email.addTextChangedListener {
            var txt=it.toString()
            if(!Regex("[A-Z,a-z,0-9]+@[A-Z,a-z,0-9]+\\.[A-Z,a-z,0-9]{2,4}$").matches(txt)){
                b.email.error="格式錯誤"
            }else{
                b.email.error=null
            }
        }

        b.pwd.addTextChangedListener {
            var txt=it.toString()
            if(  !(txt.contains(Regex("[A-Z]")) && txt.contains(Regex("[a-z]")) && txt.contains(Regex("[0-9]"))  && txt.contains(Regex("\\W")) ) || txt.contains(" ") || txt.length<8 ||txt.length>15 ){
                b.pwd.error="格式錯誤"
            }else{
                b.pwd.error=null
            }
        }

        var adapter= ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(R.array.country))
        b.country.setAdapter(adapter)

        b.country.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var lans= arrayOf("zh-Hant","en-US")
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(lans[position]))
                Info.haveChange=true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        b.reg.setOnClickListener {
            if(b.name.text.toString().isEmpty()){
                b.name.error="此處不能為空"
            }
            if(b.pwd.text.toString().isEmpty() || b.email.text.toString().isEmpty() || b.name.text.toString().isEmpty() || b.email.error!=null || b.pwd.error!=null){
                b.reg.text="註冊失敗"
                b.reg.setTextColor(Color.RED)
            }else{
                Info.loginName="Name"
                requireFragmentManager().popBackStack()
            }
        }

        b.logout.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(),MainActivity::class.java))
            (requireContext() as Activity).finishAffinity()
        }

        return b.root
    }
}