import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import daos.CustomerDao;
import entities.Customer;
import entities.Database;

public class App {
    public static void main(String[] args) throws Exception {
       List<Customer> customerList;
       try (Connection connection = Database.getDatabaseConnection();
                Statement statement = connection.createStatement();) {
            CustomerDao customerDao = new CustomerDao(connection);

        // Calling the findall function after creating a customerDAO object
            customerList = customerDao.findAll();
            System.out.println("Printing customers: ");
            for (Customer customer : customerList) {
                System.out.println(customer);
            }    

        // Insert function to insert a new customer into the table
            Customer insertCustomer = new Customer();
            insertCustomer.setName("James");
            insertCustomer.setAddress("99 Privet Drive");
            insertCustomer.setCity("Knoxville");
            insertCustomer.setState("TN");
            insertCustomer.setZip(37798);
            customerDao.insert(insertCustomer);

        // findById function to find a specific customer by their primary key
            Customer foundCustomer = new Customer();
            foundCustomer.setId(4);
            foundCustomer = customerDao.findById(foundCustomer.getId());
            System.out.println("customer returned from findById(4): " + foundCustomer);
        
        // update function to update a record found by Id
            Customer updateCustomer = new Customer();
            updateCustomer.setId(5);
            updateCustomer.setAddress("45 Sunset Blvd");
            Boolean success = customerDao.update(updateCustomer);

        // delete function to remove a record from database by ID
            Customer deleteCustomer = new Customer();
            deleteCustomer.setId(5);
            Boolean deleteSuccess = customerDao.delete(deleteCustomer.getId());
        } 
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
