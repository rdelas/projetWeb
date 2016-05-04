/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delas.common.tools.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author rdelas
 */
public class PasswordUtils {    
    
    public static String encryptStringWithSalt(final String pwd, final byte[] salt) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String encrypted = new String(md.digest(ArrayUtils.addAll(salt, pwd.getBytes())));
        return encrypted;
    }
    
    
    
}
