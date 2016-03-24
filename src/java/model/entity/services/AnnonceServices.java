/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.bean.Annonce;
import model.entity.bean.CateAnnonce;
import model.entity.bean.Utilisateur;

/**
 *
 * @author Delas
 */
@Stateless
public class AnnonceServices {

    @PersistenceContext
    private EntityManager em;

    public Annonce createAnnonce(final String titre, final String description, final CateAnnonce categorie, final String photoUrl, final Double prix, Utilisateur utilisateur, int telephone) {
        Calendar c = Calendar.getInstance();
        Date dateDepot = c.getTime();
        c.add(Calendar.MONTH, 6);
        Date dateFin = c.getTime();

        Annonce a = new Annonce(titre, description, categorie, photoUrl, dateDepot, dateFin, prix, utilisateur, telephone);
        em.persist(a);
        return a;
    }

    public Annonce getAnnoncebyId(final Long id){
        return em.find(Annonce.class, id);
    }
    
    public List<Annonce> getAnnonceByUser(final Utilisateur user){
        String query  = "Select * from Annonce a where a.utilisateur=:user;";
        
        Query q = em.createQuery(query);
        q.setParameter("user", user);
        
        return q.getResultList();
    }

}
