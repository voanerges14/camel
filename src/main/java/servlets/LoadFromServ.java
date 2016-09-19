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
import java.util.Collection;

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

//        ObjectMapper mapper = new ObjectMapper();
//        String res = "";
//        try {
//            JDBCStore jdbcStore = new JDBCStore();
//            JSONObject jsonObject = new JSONObject();
//            Collection<Company> companies = jdbcStore.companys();
//            for (Company c : companies) {
////                res = ("com" + ":" + mapper.writeValueAsString(c));
//                jsonObject.put("com", mapper.writeValueAsString(c));
//            }
//            response.setContentType("application/json");
//
//            PrintWriter out = response.getWriter();
//            out.print(jsonObject);
//            out.flush();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//        id, String name, int companyPrice, int parentId
        JDBCStore jdbcStore = null;
        try {
            jdbcStore = new JDBCStore();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject myOut = new JSONObject() ;
        Collection<Company> companies = jdbcStore.companys();
            for (Company c : companies) {
                try {
                    JSONObject object = new JSONObject();
                    object.append("parentId", c.getParentId());
                    object.append("id", c.getId());
                    object.append("name", c.getName());
                    object.append("companyPrice", c.getCompanyPrice());

                    myOut.put("company"+c.getId(), object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.print(myOut);
        out.flush();
    }

}
