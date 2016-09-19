package store;

import models.Company;

import java.util.Collection;

public interface Storage {

    public Collection<Company> companys();

    public int add(final Company company);

    public void edit(final Company company);

    public void delete(final int id);

    public Company get(final int id);

    public void close();
}
