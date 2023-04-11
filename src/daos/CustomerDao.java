package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;

public class CustomerDao implements Dao<Customer, Integer> {
    Connection connection;

// Create a connection with the database
    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

// Implementation of findAll function to find all customers in customer table
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<Customer>();
    try(Statement statement = connection.createStatement()) {
        ResultSet result = statement.executeQuery("SELECT * FROM customer");
        while(result.next()) {
            Customer customer = new Customer();
            customer.setId(result.getInt("id"));
            customer.setName(result.getString("name"));
            customer.setAddress(result.getString("address"));
            customer.setCity(result.getString("city"));
            customer.setState(result.getString("state"));
            customer.setZip(result.getInt("zip"));
            customers.add(customer);
        }
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
    return customers;
    }

// findById function to find a specific customer by their id
    public Customer findById(Integer pk) {
        Customer customer = new Customer();
        String select = "SELECT * FROM customer WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(select);) {
            ps.setInt(1, pk);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setState(result.getString("state"));
                customer.setZip(result.getInt("zip"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return customer;
    }

//  insert function to insert a new record into the table
    public void insert(Customer customer) {
        try (Statement statement = connection.createStatement()) {
            String insert = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getState());
            ps.setInt(6, customer.getZip());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                customer.setId(keys.getInt(1));
            }
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

// update function to update a record by id
    public Boolean update(Customer customer) {
        Boolean success = true;
        String update = "UPDATE customer SET address=? WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setString(1, customer.getAddress());
            ps.setInt(2, customer.getId());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
    }

// delete function to delete a record by id
    public Boolean delete(Integer pk) {
        Boolean success = false;
        String delete = "DELETE FROM customer WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(delete)) {
            ps.setInt(1, pk);
            ps.executeUpdate();
            success = true;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }
}
