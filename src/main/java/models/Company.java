package models;

public class Company  {

    private int id;
    private int parentId;
    private String name;
    private int companyPrice;

    public Company(){}

    public Company(int id, String name, int companyPrice, int parentId){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.companyPrice = companyPrice;
    }

    public void setId(int id) {this.id = id;}
    public int getId() {return id;}

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public int getParentId() {
        return parentId;
    }

    public void setName(String name) {this.name = name;}
    public String getName() {return this.name;}

    public void setCompanyPrice(int companyPrice) {this.companyPrice = companyPrice;}
    public int getCompanyPrice() {
        return companyPrice;
    }


}
