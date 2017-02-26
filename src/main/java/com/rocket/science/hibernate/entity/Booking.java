package com.rocket.science.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pawan.gupta on 26/02/17.
 */
@Entity
@XmlRootElement
@Table(name="BOOKING")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Booking {

    private int id;
    private String cust_id;
    private String driver_id;
    private String cab_id;

    public Booking() {
    }

    public Booking(int id, String cust_id, String driver_id, String cab_id) {
        this.id = id;
        this.cust_id = cust_id;
        this.driver_id = driver_id;
        this.cab_id = cab_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CUST_ID")
    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    @Column(name = "DRIVER_ID", unique = true)
    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    @Column(name = "CAB_ID", unique = true)
    public String getCab_id() {
        return cab_id;
    }

    public void setCab_id(String cab_id) {
        this.driver_id = cab_id;
    }

}

