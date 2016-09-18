package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Company;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bogdan on 18.09.2016.
 */
@WebServlet(urlPatterns = {"/get/companies"})
public class LoadFromServ extends HttpServlet {

    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //id, String name, int companyPrice, int parentId
        Company company = new Company (1, "test",10,-1);
        Company company2 = new Company (2, "test2",20,1);
        response.setContentType("application/json");

        JSONObject object = new JSONObject();
        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        try {
            object1.append("id",company.getId());
            object1.append("name",company.getName());
            object1.append("companyPrice",company.getCompanyPrice());
            object1.append("parentId",company.getParentId());
            object2.append("id",company2.getId());
            object2.append("name",company2.getName());
            object2.append("companyPrice",company2.getCompanyPrice());
            object2.append("parentId",company2.getParentId());
            object.put("company",object2);
            object.put("company2",object1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(object.toString());
        out.flush();

    }

}
