<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Upload manga</title>
</head>
<body>
<form action="/upload_file" method="post" enctype="multipart/form-data">
    <input type="text" name="manga_name" placeholder="Manga name"><br>
    <input type="text" name="chapter_name" placeholder="Chapter name"><br>
    <input type="text" name="chapter_number" placeholder="Chapter number"><br>
    <input type="file" name="file" multiple placeholder="Manga files"/>
    <input type="submit" value="Send">
</form>
</body>
</html>