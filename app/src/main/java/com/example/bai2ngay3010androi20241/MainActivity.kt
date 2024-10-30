    package com.example.bai2ngay3010androi20241

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var studentListView: ListView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: ArrayAdapter<String>
    private val students = listOf(
        Student("Nguyễn Văn A", "SV001"),
        Student("Trần Thị B", "SV002"),
        Student("Lê Văn C", "SV003"),
        Student("Phạm Thị D", "SV004"),
        Student("Nguyễn Thị E", "SV005"),
        Student("Đỗ Văn F", "SV006"),
        Student("Nguyễn Văn G", "SV007"),
        Student("Trần Thị H", "SV008"),
        Student("Lê Văn I", "SV009"),
        Student("Phạm Thị J", "SV010")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        studentListView = findViewById(R.id.studentListView)
        searchEditText = findViewById(R.id.searchEditText)

        val studentNames = students.map { "${it.name} - ${it.mssv}" }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        studentListView.adapter = adapter
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            students.filter {
                it.name.contains(text, ignoreCase = true) ||
                        it.mssv.contains(text, ignoreCase = true)
            }.map { "${it.name} - ${it.mssv}" }
        } else {
            students.map { "${it.name} - ${it.mssv}" }
        }

        adapter.clear()
        adapter.addAll(filteredList)
        adapter.notifyDataSetChanged()
    }
}