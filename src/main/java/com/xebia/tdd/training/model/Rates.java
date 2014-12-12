package com.xebia.tdd.training.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * <strong>Rates</strong> is the model/entity class that defines the Weekday and
 * Weekend Rates for the {@link Hotel}.
 * </p>
 * 
 * @author Anand Swarup V
 */
public class Rates implements Serializable {

    private static final long serialVersionUID = -4489715416554183362L;
    private final BigDecimal weekdayRates;
    private final BigDecimal weekdayRatesForRewardsMembers;
    private final BigDecimal weekendRates;
    private final BigDecimal weekendRatesForRewardsMembers;

    public Rates(BigDecimal weekdayRates, BigDecimal weekdayRatesForRewardsMembers, BigDecimal weekendRates,
            BigDecimal weekendRatesForRewardsMembers) {
        this.weekdayRates = weekdayRates;
        this.weekdayRatesForRewardsMembers = weekdayRatesForRewardsMembers;
        this.weekendRates = weekendRates;
        this.weekendRatesForRewardsMembers = weekendRatesForRewardsMembers;
    }

    /**
     * @return the weekdayRates
     */
    public BigDecimal getWeekdayRates() {
        return weekdayRates;
    }

    /**
     * @return the weekendRates
     */
    public BigDecimal getWeekendRates() {
        return weekendRates;
    }

    /**
     * @return the weekdayRatesForRewardsMembers
     */
    public BigDecimal getWeekdayRatesForRewardsMembers() {
        return weekdayRatesForRewardsMembers;
    }

    /**
     * @return the weekendRatesForRewardsMembers
     */
    public BigDecimal getWeekendRatesForRewardsMembers() {
        return weekendRatesForRewardsMembers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Rates [weekdayRates=" + weekdayRates + ", weekdayRatesForRewardsMembers=" + weekdayRatesForRewardsMembers + ", weekendRates="
                + weekendRates + ", weekendRatesForRewardsMembers=" + weekendRatesForRewardsMembers + "]";
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
        result = prime * result + ((weekdayRates == null) ? 0 : weekdayRates.hashCode());
        result = prime * result + ((weekdayRatesForRewardsMembers == null) ? 0 : weekdayRatesForRewardsMembers.hashCode());
        result = prime * result + ((weekendRates == null) ? 0 : weekendRates.hashCode());
        result = prime * result + ((weekendRatesForRewardsMembers == null) ? 0 : weekendRatesForRewardsMembers.hashCode());
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
        Rates other = (Rates) obj;
        if (weekdayRates == null) {
            if (other.weekdayRates != null)
                return false;
        } else if (!weekdayRates.equals(other.weekdayRates))
            return false;
        if (weekdayRatesForRewardsMembers == null) {
            if (other.weekdayRatesForRewardsMembers != null)
                return false;
        } else if (!weekdayRatesForRewardsMembers.equals(other.weekdayRatesForRewardsMembers))
            return false;
        if (weekendRates == null) {
            if (other.weekendRates != null)
                return false;
        } else if (!weekendRates.equals(other.weekendRates))
            return false;
        if (weekendRatesForRewardsMembers == null) {
            if (other.weekendRatesForRewardsMembers != null)
                return false;
        } else if (!weekendRatesForRewardsMembers.equals(other.weekendRatesForRewardsMembers))
            return false;
        return true;
    }

}
