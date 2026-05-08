package com.lunchbox.menu;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MenuItem {

    private @Id @GeneratedValue Long id;
    private Long businessId;
    private String name;
    private String description;
    private double price;
    private String category;

    public MenuItem() {}

    public MenuItem(Long businessId, String name, String description, double price, String category) {
        this.businessId = businessId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() { return this.id; }
    public Long getBusinessId() { return this.businessId; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public double getPrice() { return this.price; }
    public String getCategory() { return this.category; }

    public void setId(Long id) { this.id = id; }
    public void setBusinessId(Long businessId) { this.businessId = businessId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem item = (MenuItem) o;
        return Objects.equals(this.id, item.id)
                && Objects.equals(this.businessId, item.businessId)
                && Objects.equals(this.name, item.name)
                && Double.compare(this.price, item.price) == 0
                && Objects.equals(this.category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.businessId, this.name, this.price, this.category);
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id + ", businessId=" + businessId + ", name='" + name + "', price=" + price + ", category='" + category + "'}";
    }
}
