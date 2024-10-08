package com.komanrudden.multiauthapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a user in the system.
 * <p>
 * This class is mapped to the "users" table in the database.
 * </p>
 *
 * <p>Fields:</p>
 * <ul>
 *   <li><b>id</b>: The unique identifier for the user, generated automatically.</li>
 *   <li><b>email</b>: The email address of the user.</li>
 *   <li><b>firstname</b>: The first name of the user.</li>
 *   <li><b>lastname</b>: The last name of the user.</li>
 *   <li><b>password</b>: The password of the user.</li>
 *   <li><b>mailSent</b>: A flag indicating whether a mail has been sent to the user.</li>
 * </ul>
 */
@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "mail_sent")
    private Boolean mailSent;

}
