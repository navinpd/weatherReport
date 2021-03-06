package com.big.myapplication.commons

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> FragmentActivity.getViewMode(
    viewModelFactory: ViewModelProvider.Factory
): T = ViewModelProvider(this, viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> FragmentActivity.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T = getViewMode<T>(viewModelFactory).apply(body)

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer { it?.apply(body) })
}

inline fun <reified T : ViewModel> Fragment.getViewModel(
    viewModelFactory: ViewModelProvider.Factory
): T = ViewModelProvider(this, viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> Fragment.withViewModel(
    viewModelFactory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T = getViewModel<T>(viewModelFactory).apply(body)