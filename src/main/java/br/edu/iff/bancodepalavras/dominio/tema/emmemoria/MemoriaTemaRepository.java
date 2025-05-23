package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import java.util.HashMap;
import java.util.Map;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaTemaRepository implements TemaRepository {
  private static MemoriaTemaRepository soleInstance;
  private final Map<Long, Tema> map;
  private long sequence = 0;

  private MemoriaTemaRepository() {
    this.map = new HashMap<>();
  };

  public static MemoriaTemaRepository getSoleInstance() {
    if (MemoriaTemaRepository.soleInstance == null) {
      MemoriaTemaRepository.soleInstance = new MemoriaTemaRepository();
    }

    return MemoriaTemaRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    return sequence + 1;
  }

  @Override
  public Tema getPorId(long id) {
    return map.get(id);
  }

  @Override
  public Tema getPorNome(String nome) {
    return map.values().stream().filter(tema -> tema.getNome().equals(nome)).findFirst().orElse(null);
  }

  @Override
  public Tema[] getTodos() {
    return map.values().toArray(new Tema[map.values().size()]);
  }

  @Override
  public void inserir(Tema tema) throws RepositoryException {
    var existente = this.getPorNome(tema.getNome());
    if (existente != null) {
      throw new RepositoryException();
    }
    map.put(this.getProximoId(), tema);
    this.sequence++;
  }

  @Override
  public void atualizar(Tema tema) throws RepositoryException {
    if (this.getPorId(tema.getId()) == null)
      throw new RepositoryException();
    map.put(tema.getId(), tema);
  }

  @Override
  public void remover(Tema tema) throws RepositoryException {
    if (this.getPorId(tema.getId()) == null)
      throw new RepositoryException();
    map.remove(tema.getId());
  }

}
