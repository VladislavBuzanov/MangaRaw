<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script>
    function sendMessage(pageId, userId, text) {
        let body = {
            pageId: pageId,
            text: text,
            userId: userId
        };

        $.ajax({
            url: "/messages",
            method: "POST",
            data: JSON.stringify(body),
            contentType: "application/json",
            dataType: "json",
            complete: function () {
                if (text === 'Login') {
                    receiveMessage(pageId)
                }
            }
        });
    }

    // LONG POLLING
    function receiveMessage(pageId) {
        $.ajax({
            url: "/messages?pageId=" + pageId,
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log(response)
                $('#messages').first().append('<tr> <td>' + response[0]['user']['userId'] + '</td><td>' + response[0]['message'] + '</td></tr>');
                receiveMessage(pageId);
            }
        })
    }</script>
<body onload="sendMessage('${pageId}','${userId}','Login')">
<h1>Chat id - ${pageId}</h1>
<div>
    <input id="message" placeholder="Your message">
    <button onclick="sendMessage('${pageId}', ${userId},
            $('#message').val())">SEND
    </button>
</div>
<div>
    <table id="messages">
        <tr>
            <td>User</td>
            <td>Message</td>
        </tr>
        <#list history as message>
            <tr>
                <td>${message.user.getId}</td>
                <td>${message.message}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>