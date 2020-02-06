package com.github.vmas.beermeter.screen.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.github.vmas.beermeter.arch.ui.BaseFragment
import com.github.vmas.beermeter.databinding.FragmentBeerListBinding

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerListFragment : BaseFragment() {

    override val viewModel: BeerListViewModel by viewModel()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentBeerListBinding.inflate(layoutInflater, container, false).apply {
            fab.setOnClickListener {
                findNavController().navigate(BeerListFragmentDirections.navOpenAddBeer())
            }
        }
}
