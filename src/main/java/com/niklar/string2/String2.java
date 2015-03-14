package com.niklar.string2;

import java.util.Arrays;

/**
 * Created by art on 14.03.15.
 */
public class String2 implements Comparable<String2>{

    private final int[] wordArray;

    public String2(final String string) {
        wordArray = Dict.map(string);
    }

    @Override
    public String toString() {
        return Dict.map(wordArray);
    }

    @Override
    public int compareTo(final String2 o) {
        return this.toString().compareTo(o.toString());
    }
}
