package org.seniorsigan.qrauthenticatorclient

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.seniorsigan.qrauthenticatorclient.persistence.AccountModel

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        Log.d(TAG, "In success activity")
        val account = intent.getSerializableExtra(SUCCESS_INTENT) as AccountModel
        val btn = find<Button>(R.id.ok_button)
        val text = find<TextView>(R.id.success_message)
        text.text = "Logged in ${account.domain} as ${account.name}. Tokens used ${account.tokens.size - account.currentToken - 1}."
        btn.onClick {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}