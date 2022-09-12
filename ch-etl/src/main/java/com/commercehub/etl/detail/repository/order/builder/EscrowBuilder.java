package com.commercehub.etl.detail.repository.order.builder;

import com.commercehub.etl.detail.repository.order.BQOrderShopee;

import java.math.BigDecimal;

public class EscrowBuilder {
    private BigDecimal escrowAmount;
    private BigDecimal buyerTotalAmount;
    private BigDecimal originalPrice;
    private BigDecimal sellerDiscount;
    private BigDecimal shopeeDiscount;
    private BigDecimal voucherFromSeller;
    private BigDecimal coins;
    private BigDecimal buyerPaidShippingFee;
    private BigDecimal buyerTransactionFee;
    private BigDecimal crossBorderTax;
    private BigDecimal paymentPromotion;
    private BigDecimal commissionFee;
    private BigDecimal serviceFee;
    private BigDecimal sellerTransactionFee;
    private BigDecimal sellerLostCompensation;
    private BigDecimal sellerCoinCashBack;
    private BigDecimal escrowTax;
    private BigDecimal finalShippingFee;
    private BigDecimal actualShippingFee;
    private BigDecimal shopeeShippingRebate;
    private BigDecimal shippingFeeDiscountFrom3pl;
    private BigDecimal sellerShippingDiscount;
    private BigDecimal estimatedShippingFee;
    private String[] voucherCodes;
    private BigDecimal drcAdjustableFund;
    private BigDecimal costOfGoodsSold;
    private BigDecimal originalCostOfGoodsSold;
    private BigDecimal originalSHopeeDiscount;
    private BigDecimal sellerReturnRefund;
    private BigDecimal escrowAmountPri;
    private BigDecimal buyerTotalAmountPri;
    private BigDecimal originalPricePri;
    private BigDecimal sellerReturnRefundPri;
    private BigDecimal commissionFeePri;
    private BigDecimal serviceFeePri;
    private BigDecimal drcAdjustableRefundPri;
    private String priCurrency;
    private String affCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal reverseShippingFee;

    public EscrowBuilder setEscrowAmount(BigDecimal escrowAmount) {
        this.escrowAmount = escrowAmount;
        return this;
    }

    public EscrowBuilder setBuyerTotalAmount(BigDecimal buyerTotalAmount) {
        this.buyerTotalAmount = buyerTotalAmount;
        return this;
    }

    public EscrowBuilder setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public EscrowBuilder setSellerDiscount(BigDecimal sellerDiscount) {
        this.sellerDiscount = sellerDiscount;
        return this;
    }

    public EscrowBuilder setShopeeDiscount(BigDecimal shopeeDiscount) {
        this.shopeeDiscount = shopeeDiscount;
        return this;
    }

    public EscrowBuilder setVoucherFromSeller(BigDecimal voucherFromSeller) {
        this.voucherFromSeller = voucherFromSeller;
        return this;
    }

    public EscrowBuilder setCoins(BigDecimal coins) {
        this.coins = coins;
        return this;
    }

    public EscrowBuilder setBuyerPaidShippingFee(BigDecimal buyerPaidShippingFee) {
        this.buyerPaidShippingFee = buyerPaidShippingFee;
        return this;
    }

    public EscrowBuilder setBuyerTransactionFee(BigDecimal buyerTransactionFee) {
        this.buyerTransactionFee = buyerTransactionFee;
        return this;
    }

    public EscrowBuilder setCrossBorderTax(BigDecimal crossBorderTax) {
        this.crossBorderTax = crossBorderTax;
        return this;
    }

    public EscrowBuilder setPaymentPromotion(BigDecimal paymentPromotion) {
        this.paymentPromotion = paymentPromotion;
        return this;
    }

    public EscrowBuilder setCommissionFee(BigDecimal commissionFee) {
        this.commissionFee = commissionFee;
        return this;
    }

