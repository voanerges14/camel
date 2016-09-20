package servlets;

import models.Company;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet(urlPatterns = {"/get/companies"})
public class LoadFromServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            JDBCStore jdbcStore = new JDBCStore();
            JSONArray jsonArray = new JSONArray();
            Collection<Company> companies = jdbcStore.companys();
        Collections.sort((List<Company>) companies, new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                int a = ((Company) o1).getParentId();
                int b = ((Company) o2).getParentId();
                if (a < b)
                    return -1;
                else if (a == b)
                    return 0;
                else
                    return 1;
            }

        });
            jsonArray.put(mapper.writeValueAsString(companies));
            response.setContentType("application/json");

            PrintWriter out = response.getWriter();
            out.print(jsonArray);
            out.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
