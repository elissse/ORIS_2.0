<html xmlns="">
<head>
    <title> welcome!</title>
    <meta charset="utf-8\">

</head>
<body>
<h1>${title_page!}</h1>

<h3>list of users</h3>
<div>
    <table>
        <caption>list of users</caption>
        <th>Id</th> <th> name</th>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>