package com.commercehub.etl.domain.entity.order;

import com.commercehub.rest.shopee.output.GetEscrowDetailOutput;
import com.commercehub.rest.shopee.output.GetOrderDetailOutput;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class OrderTransformer {

    public static Order from(GetOrderDetailOutput.Order order, GetEscrowDetailOutput escrow) {
        Order.RecipientAddress address = getAddress(order.getRecipient_address());
        List<Order.Item> items = getItems(order.getItem_list());
        Order.Invoice invoice = order.getInvoice_data() == null ? null : getInvoice(order.getInvoice_data());
        Order.Escrow finalEscrow = getEscrow(escrow.getResponse().getOrder_income());

        Order result = new OrderBuilder()
                .setOrderSn(order.getOrder_sn())
                .setRegion(order.getRegion())
                .setCurrency(order.getCurrency())
                .setCod(order.isCod())
                .setTotalAmount(order.getTotal_amount())
                .setOrderStatus(order.getOrder_status())
                .setShippingCarrier(order.getShipping_carrier())
                .setPaymentMethod(order.getPayment_method())
                .setEstimatedShippingFee(order.getEstimated_shipping_fee())
                .setMessageToSeller(order.getMessage_to_seller())
                .setCreateTime(Instant.ofEpochSecond(order.getCreate_time()))
                .setUpdateTime(Instant.ofEpochSecond(order.getUpdate_time()))
                .setExtractTime(Instant.now())
                .setDaysToShip(order.getDays_to_shipl())
                .setShipByDate(Instant.ofEpochSecond(order.getShip_by_date()))
                .setBuyerUserId(order.getBuyer_user_id())
                .setBuyerUsername(order.getBuyer_username())
                .setRecipientAddress(address)
                .setActualShippingFee(order.getActual_shipping_fee())
                .setGoodsToDeclare(order.isGoods_to_declare())
                .setSellerNote(order.getNote())
                .setSellerNoteUpdateTime(Instant.ofEpochSecond(order.getNote_update_time()))
                .setItems(items)
                .setPayTime(Instant.ofEpochSecond(order.getPay_time()))
                .setDropshipper(order.getDropshipper())
                .setDropshipperPhone(order.getDropshipper_phone())
                .setSplitUp(order.isSplit_up())
                .setBuyerCancelReason(order.getBuyer_cancel_reason())
                .setCancelBy(order.getCancel_by())
                .setCancelReason(order.getCancel_reason())
                .setActualShippingFeeConfirmed(order.isActual_shipping_fee_confirmed())
                .setFulfillmentFlag(order.getFulfillment_flag())
                .setPickupDoneTime(Instant.ofEpochSecond(order.getPickup_done_time()))
                .setInvoice(invoice)
                .setCheckoutShippingCarrier(order.getCheckout_shipping_carrier())
                .setReverseShippingFee(order.getReverse_shipping_fee())
                .setEscrow(finalEscrow)
                .createOrder();

        return result;
    }

    private static Order.RecipientAddress getAddress(GetOrderDetailOutput.RecipientAddress addr) {
        return new RecipientAddressBuilder()
                .setName(addr.getName())
                .setPhone(addr.getPhone())
                .setTown(addr.getTown())
                .setDistrict(addr.getDistrict())
                .setCity(addr.getCity())
                .setState(addr.getState())
                .setRegion(addr.getRegion())
                .setZipcode(addr.getZipcode())
                .setFullAddress(addr.getFull_address())
                .createRecipientAddress();
    }

    private static List<Order.Item> getItems(List<GetOrderDetailOutput.Item> items) {
        return items.stream()
                .map(item -> new ItemBuilder()
                        .setId(item.getItem_id())
                        .setName(item.getItem_name())
                        .setSku(item.getItem_sku())
                        .setModelId(item.getModel_id())
                        .setModelName(item.getModel_name())
                        .setModelSku(item.getModel_sku())
                        .setModelQuantityPurchased(item.getModel_quantity_purchased())
                        .setModelOriginalPrice(item.getModeL_original_price())
                        .setModelDiscountedPrice(item.getModel_discounted_price())
                        .setWholesale(item.isWholesale())
                        .setWeight(item.getWeight())
                        .setAddOnDeal(item.isAdd_on_deal())
                        .setMainItem(item.isMain_item())
                        .setAddOnDealId(item.getAdd_on_deal_id())
                        .setPromotionType(item.getPromotion_type())
                        .setPromotionId(item.getPromotion_id())
                        .setOrderItemId(item.getOrder_item_id())
                        .setPromotionGroupId(item.getPromotion_group_id())
                        .setImageInfo(new Order.ImageInfo(item.getImage_info().getImage_url()))
                        .createItem())
                .collect(Collectors.toList());
    }

    private static Order.Invoice getInvoice(GetOrderDetailOutput.Invoice invoice) {
        return new InvoiceBuilder()
                .setNumber(invoice.getNumber())
                .setSeriesNUmber(invoice.getSeries_number())
                .setAccessKey(invoice.getAccess_key())
                .setIssueDate(Instant.ofEpochSecond(invoice.getIssue_date()))
                .setTotalValue(invoice.getTotal_value())
                .setProductsTotalValue(invoice.getProducts_total_value())
                .setTaxCode(invoice.getTax_code())
                .createInvoice();
    }

    private static Order.Escrow getEscrow(GetEscrowDetailOutput.OrderIncome income) {
        return new EscrowBuilder()
                .setEscrowAmount(income.getEscrow_amount())
                .setBuyerTotalAmount(income.getBuyer_total_amount())
                .setOriginalPrice(income.getOriginal_price())
                .setSellerDiscount(income.getSeller_discount())
                .setShopeeDiscount(income.getShopee_discount())
                .setVoucherFromSeller(income.getVoucher_from_seller())
                .setCoins(income.getCoins())
                .setBuyerPaidShippingFee(income.getBuyer_paid_shipping_fee())
                .setBuyerTransactionFee(income.getBuyer_transaction_fee())
                .setCrossBorderTax(income.getCross_border_tax())
                .setPaymentPromotion(income.getPayment_promotion())
                .setCommissionFee(income.getCommission_fee())
                .setServiceFee(income.getService_fee())
                .setSellerTransactionFee(income.getSeller_transaction_fee())
                .setSellerLostCompensation(income.getSeller_lost_compensation())
                .setSellerCoinCashBack(income.getSeller_coin_cash_back())
                .setEscrowTax(income.getEscrow_tax())
                .setFinalShippingFee(income.getFinal_shipping_fee())
                .setActualShippingFee(income.getActual_shipping_fee())
                .setShopeeShippingRebate(income.getShopee_shipping_rebate())
                .setShippingFeeDiscountFrom3pl(income.getShipping_fee_discount_from_3pl())
                .setSellerShippingDiscount(income.getSeller_shipping_discount())
                .setEstimatedShippingFee(income.getEstimated_shipping_fee())
                .setVoucherCodes(income.getSeller_voucher_code())
                .setDrcAdjustableFund(income.getDrc_adjustable_refund())
                .setCostOfGoodsSold(income.getCost_of_goods_sold())
                .setOriginalCostOfGoodsSold(income.getOriginal_cost_of_goods_sold())
                .setOriginalSHopeeDiscount(income.getOriginal_shopee_discount())
                .setSellerReturnRefund(income.getSeller_return_refund())
                .setBuyerTotalAmountPri(income.getBuyer_total_amount_pri())
                .setOriginalPricePri(income.getOriginal_price_pri())
                .setSellerReturnRefundPri(income.getSeller_return_refund_pri())
                .setCommissionFeePri(income.getCommission_fee_pri())
                .setServiceFeePri(income.getService_fee_pri())
                .setDrcAdjustableRefundPri(income.getDrc_adjustable_refund_pri())
                .setPriCurrency(income.getPri_currency())
                .setAffCurrency(income.getAff_currency())
                .setExchangeRate(income.getExchange_rate())
                .setReverseShippingFee(income.getReverse_shipping_fee())
                .createEscrow();
    }

}
