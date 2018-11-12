package com.qs.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BillInfo {
    private String billId;

    private String billType;

    private String categoryId;

    private String createBy;

    private Date createTime;

    private String isReimburse;

    private String lastModifyBy;

    private Date lastModifyTime;

    private BigDecimal money;

    private Date recordDate;

    private Date recordEndDate;

    private Date recordStartDate;

    private String remark;

    private String status;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsReimburse() {
        return isReimburse;
    }

    public void setIsReimburse(String isReimburse) {
        this.isReimburse = isReimburse;
    }

    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getRecordEndDate() {
        return recordEndDate;
    }

    public void setRecordEndDate(Date recordEndDate) {
        this.recordEndDate = recordEndDate;
    }

    public Date getRecordStartDate() {
        return recordStartDate;
    }

    public void setRecordStartDate(Date recordStartDate) {
        this.recordStartDate = recordStartDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BillInfo{" +
                "billId='" + billId + '\'' +
                ", billType='" + billType + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", isReimburse='" + isReimburse + '\'' +
                ", lastModifyBy='" + lastModifyBy + '\'' +
                ", lastModifyTime=" + lastModifyTime +
                ", money=" + money +
                ", recordDate=" + recordDate +
                ", recordEndDate=" + recordEndDate +
                ", recordStartDate=" + recordStartDate +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}