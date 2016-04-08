/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.io.Serializable;
import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author rdelas
 */
public class UserFormBean implements Serializable {
        
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    
    private static final String PHONE_PATTERN = "0{1}[1-7]{1}[0-9]{8}";
    
    @NotNull
    @Size(min = 1, max = 255)
    private String nom;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String prenom;

    @NotNull
    @Pattern(regexp = EMAIL_PATTERN)
    private String mail;
    
    @NotNull
    @Size(min = 6, max = 255)
    private String pwd;
    
    @Size(max = 255)
    private String photoUrl;
    
    @Size(min = 10, max = 10)
    @Pattern(regexp = PHONE_PATTERN)
    private String telephone;
    
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String campusId;

    public UserFormBean() {
    }

    public UserFormBean(String nom, String prenom, String mail, String pwd, String photoUrl, String telephone, String campusId) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.pwd = pwd;
        this.photoUrl = photoUrl;
        this.telephone = telephone;
        this.campusId = campusId;
    }
    
    public static UserFormBean hydrate(final Map<String, String[]> parameters){
        UserFormBean bean = new UserFormBean();
        
        bean.setNom(parameters.get("nom")[0]);
        bean.setPrenom( parameters.get("prenom")[0]);
        bean.setMail(parameters.get("mail")[0]);
        bean.setPwd(parameters.get("pwd")[0]);
        bean.setCampusId(parameters.get("campusId")[0]);
        bean.setPhotoUrl(parameters.get("photoUrl")[0]);
        bean.setTelephone(parameters.get("telephone")[0]);
        
        return bean;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCampusId() {
        return campusId;
    }

    public Long getCampusIdAsLong(){
        return Long.parseLong(campusId);
    }
    
    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }
    
    
}
