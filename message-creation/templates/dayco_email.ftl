<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Station Report</title>
</head>
<body>
    <h1>Station Report</h1>
    <p>Here are the details for the requested station report:</p>

    <table border="1" cellpadding="10">
        <tr>
            <th>Station Name</th>
            <td>${stationname}</td>
        </tr>
        <tr>
            <th>Station Code</th>
            <td>${stationcode}</td>
        </tr>
        <tr>
            <th>Part No.</th>
            <td>${name}</td>
        </tr>
        <tr>
            <th>Transaction</th>
            <td>${itemcode}</td>
        </tr>
        <tr>
            <th>Record Status</th>
            <td>${recstatus}</td>
        </tr>
    </table>

    <p>Thank you,</p>
    <p>Team</p>
</body>
</html>
