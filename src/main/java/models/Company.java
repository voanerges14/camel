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

    public Company(int id, String name, int companyPrice, int parentId){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.companyPrice = companyPrice;
    }
    public String getName() {
        return this.name;
    }
    public int getParentId() {
        return parentId;
    }

    public int getId() {
        return id;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

}
