package com.example.fact.global

object TextUtils {

    /**
     * @param str - Check if the string is not null or empty.
     * @return boolean - Returns true if the string is valid.
     */
    fun isValidString(str: String?): Boolean {
        return (str != null && str.isNotEmpty() && str.trim() != "")
    }
}
