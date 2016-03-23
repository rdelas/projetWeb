/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.services.UtilisateurServices;
import model.entity.bean.Utilisateur;
import utils.StringUtil;

/**
 *
 * @author clem
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    @EJB
    private UtilisateurServices gestionnaireUtilisateurs;

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
        // Pratique pour décider de l'action à faire
        String action = request.getParameter("action");
        String forwardTo = "";
        String message = "";

        if (action != null) {
            switch (action) {
                case "connect" :
                case "disconnect" :
                    {
                        forwardTo = "ServletLogin";
                        break;
                    }
                case "listerLesUtilisateurs":
                    {
                        Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                        request.setAttribute("listeDesUsers", liste);
                        forwardTo = "index.jsp?action=listerLesUtilisateurs";
                        message = "Liste des utilisateurs";
                        break;
                    }
                case "creerUtilisateursDeTest":
                    {
                        gestionnaireUtilisateurs.creerUtilisateursDeTest();
                        Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                        request.setAttribute("listeDesUsers", liste);
                        forwardTo = "index.jsp?action=listerLesUtilisateurs";
                        message = "Liste des utilisateurs";
                        break;
                    }
                case "creerUnUtilisateur":
                    {
                        String nom = request.getParameter("nom");
                        String prenom = request.getParameter("prenom");
                        String adresseMail = request.getParameter("adresseMail");                        
                        String pwd = request.getParameter("password");                        
                        Utilisateurs u = gestionnaireUtilisateurs.creeUtilisateur(nom, prenom, adresseMail, pwd);
                        Collection<Utilisateurs> liste = gestionnaireUtilisateurs.getAllUsers();
                        request.setAttribute("listeDesUsers", liste);
                        forwardTo = "index.jsp?action=listerLesUtilisateurs";
                        message = "Ajout d'un utilisateur";
                        break;
                    }
                case "updateUtilisateur":
                    {
                        String nom = request.getParameter("nom");
                        String prenom = request.getParameter("prenom");
                        String adresseMail = request.getParameter("adresseMail");
                        Utilisateur u = gestionnaireUtilisateurs.getUserByMail(adresseMail);
                        gestionnaireUtilisateurs.updateUser(u.getId(), nom, prenom, adresseMail);
                        Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();
                        request.setAttribute("listeDesUsers", liste);
                        forwardTo = "index.jsp?action=listerLesUtilisateurs";
                        message = "Modification de l'utilisateur "+adresseMail;
                        break;
                    }
                default:
                    forwardTo = "index.jsp?action=todo";
                    message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";
                    break;
            }
        }

        forwardTo += (!StringUtil.isEmptyTrim(message))?"&message=" + message:"";
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
        // Après un forward, plus rien ne peut être exécuté après !
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
