package store;

import models.Company;

import java.util.Collection;

public interface Storage {

    public Collection<Company> values();

    public int add(final Company user);

    public void edit(final Company user);

    public void delete(final int id);

    public Company get(final int id);

    public Company findByLogin(final String login) ;

    public int generateId();

    public void close();
}
