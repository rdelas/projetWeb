/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.bean.Adresse;
import model.entity.bean.Campus;

/**
 *
 * @author Delas
 */
@Stateless
public class CampusServices {
 
    @PersistenceContext
    private EntityManager em;

    public Campus createCampus(String nom, Adresse adresse){
        Campus c = new Campus(nom, adresse);
        em.persist(c);
        return c;
    }
    
    public Campus findCampusById(final Long id){
        return em.find(Campus.class, id);
    }
    
    public List<Campus> gatAllCampus(){
        return em.createQuery("SELECT c FROM Campus c", Campus.class).getResultList();
    }
    
}
