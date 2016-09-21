package store;

import models.Company;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MassStore implements Storage {

    List<Company> companies = new ArrayList();
    @Override
    public Collection<Company> companys() {
        return companies;
    }

    @Override
    public int add(Company company) {
        company.setId(companies.size() + 1);
        companies.add(company);
        return companies.size();
    }

    @Override
    public void edit(Company company) {
        companies.set(company.getId(), company);
    }

    @Override
    public void delete(int id) {
        companies.remove(id);
        ArrayList<Company> compList = companiesByParentId(id);
        for(Company c : compList) {
            delete(c.getId());
        }
    }

    private ArrayList<Company> companiesByParentId(int id) {
        ArrayList<Company> compList = new ArrayList<>();
        for (Company c: companies) {
            if(c.getParentId() == id)
                compList.add(c);
        }
        return compList;
    }

    @Override
    public Company get(int id) {
        return companies.get(id);
    }

    @Override
    public void close() {}
}
