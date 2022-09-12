package com.commercehub.etl.core.entitybuilder.order.shopee;

import com.commercehub.etl.core.entity.order.OrderShopee;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class OrderShopeeBuilder {
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
    private OrderShopee.RecipientAddress recipientAddress;
    private BigDecimal actualShippingFee;
    private boolean goodsToDeclare;
    private String sellerNote;
    private Instant sellerNoteUpdateTime;
    private List<OrderShopee.Item> items;
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
    private OrderShopee.Invoice invoice;
    private String checkoutShippingCarrier;
    private BigDecimal reverseShippingFee;
    private OrderShopee.Escrow escrow;

    public OrderShopeeBuilder setOrderSn(String orderSn) {
        this.orderSn = orderSn;
        return this;
    }

    public OrderShopeeBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public OrderShopeeBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public OrderShopeeBuilder setCod(boolean cod) {
        this.cod = cod;
        return this;
    }

    public OrderShopeeBuilder setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderShopeeBuilder setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderShopeeBuilder setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
        return this;
    }

    public OrderShopeeBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderShopeeBuilder setEstimatedShippingFee(BigDecimal estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
        return this;
    }

    public OrderShopeeBuilder setMessageToSeller(String messageToSeller) {
        this.messageToSeller = messageToSeller;
        return this;
    }

    public OrderShopeeBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public OrderShopeeBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public OrderShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public OrderShopeeBuilder setDaysToShip(int daysToShip) {
        this.daysToShip = daysToShip;
        return this;
    }

    public OrderShopeeBuilder setShipByDate(Instant shipByDate) {
        this.shipByDate = shipByDate;
        return this;
    }

    public OrderShopeeBuilder setBuyerUserId(long buyerUserId) {
        this.buyerUserId = buyerUserId;
        return this;
    }

    public OrderShopeeBuilder setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public OrderShopeeBuilder setRecipientAddress(OrderShopee.RecipientAddress recipientAddress) {
        this.recipientAddress = recipientAddress;
        return this;
    }

    public OrderShopeeBuilder setActualShippingFee(BigDecimal actualShippingFee) {
        this.actualShippingFee = actualShippingFee;
        return this;
    }

    public OrderShopeeBuilder setGoodsToDeclare(boolean goodsToDeclare) {
        this.goodsToDeclare = goodsToDeclare;
        return this;
    }

    public OrderShopeeBuilder setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote;
        return this;
    }

    public OrderShopeeBuilder setSellerNoteUpdateTime(Instant sellerNoteUpdateTime) {
        this.sellerNoteUpdateTime = sellerNoteUpdateTime;
        return this;
    }

    public OrderShopeeBuilder setItems(List<OrderShopee.Item> items) {
        this.items = items;
        return this;
    }

    public OrderShopeeBuilder setPayTime(Instant payTime) {
        this.payTime = payTime;
        return this;
    }

    public OrderShopeeBuilder setDropshipper(String dropshipper) {
        this.dropshipper = dropshipper;
        return this;
    }

    public OrderShopeeBuilder setDropshipperPhone(String dropshipperPhone) {
        this.dropshipperPhone = dropshipperPhone;
        return this;
    }

    public OrderShopeeBuilder setSplitUp(boolean splitUp) {
        this.splitUp = splitUp;
        return this;
    }

    public OrderShopeeBuilder setBuyerCancelReason(String buyerCancelReason) {
        this.buyerCancelReason = buyerCancelReason;
        return this;
    }

    public OrderShopeeBuilder setCancelBy(String cancelBy) {
        this.cancelBy = cancelBy;
        return this;
    }

    public OrderShopeeBuilder setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
        return this;
    }

    public OrderShopeeBuilder setActualShippingFeeConfirmed(boolean actualShippingFeeConfirmed) {
        this.actualShippingFeeConfirmed = actualShippingFeeConfirmed;
        return this;
    }

    public OrderShopeeBuilder setFulfillmentFlag(String fulfillmentFlag) {
        this.fulfillmentFlag = fulfillmentFlag;
        return this;
    }

    public OrderShopeeBuilder setPickupDoneTime(Instant pickupDoneTime) {
        this.pickupDoneTime = pickupDoneTime;
        return this;
    }

    public OrderShopeeBuilder setInvoice(OrderShopee.Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public OrderShopeeBuilder setCheckoutShippingCarrier(String checkoutShippingCarrier) {
        this.checkoutShippingCarrier = checkoutShippingCarrier;
        return this;
    }

    public OrderShopeeBuilder setReverseShippingFee(BigDecimal reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
        return this;
    }

    public OrderShopeeBuilder setEscrow(OrderShopee.Escrow escrow) {
        this.escrow = escrow;
        return this;
    }

    public OrderShopee createOrderShopee() {
        return new OrderShopee(orderSn, region, currency, cod, totalAmount, orderStatus, shippingCarrier, paymentMethod, estimatedShippingFee, messageToSeller, createTime, updateTime, extractTime, daysToShip, shipByDate, buyerUserId, buyerUsername, recipientAddress, actualShippingFee, goodsToDeclare, sellerNote, sellerNoteUpdateTime, items, payTime, dropshipper, dropshipperPhone, splitUp, buyerCancelReason, cancelBy, cancelReason, actualShippingFeeConfirmed, fulfillmentFlag, pickupDoneTime, invoice, checkoutShippingCarrier, reverseShippingFee, escrow);
    }
}