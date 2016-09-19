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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/delete/companies"})
public class DeleteCompanies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();
        Company company = new Company();
        try {
            JDBCStore jdbcStore = new JDBCStore();
            jdbcStore.clean();
            jsonObject.put("id" , company.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
    }
}
