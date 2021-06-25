package Java-programs;

public class VigenereCipher {
    private String plainText, cipherText;
    private int letter;
    private int extraShift, noOfShift;

    public VigenereCipher() {
        plainText = "";
        cipherText = "";
    }

    // A = 65, Z = 90, a = 97, z = 122
    public String encrypt(String oriWord, String key) {
        oriWord = oriWord.toLowerCase();
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < oriWord.length(); i++, j++) {
            if (j == key.length())
                j = 0;
            letter = oriWord.charAt(i);
            noOfShift = key.charAt(j) - 97;
            if (Character.isLetter(letter)) {
                if(letter + noOfShift > 'z') {
                    // calculating extra shift of the letter over and above 'z'
                    extraShift = (letter + noOfShift) - ('z' + 1);
                    // adding extra shift to 'a'
                    letter = 'a' + extraShift;
                } else
                    letter += noOfShift;
            } else {
                if (letter + noOfShift > '~') {
                    // calculating extra shift of the letter over and above '~'
                    extraShift = (letter + noOfShift) - ('~' + 1);
                    // adding extra shift to ' ' (whitespace)
                    letter = ' ' + extraShift;
                } else
                    letter += noOfShift;
            }
            cipherText += (char) letter;
        }
        return cipherText;
    }

    public String decrypt(String oriWord, String key) {
        key = key.toLowerCase();
        for (int i = 0, j = 0; i < oriWord.length(); i++, j++) {
            if (j == key.length())
                j = 0;
            letter = oriWord.charAt(i);
            noOfShift = key.charAt(j) - 97;
            if (Character.isLetter(letter)) {
                if ((letter - noOfShift) < 'a') {
                    // calculating extra shift of the letter gone below 'a'
                    extraShift = ('a' - 1) - (letter - noOfShift);
                    // reducing the extra shift from 'z'
                    letter = 'z' - extraShift;
                } else
                    letter -= noOfShift;
            } else {
                if (letter - noOfShift < ' ') {
                    // calculating extra shift of the letter gone below ' ' (whitespace)
                    extraShift = (' ' - 1) - (letter - noOfShift);
                    // reducing extra shift from '~'
                    letter = '~' + extraShift;
                } else
                    letter -= noOfShift;
            }
            plainText += (char) letter;
        }
        return plainText;
    }
}
