package com.javarush;

import java.util.ArrayList;
import java.util.List;

class LanguageIdentifier {


    static List<Character> identifyCharacter(char toIdentify){
        if (Constants.ukrainianAlphabet.contains(Character.toLowerCase(toIdentify))) {
            return  Constants.ukrainianAlphabet;
        }else {
            return Constants.englishAlphabet;
        }
    }
}
