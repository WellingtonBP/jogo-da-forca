package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Jogador extends ObjetoDominioImpl {
    private String nome;
    private int pontuacao = 0;

    private Jogador(long id, String nome) {
        super(id);
        this.nome = nome;
    }

    private Jogador(long id, String nome, int pontuacao) {
        super(id);
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public static Jogador criar(long id, String nome) {
        return new Jogador(id, nome);
    }

    public static Jogador reconstruir(long id, String nome, int pontuacao) {
        return new Jogador(id, nome, pontuacao);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void atualizarPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

}
