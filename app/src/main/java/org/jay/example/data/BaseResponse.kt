package org.jay.example.data

class BaseResponse<T> {

    var data: T? = null

    var message: String? = null

    var code: Int? = null

}