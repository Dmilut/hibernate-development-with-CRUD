package com.dmilut.hibernateCRUD.DAO;

import com.dmilut.hibernateCRUD.HibernateUtil;
import com.dmilut.hibernateCRUD.model.Employee;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static Session session;
    private static Transaction transaction;

    public final static Logger logger = Logger.getLogger(EmployeeDAO.class);

    public static void create(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(employee);
            transaction.commit();
            logger.info("Successfully Created Record In The Database!");
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public static List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            for (Object oneObject : session.createQuery("FROM Employee").getResultList()) {
                employees.add((Employee) oneObject);
            }
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        return employees;
    }

    public static void update(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employee tmpEmployee = session.get(Employee.class, employee.getId());
            tmpEmployee.setFirstName(employee.getFirstName());
            tmpEmployee.setLastName(employee.getLastName());
            tmpEmployee.setCompany(employee.getCompany());

            transaction.commit();
            logger.info("\nEmployee With Id= " + employee.getId() + " Is Successfully Updated In The Database!\n");
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public static void delete(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(employee);
            transaction.commit();
            logger.info("\nEmployee With Id= " + employee.getId() + " Is Successfully Deleted From The Database!\n");
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public static Employee findById(int id) {
        Employee employee = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            employee = session.load(Employee.class, id);
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        return employee;
    }

    public static void deleteAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("DELETE FROM Employee");
            query.executeUpdate();

            transaction.commit();
            logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch (Exception exception) {
            if (transaction != null) {
                logger.error("Transaction Is Being Rolled Back!");
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
}