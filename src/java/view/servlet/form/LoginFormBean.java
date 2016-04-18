/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet.form;

import java.util.Map;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import static view.servlet.form.Bean.hydrate;

/**
 *
 * @author rdelas
 */
public class LoginFormBean extends Bean {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

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

    public LoginFormBean() {
    }
    
    public static LoginFormBean createFromRequestParameters(final Map<String, String[]> parameters) {
        return hydrate(parameters, LoginFormBean.class);
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

    
    
}
