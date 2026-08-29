/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brightfutureapplication;

/**
 *
 * @author Max
 */
public class ReportData{
    
    private String productCode;
    private String productName;
    private double warrantyMonths;//a double coz of the possiblity of it being 6 months, which in years is 0.5 years
    private int category;
    /*for category, i noticed that we only using it for printing in search and reports
    so i decided to keep it as an integer and pass it to a method that will return a string of corresponding values */ 
    private double price;//price is a double because it can have decimal values
    private int stockLevels;// int coz the stock will always be a whole number, you can't have 0.5 of a product in stock
    private String supplier;

    public ReportData(String productCode,String productName,double warrantyMonths,
                     int categoryNumber,double productPrice,int stockLevels,String supplier){
        this.productCode=productCode;
        this.productName=productName;
        this.warrantyMonths=warrantyMonths;
        this.category=categoryNumber;
        this.price=productPrice;
        this.stockLevels=stockLevels;
        this.supplier=supplier;     
    }
    
    //getters
    public String getProductCode(){
        return this.productCode;
    }
    public String getProductName(){
        return this.productName;
    }
    public double getWarranty(){
        return this.warrantyMonths;
    }
    public int getCategory(){
        return this.category;
    }
    public double getPrice(){
        return this.price;
    }
    public int getStockLevels(){   
        return this.stockLevels;
    }
    public String getSupplier(){
        return this.supplier;
    }
    
    //setters
    public void setProductCode(String code){
        this.productCode=code;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public void setWarranty(double warrantyMonths){
        this.warrantyMonths=warrantyMonths;
    }
    public void setCategory(int categoryNumber){
        this.category=categoryNumber;
    }
    public void setPrice(double productPrice){
        this.price=productPrice;
    }
    public void setStockLevels(int stockQuantity){
        this.stockLevels=stockQuantity;
    }
    public void setSupplier(String supplier){
        this.supplier=supplier;
    }
   
}