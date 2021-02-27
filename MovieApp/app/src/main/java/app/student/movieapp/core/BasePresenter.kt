package app.student.movieapp.core

import app.student.movieapp.core.model.NetworkError
import kotlin.reflect.KClass

interface BasePresenter {


    @Deprecated("Dont using properties reflector for errors network")
    fun <T: Any> onResponseOnErrorNetwork(clazz: KClass<T>)

    fun <T> onResponseOnErrorNetwork(t: T)
}