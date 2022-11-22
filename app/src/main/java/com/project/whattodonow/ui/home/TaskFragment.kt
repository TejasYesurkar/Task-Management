package com.project.whattodonow.ui.home

import android.R
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.roomdbkotlin.NoteAdapter
import com.project.roomdbkotlin.db.TaskViewModel
import com.project.whattodonow.databinding.FragmentTaskBinding


const val EXTRA_ID = " com.huawei.todolist.EXTRA_ID"
const val EXTRA_TITLE = " com.huawei.todolist.EXTRA_TITLE"
const val EXTRA_DESCRIPTION = " com.huawei.todolist.EXTRA_DESCRIPTION"
const val EXTRA_PRIORITY = " com.huawei.todolist.EXTRA_PRIORITY"

const val ADD_NOTE_REQUEST = 1
const val EDIT_NOTE_REQUEST = 2

class TaskFragment : Fragment() {
    private lateinit var vm: TaskViewModel
    private var _binding: FragmentTaskBinding? = null
    private lateinit var adapter: NoteAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        setUpRecyclerView()

        setUpListeners()


        vm = ViewModelProviders.of(this)[TaskViewModel::class.java]

        vm.getAllNotes().observe(viewLifecycleOwner, Observer {
            Log.i("Notes observed", "$it")

            adapter.submitList(it)
        })


        return root
    }

    private fun setUpListeners() {
        binding.buttonAddNote.setOnClickListener {
//            val duedateFrag: Fragment = AddTaskFragment()
//            val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
//            ft.replace(R.id.nav_host_fragment_content_main, duedateFrag)
//            ft.addToBackStack(null)
//            ft.commit()
        }

        // swipe listener
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            UCharacter.IndicPositionalCategory.LEFT or UCharacter.IndicPositionalCategory.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = adapter.getNoteAt(viewHolder.adapterPosition)
                vm.delete(note)
            }

        }).attachToRecyclerView(binding.recyclerView)
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)

        adapter = NoteAdapter { clickedNote ->

//            UIHelper.getInstance().replaceFragment(getActivity(),
//                AddTaskFragment.newInstance(), "AddTaskFragment", com.project.whattodonow.R.id.nav_host_fragment_content_main,
//                true, UIHelper.CustomAnimationType.CUSTOM_ANIMATION_LEFT_AND_RIGHT);
//            val intent = Intent(this, AddEditNote::class.java)
//            intent.putExtra(EXTRA_ID, clickedNote.id)
//            intent.putExtra(EXTRA_TITLE, clickedNote.task)
//            intent.putExtra(EXTRA_DESCRIPTION, clickedNote.date)
//            intent.putExtra(EXTRA_PRIORITY, clickedNote.status)
//            startActivityForResult(intent, EDIT_NOTE_REQUEST)
        }
        binding.recyclerView.adapter = adapter
    }
}


//    private fun getMyData() {
//        val data = ArrayList<ItemsViewModel>()
//        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
//        // launching a new coroutine
//        GlobalScope.launch {
//            val result = quotesApi.getQuotes()
//            if (result != null)
//            // Checking the results
//                Log.d("ayush", result.body()?.results.toString())
//
//            val size = result.body()?.results?.size
//            for(day in result.body()?.results!!){
//                data.add(ItemsViewModel(R.drawable.ic_launcher_background,
//                    day.author.toString()))
//            }
//            requireActivity().runOnUiThread { // This code will always run on the UI thread, therefore is safe to modify UI elements.
//                val adapter = CustomAdapter(data)
//                binding.recyclerView.adapter = adapter
//            }
//
//            }
//        }
//    }
