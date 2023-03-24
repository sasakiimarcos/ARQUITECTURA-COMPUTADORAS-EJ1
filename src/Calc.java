public class Calc implements Calculator {

    final String[] hex_code = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    String sum(String a, String b) {
        String finalnumber = "";
        
        if(a.length() < b.length()) a = padLeftZeros(a, b.length() - a.length());
        else b = padLeftZeros(b, a.length() - b.length());         // leaves them with same length

        if (a.length() == b.length()) {
            int carryover = 0;
          for (int i = 0; i < a.length(); i++) {
              // checks different cases to deal with carryover idk
              switch((int) a.charAt(i) + (int) b.charAt(i) + carryover){
                  case(0):
                      finalnumber += Integer.toString(0);
                      break;
                  case(1):
                      finalnumber += Integer.toString(1);
                      break;
                  case(2):
                      finalnumber += Integer.toString(0);
                      carryover = 1;
                      break;
                  case(3):
                      finalnumber += Integer.toString(1);
                      carryover = 1;
                      break;
              }
          }
        } else if (a.length() > b.length()) {
            
        }
    }
    String sub(String a, String b) {
        // Get b as a char array
        char[] bChars = b.toCharArray();

        // Swap the char array to get b's complement
        for (int i = 0; i < b.length(); i++) {
            if (bChars[i] == 1) bChars[i] = 0;
            else bChars[i] = 1;
        }

        // Get the complement as a string
        String bComplement = new String(bChars);

        // Add the complement to a and ignore the carry
        return (a + b).substring(1);

    }
    //String mult(String a, String b)
    //String div(String a, String b)

    String toHex(String binary) {
        List<String> word = {};
        int num = 0;
        for (int i = 0; i + 4 < binary.length(); i += 4) {
            var w = binary.substring(i, i + 4);
            for (int j = 0; j < w.length(); j++) ;


        }

        return word;
    }

    public String fromHex(String hex){
        String r = "";
        for(char i : hex.toCharArray()) {
            int aux = 0;
            for(int j = 0; j < hex_code.length; j++) {
                if(hex_code[j].equals(i.toString())) {
                    aux = j;
                    break;
                }
            }
            // to
        }
    }

    private String padLeftZeros(String n, int len) {
        String aux = "";
        for(int i = 0; i < len; i++) {
            aux += "0";
        }
        return aux + n;
    }

    public Integer to_decimal(String n, int b){

    }
}
