<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>find professions</title>
</head>
<body>

<div class="main">
    <h2 class="form-title">find professions</h2>
    <form name = "professions" method="get" action="professions">
        <div class="form-group">
            <label for="profession"></label> <input
                type="text" name="profession" id=profession placeholder="profession"/>
        </div>
        <div class="form-group form-button">
            <input type="submit" name="find" id="find"
                   class="form-submit" value="find"/>
        </div>
    </form>
</div>
</body>
</html>