package com.example.s5_0711

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s5_0711.databinding.FragmentHomeBinding
import org.json.JSONArray

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var b=FragmentHomeBinding.inflate(layoutInflater)
        b.viewPager.adapter=HomeViewPagerAdapter(requireContext())
        b.listView.adapter=HomeListAdapter(requireContext(), JSONArray(requireContext().assets.open("news.json").bufferedReader().readText()))
        return b.root
    }

}