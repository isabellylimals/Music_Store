# 🎵 Music Store

## Descrição
O **Music Store** é um sistema de gerenciamento completo para lojas especializadas em mídias físicas musicais (vinis, CDs, fitas cassete). O sistema otimiza processos de vendas, controle de estoque e geração de relatórios, proporcionando eficiência operacional e melhor experiência para clientes e administradores.

---

## Funcionalidades Principais

### Gerenciamento de Produtos
- Cadastro de mídias musicais (vinil, CD, fita)
- Controle de estoque 
- Busca por artista, gênero ou título
- Atualização de preços e categorias

### Gestão de Vendas
- Processamento de vendas com múltiplos itens
- Cálculo automático de subtotais e total
- Histórico completo de vendas
- Emissão de recibos

###  Cadastro de Clientes
- Registro de dados pessoais
- Histórico de compras por cliente
- Sistema de autenticação segura

###  Relatórios Gerenciais
- Vendas por período/artista/gênero
- Estoque crítico 
- Clientes mais frequentes


###  Controle de Acesso
- Níveis de permissão (admin/cliente)
- Login seguro para clientes
- Área restrita para administradores

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

    class Person {
        <<abstract>>
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
    }

    
    Venda "1.1  " *-- * "1..*" ItemVenda :  
    Cliente --|> Person :  
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
- Flyway para migrações

---

## 🛠️ Instalação

### Pré-requisitos
1. JDK 17 instalado
2. MySQL 


### Passo a Passo
```bash
# Clone o repositório
[git clone https://github.com/isabellylimals/Music_Store]

# Configure o banco de dados

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