    public EscrowBuilder setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
        return this;
    }

    public EscrowBuilder setSellerTransactionFee(BigDecimal sellerTransactionFee) {
        this.sellerTransactionFee = sellerTransactionFee;
        return this;
    }

    public EscrowBuilder setSellerLostCompensation(BigDecimal sellerLostCompensation) {
        this.sellerLostCompensation = sellerLostCompensation;
        return this;
    }

    public EscrowBuilder setSellerCoinCashBack(BigDecimal sellerCoinCashBack) {
        this.sellerCoinCashBack = sellerCoinCashBack;
        return this;
    }

    public EscrowBuilder setEscrowTax(BigDecimal escrowTax) {
        this.escrowTax = escrowTax;
        return this;
    }

    public EscrowBuilder setFinalShippingFee(BigDecimal finalShippingFee) {
        this.finalShippingFee = finalShippingFee;
        return this;
    }

    public EscrowBuilder setActualShippingFee(BigDecimal actualShippingFee) {
        this.actualShippingFee = actualShippingFee;
        return this;
    }

    public EscrowBuilder setShopeeShippingRebate(BigDecimal shopeeShippingRebate) {
        this.shopeeShippingRebate = shopeeShippingRebate;
        return this;
    }

    public EscrowBuilder setShippingFeeDiscountFrom3pl(BigDecimal shippingFeeDiscountFrom3pl) {
        this.shippingFeeDiscountFrom3pl = shippingFeeDiscountFrom3pl;
        return this;
    }

    public EscrowBuilder setSellerShippingDiscount(BigDecimal sellerShippingDiscount) {
        this.sellerShippingDiscount = sellerShippingDiscount;
        return this;
    }

    public EscrowBuilder setEstimatedShippingFee(BigDecimal estimatedShippingFee) {
        this.estimatedShippingFee = estimatedShippingFee;
        return this;
    }

    public EscrowBuilder setVoucherCodes(String[] voucherCodes) {
        this.voucherCodes = voucherCodes;
        return this;
    }

    public EscrowBuilder setDrcAdjustableFund(BigDecimal drcAdjustableFund) {
        this.drcAdjustableFund = drcAdjustableFund;
        return this;
    }

    public EscrowBuilder setCostOfGoodsSold(BigDecimal costOfGoodsSold) {
        this.costOfGoodsSold = costOfGoodsSold;
        return this;
    }

    public EscrowBuilder setOriginalCostOfGoodsSold(BigDecimal originalCostOfGoodsSold) {
        this.originalCostOfGoodsSold = originalCostOfGoodsSold;
        return this;
    }

    public EscrowBuilder setOriginalSHopeeDiscount(BigDecimal originalSHopeeDiscount) {
        this.originalSHopeeDiscount = originalSHopeeDiscount;
        return this;
    }

    public EscrowBuilder setSellerReturnRefund(BigDecimal sellerReturnRefund) {
        this.sellerReturnRefund = sellerReturnRefund;
        return this;
    }

    public EscrowBuilder setEscrowAmountPri(BigDecimal escrowAmountPri) {
        this.escrowAmountPri = escrowAmountPri;
        return this;
    }

    public EscrowBuilder setBuyerTotalAmountPri(BigDecimal buyerTotalAmountPri) {
        this.buyerTotalAmountPri = buyerTotalAmountPri;
        return this;
    }

    public EscrowBuilder setOriginalPricePri(BigDecimal originalPricePri) {
        this.originalPricePri = originalPricePri;
        return this;
    }

    public EscrowBuilder setSellerReturnRefundPri(BigDecimal sellerReturnRefundPri) {
        this.sellerReturnRefundPri = sellerReturnRefundPri;
        return this;
    }

    public EscrowBuilder setCommissionFeePri(BigDecimal commissionFeePri) {
        this.commissionFeePri = commissionFeePri;
        return this;
    }

    public EscrowBuilder setServiceFeePri(BigDecimal serviceFeePri) {
        this.serviceFeePri = serviceFeePri;
        return this;
    }

    public EscrowBuilder setDrcAdjustableRefundPri(BigDecimal drcAdjustableRefundPri) {
        this.drcAdjustableRefundPri = drcAdjustableRefundPri;
        return this;
    }

    public EscrowBuilder setPriCurrency(String priCurrency) {
        this.priCurrency = priCurrency;
        return this;
    }

    public EscrowBuilder setAffCurrency(String affCurrency) {
        this.affCurrency = affCurrency;
        return this;
    }

    public EscrowBuilder setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public EscrowBuilder setReverseShippingFee(BigDecimal reverseShippingFee) {
        this.reverseShippingFee = reverseShippingFee;
        return this;
    }

    public BQOrderShopee.Escrow createEscrow() {
        return new BQOrderShopee.Escrow(escrowAmount, buyerTotalAmount, originalPrice, sellerDiscount, shopeeDiscount, voucherFromSeller, coins, buyerPaidShippingFee, buyerTransactionFee, crossBorderTax, paymentPromotion, commissionFee, serviceFee, sellerTransactionFee, sellerLostCompensation, sellerCoinCashBack, escrowTax, finalShippingFee, actualShippingFee, shopeeShippingRebate, shippingFeeDiscountFrom3pl, sellerShippingDiscount, estimatedShippingFee, voucherCodes, drcAdjustableFund, costOfGoodsSold, originalCostOfGoodsSold, originalSHopeeDiscount, sellerReturnRefund, escrowAmountPri, buyerTotalAmountPri, originalPricePri, sellerReturnRefundPri, commissionFeePri, serviceFeePri, drcAdjustableRefundPri, priCurrency, affCurrency, exchangeRate, reverseShippingFee);
    }
}