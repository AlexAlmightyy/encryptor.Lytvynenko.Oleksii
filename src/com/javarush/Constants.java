package com.javarush;

import java.util.ArrayList;
import java.util.Arrays;

class Constants {

    static final ArrayList<Character> englishAlphabet = new ArrayList<>(
            Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
                    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    );


    static final ArrayList<Character> ukrainianAlphabet = new ArrayList<>(
            Arrays.asList('а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є',
                    'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м',
                    'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х',
                    'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я')
    );

    static final double[] englishFrequencies = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
            0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327,
            0.09056, 0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074};


    static final double[] ukrainianFrequencies = {0.072, 0.017, 0.052, 0.016, 0.016, 0.035, 0.017, 0.008, 0.009,
            0.023, 0.061, 0.057, 0.006, 0.008, 0.035, 0.036, 0.031, 0.065, 0.094, 0.029, 0.047, 0.041, 0.055, 0.04,
            0.001, 0.012, 0.006, 0.018, 0.012, 0.001, 0.029, 0.004, 0.029};


}
