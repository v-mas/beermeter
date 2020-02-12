package com.github.vmas.beermeter.screen.beerdetails

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.github.vmas.beermeter.arch.rx.disposeWith
import com.github.vmas.beermeter.arch.ui.BaseFragment
import com.github.vmas.beermeter.databinding.FragmentBeerDetailsBinding
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerDetailsFragment : BaseFragment() {

    override val viewModel: BeerDetailsViewModel by viewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentBeerDetailsBinding.inflate(inflater, container, false)

    override fun bindEvents() {
        super.bindEvents()
        viewModel.eventOpenBrowser.observeOn(AndroidSchedulers.mainThread()).subscribe {
            with(requireActivity()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }.disposeWith(eventDisposables)
    }
}
