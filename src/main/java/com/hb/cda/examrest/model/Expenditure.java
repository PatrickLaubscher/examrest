package com.hb.cda.examrest.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expenditure {

    @Id
    @UuidGenerator
    private String id;
    private String description;
    private double amont;
    
    @ManyToOne
    private Contributor contributor;

    @ManyToOne
    private Group group;



    public Expenditure() {
    }

    public Expenditure(String id, String description, double amont, Contributor contributor, Group group) {
        this.id = id;
        this.description = description;
        this.amont = amont;
        this.contributor = contributor;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmont() {
        return amont;
    }

    public void setAmont(double amont) {
        this.amont = amont;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        long temp;
        temp = Double.doubleToLongBits(amont);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((contributor == null) ? 0 : contributor.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Expenditure other = (Expenditure) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (Double.doubleToLongBits(amont) != Double.doubleToLongBits(other.amont))
            return false;
        if (contributor == null) {
            if (other.contributor != null)
                return false;
        } else if (!contributor.equals(other.contributor))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        return true;
    }

    
    
}
