package com.musicApp.model;
import java.util.Collection;
import org.json.*;

public class Cart {
    private int cartId; 
    private String customerUsername; 
    private Collection <Item> items;

    
    public Cart(int cartId, String customerUsername){
        this.cartId=cartId; 
        this.customerUsername=customerUsername; 
    }

    public Cart(String customerUsername){
        this.customerUsername=customerUsername; 
    }

    public Cart(int cartId){
        this.cartId=cartId;
    }

    public int getCartId(){
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUsername(){
        return customerUsername; 
    }

    public void setUsername(String customerUsername){
        this.customerUsername=customerUsername; 
    }

    public Collection <Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {

        return String.format(
                "Cart[cartId='%d',customerUsername='%s']",
                cartId, customerUsername);
    }

}
