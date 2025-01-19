package cipher;

import Language.LanguageIdentifier;
import constants.Constants;

import java.util.ArrayList;

public class CaesarCipher {


    public String encrypt(String text, int key) {
        key = Math.abs(key);
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {

            char encryptedChar = getEncryptedChar(key, c, result);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }

    private static Character getEncryptedChar(int key, char c, StringBuilder result) {
        ArrayList<Character> alphabet = LanguageIdentifier.identifyCharacter(c);
        key %= alphabet.size();
        char lowerChar = Character.toLowerCase(c);

        int index = alphabet.indexOf(lowerChar);
        if (index == -1) {
            return c;
        }

        int newIndex = (index + key) % alphabet.size();
        return alphabet.get(newIndex);
    }

    public String decrypt(String text, int key) {

        key = Math.abs(key);

        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            Character decryptedChar = getDecryptedChar(key, c, result);
            if (decryptedChar == null) continue;

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(decryptedChar) :decryptedChar);
        }

        return result.toString();
    }

    private static Character getDecryptedChar(int key, char c, StringBuilder result) {
        ArrayList<Character> alphabet = LanguageIdentifier.identifyCharacter(c);
        key %= alphabet.size();
        char lowerChar = Character.toLowerCase(c);

        int index = alphabet.indexOf(lowerChar);
        if (index == -1) {
            return c;
        }

        int newIndex = index - key;
        if(newIndex < 0){
            newIndex += alphabet.size();
        }
        return alphabet.get(newIndex);
    }

    public int getKeyWithBruteforce(String text){
        ArrayList<Character> alphabet = identifyAlphabet(text);
        double[] languageFrequencies = alphabet.equals(Constants.englishAlphabet) ?
                Constants.englishFrequencies : Constants.ukrainianFrequencies;

        double[] charFrequencies = getCharFrequencies(text, alphabet);


        int bestKey = 0;
        double minDifference = Double.MAX_VALUE;

        for (int key = 0; key < alphabet.size(); key++) {

            double[] shiftedFrequencies = shiftFrequencies(charFrequencies, key);


            double difference = calculateDifference(shiftedFrequencies, languageFrequencies);


            if (difference < minDifference) {
                minDifference = difference;
                bestKey = key;
            }
        }

        return bestKey;
    }

    private ArrayList<Character> identifyAlphabet(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                return LanguageIdentifier.identifyCharacter(c);
            }
        }
        return Constants.englishAlphabet;
    }

    private double[] getCharFrequencies(String text, ArrayList<Character> alphabet) {
        double[] frequencies = new double[alphabet.size()];
        int[] charCounts = new int[alphabet.size()];
        int totalChars = 0;

        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);
            int index = alphabet.indexOf(lowerChar);
            if (index >= 0) {
                charCounts[index]++;
                totalChars++;
            }
        }

        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = totalChars > 0 ? (double) charCounts[i] / totalChars : 0.0;
        }

        return frequencies;
    }

    private double[] shiftFrequencies(double[] frequencies, int shift) {
        int size = frequencies.length;
        double[] shifted = new double[size];
        for (int i = 0; i < size; i++) {
            shifted[i] = frequencies[(i + shift) % size];
        }
        return shifted;
    }

    private double calculateDifference(double[] shiftedFrequencies, double[] normalFrequencies) {
        double difference = 0.0;

        for (int i = 0; i < shiftedFrequencies.length; i++) {
            difference += Math.abs(shiftedFrequencies[i] - normalFrequencies[i]);
        }

        return difference;
    }


}
