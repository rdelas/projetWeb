/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.util.Map;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import model.entity.bean.CateAnnonce;
import model.entity.bean.TypeAnnonce;

/**
 *
 * @author rdelas
 */
public class AnnonceFormBean extends Bean {

    @NotNull(message = "Le titre ne peut pas être null")
    @Size(min = 1, max = 255, message = "Le titre ne peut pas vide")
    private String titre;

    @NotNull(message = "Le type de l'annonce ne peut être null")
    private TypeAnnonce type;

    @NotNull(message = "Le prix ne peut pas être null")
    @Min(value = 0, message = "Le prix ne peut pas être inférieur à 0")
    private Double prix;

    @NotNull(message = "La catégorie ne peut pas être null")
    private CateAnnonce categorie;

    @NotNull(message = "La description ne peut pas être null")
    @Size(min = 10, message = "La description doit faire au moins 10 caractères")
    private String description;

    @Size(max = 255, message = "Le nom du fichier est trop long")
    private String photoUrl;

    public AnnonceFormBean() {
    }

    public static AnnonceFormBean createFromRequestParameters(final Map<String, String[]> parameters) {
        return hydrate(parameters, AnnonceFormBean.class);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public TypeAnnonce getType() {
        return type;
    }

    public void setType(TypeAnnonce type) {
        this.type = type;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public CateAnnonce getCategorie() {
        return categorie;
    }

    public void setCategorie(CateAnnonce categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
