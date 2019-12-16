package com.casa.azul.earthquakelogger.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.casa.azul.earthquakelogger.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
        return when (item.itemId) {
            R.id.action_about -> {
                showDialogue()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDialogue() {
        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("About EarthquakeLogger")

        // Display a message on alert dialog
        builder.setMessage("Earthquake Logger was developed by Manuel carvalho")

        builder.setNeutralButton("O.K") { _, _ ->

        }


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
