package store;

import models.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Pavlo on 14-09,Sep-16.
 */
public class JDBCStore implements Storage{
    private final Connection connection;

    public JDBCStore(Connection connection) throws SQLException {
        this.connection =  DriverManager.getConnection("jdbc:postgresql://hostname:port/companiesDB","root", "root");
    }

    public Collection<Company> companys() {
        final List<Company> companies = new ArrayList<>();
        try (final Statement statement = this.connection.createStatement();
             final ResultSet rs = statement.executeQuery("select * from company")) {
            while (rs.next()) {
                companies.add(new Company(rs.getInt("cid"), rs.getString("name"), rs.getInt("income"), rs.getInt("parentId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public int add(Company company) {
        try (final PreparedStatement statement =
                     this.connection.prepareStatement("insert into company (name) values (?)"
                             , Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getName());
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

    }

    public void delete(int id) {

    }

    public Company get(int id) {
        return null;
    }

    public Company findById(int id) {
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
