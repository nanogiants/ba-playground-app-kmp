package de.appcom.kmpplayground.fragments.usecases

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import de.appcom.kmpplayground.NasaActivity
import de.appcom.kmpplayground.NotesActivity
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.SettingsActivity
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import de.appcom.kmpplayground.models.UseCasePreview
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
      { useCasePreview: UseCasePreview ->
        Timber.d("You clicked on ${useCasePreview.toString()}")
        navigateToUseCase(useCasePreview.id)

      }
    val adapter = UseCasesAdapter(onUseCaseItemClickListener)
    usecases_recyclerview.layoutManager = LinearLayoutManager(requireContext())
    usecases_recyclerview.adapter = adapter
    usecases_recyclerview.addItemDecoration(
        UseCasesItemDecorator(resources.getDimension(R.dimen.usecases_bottom_spacing))
    )
    adapter.replaceAll(
        listOf(
            UseCasePreview(0, "-", "Description"),
            UseCasePreview(1, "-", "Description"),
            UseCasePreview(2, "Nasa", "Description"),
            UseCasePreview(3, "Settings", "Description"),
            UseCasePreview(4, "Notes", "Description"),
            UseCasePreview(5, "-", "Description"),
            UseCasePreview(6, "-", "Description"),
            UseCasePreview(7, "-", "Description"),
            UseCasePreview(8, "-", "Description")
        )
    )
  }

  private fun navigateToUseCase(pos: Int) {
    when (pos) {
//      0 -> startActivity(Intent(requireContext(), ExampleActivity::class.java))
//      1 -> startActivity(Intent(requireContext(), PixelsortActivity::class.java))
      2 -> startActivity(Intent(requireContext(), NasaActivity::class.java))
      3 -> startActivity(Intent(requireContext(), SettingsActivity::class.java))
      4 -> startActivity(Intent(requireContext(), NotesActivity::class.java))
    }
  }

}