package app.linuxshaman.undermeetup0.ui.viewpool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.linuxshaman.undermeetup0.databinding.RecyclerViewBasedBinding
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetAdapter
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.showcase.ShowcaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */

@AndroidEntryPoint
class ViewPoolFragment : Fragment() {

    @Inject
    lateinit var adapter: ScreenWidgetAdapter

    @Inject
    lateinit var viewPool: ScreenWidgetRecycledViewPool

    private val viewModel: ShowcaseViewModel by viewModels()

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
        binding.widgetList.setRecycledViewPool(viewPool)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}