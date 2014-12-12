package com.xebia.tdd.training.model;

import java.io.Serializable;

/**
 * <p>
 * <strong>Address</strong> is the model/entity class that represents an
 * Address.
 * </p>
 * 
 * @author Anand Swarup V
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 3652179005187137173L;
    private final String addressLine;
    private final String city;
    private final String state;
    private final String zip;
    private final String country;

    public Address(String addressLine, String city, String state, String zip, String country) {
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    /**
     * @return the addressLine
     */
    public String getAddressLine() {
        return addressLine;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + "]";
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
        result = prime * result + ((addressLine == null) ? 0 : addressLine.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
        Address other = (Address) obj;
        if (addressLine == null) {
            if (other.addressLine != null)
                return false;
        } else if (!addressLine.equals(other.addressLine))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (zip == null) {
            if (other.zip != null)
                return false;
        } else if (!zip.equals(other.zip))
            return false;
        return true;
    }

}
