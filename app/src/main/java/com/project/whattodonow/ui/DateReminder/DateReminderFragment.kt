package com.project.whattodonow.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.whattodonow.databinding.FragmentDateReminderBinding


class DateReminderFragment : Fragment() {

    private var _binding: FragmentDateReminderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addTask =
            ViewModelProvider(this).get(DateReminderViewModel::class.java)

        _binding = FragmentDateReminderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun receiveMessage(s: String) {

    }
}