package org.jeyam.samson.consumer.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jeyam.samson.consumer.domain.Student;
import org.jeyam.samson.consumer.service.IStudentService;
import org.jeyam.samson.consumer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HomePage extends WebPage
{
    @SpringBean
    private StudentService studentService;
    private static final long serialVersionUID = 1L;
    private Student student = new Student();

    public HomePage(final PageParameters parameters)
    {
        super(parameters);

        add(createForm());
        //add(createBackNavigationLink());
    }

    private Form createForm()
    {
        Form form = new Form("form", new CompoundPropertyModel<Student>(student));
        form.add(createNameField());
        form.add(createAgeField());
        form.add(createSaveChangesButton());
        return form;
    }

    // TODO Add your page's components here

    private TextField<String> createNameField()
    {
        return new TextField<String>("firstName");
    }

    private TextField<String> createAgeField()
    {
        return new TextField<String>("age");
    }

    private Button createSaveChangesButton()
    {
        return new Button("saveButton")
        {
            @Override
            public void onSubmit()
            {
                studentService.create(student);
            }
        };
    }
}
