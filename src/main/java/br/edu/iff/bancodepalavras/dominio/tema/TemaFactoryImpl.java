package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {
  private static TemaFactoryImpl soleInstance;

  private TemaFactoryImpl(TemaRepository temaRepository) {
    super(temaRepository);
  }

  public static TemaFactoryImpl createSoleInstance(TemaRepository temaRepository) {
    TemaFactoryImpl.soleInstance = new TemaFactoryImpl(temaRepository);
    return TemaFactoryImpl.soleInstance;
  }

  public static TemaFactoryImpl getSoleInstance() {
    return TemaFactoryImpl.soleInstance;
  }

  @Override
  public Tema getTema(String nome) {
    return Tema.criar(getProximoId(), nome);
  }
}
