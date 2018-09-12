package com.serverless.letspoll.commons

import java.util.Random


fun getRandomString(prefix: String): String {
    val mixedBag = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
    val salt = StringBuilder()
    val rnd = Random()
    while (salt.length < 18) { // length of the random string.
        val index = (rnd.nextFloat() * mixedBag.length).toInt()
        salt.append(mixedBag[index])
    }
    val sb = StringBuilder().apply {
        append(prefix)
        append("-")
        append(salt.toString())
    }

    return sb.toString()

}


