package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {
  private static TemaFactoryImpl soleInstance;
  private TemaRepository temaRepository;

  private TemaFactoryImpl(TemaRepository temaRepository) {
    super(temaRepository);
    this.temaRepository = temaRepository;
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
    var existente = this.temaRepository.getPorNome(nome);
    if (existente != null) {
      return existente;
    }
    var novoTema = Tema.criar(getProximoId(), nome);
    this.temaRepository.inserir(novoTema);
    return novoTema;
  }

}
