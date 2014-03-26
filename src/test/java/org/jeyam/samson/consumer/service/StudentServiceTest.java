package org.jeyam.samson.consumer.service;

import com.foursquare.fongo.Fongo;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import org.jeyam.samson.consumer.domain.Student;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;

/**
* @author: madasamy
* @version: 0.1
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration//(locations = "classpath:application-context.xml")
public class StudentServiceTest
{
    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");

    // nosql-unit requirement
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private StudentService studentService;

    @After
    public void tearDown()
    {
        studentService.deleteAll();
    }

    @Test
    public void testCreate()
    {
        System.out.println("--create--");
        Student student = new Student("sami", "jai", 34);
        studentService.create(student);
        assertEquals(student, studentService.findAll().get(0));
    }

    @Test
    public void testFindAll()
    {
        System.out.println("--findAll--");
        assertEquals(0, studentService.findAll().size());
        studentService.create(new Student("sami", "bar", 24));
        assertEquals(1, studentService.findAll().size());
    }

    @Test
    public void testUpdate()
    {
        System.out.println("--update--");
        studentService.create(new Student("ram", "kumar", 25));
        Query query = new Query(Criteria.where("age").is(25));
        Update update = Update.update("age", 28);
        studentService.update(query, update);
        assertEquals(28, studentService.findAll().get(0).getAge());
    }

    @Test
    public void testDelete()
    {
        System.out.println("--delete--");
        Student student = new Student("ram", "kumar", 25);
        studentService.create(student);
        assertEquals(1, studentService.findAll().size());
        studentService.delete(student);
        assertEquals(0, studentService.findAll().size());
    }



    @Configuration
    @EnableMongoRepositories
    @ComponentScan(basePackages = {"org.jeyam.samson.consumer.service"})
    // modified to not load configs from com.johnathanmarksmith.mongodb.example.MongoConfiguration
    @PropertySource("classpath:application-test.properties")
    static class PersonRepositoryTestConfiguration extends AbstractMongoConfiguration
    {

        @Override
        protected String getDatabaseName()
        {
            return "demo-test";
        }

        @Override
        public Mongo mongo()
        {
            // uses fongo for in-memory tests
            return new Fongo("mongo-test").getMongo();
        }

        @Override
        protected String getMappingBasePackage()
        {
            return "org.jeyam.samson.consumer.domain";
        }

    }
}
