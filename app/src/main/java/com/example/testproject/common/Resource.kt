package com.example.testproject.common

/*class Resource<T>(val message:String? = null,val data : T? = null) {

    class Loading<T>:Resource<T>()

    class Error<T>(message: String?):Resource<T>()
}*/

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class SUCCESS<T>(data: T) : Resource<T>(data)

    class ERROR<T>(data: T? = null, message: String) : Resource<T>(data, message)

    class LOADING<T> : Resource<T>()
}