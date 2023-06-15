package citics.sharing.data.repository

import com.citics.valuation.data.model.response.ErrorResponse

/**
 * Created by ChinhQT on 02/10/2022.
 */
sealed class Resource<T>(
    val data: T? = null,
    val error: ErrorResponse? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Failure<T>(
        dataFail: ErrorResponse? = null
    ) : Resource<T>(error = dataFail)
    class Loading<T> : Resource<T>()
    class None<T> : Resource<T>()
}