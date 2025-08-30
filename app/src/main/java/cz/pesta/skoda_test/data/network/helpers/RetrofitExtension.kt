package cz.pesta.skoda_test.data.network.helpers

import retrofit2.Retrofit

inline fun <reified T> Retrofit.api(): T = this.create(T::class.java)