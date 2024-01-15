
![OIG Logo](https://media.discordapp.net/attachments/1189248506335076532/1193294742163828856/image.png?ex=65ac3154&is=6599bc54&hm=488e00b9f6f908bcf0971f18b5b5594320d3d407ad9ce5032daca4ac5c9152f2&=&format=webp&quality=lossless)

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=CONCLUÍDO&color=GREEN&style=for-the-badge)

## Sobre o projeto
O EduMatch é um aplicativo em desenvolvimento que visa promover a educação inclusiva, equitativa e de qualidade. O objetivo principal é auxiliar os usuários na entrada em instituições de ensino técnico e superior, além de desenvolver conhecimentos relevantes em diversas áreas de atuação.

[:information_source: Saiba mais](https://github.com/VictorPrudente/vs13-squad9-EduMatch/blob/main/ApresentacaoPDF/EduTech%20-%20Vem%20Ser.pdf)

### Integrantes & Branches

- **Arthur Schroder**
    - [feature/classe-game](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/refactor/classe-game)
    - [feature/classe-softskills](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-softskills)

- **Brayan Benet**
    - [feature/classe-endereco](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-endereco)
    - [feature/classe-portugues](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-portugues)
    - [*README*](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/README)

- **Camila Bauer**
    - [feature/classe-contato](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/)
    - [feature/menu](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/menu)

- **Carlos Estrela**
    - [feature/classe-certificado](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-certificado)
    - [feature/classe-game](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/refactor/classe-game)

- **Débora Hickmann**
    - [feature/classe-softskills](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-softskills)
    - [feature/classe-escola](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-escola)

- **Vinícius Félix**
    - [feature/classe-matematica](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-matematica)
    - [feature/classe-empresa](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-Empresa)


- **Jaíne Rodrigues** - [FrontEnd](https://vs13-squad9-edu-match.vercel.app/)


- **Pablo Leão** - [FrontEnd](https://vs13-squad9-edu-match.vercel.app/)


- **Victor Prudente**
    - [feature/softskills](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-softskills)
    - [feature/classe-usuario](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-usuario)
    - [feature/classe-game](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/refactor/classe-game)
    - [feature/menu](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/menu)

## Estrutura de Desenvolvimento

### Apresentação do Projeto
A [apresentação em PDF](https://github.com/VictorPrudente/vs13-squad9-EduMatch/blob/main/ApresentacaoPDF/EduTech%20-%20Vem%20Ser.pdf) do aplicativo foi elaborada para explicar o problema abordado, objetivos, potenciais e dificuldades de implantação, fornecendo uma visão geral do projeto.

### Diagrama de Classes
Responsáveis pelo Diagrama: [Victor Prudente](https://github.com/VictorPrudente) | [Carlos Estrela](https://github.com/carlosalbertoestrela)

Veja o [Diagrama de Classes](https://lucid.app/lucidchart/dd95fb56-046f-432b-961e-838688c5f0ae/edit?viewport_loc=-419%2C-1184%2C3126%2C1495%2C0_0&invitationId=inv_65c61243-3a73-4968-b7b7-2a581f1a69a0) para visualizar a arquitetura do projeto.

### Trello
Responsáveis pelo Trello: [Carlos Estrela](https://github.com/carlosalbertoestrela) | [Arthur Schroder](https://github.com/ArthurSchroder)

Acompanhe o progresso das tarefas no [Trello](https://trello.com/b/okeu5gWa/educamatch), utilizado para mapear classes e tarefas relacionadas ao diagrama.

### Branches do Projeto

1. [**Main**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/main)
    - Ambiente principal para executar a aplicação. Todas as alterações validadas na `develop` foram integradas na `main`.

2. [**Develop**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/Develop)
    - Ambiente de consolidação para contribuições das equipes antes de ser integrada à `main`.

3. [**feature/game**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/refactor/classe-game)
    - Criação da classe abstrata game, base comum para todos os jogos.

4. [**feature/matematica**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-matematica)
    - Criação da classe matemática/matematicaService que extende da classe game, tendo questões de nível fácil, médio e díficil.

5. [**feature/portugues**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-portugues)
    - Criação da classe português/portuguesService que extende da classe game, tendo questões de nível fácil, médio e díficil.

6. [**feature/softskills**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-sofskills)
    - Criação da classe SoftSkills/SoftSkillsService que extende da classe game, tendo questões de nível fácil, médio e díficil.

7. [**feature/usuario**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-usuario)
    - Criação da classe usuario/usuarioService para criar instâncias de usuários no sistema do jogo.

8. [**feature/escola**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-escola)
    - Criação da classe escola/escolaService que representa uma instituição educacional no sistema do jogo.

9. [**feature/endereco**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-endereco)
    - Criação da classe endereço/enderecoService que representa informações sobre o endereço de uma entidade no sistema do jogo.

10. [**feature/empresa**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-Empresa)
    - Criação da classe empresa/empresaService que representa uma empresa no sistema do jogo.

11. [**feature/contato**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-Contato)
    - Criação da classe contato/contatoService que representa informações de contato no sistema do jogo.

12. [**feature/certificado**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-certificado)
    - Criação da classe certificado/certificadoService que representa um certificado de conclusão no sistema do jogo.
13. [**feature/menu**](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/menu)
    - Criação da classe menu/menuService onde roda toda interface do jogo e usamos todas funções da aplicação.


## Tecnologias utilizadas
  <img align="center" alt="Rafa-Java" height="40" width="60" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg"> <img align="center" alt="INTELLIJ" height="40" width="60" src="https://media.licdn.com/dms/image/C5607AQEHmg94dM0LuA/group-logo_image-shrink_92x92/0/1630999991585?e=1705280400&v=beta&t=zvl-vS3VMSYDHUWZ3Ot32Jcwx5zddSbBG0B3e-vrbdM">  

####
## Autores do Projeto

| <img src="https://media.licdn.com/dms/image/D4E03AQE-LrTH9UVR6w/profile-displayphoto-shrink_800_800/0/1692737537511?e=1709769600&v=beta&t=998PBs6Ht2ZAV3FoUq2C06cbVV-hnFYer_ZlFE9qLcI" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/63371569?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/112354608?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/69488591?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/101061552?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/133911179?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/93234113?v=4" width="100" height="100"> | <img src="https://media.licdn.com/dms/image/C5603AQHoRy_hhnD87A/profile-displayphoto-shrink_800_800/0/1621551129575?e=1709769600&v=beta&t=pYTRXe20pjxbK1lxOpIWPlF6kzMK-juDxLgBWQES468" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/143949574?v=4" width="100" height="100"> |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| [Arthur Schroder](https://github.com/ArthurSchroder) <br> BackEnd | [Brayan Benet](https://github.com/brayanbenet) <br> QA | [Camila Bauer](https://github.com/CamilaBauer) <br> QA | [Carlos Estrela](https://github.com/carlosalbertoestrela) <br> QA | [Débora Hickmann](https://github.com/Deboraaahickmann) <br> QA | [Vinícius Félix](https://github.com/galego-vinicius) <br> BackEnd | [Jaíne Rodrigues](https://github.com/jaineRodrigues) <br> FrontEnd | [Pablo Leão](https://github.com/pablo-leao) <br> FrontEnd | [Victor Prudente](https://github.com/VictorPrudente) <br> BackEnd |

####
## Testes de Bugs

| <img src="https://avatars.githubusercontent.com/u/112354608?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/69488591?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/101061552?v=4" width="100" height="100"> |
| :---: | :---: | :---: |
| [Camila Bauer](https://github.com/CamilaBauer) <br> Menu Do Jogo | [Carlos Estrela](https://github.com/carlosalbertoestrela) <br> Menu Do Jogo | [Débora Hickmann](https://github.com/Deboraaahickmann) <br> Menu Do Jogo |

####
## Documentação do Projeto

| <img src="https://avatars.githubusercontent.com/u/63371569?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/143949574?v=4" width="100" height="100"> |
| :---: | :---: |
| [Brayan Benet](https://github.com/brayanbenet) <br> README | [Victor Prudente](https://github.com/VictorPrudente) <br> PDF |

####
## PO do Projeto

| [<img src="https://media.licdn.com/dms/image/C4D03AQF4Sw88O-xuTg/profile-displayphoto-shrink_800_800/0/1646524748107?e=1710374400&v=beta&t=rk-FKjtsAGK1F6yiTRB6qzobBba4jKRzbK2gmkHDXDc" alt="Rafael Lazzari" width="100" height="100"/>](https://github.com/rflazzari) |
|:---:|
| [Rafael Lazzari](https://github.com/rflazzari) <br> BackEnd |

####
## Agradecimento
| <img align="center" alt="DBC-COMPANY" height="100" width="100" src="https://media.licdn.com/dms/image/C4D0BAQFszK2MGQUCUA/company-logo_200_200/0/1668687291660?e=1712793600&v=beta&t=lCZTO8Oys0B71J1Yt4Hc66uam-3_3gJZAyWqooiGUVc"> |
|:---:|
| [DBC Company](https://www.linkedin.com/company/dbc-company/mycompany/) |
