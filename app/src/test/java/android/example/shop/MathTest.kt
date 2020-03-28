package android.example.shop

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MathTest {

    @Test
    fun increment_test() {
        var i = 2
        i++
        assertEquals(3, i)
    }
}