package Vedant.Java_Projects;

public class CaesarCipher {
    private String plainText, cipherText;
    private int letter;
    private int extraShift;

    public CaesarCipher() {
        plainText = "";
        cipherText = "";
    }

    // lower limit " " - 32, upper limit "~" - 126
    public String encrypt(String oriWord, int noOfShift) {
        for(int i = 0; i < oriWord.length(); i++) {
            letter = Character.toLowerCase(oriWord.charAt(i));
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
    public String decrypt(String oriWord, int noOfShift) {
        oriWord = oriWord.toLowerCase();
        for(int i = 0; i < oriWord.length(); i++) {
            letter = oriWord.charAt(i);
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
