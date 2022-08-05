package com.cspl.integration.dao;

import com.cspl.integration.data.models.AuditEntity;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Common Database operation
 * @author Ashutosh
 */
public class CommonDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void writeConcern() {
        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    public <T> void save(T entityToSave) {
        if (entityToSave != null) {
            mongoTemplate.save(entityToSave);
        }
    }

    public <T> T findOne(Query query, Class<T> entityClass) {
        return mongoTemplate.findOne(query, entityClass);
    }

    public <T> T findOne(Map<String, Object> params, Class<T> entityClass) {
        Query query = new Query();
        if (params == null || params.isEmpty()) {
            return null;
        } else {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
            }
        }
        return mongoTemplate.findOne(query, entityClass);
    }

    public <T extends AuditEntity> T saveAndGet(T entityToSave, Class<T> clazz) {

        if (entityToSave != null) {
            entityToSave = mongoTemplate.save(entityToSave);
            Query query = new Query().addCriteria(Criteria.where("_id")
                    .is(entityToSave.getId()));
            // to get the updated data.
            return findOne(query, clazz);
        }
        return null;
    }
}
