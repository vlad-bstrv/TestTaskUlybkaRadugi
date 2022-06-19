package com.vladbstrv.testtaskulybkaradugi.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladbstrv.testtaskulybkaradugi.data.AppState
import com.vladbstrv.testtaskulybkaradugi.databinding.FragmentOrderBinding
import com.vladbstrv.testtaskulybkaradugi.ui.order.adapter.OrderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderAdapter: OrderAdapter

    private val viewModel: OrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.getData()
        }

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    binding.loadingFrameLayout.visibility = View.GONE
                    orderAdapter = OrderAdapter()
                    binding.orderRecyclerView.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = orderAdapter
                    }
                    orderAdapter.setData(it.data)
                }
                is AppState.Loading -> {
                    binding.loadingFrameLayout.visibility = View.VISIBLE
                }
                is AppState.Error -> {
                    binding.loadingFrameLayout.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.fabBottom.setOnClickListener {
            viewModel.addDataBottom(orderAdapter.selectedItem)
            orderAdapter.addItemToBottom()
            binding.orderRecyclerView.smoothScrollToPosition(orderAdapter.itemCount)
            orderAdapter.selectedItem.clear()
        }

        binding.fabTop.setOnClickListener {
            viewModel.addDataTop(orderAdapter.selectedItem)
            orderAdapter.addItemToTop()
            orderAdapter.selectedItem.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.sortData()
        _binding = null
    }


}