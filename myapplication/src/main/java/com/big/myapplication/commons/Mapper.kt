package com.big.myapplication.commons

interface Mapper<Param, Result> {
    fun mpa(from : Param): Result
}