package com.unfortunatefortunes.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unfortunatefortunes.R

class FortuneFragment : Fragment() {

    companion object {
        fun newInstance() = FortuneFragment()
    }

    private lateinit var viewModel: FortuneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fortune, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FortuneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}