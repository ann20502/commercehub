package com.commercehub.linker.detail.repository;

import com.commercehub.common.TimedTaskUtils;
import com.commercehub.linker.core.repository.TimedTaskGroupRepository;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Dependent
public class FSTimedTaskGroupRepositoryInteractor implements TimedTaskGroupRepository {

    @Inject
    Firestore firestore;

    @Override
    public List<String> getAllTaskGroup() {
        return StreamSupport.stream(firestore.listCollections().spliterator(), false)
                .map(CollectionReference::getId)
                .filter(id -> id.startsWith(TimedTaskUtils.COLLECTION_PREFIX))
                .collect(Collectors.toList());
    }

}
