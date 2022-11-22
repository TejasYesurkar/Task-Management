package com.project.whattodonow.ui.add_task

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.whattodonow.databinding.FragmentAddTaskBinding
import com.project.whattodonow.databinding.FragmentDailyBinding
import com.project.whattodonow.databinding.FragmentDateReminderBinding
import java.util.*


class AddTasksFragment : Fragment() {
//    var editTextDate: EditText
//    var editTextTask: TextView
//    var editTextDesc: TextView
//    var buttonSave: Button
    private var _binding: FragmentAddTaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val addTask =
            ViewModelProvider(this).get(AddTasksViewModel::class.java)

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.etDate.setOnClickListener {
            clickDataPicker()
        }
        binding.btnsave.setOnClickListener() {
            if (binding.etDate.text.length == 0) {
                Log.d(">>","editTextDate - false")
            } else if (binding.etTask.text.length == 0) {
                Log.d(">>","editTextTask - false")
            } else if ( binding.etDesc.text.length == 0) {
                Log.d(">>","editTextDesc - false")
            }else{
                Log.d(">>","true")
            }
        }
        return root
    }

    fun clickDataPicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            Log.d(">>", """$dayOfMonth - ${monthOfYear + 1} - $year""")
            binding.etDate.text  = "Date of Completion: "+"""$dayOfMonth - ${monthOfYear + 1} - $year"""
        }, year, month, day)
        dpd.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun receiveMessage(s: String) {

    }
}