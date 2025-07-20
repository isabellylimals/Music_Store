# 🎵 Music Store

## Descrição
O **Music Store** é um sistema de gerenciamento completo para lojas especializadas em mídias físicas musicais (vinis, CDs, fitas cassete). Além de otimizar processos de vendas, realiza o controle de estoque e geração de relatórios. Com isso, garante eficiência operacional e melhor experiência para clientes e administradores.

---

## Funcionalidades Principais
### **Administrador**

### Gerenciamento de Produtos;
- Cadastrar mídias musicais (vinil, CD, fita);
- Exibir informações por produto;
- Repor estoque;
- Buscar produto por id;
- Listar todos os produtos cadastrados;
- Atualizar o produto;
- Inativar produto.

### Gestão de Vendas
- Processar vendas com múltiplos itens;
- Calcular de forma automática subtotais e total;
- Gerar relatório das vendas realizadas;
- Emitir do resumo da venda atual;
- Remover ou adicionar itens antes da confirmação.

### Cadastro de Clientes
- Cadastrar novo cliente;
- Listar clientes cadastrados.

### Relatórios Gerenciais
- Administrador pode realizar venda em cliente;
- Imprimir o histórico de vendas.

### **(Cliente)**

### Login de cliente
- Registrar dados pessoais;
- Visualizar histórico de compras por cliente;
- Buscar produtos por nome, artista, gênero ou listagem de produtos;
- Atualizar status da conta;
- Alterar dados cadastrais.

### Controle de Acesso
- Níveis de permissão (admin/cliente): Por meio do login;
- **Clientes**: Acesso a busca de produtos disponíveis, pesquisar pelo nome, gênero ou artista, gerenciamento da própria conta e histórico de compras realizadas;
- **Administradores**: acesso ao gerenciamento de produtos por meio de id e de vendas, cadastro de novos clientes e listagem de  todos os clientes cadastrados.

---

##  Diagrama de Classes

```mermaid
classDiagram
    class Venda {
        -id: int
        -data: Date
        -valorTotal: double
        +exibirResumo() void
        +adicionarItem(ItemVenda) void
        +removerItem(ItemVenda) void
        +calcularTotal() double
        +finalizarVenda() void
        +gerarRelatorioVendas() String
    }

    class ItemVenda {
        -quantidade: int
        +calcularSubtotal() double
    }

    class Pessoa {
        -id: int
        -nome: String
        -email: String
        -senha: String
        -ehAdministrador: boolean
        +login(String, String) boolean
        +criarNovoLogin(String, String) void
    }

    class Cliente {
        -telefone: String
        +cadastrarCliente() void
        +exibirDadosCadastrais() void
        +consultarTodosClientes() void
        +alterarCadastro() void
        +removerCliente() void
        +buscarClientePorId(int id) Cliente
    }

    class Produto {
        -id: int
        -nome: String
        -tipo: String
        -genero: String
        -artista: String
        -anoLancamento: int
        -preco: double
        -qtdEstoque: int
        +cadastrarProduto() void
        +exibirInformacoes() void
        +exibirTodosProdutos() void
        +alterarProduto() void
        +excluirProduto() void
        +buscarProdutoPorId(int id) List Produto
        +buscarProdutoPorNome(String nomeBusca) List Produto
        +buscarProdutoPorArtista(String artistaBusca) List Produto
        +buscarProdutoPorGenero(String generoBusca) List Produto 
    }

    
    Venda "1.1  " *--  "1..*" ItemVenda :  
    Cliente --|> Pessoa :  
    ItemVenda "0..*" --*"1.1  " Produto :  
    Cliente "1.1  " -- "0..*  " Venda :  

```

## ⚙️ Requisitos Técnicos

### **Backend**
- Java 17+
- Spring Boot 3.1+
- Spring Data JPA
- Spring Security

### **Banco de Dados**
- MySQL 8.0+
- Railway para migrações

---

## 🛠️ Instalação

### Pré-requisitos
1. JDK 17 instalado
2. MySQL 


### Passo a Passo
```bash
# Clone o repositório
[git clone https://github.com/isabellylimals/Music_Store]

# Terminal
passo 1: javac -d bin -cp "lib/mysql-connector-j-9.3.0.jar" DAO/*.java @(Get-ChildItem -Recurse -Filter *.java -Path src,views | ForEach-Object { $_.FullName })
passo 2: java -cp "bin;lib/mysql-connector-j-9.3.0.jar" src.models.Main
```

## Equipe de Desenvolvimento

<table align="center">
  <tr>    
    <td align="center">
      <a href="https://github.com/Leticiavieirg">
        <img src="https://avatars.githubusercontent.com/u/90807534?v=4" 
        width="120px;" alt="Foto de Letícia Vieira"/><br>
        <sub>
          <b>Letícia Vieira</b>
         </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/">
        <img src="https://avatars.githubusercontent.com/u/144949202?v=4" 
        width="120px;" alt="Foto de Jose Kayque"/><br>
        <sub>
          <b>Jose Kayque</b>
         </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/">
        <img src="https://avatars.githubusercontent.com/u/146142470?v=4" 
        width="120px;" alt="Samuel Iago"/><br>
        <sub>
          <b>Samuel Iago</b>
         </sub>
      </a>
    </td>
    </td>
    <td align="center">
      <a href="https://github.com/isabellylimals">
        <img src="https://avatars.githubusercontent.com/u/134648243?v=4" 
        width="120px;" alt="Maria Isabelly"/><br>
        <sub>
          <b>Maria Isabelly</b>
         </sub>
      </a>
    </td>
  </tr>
</table>

Licença
Este projeto está licenciado sob a MIT License - veja o arquivo LICENSE.md para detalhes.
