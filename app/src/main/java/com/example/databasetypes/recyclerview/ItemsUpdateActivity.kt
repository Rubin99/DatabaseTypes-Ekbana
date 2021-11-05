package com.example.databasetypes.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.databasetypes.R
import com.example.databasetypes.roomdatabase.Movie
import com.example.databasetypes.roomdatabase.MovieViewModel

class ItemsUpdateActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    var movie:Movie?=null


    private lateinit var idUpdateET: EditText

    //private lateinit var imageUrlET: EditText
    private lateinit var movieNameUpdateET: EditText
    private lateinit var rating1UpdateET: EditText
    private lateinit var rating2UpdateET: EditText
    private lateinit var ratingMetaUpdateET: EditText
    private lateinit var updateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_update)

        idUpdateET = findViewById(R.id.idUpdateET)
        //imageUrlET = findViewById(R.id.imageUrlET)
        movieNameUpdateET = findViewById(R.id.movieNameUpdateET)
        rating1UpdateET = findViewById(R.id.rating1UpdateET)
        rating2UpdateET = findViewById(R.id.rating2UpdateET)
        ratingMetaUpdateET = findViewById(R.id.ratingMetaUpdateET)
        updateBtn = findViewById(R.id.updateBtn)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movie = intent?.getParcelableExtra("toUpdate")

        updateBtn.setOnClickListener {
            updateDataInDatabase()
        }
    }

    private fun updateDataInDatabase() {
        val id = idUpdateET.text.toString()
        //val url = imageUrlET.text.toString()
        val name = movieNameUpdateET.text.toString()
        val rating1 = rating1UpdateET.text
        val rating2 = rating2UpdateET.text
        val ratingMeta = ratingMetaUpdateET.text

        if (inputCheck(id, name, rating1, rating2, ratingMeta)) {

            val updatedMovie = Movie(
                id,
                name,
                Integer.parseInt(rating1.toString()),
                Integer.parseInt(rating2.toString()),
                Integer.parseInt(ratingMeta.toString())
            )
            movieViewModel.updateMovie(updatedMovie)
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please Fill.", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(
        id: String,
        name: String,
        rating1: Editable?,
        rating2: Editable?,
        ratingMeta: Editable?
    ): Boolean {
        return  !(TextUtils.isEmpty(id) && TextUtils.isEmpty(name) && rating1!!.isEmpty() && rating2!!.isEmpty() && ratingMeta!!.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll){
            delete()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delete() {
        movieViewModel.deleteMovie(movie!!)
        Toast.makeText(this, "DELETED MOVIE", Toast.LENGTH_LONG).show()
    }
}