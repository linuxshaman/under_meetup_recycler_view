package app.linuxshaman.undermeetup0.ui.background

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.linuxshaman.undermeetup0.databinding.RecyclerViewBasedBinding
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */

@AndroidEntryPoint
class BackgroundFragment : Fragment() {

    @Inject
    lateinit var adapter: ScreenWidgetAdapter

    private var _binding: RecyclerViewBasedBinding? = null
    private val binding: RecyclerViewBasedBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerViewBasedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.widgetList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}