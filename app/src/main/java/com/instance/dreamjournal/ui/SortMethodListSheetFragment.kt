/*
package com.instance.dreamjournal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.instance.dreamjournal.SortMethod
import com.instance.dreamjournal.ui.home.HomeViewModel

import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SortMethodListSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSortMethodsBinding? = null
    private val homeViewModel by sharedViewModel<HomeViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSortMethodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = SortMethodAdapter(listOf(SortMethod.DURATION, SortMethod.DATE))
    }

    private inner class ViewHolder(binding: SortMethodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val sortMethodContainer: RelativeLayout = binding.sortMethodContainer
        val sortMethodText: TextView = binding.sortMethodText
        val sortMethodImage: ImageView = binding.sortMethodImage
    }

    private inner class SortMethodAdapter(private val data: List<SortMethod>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                SortMethodItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.sortMethodText.text = data[position].userString
            holder.sortMethodImage.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    data[position].drawable
                )
            )

            holder.sortMethodContainer.setOnClickListener {
                when (data[position]) {
                    SortMethod.DURATION -> homeViewModel.sortRecordingsByDuration()
                    SortMethod.DATE -> homeViewModel.sortRecordingsByDate()
                }

                this@SortMethodListSheetFragment.dismiss()
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}*/
