package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
  private static JogadorFactoryImpl soleInstance;

  private JogadorRepository jogadorRepository;

  private JogadorFactoryImpl(JogadorRepository jogadorRepository) {
    super(jogadorRepository);
    this.jogadorRepository = jogadorRepository;
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
    var existente = this.jogadorRepository.getPorNome(nome);

    if (existente != null) {
      return existente;
    }

    var novoJogador = Jogador.criar(getProximoId(), nome);
    this.jogadorRepository.inserir(novoJogador);
    return novoJogador;
  }

}
