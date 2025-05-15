package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import java.util.HashMap;
import java.util.Map;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaJogadorRepository implements JogadorRepository {
  private static MemoriaJogadorRepository soleInstance;
  private final Map<Long, Jogador> map;
  private long sequence = 0;

  private MemoriaJogadorRepository() {
    this.map = new HashMap<>();
  };

  public static MemoriaJogadorRepository getSoleInstance() {
    if (MemoriaJogadorRepository.soleInstance == null) {
      MemoriaJogadorRepository.soleInstance = new MemoriaJogadorRepository();
    }

    return MemoriaJogadorRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    return sequence + 1;
  }

  @Override
  public Jogador getPorId(long id) {
    return map.get(id);
  }

  @Override
  public Jogador getPorNome(String nome) {
    return map.values().stream().filter(jogador -> jogador.getNome().equals(nome)).findFirst().orElse(null);
  }

  @Override
  public void inserir(Jogador jogador) throws RepositoryException {
    if (this.getPorId(jogador.getId()) != null)
      throw new RepositoryException();

    map.put(this.getProximoId(), jogador);
    this.sequence++;
  }

  @Override
  public void atualizar(Jogador jogador) throws RepositoryException {
    if (this.getPorId(jogador.getId()) == null)
      throw new RepositoryException();
    map.put(jogador.getId(), jogador);
  }

  @Override
  public void remover(Jogador jogador) throws RepositoryException {
    if (this.getPorId(jogador.getId()) == null)
      throw new RepositoryException();
    map.remove(jogador.getId());
  }

}
