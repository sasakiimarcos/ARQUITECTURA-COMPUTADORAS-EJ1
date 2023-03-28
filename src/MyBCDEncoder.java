public class MyBCDEncoder implements BCDEncoder {

    @Override
    public String encode(int a) {
        StringBuilder r = new StringBuilder();
        do{
            StringBuilder b = new StringBuilder(intToBString(a%10));
            r.append(b.reverse());
            a /= 10;
        } while(a != 0);

        return r.reverse().toString();
    }

    @Override
    public int decode(String a) {   // 4 bits
        int r = 0;
        int c = 0;
        for(int i = 0; i < a.length(); i += 4) {
            String s = a.substring(i, i+4);
            r += Math.pow(10, (a.length() - i)/4 - 1) * bStringToInt(s);  // Multiplies the digit with the decimal position
        }   // BCD[123] | i = 0 y length = 12, length-i = 12, (length-i)/4 = 3, (length-i)/4-1 = 2 --> r += 100 * 1
        return r;
    }

    private String intToBString(int c) {    //to 4 bits
        StringBuilder r = new StringBuilder();
        for(int i = 3; i >= 0; i--) {
            int aux = (int) Math.pow(2, i);
            if(c >= aux) {
                c -= aux;
                r.append("1");
            } else r.append("0");
        }
        return r.toString();
    }

    private int bStringToInt(String b) {
        int r = 0;
        for(int i = 3; i >= 0; i--) {
            if(b.charAt(i) == '1') r += Math.pow(2, b.length()-1 - i);
        }
        return r;
    }
}
