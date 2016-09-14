package models;

/**
 * Created by Pavlo on 14-09,Sep-16.
 */
public class Company {
    private int id;
    private int parentId;
    private String name;
    private int companyPrice;
    private int allCompanysPrice;

    public Company(){

    }
    public Company(int id, int parentId, String name, int companyPrice){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.companyPrice = companyPrice;
    }
}
