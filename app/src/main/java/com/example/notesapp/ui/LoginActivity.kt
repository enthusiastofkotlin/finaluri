package com.example.notesapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logInActivityListeners()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
        binding.tvHaventAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun logInActivityListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.tilEmail.toString()
            val pass = binding.tilPass.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "ველების შევსება სავალდებულოა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
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