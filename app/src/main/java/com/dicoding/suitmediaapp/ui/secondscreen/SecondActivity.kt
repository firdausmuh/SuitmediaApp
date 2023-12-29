package com.dicoding.suitmediaapp.ui.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.dicoding.suitmediaapp.R
import com.dicoding.suitmediaapp.databinding.ActivitySecondBinding
import com.dicoding.suitmediaapp.ui.thirdscreen.ThirdActivity
import com.dicoding.utils.NAME
import com.dicoding.utils.USERNAME

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var username: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
            title = getString(R.string.second_screen)
        }
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(NAME)

        name?.let { binding.tvName.text = it }


        binding.btnChoose.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            chooseUser.launch(intent)
        }
    }
    private val chooseUser = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        username = it.data?.getStringExtra(USERNAME)
        username?.let { binding.tvUsername.text = it }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}