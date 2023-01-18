package com.example.notesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityLoginBinding
import com.example.notesapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RegisterActivityListeners()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
        binding.tvHaventAccount.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }


    }

    private fun RegisterActivityListeners() {
        binding.btnRegister.setOnClickListener {
            val email = binding.tilEmail.toString()
            val pass = binding.tilPassword.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, AddNoteActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "მონაცემები არასწორია", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}