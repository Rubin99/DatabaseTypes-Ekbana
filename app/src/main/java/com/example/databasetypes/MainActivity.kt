package com.example.databasetypes

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var sharedPref: SharedPreferences? = null
    var sharedPrefEditor: SharedPreferences.Editor? = null

    private lateinit var nameEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var viewNameTextView: TextView
    private lateinit var viewPasswordTextView: TextView

    private lateinit var saveBtn: Button
    private lateinit var viewBtn: Button
    private lateinit var clearBtn: Button

    private lateinit var loginBtn: Button
    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        viewNameTextView = findViewById(R.id.viewNameTextView)
        viewPasswordTextView = findViewById(R.id.viewPasswordTextView)

        saveBtn = findViewById(R.id.saveBtn)
        viewBtn = findViewById(R.id.viewBtn)
        clearBtn = findViewById(R.id.clearBtn)

        loginBtn = findViewById(R.id.loginBtn)
        infoTextView = findViewById(R.id.infoTextView)

        //val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        sharedPref = getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE);
        checkLogin()

        saveBtn.setOnClickListener(View.OnClickListener {
            val name: String = nameEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            sharedPrefEditor = sharedPref?.edit()


            sharedPrefEditor?.putString("name_key", name)
            sharedPrefEditor?.putString("password_key", password)
            sharedPrefEditor?.apply()
            sharedPrefEditor?.commit()
        })
        viewBtn.setOnClickListener(View.OnClickListener {
            val sharedNameValue = sharedPref?.getString("name_key", "NAME")
            val sharedPasswordValue = sharedPref?.getString("password_key", "PASSWORD")

            if (sharedNameValue.equals("NAME") && sharedPasswordValue.equals("PASSWORD")) {
                viewNameTextView.setText("Default Name: $sharedNameValue").toString()
                viewPasswordTextView.setText("Default Password: $sharedPasswordValue").toString()
            } else {
                viewNameTextView.setText(sharedNameValue).toString()
                viewPasswordTextView.setText(sharedPasswordValue).toString()
            }
        })
        clearBtn.setOnClickListener(View.OnClickListener {
            sharedPrefEditor = sharedPref?.edit()
            sharedPrefEditor?.clear()
            sharedPrefEditor?.apply()
            viewNameTextView.setText("").toString()
            viewPasswordTextView.setText("").toString()
        })

        loginBtn.setOnClickListener(View.OnClickListener {
            if (!(nameEditText.text.toString() != "" && passwordEditText.text.toString() != "")) {
                infoTextView.text = "Enter valid credentials"
            } else {
                doLogin(nameEditText.text.toString(), passwordEditText.text.toString())
            }
        })

    }

    private fun doLogin(userName: String?, userPassword: String?) {
        try {
            if (userPassword.equals("123456")) {
                if (sharedPref == null)
                    sharedPref = getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)
                sharedPrefEditor = sharedPref?.edit()
                sharedPrefEditor?.putString("name_key", userName)
                sharedPrefEditor?.commit()

                val i = Intent(this, SecondActivity::class.java)
                startActivity(i)
                finish()

            } else infoTextView.text = "Invalid Credentials"
        } catch (ex: Exception) {
            infoTextView.text = ex.message.toString()
        }
    }

    private fun checkLogin() {
        if (sharedPref == null)
            sharedPref = getSharedPreferences("SharedPrefFile", MODE_PRIVATE)

        val userName = sharedPref?.getString("name_key", "")

        if (userName != null && userName != "") {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
            finish()
        }
    }

}