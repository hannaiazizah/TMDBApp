package com.hanna.pagingmovies.domain.core

import com.hanna.pagingmovies.data.core.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class FlowUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Flow<Either<Failure, Type>>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        appDispatchers: AppDispatchers,
        onResult: (Flow<Either<Failure, Type>>) -> Unit = {}
    ) {
        scope.launch {
            val deferred = async(appDispatchers.io) {
                run(params)
            }
            onResult(deferred.await())
        }
    }

    class None
}