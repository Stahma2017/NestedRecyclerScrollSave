package com.example.nestedrecyclerscrollsave

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.nestedrecyclerscrollsave.databinding.ActivityMainBinding
import com.example.nestedrecyclerscrollsave.util.LoggerObserver

class MainActivity : AppCompatActivity(), NavigationController {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(LoggerObserver())
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigateToRoot()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TEST14", "${this.javaClass.simpleName}: onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("TEST14", "${this.javaClass.simpleName}: onRestoreInstanceState()")
    }

    private fun navigateToRoot() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, RootFragment())
        }
    }

    override fun navigateToA() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragment_container, FragmentA())
        }
    }

    override fun navigateToB() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragment_container, FragmentB())
        }
    }
}