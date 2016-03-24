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
public enum CateAnnonce {      
    VEHICULE("Vehicule"),
    MULTIMEDIA("Multimedia"),
    MEUBLE("Meuble"),
    VETEMENT("Vetement"),
    MUSIQUE("Musique");
    
    private String name;
    
    private CateAnnonce(final String name) {
        this.name = name;
    }
    
    private String getName() {
        return name;
    }
}
