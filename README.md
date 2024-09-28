# Finance APP Backend

## 🏗️ Arquitetura
Este projeto segue o padrão MVC (Model-View-Controller), muito comum em aplicações Spring Boot. Abaixo, está a explicação de como cada parte da arquitetura está organizada:

---

### Modelo (Model)
- A camada Model é responsável pela definição das entidades e classes que representam os dados da aplicação. No projeto, usamos JPA para mapeamento objeto-relacional.
Exemplos: Expense.java, Salary.java, etc.
### Controlador (Controller)
- A camada Controller é responsável por receber as requisições HTTP, processá-las e retornar a resposta apropriada. Os controladores também interagem com a camada de serviço.
Exemplos: `ExpenseController.java`, `SalaryController.java`.
### Serviço (Service)
- A camada Service contém a lógica de negócios da aplicação. Ela processa as informações e regras de negócio, além de interagir com a camada de repositório.
Exemplos: `ExpenseService.java`, `SalaryService.java`.
### Repositório (Repository)
- A camada Repository é responsável pela interação com o banco de dados. Utilizei Spring Data JPA para facilitar as operações de CRUD e o gerenciamento das transações.
Exemplos: `ExpenseRepository.java`, `SalaryRepository.java`.
## 🚀 Tecnologias
- [x] Java 17
- [X] Spring Boot: Framework para desenvolvimento de aplicações em Java.
- [x] Spring Data JPA: Abstração de persistência de dados usando JPA (Java Persistence API).
- [x] Spring Web: Módulo do Spring que permite criar APIs RESTful.




