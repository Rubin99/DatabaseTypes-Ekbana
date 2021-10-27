package com.example.databasetypes.fileio

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.databasetypes.R
import java.io.*

class FileActivity : AppCompatActivity() {

    private lateinit var fileNameEditText: EditText
    private lateinit var fileDataEditText: EditText

    private lateinit var saveButton: Button
    private lateinit var viewButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        fileNameEditText = findViewById(R.id.fileNameEditText)
        fileDataEditText = findViewById(R.id.fileDataEditText)

        saveButton = findViewById(R.id.saveButton)
        viewButton = findViewById(R.id.viewButton)

        saveButton.setOnClickListener(View.OnClickListener {
            val file: String = fileNameEditText.text.toString()
            val data: String = fileDataEditText.text.toString()
            val fileOutputStream: FileOutputStream

            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
            fileNameEditText.text.clear()
            fileDataEditText.text.clear()
        })

        viewButton.setOnClickListener(View.OnClickListener {
            val fileName = fileNameEditText.text.toString()
            if(fileName.toString()!=null && fileName.toString().trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(fileName)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                fileDataEditText.setText(stringBuilder.toString()).toString()
            }else{
                Toast.makeText(applicationContext,"file name cannot be blank",Toast.LENGTH_LONG).show()
            }
        })
    }
}