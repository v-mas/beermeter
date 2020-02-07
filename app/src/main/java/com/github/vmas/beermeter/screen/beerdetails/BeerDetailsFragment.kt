package com.github.vmas.beermeter.screen.beerdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.github.vmas.beermeter.arch.ui.BaseFragment
import com.github.vmas.beermeter.databinding.FragmentBeerDetailsBinding

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerDetailsFragment : BaseFragment() {

    override val viewModel: BeerDetailsViewModel by viewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentBeerDetailsBinding.inflate(inflater, container, false)

}
