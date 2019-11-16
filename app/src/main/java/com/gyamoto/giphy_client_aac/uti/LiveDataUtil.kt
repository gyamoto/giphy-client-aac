package com.gyamoto.giphy_client_aac.uti

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

inline fun <S, T> LiveData<T>.map(crossinline func: (T?) -> S?): LiveData<S> {
    return Transformations.map(this) { func(it) }
}

inline fun <S, T> LiveData<T>.flatMap(crossinline func: (T?) -> LiveData<S>?): LiveData<S> {
    return Transformations.switchMap(this) { func(it) }
}

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observe: (T?) -> Unit) {
    observe(owner, Observer { observe(it) })
}
