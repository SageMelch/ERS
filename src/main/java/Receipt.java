import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Receipt {
    @Id
    @Column
    private int id;
    @Column
    private String request;
    @Column
    private double amount;
    @Column
    private String status;
    @Column
    private String subDate;
    @Column
    private String resDate;
    public Receipt(){

    }
    public Receipt(int id, String request, double amount, String subDate){
        this.id=id;
        this.request=request;
        this.amount=amount;
        this.subDate=subDate;
    }
    public Receipt(int id, String request, double amount, String status, String resDate, String subDate){
        this.id=id;
        this.request=request;
        this.amount=amount;
        this.status=status;
        this.resDate=resDate;
        this.subDate=subDate;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getRequest(){
        return request;
    }
    public void setRequest(String request){
        this.request=request;
    }
    public Double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }
    public String getResDate(){
        return resDate;
    }
    public void setResDate(String resDate){
        this.resDate=resDate;
    }
    public String getSubDate(){
        return subDate;
    }
    public void setSubDate(String subDate){
        this.subDate=subDate;
    }
    @Override
    public String toString() {
        return "Receipt: " + this.id + ", " + this.request + ", " + this.amount + ", " + this.status + ", " + this.getResDate() + ", " + this.getSubDate();
    }
}