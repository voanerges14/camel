package servlets;

import store.JDBCStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/delete/company"})
public class DeleteCompany extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        int id = 0;

        if(br != null){
            json = br.readLine();
        }

        try {
            JDBCStore jdbcStore = new JDBCStore();
            jdbcStore.delete(id);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


       /* try {
            JDBCStore jdbcStore = new JDBCStore();
//            jdbcStore.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //response.setContentType("application/json");
        //response.setStatus(response.SC_OK);
        PrintWriter out = response.getWriter();
       // out.print();
        out.flush();
    }

}
