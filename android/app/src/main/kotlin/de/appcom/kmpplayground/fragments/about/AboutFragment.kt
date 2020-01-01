package de.appcom.kmpplayground.fragments.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.fragments.base.BaseFragment
import de.appcom.kmpplayground.fragments.base.BasePresenter
import de.appcom.kmpplayground.models.AboutEntry
//import kotlinx.android.synthetic.main.about_fay_entry.about_entry_description
//import kotlinx.android.synthetic.main.about_fay_entry.about_entry_title
import kotlinx.android.synthetic.main.about_fay_entry.view.about_entry_description
import kotlinx.android.synthetic.main.about_fay_entry.view.about_entry_title
import kotlinx.android.synthetic.main.fragment_about.about_faq_linearlayout
import kotlinx.android.synthetic.main.fragment_about.about_nestedscrollview
import javax.inject.Inject

/**
 * Created by Fabian Heck on 2019-10-23.
 * Copyright (c) 2019 appcom interactive GmbH. All rights reserved.
 */
class AboutFragment : BaseFragment(R.layout.fragment_about), AboutView {

  @Inject
  lateinit var presenter: AboutPresenter

  override val titleRes: Int
    get() = R.string.about_title

  override val adaptiveToolbarScrollContainer: View
    get() = about_nestedscrollview

  override fun providePresenterToParent(): BasePresenter = presenter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    createFaqFromContent()

  }

  private fun createFaqFromContent() {
    listOf(
      AboutEntry(R.string.about_faq_q1, R.string.about_faq_a1),
      AboutEntry(R.string.about_faq_q2, R.string.about_faq_a2),
      AboutEntry(R.string.about_faq_q3, R.string.about_faq_a3),
      AboutEntry(R.string.about_faq_q4, R.string.about_faq_a4),
      AboutEntry(R.string.about_faq_q5, R.string.about_faq_a5),
      AboutEntry(R.string.about_faq_q6, R.string.about_faq_a6)
    ).forEach { faqEntry ->
      about_faq_linearlayout.addView(
        LayoutInflater.from(requireContext())
          .inflate(R.layout.about_fay_entry, null, false)
          .apply {
            this.about_entry_title?.text = getString(faqEntry.titleStringRes)
            this.about_entry_description?.text = getString(faqEntry.descriptionStringRes)
//            this.about_entry_title.text = getString(faqEntry.titleStringRes)
//            this.about_entry_description.text = getString(faqEntry.descriptionStringRes)
          }
      )
    }
  }

}

