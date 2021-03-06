package org.seniorsigan.zkpauthenticatorclient

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity() {
    lateinit var accountsView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val button = find<Button>(R.id.button)
        button.onClick { view ->
            val intent = Intent(this, QRCodeScannerActivity::class.java)
            startActivity(intent)
        }

        accountsView = find<RecyclerView>(R.id.accounts_recycler_view)
        accountsView.setHasFixedSize(true)
        accountsView.layoutManager = LinearLayoutManager(this)
        accountsView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy <= 0) {
                    button.visibility = View.VISIBLE
                } else {
                    button.visibility = View.INVISIBLE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        fillAccountsView()
    }

    private fun fillAccountsView() {
        val accounts = App.userRepository.findAll()
        accountsView.adapter = AccountsAdapter(accounts)

        val noAccountsView = find<TextView>(R.id.noAccountsView)
        if (accountsView.adapter.itemCount == 0) {
            noAccountsView.visibility = View.VISIBLE
        } else {
            noAccountsView.visibility = View.INVISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        if (id == R.id.drop_all) {
            App.userRepository.deleteAll()
            fillAccountsView()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
