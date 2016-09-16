package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


@WebServlet(urlPatterns = {"/add/company"})
public class CompanyViewServlet extends HttpServlet {


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
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }

        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // 3. Convert received JSON to Article
        Company company = mapper.readValue(json, Company.class);

//
        // 4. Set response type to JSON
        response.setContentType("application/json");

        // 5. Add article to List<Article>
//       companies.add(company);

        // 6. Send List<Article> as JSON to client
//        mapper.writeValue(response.getOutputStream(), "{\"id\":\"5\"}");

//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"id\":\"5\"}");

    }


}
