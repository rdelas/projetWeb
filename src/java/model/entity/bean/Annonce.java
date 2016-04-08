/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.bean;

import com.delas.common.tools.object.ClassUtil;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Delas
 */
@Entity
public class Annonce implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String titre;
    
    @Lob
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CateAnnonce categorie;
    
    private String photoUrl;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)    
    private Date dateDepot;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date dateFin;
    
    @Column(nullable = false)
    private Double prix;
    
    @ManyToOne(cascade = {CascadeType.ALL}) 
    private Utilisateur utilisateur;
    
    

    public Annonce() {
    }

    public Annonce(String titre, String description, CateAnnonce categorie, String photoUrl, Date dateDepot, Date dateFin, Double prix, Utilisateur utilisateur) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.photoUrl = photoUrl;
        this.dateDepot = dateDepot;
        this.dateFin = dateFin;
        this.prix = prix;
        this.utilisateur = utilisateur;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CateAnnonce getCategorie() {
        return categorie;
    }

    public void setCategorie(CateAnnonce categorie) {
        this.categorie = categorie;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
       
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return ClassUtil.toString(this);
//        return "produit.model.Produit[ id=" + id + " ]";
    }
    
}
