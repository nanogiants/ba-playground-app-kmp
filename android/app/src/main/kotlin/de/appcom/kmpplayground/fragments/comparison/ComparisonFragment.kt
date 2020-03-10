package de.appcom.kmpplayground.fragments.comparison

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.databinding.FragmentComparisonBinding
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class ComparisonFragment : BaseFragment(), ComparisonView {

  @Inject
  lateinit var presenter: ComparisonPresenter

  private var _binding: FragmentComparisonBinding? = null
  private val binding get() = _binding!!

  override val titleRes: Int
    get() = R.string.comparison_title

  override fun providePresenterToParent(): BasePresenter = presenter

  override val adaptiveToolbarScrollContainer: View
    get() = binding.comparisonScrollview

  override fun contentView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentComparisonBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onResume() {
    super.onResume()
    binding.apply {
      comparisionDataViewAll.setData(0.29f, getString(R.string.compare_average))
      comparisionDataViewUc1.setData(0.47f, getString(R.string.nasa_title))
      comparisionDataViewUc2.setData(0.26f, getString(R.string.notes_title))
      comparisionDataViewUc3.setData(0.1f, getString(R.string.settings_title))
      comparisionDataViewUc4.setData(0.36f, getString(R.string.fibonacci_title))
      comparisionDataViewUc5.setData(0.0f, getString(R.string.pixelsort_title))
      comparisionDataViewUc6.setData(0.54f, getString(R.string.game_title))
    }
  }
}