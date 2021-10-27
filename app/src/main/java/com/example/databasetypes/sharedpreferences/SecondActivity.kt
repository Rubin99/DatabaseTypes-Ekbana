package com.example.databasetypes.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.databasetypes.fileio.FileActivity
import com.example.databasetypes.R
import com.example.databasetypes.recyclerview.RecyclerActivity


class SecondActivity : AppCompatActivity() {

    private lateinit var displayNameTextView: TextView
    private lateinit var logoutBtn: Button

    var sharedPref: SharedPreferences? = null
    var sharedPrefEditor: SharedPreferences.Editor? = null

    private lateinit var fileBtn: Button
    private lateinit var roomBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        displayNameTextView = findViewById(R.id.displayNameTextView)
        logoutBtn = findViewById(R.id.logoutBtn)

        fileBtn = findViewById(R.id.fileBtn)
        roomBtn = findViewById(R.id.roomBtn)

        sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE);
        checkLogin();

        logoutBtn.setOnClickListener(View.OnClickListener {
            logout()
        })
        fileBtn.setOnClickListener(View.OnClickListener {
            val n = Intent(this, FileActivity::class.java)
            startActivity(n)
            finish()
        })
        roomBtn.setOnClickListener(View.OnClickListener {
            val r = Intent(this, RecyclerActivity::class.java)
            startActivity(r)
            finish()
        })
    }

    private fun logout() {
        try {
            if (sharedPref == null)
                sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE)
            sharedPrefEditor = sharedPref?.edit()
            sharedPrefEditor?.putString("name_key", "")
            sharedPrefEditor?.commit()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()

        } catch (ex: Exception) {
            Toast.makeText(this, ex.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLogin() {
        if (sharedPref == null)
            sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE)


        val userName = sharedPref?.getString("name_key", "")

        if (userName != null && userName != "") {
            displayNameTextView.text = "Welcome $userName"
        } else {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}