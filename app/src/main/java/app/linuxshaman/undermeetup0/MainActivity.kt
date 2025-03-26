package app.linuxshaman.undermeetup0

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.linuxshaman.undermeetup0.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentById(android.R.id.content) == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(android.R.id.content, MainFragment())
            transaction.commit()
        }
    }
}