package com.example.Covid_Alert.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name="authorities")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Authorities {
    private String username;
    private String authority;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authority_id;

    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public Long getAuthority_id() {
        return authority_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
