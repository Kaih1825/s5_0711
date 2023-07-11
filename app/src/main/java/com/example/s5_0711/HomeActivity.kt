package com.example.s5_0711

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.s5_0711.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var b:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.loginName.text=Info.loginName

        b.openDrawer.setOnClickListener{
            b.drawer.open()
        }

        var fm=supportFragmentManager.beginTransaction()
        if(!Info.haveChange){
            fm.add(R.id.layout,HomeFragment()).commit()
        }else{
            b.pi.imageTintList= ColorStateList.valueOf(resources.getColor(android.R.color.holo_blue_light))
            b.pt.setTextColor(resources.getColor(android.R.color.holo_blue_light))

            b.hi.imageTintList= ColorStateList.valueOf(Color.BLACK)
            b.ht.setTextColor(Color.BLACK)
        }

        b.home.setOnClickListener {
            var fm=supportFragmentManager.beginTransaction()
            fm.addToBackStack(fm.javaClass.toString())
            fm.replace(R.id.layout,HomeFragment()).commit()
            b.hi.imageTintList= ColorStateList.valueOf(resources.getColor(android.R.color.holo_blue_light))
            b.ht.setTextColor(resources.getColor(android.R.color.holo_blue_light))

            b.pi.imageTintList= ColorStateList.valueOf(Color.BLACK)
            b.pt.setTextColor(Color.BLACK)

        }

        b.person.setOnClickListener {
            var fm=supportFragmentManager.beginTransaction()
            fm.addToBackStack(fm.javaClass.toString())
            fm.replace(R.id.layout,PersonInfoFragment()).commit()
            b.pi.imageTintList= ColorStateList.valueOf(resources.getColor(android.R.color.holo_blue_light))
            b.pt.setTextColor(resources.getColor(android.R.color.holo_blue_light))

            b.hi.imageTintList= ColorStateList.valueOf(Color.BLACK)
            b.ht.setTextColor(Color.BLACK)

        }
        if(Info.loginName=="訪客"){
            b.person.visibility=View.GONE
        }

    }
}