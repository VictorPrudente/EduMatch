-- INSERT EMPRESA
INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TechSol','Tecnologia','12345678900210','Desenvolvimento de software', 0);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EcoBio','Meio Ambiente','98765432100010','Reciclagem e preservação', 1);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'SaúdeVital','Saúde','78901234500010','Serviços de saúde e cuidados', 0);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'AgroTech','Agricultura','34567890100010','Desenvolvimento de tecnologias agrícolas', 0);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ConstruPrime','Construção','01234567800010','Construção civil e infraestrutura', 1);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'ModaStyle','Moda','56789012300010','Comércio de roupas e acessórios', 1);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EducaTech','Educação','23456789000010','Tecnologias educacionais', 1);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600010','Energia solar sustentável', 0);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'EnergiSolar','Energia','89012345600011','Energia solar sustentável', 1);

INSERT INTO EMPRESA (id_empresa, nome, setor, cnpj, area_de_atuacao, tipo)
VALUES (SEQ_EMPRESA.NEXTVAL, 'TransLog','Logística','45678901200010','Serviços de logística', 0);


-- Insert Escola
INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Davi Canabarro', 1, 12345678901234);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Esola Estacio', 0, 11236589871236);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Educação', 0, 98756987456932);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Manoel Garcia', 0, 98745632589874);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola São José', 1, 99987986932156);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Ensinando', 1, 98789878523654);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Aprendendo mais', 0, 32659878596321);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola Garcia Oliveira', 1, 98789875987458);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola João Alves', 1, 99653698569875);

INSERT INTO ESCOLA (id_escola, nome, tipo, cnpj)
VALUES (SEQ_ESCOLA.NEXTVAL, 'Escola do Bom Fim', 0, 99874589651523);


-- Insert Usuário
INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'pedro@email.com', '7a8b2f91e4d56c0f3a2e8b1c09874d3e7890123456789abcdeffedcba9876543', 'Pedro', 'Brown', '66337423046', 17, 18, 1, 1);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'lucio@email.com', 'c6543210fedcba9876543210abcdef0123456789abcdef0123456789abcdef01', 'Lucio', 'Blue', '13395182010', 16, 35, 2, 2);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'marcos@email.com', '0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef0', 'Marcos', 'Yellow', '04590223007', 17, 28, 3, 3);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'lucia@email.com', 'a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2c3d4e5f6a1b2', 'Lucia', 'White', '02103473094', 19, 16, 4, 4);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'maria@email.com', '9a8b7c6d5e4f3a2b1c0d9e8f7a6b5c4d3e2f1a0b987654321098765432109876', 'Maria', 'Black', '45966305005', 15, 12, 5, 5);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'yolanda@email.com', '3e5f7a1b9c2d8f6a0b4c7d3e9f2a5b6c8d0e4f1a7b5c2d9e6f0a8b1c4d7e2f3', 'Yolanda', 'Red', '59631001008', 18, 39, 6, 6);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'josefina@email.com', '4c7a8e2f6b1d9c5a3f0e8b7d4a6f2c9b3e1d7a5b6c8d2f3a0b9e4c1d5', 'Josefina', 'Purple', '09707645024', 17, 30, 7, 7);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'carla@email.com', 'aabbccddeeff00112233445566778899aabbccddeeff00112233445566778899', 'Carla', 'Gold', '57995620039', 16, 45, 8, 8);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'leslie@email.com', 'e2f4a6c8d0b295f173c3e1d7a5b6c8d2f3a0b9e4c1d5', 'Leslie', 'Pink', '74140982063', 17, 38, 9, 9);

INSERT INTO USUARIO(id_usuario, email, senha, nome, sobrenome, cpf, idade, pontuacao, id_empresa, id_escola)
VALUES (SEQ_USUARIO.nextval, 'john@email.com', '6a4b8c2d7e0f3a5b1c9d4e8f2a6b7c0d1e9f5a3b2c4d6', 'John', 'Silver', '43830463049', 17, 40, 10, 10);


--Insert questao MATEMATICA
INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Para a excursão da escola, a escola alugou um ônibus que tem 29 lugares.
Somente 18 adolescentes confirmaram que irão na viagem.
Quantos lugares irão sobrar?

