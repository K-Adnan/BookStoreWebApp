package ka.bookstorewebapp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REJECTEDUSERS")
@Data
public class RejectedUser {

    @Id
    @GeneratedValue
    private int id;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String reasonForRejection;

    public RejectedUser() {
    }

}

