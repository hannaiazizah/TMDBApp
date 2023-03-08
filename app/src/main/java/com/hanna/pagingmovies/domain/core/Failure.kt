package com.hanna.pagingmovies.domain.core

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object DataFormatError : Failure()
    object UnknownError: Failure()
    class ServerError(val throwable: Throwable?) : Failure()
    object Empty : Failure()
}