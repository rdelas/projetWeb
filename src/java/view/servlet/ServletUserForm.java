/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.bean.Campus;
import model.entity.bean.Utilisateur;
import model.entity.services.CampusServices;
import model.entity.services.UtilisateurServices;

/**
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletUserForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletUserForm at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        List<Campus> campusList = campusServices.gatAllCampus();
        
        request.setAttribute("campusList", campusList);
        
        RequestDispatcher dp = request.getRequestDispatcher("includes/form_add.jsp");
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresseMail = request.getParameter("adresseMail");
        String pwd = request.getParameter("password");
        Long campusId = Long.parseLong(request.getParameter("campus"));
        Campus campus = campusServices.findCampusById(campusId);
        String photoUrl = request.getParameter("photoUrl");
        String telephone = request.getParameter("telephone");
        Utilisateur u = userServices.creeUtilisateur(nom, prenom, adresseMail, pwd, photoUrl, telephone, campus);
        
        request.getSession().setAttribute("user", u);
        RequestDispatcher dp = request.getRequestDispatcher("ServletUsers?action=");
        dp.forward(request, response);
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
