<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jingjing
  Date: 28/10/2021
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <p> Welcome </p>
    <a href = "/doLogout"> Logout </a>
    <p>Click the button to authorize geolocation</p>

    <p id="demo"></p>

    <button onclick="getCurrentLocation()">Get Location</button>

    <form:form modelAttribute="location" method="POST" action="/getLocation">
        <input type="hidden" name = "latitude" id="latitude" >
        <input type="hidden" name = "longitude" id="longitude" >
        <input type="hidden" name = "location_date" id="location_date" >
        <input type="submit" role="button" value="Submit">
    </form:form>

    <script>
        var x = document.getElementById("demo");
        function getCurrentLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                x.innerHTML = "Geolocation is not supported by this browser.";
            }
        }

        function showPosition(position) {
            document.getElementById("latitude").value = position.coords.latitude;
            document.getElementById("longitude").value = position.coords.longitude;
            console.log(position.coords.latitude);
            x.innerHTML = "Latitude: " + position.coords.latitude +
                "<br>Longitude: " + position.coords.longitude + "<br>Date: " + Date.now();
        }
    </script>
</body>
</html>
