package com.example.s5_0711

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.widget.addTextChangedListener
import com.example.s5_0711.databinding.ActivityRegBinding

class RegActivity : AppCompatActivity() {
    lateinit var b:ActivityRegBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityRegBinding.inflate(layoutInflater)
        setContentView(b.root)

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

        var adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(R.array.country))
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
                startActivity(Intent(this,HomeActivity::class.java))
                finishAffinity()
            }
        }
    }
}