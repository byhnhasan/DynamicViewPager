package com.hsnbyhn.dynamicviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsnbyhn.dynamicviewpager.databinding.FragmentSecondBinding

/**
 * Created by hasanbayhan on 27.12.2020
 **/

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        fun newInstance() = SecondFragment()
    }
}