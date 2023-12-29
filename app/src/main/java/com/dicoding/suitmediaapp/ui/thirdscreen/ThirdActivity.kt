package com.dicoding.suitmediaapp.ui.thirdscreen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.utils.ViewModelFactory
import com.dicoding.model.UserAdapter
import com.dicoding.suitmediaapp.R
import com.dicoding.suitmediaapp.databinding.ActivityThirdBinding
import com.dicoding.utils.USERNAME

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: ThirdViewModel by viewModels {
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
            title = getString(R.string.third_screen)
        }
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        setSwipeRefreshListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
    private fun setupRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        adapter = UserAdapter(){ username ->
            val intent = Intent()
            intent.putExtra(USERNAME, username)
            setResult(200, intent)
            finish()
        }

        binding.rvUser.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { adapter.retry() },
            footer = LoadingStateAdapter { adapter.retry() }
        )
    }

    private fun observeViewModel() {
        viewModel.listUser.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setSwipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            adapter.notifyDataSetChanged()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
