package com.commercehub.link.client.repository;

public interface LinkingRequestRepository {

    LinkingRequest add(LinkingRequest linkingRequest);

    LinkingRequest get(String id);

}
