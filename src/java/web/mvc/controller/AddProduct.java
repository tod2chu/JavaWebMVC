/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web.mvc.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.mvc.dao.ProductDao;
import web.mvc.model.Product;

/**
 *
 * @author Praew
 */
public class AddProduct extends HttpServlet {

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
        
        // ดึงค่าจาก View มาเก็บในตัวแปร
        request.setCharacterEncoding("utf-8"); // set ภาษา
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        // สร้าง model กำหนดค่าที่รับเข้ามาให้กับตัวแปร
        Product product = new Product(name, price);
        // สร้างออบเจ็กต์ dao เรียกใช้เมธอด add()
        ProductDao dao = new ProductDao();
        boolean result = dao.add(product);
        if(result) {
            RequestDispatcher rd = request.getRequestDispatcher("ShowProduct");
            String confirm_alert = "<script> alert('บันทึกข้อมูลสำเร็จ'); </script>";
            request.setAttribute("confirm_alert", confirm_alert);
            rd.forward(request, response);
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
