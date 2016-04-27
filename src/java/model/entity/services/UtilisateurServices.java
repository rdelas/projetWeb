package model.entity.services;

import com.delas.common.tools.password.PasswordUtils;
import com.delas.common.tools.string.StringUtil;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entity.bean.Campus;
import model.entity.bean.Utilisateur;
import view.servlet.form.UserFormBean;

@Stateless
public class UtilisateurServices {

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé
    // à partir du contenu de persistence.xml

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private CampusServices campusService;

    public Utilisateur creeUtilisateur(final UserFormBean bean) { 
        Campus campus = campusService.findCampusById(bean.getCampusId());
        if(campus == null){
            return null;
        }
        return creeUtilisateur(bean, campus);
    }
    
    public Utilisateur creeUtilisateur(final UserFormBean bean, final Campus campus) {    
        return creeUtilisateur(bean.getNom(), bean.getPrenom(), bean.getMail(), bean.getPwd(), bean.getPhotoUrl(), bean.getTelephone(), campus);
    }

    public Utilisateur creeUtilisateur(final String nom, final String prenom, final String mail, final String password, final String photoUrl, final String telephone, final Campus campus) {
        Utilisateur u = new Utilisateur(mail, nom, prenom, password, photoUrl, telephone, campus);
        em.persist(u);
        em.flush();
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
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize);

        return q.getResultList();
    }
    
    public Utilisateur updateUser(final UserFormBean bean){        
        Campus campus = campusService.findCampusById(bean.getCampusId());
        if(campus == null){
            return null;
        }
        
        return updateUser(bean.getMail(), bean.getNom(), bean.getPrenom(), bean.getPwd(), bean.getPhotoUrl(), bean.getTelephone(), campus);
    }

    public Utilisateur updateUser(final String mail, final String nom, final String prenom, final String password, final String photoUrl, final String telephone, final Campus campus) {

        Utilisateur u = em.find(Utilisateur.class, mail);
        
        if(u == null){
            return null;
        }
        
        u.setFirstname(prenom);
        u.setLastname(nom);
        u.setCampus(campus);
        u.setPassword(password);
        u.setPhotoUrl(photoUrl);
        u.setTelephones(telephone);
        
        return u;
    }

    public Utilisateur getUserByMail(final String adresseMail) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u WHERE u.adresseMail=:adresseMail");
        q.setParameter("adresseMail", adresseMail);

        Object o = null;
        try {
            o = q.getSingleResult();
        } catch (Exception e) {
            //System.err.println("No Result for adresse mail : " + adresseMail);
        }

        return (o != null) ? (Utilisateur) o : null;
    }

    public boolean checkUserPwd(final Utilisateur u, final String password) {
        if (u == null) {
            return false;
        }

        byte[] salt = u.getSalt();
        String pwdToTest = null;
        try {
            pwdToTest = PasswordUtils.encryptStringWithSalt(password, salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilisateurServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (StringUtil.isEmpty(pwdToTest)) {
            return false;
        }

        String pwd = u.getPassword();

        return pwd.equals(pwdToTest);
    }
}
