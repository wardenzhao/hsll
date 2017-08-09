package cn.thinkjoy.hsll.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by warden on 17/8/8.
 */
public class Order implements Serializable{

    private long id;
    private long buyMemberId;
    private long goodsId;
    private String orderNo;
    private long goodsSpecId;
    private int goodsNum;
    private long addressId;
    private String batchId;
    private String goodsCode;
    private String taxInfo;
    private String buyerMessage;
    private int status;
    private Date createdTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyMemberId() {
        return buyMemberId;
    }

    public void setBuyMemberId(long buyMemberId) {
        this.buyMemberId = buyMemberId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getGoodsSpecId() {
        return goodsSpecId;
    }

    public void setGoodsSpecId(long goodsSpecId) {
        this.goodsSpecId = goodsSpecId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(String taxInfo) {
        this.taxInfo = taxInfo;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
