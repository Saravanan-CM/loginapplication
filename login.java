

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username=request.getParameter("email");
        String password=request.getParameter("password");
        PrintWriter out = response.getWriter();
        try{
            /* TODO output your page here. You may use following sample code. */

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sampleapp","root","");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from login");
            while(rs.next())
            {
                String n=rs.getString(1);
                String p=rs.getString(2);
                if(username.equals(n) && password.equals(p))
                {
                    //RequestDispatcher rd= request.getRequestDispatcher("profile.jsp");
                    //rd.forward(request,response);
                    response.sendRedirect("https://in.linkedin.com/in/saravanan-c-m-b7aa51105");
                }
                else
                {
                    RequestDispatcher rd1= request.getRequestDispatcher("incorrect.html");
                    rd1.forward(request, response);
                }
            }
        }
        catch (ClassNotFoundException | SQLException | IOException | ServletException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
