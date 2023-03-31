import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Calc implements Calculator {

    final String[] hex_code = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    final HashMap<String, String> hexToBinMap = new HashMap<>(Map.ofEntries(
            entry("0", "0000"), entry("1", "0001"), entry("2", "0010"),
            entry("3", "0011"), entry("4", "0100"), entry("5", "0101"),
            entry("6", "0110"), entry("7", "0111"), entry("8", "1000"),
            entry("9", "1001"), entry("A", "1010"), entry("B", "1011"),
            entry("C", "1100"), entry("D", "1101"), entry("E", "1110"),
            entry("F", "1111")
    ));
    final HashMap<String, String> binToHexMap;

    Calc() {
        // Need constructor to invert hexToBinMap, so that we have
        // binToHexMap

        binToHexMap = new HashMap<>();
        for(Map.Entry<String, String> entry : hexToBinMap.entrySet()){
            binToHexMap.put(entry.getValue(), entry.getKey());
        }
    }



    @Override
    public String sum(String a, String b){
        int carryover = 0;
        String stringA = stripZeros(a);
        String stringB = stripZeros(b);
        StringBuilder finalString = new StringBuilder();
        if (stringA.length() > stringB.length()) {
            stringB = addZeros(stringB, stringA.length());
        } else if (stringA.length() < stringB.length()) {
            stringA = addZeros(stringA, stringB.length());
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
            finalString.insert(0, "1");;
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
            StringBuilder finalString = new StringBuilder(sum(stringA, getComplement2(stringB)));
            return finalString.deleteCharAt(0).toString();
        } else {
            throw new RuntimeException("Subtrahend is greater than the minuend :(");
        }
    }

    public String mult(String a, String b) {
        String finalString = "0";
        for (int i = 0; i < to_decimal(a, 2); i++) {
            finalString = sum(finalString, b);
        }
        return finalString;
    }

    //String div(String a, String b)

    public String toHex(String binary) {
        binary = padLeftZeros(binary, binary.length() % 4);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i + 4 < binary.length(); i += 4) {
            var w = binary.substring(i, i + 4);
            sb.append(binToHexMap.get(w));
        }

        return sb.toString();
    }

    public String fromHex(String hex){
        StringBuilder sb = new StringBuilder();
        for(char c : hex.toCharArray()) {
            sb.append(hexToBinMap.get(c));
        }

        //TODO: should delete zeros on the left

        return sb.toString();
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
            if (String.valueOf(stringA.charAt(i)).equals("1") && String.valueOf(stringB.charAt(i)).equals("0")) {
                return true;
            } else if (String.valueOf(stringB.charAt(i)).equals("1") && String.valueOf(stringA.charAt(i)).equals("0")) {
                return false;
            }
        }
        return false;
    }

    //    Function that returns the 2's binary complement of a given binary number
    private String getComplement2 (String a) {
        StringBuilder newString = new StringBuilder("");
        for (int i = a.length() - 1; i >= 0; i--) {
            if (Character.toString(a.charAt(i)).equals("1")) {
                newString.insert(0, "0");
            } else {
                newString.insert(0, "1");
            }
        }
        return sum("1", newString.toString());
    }

    //    Function that strips unnecessary 0s from the left of a binary number
    private String stripZeros(String a) {
        for (int i = 0; i < a.length(); i++) {
            if (String.valueOf(a.charAt(i)).equals("1")) {
                return a.substring(i);
            }
        }
        return "0";
    }

    //    Function that adds zeros to the left of a binary number
    private String addZeros(String a, int size) {
        StringBuilder newString = new StringBuilder(a);
        for(int i = 0; i < size - a.length(); i++) {
            newString.insert(0, "0");
        }
        return newString.toString();
    }

    //    Function that turns a base 2 - 9 number into a decimal one
    private int to_decimal(String n, int b){
        int finalSum = 0;
        for(int i = 0; i < n.length(); i++) {
            finalSum += Integer.parseInt(String.valueOf(n.charAt(i))) * Math.pow(b, n.length() - i -1);
        }
        return finalSum;
    }
}
