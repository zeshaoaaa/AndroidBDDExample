package org.jay.example.data

/**
 * 接口响应的数据结构的基类
 */
class BaseResponse<T> {

    /**
     * 接口返回的数据类型
     */
    var data: T? = null

    /**
     * 接口返回的提示消息
     */
    var message: String? = null

    /**
     * 接口返回的响应码
     */
    var code: Int? = null

    override fun toString(): String {
        return "BaseResponse(data=$data, message=$message, code=$code)"
    }


}