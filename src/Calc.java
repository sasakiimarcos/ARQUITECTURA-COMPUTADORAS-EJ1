
public class Calc implements Calculator {

    final String[] hex_code = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    @Override
    public String sum(String a, String b){
        int carryover = 0;
        String stringA = a;
        String stringB = b;
        StringBuilder finalString = new StringBuilder();
        if (a.length() > b.length()) {
            stringB = addZeros(b, a.length());
        } else if (a.length() < b.length()) {
            stringA = addZeros(b, a.length());
        }
        for (int i = stringA.length() - 1;i >= 0; i-- ) {
            switch (Integer.parseInt(String.valueOf(stringA.charAt(i))) + Integer.parseInt(String.valueOf(stringB.charAt(i))) + carryover) {
                case (0) -> {
                    finalString.insert(0, 0);
                    carryover = 0;
                }
                case (1) -> {
                    finalString.insert(0, 1);
                    carryover = 0;
                }
                case (2) -> {
                    finalString.insert(0, 0);
                    carryover = 1;
                }
                case (3) -> {
                    finalString.insert(0, 1);
                    carryover = 1;
                }
            }
        }
        if (carryover == 1) {
            finalString.insert(0, "1");
        }
        return finalString.toString();
    }

    @Override
    public String sub(String a, String b){
        if (isGreaterThan(a, b)) {
            String stringA = a;
            String stringB = b;
            if (a.length() > b.length()) {
                stringB = addZeros(b, a.length());
            } else if (a.length() < b.length()) {
                stringA = addZeros(b, a.length());
            }
            StringBuilder finalString = new StringBuilder(sum(stringA, sum(getComplement(stringB),"1")));
            return finalString.deleteCharAt(0).toString();
        } else {
            throw new RuntimeException("Subtrahend is greater than the minuend :(");
        }
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

//    Function that returns whether the first binary number is greater than the second one
    private Boolean isGreaterThan (String a, String b) {
        String stringA = a;
        String stringB = b;
        int countA = 0;
        int countB = 0;
        if (a.length() > b.length()) {
            stringB = addZeros(b, a.length());
        } else if (a.length() < b.length()) {
            stringA = addZeros(b, a.length());
        }
        for (int i = 0; i < stringA.length(); i++) {
            countA += Integer.parseInt(String.valueOf(stringA.charAt(i))) * Math.pow(2, stringA.length() - i);
            countB += Integer.parseInt(String.valueOf(stringB.charAt(i))) * Math.pow(2, stringB.length() - i);
        }
        return countA > countB;
    }

//    Function that returns the binary complement of a given binary number
    private String getComplement (String a) {
    StringBuilder newString = new StringBuilder("");
        for (int i = a.length() - 1; i >= 0; i--) {
            if (Character.toString(a.charAt(i)).equals("1")) {
                newString.insert(0, "0");
            } else {
                newString.insert(0, "1");
            }
        }
        return newString.toString();
    }

//    Function that adds zeros to the left of a binary number
    private String addZeros(String a, int size) {
        StringBuilder newString = new StringBuilder(a);
        for(int i = 0; i < size - a.length(); i++) {
            newString.insert(0, "0");
        }
        return newString.toString();
    }

    public Integer to_decimal(String n, int b){

    }
}
