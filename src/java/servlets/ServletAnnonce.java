/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.bean.Annonce;
import model.entity.bean.CateAnnonce;
import model.entity.bean.Utilisateur;
import model.entity.services.AnnonceServices;

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

        if (action != null) {
            switch (action) {
                case "ceerUneAnnonce": {
                    String titre = request.getParameter("titre");
                    String description = request.getParameter("description");
                    String cate = request.getParameter("categorie");
                    CateAnnonce categorie = CateAnnonce.valueOf(cate.toUpperCase());
                    String photoUrl = request.getParameter("photoUrl");
                    Double prix = Double.parseDouble(request.getParameter("prix"));
                    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
                    String telephone = request.getParameter("tel");
                    Annonce a = annonceServices.createAnnonce(titre, description, categorie, photoUrl, prix, utilisateur, telephone);
                    annonceServices.updateAnnonce(a.getId(), utilisateur.getId());
                    break;
                }
                case "listerLesAnnonces": {
                    Collection<Annonce> liste = annonceServices.getAllAnnonce();
                    request.setAttribute("listeDesAnnonces", liste);
                    forwardTo = "index.jsp?action=listerLesAnnonces";
                    message = "Liste des annonces";
                    break;
                }
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
