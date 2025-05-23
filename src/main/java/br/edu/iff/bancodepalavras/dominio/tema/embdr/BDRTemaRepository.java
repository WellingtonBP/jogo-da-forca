package br.edu.iff.bancodepalavras.dominio.tema.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRTemaRepository implements TemaRepository {
  private static BDRTemaRepository soleInstance;

  private BDRTemaRepository() {
  };

  public static BDRTemaRepository getSoleInstance() {
    if (BDRTemaRepository.soleInstance == null) {
      BDRTemaRepository.soleInstance = new BDRTemaRepository();
    }
    return BDRTemaRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    throw new UnsupportedOperationException("Unimplemented method 'getProximoId'");
  }

  @Override
  public Tema getPorId(long id) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
  }

  @Override
  public Tema getPorNome(String nome) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorNome'");
  }

  @Override
  public Tema[] getTodos() {
    throw new UnsupportedOperationException("Unimplemented method 'getTodos'");
  }

  @Override
  public void inserir(Tema tema) {
    throw new UnsupportedOperationException("Unimplemented method 'inserir'");
  }

  @Override
  public void atualizar(Tema tema) throws RepositoryException {
    throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
  }

  @Override
  public void remover(Tema tema) throws RepositoryException {
    throw new UnsupportedOperationException("Unimplemented method 'remover'");
  }

}
