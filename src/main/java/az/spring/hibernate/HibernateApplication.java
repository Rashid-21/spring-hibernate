package az.spring.hibernate;


import az.spring.hibernate.config.SpringHibernateConfig;
import az.spring.hibernate.dao.EmployeeDao;
import az.spring.hibernate.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class HibernateApplication {
    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringHibernateConfig.class);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans/spring-beans.xml");

        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);
//        Employee employee = new Employee();
//        employee.setName("Muhammad");
//        employee.setSurname("Alakbarov");
//        employee.setAge(18);
//        employee.setSalary(450);
//
//        employeeDao.save(employee);

//        employeeDao.delete(4);
//
        employeeDao.findAll()
                .forEach(System.out::println);

//        System.out.println(employeeDao.findByName("Rashid"));

    }
}
