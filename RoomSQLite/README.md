# Gerenciador de Livros (Room SQLite)

## 📌 Sobre o Projeto
Este é um aplicativo Android desenvolvido como atividade da disciplina de **Programação para Dispositivos Móveis**. O objetivo principal do projeto é demonstrar o uso do **Room Database** (uma camada de abstração sobre o SQLite) para realizar o armazenamento persistente de dados locais em um dispositivo Android.

O aplicativo funciona como um gerenciador de livros, permitindo ao usuário realizar operações completas de CRUD (Create, Read, Update, Delete).

## 🛠 Tecnologias e Conceitos Aplicados
Durante o desenvolvimento deste projeto, foram explorados e aplicados os seguintes conceitos:

*   **Room Database:** Configuração do banco de dados (`AppDatabase`), criação de Entidades (`Book`) e Data Access Objects (`BookDao`).
*   **Kotlin Coroutines & Flow:** Utilizados para executar as consultas e inserções no banco de dados de forma assíncrona, evitando travamentos na *Main Thread* (interface do usuário). O `Flow` foi usado para observar as mudanças no banco de dados em tempo real.
*   **RecyclerView & ListAdapter (com DiffUtil):** Implementação de uma lista dinâmica e eficiente para exibir os livros cadastrados. A substituição do `notifyDataSetChanged()` pelo `DiffUtil` garantiu melhor performance e animações mais suaves ao atualizar os dados.
*   **ViewBinding:** Substituição do `findViewById` para acessar as Views do XML diretamente no código Kotlin de forma mais segura.
*   **Design de Layout (XML):** Criação de interfaces responsivas usando `LinearLayout`, aplicação de margens/paddings e configuração de propriedades como `fitsSystemWindows` para suporte edge-to-edge.
*   **Recursos do Android (strings.xml):** Boas práticas de extração de textos fixos (`hardcoded`) para os arquivos de recursos.

## 📱 Funcionalidades
1.  **Cadastrar Livro:** Inserção de um novo livro informando Título, Autor, Editora e Ano de Lançamento.
2.  **Listar Livros:** Exibição imediata de todos os livros salvos no banco local.
3.  **Atualizar Livro:** Possibilidade de clicar em um item da lista, preencher seus dados no formulário e atualizar as informações no banco.
4.  **Excluir Livro:** Remoção de um livro selecionado diretamente da base de dados.

## 🎓 Informações Acadêmicas
*   **Instituição:** Instituto Federal de Educação, Ciência e Tecnologia de Pernambuco (IFPE) - Campus Belo Jardim
*   **Curso:** Engenharia de Software
*   **Disciplina:** Programação para Dispositivos Móveis
*   **Professor:** Lucas Sampaio
*   **Aluno:** Marcelo Augusto
