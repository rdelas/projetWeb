package model.entity.services;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.bean.Utilisateur;

@Stateless
public class UtilisateurServices {
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé
    // à partir du contenu de persistence.xml
    @PersistenceContext
    private EntityManager em;

    public void creerUtilisateursDeTest() {
        creeUtilisateur("John", "Lennon", "jlennon", "pwd");
        creeUtilisateur("Paul", "Mac Cartney", "pmc", "pwd");
        creeUtilisateur("Ringo", "Starr", "rstarr", "pwd");
        creeUtilisateur("Georges", "Harisson", "georgesH", "pwd");
    }

    public Utilisateur creeUtilisateur(final String nom, final String prenom, final String mail, final String password) {
        Utilisateur u = new Utilisateur(mail, nom, prenom, password);
        em.persist(u);
        return u;
    }

    public Collection<Utilisateur> getAllUsers() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }
    
    public Collection<Utilisateur> getUsersPaginated(final int page, final int pageSize) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u")
                .setFirstResult((page-1) * pageSize)
                .setMaxResults(pageSize);

        return q.getResultList();
    }

    public void updateUser(final int id, final String nom, final String prenom, final String adresseMail){
        
        Utilisateur u = em.find(Utilisateur.class, id);
        
        u.setFirstname(prenom);
        u.setLastname(nom);
        u.setAdresseMail(adresseMail);
        
    }
    
    public Utilisateur getUserByMail(final String adresseMail) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u WHERE u.adresseMail=:adresseMail");
        q.setParameter("adresseMail", adresseMail);
        
        Object o = null;
        try{
           o = q.getSingleResult(); 
        } catch (Exception e){
            System.err.println("No Result for adresse mail : " + adresseMail);
        }finally{
            return (o!=null)?(Utilisateur)o:null;
        }
    }
}