/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.bean;

import com.delas.common.tools.object.ClassUtil;
import com.delas.common.tools.password.PasswordUtils;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Clem
 */
@Entity
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false)
    private String firstname;
    
    @Column(nullable = false)
    private String lastname;
    
    @Column(unique = true, nullable = false)
    private String adresseMail;
    
    @Column(nullable = false)
    private String password;
        
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false)    
    private Campus campus;

    @Column(nullable = false)
    private byte[] salt;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
        
    @OneToMany(mappedBy = "utilisateur")
    private List<Telephone> telephones;
    
    @OneToMany(mappedBy = "utilisateur")
    private List<Annonce> annonces;
    
    
    public Utilisateur() {
    }

    public Utilisateur(final String adresseMail, final String lastname, final String firstname, final String password, final Campus campus) {
        try {
            this.adresseMail = adresseMail;
            this.lastname = lastname;
            this.firstname = firstname;
            final Random r = new SecureRandom();
            this.salt = new byte[64];
            r.nextBytes(salt);
            this.password = PasswordUtils.encryptStringWithSalt(password, salt);
            this.dateCreation = Calendar.getInstance().getTime();
            this.campus = campus;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return ClassUtil.toString(this);
//        return "utilisateurs.modeles.Utilisateurs[ id=" + id + " ]";
    }
    
}
