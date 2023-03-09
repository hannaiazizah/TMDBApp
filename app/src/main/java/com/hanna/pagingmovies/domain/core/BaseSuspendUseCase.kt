package com.hanna.pagingmovies.domain.core

abstract class BaseSuspendUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    class None
}