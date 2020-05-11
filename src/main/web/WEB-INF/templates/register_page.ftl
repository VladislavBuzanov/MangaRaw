<#ftl encoding="UTF-8"/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RawManga</title>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="../static/css/Registration-Form-with-Photo.css">
    <link rel="stylesheet" href="../static/css/styles.css">
</head>

<body>
<#--<#if error??>
    <#list error as e>
        <@spring.message code="${e}" />
    </#list>
<#else>
</#if>-->
<div class="register-photo">
    <div class="form-container">
        <@spring.message code="signUp"/>
        <@spring.bind "signUpDto"/>
        <form method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <@spring.formInput "signUpDto.login"/>
            <@spring.formInput "signUpDto.email"/>
            <@spring.formPasswordInput "signUpDto.password"/>
            <@spring.showErrors ""/>
            <button class="btn btn-primary btn-block" type="submit">Sign Up</button>
            <a href="/login" class="already">You already have an account? Login here.</a></form>
    </div>
</div>
<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
</body>

</html>