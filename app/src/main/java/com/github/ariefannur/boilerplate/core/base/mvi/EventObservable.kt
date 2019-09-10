package com.github.ariefannur.boilerplate.core.base.mvi

import io.reactivex.Observable

interface EventObservable<E> {
    fun events(): Observable<E>
}