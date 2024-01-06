# EduMatch

## :information_source: Sobre o projeto
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=Em%20Andamento&color=red&style=for-the-badge)<br>
Um aplicativo que visa assegurar a educação inclusiva, equitativa e de qualidade para seus usuários, auxiliando na entrada em entidades de ensino de nível técnico e superior, bem como também desenvolver conhecimentos relevantes em diversas áreas de atuação.
### Integrantes & Branches
- **Arthur Schroder** - [feat/classe-softskills]() | [feat/classe-game]() 
- **Brayan Benet** - [feat/classe-endereco](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/feature/classe-endereco) | [*README*](https://github.com/VictorPrudente/vs13-squad9-EduMatch/tree/README)
- **Camila Bauer** - [feat/classe-contato]() 
- **Carlos Estrela** - [feat/classe-certificado]() | [feat/classe-game]()
- **Débora Hickmann** - [feat/classe-softskills]() | [feat/classe-escola]()
- **Vinícius Félix** - [feat/classe-matematica]() | [feat/classe-empresa]()
- **Jaíne Rodrigues** - ***FrontEnd**
- **Pablo Leão** - ***FrontEnd**
- **Victor Prudente** - [feat/interface-modalidades]() | [feat/classe-usuario]()

## ESTRUTURA DE DESENVOLVIMENTO
### [Apresentação do Projeto](https://github.com/VictorPrudente/vs13-squad9-EduMatch/blob/main/ApresentacaoPDF/EduTech%20-%20Vem%20Ser.pdf)
#### A apresentação em PDF do aplicativo foi desenvolvida para explicar o problema abordado, objetivos, principais potenciais e as dificuldades de implantação, através de uma contextualização geral, bem como algumas soluções ao longo dela.
#### Toda idéia e implementação do projeto foi desenvolvida apartir de um brainstorm de idéias de todos integrantes da equipe (FrontEnd, BackEnd, QA)

### [Diagrama de Classes](https://lucid.app/lucidchart/dd95fb56-046f-432b-961e-838688c5f0ae/edit?viewport_loc=-419%2C-1184%2C3126%2C1495%2C0_0&invitationId=inv_65c61243-3a73-4968-b7b7-2a581f1a69a0)
**Responsáveis pelo Diagrama: [Victor Prudente](https://github.com/VictorPrudente) | [Carlos Estrela](https://github.com/carlosalbertoestrela)**

#### Este é um esforço colaborativo para visualizar e aprimorar a arquitetura do projeto. O diagrama de classes passa por uma constante evolução na medida que os integrantes da equipe nove se aprofundam nos seus conhecimentos. 

### [Trello](https://trello.com/b/okeu5gWa/educamatch)
**Responsáveis pelo Trello: [Carlos Estrela](https://github.com/carlosalbertoestrela) | [Arthur Schroder](https://github.com/ArthurSchroder)**

#### Utilizamos o Trello como uma ferramenta de gerenciamento para mapear as classes e tarefas relacionadas ao diagrama. Cada integrante da equipe contribuiu para a definição, design e implementação de classes específicas, além de acompanhar o progresso das tarefas.
#### O Trello serviu como um quadro visual onde organizamos e priorizamos as atividades, facilitando a comunicação e colaboração entre os membros da equipe. Isso nos permitiu manter um controle eficiente do desenvolvimento do projeto.

### Branches do Projeto
1. [**Main**](COLOCAR LINK)
    - A branch `main` foi utilizada como o ambiente principal para executar a aplicação. Todas as alterações validadas na `develop` foram cuidadosamente integradas na `main`.
    - Isso garantiu que a versão principal do jogo, acessível pela branch `main`, refletisse uma aplicação funcional e estável que os usuários finais poderiam utilizar.

2. [**Develop**](COLOCAR LINK)
    - A branch `develop` foi utilizada como um ambiente de consolidação para as diferentes contribuições das equipes. Antes de uma nova funcionalidade ou correção ser integrada à branch `main`, ela passava por testes extensivos na `develop`.
    - Isso permitiu uma abordagem mais iterativa e colaborativa, onde as equipes podiam validar suas implementações antes de atingir a versão principal.

3. [**feat/game**](COLOCAR LINK)
    - A branch feat/game foi usada para criar a classe game e é a base comum para todos os jogos e fornece funcionalidades essenciais para gerenciar o estado do jogo.

4. [**feat/matematica**](COLOCAR LINK)
    - [Descrição da Funcionalidade da Matemática]

5. [**feat/portugues**](COLOCAR LINK)
    - [Descrição da Funcionalidade do Português]

6. [**feat/softskills**](COLOCAR LINK)
    - [Descrição da Funcionalidade de Softskills]

7. [**feat/usuario**](COLOCAR LINK)
    - A branch feat/usuario foi usada para criar a classe usuario e é utilizada para criar instâncias de usuários no sistema do jogo.
    - Cada usuário pode ter seu próprio conjunto de atributos, como pontuação, tipo de usuário e assim por diante.

8. [**feat/escola**](COLOCAR LINK)
    - A branch feat/escola foi usada para criar a classe escola que representa uma instituição educacional no sistema do jogo.
    - Ela contém informações básicas sobre uma escola, como nome, tipo e CNPJ.

9. [**feat/endereco**](COLOCAR LINK)
    - A branch feat/endereco foi usada para criar a classe endereço que representa informações sobre o endereço de uma entidade no sistema do jogo, como um usuário, escola ou empresa.

10. [**feat/empresa**](COLOCAR LINK)
    - A branch feat/empresa foi usada para criar a classe empresa que representa uma empresa no sistema do jogo.
    - Ela possui informações sobre a empresa, como nome, setor, CNPJ, área de atuação e também interage com usuários, fornecendo suporte e cadastrando alunos.

11. [**feat/contato**](COLOCAR LINK)
    - A branch feat/contato foi usada para criar a classe contato que representa informações de contato no sistema do jogo. Ela inclui descrição, telefone e tipo de contato.

12. [**feat/certificado**](COLOCAR LINK)
    - A branch feat/certificado foi usada para criar a classe certificado que representa um certificado de conclusão no sistema do jogo.
    - Ele inclui informações como ID, data de conclusão, trilha (que pode ser Matemática, Português ou Softskills) e o usuário associado.
 
 ## :handbag: Autores

<div style="display: flex; gap: 20px; flex-wrap: wrap; justify-content: center;">

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://media.licdn.com/dms/image/D4E03AQE-LrTH9UVR6w/profile-displayphoto-shrink_800_800/0/1692737537511?e=1709769600&v=beta&t=998PBs6Ht2ZAV3FoUq2C06cbVV-hnFYer_ZlFE9qLcI" width=115><br>
    <sub><a href="https://github.com/ArthurSchroder">Arthur Schroder</a> - BackEnd</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/63371569?v=4" width=115><br>
    <sub><a href="https://github.com/brayanbenet">Brayan Benet</a> - QA</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/112354608?v=4" width=115><br>
    <sub><a href="https://github.com/CamilaBauer">Camila Bauer</a> - QA</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/69488591?v=4" width=115><br>
    <sub><a href="https://github.com/carlosalbertoestrela">Carlos Estrela</a> - BackEnd</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/101061552?v=4" width=115><br>
    <sub><a href="https://github.com/Deboraaahickmann">Débora Hickmann</a> - QA</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/133911179?v=4" width=115><br>
    <sub><a href="https://github.com/galego-vinicius">Vinícius Félix</a> - BackEnd</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/93234113?v=4" width=115><br>
    <sub><a href="https://github.com/jaineRodrigues">Jaíne Rodrigues</a> - FrontEnd</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://media.licdn.com/dms/image/C5603AQHoRy_hhnD87A/profile-displayphoto-shrink_800_800/0/1621551129575?e=1709769600&v=beta&t=pYTRXe20pjxbK1lxOpIWPlF6kzMK-juDxLgBWQES468" width=115><br>
    <sub><a href="https://github.com/pablo-leao">Pablo Leão</a> - FrontEnd</sub>
  </div>

  <div style="text-align: center; max-width: 150px;">
    <img loading="lazy" src="https://avatars.githubusercontent.com/u/143949574?v=4" width=115><br>
    <sub><a href="https://github.com/VictorPrudente">Victor Prudente</a> - BackEnd</sub>
  </div>

</div>