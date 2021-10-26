package com.example.Covid_Alert.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name="autorities")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Autorities {
    private String username;
    private String autority;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authority_id;

    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public Long getAuthority_id() {
        return authority_id;
    }
}
