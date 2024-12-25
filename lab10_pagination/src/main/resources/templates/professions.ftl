<!DOCTYPE html>
<html lang="en">
<head>
    <title>professions</title>
    <meta charset="utf-8">
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            height: 200vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .header-image {
            width: 100%;
            max-width: 800px;
            margin: 20px auto;
        }

        table {
            border-collapse: collapse;
            width: 90%;
            text-align: left;
            background-color: #f8bddc;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #f16aa6;
            padding: 8px;
        }

        th {
            background-color: #f46ab3;
            font-weight: bold;
        }

        caption {
            font-size: 1.5em;
            margin-bottom: 10px;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f19dcf;
        }
    </style>
</head>
<body>

<div>

    <table>
        <caption>professions</caption>
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        <#list profession_list as profession>
            <tr>
                <td>${profession.id()}</td>
                <td>${profession.name()}</td>
            </tr>
        </#list>
    </table>

</div>
<div>
    <th>
        <form action="/professions" method="GET" style="position: fixed; top: 50%; left: 10px; transform: translateY(-50%);">
            <input type="hidden" name="page" value="${page - 1}">
            <button type="submit" class="navigation-button prev-button">❮ prev</button>
        </form>
    </th>
    <th>
        <form action="/professions" method="GET" style="position: fixed; top: 50%; right: 10px; transform: translateY(-50%);">
            <input type="hidden" name="page" value="${page + 1}">
            <button type="submit" class="navigation-button next-button">next ❯</button>
        </form>
    </th>
</div>
</body>
</html>
