package com.dmilut.hibernateCRUD;

import com.dmilut.hibernateCRUD.DAO.EmployeeDAO;
import com.dmilut.hibernateCRUD.model.Employee;
import org.apache.log4j.Logger;

import java.util.List;

public class App {

    public final static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setFirstName("TestFirstName1");
        employee1.setLastName("TestLastName1");
        employee1.setCompany("TestCompany1");

        Employee employee2 = new Employee();
        employee2.setFirstName("TestFirstName2");
        employee2.setLastName("TestLastName2");
        employee2.setCompany("TestCompany2");

        Employee employee3 = new Employee();
        employee3.setFirstName("TestFirstName3");
        employee3.setLastName("TestLastName3");
        employee3.setCompany("TestCompany3");


        logger.info("\n\n=======CREATE RECORDS=======\n");
        EmployeeDAO.create(employee1);
        EmployeeDAO.create(employee2);
        EmployeeDAO.create(employee3);

        logger.info("\n\n=======READ RECORDS=======\n");
        List<Employee> employees = EmployeeDAO.findAll();

        if (employees != null & employees.size() > 0) {
            for (Object employee : employees) {
                logger.info(employee.toString());
            }
        }

        logger.info("\n\n=======UPDATE RECORDS=======\n");
        employee1.setCompany("TestCompany4");
        EmployeeDAO.update(employee1);

        employees = EmployeeDAO.findAll();
        if (employees != null & employees.size() > 0) {
            for (Object employee : employees) {
                logger.info(employee.toString());
            }
        }

        logger.info("\n\n=======DELETE RECORD=======\n");
        EmployeeDAO.delete(employee1);

        employees = EmployeeDAO.findAll();
        for (Object employee : employees) {
            logger.info(employee.toString());
        }

        logger.info("\n\n=======DELETE ALL RECORDS=======\n");
        EmployeeDAO.deleteAll();

        employees = EmployeeDAO.findAll();
        if (employees.size() == 0) {
            logger.info("\nNo Records Are Present In The Database Table!\n");
        }

        System.exit(0);
    }
}