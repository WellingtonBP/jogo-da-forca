package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.repository.Repository;

public interface JogadorRepository extends Repository {
  public Jogador getPorId(long id);

  public Jogador getPorNome(String nome);

  public void inserir(Jogador jogador);

  public void atualizar(Jogador jogador);

  public void remover(Jogador jogador);
}
