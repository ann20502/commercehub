package com.commercehub.etl.domain.entity.order;

import com.squareup.moshi.Json;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Order {

    @Json(name = "order_sn")
    public final String orderSn;

    @Json(name = "region")
    public final String region;

    @Json(name = "currency")
    public final String currency;

    @Json(name = "cod")
    public final boolean cod;

    @Json(name = "total_amount")
    public final BigDecimal totalAmount;

    @Json(name = "order_status")
    public final String orderStatus;

    @Json(name = "shipping_carrier")
    public final String shippingCarrier;

    @Json(name = "payment_method")
    public final String paymentMethod;

    @Json(name = "estimated_shipping_fee")
    public final BigDecimal estimatedShippingFee;

    @Json(name = "message_to_seller")
    public final String messageToSeller;

    @Json(name = "create_time")
    public final Instant createTime;

    @Json(name = "update_time")
    public final Instant updateTime;

    @Json(name = "extract_time")
    public final Instant extractTime;

    @Json(name = "days_to_ship")
    public final int daysToShip;

    @Json(name = "ship_by_date")
    public final Instant shipByDate;

    @Json(name = "buyer_user_id")
    public final long buyerUserId;

    @Json(name = "buyer_username")
    public final String buyerUsername;

    @Json(name = "recipient_address")
    public final RecipientAddress recipientAddress;

    @Json(name = "actual_shipping_fee")
    public final BigDecimal actualShippingFee;

    @Json(name = "goods_to_declare")
    public final boolean goodsToDeclare;

    @Json(name = "seller_note")
    public final String sellerNote;

    @Json(name = "seller_note_update_time")
    public final Instant sellerNoteUpdateTime;

    @Json(name = "items")
    public final List<Item> items;

    @Json(name = "pay_time")
    public final Instant payTime;

    @Json(name = "dropshipper")
    public final String dropshipper;

    @Json(name = "dropshipper_phone")
    public final String dropshipperPhone;

    @Json(name = "split_up")
    public final boolean splitUp;

    @Json(name = "buyer_cancel_reason")
    public final String buyerCancelReason;

    @Json(name = "cancel_by")
    public final String cancelBy;

    @Json(name = "cancel_reason")
    public final String cancelReason;

    @Json(name = "actual_shipping_fee_confirmed")
    public final boolean actualShippingFeeConfirmed;

    @Json(name = "fulfillment_flag")
    public final String fulfillmentFlag;

    @Json(name = "pickup_done_time")
    public final Instant pickupDoneTime;

    @Json(name = "invoice_data")
    public final Invoice invoice;

    @Json(name = "checkout_shipping_carrier")
    public final String checkoutShippingCarrier;

    @Json(name = "reverse_shipping_fee")
    public final BigDecimal reverseShippingFee;

    @Json(name = "escrow")
    public final Escrow escrow;

    public Order(String orderSn, String region, String currency, boolean cod, BigDecimal totalAmount, String orderStatus, String shippingCarrier, String paymentMethod, BigDecimal estimatedShippingFee, String messageToSeller, Instant createTime, Instant updateTime, Instant extractTime, int daysToShip, Instant shipByDate, long buyerUserId, String buyerUsername, RecipientAddress recipientAddress, BigDecimal actualShippingFee, boolean goodsToDeclare, String sellerNote, Instant sellerNoteUpdateTime, List<Item> items, Instant payTime, String dropshipper, String dropshipperPhone, boolean splitUp, String buyerCancelReason, String cancelBy, String cancelReason, boolean actualShippingFeeConfirmed, String fulfillmentFlag, Instant pickupDoneTime, Invoice invoice, String checkoutShippingCarrier, BigDecimal reverseShippingFee, Escrow escrow) {
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

        @Json(name = "name")
        public final String name;

        @Json(name = "phone")
        public final String phone;

        @Json(name = "town")
        public final String town;

        @Json(name = "district")
        public final String district;

        @Json(name = "city")
        public final String city;

        @Json(name = "state")
        public final String state;

        @Json(name = "region")
        public final String region;

        @Json(name = "zipcode")
        public final String zipcode;

        @Json(name = "full_address")
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

        @Json(name = "item_id")
        public final long id;

        @Json(name = "item_name")
        public final String name;

        @Json(name = "item_sku")
        public final String sku;

        @Json(name = "model_id")
        public final long modelId;

        @Json(name = "model_name")
        public final String modelName;

        @Json(name = "modeL_sku")
        public final String modelSku;

        @Json(name = "model_quantity_purchased")
        public final int modelQuantityPurchased;

        @Json(name = "model_original_price")
        public final BigDecimal modelOriginalPrice;

        @Json(name = "model_discounted_price")
        public final BigDecimal modelDiscountedPrice;

        @Json(name = "wholesale")
        public final boolean wholesale;

        @Json(name = "weight")
        public final BigDecimal weight;

        @Json(name = "add_on_deal")
        public final boolean addOnDeal;

        @Json(name = "main_item")
        public final boolean mainItem;

        @Json(name = "add_on_deal_id")
        public final long addOnDealId;

        @Json(name = "promotion_type")
        public final String promotionType;

        @Json(name = "promotion_id")
        public final long promotionId;

        @Json(name = "order_item_id")
        public final long orderItemId;

        @Json(name = "promotion_group_id")
        public final long promotionGroupId;

        @Json(name = "image_info")
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

        @Json(name = "image_url")
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

        @Json(name = "number")
        public final String number;

        @Json(name = "series_number")
        public final String seriesNUmber;

        @Json(name = "access_key")
        public final String accessKey;

        @Json(name = "issue_date")
        public final Instant issueDate;

        @Json(name = "total_value")
        public final BigDecimal totalValue;

        @Json(name = "products_total_value")
        public final BigDecimal productsTotalValue;

        @Json(name = "tax_code")
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

        @Json(name = "escrow_amount")
        public final BigDecimal escrowAmount;

        @Json(name = "buyer_total_amount")
        public final BigDecimal buyerTotalAmount;

        @Json(name = "original_price")
        public final BigDecimal originalPrice;

        @Json(name = "seller_discount")
        public final BigDecimal sellerDiscount;

        @Json(name = "shopee_discount")
        public final BigDecimal shopeeDiscount;

        @Json(name = "voucher_from_seller")
        public final BigDecimal voucherFromSeller;

        @Json(name = "coins")
        public final BigDecimal coins;

        @Json(name = "buyer_paid_shipping_fee")
        public final BigDecimal buyerPaidShippingFee;

        @Json(name = "buyer_transaction_fee")
        public final BigDecimal buyerTransactionFee;

        @Json(name = "cross_border_tax")
        public final BigDecimal crossBorderTax;

        @Json(name = "payment_promotion")
        public final BigDecimal paymentPromotion;

        @Json(name = "commission_fee")
        public final BigDecimal commissionFee;

        @Json(name = "service_fee")
        public final BigDecimal serviceFee;

        @Json(name = "seller_transaction_fee")
        public final BigDecimal sellerTransactionFee;

        @Json(name = "seller_lost_compensation")
        public final BigDecimal sellerLostCompensation;

        @Json(name = "seller_coin_cash_back")
        public final BigDecimal sellerCoinCashBack;

        @Json(name = "escrow_tax")
        public final BigDecimal escrowTax;

        @Json(name = "final_shipping_fee")
        public final BigDecimal finalShippingFee;

        @Json(name = "actual_shipping_fee")
        public final BigDecimal actualShippingFee;

        @Json(name = "shopee_shipping_rebate")
        public final BigDecimal shopeeShippingRebate;

        @Json(name = "shipping_fee_discount_from_3pl")
        public final BigDecimal shippingFeeDiscountFrom3pl;

        @Json(name = "seller_shipping_discount")
        public final BigDecimal sellerShippingDiscount;

        @Json(name = "estimated_shipping_fee")
        public final BigDecimal estimatedShippingFee;

        @Json(name = "seller_voucher_code")
        public final String[] voucherCodes;

        @Json(name = "drc_adjustable_fund")
        public final BigDecimal drcAdjustableFund;

        @Json(name = "cost_of_goods_sold")
        public final BigDecimal costOfGoodsSold;

        @Json(name = "original_cost_of_goods_sold")
        public final BigDecimal originalCostOfGoodsSold;

        @Json(name = "original_shopee_discount")
        public final BigDecimal originalSHopeeDiscount;

        @Json(name = "seller_return_refund")
        public final BigDecimal sellerReturnRefund;

        @Json(name = "escrow_amount_pri")
        public final BigDecimal escrowAmountPri;

        @Json(name = "buyer_total_amount_pri")
        public final BigDecimal buyerTotalAmountPri;

        @Json(name = "original_price_pri")
        public final BigDecimal originalPricePri;

        @Json(name = "seller_return_refund_pri")
        public final BigDecimal sellerReturnRefundPri;

        @Json(name = "commission_fee_pri")
        public final BigDecimal commissionFeePri;

        @Json(name = "service_fee_pri")
        public final BigDecimal serviceFeePri;

        @Json(name = "drc_adjustable_refund_pri")
        public final BigDecimal drcAdjustableRefundPri;

        @Json(name = "pri_currency")
        public final String priCurrency;

        @Json(name = "aff_currency")
        public final String affCurrency;

        @Json(name = "exchange_rate")
        public final BigDecimal exchangeRate;

        @Json(name = "reverse_shipping_fee")
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
