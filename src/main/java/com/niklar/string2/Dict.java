package com.niklar.string2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by art on 14.03.15.
 */
public class Dict {

    private static final LongAdder dictAdder = new LongAdder();
    private final static BiMap<String, Integer> dict = Maps.synchronizedBiMap(HashBiMap.create());

    enum WORD_FORM {ALL_LOWERCASE, FIRST_UPPER, OTHER}

    private Dict() {

    }

    public static int[] map(final String origin) {
        return Arrays.asList(origin.split(" ")).stream()
                .mapToInt(word -> Dict.getIdForWord(word))
                .toArray();
    }

    public static String map(final int[] arr) {
        return Ints.asList(arr).stream()
                .map(Dict::getWordForId)
                .reduce((s1, s2) -> s1 + " " + s2)
                .get();
    }

    private static int getIdForWord(final String word) {
        final WORD_FORM wordForm = getWordForm(word);
        if (wordForm.equals(WORD_FORM.OTHER) || wordForm.equals(WORD_FORM.ALL_LOWERCASE)) {
            Integer idx = dict.get(word);
            if (idx != null) {
                return idx;
            } else {
                return insertAndReturnId(word);
            }
        } else {
            // FIRST_UPPER
            Integer idx = dict.get(word.toLowerCase());
            if (idx != null) {
                return idx * -1;
            } else {
                return insertAndReturnId(word.toLowerCase()) * -1;
            }
        }
    }

    private static String getWordForId(final int id) {
        int getId = id < 0 ? id * -1 : id;
        final String lowercseWord = dict.inverse().get(getId);
        if (id < 0) {
            if (lowercseWord.length() == 1)
                return lowercseWord.toUpperCase();
            return lowercseWord.substring(0, 1).toUpperCase() + lowercseWord.substring(1);
        }
        return lowercseWord;
    }

    private synchronized static int insertAndReturnId(String word) {
        dictAdder.add(1);
        dict.put(word, dictAdder.intValue());
        return dictAdder.intValue();
    }


    private static WORD_FORM getWordForm(final String word) {
        if (word.length() == 0) {
            return WORD_FORM.OTHER;
        }
        if (Character.isUpperCase(word.charAt(0))) {
            if (word.length() == 1) {
                return WORD_FORM.FIRST_UPPER;
            } else {
                return StringUtils.isAllLowerCase(word.substring(1))
                        ? WORD_FORM.FIRST_UPPER
                        : WORD_FORM.OTHER;
            }
        } else {
            return StringUtils.isAllLowerCase(word)
                    ? WORD_FORM.ALL_LOWERCASE
                    : WORD_FORM.OTHER;
        }
    }


}
