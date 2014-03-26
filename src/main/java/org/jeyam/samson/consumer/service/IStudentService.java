package org.jeyam.samson.consumer.service;

import org.jeyam.samson.consumer.domain.Student;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author: madasamy
 * @version: 0.1
 */
public interface IStudentService
{
    void create(Student student);

    void update(Query query, Update update);

    List<Student> findAll();

    void delete(Student student);

    void deleteAll();
}
