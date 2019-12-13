package com.casa.azul.earthquakelogger.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.casa.azul.dogs.view.QuakeListAdapter
import com.casa.azul.earthquakelogger.R
import com.casa.azul.earthquakelogger.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val quakeListAdapter = QuakeListAdapter(arrayListOf())
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        viewModel.refresh()

        quake_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = quakeListAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.quakes.observe(this, Observer { quakes ->
            quakes?.let {
                quake_recyclerView.visibility = View.VISIBLE
                quakeListAdapter.updateQuakeList(quakes)
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                if (loading) {
                    quake_progressBar.visibility = View.GONE
                }
            }
        })

    }


}
