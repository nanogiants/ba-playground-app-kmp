package de.appcom.kmpplayground.fragments.usecases

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import app.UseCase
import app.appUseCases
import de.appcom.kmpplayground.FibonacciActivity
import de.appcom.kmpplayground.NasaActivity
import de.appcom.kmpplayground.NotesActivity
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.SettingsActivity
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import de.appcom.kmpplayground.models.UseCasePreview
import de.appcom.pixelsort.PixelsortActivity
import kotlinx.android.synthetic.main.fragment_usecases.usecases_recyclerview
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class UseCasesFragment : BaseFragment(R.layout.fragment_usecases), UseCasesView {

  @Inject
  lateinit var presenter: UseCasesPresenter

  override val titleRes: Int
    get() = R.string.usecases_title

  override val adaptiveToolbarScrollContainer: View
    get() = usecases_recyclerview

  override fun providePresenterToParent(): BasePresenter = presenter

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
    val adapter = UseCasesAdapter(onUseCaseItemClickListener)
    usecases_recyclerview.layoutManager = LinearLayoutManager(requireContext())
    usecases_recyclerview.adapter = adapter
    usecases_recyclerview.addItemDecoration(
        UseCasesItemDecorator(resources.getDimension(R.dimen.usecases_bottom_spacing))
    )
    val a =
    adapter.replaceAll(appUseCases)
  }

  private fun navigateToUseCase(id: UseCase.Identifier) {
    when (id) {
      UseCase.Identifier.NASA -> startActivity(Intent(requireContext(), NasaActivity::class.java))
      UseCase.Identifier.SETTINGS -> startActivity(Intent(requireContext(), SettingsActivity::class.java))
      UseCase.Identifier.NOTES -> startActivity(Intent(requireContext(), NotesActivity::class.java))
      UseCase.Identifier.FIBONACCI -> startActivity(Intent(requireContext(), FibonacciActivity::class.java))
      UseCase.Identifier.PIXELSORT -> startActivity(Intent(requireContext(), PixelsortActivity::class.java))
    }
  }

}