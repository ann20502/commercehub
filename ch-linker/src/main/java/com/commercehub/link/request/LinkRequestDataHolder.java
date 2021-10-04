package com.commercehub.link.request;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LinkRequestDataHolder {

    private String client = "";

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
