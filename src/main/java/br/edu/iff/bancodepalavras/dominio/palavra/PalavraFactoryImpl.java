package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
  private static PalavraFactoryImpl soleInstance;

  private PalavraRepository palavraRepository;

  private PalavraFactoryImpl(PalavraRepository PalavraRepository) {
    super(PalavraRepository);
    this.palavraRepository = PalavraRepository;
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
    var existente = this.palavraRepository.getPalavra(palavra);
    if (existente != null) {
      return existente;
    }
    var novaPalavra = Palavra.criar(getProximoId(), palavra, tema);
    try {
      this.palavraRepository.inserir(novaPalavra);
    } catch (RepositoryException e) {
      return null;
    }
    return novaPalavra;
  }

}
