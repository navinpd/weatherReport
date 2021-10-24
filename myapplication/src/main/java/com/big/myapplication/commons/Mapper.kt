package com.big.myapplication.commons

interface Mapper<Param, Result> {
    fun map(from : Param): Result
}