package app.linuxshaman.undermeetup0.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.linuxshaman.undermeetup0.databinding.RecyclerViewBasedBinding
import app.linuxshaman.undermeetup0.ui.action.UIAction
import app.linuxshaman.undermeetup0.ui.action.UICallback
import app.linuxshaman.undermeetup0.ui.action.UICallbackManager
import app.linuxshaman.undermeetup0.ui.tools.ScreenWidgetAdapter
import app.linuxshaman.undermeetup0.ui.tools.ScreenWidgetRecycledViewPool
import app.linuxshaman.undermeetup0.ui.tools.WidgetListDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by linuxshaman on 23.03.2025.
 */
@AndroidEntryPoint
class MainFragment : Fragment(), UICallback {

    @Inject
    lateinit var adapter: ScreenWidgetAdapter

    @Inject
    lateinit var callbackManager: UICallbackManager

    @Inject
    lateinit var viewPool: ScreenWidgetRecycledViewPool

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
        binding.widgetList.setRecycledViewPool(viewPool)

        val onBackPressedDispatcher = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                viewModel.navigateUp()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedDispatcher
        )


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.state.collect { state ->
                        adapter.setWidgets(state.widgets)
                        onBackPressedDispatcher.isEnabled = state.isUpNavigationEnabled
                    }
                }
            }
        }

        callbackManager.addCallback(viewLifecycleOwner, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUIAction(action: UIAction) {
        when (action) {
            is UIAction.Navigate -> {
                viewModel.navigate(action.destination)
            }

            is UIAction.NavigateUp -> {
                viewModel.navigateUp()
            }

            is UIAction.MultiAction -> {
                action.actions.forEach { subAction ->
                    onUIAction(subAction)
                }
            }

            is UIAction.SetBuyProAttempt -> {
                viewModel.setBuyProAttempt(action.attempt)
            }

            is UIAction.Subscribe -> {

            }
        }
    }


}