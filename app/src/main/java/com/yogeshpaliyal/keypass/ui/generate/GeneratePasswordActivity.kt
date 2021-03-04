package com.yogeshpaliyal.keypass.ui.generate

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.yogeshpaliyal.keypass.databinding.ActivityGeneratePasswordBinding
import com.yogeshpaliyal.keypass.utils.PasswordGenerator


class GeneratePasswordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGeneratePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneratePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generatePassword()

        binding.btnRefresh.setOnClickListener {
            generatePassword()
        }

        binding.tilPassword.setEndIconOnClickListener {
            val clipboard= getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("random_password", binding.etPassword.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }


    private fun generatePassword(){
        val password = PasswordGenerator(
            10, binding.cbCapAlphabets.isChecked,
            binding.cbLowerAlphabets.isChecked,
            binding.cbSymbols.isChecked,
            binding.cbNumbers.isChecked
        ).generatePassword()

        binding.etPassword.setText(password)
    }
}