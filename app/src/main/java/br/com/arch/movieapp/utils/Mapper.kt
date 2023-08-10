package br.com.arch.movieapp.utils

interface Mapper<T, Z> {

    fun converter(from: T): Z
}