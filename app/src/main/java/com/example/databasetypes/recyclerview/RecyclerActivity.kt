package com.example.databasetypes.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasetypes.R
import com.example.databasetypes.fileio.FileActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecyclerActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerDataList: MutableList<RecyclerData>

    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView = findViewById(R.id.recyclerView)

        floatingActionButton = findViewById(R.id.floatingActionButton)

        floatingActionButton.setOnClickListener(View.OnClickListener {
            val f = Intent(this, ItemsAddActivity::class.java)
            startActivity(f)
            finish()
        })

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerDataList = ArrayList()
        recyclerDataList.add(
            RecyclerData(
                "Dune (2021)",
                8.8,
                8,
                88,
                false,
                "https://cdn.europosters.eu/image/1300/posters/dune-fear-is-the-mind-killer-i107183.jpg"

            )
        )
        recyclerDataList.add(
            RecyclerData(
                "The French Dispatch (2021)",
                7.2,
                7,
                71,
                false,
                "https://m.media-amazon.com/images/I/71nWfxOlCTL._AC_SL1398_.jpg"
            )
        )
        recyclerDataList.add(
            RecyclerData(
                "Free Guy (2021)",
                9.0,
                9,
                92,
                false,
                "https://img.republicworld.com/republic-prod/stories/images/16019654225f7c0d6e3ec0a.png"
            )
        )
        recyclerDataList.add(
            RecyclerData(
                "The Suicide Squad (2021)",
                8.5,
                8,
                85,
                false,
                "https://cdn.shopify.com/s/files/1/0037/8008/3782/products/TheSuicideSquadOneSheetMoviePoster-145571_1024x1024@2x.jpg?v=1622506700"

            )
        )
        recyclerDataList.add(
            RecyclerData(
                "Black Widow (2021)",
                8.1,
                8,
                81,
                false,
                "https://m.media-amazon.com/images/M/MV5BNjRmNDI5MjMtMmFhZi00YzcwLWI4ZGItMGI2MjI0N2Q3YmIwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg"
            )
        )
        recyclerDataList.add(
            RecyclerData(
                "The Conjuring 3 (2021)",
                8.0,
                8,
                80,
                false,
                "https://wallpaperaccess.com/full/4592385.jpg"
            )
        )

        recyclerAdapter = RecyclerAdapter(this, recyclerDataList) { position ->
            val recyclerData = recyclerDataList[position]
            if (recyclerData.bookmarked) {
                recyclerData.bookmarked = false
            } else {
                recyclerData.bookmarked = true
            }
            recyclerAdapter.notifyItemChanged(position)
        }
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter
    }

}