package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentTabLayoutCaeDetailsBinding
import com.example.hospitalapp.features.doctor.presentation.adapters.AdapterViewPager
import com.example.hospitalapp.features.doctor.presentation.viewModel.TabLayoutCaseDetailsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabLayoutCaeDetailsFragment : Fragment() {

    private var _binding: FragmentTabLayoutCaeDetailsBinding? = null
    private val binding get() = _binding!!
    private var caseId = 0
    private val viewModel: TabLayoutCaseDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabLayoutCaeDetailsBinding.inflate(inflater, container, false)
        caseId = arguments?.getInt(Const.CASE_ID)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        onClicks()
        observers()
    }

    private fun observers() {
        viewModel.logoutCallLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    showToast("Case logged out")
                    findNavController().popBackStack()
                }

                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(result.message)
                }
                else -> binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun setupViewPager() {
        val viewPagerAdapter = AdapterViewPager(requireActivity(),caseId)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = createTabView(position)
        }.attach()

        updateTabBackground(binding.tabLayout.getTabAt(0), true)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                updateTabBackground(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabBackground(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun createTabView(position: Int): View {
        val tabView = LayoutInflater.from(context).inflate(R.layout.item_case_details_tab, null)
        val tabText = tabView.findViewById<TextView>(R.id.tabTxt)
        tabText.text = when (position) {
            0 -> "Case"
            1 -> "Medical Record"
            2 -> "Medical Measurement"
            else -> null
        }
        return tabView
    }

    private fun updateTabBackground(tab: TabLayout.Tab?, isSelected: Boolean) {
        val tabView = tab?.customView ?: return
        val tabText = tabView.findViewById<TextView>(R.id.tabTxt)

        if (isSelected) {
            tabText.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.mint_green))
            tabText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        } else {
            tabText.setBackgroundResource(R.drawable.edit_item)
            tabText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnLogout.setOnClickListener {
            viewModel.logoutCall(caseId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
