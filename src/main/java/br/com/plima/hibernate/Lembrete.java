package br.com.plima.hibernate;

import javax.persistence.*;

@Entity
public class Lembrete {
    //estrategia de persistencia
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    //cria coluna no banco de dados e esse valor não podera ser null
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    //meu construtor vazio
    //toda entidade tem um construtor vazio
    public Lembrete() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //criar o metodo toString

    @Override
    public String toString() {
        return "Lembrete{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
