package com.commercehub.link.client.shopee;

import com.commercehub.link.client.AuthorizationResponse;
import com.commercehub.link.client.TokenResponse;
import com.commercehub.link.client.TokenResponseHandler;
import com.commercehub.link.client.implementation.AuthorizationResponseDefault;
import com.commercehub.link.client.persistence.Linking;
import com.commercehub.link.client.persistence.LinkingShopee;
import com.commercehub.link.client.persistence.LinkingShopeeBuilder;
import com.commercehub.link.exception.LinkRuntimeException;
import com.commercehub.link.qualifier.LinkQualifier;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Dependent
@LinkQualifier("shopee")
public class TokenResponseHandlerShopee implements TokenResponseHandler {

    @Inject
    Firestore firestore;

    @Override
    public Uni<AuthorizationResponse> handle(Uni<TokenResponse> tokenResponse) {
        return tokenResponse
                .map(response -> (TokenResponseShopee) response)
                .map(response -> {
                    if ( !save(response) ) { throw new LinkRuntimeException("Failed to save token"); }
                    return getResponse(response);
                });
    }

    // TODO: Confirm if the transaction works ...
    private boolean save(TokenResponseShopee token) {
        try {
            final LinkingShopee linking = getLinking(token);
            final Query query = firestore.collection("linking").whereEqualTo("shopId", token.getShopId());
            return firestore.runTransaction(transaction -> {
                for (QueryDocumentSnapshot document : query.get().get().getDocuments()) {
                    document.getReference().delete();
                }

                Timestamp timestamp = firestore.collection("linking").document().set(linking).get().getUpdateTime();
                System.out.println("Linking [Shopee] added into firestore at " + timestamp.toString());
                return true;
            }).get();
        } catch(InterruptedException | ExecutionException ex) {
            System.out.println("Failed to save into firestore: " + ex.getMessage());
            throw new LinkRuntimeException("Failed to save into firestore: " + ex.getMessage());
        }
    }

    private LinkingShopee getLinking(TokenResponseShopee token) {
        return new LinkingShopeeBuilder(token.getShopId(), token.getShopName(), Linking.STATUS_ACTIVE)
                .setAccessToken(token.accessToken(), new Date(token.accessTokenExpiry()))
                .setRefreshToken(token.refreshToken(), null)
                .setShopStatus(token.getShopStatus())
                .setShopRegion(token.getShopRegion())
                .createLinkingShopee();
    }

    private AuthorizationResponse getResponse(TokenResponseShopee token) {
        return new AuthorizationResponseDefault(
                LinkClientShopee.NAME,
                token.accessToken(),
                token.accessTokenExpiry(),
                token.refreshToken(),
                token.refreshTokenExpiry()
        );
    }

}
