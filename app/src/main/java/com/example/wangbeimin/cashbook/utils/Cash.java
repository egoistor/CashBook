package com.example.wangbeimin.cashbook.utils;

import org.litepal.crud.LitePalSupport;

public class Cash extends LitePalSupport{

    private String income;
    private String tag;
    private String type;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int year;
    private int month;
    private int day;
    private int hour;

    public Cash(){

    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDay(int day) {

        this.day = day;
    }

    public void setMonth(int month) {

        this.month = month;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public void setTag(String tag) {

        this.tag = tag;
    }

    public void setIncome(String income) {

        this.income = income;
    }

    public int getHour() {

        return hour;
    }

    public int getDay() {

        return day;
    }

    public int getMonth() {

        return month;
    }

    public int getYear() {

        return year;
    }

    public String getTag() {

        return tag;
    }

    public String getIncome() {

        return income;
    }
}
