package android.example.shop

import org.junit.Assert.*
import org.junit.Test

class IncrementTest {
    @Test
    fun incrementTest() {
        var i = 1
        i += 1
        assertEquals(i, 2)
    }
}