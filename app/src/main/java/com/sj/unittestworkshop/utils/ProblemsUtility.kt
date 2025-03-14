package com.sj.unittestworkshop.utils


fun String?.isPalindrome(): Boolean {
    val cleaned = this?.filter { it.isLetterOrDigit() }?.lowercase() ?: ""
    var start = 0
    var end = cleaned.length - 1

    while (start < end) {
        if (cleaned[start] != cleaned[end]) {
            return false
        }
        start++
        end--
    }
    return true
}

