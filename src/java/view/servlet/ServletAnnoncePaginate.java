/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entity.bean.Annonce;
import model.entity.bean.CateAnnonce;
import model.entity.bean.TypeAnnonce;
import model.entity.services.AnnonceServices;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Clem
 */
@WebServlet(name = "ServletAnnoncePaginate", urlPatterns = {"/ServletAnnoncePaginate"})
public class ServletAnnoncePaginate extends HttpServlet {

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
        
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("pageSize");
        
        String titre = request.getParameter("titre");
        String campusIdStr = request.getParameter("campusId");
        String prixMinStr = request.getParameter("prixMin");
        String prixMaxStr = request.getParameter("prixMax");
        String typeStr = request.getParameter("type");
        String categorieStr = request.getParameter("categorie");
        String photoStr = request.getParameter("photo");
        
        int page = 1, pageSize = 10;
        Long campusId = null;
        Double prixMin = null, prixMax = null;
        TypeAnnonce type = null;
        CateAnnonce categorie = null;
        Boolean photo = null;
        
        if (StringUtils.isNotBlank(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                System.err.println("Page is not a number");
            }
        }
        if (StringUtils.isNotBlank(pageSizeStr)) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
            } catch (NumberFormatException e) {
                System.err.println("Page Size is not a number");
            }
        }
        if(StringUtils.isNotBlank(campusIdStr)){
            try {
                campusId = Long.parseLong(campusIdStr);
            } catch (NumberFormatException e) {
                System.err.println("Campus Id is not a number");
            }
        }
        if(StringUtils.isNotBlank(prixMinStr) && StringUtils.isNotBlank(prixMaxStr) ){
            try {
                prixMin = Double.parseDouble(prixMinStr);
                prixMax = Double.parseDouble(prixMaxStr);
            } catch (NumberFormatException e) {
                System.err.println("Price range is not a number");
            }
        }
        if(StringUtils.isNotBlank(typeStr)){
            type = TypeAnnonce.valueOf(typeStr);
        }
        if(StringUtils.isNotBlank(categorieStr)){
            categorie = CateAnnonce.valueOf(categorieStr);
        }
        if(StringUtils.isNotBlank(photoStr)){
            photo = Boolean.parseBoolean(photoStr);
        }

//        System.out.print("TEST");
        Collection<Annonce> liste = annonceServices.getAnnoncePaginated(page, pageSize, titre, campusId,
                prixMin, prixMax, type, categorie, photo);
        Long size = annonceServices.getAnnonceCountCriterized(titre, campusId,
                prixMin, prixMax, type, categorie, photo);
        System.out.println("Size result : "+size);
        Long nbPage = size / pageSize + ((size % pageSize > 0) ? 1 : 0);
        request.setAttribute("listeDesAnnonces", liste);
        request.setAttribute("nbPages", nbPage);
        request.setAttribute("currentPage", page);

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
