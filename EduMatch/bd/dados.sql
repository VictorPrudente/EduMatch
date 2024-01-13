--INSERT ENDERECO
INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Presidente Vargas', 123, 'Chácara', '12345678', 'Porto Alegre', 'RS', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Pedro', 456, 'Bloco a', '23456789', 'Rio de Janeiro', 'RJ', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Brasil', 789, 'Apto 4', '34567890', 'Caxias Do Sul', 'RS', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Sete de Setembro', 101, 'Casa da frente', '45678901', 'Torres', 'RS', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Amazonas', 112, 'Casa 3', '56789012', 'Novo Hamburgo', 'RS', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Cabo Frio', 223, 'Fundos', '67890123', 'São Paulo', 'SP', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Parana', 334, 'Casa A2', '78901234', 'Feira de Santana', 'BA', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Jorge', 445, 'Beco', '89012345', 'Salvador', 'BA', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Assis Brasil', 556, 'Apto 10', '90123456', 'Ilhéus', 'BA', 'Brasil');

INSERT INTO VS_13_EQUIPE_9.ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua 20 de Novembro', 641, 'Apto 2', '12836348', 'Porto Alegre', 'RS', 'Brasil');


-- INSERT EMPRESA
INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TechSol','Tecnologia','12345678900210','Desenvolvimento de software', 0);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EcoBio','Meio Ambiente','98765432100010','Reciclagem e preservação', 1);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'SaúdeVital','Saúde','78901234500010','Serviços de saúde e cuidados', 0);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'AgroTech','Agricultura','34567890100010','Desenvolvimento de tecnologias agrícolas', 0);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ConstruPrime','Construção','01234567800010','Construção civil e infraestrutura', 1);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ModaStyle','Moda','56789012300010','Comércio de roupas e acessórios', 1);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EducaTech','Educação','23456789000010','Tecnologias educacionais', 1);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600010','Energia solar sustentável', 0);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600011','Energia solar sustentável', 1);

INSERT INTO VS_13_EQUIPE_9.EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TransLog','Logística','45678901200010','Serviços de logística', 0);
   

-- Insert Escola


   
-- Insert Usuário
INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Pedro', 'Brown', '66337423046', 17, 18, 1, 1);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Lucio', 'Blue', '13395182010', 16, 35, 2, 2);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Marcos', 'Yellow', '04590223007', 17, 28, 3, 3);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Lucia', 'White', '02103473094', 19, 16, 4, 4);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Maria', 'Black', '45966305005', 15, 12, 5, 5);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Yolanda', 'Red', '59631001008', 18, 39, 6, 6);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Josefina', 'Purple', '09707645024', 17, 30, 7, 7);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Carlas', 'Gold', '57995620039', 16, 45, 8, 8);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'Leslie', 'Pink', '74140982063', 17, 38, 9, 9);

INSERT INTO VS_13_EQUIPE_9.USUARIO(id_usuario, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'John', 'Silver', '43830463049', 17, 40, 10, 10);
