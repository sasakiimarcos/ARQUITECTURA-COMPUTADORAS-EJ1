import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCDEncoderTest {

    BCDEncoder bcdEncoder = new MyBCDEncoder();

    String[] bcdCode = {
            "0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111",
            "1000", "1001"
    };

    @Test
    void encodeSingleNumber() {
        for(int i = 0; i < bcdCode.length; i++)
            assertEquals(bcdCode[i], bcdEncoder.encode(i));
    }

    @Test
    void encodeMultipleNumber() {
        assertEquals(bcdCode[1] + bcdCode[2] + bcdCode[3], bcdEncoder.encode(123));
        assertEquals(bcdCode[6] + bcdCode[5] + bcdCode[4], bcdEncoder.encode(654));
        assertEquals(bcdCode[8] + bcdCode[8] + bcdCode[8], bcdEncoder.encode(888));
    }

    @Test
    void decodeSingleNumber() {
        for(int i = 0; i < bcdCode.length; i++)
            assertEquals(i, bcdEncoder.decode(bcdCode[i]));
    }

    @Test
    void decodeMultipleNumber() {
        assertEquals(123, bcdEncoder.decode(bcdCode[1] + bcdCode[2] + bcdCode[3]));
        assertEquals(11235, bcEncoder.decode(bcdCode[1] + bcdCode[1] + bcdCode[2] + bcdCode[3] + bcdCode[5]))
        assertEquals(9856, bcEncoder.decode(bcdCode[9] + bcdCode[8] + bcdCode[5] + bcdCode[6]))
    }
}