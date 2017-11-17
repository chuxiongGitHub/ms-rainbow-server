package com.rainbow.ms.security

object WhiteList {

    private val list = arrayListOf("/druid")

    fun get() = list

    fun check(uri: String) = list.any { uri.startsWith(it) }
}