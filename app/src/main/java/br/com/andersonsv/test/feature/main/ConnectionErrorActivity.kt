package br.com.andersonsv.test.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.andersonsv.test.R
import kotlinx.android.synthetic.main.activity_connection_error.*


class ConnectionErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_error)

        buttonErrorTryAgain.setOnClickListener {
            val showMainIntent = Intent(this, MainActivity::class.java)
            startActivity(showMainIntent)
        }
    }
}