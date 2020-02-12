package com.github.vmas.beermeter.screen.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.vmas.beermeter.arch.rx.disposeWith
import com.github.vmas.beermeter.arch.ui.BaseFragment
import com.github.vmas.beermeter.databinding.FragmentBeerListBinding
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerListFragment : BaseFragment() {

    override val viewModel: BeerListViewModel by viewModel()

    private val beerAdapter by lazy { BeerAdapter(viewModel::onBeerSelect) }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentBeerListBinding.inflate(layoutInflater, container, false).apply {
            recyclerView.adapter = beerAdapter
            fab.setOnClickListener {
                findNavController().navigate(BeerListFragmentDirections.navOpenAddBeer())
            }
        }

    override fun bindData() {
        super.bindData()
        viewModel.beers.observe(viewLifecycleOwner, beerAdapter::submitList)
    }

    override fun bindEvents() {
        super.bindEvents()
        viewModel.actionOpenBeerDetails.observeOn(AndroidSchedulers.mainThread()).subscribe {
            findNavController().navigate(BeerListFragmentDirections.navOpenBeerDetails(it))
        }.disposeWith(eventDisposables)
    }
}
