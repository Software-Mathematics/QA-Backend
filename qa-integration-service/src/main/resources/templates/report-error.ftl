<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style nonce="${cspNonce}">
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 40px; }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 { color: #dc3545; font-size: 24px; }
        p { font-size: 16px; color: #333; }
    </style>
</head>
<body>
    <div class="container">
        <h1>${title}</h1>
        <p>${message}</p>
    </div>
</body>
</html>
