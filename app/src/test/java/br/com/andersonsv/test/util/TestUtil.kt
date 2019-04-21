package br.com.andersonsv.test.util

object TestUtil {
    fun loadTextFile(name: String): String = this::class.java.classLoader.getResource(name).readText()
}