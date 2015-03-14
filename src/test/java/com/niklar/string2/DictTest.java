package com.niklar.string2;

import com.google.common.primitives.Ints;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DictTest {

    @Test
    public void testStringToIntMapping() throws Exception {
        int[] map = Dict.map("Vegetarier essen, meinem Essen das Essen weg.");
    }
}