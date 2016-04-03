/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.bean;

/**
 *
 * @author Delas
 */
public enum TelType {
    DOMICILE("Domicile"),
    MOBILE("Mobile"),
    AUTRE("Autre");

    private final String value;
    
    private TelType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
