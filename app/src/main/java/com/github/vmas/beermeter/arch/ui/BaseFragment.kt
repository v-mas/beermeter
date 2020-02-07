package com.github.vmas.beermeter.arch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.github.vmas.beermeter.BR
import com.github.vmas.beermeter.arch.di.dependencyContainer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.DisposableContainer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: ViewModel
    protected lateinit var binding: ViewDataBinding

    protected inline fun <reified T : ViewModel> viewModel() =
        viewModels<T>(factoryProducer = { dependencyContainer.viewModelFactory(this) })

    protected val eventDisposables: DisposableContainer
        get() = disposables

    private var disposables = CompositeDisposable()

    protected abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = createBinding(inflater, container)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewmodel, viewModel)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }
    }

    override fun onStop() {
        disposables.dispose()
        super.onStop()
    }
}
