<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <% if(request.getParameter("error") != null) { %>
    <div class="errblock">Invalid Username and Password</div>
    <% } %>
    <% if(request.getParameter("logout") != null) { %>
    <div class="alert alert-success" role="alert">
        Logout was successful
    </div>
    <% } %>
    <form:form action="/doLogin" method="POST">
        <label for="username">User name: </label>
        <input type="text" id="username" name="username"/>
        <br/>
        <label for="password">Mot de passe</label>
        <input type="password" id="password" name="password"/>
        <label>Remember me:
            <input type="checkbox" name="remember-me">
        </label>
        <br/>
        <input type="submit" value="Login" role="button" class="btn btn-lg btn-primary" />
    </form:form>
    <div>
        <a href=""> Forgot password</a>
    </div>
</div>
</body>
</html>
