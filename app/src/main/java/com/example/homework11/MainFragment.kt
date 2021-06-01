package com.example.homework11


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework11.databinding.MainFragmentBinding


class MainFragment : Fragment() {



    private val viewModel: MainViewModel by viewModels()

    private lateinit var  binding: MainFragmentBinding

    private lateinit var  adapter : RecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)

        init()

        return binding.root

    }


    private fun init(){
        viewModel.init()
        observes()
        initRecycler()

        binding.refresh.setOnRefreshListener {
            adapter.clearData()
            viewModel.init()
        }
    }


    private fun observes(){
        viewModel._itemsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it as MutableList<Model>)
        })

        viewModel._loading.observe(viewLifecycleOwner, Observer {
            binding.refresh.isRefreshing = it

        })

    }

    private fun initRecycler(){
        adapter = RecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter

    }




}