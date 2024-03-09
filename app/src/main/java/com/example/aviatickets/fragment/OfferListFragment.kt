package com.example.aviatickets.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.service.FakeService

class OfferListFragment : Fragment() {

    private var _binding: FragmentOfferListBinding? = null
    private val binding get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        fun newInstance() = OfferListFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSorting()
        loadOffers()

    }

    private fun setupRecyclerView() {
        binding.offerList.apply {
            adapter = this@OfferListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupSorting() {
        binding.sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sort_by_price -> sortOffersByPrice()
                R.id.sort_by_duration -> sortOffersByDuration()
            }
        }
    }

    private fun sortOffersByPrice() {
        val sortedList = FakeService.offerList.sortedBy { it.price }
        adapter.submitList(sortedList)
    }

    private fun sortOffersByDuration() {
        val sortedList = FakeService.offerList.sortedBy { it.flight.duration }
        adapter.submitList(sortedList)
    }

    private fun loadOffers() {
        adapter.submitList(FakeService.offerList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

