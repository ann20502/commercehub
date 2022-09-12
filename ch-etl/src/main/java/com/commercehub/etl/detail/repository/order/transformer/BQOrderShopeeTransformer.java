package com.commercehub.etl.detail.repository.order.transformer;

import com.commercehub.etl.core.entity.order.OrderShopee;
import com.commercehub.etl.detail.repository.order.BQOrderShopee;
import com.commercehub.etl.detail.repository.order.builder.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class BQOrderShopeeTransformer {

    public static BQOrderShopee from(OrderShopee order) {
        BQOrderShopee.RecipientAddress address = getAddress(order.recipientAddress);
        List<BQOrderShopee.Item> items = getItems(order.items);
        BQOrderShopee.Invoice invoice = order.invoice == null ? null : getInvoice(order.invoice);
        BQOrderShopee.Escrow finalEscrow = getEscrow(order.escrow);

        BQOrderShopee result = new BQOrderShopeeBuilder()
                .setOrderSn(order.orderSn)
                .setRegion(order.region)
                .setCurrency(order.currency)
                .setCod(order.cod)
                .setTotalAmount(order.totalAmount)
                .setOrderStatus(order.orderStatus)
                .setShippingCarrier(order.shippingCarrier)
                .setPaymentMethod(order.paymentMethod)
                .setEstimatedShippingFee(order.estimatedShippingFee)
                .setMessageToSeller(order.messageToSeller)
                .setCreateTime(order.createTime)
                .setUpdateTime(order.updateTime)
                .setExtractTime(Instant.now())
                .setDaysToShip(order.daysToShip)
                .setShipByDate(order.shipByDate)
                .setBuyerUserId(order.buyerUserId)
                .setBuyerUsername(order.buyerUsername)
                .setRecipientAddress(address)
                .setActualShippingFee(order.actualShippingFee)
                .setGoodsToDeclare(order.goodsToDeclare)
                .setSellerNote(order.sellerNote)
                .setSellerNoteUpdateTime(order.sellerNoteUpdateTime)
                .setItems(items)
                .setPayTime(order.payTime)
                .setDropshipper(order.dropshipper)
                .setDropshipperPhone(order.dropshipperPhone)
                .setSplitUp(order.splitUp)
                .setBuyerCancelReason(order.buyerCancelReason)
                .setCancelBy(order.cancelBy)
                .setCancelReason(order.cancelReason)
                .setActualShippingFeeConfirmed(order.actualShippingFeeConfirmed)
                .setFulfillmentFlag(order.fulfillmentFlag)
                .setPickupDoneTime(order.pickupDoneTime)
                .setInvoice(invoice)
                .setCheckoutShippingCarrier(order.checkoutShippingCarrier)
                .setReverseShippingFee(order.reverseShippingFee)
                .setEscrow(finalEscrow)
                .createBQOrderShopee();

        return result;
    }

    private static BQOrderShopee.RecipientAddress getAddress(OrderShopee.RecipientAddress addr) {
        return new RecipientAddressBuilder()
                .setName(addr.name)
                .setPhone(addr.phone)
                .setTown(addr.town)
                .setDistrict(addr.district)
                .setCity(addr.city)
                .setState(addr.state)
                .setRegion(addr.region)
                .setZipcode(addr.zipcode)
                .setFullAddress(addr.fullAddress)
                .createRecipientAddress();
    }

    private static List<BQOrderShopee.Item> getItems(List<OrderShopee.Item> items) {
        return items.stream()
                .map(item -> new ItemBuilder()
                        .setId(item.id)
                        .setName(item.name)
                        .setSku(item.sku)
                        .setModelId(item.modelId)
                        .setModelName(item.modelName)
                        .setModelSku(item.modelSku)
                        .setModelQuantityPurchased(item.modelQuantityPurchased)
                        .setModelOriginalPrice(item.modelOriginalPrice)
                        .setModelDiscountedPrice(item.modelDiscountedPrice)
                        .setWholesale(item.wholesale)
                        .setWeight(item.weight)
                        .setAddOnDeal(item.addOnDeal)
                        .setMainItem(item.mainItem)
                        .setAddOnDealId(item.addOnDealId)
                        .setPromotionType(item.promotionType)
                        .setPromotionId(item.promotionId)
                        .setOrderItemId(item.orderItemId)
                        .setPromotionGroupId(item.promotionGroupId)
                        .setImageInfo(new BQOrderShopee.ImageInfo(item.imageInfo.imageUrl))
                        .createItem())
                .collect(Collectors.toList());
    }

    private static BQOrderShopee.Invoice getInvoice(OrderShopee.Invoice invoice) {
        return new InvoiceBuilder()
                .setNumber(invoice.number)
                .setSeriesNUmber(invoice.seriesNUmber)
                .setAccessKey(invoice.accessKey)
                .setIssueDate(invoice.issueDate)
                .setTotalValue(invoice.totalValue)
                .setProductsTotalValue(invoice.productsTotalValue)
                .setTaxCode(invoice.taxCode)
                .createInvoice();
    }

    private static BQOrderShopee.Escrow getEscrow(OrderShopee.Escrow income) {
        return new EscrowBuilder()
                .setEscrowAmount(income.escrowAmount)
                .setBuyerTotalAmount(income.buyerTotalAmount)
                .setOriginalPrice(income.originalPrice)
                .setSellerDiscount(income.sellerDiscount)
                .setShopeeDiscount(income.shopeeDiscount)
                .setVoucherFromSeller(income.voucherFromSeller)
                .setCoins(income.coins)
                .setBuyerPaidShippingFee(income.buyerPaidShippingFee)
                .setBuyerTransactionFee(income.buyerTransactionFee)
                .setCrossBorderTax(income.crossBorderTax)
                .setPaymentPromotion(income.paymentPromotion)
                .setCommissionFee(income.commissionFee)
                .setServiceFee(income.serviceFee)
                .setSellerTransactionFee(income.sellerTransactionFee)
                .setSellerLostCompensation(income.sellerLostCompensation)
                .setSellerCoinCashBack(income.sellerCoinCashBack)
                .setEscrowTax(income.escrowTax)
                .setFinalShippingFee(income.finalShippingFee)
                .setActualShippingFee(income.actualShippingFee)
                .setShopeeShippingRebate(income.shopeeShippingRebate)
                .setShippingFeeDiscountFrom3pl(income.shippingFeeDiscountFrom3pl)
                .setSellerShippingDiscount(income.sellerShippingDiscount)
                .setEstimatedShippingFee(income.estimatedShippingFee)
                .setVoucherCodes(income.voucherCodes)
                .setDrcAdjustableFund(income.drcAdjustableFund)
                .setCostOfGoodsSold(income.costOfGoodsSold)
                .setOriginalCostOfGoodsSold(income.originalCostOfGoodsSold)
                .setOriginalSHopeeDiscount(income.originalSHopeeDiscount)
                .setSellerReturnRefund(income.sellerReturnRefund)
                .setBuyerTotalAmountPri(income.buyerTotalAmountPri)
                .setOriginalPricePri(income.originalPricePri)
                .setSellerReturnRefundPri(income.sellerReturnRefundPri)
                .setCommissionFeePri(income.commissionFeePri)
                .setServiceFeePri(income.serviceFeePri)
                .setDrcAdjustableRefundPri(income.drcAdjustableRefundPri)
                .setPriCurrency(income.priCurrency)
                .setAffCurrency(income.affCurrency)
                .setExchangeRate(income.exchangeRate)
                .setReverseShippingFee(income.reverseShippingFee)
                .createEscrow();
    }

}
