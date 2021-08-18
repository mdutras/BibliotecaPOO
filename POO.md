# Administrador
## Variáveis
- Login
- Senha
## Funções
- Cadastrar e remover bibliotecários e afiliados
- Adicionar, atualizar e remover material no acervo
- Visualizar acervo
    - Características do material
    - Estado
    - Alugado por
    - N° de vezes alugado
- Visualizar lista de bibliotecários e afiliados
- Gerenciar eventos
- Analisar sugestões de adição ao acervo e proposição de eventos

# Bibliotecário
## Variáveis
- Login
- Senha
## Funções
- Cadastrar e remover afiliados
- Adicionar, atualizar e remover material no acervo
- Visualizar acervo
    - Características do material
    - Estado
    - Alugado por
    - N° de vezes alugado
- Visualizar lista de afiliados
- Gerenciar eventos
- Analisar sugestões de adição ao acervo e proposição de eventos
- Emprestar material

# Afiliados
## Variáveis
- Login
- Senha
- Estado
    - Ativo
    - Atingiu limite
    - Livro atrasado
    - Suspenso
    - Desativado
## Funções
- Visualizar acervo
    - Características do material
    - Estado
- Requisitar livros que estejam disponíveis
- Retornar livro
- Renovar empréstimo de livros
- Reservar livros que não estejam disponíveis
- Acesso ao histórico de livros alugados
- Participar de eventos
- Propor evento
- Sugerir adição ao acervo

# Material
## Variáveis
- Código
- Título
- Subtítulo
- Data de publicação
- Tags
- Estado
    - Disponível
    - Alugado
    - Indisponível
## Decorators
- Livro
    - Autor
    - Gênero
- Cordel
    - Autor
- Biografia
    - Autor
- Enciclopédia
    - Autor
- Didático
    - Autor
    - Assunto
- Revista
- Autor
- Historia em quadrinhos
## Funções
- Registrar