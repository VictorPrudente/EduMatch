--INSERT ENDERECO
INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Presidente Vargas', 123, 'Chácara', '12345-678', 'Porto Alegre', 'RS', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Pedro', 456, 'Bloco a', '23456-789', 'Rio de Janeiro', 'RJ', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Brasil', 789, 'Apto 4', '34567-890', 'Caxias Do Sul', 'RS', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Sete de Setembro', 101, 'Casa da frente', '45678-901', 'Torres', 'RS', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Amazonas', 112, 'Casa 3', '56789-012', 'Novo Hamburgo', 'RS', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Cabo Frio', 223, 'Fundos', '67890-123', 'São Paulo', 'SP', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Parana', 334, 'Casa A2', '78901-234', 'Feira de Santana', 'BA', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Jorge', 445, 'Beco', '89012-345', 'Salvador', 'BA', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Assis Brasil', 556, 'Apto 10', '90123-456', 'Ilhéus', 'BA', 'Brasil');

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua 20 de Novembro', 641, 'Apto 2', '12836-348', 'Porto Alegre', 'RS', 'Brasil');

-- INSERT EMPRESA
INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TechSol','Tecnologia','12345678900010','Desenvolvimento de software', 'PUBLICA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EcoBio','Meio Ambiente','98765432100010','Reciclagem e preservação', 'PRIVADA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'SaúdeVital','Saúde','78901234500010','Serviços de saúde e cuidados', 'PUBLICA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'AgroTech','Agricultura','34567890100010','Desenvolvimento de tecnologias agrícolas', 'PUBLICA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ConstruPrime','Construção','01234567800010','Construção civil e infraestrutura', 'PRIVADA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ModaStyle','Moda','56789012300010','Comércio de roupas e acessórios', 'PRIVADA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EducaTech','Educação','23456789000010','Tecnologias educacionais', 'PRIVADA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600010','Energia solar sustentável', 'PUBLICA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600011','Energia solar sustentável', 'PRIVADA');

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TransLog','Logística','45678901200010','Serviços de logística', 'PUBLICA');

--ESCOLA

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Davi Canabarro', 'PRIVADA', 12345678901234);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Esola Estacio', 'PUBLICA', 11236589871236);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Educação', 'PUBLICA', 98756987456932);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Manoel Garcia', 'PUBLICA', 98745632589874);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola São José', 'PRIVADA', 99987986932156);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Ensinando', 'PRIVADA', 98789878523654);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Aprendendo mais', 'PUBLICA', 32659878596321);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola Garcia Oliveira', 'PRIVADA', 98789875987458);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola João Alves', 'PRIVADA', 99653698569875);

INSERT INTO VS_13_EQUIPE_9.ESCOLA (id_escola, nome, tipo, cnpj)
VALUES ('Escola do Bom Fim', 'PUBLICA', 99874589651523);

--MATEMATICA

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, C, FACIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, E, FACIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, B, FACIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, E, MEDIO);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, A, MEDIO);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, B, MEDIO);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, A, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, A, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, A, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.MATEMATICA (id_matematica, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, C, FACIL);

--PORTUGUES

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, C, FACIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, A, FACIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, A, FACIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, A, MEDIO);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, A, MEDIO);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, A, MEDIO);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, C, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, A, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, A, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.PORTUGUES (id_portugues, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, A, FACIL);

--SOFTSKILLS

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (1, 1, E, FACIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (2, 1, B, FACIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (3, 1, B, FACIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (4, 3, C, MEDIO);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (5, 3, B, MEDIO);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (6, 3, B, MEDIO);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (7, 5, C, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (8, 5, C, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (9, 5, C, DIFICIL);

INSERT INTO VS_13_EQUIPE_9.SOFT_SKILLS (id_soft_skills, pergunta, pontos, opcao_correta, dificuldade)
VALUES (10, 1, C, FACIL);