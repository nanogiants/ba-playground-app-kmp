package de.appcom.kmpplayground.fragments.usecases

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.domain.UseCase
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.databinding.FragmentUsecasesBinding
import de.appcom.kmpplayground.fibonacci.presentation.FibonacciActivity
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import de.appcom.kmpplayground.game.presentation.GameActivity
import de.appcom.kmpplayground.models.appUseCases
import de.appcom.kmpplayground.nasa.presentation.NasaActivity
import de.appcom.kmpplayground.notes.presentation.NotesActivity
import de.appcom.kmpplayground.pixelsort.presentation.PixelsortActivity
import de.appcom.kmpplayground.settings.presentation.SettingsActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class UseCasesFragment : BaseFragment(), UseCasesView {

  @Inject
  lateinit var presenter: UseCasesPresenter

  private var _binding: FragmentUsecasesBinding? = null
  private val binding get() = _binding!!

  override val titleRes: Int
    get() = R.string.usecases_title

  override val adaptiveToolbarScrollContainer: View
    get() = binding.usecasesRecyclerview

  override fun providePresenterToParent(): BasePresenter = presenter

  override fun contentView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentUsecasesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpUseCasesList()
  }

  fun setUpUseCasesList() {
    val onUseCaseItemClickListener =
      { useCase: UseCase ->
        Timber.d("You clicked on ${useCase.toString()}")
        navigateToUseCase(useCase.id)

      }
    val useCasesAdapter = UseCasesAdapter(onUseCaseItemClickListener)
    binding.usecasesRecyclerview.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = useCasesAdapter
      addItemDecoration(
        UseCasesItemDecorator(resources.getDimension(R.dimen.usecases_bottom_spacing))
      )
    }
    useCasesAdapter.replaceAll(appUseCases)
  }

  private fun navigateToUseCase(id: UseCase.Identifier) {
    when (id) {
      UseCase.Identifier.NASA -> startActivity(
        Intent(
          requireContext(),
          NasaActivity::class.java
        )
      )
      UseCase.Identifier.SETTINGS -> startActivity(
        Intent(
          requireContext(),
          SettingsActivity::class.java
        )
      )
      UseCase.Identifier.NOTES -> startActivity(
        Intent(
          requireContext(),
          NotesActivity::class.java
        )
      )
      UseCase.Identifier.FIBONACCI -> startActivity(
        Intent(
          requireContext(),
          FibonacciActivity::class.java
        )
      )
      UseCase.Identifier.PIXELSORT -> startActivity(
        Intent(
          requireContext(),
          PixelsortActivity::class.java
        )
      )
      UseCase.Identifier.GAME -> startActivity(
        Intent(
          requireContext(),
          GameActivity::class.java
        )
      )
    }
  }
}