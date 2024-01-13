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
INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Davi Canabarro', 1, 12345678901234);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Esola Estacio', 0, 11236589871236);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Educação', 0, 98756987456932);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Manoel Garcia', 0, 98745632589874);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola São José', 1, 99987986932156);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Ensinando', 1, 98789878523654);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Aprendendo mais', 0, 32659878596321);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Garcia Oliveira', 1, 98789875987458);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola João Alves', 1, 99653698569875);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola do Bom Fim', 0, 99874589651523);

   
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


--Insert Matematica
INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, C, 0);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, E, 0);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, B, 0);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, E, 1);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, A, 1);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, B, 1);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, A, 2);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, A, 2);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, A, 2);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, C, 0);


--Insert Português
INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, C, 0);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, A, 0);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, A, 0);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, A, 1);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, A, 1);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, A, 1);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, C, 2);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, A, 2);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, A, 2);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, A, 0);


--Insert SoftSkills
INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, E, 0);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, B, 0);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, B, 0);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, C, 1);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, B, 1);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, B, 1);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, C, 2);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, C, 2);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, C, 2);

INSERT INTO VS_13_EQUIPE_9.SOFTSKILLS (id_softskills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, C, 0);

-- Insert Contatos

--Contatos Empresa
INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL,'Administrativo','513262-1298', 0, NULL, 1, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Vendas', '513262-2345', 1, NULL, 2, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Suporte', '513262-3456', 2, NULL, 3, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Informações', '513262-4567', 0, NULL, 4, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Emergência', '513262-5678', 1, NULL, 5, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Financeiro', '513262-6789', 2, NULL, 6, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Atendimento', '513262-7890', 0, NULL, 7, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Recursos Humanos', '513262-8901', 1, NULL, 8, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Compras', '513262-9012', 2, NULL, 9, NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Marketing', '513262-0123', 0, NULL, 10, NULL);

--Contatos Usuario
INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Casa', '5198956-0457', 2, 1, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Casa', '5199874-7896', 0, 2, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Trabalho', '5198765-4321', 1, 3, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Celular', '5196543-2109', 2, 4, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5198765-0987', 0, 5, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Casa', '5196543-5678', 1, 6, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Celular', '5197654-3210', 2, 7, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Trabalho', '5198765-4321', 0, 8, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Casa', '5196543-2109', 1, 9, NULL,NULL);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5198765-0987', 2, 10, NULL,NULL);

--Contatos Escola
INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5198765-0987', 2, NULL, NULL,1);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5198765-0987', 0, NULL, NULL, 2);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Telefone', '5191234-5678', 1, NULL, NULL, 3);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Celular', '5187654-3210', 2, NULL, NULL, 4);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Telefone', '5198456-9876', 0, NULL, NULL, 5);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Celular', '5198754-5674', 1, NULL, NULL, 6);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5199293-9981', 2, NULL, NULL, 7);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Whatsapp', '5198234-5469', 0, NULL, NULL, 8);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Telefone', '5198127-0119', 1, NULL, NULL, 9);

INSERT INTO CONTATO (id_contato, descricao, telefone, tipo_contato, id_usuario, id_empresa, id_escola)
VALUES (SEQ_CONTATO.NEXTVAL, 'Celular', '5198289-5689', 2, NULL, NULL, 10);