A) 10
B) 9
C) 11
D) 12
E) 8', 1, 'C', 1, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Ana esta de aniversário, ela convidou 17 meninos e 27 meninas.
Quantos convidados vai ter no total da festa?

A) 40
B) 34
C) 42
D) 37
E) 44', 1, 'E', 1, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Joao precisa de 15 cadernos.
Seu pai lhe deu 7 e sua mãe deu 6 cadernos.
Faltou quantos cadernos para o João?

A) 1
B) 2
C) 4
D) 3
E) 5', 1, 'B', 1, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Um triângulo retângulo tem as seguintes medidas:
Lado A: 6 cm
Lado B: 8 cm
Qual é o comprimento da hipotenusa (lado C) desse triângulo?

A) 10 cm
B) 12
C) 14
D) 15
E) 18', 1, 'B', 1, 2, 1);


INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o dobro de 27,5?

A) 61
B) 60
C) 51
D) 54
E) 55', 3, 'E', 2, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Quanto é 963 divido por 3?

A) 321
B) 431
C) 323
D) 421
E) 325', 3, 'A', 2, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o triplo de 562?

A) 1684
B) 1686
C) 1124
D) 1844
E) 1680', 3, 'B', 2, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o antecessor do número 81, multiplicado por 2?

A) 160
B) 162
C) 180
D) 145
E) 161', 5, 'A', 3, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o sucessor do número 101, dividido por 3?
A) 34
B) 52
C) 36
D) 50
E) 32', 5, 'A', 3, 2, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual a soma dos lados de 6 triangulos?

A) 18
B) 9
C) 16
D) 6
E) 3', 5, 'A', 3, 2, 1);


--Insert Português
INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o sinônimo da palavra "rápido"?

A) Devagar
B) Lento
C) Ágil
D) Manso
E) Estático', 1, 'C', 1, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o antônimo da palavra "alegre"?

A) Triste
B) Feliz
C) Radiante
D) Contente
E) Jubiloso', 1, 'A', 1, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o sinônimo da palavra "grande"?

A) Pequeno
B) Gigante
C) Largo
D) Longo
E) Curto', 1, 'B', 1, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o plural correto de "cidadão"?

A) Cidadãos
B) Cidadãoes
C) Cidadõeis
D) Cidadões
E) Cidadães', 3, 'A', 2, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o significado da expressão "a gota dágua"?

A) Algo pequeno e irrelevante
B) Uma grande conquista
C) Uma chuva intensa
D) Uma expressão de alegria
E) Uma demonstração de coragem', 3, 'A', 2, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o autor da obra "Dom Casmurro"?

A) Machado de Assis
B) José de Alencar
C) Lima Barreto
D) Graciliano Ramos
E) Monteiro Lobato', 3, 'A', 2, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Na frase "Aquele gesto revelou uma benevolência incomum", o que significa "benevolência"?

A) Maldade
B) Crueldade
C) Bondade
D) Arrogância
E) Desprezo', 5, 'C', 3, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Na frase "A tessitura da trama era intricada", o que significa "tessitura"?

A) Textura
B) Espessura
C) Altura
D) Largura
E) Densidade', 5, 'A', 3, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Qual é o significado da expressão "chover no molhado"?
A) Conseguir algo com facilidade
B) Lidar com um problema difícil
C) Estar sempre ocupado
D) Discutir algo desnecessário
E) Chorar por algo perdido', 5, 'D', 3, 1, 1);

INSERT INTO QUESTAO (id_questao, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.nextval, 'Identifique a alternativa em que o uso da vírgula está correto:

a) O carro novo é, bonito.
b) Na festa, encontramos amigos.
c) Ela comprou um, celular novo.
d) Eles foram ao cinema, ontem.
e) Gostamos de pizza, com bastante queijo.', 5, 'B', 3, 1, 1);


