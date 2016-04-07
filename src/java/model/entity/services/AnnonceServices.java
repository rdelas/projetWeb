/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.bean.Annonce;
import model.entity.bean.Campus;
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

    public Annonce createAnnonce(final String titre, final String description, final CateAnnonce categorie, final String photoUrl, final Double prix, Utilisateur utilisateur) {
        Calendar c = Calendar.getInstance();
        Date dateDepot = c.getTime();
        c.add(Calendar.MONTH, 6);
        Date dateFin = c.getTime();

        Annonce a = new Annonce(titre, description, categorie, photoUrl, dateDepot, dateFin, prix, utilisateur);
        em.persist(a);        
        em.flush();
        return a;
    }

    public Annonce updateAnnonce(final Long idAnnonce, final int idUser) {

        Annonce a = em.find(Annonce.class, idAnnonce);
        Utilisateur u = em.find(Utilisateur.class, idUser);

        a.setUtilisateur(u);

        return a;
    }

    public Annonce getAnnoncebyId(final Long id) {
        return em.find(Annonce.class, id);
    }

    public List<Annonce> getAnnonceByUser(final Utilisateur user) {
        String query = "Select * from Annonce a where a.utilisateur=:user;";

        Query q = em.createQuery(query);
        q.setParameter("user", user);

        return q.getResultList();
    }

    public Collection<Annonce> getAllAnnonce() {
        Query qAnnonce = em.createQuery("select a from Annonce a");
        return qAnnonce.getResultList();
    }

    public Collection<Annonce> getAnnoncePaginated(final int page, final int pageSize) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select a from Annonce a")
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize);

        return q.getResultList();
    }
}
