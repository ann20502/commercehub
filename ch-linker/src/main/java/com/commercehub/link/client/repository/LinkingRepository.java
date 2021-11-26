package com.commercehub.link.client.repository;

public interface LinkingRepository {

    Linking get(String id);

    Linking get(String platform, String partnerId, String shopId);

    boolean insertOrUpdateIfExist(Linking linking);

    boolean disable(String documentId);

}
