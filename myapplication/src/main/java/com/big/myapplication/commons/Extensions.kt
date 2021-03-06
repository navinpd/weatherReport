package com.big.myapplication.commons

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) : Disposable {
    compositeDisposable.add(this)
    return this
}