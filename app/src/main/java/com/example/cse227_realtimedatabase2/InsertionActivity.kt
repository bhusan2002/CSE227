package com.example.cse227_realtimedatabase2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var salary: EditText
    private lateinit var btnAddData: Button

    private lateinit var dbref : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertion)
        name = findViewById(R.id.editTextText)
        age = findViewById(R.id.editTextText2)
        salary = findViewById(R.id.editTextText3)
        btnAddData = findViewById(R.id.button3)
        dbref = FirebaseDatabase.getInstance().getReference("Employees")
        btnAddData.setOnClickListener {
            saveEmployeeData()
        }

    }
    private fun saveEmployeeData() {
        val empName = name.text.toString()
        val empAge = age.text.toString()
        val empSalary = salary.text.toString()

        if (empName.isEmpty()) {
            name.error = "Please enter name"
        }
        if (empAge.isEmpty()) {
            age.error = "Please enter age"
        }
        if (empSalary.isEmpty()) {
            salary.error = "Please enter salary"
        }
        val empId = dbref.push().key!!

        val employee = EmployeeModel(empId, empName, empAge, empSalary)
        if (empName.isNotEmpty() && empAge.isNotEmpty() && empSalary.isNotEmpty()) {
            dbref.child(empId!!).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
                    name.text.clear()
                    age.text.clear()
                    salary.text.clear()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}