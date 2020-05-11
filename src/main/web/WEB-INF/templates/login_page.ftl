<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Page</title>
</head>
<body>
<h1>Login</h1><br>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label>Login:
        <input type="text" name="login">
    </label>
    <label>Password:
        <input type="password" name="password">
    </label>
    <br>
    <label> Remember me
        <input name="remember-me" type="checkbox">
    </label>
    <input type="submit" value="Login">
</form>
</body>
</html>