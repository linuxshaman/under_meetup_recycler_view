package app.linuxshaman.undermeetup0

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.linuxshaman.undermeetup0.databinding.ActivityMainBinding
import app.linuxshaman.undermeetup0.ui.core.widget.ViewTypes
import app.linuxshaman.undermeetup0.ui.showcase.ShowCaseRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var showCaseRepository: ShowCaseRepository

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    showCaseRepository.getCount().collect { count ->
                        presentCount(count)
                    }
                }
            }
        }
    }

    private fun presentCount(count: Map<String, Int>) {
        val value = StringBuilder()
        count.forEach { (type, count) ->
            value.append("$type: $count\n")
        }
        binding.count.text = value.toString()
        binding.count.requestLayout()
    }
}