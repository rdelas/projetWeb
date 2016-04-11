/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.util.Map;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Formulaire d'ajout ou de modification d'un utilisateur.
 *
 * @see Bean
 *
 * @author rdelas
 */
public class UserFormBean extends Bean {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    private static final String PHONE_PATTERN = "0{1}[1-7]{1}[0-9]{8}";

    /**
     * Nom de l'utilisateur
     */
    @NotNull(message = "Le nom ne peut pas être null")
    @Size(min = 1, max = 255, message = "Le nom ne doit pas être vide")
    private String nom;

    /**
     * Prénom de l'utilisateur
     */
    @NotNull(message = "Le prénom ne peut pas être null")
    @Size(min = 1, max = 255, message = "Le prénom ne doit pas être vide")
    private String prenom;
    
    /**
     * Mail de l'utilisateur
     */
    @NotNull(message = "Le mail ne peut pas être null")
    @Size(min = 1, max = 255, message = "Le mail ne doit pas être vide")
    @Pattern(regexp = EMAIL_PATTERN, message = "Le mail saisi n'est pas valide")
    private String mail;

    /**
     * Mot de passe de l'utilisateur
     */
    @NotNull(message = "Le mot de passe ne peut pas être null")
    @Size(min = 6, max = 255, message = "Le mot de passe doit être d'une longueur minimale de 6 caractères")
    private String pwd;

    /**
     * Confirmation du mot de passe de l'utilisateur
     */
    @NotNull(message = "La confirmation de mot de passe ne peut pas être null")
    @Size(min = 6, max = 255, message = "La confirmation du mot de passe doit être d'une longueur minimale de 6 caractères")
    private String confirm;

    /**
     * URL de la photo de l'utilisateur
     */
    @Size(max = 255, message = "Le nom du fichier est trop long")
    private String photoUrl;

    /**
     * Téléphone de l'utilisateur
     */
    @Size(min = 10, max = 10, message = "Le numéro de téléphone doit comporter 10 chiffres")
    @Pattern(regexp = PHONE_PATTERN, message = "Le numéro de téléphone doit respecter le format suivant : 0[1-7][0-9]{8}")
    private String telephone;

    /**
     * Identifiant du campus de l'utilisateur
     */
    @NotNull(message = "Le campus ne peut pas être null")
    @Min(value = 0, message = "L'identifiant du campus doit être un nombre")
    private Long campusId;

    public UserFormBean() {
    }

    public static UserFormBean createFromRequestParameters(final Map<String, String[]> parameters) {
        return hydrate(parameters, UserFormBean.class);
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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

    public Long getCampusId() {
        return campusId;
    }

    public void setCampusId(Long campusId) {
        this.campusId = campusId;
    }

}
