/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.delas.common.tools.string.StringUtil;
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
import model.entity.services.AnnonceServices;

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
        int page = 1, pageSize = 10;
        if (!StringUtil.isEmptyTrim(pageStr)) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                System.err.println("Page is not a number");
            }
        }

        if (!StringUtil.isEmptyTrim(pageSizeStr)) {
            try {
                pageSize = Integer.parseInt(pageSizeStr);
            } catch (NumberFormatException e) {
                System.err.println("Page Size is not a number");
            }
        }

//        String forwardTo = "";
//        String message = "";
        Collection<Annonce> liste = annonceServices.getAnnoncePaginated(page, pageSize);
        int size = annonceServices.getAllAnnonce().size();
        int nbPage = size / pageSize + ((size % pageSize > 0) ? 1 : 0);
        request.setAttribute("listeDesAnnonce", liste);
        request.setAttribute("nbPages", nbPage);
        request.setAttribute("currentPage", page);

        RequestDispatcher dp = request.getRequestDispatcher("includes/list_annonce.jsp");
        dp.forward(request, response);
//        response.setContentType(MediaType.TEXT_PLAIN);
//        response.setHeader("Cache-Control", "no-cache");
//        
//        String json = new Gson().toJson(liste);
//        response.getWriter().write(json);
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
