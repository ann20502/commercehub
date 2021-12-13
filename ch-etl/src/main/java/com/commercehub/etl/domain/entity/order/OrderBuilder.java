package com.commercehub.etl.domain.entity.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderBuilder {
    private String orderSn;
    private String region;
    private String currency;
    private boolean cod;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String shippingCarrier;
    private String paymentMethod;
    private BigDecimal estimatedShippingFee;
    private String messageToSeller;
    private Instant createTime;
    private Instant updateTime;
    private Instant extractTime;
    private int daysToShip;
    private Instant shipByDate;
    private long buyerUserId;
    private String buyerUsername;
    private Order.RecipientAddress recipientAddress;
    private BigDecimal actualShippingFee;
    private boolean goodsToDeclare;
    private String sellerNote;
    private Instant sellerNoteUpdateTime;
    private List<Order.Item> items;
    private Instant payTime;
    private String dropshipper;
    private String dropshipperPhone;
    private boolean splitUp;
    private String buyerCancelReason;
    private String cancelBy;
    private String cancelReason;
    private boolean actualShippingFeeConfirmed;
    private String fulfillmentFlag;
    private Instant pickupDoneTime;
    private Order.Invoice invoice;
    private String checkoutShippingCarrier;
    private BigDecimal reverseShippingFee;
    private Order.Escrow escrow;

    public OrderBuilder setOrderSn(String orderSn) {
        this.orderSn = orderSn;
        return this;
    }

    public OrderBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public OrderBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public OrderBuilder setCod(boolean cod) {
        this.cod = cod;
        return this;
    }

    public OrderBuilder setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderBuilder setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderBuilder setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
        return this;
    }

    public OrderBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderBuilder setEstimatedShippingFee(BigDecimal estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
        return this;
    }

    public OrderBuilder setMessageToSeller(String messageToSeller) {
        this.messageToSeller = messageToSeller;
        return this;
    }

    public OrderBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public OrderBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public OrderBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public OrderBuilder setDaysToShip(int daysToShip) {
        this.daysToShip = daysToShip;
        return this;
    }

    public OrderBuilder setShipByDate(Instant shipByDate) {
        this.shipByDate = shipByDate;
        return this;
    }

    public OrderBuilder setBuyerUserId(long buyerUserId) {
        this.buyerUserId = buyerUserId;
        return this;
    }

    public OrderBuilder setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public OrderBuilder setRecipientAddress(Order.RecipientAddress recipientAddress) {
        this.recipientAddress = recipientAddress;
        return this;
    }

    public OrderBuilder setActualShippingFee(BigDecimal actualShippingFee) {
        this.actualShippingFee = actualShippingFee;
        return this;
    }

    public OrderBuilder setGoodsToDeclare(boolean goodsToDeclare) {
        this.goodsToDeclare = goodsToDeclare;
        return this;
    }

    public OrderBuilder setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote;
        return this;
    }

    public OrderBuilder setSellerNoteUpdateTime(Instant sellerNoteUpdateTime) {
        this.sellerNoteUpdateTime = sellerNoteUpdateTime;
        return this;
    }

    public OrderBuilder setItems(List<Order.Item> items) {
        this.items = items;
        return this;
    }

    public OrderBuilder setPayTime(Instant payTime) {
        this.payTime = payTime;
        return this;
    }

    public OrderBuilder setDropshipper(String dropshipper) {
        this.dropshipper = dropshipper;
        return this;
    }

    public OrderBuilder setDropshipperPhone(String dropshipperPhone) {
        this.dropshipperPhone = dropshipperPhone;
        return this;
    }

    public OrderBuilder setSplitUp(boolean splitUp) {
        this.splitUp = splitUp;
        return this;
    }

    public OrderBuilder setBuyerCancelReason(String buyerCancelReason) {
        this.buyerCancelReason = buyerCancelReason;
        return this;
    }

    public OrderBuilder setCancelBy(String cancelBy) {
        this.cancelBy = cancelBy;
        return this;
    }

    public OrderBuilder setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
        return this;
    }

    public OrderBuilder setActualShippingFeeConfirmed(boolean actualShippingFeeConfirmed) {
        this.actualShippingFeeConfirmed = actualShippingFeeConfirmed;
        return this;
    }

    public OrderBuilder setFulfillmentFlag(String fulfillmentFlag) {
        this.fulfillmentFlag = fulfillmentFlag;
        return this;
    }

    public OrderBuilder setPickupDoneTime(Instant pickupDoneTime) {
        this.pickupDoneTime = pickupDoneTime;
        return this;
    }

    public OrderBuilder setInvoice(Order.Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public OrderBuilder setCheckoutShippingCarrier(String checkoutShippingCarrier) {
        this.checkoutShippingCarrier = checkoutShippingCarrier;
        return this;
    }

    public OrderBuilder setReverseShippingFee(BigDecimal reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
        return this;
    }

    public OrderBuilder setEscrow(Order.Escrow escrow) {
        this.escrow = escrow;
        return this;
    }

    public Order createOrder() {
        return new Order(orderSn, region, currency, cod, totalAmount, orderStatus, shippingCarrier, paymentMethod, estimatedShippingFee, messageToSeller, createTime, updateTime, extractTime, daysToShip, shipByDate, buyerUserId, buyerUsername, recipientAddress, actualShippingFee, goodsToDeclare, sellerNote, sellerNoteUpdateTime, items, payTime, dropshipper, dropshipperPhone, splitUp, buyerCancelReason, cancelBy, cancelReason, actualShippingFeeConfirmed, fulfillmentFlag, pickupDoneTime, invoice, checkoutShippingCarrier, reverseShippingFee, escrow);
    }
}