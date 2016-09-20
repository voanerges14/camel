package store;

import models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCStore implements Storage{
    private final Connection connection;
    String dbName = "postgres";
    String dbPassword = "root";

    public JDBCStore() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.mysql.jdbc.GoogleDriver");
//        this.connection =  DriverManager.getConnection("jdbc:mysql://companies.czrh6kl4gie2.us-west-2.rds.amazonaws.com/companies", "root", "rootroot");
//        this.connection =  DriverManager.getConnection("jdbc:mysql://localhost:3307/companies", "root", "root");
        this.connection =  DriverManager.getConnection("jdbc:google:mysql://104.198.56.204:3306/companies", "root", "root");

//        String url = null;
//        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//            // Load the class that provides the new "jdbc:google:mysql://" prefix.
//            Class.forName("com.mysql.jdbc.GoogleDriver");
//            url = "jdbc:google:mysql://eliftechcompanies:eliftechcompanies:us-central1:eliftechcompanies/companies?user=root";
//        } else {
//            // Local MySQL instance to use during development.
//            Class.forName("com.mysql.jdbc.Driver");
//            url = "jdbc:mysql://127.0.0.1:3306/companies?user=root";
//        }
//        this.connection =  DriverManager.getConnection(url);
    }

    public Collection<Company> companys() {
        final List<Company> companies = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from company")) {
            while (rs.next()) {
                companies.add(new Company(rs.getInt("cid"), rs.getString("name")
                        , rs.getInt("price"), rs.getInt("parentId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return companies;
    }

    public int add(Company company) {
        try (final PreparedStatement statement =
             this.connection.prepareStatement("INSERT INTO company (name, price, parentid) VALUES(?,?,?)"
                             , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getName());
            statement.setDouble(2, company.getCompanyPrice());
            statement.setInt(3, company.getParentId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not create new company");
    }

    public void edit(Company company) {
//        String sqlExpresion = "UPDATE company SET name = (?), price = (?), parentId = (?) WHERE ID = (?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE company SET name = ?, price = ? WHERE cid = ?")) {
            statement.setString(1, company.getName());
            statement.setDouble(2, company.getCompanyPrice());
            statement.setInt(3, company.getParentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {

        String sql = "WITH RECURSIVE\n" +
                " Rec (cid, name, price, parentId)\n" +
                " AS (\n" +
                "   SELECT cid, name, price, parentId FROM company\n" +
                "   UNION ALL\n" +
                "   SELECT Rec.cid, Rec.name, Rec.price, Rec.parentId\n" +
                "    FROM Rec, company\n" +
                "    WHERE Rec.cid = company.parentId\n" +
                "   )\n" +
                " DELETE FROM company WHERE (SELECT * FROM Rec \n" +
                " WHERE cid is (?));";

    }

    public Company get(int id) {

        try (final PreparedStatement statement = this.connection.prepareStatement("select * from company where cid=(?)")) {
            statement.setInt(1, id);
            try (final ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    return new Company(rs.getInt("cid"), rs.getString("name")
                            ,rs.getInt("companyPrice"), rs.getInt("parentId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(String.format("User is does not exists", id));
    }


    public void clean(){
        try
         {
             Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM company");
             statement.executeQuery("ALTER SEQUENCE company_cid_seq  RESTART WITH 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id){
//       String sqlExpresion = "delete from company where id = (?)";
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "delete from company where cid = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Company> companiesByParentId(int id) {
        final List<Company> companies = new ArrayList<>();
        try (final PreparedStatement statement = this.connection.prepareStatement(
                "select * from company where parentId = ?"))
 {
            statement.setInt(1, id);
     ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                companies.add(new Company(rs.getInt("cid"), rs.getString("name")
                        , rs.getInt("price"), rs.getInt("parentId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
