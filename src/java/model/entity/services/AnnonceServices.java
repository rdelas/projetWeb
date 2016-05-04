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
import model.entity.bean.Campus;
import model.entity.bean.CateAnnonce;
import model.entity.bean.TypeAnnonce;
import model.entity.bean.Utilisateur;
import org.apache.commons.lang3.StringUtils;
import view.servlet.form.AnnonceFormBean;
import view.servlet.form.AnnonceSearchFormBean;

/**
 *
 * @author Delas
 */
@Stateless
public class AnnonceServices {

    @PersistenceContext
    private EntityManager em;

    public Annonce creerAnnonce(AnnonceFormBean bean, Utilisateur u) {
        return creerAnnonce(bean.getTitre(), bean.getDescription(), bean.getType(), bean.getCategorie(), bean.getPhotoUrl(), bean.getPrix(), u);
    }

    public Annonce creerAnnonce(final String titre, final String description, final TypeAnnonce type, final CateAnnonce categorie, final String photoUrl, final Double prix, Utilisateur utilisateur) {
        Calendar c = Calendar.getInstance();
        Date dateDepot = c.getTime();
        c.add(Calendar.MONTH, 6);
        Date dateFin = c.getTime();

        Utilisateur u = em.find(utilisateur.getClass(), utilisateur.getId());

        Annonce a = new Annonce(titre, description, type, categorie, photoUrl, dateDepot, dateFin, prix, u);
        em.persist(a);
        //em.flush();
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

    public List<Annonce> getAnnoncesByUser(final Utilisateur user) {
        Utilisateur u = em.find(Utilisateur.class, user.getId());
        
        String query = "Select a from Annonce a where a.utilisateur=:user";

        Query q = em.createQuery(query);
        q.setParameter("user", u);

        return q.getResultList();
    }

    public List<Annonce> getAllAnnonce() {
        Query qAnnonce = em.createQuery("select a from Annonce a");
        return qAnnonce.getResultList();
    }

    public List<Annonce> getAnnoncePaginated(final AnnonceSearchFormBean form) {
        if (form == null) {
            return null;
        }

        return getAnnoncePaginated(form.getPage(), form.getPageSize(), form.getTitre(), form.getCampusId(),
                form.getPrixMin(), form.getPrixMax(), form.getType(), form.getCategorie(), form.getPhoto());
    }

    public List<Annonce> getAnnoncePaginated(final int page, final int pageSize,
            final String titre, final Long campusId, final Double prixMin, final Double prixMax,
            final TypeAnnonce type, final CateAnnonce cate, final Boolean photo) {

        String queryString = "select a from Annonce a";
        Query q = buildQueryWithWhereClause(queryString, titre, campusId, prixMin, prixMax, type, cate, photo)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize);

        return q.getResultList();
    }

    public List<Annonce> getAllAnnonceCriterized(final String titre, final Long campusId, final Double prixMin, final Double prixMax,
            final TypeAnnonce type, final CateAnnonce cate, final Boolean photo) {

        String queryString = "select a from Annonce a";
        Query q = buildQueryWithWhereClause(queryString, titre, campusId, prixMin, prixMax, type, cate, photo);

        return q.getResultList();
    }

    public Long getAnnonceCountCriterized(final AnnonceSearchFormBean form) {
        if (form == null) {
            return null;
        }

        return getAnnonceCountCriterized(form.getTitre(), form.getCampusId(), form.getPrixMin(),
                form.getPrixMax(), form.getType(), form.getCategorie(), form.getPhoto());
    }

    public Long getAnnonceCountCriterized(final String titre, final Long campusId, final Double prixMin, final Double prixMax,
            final TypeAnnonce type, final CateAnnonce cate, final Boolean photo) {

        String quetyString = "Select COUNT(a.id) from Annonce a";
        Query q = buildQueryWithWhereClause(quetyString, titre, campusId, prixMin, prixMax, type, cate, photo);

        return (Long) q.getSingleResult();
    }

    private Query buildQueryWithWhereClause(final String queryString, final String titre, final Long campusId, final Double prixMin, final Double prixMax,
            final TypeAnnonce type, final CateAnnonce cate, final Boolean photo) {

        // Exécution d'une requête équivalente à un select *
        String whereClause = "";

        if (StringUtils.isNotBlank(titre)) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "lower(a.titre) like :titre";
        }
        if (campusId != null) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "a.utilisateur.campus = :campus";
        }
        if (prixMin != null && prixMax != null) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "a.prix BETWEEN :prixMin AND :prixMax";
        }
        if (type != null) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "a.type = :type";
        }
        if (cate != null) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "a.categorie = :cate";
        }
        if (photo != null) {
            whereClause += ((!StringUtils.isNotBlank(whereClause)) ? " WHERE " : " AND ") + "a.photoUrl IS" + ((photo) ? " NOT " : " ") + "NULL ";
        }

        Query q = em.createQuery(queryString + whereClause);

        if (StringUtils.isNotBlank(titre)) {
            q.setParameter("titre", "%" + titre.trim().toLowerCase() + "%");
        }
        if (campusId != null) {
            Campus campus = em.find(Campus.class, campusId);
            q.setParameter("campus", campus);
        }
        if (prixMin != null && prixMax != null) {
            q.setParameter("prixMin", prixMin);
            q.setParameter("prixMax", prixMax);
        }
        if (type != null) {
            q.setParameter("type", type);
        }
        if (cate != null) {
            q.setParameter("cate", cate);
        }

        return q;
    }

    public Annonce getAnnonceEncryptedIds(final String hash) {

        String queryString = "select  a from Annonce a where a.encryptedId = :hash";

        return (Annonce)em.createQuery(queryString)
                .setParameter("hash", hash)
                .getSingleResult();
    }
}
