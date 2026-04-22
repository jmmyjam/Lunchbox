package com.lunchbox.businesses;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Business {

    private @Id @GeneratedValue Long id;
    private String name;
    private String type;
    private String address;
    private String phoneNumber;
    private String email;
    private String description;
    private int priceRange;
    private boolean isActive;
    private double rating;
    private int avgPrepTimeMinutes;

    public Business() {
    }

    public Business(String name, String type, String address, String phoneNumber, String email, String description,
            int priceRange, boolean isActive, double rating, int avgPrepTimeMinutes) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.priceRange = priceRange;
        this.isActive = isActive;
        this.rating = rating;
        this.avgPrepTimeMinutes = avgPrepTimeMinutes;
    }

    /* GETTERS */

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPriceRange() {
        return this.priceRange;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public double getRating() {
        return this.rating;
    }

    public int getAvgPrepTimeMinutes() {
        return this.avgPrepTimeMinutes;
    }

    /* SETTERS */

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAvgPrepTimeMinutes(int avgPrepTimeMinutes) {
        this.avgPrepTimeMinutes = avgPrepTimeMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Business))
            return false;
        Business business = (Business) o;
        return Objects.equals(this.id, business.id)
                && Objects.equals(this.name, business.name)
                && Objects.equals(this.type, business.type)
                && Objects.equals(this.address, business.address)
                && Objects.equals(this.phoneNumber, business.phoneNumber)
                && Objects.equals(this.email, business.email)
                && Objects.equals(this.description, business.description)
                && Objects.equals(this.priceRange, business.priceRange)
                && Objects.equals(this.isActive, business.isActive)
                && Double.compare(this.rating, business.rating) == 0
                && this.avgPrepTimeMinutes == business.avgPrepTimeMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.type, this.address, this.phoneNumber, this.email, this.description,
                this.priceRange, this.isActive, this.rating, this.avgPrepTimeMinutes);
    }

    @Override
    public String toString() {
        return "Business{" + "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", type='" + this.type + '\'' +
                ", address='" + this.address + '\'' +
                ", phoneNumber='" + this.phoneNumber + '\'' +
                ", email='" + this.email + '\'' +
                ", description='" + this.description + '\'' +
                ", priceRange='" + this.priceRange + '\'' +
                ", isActive='" + this.isActive + '\'' +
                ", rating='" + this.rating + '\'' +
                ", avgPrepTimeMinutes='" + this.avgPrepTimeMinutes + '\'' +
                '}';
    }
}
