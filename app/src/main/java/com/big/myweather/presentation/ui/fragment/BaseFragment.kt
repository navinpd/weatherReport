package com.big.myweather.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VB : ViewBinding>(@get:LayoutRes val layoutId: Int) : Fragment(layoutId) {


    protected lateinit var viewBinding: VB

    val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = bindView(view)
        viewCreated(view, savedInstanceState)
    }

    protected abstract fun viewCreated(view: View, savedInstanceState: Bundle?)

    protected abstract fun bindView(view: View): VB

    open fun showLoading() {

    }

    open fun hideLoading() {

    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}