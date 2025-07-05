package src.models;
import java.util.Date;

public class Venda {
private int id;
private Date data;
private double valorTotal;
//private ArrayList <ItemVenda> itensVenda = new ArrayList<>();

public Venda(int id, Date data){
    this.id= id;
    this.data=data;
    this.valorTotal= 0.0;
}

public int getId() {
    return id;
}

public Date getData() {
    return data;
}

public double getValorTotal() {
    return valorTotal;
}

public void setId(int id) {
    this.id = id;
}

public void setData(Date data) {
    this.data = data;
}

public void setValorTotal(double valorTotal) {
    this.valorTotal = valorTotal;
}

@Override
public String toString() {
    return "\nID: " + id + "\nData da Venda: " + data + "\nValor Total da venda: " + valorTotal;
}

//aqui depois que for feito a classe Produto, poderemos modificar e chamar apenas o metodo gerarRelatorio que vai ser criado posteriormente

public void exbibirResumo() {
    System.out.println("Resumo da Venda:");
    toString();

}


}