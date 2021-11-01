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

    <form:form modelAttribute="location" method="POST" action="/getLocation">
        <input type="hidden" name = "latitude" id="latitude" >
        <input type="hidden" name = "longitude" id="longitude" >
        <input type="hidden" name = "location_date" id="location_date" >
        <input onclick="getLocation()" type="submit" role="button" value="Get Location">
    </form:form>

    <script>
        var x = document.getElementById("demo");
        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                x.innerHTML = "Geolocation is not supported by this browser.";
            }
        }

        function showPosition(position) {
            x.innerHTML = "Latitude: " + position.coords.latitude +
                "<br>Longitude: " + position.coords.longitude + Date.now();
        }
    </script>
</body>
</html>
