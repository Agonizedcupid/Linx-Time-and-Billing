package com.aariyan.linxtimeandbilling.Model;

public class TimingModel {
    private String userName,customerName,startDate,billableTime,status,totalTime,workType,description;
    public TimingModel(){}

    public TimingModel(String userName, String customerName, String startDate, String billableTime, String status, String totalTime, String workType, String description) {
        this.userName = userName;
        this.customerName = customerName;
        this.startDate = startDate;
        this.billableTime = billableTime;
        this.status = status;
        this.totalTime = totalTime;
        this.workType = workType;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBillableTime() {
        return billableTime;
    }

    public void setBillableTime(String billableTime) {
        this.billableTime = billableTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
