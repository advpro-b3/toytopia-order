package id.ac.ui.cs.advprog.toytopiaorder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private int discount;
    private String availability;
}