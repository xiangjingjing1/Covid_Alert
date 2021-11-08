package com.example.Covid_Alert.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity(name="verificationtoken")
@Access(AccessType.FIELD)
public class VerificationToken {
    public static final int EXPIRATION = 60 * 24; // nb min
    @Id
    private String token;
    private String username;
    private Date expirydate;

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeInMinutes);
        return cal.getTime();
    }
    // + getters and setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpiryDate() {
        return expirydate;
    }

    public void setExpiryDate(Date expirydate) {
        this.expirydate = expirydate;
    }
}
