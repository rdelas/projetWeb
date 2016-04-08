/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import com.delas.common.tools.number.MyNumberUtils;
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
import org.apache.commons.lang3.math.NumberUtils;

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
            String[] paramValue = parameters.get(fieldName);
            if (paramValue != null && paramValue.length > 0) {
                field.setAccessible(true);
                Object value = null;
                try {
                    if(paramValue.length > 1){
                        value = paramValue;
                    } else{
                        String pVal = paramValue[0];
                        Class<?> fieldClazz = Class.forName(field.getType().getCanonicalName());
                        if(fieldClazz.isEnum()){
                            value = Enum.valueOf((Class<Enum>)field.getType(), pVal);
                        } else if(Number.class.isAssignableFrom(fieldClazz) && NumberUtils.isParsable(pVal)){
                            if(Double.class.isAssignableFrom(fieldClazz)){
                                value = Double.parseDouble(pVal);
                            } else if(Float.class.isAssignableFrom(fieldClazz)) {
                                value = Float.parseFloat(pVal);
                            } else if(Long.class.isAssignableFrom(fieldClazz)) {
                                value = Long.parseLong(pVal);
                            } else if(Integer.class.isAssignableFrom(fieldClazz)) {
                                value = Integer.parseInt(pVal);
                            }
                        } else{
                            value = pVal;
                        }
                    }
                    field.set(t, value);
                } catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException ex) {
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
