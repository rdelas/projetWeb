/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import java.io.IOException;
import java.util.HashMap;
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
import model.entity.bean.Annonce;
import model.entity.bean.CateAnnonce;
import model.entity.bean.Utilisateur;
import model.entity.services.AnnonceServices;
import view.servlet.form.AnnonceFormBean;
import view.servlet.form.Bean;

/**
 *
 * @author Clem
 */
@WebServlet(name = "ServletAnnonce", urlPatterns = {"/ServletAnnonce"})
public class ServletAnnonce extends HttpServlet {

    @EJB
    private AnnonceServices annonceServices;


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String forwardTo = "";
        String message = "";
        System.out.print("ACTION" + action);
        if (action != null) {
            switch (action) {
                case "creerUneAnnonce": {
                    String titre = request.getParameter("titre");
                    String description = request.getParameter("description");
                    String cate = request.getParameter("categorie");
                    CateAnnonce categorie = CateAnnonce.valueOf(cate.toUpperCase());
                    String photoUrl = request.getParameter("photoUrl");
                    Double prix = Double.parseDouble(request.getParameter("prix"));
                    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");   
                    Annonce a = annonceServices.creerAnnonce(titre, description, categorie, photoUrl, prix, utilisateur);
                    annonceServices.updateAnnonce(a.getId(), utilisateur.getId());
                    break;
                }
            }
        }
        
        RequestDispatcher dp = request.getRequestDispatcher("includes/liste_annonce.jsp");
        dp.include(request, response);
    }

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
        processRequest(request, response);
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

        AnnonceFormBean form = AnnonceFormBean.createFromRequestParameters(request.getParameterMap());

        if(!form.validate()){
            Map<String, String> errors = new HashMap<>();
            Set<ConstraintViolation<Bean>> validationErrors = form.getErrors();
            validationErrors.stream().forEach((error) -> {
                errors.put(error.getPropertyPath().toString(), error.getMessage());
            });
            request.setAttribute("errors", errors);
        } else {
        
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
            Annonce a = annonceServices.creerAnnonce(form, utilisateur);
        }
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
