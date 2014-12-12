package com.xebia.tdd.training.model;

import java.io.Serializable;

/**
 * <p>
 * <strong>Hotel</strong> is the model/entity class that represents a hotel.
 * </p>
 * 
 * @author Anand Swarup V
 */
public class Hotel implements Serializable {

    private static final long serialVersionUID = -371571627537440184L;
    private final Long id;
    private final String name;
    private final Address address;
    private final int rating;
    private final Rates rates;

    public Hotel(Long id, String name, Address address, int rating, Rates rates) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.rates = rates;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @return the rates
     */
    public Rates getRates() {
        return rates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Hotel [id=" + id + ", name=" + name + ", address=" + address + ", rating=" + rating + ", rates=" + rates + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rates == null) ? 0 : rates.hashCode());
        result = prime * result + rating;
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hotel other = (Hotel) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (rates == null) {
            if (other.rates != null)
                return false;
        } else if (!rates.equals(other.rates))
            return false;
        if (rating != other.rating)
            return false;
        return true;
    }

}
