/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionnaires.produit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.produit.Produit;
import model.utilisateurs.Utilisateurs;

/**
 *
 * @author Delas
 */
public class ProduitServices {
    
    @PersistenceContext
    private EntityManager em;
    
    public Produit creeProduit(final String firstname, final float prix, final int cp, final String description, final int tel) {
        Produit p = new Produit(firstname, prix, cp, description, tel);
        em.persist(p);
        return p;
    }
}
