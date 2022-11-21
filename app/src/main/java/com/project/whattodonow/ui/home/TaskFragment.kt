package com.project.whattodonow.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.retrofit_kotlin.ApiInterface
import com.project.retrofit_kotlin.MyDataItem
import com.project.roomdbkotlin.db.NoteViewModel
import com.project.whattodonow.databinding.FragmentTaskBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TaskFragment : Fragment() {
    private lateinit var vm: NoteViewModel
    private var _binding: FragmentTaskBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)



        return root
    }
    private fun getMyData() {
//        val textResponse = findViewById<TextView>(R.id.textview)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", t.message.toString())
            }
        })
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
