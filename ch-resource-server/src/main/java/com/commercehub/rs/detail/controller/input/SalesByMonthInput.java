package com.commercehub.rs.detail.controller.input;

import com.commercehub.rs.core.usecase.GetSale;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Issues:
 * 1. The isValid() returning status 500
 * 2. This class has to be an independent class, cannot be nested in Controller
 *
 */
@Deprecated
public class SalesByMonthInput {

    @Pattern(regexp = GetSale.REGEX_GROUP)
    @PathParam("groupBy")
    private String groupBy;

    @NotBlank
    @PathParam("shopId")
    private String shopId;

    @NotBlank
    @QueryParam("from")
    private String from;

    @NotBlank
    @QueryParam("to")
    private String to;

    @NotBlank
    @QueryParam("zone")
    private String zone;

    // This is returning status 500 ...
    @AssertTrue()
    boolean isValid() {
        return false;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public String getShopId() {
        return shopId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getZone() {
        return zone;
    }

}
