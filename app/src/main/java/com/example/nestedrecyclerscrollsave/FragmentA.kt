package com.example.nestedrecyclerscrollsave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nestedrecyclerscrollsave.databinding.FragmentABinding
import com.example.nestedrecyclerscrollsave.util.LoggerObserver

class FragmentA: Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding: FragmentABinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LoggerObserver())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.action.setOnClickListener {
            (activity as? NavigationController)?.navigateToB()
        }
    }
}