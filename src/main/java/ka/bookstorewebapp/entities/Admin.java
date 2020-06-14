package ka.bookstorewebapp.entities;

import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Admin")
@SuperBuilder(toBuilder=true)
public class Admin extends User {

    public Admin() {
    }

    public Admin(String userEmail, String firstName, String password, String lastName, String address, String phoneNumber) {
        super(userEmail, password, firstName, lastName, address, phoneNumber);
    }

    public void setEmailAddress() {
        setEmailAddress(getFirstName().toLowerCase() + "." + getLastName().toLowerCase() + "@books4u.com");
    }

}
