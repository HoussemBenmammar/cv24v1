package fr.univrouen.cv24v1.service;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import fr.univrouen.cv24v1.models.DatabaseSequence;

public class SequenceGeneratorService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public int generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        DatabaseSequence counter = mongoTemplate.findAndModify(query, update, options, DatabaseSequence.class);
        return counter != null ? counter.getSeq() : 1;
    }
}
