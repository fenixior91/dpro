package com.dpro.domains;

import java.util.Date;

public class User {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String email;
    private Date dateOfBirth;
    private String pesel;
    
    /**
     * @return identyfikator użytkownika
     */
    public long getId() {
        return id;
    }

    /**
     * @return nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return hasło
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return wartość logiczna, informująca, czy użytkownik jest aktywny
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @return adres e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return imię
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return nazwisko
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return data urodzenia
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return pesel
     */
    public String getPesel() {
        return pesel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Username: %s, Password: %s, First Name: %s, Last Name: %s, Enabled: %s, Email: %s, Date Of Birth: %s, PESEL: %s",
                getId(), getUsername(), getPassword(), getFirstName(), getLastName(), isEnabled(), getEmail(), getDateOfBirth(), getPesel());
    }
}
