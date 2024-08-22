package com.example.cse227_realtimedatabase2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName : TextView
    private lateinit var tvEmpAge : TextView
    private lateinit var tvEmpSalary : TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employee_details)
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpAge = findViewById(R.id.tvEmpAge)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)

        btnDelete = findViewById(R.id.btnDelete)
        btnUpdate = findViewById(R.id.btnUpdate)

        setValueToViews()

        btnDelete.setOnClickListener{
            deleteRecord(intent.getStringExtra("empId").toString())
        }

        btnUpdate.setOnClickListener{
            val intent = Intent(this@EmployeeDetailsActivity,EmployeeDetailsActivity::class.java)
        }
        intent.putExtra("empId",tvEmpId.text.toString())
        intent.putExtra("empName",tvEmpName.text.toString())
        intent.putExtra("empAge",tvEmpAge.text.toString())
        intent.putExtra("empSalary",tvEmpSalary.text.toString())

        startActivity(intent)

    }
    private fun setValueToViews(){
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpSalary.text = intent.getStringExtra("empSalary")
        tvEmpAge.text = intent.getStringExtra("empAge")
    }

    private fun deleteRecord(id:String) {
        val dbref = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mtask = dbref.removeValue()

        mtask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}