package com.rocket.science.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sinraja on 2/24/17.
 */
@Entity
@XmlRootElement
@Table(name="USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    private int id;
    private String name;
    private String phoneNumber;

    public User() {
    }

    public User(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String agentName) {
        this.name = agentName;
    }

    @Column(name = "PHONE_NO" , unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}