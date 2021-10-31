package com.example.databasetypes.recyclerview

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.databasetypes.R
import com.example.databasetypes.roomdatabase.Movie
import com.example.databasetypes.roomdatabase.MovieViewModel

class ItemsAddActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel


    private lateinit var idET: EditText

    //private lateinit var imageUrlET: EditText
    private lateinit var movieNameET: EditText
    private lateinit var rating1ET: EditText
    private lateinit var rating2ET: EditText
    private lateinit var ratingMetaET: EditText
    private lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_add)

        idET = findViewById(R.id.idET)
        //imageUrlET = findViewById(R.id.imageUrlET)
        movieNameET = findViewById(R.id.movieNameET)
        rating1ET = findViewById(R.id.rating1ET)
        rating2ET = findViewById(R.id.rating2ET)
        ratingMetaET = findViewById(R.id.ratingMetaET)
        addBtn = findViewById(R.id.addBtn)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        /*addBtn.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(idET.text) || TextUtils.isEmpty(imageUrlET.text) || TextUtils.isEmpty(
                    movieNameET.text
                ) || TextUtils.isEmpty(rating1ET.text) || TextUtils.isEmpty(rating2ET.text) || TextUtils.isEmpty(
                    ratingMetaET.text
                )
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else{
                val id = idET.text.toString()
                val url = imageUrlET.text.toString()
                val name = movieNameET.text.toString()
                val rating1 = rating1ET.text.toString()
                val rating2 = rating2ET.text.toString()
                val ratingMeta = ratingMetaET.text.toString()
            }
            finish()
        }*/
    }

    private fun insertDataToDatabase() {
        val id = idET.text.toString()
        //val url = imageUrlET.text.toString()
        val name = movieNameET.text.toString()
        val rating1 = rating1ET.text
        val rating2 = rating2ET.text
        val ratingMeta = ratingMetaET.text

        if (inputCheck(id, name, rating1, rating2, ratingMeta)) {

            val movie = Movie(
                id,
                name,
                Integer.parseInt(rating1.toString()),
                Integer.parseInt(rating2.toString()),
                Integer.parseInt(ratingMeta.toString())
            )
            movieViewModel.addMovie(movie)
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
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

    /* private fun inputCheck(id: String, name: String, rating1: Editable?, rating2: Editable?, ratingMeta: Editable?): Boolean {
         return !(TextUtils.isEmpty(id) && TextUtils.isEmpty(name) && rating1!!.isEmpty() && rating2!!.isEmpty() && ratingMeta!!.isEmpty())
     }
 */
    companion object {
        const val EXTRA_REPLY = "com.example.android.movielistsql.REPLY"
    }
}