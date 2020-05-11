<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
Welcome to the root page, you must be authenticated to be there :)<br>
Hello, ${user} <br>
${content}
<form action="/profile" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label> Save in session
        <input type="text" name="content">
    </label> <input type="submit">
</form>
<form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Logout">
</form>
</body>
</html>