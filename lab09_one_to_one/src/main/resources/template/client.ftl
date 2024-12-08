<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Welcome!</title>
    <meta charset="utf-8"/>
    <link rel='stylesheet' href='/lab09_one_to_one_war/static/css/lab07.css'>
</head>
<body>
<h1>hi new client</h1>

<form action="/lab09_war/index" method="post">
    <div>
        <label>name</label>
        <input type="text" name="name" id = "name">
    </div>
    <div>
        <label>email</label>
        <input type="text" name="email" id="email">
    </div>
    <div>
        <label>client info</label>
        <input type="text" name="clientInfo" id="clientInfo">
    </div>
    <div>
        <label>phone</label>
        <input type="text" name="phone" id="phone">
    </div>
    <div>
        <label>address</label>
        <input type="text" name="address" id="address">
    </div>
    <div>
        <label>passport</label>
        <input type="text" name="passport" id="passport">
    </div>
    <div>
        <input type="submit" value="save">
    </div>
</form>

</body>
</html>