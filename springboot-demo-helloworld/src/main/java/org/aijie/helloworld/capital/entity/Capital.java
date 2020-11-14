package org.aijie.helloworld.capital.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Capital)实体类
 *
 * @author makejava
 * @since 2020-11-13 22:07:53
 */
public class Capital implements Serializable {
    private static final long serialVersionUID = -87320772675420764L;

    private Integer id;

    private String idCard;

    private String contract;

    private String name;

    private String date;

    private String money;

    private String term;

    private String company;

    private String car;

    private String oper;

    private Date operDate;

    private String notes;

    private String address;

    private String code;

    private String dueDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}