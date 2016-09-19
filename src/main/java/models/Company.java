package models;

/**
 * Created by Pavlo on 14-09,Sep-16.
 */
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

//    @Override
//    public String toString(){
//        return "{\"id\":" + this.id + ",\"name\":\"" + this.name + "\",\"companyPrice\":"
//                + this.companyPrice + ",\"parentId\":" + this.parentId + "}";
//    }
//        @Override
//        public String toString() {
//            return "Article [title=" + name + ", url=" + name + ", categories="
//                    + name + ", tags=" + name + "]";
//        }
}
