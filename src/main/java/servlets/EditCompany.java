package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Company;
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
import java.util.LinkedList;
import java.util.List;

@WebServlet(urlPatterns = {"/edit/company"})
public class EditCompany extends HttpServlet {
    private static final long serialVersionUID = 1L;

    List<Company> companies = new LinkedList<Company>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        ObjectMapper mapper = new ObjectMapper();
        Company company = mapper.readValue(json, Company.class);
        response.setContentType("application/json");

        try {
            JDBCStore jdbcStore = new JDBCStore();
            jdbcStore.edit(company);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(new JSONObject());
        out.flush();

    }
}
