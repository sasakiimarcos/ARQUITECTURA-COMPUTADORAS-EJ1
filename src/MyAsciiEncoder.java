public class MyAsciiEncoder implements AsciiEncoder {

    public String encode(String binary) {    // 8 bits
        StringBuilder r = new StringBuilder();
        for(int i = 0; i < binary.length(); i += 8) {
            String s = binary.substring(i, i+8);
            char c = (char) bStringToInt(s);
            r.append(c);
        }
        return r.toString();
    }

    public String decode(String ascii) {
        StringBuilder r = new StringBuilder();

        for(int i = 0; i < ascii.length(); i ++) {
            int c = (int) ascii.charAt(i);
            r.append(intToBString(c));
        }
        return r.toString();
    }

    private int bStringToInt(String b) {
        int r = 0;
        for(int i = b.length() - 1; i >= 0; i--) {
            if (b.charAt(i) == '1') r += Math.pow(2, b.length()-1 - i);
        }
        return r;
    }

    private String intToBString(int c) {    //up to 8 bits
        StringBuilder r = new StringBuilder();
        for(int i = 7; i >= 0; i--) {
            int aux = (int) Math.pow(2, i);
            if(c >= aux) {
                c -= aux;
                r.append("1");
            } else r.append("0");
        }
        return r.toString();
    }
}
