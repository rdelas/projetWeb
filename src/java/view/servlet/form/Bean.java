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

/**
 *
 * @author rdelas
 */
public abstract class Bean implements Serializable {

    private Set<ConstraintViolation<Bean>> errors;
    
    protected static final <T> T hydrate(Map<String, String[]> parameters, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            String[] value = parameters.get(fieldName);
            if (value != null) {
                field.setAccessible(true);
                try {
                    field.set(t, value[0]);
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
                }
                field.setAccessible(false);
            }
        }

        return t;
    }

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
