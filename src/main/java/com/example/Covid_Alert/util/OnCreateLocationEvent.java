package com.example.Covid_Alert.util;

import com.example.Covid_Alert.models.Location;
import org.springframework.context.ApplicationEvent;

public class OnCreateLocationEvent extends ApplicationEvent {
    private String appUrl; private Location location;
    public OnCreateLocationEvent(String appUrl, Location location) {
        super(location);
        this.appUrl = appUrl;
        this.location = location;
    }
    public String getAppUrl() {
        return appUrl;
    }
    public Location getLocation() {
        return location;
    }
}
