/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import model.entity.bean.Campus;
import model.entity.bean.Utilisateur;
import model.entity.services.CampusServices;
import model.entity.services.UtilisateurServices;
import org.apache.commons.lang3.StringUtils;
import view.servlet.form.Bean;
import view.servlet.form.UserFormBean;

/**
 * Servlet gérant le formulaire d'ajout ou de modification d'utilisateur
 *
 * @author Delas
 */
@WebServlet(name = "ServletUserForm", urlPatterns = {"/ServletUserForm"})
public class ServletUserForm extends HttpServlet {

    @EJB
    private CampusServices campusServices;

    @EJB
    private UtilisateurServices userServices;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        //ajoute des attribut nécessaire à l'affichage du formulaire
        addData(request, action);

        //récupération de la JSP
        RequestDispatcher dp = request.getRequestDispatcher("includes/form_add.jsp");

        //Gérération de la jsp avec les attributs affecté pour l'inclusion dans une page mère
        dp.include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Récupération des paramètre de la requête et mise sous la forme d'un bean du formulaire
        UserFormBean bean = UserFormBean.createFromRequestParameters(request.getParameterMap());

        String forwardTo = "";

        //validation du formulaire
        if (!bean.validate()) {
            //Cas du formulaire invalide

            //Récupération de la liste des erreurs du formulaire
            Map<String, String> errors = new HashMap<>();
            Set<ConstraintViolation<Bean>> validationErrors = bean.getErrors();

            //Boucle foreach version Java 8 (recommandé par NetBeans)
            validationErrors.stream().forEach((error) -> {
                errors.put(error.getPropertyPath().toString(), error.getMessage());
            });

            addData(request, bean.getAction());
            
            //ajout des erreur pour le traitement par la JSP
            request.setAttribute("errors", errors);

            //remise à zéro du mot de passe et de sa confirmation
            bean.setPwd(null);
            bean.setConfirm(null);

            //Renvoie du formulaire pour re-remplir les champs
            request.setAttribute("form", bean);

            //Affection de la page a appeler par le RequestDispatcher
            forwardTo = "includes/form_add.jsp";

        } else {

            System.out.println("Form valid");
            boolean isModify = bean.getAction().equalsIgnoreCase("modify");
            Utilisateur u = null;
            if(isModify){
                u = userServices.updateUser(bean);
            } else {
                //Création de l'utilisateur à l'aide du formulaire
                u = userServices.creeUtilisateur(bean);
            }
            //Ajout de l'utilisateur en session pour la connexion après création de compte
            request.getSession().setAttribute("user", u);

            //Affection de la page a appeler par le RequestDispatcher
            //On fait suivre à la servlet de login qui s'occupera de faire le redirect
            forwardTo = "ServletLogin";
        }

        //Le RequestDispatcher va faire suivre à la page indiquée
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
    }

    /**
     * Ajoute à la requête les attributs nécessaires à la génération de la JSP
     *
     * @param request
     */
    private void addData(HttpServletRequest request, String action) {
        //Récupération de la liste des campus
        List<Campus> campusList = campusServices.getAllCampus();

        //Ajout de la liste des campus
        request.setAttribute("campusList", campusList);

        boolean isModify = StringUtils.isNotBlank(action) && action.equalsIgnoreCase("modify");
        
        String titre = (isModify) ? "Modifier le profil" : "S'enregistrer";
        String btnLabel = (isModify) ? "Modifier" : "Enregistrer";
        
        //ajout du titre du fieldset
        request.setAttribute("titre", titre);

        //ajout du label du bouton du formulaire
        request.setAttribute("btnLabel", btnLabel);
        
        //ajout du type d'action
        request.setAttribute("action", action);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
