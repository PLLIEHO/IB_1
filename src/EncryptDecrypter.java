import java.util.Random;

public class EncryptDecrypter {

    private int key;
    private String input;

    public EncryptDecrypter(int key, String input){
        this.key = key;
        this.input = input;
    }

    public String encrypt(){
        String keyString = decimalToBinary();
        char[] keyArray = keyString.toCharArray();
        char[] inputArray = input.toCharArray();

        int counter = 0;
        String enc = "";
        while(counter < input.length() - 1) {
            for (char c : keyArray) {
                if (c == '1') {
                    enc += inputArray[counter];
                    counter++;
                    if(counter > input.length() - 1) break;
                } else {
                    enc += pseudoRandomGenerator();
                }
            }
        }

        return enc;
    }

    public String decrypt(){
        String keyString = decimalToBinary();
        char[] keyArray = keyString.toCharArray();
        char[] inputArray = input.toCharArray();

        int counter = input.length() - 1;
        String dec = "";
        int offset = 0;

        while(counter > 0){
            for(int i = 0; i < keyArray.length - 1; i++) {
                counter--;
                if (keyArray[i] == '1') {
                    dec += inputArray[offset + i];
                }
                if (counter < 0) break;
            }
            offset += keyArray.length;
            counter--;

        }

        return dec;
    }

    private char pseudoRandomGenerator(){
        Random rn = new Random();
        int code = 0;
        if(rn.nextBoolean()) {
            code = rn.nextInt(1103 - 1040 + 1) + 1040;
        }
        else {
            code = rn.nextInt(63 - 32 + 1) + 32;
        }
        return (char) code;
    }

    public String decimalToBinary(){
        String keyString = Integer.toBinaryString(key);
        if(keyString.length() < 16){
            String nabivka = "";
            for(int i = 0; i < 16-keyString.length(); i++){
                nabivka += '0';
            }
            return nabivka + keyString;
        }
        else if(keyString.length() > 16){
            return keyString.substring(0, 15);
        }
        return keyString;
    }
}
