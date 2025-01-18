package cipher;

import Language.LanguageIdentifier;
import constants.Constants;

import java.util.ArrayList;

public class CaesarCipher {

    LanguageIdentifier languageIdentifier = new LanguageIdentifier();

    public String encrypt(String text, int key) {
        ArrayList<Character> alphabet = languageIdentifier.identifyLanguage(text);
        key = Math.abs(key);
        key %= alphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);

            int index = alphabet.indexOf(lowerChar);
            if (index == -1) {
                result.append(c);
                continue;
            }

            int newIndex = (index + key) % alphabet.size();
            char encryptedChar = alphabet.get(newIndex);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }

    public String decrypt(String text, int key) {
        ArrayList<Character> alphabet = languageIdentifier.identifyLanguage(text);
        key = Math.abs(key);
        key %= alphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char lowerChar = Character.toLowerCase(c);

            int index = alphabet.indexOf(lowerChar);
            if (index == -1) {
                result.append(c);
                continue;
            }

            int newIndex = index - key;
            if(newIndex < 0){
                newIndex += alphabet.size();
            }
            char encryptedChar = alphabet.get(newIndex);

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }

        return result.toString();
    }

    public int getKeyWithBruteforce(String text){
        ArrayList<Character> alphabet = languageIdentifier.identifyLanguage(text);
        double[] languageFrequencies = languageIdentifier
                                        .identifyLanguage(text)
                                        .equals(Constants.englishAlphabet) ?
                    Constants.englishFrequencies : Constants.ukrainianFrequencies;
        double[] charFrequencies = getCharFrequencies(text);


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

    private double[] getCharFrequencies(String text) {
        ArrayList<Character> alphabet = languageIdentifier.identifyLanguage(text);
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
