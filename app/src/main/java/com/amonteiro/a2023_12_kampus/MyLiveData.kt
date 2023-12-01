package com.amonteiro.a2023_12_kampus

class MyLiveData<T> {

    var value :T? = null
        set(value) {
            field=value
            observator?.invoke(value)
        }

    var observator : ((T?)->Unit)? = null
        set(newValue) {
            field =newValue
            observator?.invoke(value)
        }


}