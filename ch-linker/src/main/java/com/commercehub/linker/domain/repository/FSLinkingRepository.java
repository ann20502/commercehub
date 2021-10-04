package com.commercehub.linker.domain.repository;

import com.commercehub.linker.domain.entity.Linking;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Dependent
public class FSLinkingRepository implements LinkingRepository {

    @Inject
    Firestore firestore;

    @Override
    public List<Linking> getAll(String status) {
        try {
            final Query query = firestore.collection("linking").whereEqualTo("status", status);
            return query
                    .get().get().getDocuments()
                    .stream()
                    .map(snapshot -> snapshot.toObject(Linking.class))
                    .collect(Collectors.toList());
        } catch( InterruptedException | ExecutionException ex ) {
            System.out.println("Failed to retrieve linking: " + ex.getMessage());
        }

        return new ArrayList<>();
    }

}
