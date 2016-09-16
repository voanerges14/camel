package store;

import models.Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Pavlo on 14-09,Sep-16.
 */
public class JDBCStore implements Storage{
    private final Connection connection;

    public JDBCStore(Connection connection) throws SQLException {
        this.connection =  DriverManager.getConnection("jdbc:postgresql://hostname:port/companiesDB","root", "root");
    }

    public Collection<Company> companys() {
//        final List<Company> companies = new ArrayList<>();
//        try (final Statement statement = this.connection.createStatement();
//             final ResultSet rs = statement.executeQuery("select * from company")) {
//            while (rs.next()) {
//                companies.add(new Company(rs.getInt("cid"), rs.getString("name")
//                        , rs.getInt("income"), rs.getInt("parentId")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return companies;
    return null;
    }
/*не додає всіх параметрів */
//    public int add(Company company) {
//        try (final PreparedStatement statement =
//                     this.connection.prepareStatement("insert into company (name) values (?)"
//                             , Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, company.getName());
//            statement.executeUpdate();
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    return generatedKeys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new IllegalStateException("Could not create new company");
//    }

    public int add(Company company) {
//        try (final PreparedStatement statement =
//                     this.connection.prepareStatement("insert into company (name) values (?)"
//                             , Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, company.getName());
//            statement.setInt(1, company.getCompanyPrice());
//            statement.setInt(1, company.getParentId());
//            statement.executeUpdate();
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    return generatedKeys.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new IllegalStateException("Could not create new company");
        return 0;
    }

    public void edit(Company company) {

    }
/*хз хз*/
    public void delete(int id) {
        try (final PreparedStatement statement = this.connection.prepareStatement("DELETE FROM company WHERE cid=(?)")) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company get(int id) {

//        try (final PreparedStatement statement = this.connection.prepareStatement("select * from company where cid=(?)")) {
//            statement.setInt(1, id);
//            try (final ResultSet rs = statement.executeQuery()) {
//                while (rs.next()) {
//                    return new Company(rs.getInt("cid"), rs.getString("name")
//                            ,rs.getInt("companyPrice"), rs.getInt("parentId"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        throw new IllegalStateException(String.format("User is does not exists", id));
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
