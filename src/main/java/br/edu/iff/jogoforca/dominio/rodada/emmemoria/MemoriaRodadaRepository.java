package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import java.util.HashMap;
import java.util.Map;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaRodadaRepository implements RodadaRepository {
  private static MemoriaRodadaRepository soleInstance;
  private final Map<Long, Rodada> map;
  private long sequence = 0;

  private MemoriaRodadaRepository() {
    this.map = new HashMap<>();
  }

  public static MemoriaRodadaRepository getSoleInstance() {
    if (MemoriaRodadaRepository.soleInstance == null) {
      MemoriaRodadaRepository.soleInstance = new MemoriaRodadaRepository();
    }

    return MemoriaRodadaRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    return sequence + 1;
  }

  @Override
  public Rodada getPorId(long id) {
    return map.get(id);
  }

  @Override
  public Rodada[] getPorJogador(Jogador jogador) {
    var lista = this.map.values().stream().filter(rodada -> rodada.getJogador().equals(jogador)).toList();

    return lista.toArray(new Rodada[lista.size()]);
  }

  @Override
  public void inserir(Rodada rodada) throws RepositoryException {
    map.put(this.getProximoId(), rodada);
    this.sequence++;
  }

  @Override
  public void atualizar(Rodada rodada) throws RepositoryException {
    map.put(rodada.getId(), rodada);
  }

  @Override
  public void remover(Rodada rodada) throws RepositoryException {
    map.remove(rodada.getId());
  }

}
