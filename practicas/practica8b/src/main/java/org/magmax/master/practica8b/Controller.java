/*
 * Copyright (C) 2012 miguel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.magmax.master.practica8b;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.magmax.master.practica8b.pojo.Issue;
import org.magmax.master.practica8b.pojo.Question;

/**
 *
 * @author miguel
 */
public class Controller extends HttpServlet {

    public static final String DEFAULT_CHARSET = "text/html;charset=UTF-8";
    private Domain domain;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(DEFAULT_CHARSET);
        domain = Configuration.getInstance().getDomain(this, request, response);
        loadNextPage(request, response);
    }

    public void loadNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer level = getSessionLevel(request);
            Integer issue = getSessionIssue(request);
            if (level == null && issue == null) {
                showIndex();
                return;
            }

            if (level == getContextLevel() && issue == getContextIssue()) {
                showResults(level, getSessionExam(request), getSessionAnswers(request));
                return;
            }

            if (true) {
                showPerformExam(issue, level);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    /**
     * This is only for tests purposes. Do not use.
     */
    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    private Integer getContextIssue() {
        String result = domain.getContextParameter("issue");
        return result == null? null : Integer.valueOf(result);
    }

    private Integer getContextLevel() {
        String result = domain.getContextParameter("level");
        return result == null? null : Integer.valueOf(result);
    }

    private Integer getSessionLevel(HttpServletRequest request) {
        String result = (String) request.getAttribute("level");
        return result == null ? null : Integer.valueOf(result);
    }

    private Integer getSessionIssue(HttpServletRequest request) {
        String result = (String) request.getAttribute("issue");
        return result == null ? null : Integer.valueOf(result);
    }

    private Question[] getSessionExam(ServletRequest request) {
        return (Question[]) request.getAttribute("exam");
    }

    private Integer[] getSessionAnswers(ServletRequest request) {
        Integer[] result = (Integer[]) request.getAttribute("answers");
        if (result == null) {
            return new Integer[0];
        }
        return result;
    }

    private void showIndex() throws Exception {
        Persistence persistence = getPersistence();
        Redirector redirector = domain.getRedirector();
        redirector.addAttribute("issue_list", getAllIssues(persistence));
        redirector.redirect(JspPage.CREATE);
    }

    private Issue[] getAllIssues(Persistence persistence) throws Exception {
        Issue[] result = persistence.getAllIssues();
        if (result == null) {
            throw new Exception("Problems found to find Issues");
        }
        return result;
    }

    private void showPerformExam(int issue_id, int level) throws Exception {
        Persistence persistence = getPersistence();
        Redirector redirector = domain.getRedirector();
        redirector.addAttribute("exam", getExam(persistence, issue_id, level));
        redirector.redirect(JspPage.EXAM);
    }

    private void showResults(Integer level, Question[] exam, Integer[] answers) throws Exception {
        MessageGenerator message = domain.getMessageGenerator();
        message.setLevel(level);
        message.setPunctuation(0);
        Redirector redirector = domain.getRedirector();
        redirector.addAttribute("message", message.getMessage());
        redirector.redirect(JspPage.RESULT);
    }

    private Persistence getPersistence() throws Exception, ClassNotFoundException, DriverNotDefinedException {
        Persistence persistence = domain.getPersistence();
        if (persistence == null) {
            throw new Exception("Could not connect to database");
        }
        return persistence;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
        return "This is a servlet for practice 8 of a Java2EE Master.";
    }// </editor-fold>

    private List<Question> getExam(Persistence persistence, int issue_id, int level) throws SQLException {
        return persistence.retrieveQuestions(issue_id, level);
    }
}
