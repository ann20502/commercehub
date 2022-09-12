package com.commercehub.etl.core.entity.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class OrderShopee {

    public final String orderSn;
    public final String region;
    public final String currency;
    public final boolean cod;
    public final BigDecimal totalAmount;
    public final String orderStatus;
    public final String shippingCarrier;
    public final String paymentMethod;
    public final BigDecimal estimatedShippingFee;
    public final String messageToSeller;
    public final Instant createTime;
    public final Instant updateTime;
    public final Instant extractTime;
    public final int daysToShip;
    public final Instant shipByDate;
    public final long buyerUserId;
    public final String buyerUsername;
    public final RecipientAddress recipientAddress;
    public final BigDecimal actualShippingFee;
    public final boolean goodsToDeclare;
    public final String sellerNote;
    public final Instant sellerNoteUpdateTime;
    public final List<Item> items;
    public final Instant payTime;
    public final String dropshipper;
    public final String dropshipperPhone;
    public final boolean splitUp;
    public final String buyerCancelReason;
    public final String cancelBy;
    public final String cancelReason;
    public final boolean actualShippingFeeConfirmed;
    public final String fulfillmentFlag;
    public final Instant pickupDoneTime;
    public final Invoice invoice;
    public final String checkoutShippingCarrier;
    public final BigDecimal reverseShippingFee;
    public final Escrow escrow;

    public OrderShopee(String orderSn, String region, String currency, boolean cod, BigDecimal totalAmount, String orderStatus, String shippingCarrier, String paymentMethod, BigDecimal estimatedShippingFee, String messageToSeller, Instant createTime, Instant updateTime, Instant extractTime, int daysToShip, Instant shipByDate, long buyerUserId, String buyerUsername, RecipientAddress recipientAddress, BigDecimal actualShippingFee, boolean goodsToDeclare, String sellerNote, Instant sellerNoteUpdateTime, List<Item> items, Instant payTime, String dropshipper, String dropshipperPhone, boolean splitUp, String buyerCancelReason, String cancelBy, String cancelReason, boolean actualShippingFeeConfirmed, String fulfillmentFlag, Instant pickupDoneTime, Invoice invoice, String checkoutShippingCarrier, BigDecimal reverseShippingFee, Escrow escrow) {
        this.orderSn = orderSn;
        this.region = region;
        this.currency = currency;
        this.cod = cod;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.shippingCarrier = shippingCarrier;
        this.paymentMethod = paymentMethod;
        this.estimatedShippingFee = estimatedShippingFee;
        this.messageToSeller = messageToSeller;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.extractTime = extractTime;
        this.daysToShip = daysToShip;
        this.shipByDate = shipByDate;
        this.buyerUserId = buyerUserId;
        this.buyerUsername = buyerUsername;
        this.recipientAddress = recipientAddress;
        this.actualShippingFee = actualShippingFee;
        this.goodsToDeclare = goodsToDeclare;
        this.sellerNote = sellerNote;
        this.sellerNoteUpdateTime = sellerNoteUpdateTime;
        this.items = items;
        this.payTime = payTime;
        this.dropshipper = dropshipper;
        this.dropshipperPhone = dropshipperPhone;
        this.splitUp = splitUp;
        this.buyerCancelReason = buyerCancelReason;
        this.cancelBy = cancelBy;
        this.cancelReason = cancelReason;
        this.actualShippingFeeConfirmed = actualShippingFeeConfirmed;
        this.fulfillmentFlag = fulfillmentFlag;
        this.pickupDoneTime = pickupDoneTime;
        this.invoice = invoice;
        this.checkoutShippingCarrier = checkoutShippingCarrier;
        this.reverseShippingFee = reverseShippingFee;
        this.escrow = escrow;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderSn='" + orderSn + '\'' +
                ", region='" + region + '\'' +
                ", currency='" + currency + '\'' +
                ", cod=" + cod +
                ", totalAmount=" + totalAmount +
                ", orderStatus='" + orderStatus + '\'' +
                ", shippingCarrier='" + shippingCarrier + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", estimatedShippingFee='" + estimatedShippingFee + '\'' +
                ", messageToSeller='" + messageToSeller + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", extractTime=" + extractTime +
                ", daysToShip=" + daysToShip +
                ", shipByDate=" + shipByDate +
                ", buyerUserId=" + buyerUserId +
                ", buyerUsername='" + buyerUsername + '\'' +
                ", recipientAddress=" + recipientAddress +
                ", actualShippingFee=" + actualShippingFee +
                ", goodsToDeclare=" + goodsToDeclare +
                ", sellerNote='" + sellerNote + '\'' +
                ", sellerNoteUpdateTime=" + sellerNoteUpdateTime +
                ", items=" + items +
                ", payTime=" + payTime +
                ", dropshipper='" + dropshipper + '\'' +
                ", dropshipperPhone='" + dropshipperPhone + '\'' +
                ", splitUp=" + splitUp +
                ", buyerCancelReason='" + buyerCancelReason + '\'' +
                ", cancelBy='" + cancelBy + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", actualShippingFeeConfirmed=" + actualShippingFeeConfirmed +
                ", fulfillmentFlag='" + fulfillmentFlag + '\'' +
                ", pickupDoneTime=" + pickupDoneTime +
                ", invoice=" + invoice +
                ", checkoutShippingCarrier='" + checkoutShippingCarrier + '\'' +
                ", reverseShippingFee=" + reverseShippingFee +
                ", escrow=" + escrow +
                '}';
    }

    public static class RecipientAddress {

        public final String name;
        public final String phone;
        public final String town;
        public final String district;
        public final String city;
        public final String state;
        public final String region;
        public final String zipcode;
        public final String fullAddress;

        public RecipientAddress(String name, String phone, String town, String district, String city, String state, String region, String zipcode, String fullAddress) {
            this.name = name;
            this.phone = phone;
            this.town = town;
            this.district = district;
            this.city = city;
            this.state = state;
            this.region = region;
            this.zipcode = zipcode;
            this.fullAddress = fullAddress;
        }

        @Override
        public String toString() {
            return "RecipientAddress{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", town='" + town + '\'' +
                    ", district='" + district + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", region='" + region + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", fullAddress='" + fullAddress + '\'' +
                    '}';
        }
    }

    public static class Item {

        public final long id;
        public final String name;
        public final String sku;
        public final long modelId;
        public final String modelName;
        public final String modelSku;
        public final int modelQuantityPurchased;
        public final BigDecimal modelOriginalPrice;
        public final BigDecimal modelDiscountedPrice;
        public final boolean wholesale;
        public final BigDecimal weight;
        public final boolean addOnDeal;
        public final boolean mainItem;
        public final long addOnDealId;
        public final String promotionType;
        public final long promotionId;
        public final long orderItemId;
        public final long promotionGroupId;
        public final ImageInfo imageInfo;

        public Item(long id, String name, String sku, long modelId, String modelName, String modelSku, int modelQuantityPurchased, BigDecimal modelOriginalPrice, BigDecimal modelDiscountedPrice, boolean wholesale, BigDecimal weight, boolean addOnDeal, boolean mainItem, long addOnDealId, String promotionType, long promotionId, long orderItemId, long promotionGroupId, ImageInfo imageInfo) {
            this.id = id;
            this.name = name;
            this.sku = sku;
            this.modelId = modelId;
            this.modelName = modelName;
            this.modelSku = modelSku;
            this.modelQuantityPurchased = modelQuantityPurchased;
            this.modelOriginalPrice = modelOriginalPrice;
            this.modelDiscountedPrice = modelDiscountedPrice;
            this.wholesale = wholesale;
            this.weight = weight;
            this.addOnDeal = addOnDeal;
            this.mainItem = mainItem;
            this.addOnDealId = addOnDealId;
            this.promotionType = promotionType;
            this.promotionId = promotionId;
            this.orderItemId = orderItemId;
            this.promotionGroupId = promotionGroupId;
            this.imageInfo = imageInfo;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", sku='" + sku + '\'' +
                    ", modelId=" + modelId +
                    ", modelName='" + modelName + '\'' +
                    ", modelSku='" + modelSku + '\'' +
                    ", modelQuantityPurchased=" + modelQuantityPurchased +
                    ", modelOriginalPrice=" + modelOriginalPrice +
                    ", modelDiscountedPrice=" + modelDiscountedPrice +
                    ", wholesale=" + wholesale +
                    ", weight=" + weight +
                    ", addOnDeal=" + addOnDeal +
                    ", mainItem=" + mainItem +
                    ", addOnDealId=" + addOnDealId +
                    ", promotionType='" + promotionType + '\'' +
                    ", promotionId=" + promotionId +
                    ", orderItemId=" + orderItemId +
                    ", promotionGroupId=" + promotionGroupId +
                    ", imageInfo=" + imageInfo +
                    '}';
        }
    }

    public static class ImageInfo {

        public final String imageUrl;

        public ImageInfo(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        @Override
        public String toString() {
            return "ImageInfo{" +
                    "imageUrl='" + imageUrl + '\'' +
                    '}';
        }
    }

    public static class Invoice {

        public final String number;
        public final String seriesNUmber;
        public final String accessKey;
        public final Instant issueDate;
        public final BigDecimal totalValue;
        public final BigDecimal productsTotalValue;
        public final String taxCode;

        public Invoice(String number, String seriesNUmber, String accessKey, Instant issueDate, BigDecimal totalValue, BigDecimal productsTotalValue, String taxCode) {
            this.number = number;
            this.seriesNUmber = seriesNUmber;
            this.accessKey = accessKey;
            this.issueDate = issueDate;
            this.totalValue = totalValue;
            this.productsTotalValue = productsTotalValue;
            this.taxCode = taxCode;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "number='" + number + '\'' +
                    ", seriesNUmber='" + seriesNUmber + '\'' +
                    ", accessKey='" + accessKey + '\'' +
                    ", issueDate=" + issueDate +
                    ", totalValue=" + totalValue +
                    ", productsTotalValue=" + productsTotalValue +
                    ", taxCode='" + taxCode + '\'' +
                    '}';
        }
    }

    public static class Escrow {

        public final BigDecimal escrowAmount;
        public final BigDecimal buyerTotalAmount;
        public final BigDecimal originalPrice;
        public final BigDecimal sellerDiscount;
        public final BigDecimal shopeeDiscount;
        public final BigDecimal voucherFromSeller;
        public final BigDecimal coins;
        public final BigDecimal buyerPaidShippingFee;
        public final BigDecimal buyerTransactionFee;
        public final BigDecimal crossBorderTax;
        public final BigDecimal paymentPromotion;
        public final BigDecimal commissionFee;
        public final BigDecimal serviceFee;
        public final BigDecimal sellerTransactionFee;
        public final BigDecimal sellerLostCompensation;
        public final BigDecimal sellerCoinCashBack;
        public final BigDecimal escrowTax;
        public final BigDecimal finalShippingFee;
        public final BigDecimal actualShippingFee;
        public final BigDecimal shopeeShippingRebate;
        public final BigDecimal shippingFeeDiscountFrom3pl;
        public final BigDecimal sellerShippingDiscount;
        public final BigDecimal estimatedShippingFee;
        public final String[] voucherCodes;
        public final BigDecimal drcAdjustableFund;
        public final BigDecimal costOfGoodsSold;
        public final BigDecimal originalCostOfGoodsSold;
        public final BigDecimal originalSHopeeDiscount;
        public final BigDecimal sellerReturnRefund;
        public final BigDecimal escrowAmountPri;
        public final BigDecimal buyerTotalAmountPri;
        public final BigDecimal originalPricePri;
        public final BigDecimal sellerReturnRefundPri;
        public final BigDecimal commissionFeePri;
        public final BigDecimal serviceFeePri;
        public final BigDecimal drcAdjustableRefundPri;
        public final String priCurrency;
        public final String affCurrency;
        public final BigDecimal exchangeRate;
        public final BigDecimal reverseShippingFee;

        public Escrow(BigDecimal escrowAmount, BigDecimal buyerTotalAmount, BigDecimal originalPrice, BigDecimal sellerDiscount, BigDecimal shopeeDiscount, BigDecimal voucherFromSeller, BigDecimal coins, BigDecimal buyerPaidShippingFee, BigDecimal buyerTransactionFee, BigDecimal crossBorderTax, BigDecimal paymentPromotion, BigDecimal commissionFee, BigDecimal serviceFee, BigDecimal sellerTransactionFee, BigDecimal sellerLostCompensation, BigDecimal sellerCoinCashBack, BigDecimal escrowTax, BigDecimal finalShippingFee, BigDecimal actualShippingFee, BigDecimal shopeeShippingRebate, BigDecimal shippingFeeDiscountFrom3pl, BigDecimal sellerShippingDiscount, BigDecimal estimatedShippingFee, String[] voucherCodes, BigDecimal drcAdjustableFund, BigDecimal costOfGoodsSold, BigDecimal originalCostOfGoodsSold, BigDecimal originalSHopeeDiscount, BigDecimal sellerReturnRefund, BigDecimal escrowAmountPri, BigDecimal buyerTotalAmountPri, BigDecimal originalPricePri, BigDecimal sellerReturnRefundPri, BigDecimal commissionFeePri, BigDecimal serviceFeePri, BigDecimal drcAdjustableRefundPri, String priCurrency, String affCurrency, BigDecimal exchangeRate, BigDecimal reverseShippingFee) {
            this.escrowAmount = escrowAmount;
            this.buyerTotalAmount = buyerTotalAmount;
            this.originalPrice = originalPrice;
            this.sellerDiscount = sellerDiscount;
            this.shopeeDiscount = shopeeDiscount;
            this.voucherFromSeller = voucherFromSeller;
            this.coins = coins;
            this.buyerPaidShippingFee = buyerPaidShippingFee;
            this.buyerTransactionFee = buyerTransactionFee;
            this.crossBorderTax = crossBorderTax;
            this.paymentPromotion = paymentPromotion;
            this.commissionFee = commissionFee;
            this.serviceFee = serviceFee;
            this.sellerTransactionFee = sellerTransactionFee;
            this.sellerLostCompensation = sellerLostCompensation;
            this.sellerCoinCashBack = sellerCoinCashBack;
            this.escrowTax = escrowTax;
            this.finalShippingFee = finalShippingFee;
            this.actualShippingFee = actualShippingFee;
            this.shopeeShippingRebate = shopeeShippingRebate;
            this.shippingFeeDiscountFrom3pl = shippingFeeDiscountFrom3pl;
            this.sellerShippingDiscount = sellerShippingDiscount;
            this.estimatedShippingFee = estimatedShippingFee;
            this.voucherCodes = voucherCodes;
            this.drcAdjustableFund = drcAdjustableFund;
            this.costOfGoodsSold = costOfGoodsSold;
            this.originalCostOfGoodsSold = originalCostOfGoodsSold;
            this.originalSHopeeDiscount = originalSHopeeDiscount;
            this.sellerReturnRefund = sellerReturnRefund;
            this.escrowAmountPri = escrowAmountPri;
            this.buyerTotalAmountPri = buyerTotalAmountPri;
            this.originalPricePri = originalPricePri;
            this.sellerReturnRefundPri = sellerReturnRefundPri;
            this.commissionFeePri = commissionFeePri;
            this.serviceFeePri = serviceFeePri;
            this.drcAdjustableRefundPri = drcAdjustableRefundPri;
            this.priCurrency = priCurrency;
            this.affCurrency = affCurrency;
            this.exchangeRate = exchangeRate;
            this.reverseShippingFee = reverseShippingFee;
        }

        @Override
        public String toString() {
            return "Escrow{" +
                    "escrowAmount=" + escrowAmount +
                    ", buyerTotalAmount=" + buyerTotalAmount +
                    ", originalPrice=" + originalPrice +
                    ", sellerDiscount=" + sellerDiscount +
                    ", shopeeDiscount=" + shopeeDiscount +
                    ", voucherFromSeller=" + voucherFromSeller +
                    ", coins=" + coins +
                    ", buyerPaidShippingFee=" + buyerPaidShippingFee +
                    ", buyerTransactionFee=" + buyerTransactionFee +
                    ", crossBorderTax=" + crossBorderTax +
                    ", paymentPromotion=" + paymentPromotion +
                    ", commissionFee=" + commissionFee +
                    ", serviceFee=" + serviceFee +
                    ", sellerTransactionFee=" + sellerTransactionFee +
                    ", sellerLostCompensation=" + sellerLostCompensation +
                    ", sellerCoinCashBack=" + sellerCoinCashBack +
                    ", escrowTax=" + escrowTax +
                    ", finalShippingFee=" + finalShippingFee +
                    ", actualShippingFee=" + actualShippingFee +
                    ", shopeeShippingRebate=" + shopeeShippingRebate +
                    ", shippingFeeDiscountFrom3pl=" + shippingFeeDiscountFrom3pl +
                    ", sellerShippingDiscount=" + sellerShippingDiscount +
                    ", estimatedShippingFee=" + estimatedShippingFee +
                    ", voucherCodes=" + Arrays.toString(voucherCodes) +
                    ", drcAdjustableFund=" + drcAdjustableFund +
                    ", costOfGoodsSold=" + costOfGoodsSold +
                    ", originalCostOfGoodsSold=" + originalCostOfGoodsSold +
                    ", originalSHopeeDiscount=" + originalSHopeeDiscount +
                    ", sellerReturnRefund=" + sellerReturnRefund +
                    ", escrowAmountPri=" + escrowAmountPri +
                    ", buyerTotalAmountPri=" + buyerTotalAmountPri +
                    ", originalPricePri=" + originalPricePri +
                    ", sellerReturnRefundPri=" + sellerReturnRefundPri +
                    ", commissionFeePri=" + commissionFeePri +
                    ", serviceFeePri=" + serviceFeePri +
                    ", drcAdjustableRefundPri=" + drcAdjustableRefundPri +
                    ", priCurrency='" + priCurrency + '\'' +
                    ", affCurrency='" + affCurrency + '\'' +
                    ", exchangeRate=" + exchangeRate +
                    ", reverseShippingFee=" + reverseShippingFee +
                    '}';
        }
    }

}
