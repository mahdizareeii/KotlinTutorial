package com.kotlin.kotlintutorial.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.kotlintutorial.R
import com.kotlin.kotlintutorial.data.model.User
import com.kotlin.kotlintutorial.ui.main.adapter.RVAdapter
import com.kotlin.kotlintutorial.ui.main.viewModel.MainViewModel
import com.kotlin.kotlintutorial.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    //private val mainViewModel: MainViewModel by viewModel()
    private val mainViewModel: MainViewModel by inject()

    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        setUpObserver()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        adapter = RVAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        mainViewModel.users.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { users ->
                        renderList(users)
                    }
                }

                Status.LOADING -> {
                    Toast.makeText(this, "please wait ...", Toast.LENGTH_SHORT).show()
                }

                Status.ERROR -> {
                    Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}