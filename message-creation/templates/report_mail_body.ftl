<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body style="font-family: Arial, sans-serif; line-height: 1.6;">

    <p>Dear ${username},</p>

    <p>The QA team has completed testing. Please find the details below:</p>

    <ul>
        <li><strong>Transaction ID:</strong> ${transactionId}</li>
        <li><strong>Project Name:</strong> ${projectName}</li>
        <li><strong>Test Status:</strong> ${recstatus}</li>
    </ul>
    <p>
            <a href="https://investment-new.softwaremathematics.com/view-report/report?transactionId=${transactionId}"
               style="display: inline-block;
                      padding: 6px 12px;
                      background-color: #007BFF;
                      color: #ffffff;
                      font-size: 14px;
                      text-decoration: none;
                      border-radius: 4px;
                      margin-top: 10px;">
                View Report
            </a>
        </p>

    <p>Please review the report for further details.</p>

    <p>Best regards,<br/>
    QA Team</p>

</body>
</html>