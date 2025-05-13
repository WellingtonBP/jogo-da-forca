package br.edu.iff.jogoforca.dominio.jogador.embdr;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;

public class BDRJogadorRepository implements JogadorRepository {
  private static BDRJogadorRepository soleInstance;

  private BDRJogadorRepository() {
  };

  public static BDRJogadorRepository getSoleInstance() {
    if (BDRJogadorRepository.soleInstance == null) {
      BDRJogadorRepository.soleInstance = new BDRJogadorRepository();
    }
    return BDRJogadorRepository.soleInstance;
  }

  @Override
  public long getProximoId() {
    throw new UnsupportedOperationException("Unimplemented method 'getProximoId'");
  }

  @Override
  public Jogador getPorId(long id) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorId'");
  }

  @Override
  public Jogador getPorNome(String nome) {
    throw new UnsupportedOperationException("Unimplemented method 'getPorNome'");
  }

  @Override
  public void inserir(Jogador jogador) {
    throw new UnsupportedOperationException("Unimplemented method 'inserir'");
  }

  @Override
  public void atualizar(Jogador jogador) {
    throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
  }

  @Override
  public void remover(Jogador jogador) {
    throw new UnsupportedOperationException("Unimplemented method 'remover'");
  }

}
