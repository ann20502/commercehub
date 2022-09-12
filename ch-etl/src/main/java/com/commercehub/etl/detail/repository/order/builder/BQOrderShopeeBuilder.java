package com.commercehub.etl.detail.repository.order.builder;

import com.commercehub.etl.detail.repository.order.BQOrderShopee;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class BQOrderShopeeBuilder {
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
    private BQOrderShopee.RecipientAddress recipientAddress;
    private BigDecimal actualShippingFee;
    private boolean goodsToDeclare;
    private String sellerNote;
    private Instant sellerNoteUpdateTime;
    private List<BQOrderShopee.Item> items;
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
    private BQOrderShopee.Invoice invoice;
    private String checkoutShippingCarrier;
    private BigDecimal reverseShippingFee;
    private BQOrderShopee.Escrow escrow;

    public BQOrderShopeeBuilder setOrderSn(String orderSn) {
        this.orderSn = orderSn;
        return this;
    }

    public BQOrderShopeeBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public BQOrderShopeeBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BQOrderShopeeBuilder setCod(boolean cod) {
        this.cod = cod;
        return this;
    }

    public BQOrderShopeeBuilder setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public BQOrderShopeeBuilder setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public BQOrderShopeeBuilder setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
        return this;
    }

    public BQOrderShopeeBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public BQOrderShopeeBuilder setEstimatedShippingFee(BigDecimal estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
        return this;
    }

    public BQOrderShopeeBuilder setMessageToSeller(String messageToSeller) {
        this.messageToSeller = messageToSeller;
        return this;
    }

    public BQOrderShopeeBuilder setCreateTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public BQOrderShopeeBuilder setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public BQOrderShopeeBuilder setExtractTime(Instant extractTime) {
        this.extractTime = extractTime;
        return this;
    }

    public BQOrderShopeeBuilder setDaysToShip(int daysToShip) {
        this.daysToShip = daysToShip;
        return this;
    }

    public BQOrderShopeeBuilder setShipByDate(Instant shipByDate) {
        this.shipByDate = shipByDate;
        return this;
    }

    public BQOrderShopeeBuilder setBuyerUserId(long buyerUserId) {
        this.buyerUserId = buyerUserId;
        return this;
    }

    public BQOrderShopeeBuilder setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public BQOrderShopeeBuilder setRecipientAddress(BQOrderShopee.RecipientAddress recipientAddress) {
        this.recipientAddress = recipientAddress;
        return this;
    }

    public BQOrderShopeeBuilder setActualShippingFee(BigDecimal actualShippingFee) {
        this.actualShippingFee = actualShippingFee;
        return this;
    }

    public BQOrderShopeeBuilder setGoodsToDeclare(boolean goodsToDeclare) {
        this.goodsToDeclare = goodsToDeclare;
        return this;
    }

    public BQOrderShopeeBuilder setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote;
        return this;
    }

    public BQOrderShopeeBuilder setSellerNoteUpdateTime(Instant sellerNoteUpdateTime) {
        this.sellerNoteUpdateTime = sellerNoteUpdateTime;
        return this;
    }

    public BQOrderShopeeBuilder setItems(List<BQOrderShopee.Item> items) {
        this.items = items;
        return this;
    }

    public BQOrderShopeeBuilder setPayTime(Instant payTime) {
        this.payTime = payTime;
        return this;
    }

    public BQOrderShopeeBuilder setDropshipper(String dropshipper) {
        this.dropshipper = dropshipper;
        return this;
    }

    public BQOrderShopeeBuilder setDropshipperPhone(String dropshipperPhone) {
        this.dropshipperPhone = dropshipperPhone;
        return this;
    }

    public BQOrderShopeeBuilder setSplitUp(boolean splitUp) {
        this.splitUp = splitUp;
        return this;
    }

    public BQOrderShopeeBuilder setBuyerCancelReason(String buyerCancelReason) {
        this.buyerCancelReason = buyerCancelReason;
        return this;
    }

    public BQOrderShopeeBuilder setCancelBy(String cancelBy) {
        this.cancelBy = cancelBy;
        return this;
    }

    public BQOrderShopeeBuilder setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
        return this;
    }

    public BQOrderShopeeBuilder setActualShippingFeeConfirmed(boolean actualShippingFeeConfirmed) {
        this.actualShippingFeeConfirmed = actualShippingFeeConfirmed;
        return this;
    }

    public BQOrderShopeeBuilder setFulfillmentFlag(String fulfillmentFlag) {
        this.fulfillmentFlag = fulfillmentFlag;
        return this;
    }

    public BQOrderShopeeBuilder setPickupDoneTime(Instant pickupDoneTime) {
        this.pickupDoneTime = pickupDoneTime;
        return this;
    }

    public BQOrderShopeeBuilder setInvoice(BQOrderShopee.Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public BQOrderShopeeBuilder setCheckoutShippingCarrier(String checkoutShippingCarrier) {
        this.checkoutShippingCarrier = checkoutShippingCarrier;
        return this;
    }

    public BQOrderShopeeBuilder setReverseShippingFee(BigDecimal reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
        return this;
    }

    public BQOrderShopeeBuilder setEscrow(BQOrderShopee.Escrow escrow) {
        this.escrow = escrow;
        return this;
    }

    public BQOrderShopee createBQOrderShopee() {
        return new BQOrderShopee(orderSn, region, currency, cod, totalAmount, orderStatus, shippingCarrier, paymentMethod, estimatedShippingFee, messageToSeller, createTime, updateTime, extractTime, daysToShip, shipByDate, buyerUserId, buyerUsername, recipientAddress, actualShippingFee, goodsToDeclare, sellerNote, sellerNoteUpdateTime, items, payTime, dropshipper, dropshipperPhone, splitUp, buyerCancelReason, cancelBy, cancelReason, actualShippingFeeConfirmed, fulfillmentFlag, pickupDoneTime, invoice, checkoutShippingCarrier, reverseShippingFee, escrow);
    }
}