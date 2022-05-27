package com.unfortunatefortunes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unfortunatefortunes.R
import com.unfortunatefortunes.databinding.FragmentFortuneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FortuneFragment : Fragment() {

    private val fortuneViewModel: FortuneViewModel? = null


    private lateinit var binding: FragmentFortuneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fortune, container, false)
    }


}