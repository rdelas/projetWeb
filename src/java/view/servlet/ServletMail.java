/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import model.entity.bean.Annonce;
import model.entity.bean.Utilisateur;
import model.entity.services.AnnonceServices;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rdelas
 */
@WebServlet(name = "ServletMail", urlPatterns = {"/ServletMail"})
public class ServletMail extends HttpServlet {

    @EJB
    private AnnonceServices annonceServices;

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

        String id = request.getParameter("id");
        String url = "";
        if(StringUtils.isNotBlank(id)){
            Annonce a = annonceServices.getAnnonceEncryptedIds(id);
            request.setAttribute("annonce", a);
            url = "mail.jsp";
        } else {
            url = "index.jsp";
        }
        RequestDispatcher dp = request.getRequestDispatcher(url);
        dp.forward(request, response);
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

        String content = request.getParameter("content");
        String id = request.getParameter("id");
        Utilisateur u = (Utilisateur) request.getSession().getAttribute("user");
        Annonce a = annonceServices.getAnnonceEncryptedIds(id);

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            private final StringWriter sw = new StringWriter();

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(sw);
            }

            @Override
            public String toString() {
                return sw.toString();
            }
        };

        request.setAttribute("content", content);
        request.setAttribute("annonce", a);
        request.getRequestDispatcher("template/mail_template.jsp").include(request, responseWrapper);
        sendMail(responseWrapper.toString(), u.getAdresseMail(), a.getUtilisateur().getAdresseMail());
        
        response.sendRedirect("index.jsp");
    }

    private void sendMail(String content, String from, String to) {
        /*
         * Code récupéré depuis : 
         * http://www.tutorialspoint.com/java/java_sending_email.htm
         * http://stackoverflow.com/questions/5068827/how-do-i-send-html-email-via-java
         *
         */


        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Mail de test");

            // Now set the actual message
            message.setContent(content, "text/html; charset=utf-8");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
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
