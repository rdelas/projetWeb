/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.entity.bean.TelType;
import model.entity.bean.Telephone;
import model.entity.bean.Utilisateur;

/**
 *
 * @author Delas
 */
@Stateless
public class TelephoneServices {

    @PersistenceContext
    private EntityManager em;

    public Telephone createTelephone(String numero, TelType type, Utilisateur user){
        
        Telephone t = new Telephone(numero, type, user);
        em.persist(t);
        em.flush();
        return t;
    }
    
}
