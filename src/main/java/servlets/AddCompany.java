package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.LinkedList;
import java.util.List;


@WebServlet(urlPatterns = {"/add/company"})
public class AddCompany extends HttpServlet {


    private static final long serialVersionUID = 1L;

    // This will store all received articles
    List<Company> companies = new LinkedList<Company>();

    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        // 1. get received JSON data from request
        request.setCharacterEncoding("windows-1251");
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        ObjectMapper mapper = new ObjectMapper();
        Company company = mapper.readValue(json, Company.class);
        JSONObject object = new JSONObject();
        response.setContentType("application/json");

        try {
            JDBCStore jdbcStore = new JDBCStore();
            object.put("id" , jdbcStore.add(company));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(object.toString());
        out.flush();

    }


}
