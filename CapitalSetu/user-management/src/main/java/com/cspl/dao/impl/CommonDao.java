package com.cspl.dao.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import com.cspl.models.AuditEntity;
import com.cspl.models.Counters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.WriteConcern;

@Component
public class CommonDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    public void writeConcern() {
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
    }

    private static final Long COLLECTION_COUNTER = 1L;

    public <T> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    public <T> void save(T entityToSave) {
        if (entityToSave != null) {
            mongoTemplate.save(entityToSave);
        }
    }

    public <T> T saveAndGet(T entityToSave) {
        if (entityToSave != null) {
            return mongoTemplate.save(entityToSave);
        }
        return null;
    }
    
    public <T> T findOne(Query query, Class<T> entityClass) {
        return mongoTemplate.findOne(query, entityClass);
    }

    public <T> T findOne(Map<String, Object> params, Class<T> entityClass) {
        Query query = new Query();
        if (params == null || params.isEmpty()) {
            return null;
        } else {
            for (Entry<String, Object> entry : params.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        return mongoTemplate.findOne(query, entityClass);
    }

    public synchronized <T> Long getNextSequenceId(String collectionName, Class<T> entityClass) {
        if (null == collectionName || collectionName.isEmpty()) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("name", collectionName);
        Counters counter = findOne(params, Counters.class);
        if (counter != null) {
            if (null != counter.getSeqId() && counter.getSeqId() > 0) {
                counter.setSeqId(counter.getSeqId() + 1);
            } else {
                counter.setSeqId(COLLECTION_COUNTER);
            }
        } else {
            counter = new Counters();
            counter.setSeqId(COLLECTION_COUNTER);
            counter.setName(collectionName);
        }
        mongoTemplate.save(counter);
        return counter.getSeqId();
    }

    public <T> T update(Map<String, Object> inputQuery, Map<String, Object> data, Class<T> entityClass) {
        Query query = new Query();
        for (Entry<String, Object> entry : inputQuery.entrySet()) {
            query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
        }
        Update update = new Update();
        for (Entry<String, Object> entry : data.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        update.set("lastModifiedDate", LocalDateTime.now());
        return mongoTemplate.findAndModify(query, update, entityClass);
    }

}
