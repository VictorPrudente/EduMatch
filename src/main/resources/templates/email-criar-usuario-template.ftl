<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-mail Educacional</title>
    <style>
        /* Reset CSS */
        body, html {
margin: 0;
padding: 0;
display: flex;
justify-content: center;
align-items: center;
height: 100%;
}

        body {
font-family: Arial, sans-serif;
background-color: #f8f8f8;
}

.container {
max-width: 600px;
padding: 20px;
background-color: #fff;
border-radius: 10px;
box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
display: flex;
flex-direction: column;
align-items: center;
justify-content: center;
text-align: center;
}

.header {
margin-bottom: 20px;
}

.header h1 {
font-size: 36px;
color: #007bff;
margin: 0;
}

.content {
padding: 20px;
border-top: 2px solid #007bff;
border-bottom: 2px solid #007bff;
}

.content p {
font-size: 18px;
color: #333;
margin: 0 0 15px 0;
}

.cta-btn {
display: inline-block;
padding: 10px 20px;
background-color: #007bff;
color: #fff;
text-decoration: none;
border-radius: 5px;
}

.footer {
margin-top: 20px;
color: #666;
}

.footer p {
font-size: 14px;
}
</style>
</head>
<body>
<div class="container">
        <div class="header">
            <h1>EduMatch</h1>
        </div>
        <div class="content">
            <p>Olá <strong>${nome}!</strong>,</p>
            <p>Seu cadastro foi realizado com sucesso. Estamos felizes em ter você em nosso sistema!</p>
            <p>Seu identificador é ${id}</p>
            <h3>Qualquer dúvida, contactar o suporte pelo e-mail abaixo: </h3>
            <a href="#" class="cta-btn"> ${email}</a>
        </div>
        <div class="footer">
            <p>&copy; 2024 EduMatch. Todos os direitos reservados.</p>
        </div>
    </div>
</body>
</html>