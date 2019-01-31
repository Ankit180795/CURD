/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ankit
 */
public class done extends HttpServlet {

    

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
               response.setContentType("text/html");
               PrintWriter out = response.getWriter();
             
          String i = request.getParameter("id");
          String n = request.getParameter("name");
          String phone = request.getParameter("mobile-num");
          String eid = request.getParameter("email-id");
          String dob = request.getParameter("bday"); 
          String gen = request.getParameter("gen"); 
          String add = request.getParameter("address"); 
        
          String s=request.getParameter("btn");
          
          Class.forName("oracle.jdbc.driver.OracleDriver");
          Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","3101");
          PreparedStatement ps;
        
             switch (s) {
                 case "submit":
                     String query = "INSERT INTO EMP VALUES(?,?,?,?,?,?,?)";
                     ps = con.prepareStatement(query);
                     ps.setString(1,i);
                     ps.setString(2,n);
                     ps.setString(3,phone);
                     ps.setString(4,eid);
                     ps.setString(5,dob);
                     ps.setString(6,gen);
                     ps.setString(7,add);
                     if(ps.executeUpdate() > 0){
                         out.println("<font size='6' color=blue>" + "Record has been inserted" + "</font>");
                     }
                     else
                     {
                         out.println("<font size='6' color=blue>" + "failed to insert the data" + "</font>");
                     }       break;
                 case "delete":
                     String query1 = "DELETE FROM EMP WHERE ID =?";
                     ps = con.prepareStatement(query1);
                     ps.setString(1,i);
                     int k = ps.executeUpdate();
                     if (k > 0){
                         out.println("<body><h3>deleted with id " + i + "</h3></body></html>");
                     }    break;
                 case "Update":
                     String query2 ="UPDATE EMP SET NAME='"+n+"',MOBILE_NUMBER='"+phone+"',EMAIL='"+eid+"',DATE_OF_BIRTH='"+dob+"',GENDER='"+gen+"',ADDRESS='"+add+"' WHERE ID="+i;
                     ps = con.prepareStatement(query2);
                     int j = ps.executeUpdate();
                     if (j>0){
                         out.println("update successfully");
                     }    break;
                 case "View_All":
                     {
                         ps = con.prepareStatement("SELECT * FROM EMP");
                         ResultSet rs1 = ps.executeQuery();
                         while (rs1.next()) {
                             out.println("<tr><td>" + rs1.getString(1) + "</td><td>" + rs1.getString(2) + "</td><td>" + rs1.getString(3) + "</td><td>" + rs1.getString(4) + "</td><td>" + rs1.getString(5) + "</td><td>" + rs1.getString(6) + "</td><td>" + rs1.getString(7) + "</td><tr>");
                         }break;
                     }
                 case "search":
                     {
                         ps = con.prepareStatement("SELECT * FROM EMP WHERE ID=?");
                         ps.setString(1,i);
                         out.println(i);
                         ResultSet rs1 = ps.executeQuery();
                         while (rs1.next()) {
                             out.println("<tr><td>" + rs1.getString(1) + "</td><td>" + rs1.getString(2) + "</td><td>" + rs1.getString(3) + "</td><td>" + rs1.getString(4) + "</td><td>" + rs1.getString(5) + "</td><td>" + rs1.getString(6) + "</td><td>" + rs1.getString(7) + "</td><td>");
                         }break;
                     }
                 default:
                     break;
             }
          }
         
         catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(done.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

 
}
