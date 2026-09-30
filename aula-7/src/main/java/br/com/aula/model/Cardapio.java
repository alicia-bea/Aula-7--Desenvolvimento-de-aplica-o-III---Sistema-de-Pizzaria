package br.com.aula.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_cardapio")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String codigo;

    @ElementCollection
    @CollectionTable(name = "tb_cardapio_ingredientes", joinColumns = @JoinColumn(name = "cardapio_id"))
    @Column(name = "ingrediente")
    private List<String> ingredientes = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal preco;

    public Cardapio() {}

    public Cardapio(Long id, String nome, String codigo, List<String> ingredientes, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.ingredientes = ingredientes;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public List<String> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<String> ingredientes) { this.ingredientes = ingredientes; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}