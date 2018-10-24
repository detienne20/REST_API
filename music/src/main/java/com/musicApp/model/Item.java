package com.musicApp.model;
import org.json.*;

public class Item {
    private int itemId,upc,cartId;
    private double msrp, salePrice; 
    private String name, shortDescription, brandName, color,size, gender;
  
    public Item(int itemId, String name, double msrp, double salePrice,int upc, String shortDescription, String brandName, String size, String color, String gender,int cartId) {
        this.itemId=itemId; 
        this.name=name; 
        this.msrp=msrp; 
        this.salePrice=salePrice;
        this.upc=upc;
        this.shortDescription=shortDescription;
        this.brandName=brandName;
        this.size=size;
        this.color=color;
        this.gender=gender; 
        this.cartId=cartId; 
    }

    public Item(int itemId, String name, double msrp, double salePrice,int upc, String shortDescription, String brandName, String size, String color, String gender) {
        this.itemId=itemId; 
        this.name=name; 
        this.msrp=msrp; 
        this.salePrice=salePrice;
        this.upc=upc;
        this.shortDescription=shortDescription;
        this.brandName=brandName;
        this.size=size;
        this.color=color;
        this.gender=gender; 
    }

    public Item(int itemId, String name, double msrp, double salePrice) {
        this.itemId=itemId; 
        this.name=name; 
        this.msrp=msrp; 
        this.salePrice=salePrice;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId=itemId; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public double getMSRP() {
        return msrp; 
    }

    public void setMSRP(double msrp) {
        this.msrp=msrp;
    }

   public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice=salePrice;
    }

    public int getUPC() {
        return upc;
    }

    public void setUPC(int upc) {
         this.upc=upc; 
    }

    public String getDescription() {
        return  shortDescription;
    }

    public void setDescription(String shortDescription) {
        this.shortDescription=shortDescription;
    }

    public String getBrandName() {
        return  brandName; 
    }

    public void setBrandName(String brandName) {
        this.brandName=brandName; 
    }

    public String getSize(){
        return size; 
    }

    public void setSize(String size) {
        this.size=size;  
    }

    public String getColor() {
        return  color; 
    }

    public void setColor(String color) {
        this.color=color; 
    }

    public String getGender() {
        return  gender; 
    }

    public void setGender(String gender) {
        this.gender=gender; 
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId=cartId; 
    }


    @Override
    public String toString() {
        return String.format(
                "Item[itemId='%d',name='%s',msrp='%f',salePrice='%f',upc='%d',shortDescription='%s',brandName='%s',size='%s',color='%s',gender='%s']",
                itemId,name,msrp,salePrice,upc,shortDescription,brandName,size,color,gender);
    }

}
