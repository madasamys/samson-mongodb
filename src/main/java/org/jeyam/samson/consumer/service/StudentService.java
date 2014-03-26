package org.jeyam.samson.consumer.service;

import org.jeyam.samson.consumer.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import java.util.List;

/**
 * @author: madasamy
 * @version: 0.1
 */
@Repository
@Named
public class StudentService //implements IStudentService
{
    static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    MongoTemplate mongoTemplate;

//    @Override
    public void create(Student student)
    {
        mongoTemplate.insert(student);
    }

//    @Override
    public void update(Query query, Update update)
    {
        mongoTemplate.updateFirst(query, update, Student.class);
    }

//    @Override
    public List<Student> findAll()
    {
        List<Student> students = mongoTemplate.findAll(Student.class);
        logger.debug("Student: {}", students);
        return students;
    }

//    @Override
    public void delete(Student student)
    {
        mongoTemplate.remove(student);
    }

//    @Override
    public void deleteAll()
    {
        Query searchUserQuery = new Query(Criteria.where("age").gt(0));
        mongoTemplate.remove(searchUserQuery, Student.class);
    }
}
