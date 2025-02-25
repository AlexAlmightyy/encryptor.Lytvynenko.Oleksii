package com.javarush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaesarCipher {


    public String encrypt(String text, int key) {
        key = Math.abs(key);
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            char encryptedChar = getEncryptedChar(key, c);
            result.append(Character.isUpperCase(c) ? Character.toUpperCase(encryptedChar) : encryptedChar);
        }
        return result.toString();
    }

    private static Character getEncryptedChar(int key, char c) {
        List<Character> alphabet = LanguageIdentifier.identifyCharacter(c);
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
            Character decryptedChar = getDecryptedChar(key, c);
            if (decryptedChar == null) continue;

            result.append(Character.isUpperCase(c) ? Character.toUpperCase(decryptedChar) : decryptedChar);
        }
        return result.toString();
    }

    private static Character getDecryptedChar(int key, char c) {
        List<Character> alphabet = LanguageIdentifier.identifyCharacter(c);
        key %= alphabet.size();
        char lowerChar = Character.toLowerCase(c);
        int index = alphabet.indexOf(lowerChar);
        if (index == -1) {
            return c;
        }
        int newIndex = index - key;
        if (newIndex < 0) {
            newIndex += alphabet.size();
        }
        return alphabet.get(newIndex);
    }

    public int getKeyWithBruteforce(String text) {
        Map<String, String> textParts = splitByAlphabet(text);
        String englishText = textParts.getOrDefault("english", "");
        String ukrainianText = textParts.getOrDefault("ukrainian", "");
        int englishKey = englishText.isEmpty() ? 0 : findKeyForAlphabet(englishText, Constants.englishAlphabet, Constants.englishFrequencies);
        int ukrainianKey = ukrainianText.isEmpty() ? 0 : findKeyForAlphabet(ukrainianText, Constants.ukrainianAlphabet, Constants.ukrainianFrequencies);
        if (englishKey != 0 && ukrainianKey != 0) {
            while (englishKey != ukrainianKey) {
                if (englishKey < ukrainianKey) {
                    englishKey += Constants.englishAlphabet.size();
                } else {
                    ukrainianKey += Constants.ukrainianAlphabet.size();
                }
            }
        }
        return englishText.length() > ukrainianText.length() ? englishKey : ukrainianKey;
    }

    private int findKeyForAlphabet(String text, List<Character> alphabet, double[] frequencies) {
        double[] charFrequencies = getCharFrequencies(text, alphabet);
        int bestKey = 0;
        double minDifference = Double.MAX_VALUE;
        for (int key = 0; key < alphabet.size(); key++) {
            double[] shiftedFrequencies = shiftFrequencies(charFrequencies, key);
            double difference = calculateDifference(shiftedFrequencies, frequencies);
            if (difference < minDifference) {
                minDifference = difference;
                bestKey = key;
            }
        }
        return bestKey;
    }

    private Map<String, String> splitByAlphabet(String text) {
        StringBuilder englishPart = new StringBuilder();
        StringBuilder ukrainianPart = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Constants.englishAlphabet.contains(Character.toLowerCase(c))) {
                englishPart.append(c);
            } else if (Constants.ukrainianAlphabet.contains(Character.toLowerCase(c))) {
                ukrainianPart.append(c);
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put("english", englishPart.toString());
        result.put("ukrainian", ukrainianPart.toString());
        return result;
    }

    private double[] getCharFrequencies(String text, List<Character> alphabet) {
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
