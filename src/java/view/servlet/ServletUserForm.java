/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import com.delas.common.tools.object.ClassUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import model.entity.bean.Campus;
import model.entity.bean.Utilisateur;
import model.entity.services.CampusServices;
import model.entity.services.UtilisateurServices;
import view.servlet.form.UserFormBean;

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

        addData(request);

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

        UserFormBean bean = UserFormBean.hydrate(request.getParameterMap());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserFormBean>> validationErrors = validator.validate(bean);

        String forwardTo = "";
        if (!validationErrors.isEmpty()) {

            System.out.println("Form invalid");
            Map<String, String> errors = new HashMap<>();

            validationErrors.stream().forEach((error) -> {
                errors.put(error.getPropertyPath().toString(), error.getMessage());
            });
            System.out.println(Arrays.toString(errors.values().toArray()));
            addData(request);
            request.setAttribute("errors", errors);
            
            request.setAttribute("form", bean);
            forwardTo = "includes/form_add.jsp";

        } else {

            System.out.println("Form valid");
            Utilisateur u = userServices.creeUtilisateur(bean);
            System.out.println(ClassUtil.toString(u));
            request.getSession().setAttribute("user", u);
            forwardTo = "ServletLogin";
        }

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
    }

    
    private void addData(HttpServletRequest request){
        List<Campus> campusList = campusServices.gatAllCampus();

        request.setAttribute("campusList", campusList);
        request.setAttribute("titre", "S'enregister");
        request.setAttribute("btnLabel", "Enregister");
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
