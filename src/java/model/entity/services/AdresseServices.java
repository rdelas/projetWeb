/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.bean.Adresse;

/**
 *
 * @author Delas
 */
@Stateless
public class AdresseServices {

    @PersistenceContext
    private EntityManager em;

    public Adresse createAdresse(String rue, String complement, String codePostal, String ville, Double latitude, Double longitude) {

        Adresse a = new Adresse(rue, complement, codePostal, ville, latitude, longitude);
        em.persist(a);
        return a;
    }

}
