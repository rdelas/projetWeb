/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delas.common.tools.number;

import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author rdelas
 */
public class MyNumberUtils {

    public static boolean isDouble(Double number) {
        return Math.ceil(number) != Math.floor(number);
    }

    public static boolean isLong(double number) {
        return Math.ceil(number) == Math.floor(number);
    }
}
