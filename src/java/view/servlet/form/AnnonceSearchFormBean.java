/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.util.Map;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import model.entity.bean.CateAnnonce;
import model.entity.bean.TypeAnnonce;
import static view.servlet.form.Bean.hydrate;

/**
 *
 * @author rdelas
 */
public class AnnonceSearchFormBean extends Bean {

    @NotNull(message = "Le numero de page ne peut pas être null")
    @Min(value = 0, message = "Le numero de page ne peut pas être inférieur à 0")
    private Integer page;

    @NotNull(message = "Le nombre d'éléments par page ne peut pas être null")
    @Min(value = 0, message = "Le nombre d'éléments par page ne peut pas être inférieur à 0")
    private Integer pageSize;

    private String titre;
    
    @Min(value = 0, message = "Le numéro de campus ne peut pas être inférieur à 0")
    private Long campusId;

    @DecimalMin(value = "0.0", message = "Le prix min ne peut pas être inférieur à 0")
    private Double prixMin;

    @DecimalMin(value = "0.0", message = "Le prix max ne peut pas être inférieur à 0")
    private Double prixMax;

    private TypeAnnonce type;

    private CateAnnonce categorie;

    private Boolean photo;

    public AnnonceSearchFormBean() {
    }

    public static AnnonceSearchFormBean createFromRequestParameters(final Map<String, String[]> parameters) {
        return hydrate(parameters, AnnonceSearchFormBean.class);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getCampusId() {
        return campusId;
    }

    public void setCampusId(Long campusId) {
        this.campusId = campusId;
    }

    public Double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(Double prixMin) {
        this.prixMin = prixMin;
    }

    public Double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(Double prixMax) {
        this.prixMax = prixMax;
    }

    public TypeAnnonce getType() {
        return type;
    }

    public void setType(TypeAnnonce type) {
        this.type = type;
    }

    public CateAnnonce getCategorie() {
        return categorie;
    }

    public void setCategorie(CateAnnonce categorie) {
        this.categorie = categorie;
    }

    public Boolean getPhoto() {
        return photo;
    }

    public void setPhoto(Boolean photo) {
        this.photo = photo;
    }

}
