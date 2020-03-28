package android.example.shop

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MathTest {

    @Test
    fun dividing_test() {
        var i = 4
        var j = 2
        assertEquals(i / j, 2)
    }

    @Test
    fun increment_test() {
        var i = 2
        i++
        assertEquals(3, i)
    }

    @Test
    fun multiplication_test() {
        var i = 4
        var j = 2
        assertEquals(i * j, 8)
    }
}