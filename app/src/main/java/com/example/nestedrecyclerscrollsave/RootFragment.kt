package com.example.nestedrecyclerscrollsave

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerscrollsave.adapter.NestedRecyclerAdapter
import com.example.nestedrecyclerscrollsave.databinding.FragmentRootBinding
import com.example.nestedrecyclerscrollsave.model.DataSets
import com.example.nestedrecyclerscrollsave.adapter.savestate.RecyclerStateManager
import com.example.nestedrecyclerscrollsave.util.LoggerObserver
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RootFragment : Fragment() {

    private var _binding: FragmentRootBinding? = null
    private val binding: FragmentRootBinding get() = _binding!!

    private val bannerVisibilityState = MutableStateFlow(false)
    private val bannerOffEventChannel = Channel<Unit>(
        capacity = Channel.RENDEZVOUS,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val stateSaver = RecyclerStateManager()

    private val nestedRecyclerAdapter = NestedRecyclerAdapter(
        onInnerItemClick = {
            (activity as NavigationController).navigateToA()
        },
        stateSaver
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LoggerObserver())
        bannerOffEventChannel.receiveAsFlow()
            .onEach {
                delay(400L)
                bannerVisibilityState.emit(false)
            }
            .launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(
            LoggerObserver.TAG,
            "${this.javaClass.simpleName}, onCreateView(), hashCode = ${this.hashCode()}"
        )
        _binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = nestedRecyclerAdapter
            addItemDecoration(
                MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
            )
        }

        lifecycleScope.launch {
            bannerVisibilityState.collect { isBannerVisible ->
                binding.banner.isVisible = isBannerVisible
            }
        }

        binding.dataset1.setOnClickListener {
            nestedRecyclerAdapter.submitList(DataSets.dataSet1)
        }

        binding.dataset2.setOnClickListener {
            nestedRecyclerAdapter.submitList(DataSets.dataSet2)
        }

        binding.dataset3.setOnClickListener {
            nestedRecyclerAdapter.submitList(DataSets.dataSet3)
        }
    }

    override fun onStop() {
        super.onStop()
        stateSaver.saveViewStates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arguments?.putBoolean(SIMPLE_KEY, false)
        Log.d(
            LoggerObserver.TAG,
            "${this.javaClass.simpleName}, onDestroyView(), hashCode = ${this.hashCode()}"
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(
            "TEST14",
            "${this.javaClass.simpleName}, onSaveInstanceState(), hashCode = ${this.hashCode()}"
        )
    }

    private fun showBanner() {
        lifecycleScope.launch {
            bannerVisibilityState.emit(true)
        }
        bannerOffEventChannel.trySend(Unit)
    }

    companion object {
        private const val SIMPLE_KEY = "SIMPLE_KEY"
    }
}