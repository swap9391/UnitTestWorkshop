package com.sj.unittestworkshop.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ProblemsUtilityTest {

    @Test
    fun testPalindrome_emptyString() {
        assertTrue("".isPalindrome())
    }

    @Test
    fun testPalindrome_nullString() {
        assertTrue(null.isPalindrome())
    }

    @Test
    fun testPalindrome_singleCharacter() {
        assertTrue("a".isPalindrome())
    }

    @Test
    fun testPalindrome_simplePalindrome() {
        assertTrue("madam".isPalindrome())
    }

    @Test
    fun testPalindrome_caseInsensitive() {
        assertTrue("Madam".isPalindrome())
    }

    @Test
    fun testPalindrome_withSpacesAndPunctuation() {
        assertTrue("A man, a plan, a canal: Panama".isPalindrome())
    }

    @Test
    fun testPalindrome_nonPalindrome() {
        assertFalse("hello".isPalindrome())
    }

    @Test
    fun testPalindrome_almostPalindrome() {
        assertFalse("madame".isPalindrome())
    }

    @Test
    fun testPalindrome_numericPalindrome() {
        assertTrue("121".isPalindrome())
    }

    @Test
    fun testPalindrome_alphanumericPalindrome() {
        assertFalse("racecar1".isPalindrome())
    }

    @Test
    fun testPalindrome_alphanumericNonPalindrome() {
        assertFalse("racecar12".isPalindrome())
    }

}