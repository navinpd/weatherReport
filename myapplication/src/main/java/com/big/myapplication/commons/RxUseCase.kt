package com.big.myapplication.commons

import io.reactivex.Observable

interface RxUseCase<Param, Result> {
    fun run(params: Param? = null): Observable<Result>
}
