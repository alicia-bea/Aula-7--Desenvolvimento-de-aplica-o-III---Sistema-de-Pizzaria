package br.com.aula.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoModel> itens = new ArrayList<>();

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemPedidoModel> getItens() { return itens; }
    public void setItens(List<ItemPedidoModel> itens) { this.itens = itens; }

    public void adicionarItem(ItemPedidoModel item) {
        itens.add(item);
        item.setPedido(this);
        recalcularValorTotal();
    }

    public void recalcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedidoModel::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}