--Insert QUESTAO SOFTSKILLS
INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Qual habilidades é fundamental para um bom trabalho em equipe?
A) Falar alto
B) Chegar no horário
C) Ter mais conhecimento que o líder
D) Falar várias línguas
E) Empatia', 1, 'E', 1, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Como ser proativo no trabalho?
A) Fazer somente quando é solicitado
B) Tomar ações preventivas e antecipar problemas
C) Fazer o trabalho do colega
D) Não se propor a aprender novas funções
E) Realizar apenas tarefas fáceis
', 1, 'B', 1, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Qual habilidade é fundamental para uma boa comunicação?
A) Falar alto para ser ouvido por todos
B) Escutar ativamente
C) Utilizar vocabulário técnico
D) Falar o tempo todo para garantir que todos saibam que você está presente
E) Evitar contato visual para se concentrar nas palavras
', 1, 'B', 1, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Como a inteligência emocional agrega no ambiente profissional?
A) Fazer o trabalho rápido
B) Não tem necessidade de falar com os colegas
C) Ajuda em épocas de crise e no trabalho em equipe
D) Não precisar pedir ajuda, quando estiver com dúvida
E) Não leva em consideração o sentimento dos colegas ', 3, 'C', 2, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Qual soft skill é essencial para a capacidade de aceitar feedback construtivo e aplicá-lo para o aprimoramento pessoal e profissional?
A) Comunicação eficaz
B) Adaptabilidade
C) Empatia
D) Resolução de problemas
E) Inteligência emocional', 3, 'B', 2, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'O que é resiliência no ambiente profissional?
A) Não enfrentar os desafios
B) Habilidade de se adaptar a mudanças e superar dificuldade, trilhas
C) Evitar qualquer tipo de risco
D) Saber dizer “não”
E) Fazer qualquer coisa que é solicitado ', 3, 'B', 2, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'O que é empatia e como ela melhora o ambiente de trabalho?
A) Dar presentes aos colegas
B) Capacidade de compreender e se colocar no lugar do outro
C) Expressar apenas emoções positivas para manter um ambiente agradável
D) Falar somente o que o colega quer ouvir
E) Somente dar feedback positivo', 3, 'B', 2, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Por que a gestão do tempo é essencial no ambiente profissional?
A)  Para todos os colegas saberem quando deve ser feito a entrega
B) Gestão do tempo é essencial, somente em equipes grandes
C) Maximizar a produtividade e a eficiência
D) Não precisar controlar os prazos
E) Priorizar somente grandes projetos', 5, 'C', 3, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Qual é a importância da inclusão no local de trabalho?
A)  Reduzir a inovação
B) Reduzir a criatividade
C) Ampliar perspectivas e promover um ambiente mais inclusivo
D) Aumentar a competição entre os funcionários
E) Evitar diferentes opiniões
', 5, 'C', 3, 3, 1);

INSERT INTO QUESTAO (ID_QUESTAO, pergunta, pontos, opcao_correta, dificuldade, trilha, status)
VALUES (SEQ_QUESTAO.NEXTVAL,'Como a resolução de problemas desafiadores contribui para o crescimento profissional?
A) Reduzindo a capacidade analítica
B) Diminuindo o aprendizado
C) Desenvolvendo habilidades de resolução de problemas
D) Diminuindo a adaptabilidade
E) Diminuindo a empatia', 5, 'C', 3, 3, 1);

-- Insert Contatos

-- enderecos usuarios
INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Presidente Vargas', 123, 'Chácara', '12345678', 'Porto Alegre', 'RS', 'Brasil', 1, 0, 1);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Pedro', 456, 'Bloco a', '23456789', 'Rio de Janeiro', 'RJ', 'Brasil', 1, 0, 1);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Brasil', 789, 'Apto 4', '34567890', 'Caxias Do Sul', 'RS', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Sete de Setembro', 101, 'Casa da frente', '45678901', 'Torres', 'RS', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Amazonas', 112, 'Casa 3', '56789012', 'Novo Hamburgo', 'RS', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Cabo Frio', 223, 'Fundos', '67890123', 'São Paulo', 'SP', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua Parana', 334, 'Casa A2', '78901234', 'Feira de Santana', 'BA', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua São Jorge', 445, 'Beco', '89012345', 'Salvador', 'BA', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Avenida Assis Brasil', 556, 'Apto 10', '90123456', 'Ilhéus', 'BA', 'Brasil', 1, 0);

INSERT INTO ENDERECO (id_endereco, logradouro, numero, complemento, cep, cidade, estado, pais, id_usuario, tipo_endereco)
VALUES (SEQ_ENDERECO.NEXTVAL,'Rua 20 de Novembro', 641, 'Apto 2', '12836348', 'Porto Alegre', 'RS', 'Brasil', 1, 0);
