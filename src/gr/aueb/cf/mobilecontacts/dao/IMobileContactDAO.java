package gr.aueb.cf.mobilecontacts.dao;

import gr.aueb.cf.mobilecontacts.model.MobileContact;

import java.util.List;

/**
 * DAO = Data access objects - Κάνουμε ένα Public API
 * το οποίο έχει Public μεθόδους
 * CRUD ( INSERT UPDATE DELETE SELECT )
 */
public interface IMobileContactDAO {

    // Βασικά (INSERT, UPDATE, DELETE, SELECT) - trivial
    MobileContact insert(MobileContact mobileContact);
    MobileContact update(Long id, MobileContact mobileContact);
    void deleteById(Long id);
    MobileContact getById(Long id);
    List<MobileContact> getAll();  // Το List είναι ένα interface (Το ArrayList είναι κλάση) το οποίο λειτουργεί ως container ενός πίνακα

    void deleteByPhoneNumber(String phoneNumber);

    // Queries
    MobileContact getByPhoneNumber(String phoneNumber);
    boolean userIdExists(Long id);
    boolean phoneNumberExists(String phoneNumber);
    

}
