/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.validator;

import com.delas.common.tools.list.ListUtil;
import com.delas.common.tools.object.ClassUtil;
import com.delas.common.tools.string.StringUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.bean.CateAnnonce;

/**
 *
 * @author Delas
 */
@Stateless
public class AnnonceValidator {
    
    private Map<String, String> errors;
        
    public boolean validate(HttpServletRequest request, HttpServletResponse response){
        boolean flag = true;
        
        errors = new HashMap<>();
        String titre = request.getParameter("titre");
        if(StringUtil.isEmptyTrim(titre)){
            errors.put("titre", "Le titre ne peut être vide.");
            flag = false;
        }
        
        String description = request.getParameter("description");
        if(StringUtil.isEmptyTrim(description)){
            errors.put("description", "La description ne peut être vide.");
            flag = false;
        }
        
        String cate = request.getParameter("categorie");
        if(StringUtil.isEmptyTrim(cate)){
            errors.put("categorie", "La categorie ne peut être vide.");
            flag = false;
        } else { 
            try{
                CateAnnonce.valueOf(cate.toUpperCase());
            } catch(IllegalArgumentException e){
                errors.put("categorie", "La categorie donnée n'existe pas.");
                flag = false;
            }
        }
                
        String prixStr = request.getParameter("prix");
        if(StringUtil.isEmptyTrim(prixStr)){
            errors.put("prix", "Le prix ne peut être vide.");
            flag = false;
        } else {
            try{
                Double.parseDouble(prixStr);
            }catch(NumberFormatException  e){
                errors.put("prix", "Le prix renseigné n'est pas un nombre.");
                flag = false;
            }
        }
        
        String telephone = request.getParameter("tel");
        if(StringUtil.isEmptyTrim(telephone)){
            errors.put("tel", "Le telephone ne peut être vide.");
            flag = false;
        } else {
            Pattern pattern = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
            if(!telephone.matches(pattern.pattern())){
                errors.put("tel", "Le telephone renseigné n'est pas valide.");
            flag = false;
            }
        }
        
        return flag;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    
    
}
