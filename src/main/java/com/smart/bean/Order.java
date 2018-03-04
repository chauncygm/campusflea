package com.smart.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_order")
public class Order {

    @Id
    @Column("order_id")
    @Comment("订单id")
    private int orderId;

    @Column("order_no")
    @Comment("订单编号")
    private int orderNo;

    @Column("good_id")
    @Comment("商品id")
    private int goodId;

    @Column("good_name")
    @Comment("商品名")
    private String goodName;

    @Column("price")
    @Comment("成交价格")
    private int price;

    @Column("seller")
    @Comment("买家id")
    private int seller;

    @Column("buyer")
    @Comment("买家id")
    private int buyer;

    @Column("type")
    @Comment("支付方式")
    private int type;

    @Column("is_confirm")
    @Comment("是否已确认？0否：1是")
    private int isConfirm;

    @Column("is_complete")
    @Comment("是否完成？0否：1是")
    private int isComplete;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNo=" + orderNo +
                ", goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", seller=" + seller +
                ", buyer=" + buyer +
                ", type=" + type +
                ", isConfirm=" + isConfirm +
                ", isComplete=" + isComplete +
                '}';
    }
}
