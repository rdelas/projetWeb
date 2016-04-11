/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Classe abstraite permetant l'auto-hydration et la validation d'un formulaire
 * HTML sous la forme d'un objet Java.
 *
 *
 * Ce formulaire s'auto-rempli à partir de la map de parametre d'une requête
 * HTTP. Les attributs de la classe doivent correspondre aux champs du
 * formulaire HTML. La validation du formulaire s'effectue à l'aide des
 * annoations du package javax.validation.constraints
 *
 * @author rdelas
 */
public abstract class Bean implements Serializable {

    //Ensemble des erreurs du formulaire
    private Set<ConstraintViolation<Bean>> errors;

    /**
     * Hydrate le formulaire à l'aide de la map de paramètre. La clé correspond
     * au nom de l'attribut de la classe, et la value, à la valeur à attribuer.
     *
     * Ne prend en compte que les formulaire ayant les champs des type suivant 
     *  - String
     *  - Nombre
     *      -> Double
     *      -> Float
     *      -> Long
     *      -> Integer
     *  - Enum
     *  - Booleen
     * 
     * @param <T> Type de la classe à retourner
     * @param parameters La map des paramètres à traiter
     * @param clazz La classe de l'objet à hydrater
     * @return Le formulaire avec ses attributs affectés
     */
    protected static final <T> T hydrate(Map<String, String[]> parameters, Class<T> clazz) {
        T t = null;

        // Créer une nouvelle instance du formulaire
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Récuperation de la liste des champs du formulaire
        for (Field field : clazz.getDeclaredFields()) {

            //Recupération du nom du champ 
            String fieldName = field.getName();

            //Récuperation de la valeur du paramètre à l'aide du nom du champ à remplir
            String[] paramValue = parameters.get(fieldName);

            if (paramValue != null && paramValue.length > 0) {
                //Modification de l'accessibilité du champ, afin d'effectuer des traitement dessus
                field.setAccessible(true);
                Object value = null;
                try {
                    //Cas ou le paramètre à pour valeur un tableau
                    if (paramValue.length > 1) {
                        value = paramValue;
                    } else {
                        //Récuperation de la valeur unique
                        String pVal = paramValue[0];

                        //Récupération de la classe du champ à traiter
                        Class<?> fieldClazz = Class.forName(field.getType().getCanonicalName());

                        if (fieldClazz.isEnum()) { //Cas d'un enum
                            //On récupère la valeur de l'enum
                            value = Enum.valueOf((Class<Enum>) field.getType(), pVal);
                        } else if (fieldClazz.equals(Boolean.TYPE)) { //Cas d'un booleen
                            value = BooleanUtils.toBooleanObject(pVal);

                        } else if (Number.class.isAssignableFrom(fieldClazz) && NumberUtils.isParsable(pVal)) { //Cas d'un nombre
                            //On determine le type de nombre (double, float, long, int) et on l'affecte
                            if (Double.class.isAssignableFrom(fieldClazz)) {
                                value = Double.parseDouble(pVal);
                            } else if (Float.class.isAssignableFrom(fieldClazz)) {
                                value = Float.parseFloat(pVal);
                            } else if (Long.class.isAssignableFrom(fieldClazz)) {
                                value = Long.parseLong(pVal);
                            } else if (Integer.class.isAssignableFrom(fieldClazz)) {
                                value = Integer.parseInt(pVal);
                            }
                        } else { //Cas d'un String
                            value = pVal;
                        }
                    }
                    //Affectation du champ à sa valeur
                    field.set(t, value);
                } catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException ex) {
                    Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
                }
                field.setAccessible(false);
            }
        }

        //Retourne le formulaire hydraté
        return t;
    }

    /**
     * Valide le formulaire
     *
     * @return true si valide, sinon false
     */
    public final boolean validate() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        this.errors = validator.validate(this);

        return errors.isEmpty();
    }

    public Set<ConstraintViolation<Bean>> getErrors() {
        return errors;
    }

}
