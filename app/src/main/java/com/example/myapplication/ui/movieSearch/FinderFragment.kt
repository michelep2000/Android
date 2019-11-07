package com.example.myapplication.ui.movieSearch


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Movie2
import com.example.myapplication.model.RetrofitFactory
import kotlinx.android.synthetic.main.fragment_finder.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_finder.*


/**
 * A simple [Fragment] subclass.
 */
class FinderFragment : Fragment() {
    private lateinit var viewAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_finder, container, false)

        /*var editText: EditText = finderSearch as EditText
        editText.setTextColor(Color.WHITE)*/

        viewAdapter = MoviesAdapter {
            Toast.makeText(this.context, it.title, Toast.LENGTH_SHORT).show()
            openMovieDetail(it.id)
        }

        view.finderRecyclerView.adapter = viewAdapter
        view.finderSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                search(text)
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                //search(text)
                return false
            }

        })

        view.finderRecyclerView.layoutManager = LinearLayoutManager(this.context)
        view.finderRecyclerView.setHasFixedSize(true)

        return view
    }

    private fun search(term: String) {

        val service = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {

            val response = service.searchMovies(term)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    viewAdapter.addMovies(response.body()!!.results)
                }
            }
        }
    }

    private fun openMovieDetail(id: Int) {
        val intent = Intent(this.context, FinderDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}

