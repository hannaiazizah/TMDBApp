package com.hanna.pagingmovies.domain.core

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 *
 * adjusted from https://github.com/android10/Android-CleanArchitecture-Kotlin/
 */

abstract class UseCase<Type, Params> : BaseSuspendUseCase<Either<Failure, Type>, Params>()