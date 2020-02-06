package com.github.vmas.beermeter.screen.addbeer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.github.vmas.beermeter.arch.ui.BaseFragment
import com.github.vmas.beermeter.databinding.FragmentAddBeerBinding

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class AddBeerFragment : BaseFragment() {
    override val viewModel: AddBeerViewModel by viewModels()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding =
        FragmentAddBeerBinding.inflate(layoutInflater, container, false)
}
