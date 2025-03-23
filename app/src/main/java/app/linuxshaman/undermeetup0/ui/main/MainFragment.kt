package app.linuxshaman.undermeetup0.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import app.linuxshaman.undermeetup0.databinding.RecyclerViewBasedBinding
import app.linuxshaman.undermeetup0.ui.core.action.UIAction
import app.linuxshaman.undermeetup0.ui.core.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.core.tools.ScreenWidgetAdapter
import app.linuxshaman.undermeetup0.ui.core.tools.WidgetListDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var adapter: ScreenWidgetAdapter

    @Inject
    lateinit var callbackManager: UICallbackManager

    @Inject
    lateinit var widgetListDecoration: WidgetListDecoration

    private val viewModel: MainViewModel by viewModels()

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
        binding.widgetList.addItemDecoration(widgetListDecoration)


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.widgets.collect { widgets ->
                        adapter.setWidgets(widgets)
                    }
                }
            }
        }

        callbackManager.addCallback(viewLifecycleOwner) { action ->
            when (action) {
                is UIAction.Navigate -> {
                    findNavController().navigate(action.destination)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}