package gr.aueb.cf.mobilecontacts.model;

import java.util.Objects;

public class MobileContact extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String phoneNumber;

    public MobileContact() {

    }

    public MobileContact(String firstname, String lastname, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Firstname: " + firstname +", Lastname: " + lastname + ", Phone Number: " + phoneNumber;
    }



    @Override
    public boolean equals(Object other) {
        if (this == other) return true;                                        // Οι δείκτες δείχνουν στο ίδιο περιεχόμενο (this και other) άρα true είναι δηλαδή ίσα
        if (!(other instanceof MobileContact that)) return false;              // Αν το other δεν είναι MobileContact τότε δεν είναι ίδιου τύπου άρα false δεν είναι ίσα

        // Στη java 17 το παρακάτω κάνει δύο πράγματα
        // Έλεγχο αν είναι instanceof και αν ναι μετά κάνει
        // typecast (όπως κάναμε πριν τη java17 με τον παραπάνω κώδικα)
        //  if (!(other instanceof MobileContact that)) return false;
        return Objects.equals(getFirstname(), that.getFirstname())             // Objects είναι μια utility κλάση έχει ίδιο όνομα με το βασικό object πχ Object class , utility Objects, Array/Arrays, Collection/Collections  (static)
                && Objects.equals(getLastname(), that.getLastname())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber());    // Το other είναι το that.  Αν είναι ίσα όλα τα πεδία Πχ firstname με το firstname στο that ΚΑΙ && lastname && phone number τότε είναι ίσα
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getPhoneNumber());  // Κλάση της object παίρνει όλα τα πεδία και εξάγει αυτόματα έναν hashcode στο 16αδικό έναν int
    }
}
