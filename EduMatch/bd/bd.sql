BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'CONTATO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_CONTATO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'ENDERECO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_ENDERECO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'CERTIFICADO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_CERTIFICADO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'USUARIO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_USUARIO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'ESCOLA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_ESCOLA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'EMPRESA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_EMPRESA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'SOFTSKILL';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_SOFTSKILL';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'MATEMATICA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_MATEMATICA';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'PORTUGUES';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_PORTUGUES';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE ' || 'QUESTAO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;


BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE ' || 'SEQ_QUESTAO';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN
            RAISE;
        END IF;
END;


CREATE TABLE ESCOLA (
                        id_escola NUMBER(38,0) NOT NULL,
                        nome VARCHAR2(50) NOT NULL,
                        tipo NUMBER(1) NOT NULL CHECK (tipo IN (0,1)),
                        cnpj VARCHAR2(14) UNIQUE,
                        PRIMARY KEY ( id_escola )
);


CREATE SEQUENCE SEQ_ESCOLA
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE EMPRESA (
                         id_empresa NUMBER(38,0) NOT NULL,
                         nome VARCHAR2(250) NOT NULL,
                         cnpj VARCHAR2(14) NOT NULL UNIQUE,
                         setor VARCHAR2(50) NOT NULL,
                         area_de_atuacao VARCHAR2(50) NOT NULL,
                         tipo NUMBER(1) NOT NULL CHECK (tipo IN (0,1)),
                         PRIMARY KEY ( id_empresa )
);


CREATE SEQUENCE SEQ_EMPRESA
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE USUARIO (
                         id_usuario NUMBER(38,0) NOT NULL,
                         nome VARCHAR2(50),
                         sobrenome VARCHAR2(50),
                         email VARCHAR2(100) NOT NULL UNIQUE,
                         senha VARCHAR2(100) NOT NULL,
                         cpf VARCHAR2(11) UNIQUE,
                         idade NUMBER(2,0),
                         pontuacao NUMBER(3,0),
                         id_contato NUMBER(38,0),
                         id_certificado NUMBER(38,0),
                         id_endereco NUMBER(38,0),
                         id_empresa NUMBER(38,0),
                         id_escola NUMBER(38,0),
                         PRIMARY KEY ( id_usuario ),
                         CONSTRAINT FK_USUARIO_ESCOLA FOREIGN KEY ( id_escola ) REFERENCES ESCOLA ( id_escola ),
                         CONSTRAINT FK_USUARIO_EMPRESA FOREIGN KEY ( id_empresa ) REFERENCES EMPRESA ( id_empresa )
);


CREATE SEQUENCE SEQ_USUARIO
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE CONTATO (
                         id_contato NUMBER(38,0) NOT NULL,
                         descricao VARCHAR2(200),
                         telefone VARCHAR2(14) UNIQUE,
                         tipo_contato NUMBER(1) NOT NULL CHECK (tipo_contato IN (0,1,2)),
                         id_usuario NUMBER(38,0),
                         PRIMARY KEY ( id_contato ),
                         CONSTRAINT FK_CONTATO_USUARIO FOREIGN KEY ( id_usuario ) REFERENCES USUARIO ( id_usuario )
);


CREATE SEQUENCE SEQ_CONTATO
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE ENDERECO (
                          id_endereco NUMBER(38,0) NOT NULL,
                          logradouro VARCHAR2(250) NOT NULL,
                          numero NUMBER(10) NOT NULL,
                          complemento VARCHAR2(250) NOT NULL,
                          cep VARCHAR2(8) NOT NULL,
                          cidade VARCHAR2(50) NOT NULL,
                          estado VARCHAR2(50) NOT NULL,
                          pais VARCHAR2(50) NOT NULL,
                          tipo_endereco NUMBER(1,0) NOT NULL CHECK (tipo_endereco IN (0, 1)),
                          id_usuario NUMBER(38,0),
                          PRIMARY KEY ( id_endereco ),
                          CONSTRAINT FK_ENDERECO_USUARIO FOREIGN KEY ( id_usuario ) REFERENCES USUARIO ( id_usuario )
);


CREATE SEQUENCE SEQ_ENDERECO
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE CERTIFICADO (
                             id_certificado NUMBER(38,0) NOT NULL,
                             trilha NUMBER(1,0) NOT NULL CHECK (trilha IN (1,2,3)),
                             dificuldade NUMBER(1,0) NOT NULL CHECK (dificuldade IN (1,2,3)),
                             data_emitida DATE NOT NULL,
                             id_usuario NUMBER(38,0) NOT NULL,
                             PRIMARY KEY ( id_certificado ),
                             CONSTRAINT FK_CERTIFICADO_USUARIO FOREIGN KEY ( id_usuario ) REFERENCES USUARIO ( id_usuario )
);


CREATE SEQUENCE SEQ_CERTIFICADO
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;


CREATE TABLE QUESTAO (
                           id_questao NUMBER(38,0) NOT NULL,
                           pontos NUMBER(1,0) NOT NULL CHECK(pontos IN (1,3,5)),
                           pergunta VARCHAR2(4000) UNIQUE NOT NULL,
                           opcao_correta CHAR(1) NOT NULL,
                           trilha NUMBER(1,0) NOT NULL CHECK (trilha IN (1, 2, 3)),
                           dificuldade NUMBER(1,0) NOT NULL CHECK (dificuldade IN (1,2,3)),
                           status NUMBER (1,0) NOT NULL CHECK (status IN (0,1)),
                           PRIMARY KEY ( id_questao )
);


CREATE SEQUENCE SEQ_QUESTAO
    START WITH     1
    INCREMENT BY   1
    NOCACHE
    NOCYCLE;    