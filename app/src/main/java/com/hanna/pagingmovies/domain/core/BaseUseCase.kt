package com.hanna.pagingmovies.domain.core

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    class None
}