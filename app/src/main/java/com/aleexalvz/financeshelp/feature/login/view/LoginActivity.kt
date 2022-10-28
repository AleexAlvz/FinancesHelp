package com.aleexalvz.financeshelp.feature.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aleexalvz.financeshelp.R
import com.aleexalvz.financeshelp.feature.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.login_btn_login)
            .setOnClickListener {
                startActivity(Intent(this, HomeActivity::class.java))
            }
    }
}