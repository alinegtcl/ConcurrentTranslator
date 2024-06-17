package com.tolentinoluisi.concurrenttranslator.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.tolentinoluisi.concurrenttranslator.R
import com.tolentinoluisi.concurrenttranslator.databinding.FragmentTranslatorBinding
import com.tolentinoluisi.concurrenttranslator.domain.entities.Language
import com.tolentinoluisi.concurrenttranslator.domain.utils.Constants.EMPTY_STRING
import com.tolentinoluisi.concurrenttranslator.presentation.viewmodel.TranslatorState
import com.tolentinoluisi.concurrenttranslator.presentation.viewmodel.TranslatorViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatorFragment : Fragment() {
    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TranslatorViewModel by viewModel()

    private var text: String = EMPTY_STRING
    private lateinit var sourceLanguage: Language
    private lateinit var targetLanguage: Language

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLanguages()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputText()
        setupObserver()
        setupButton()
    }

    private fun setupInputText() {
        binding.inputText.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                text = it.toString()
            } else {
                binding.inputText.error = getString(R.string.label_field_required)
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    TranslatorState.HideLoading -> binding.loading.visibility = View.GONE
                    TranslatorState.ShowLoading -> binding.loading.visibility = View.VISIBLE
                    TranslatorState.LanguagesError -> {
                        binding.outputText.visibility = View.GONE
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.errorMessage.text = getString(R.string.error_languages)
                    }

                    TranslatorState.TranslateError -> {
                        binding.outputText.visibility = View.GONE
                        binding.errorLayout.visibility = View.VISIBLE
                        binding.errorMessage.text = getString(R.string.error_message)
                    }

                    is TranslatorState.LanguagesSuccess -> {
                        setupSpinners(state.languages)
                    }

                    is TranslatorState.TranslateSuccess -> {
                        binding.outputText.text = state.outputText
                        binding.errorLayout.visibility = View.GONE
                        binding.outputText.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupSpinners(languages: List<Language>) {
        val adapter = ArrayAdapter(
            requireContext(),
            org.koin.android.R.layout.support_simple_spinner_dropdown_item,
            languages.map { it.name }
        )
        binding.sourceLanguageSpinner.adapter = adapter
        binding.targetLanguageSpinner.adapter = adapter

        binding.sourceLanguageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    sourceLanguage = languages[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        binding.targetLanguageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    targetLanguage = languages[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setupButton() {
        binding.translateButton.setOnClickListener {
            if (text.isNotEmpty()) {
                viewModel.translate(
                    text,
                    sourceLanguage,
                    targetLanguage
                )
            } else {
                binding.inputText.error = getString(R.string.label_field_required)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}