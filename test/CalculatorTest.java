import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator myCalc = new Calc();

    @Test
    void sumZeros() {
        assertEquals("0", myCalc.sum("0", "0"));
    }

    @Test
    void sumDiffLengthZero() {
        assertEquals("10", myCalc.sum("10", "0"));
        assertEquals("10", myCalc.sum("0", "10"));

        assertEquals("11010100", myCalc.sum("11010100", "0"));
        assertEquals("11010100", myCalc.sum("0", "11010100"));

        assertEquals("111111111010100", myCalc.sum("0", "111111111010100"));
        assertEquals("111111111010100", myCalc.sum("111111111010100", "0"));
    }

    @Test
    void sumSameLength() {
        assertEquals("1010", myCalc.sum("100", "110"));
        assertEquals("10111", myCalc.sum("1010", "1101"));
        assertEquals("11001", myCalc.sum("1010", "1111"));
    }

    @Test
    void sumDiffLength() {
        assertEquals("100000000000", myCalc.sum("11111111111", "1"));

        assertEquals("111000", myCalc.sum("101011", "1101"));
        assertEquals("10000011010", myCalc.sum("10101001", "1101110001"));
        assertEquals("110111110101010", myCalc.sum("1011001", "110111101010001"));
    }

    @Test
    void sub() {
        fail();
    }

    @Test
    void subZeros() {
        assertEquals("0", myCalc.sub("0", "0"));
    }

    @Test
    void subDiffLengthZero() {
        assertEquals("10", myCalc.sub("10", "0"));

        assertEquals("11010100", myCalc.sub("11010100", "0"));

        assertEquals("111111111010100", myCalc.sub("111111111010100", "0"));
    }

    @Test
    void subSameLength() {
        assertEquals("0", myCalc.sub("1111", "1111"));
        assertEquals("11", myCalc.sub("1100", "1001"));
        assertEquals("1", myCalc.sub("1011", "1010"));
    }

    @Test
    void subDiffLength() {
        assertEquals("11111111111", myCalc.sub("100000000000", "1"));

        assertEquals("1001111000", myCalc.sub("1011001110", "1010110"));
        assertEquals("110100101", myCalc.sub("111000110", "100001"));
        assertEquals("10011101011", myCalc.sub("10101101010", "1111111"));
    }

    @Test
    void underflow {
        // Assuming we shouldn't take negatives into account
        assertThrows(UnderflowException.class, () -> {
            myCalc.sub("0", "1");
        })
    }

    @Test
    void zeroToHex() {
        assertEquals("0", myCalc.toHex("0"));
    }

    @Test
    void TwoBytesToHex() {
        assertEquals("8B64", myCalc.toHex("1000101101100100"));
        assertEquals("F0C3", myCalc.toHex("1111000011000011"));
        assertEquals("D456", myCalc.toHex("1101010001010110"));
    }

    @Test
    void diffLengthsToHex() {
        assertEquals("9", myCalc.toHex("9"));
        assertEquals("12CA5", myCalc.toHex("10010110010100101"));
        assertEquals("1000", myCalc.toHex("1000000000000"));
        assertEquals("AA", myCalc.toHex("10101010"));
    }

    @Test
    void zeroFromHex() {
        assertEquals("0", myCalc.fromHex("0"));
    }

    @Test
    void TwoBytesFromHex() {
        assertEquals("1000101101100100", myCalc.fromHex("8B64"));
        assertEquals("1111000011000011", myCalc.fromHex("F0C3"));
        assertEquals("1101010001010110", myCalc.fromHex("D456"));
    }

    @Test
    void diffLengthsFromHex() {
        assertEquals("9", myCalc.fromHex("9"));
        assertEquals("10010110010100101", myCalc.fromHex("12CA5"));
        assertEquals("1000000000000", myCalc.fromHex("1000"));
        assertEquals("10101010", myCalc.fromHex("AA"));
    }

}
