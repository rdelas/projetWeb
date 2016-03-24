/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delas.common.tools.string;

/**
 *
 * @author clem
 */
public class StringUtil {
    
    
    public static boolean isEmptyTrim(final String str){
        return isEmpty(str.trim());
    }
    
    public static boolean isEmpty(final String str){
        return (str == null)?true:("".equals(str));
    }
}
