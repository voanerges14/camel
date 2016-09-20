package servlets;

import models.Company;
import org.json.JSONException;
import org.json.JSONObject;
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
import java.util.Collection;

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
            JSONObject jsonObject = new JSONObject(json);
            recursDelet(jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.flush();
    }

    private static void recursDelet(int id){
        try {
            JDBCStore jdbcStore = new JDBCStore();
            jdbcStore.deleteById(id);
            Collection<Company> compList = jdbcStore.companiesByParentId(id);
            for(Company c : compList) {
                recursDelet(c.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
