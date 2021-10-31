package com.example.databasetypes.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetypes.R
import com.example.databasetypes.roomdatabase.Movie
import com.example.databasetypes.roomdatabase.MovieViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecyclerActivity : AppCompatActivity() {

    private val newMovieActivityRequestCode = 1
    private lateinit var movieViewModel: MovieViewModel

    lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerDataList: MutableList<RecyclerData>

    var movie: List<Movie> = listOf()

    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView = findViewById(R.id.recyclerView)

        floatingActionButton = findViewById(R.id.floatingActionButton)

        initRecyclerView()
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getAllMovies.observe(this, Observer { movie ->
            this.movie=movie

            recyclerAdapter.setData(movie)
        })

        floatingActionButton.setOnClickListener(View.OnClickListener {
            val f = Intent(this, ItemsAddActivity::class.java)
            startActivityForResult(f, newMovieActivityRequestCode)
        })

    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newMovieActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(ItemsAddActivity.EXTRA_REPLY)?.let { reply ->
                val movie = Movie(reply, reply, reply, reply, reply, reply)
                movieViewModel.insert(movie)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not Saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }*/

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerAdapter(){
            //navigateToUpdateActivity(user[it])
        }
        recyclerView?.adapter = recyclerAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
       /* recyclerDataList = ArrayList()
        *//*recyclerAdapter = RecyclerAdapter(this, recyclerDataList) { position ->
            val imdbData = recyclerDataList[position]
            if (imdbData.bookmarked){
                imdbData.bookmarked = false
            }else{
                imdbData.bookmarked = true
            }
            recyclerAdapter.notifyItemChanged(position)
            //navigateToUpdateActivity(movie[it])
        }*//*
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter
*/
    }

    /*private fun navigateToUpdateActivity(movie: Movie) {
        val u = Intent(this, )
    }*/

}