import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsciiEncoderTest {

    AsciiEncoder asciiEncoder = new MyAsciiEncoder();

    final String A = "01000001";    // A = 0100_0001 = 65
    final String L = "01001100";    // L = 0100_1100 = 76

    final String ABC = "010000010100001001000011";  // "0100_0001-0100_0010-0100_0011"
    final String LMO = "010011000100110101001111";  // "0100_1100-0100_1101-0100_1111"

    @Test
    void encodeSingleCharacter() {
        assertEquals("A", asciiEncoder.encode(A));
        assertEquals("L", asciiEncoder.encode(L));
    }

    @Test
    void encodeMultipleCharacters() {
        assertEquals("ABC", asciiEncoder.encode(ABC));
        assertEquals("LMO", asciiEncoder.encode(LMO));
    }

    @Test
    void decodeSingleCharacter() {
        assertEquals(A, asciiEncoder.decode("A"));
        assertEquals(L, asciiEncoder.decode("L"));
    }

    @Test
    void decodeMultipleCharacters() {
        assertEquals(ABC, asciiEncoder.decode("ABC"));
        assertEquals(LMO, asciiEncoder.decode("LMO"));
    }
}