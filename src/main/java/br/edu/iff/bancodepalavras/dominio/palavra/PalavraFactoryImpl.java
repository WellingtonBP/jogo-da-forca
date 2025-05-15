package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
  private static PalavraFactoryImpl soleInstance;

  private PalavraFactoryImpl(PalavraRepository palavraRepository) {
    super(palavraRepository);
  }

  public static PalavraFactoryImpl createSoleInstance(PalavraRepository palavraRepository) {
    PalavraFactoryImpl.soleInstance = new PalavraFactoryImpl(palavraRepository);
    return PalavraFactoryImpl.soleInstance;
  }

  public static PalavraFactoryImpl getSoleInstance() {
    return PalavraFactoryImpl.soleInstance;
  }

  @Override
  public Palavra getPalavra(String palavra, Tema tema) {
    return Palavra.criar(getProximoId(), palavra, tema);
  }

}
