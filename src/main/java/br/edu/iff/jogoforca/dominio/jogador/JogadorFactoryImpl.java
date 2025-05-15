package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
  private static JogadorFactoryImpl soleInstance;

  private JogadorFactoryImpl(JogadorRepository jogadorRepository) {
    super(jogadorRepository);
  }

  public static JogadorFactoryImpl createSoleInstance(JogadorRepository jogadorRepository) {
    JogadorFactoryImpl.soleInstance = new JogadorFactoryImpl(jogadorRepository);
    return JogadorFactoryImpl.soleInstance;
  }

  public static JogadorFactoryImpl getSoleInstance() {
    return JogadorFactoryImpl.soleInstance;
  }

  @Override
  public Jogador getJogador(String nome) {
    return Jogador.criar(getProximoId(), nome);
  }

}
