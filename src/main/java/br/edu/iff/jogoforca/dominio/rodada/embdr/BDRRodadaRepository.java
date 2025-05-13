package br.edu.iff.jogoforca.dominio.rodada.embdr;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRRodadaRepository implements RodadaRepository {
  private static BDRRodadaRepository soleInstance;

  private BDRRodadaRepository() {
  };

  public static BDRRodadaRepository getSoleInstance() {
    if (BDRRodadaRepository.soleInstance == null) {
      BDRRodadaRepository.soleInstance = new BDRRodadaRepository();
    }
    return BDRRodadaRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    throw new UnsupportedOperationException("Unimplemented method 'getProximoId'");
  }

  @Override
  public Rodada getPorId(long id) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
  }

  @Override
  public Rodada getPorJogador(Jogador jogador) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorJogador'");
  }

  @Override
  public void inserir(Rodada rodada) throws RepositoryException {
    throw new UnsupportedOperationException("Unimplemented method 'inserir'");
  }

  @Override
  public void atualizar(Rodada rodada) throws RepositoryException {
    throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
  }

  @Override
  public void remover(Rodada rodada) throws RepositoryException {
    throw new UnsupportedOperationException("Unimplemented method 'remover'");
  }

}
