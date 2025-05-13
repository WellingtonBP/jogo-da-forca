package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
  private static PalavraAppService soleInstance;
  private TemaRepository temaRepository;
  private PalavraRepository palavraRepository;
  private PalavraFactory palavraFactory;

  private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository,
      PalavraFactory palavraFactory) {
    this.temaRepository = temaRepository;
    this.palavraRepository = palavraRepository;
    this.palavraFactory = palavraFactory;
  }

  public static PalavraAppService createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository,
      PalavraFactory palavraFactory) {
    PalavraAppService.soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    return PalavraAppService.soleInstance;
  }

  public static PalavraAppService getSoleInstance() {
    return PalavraAppService.soleInstance;
  }

  public boolean novaPalavra(String palavra, long temaId) {
    var tema = this.temaRepository.getPorId(temaId);
    if (tema == null) {
      return false;
    }
    try {
      var palavraObj = this.palavraRepository.getPalavra(palavra);
      if (palavraObj != null) {
        return true;
      }
      var novaPalavra = Palavra.criar(this.palavraRepository.getProximoId(), palavra, tema);
      this.palavraRepository.inserir(novaPalavra);
      return true;
    } catch (RepositoryException e) {
      return false;
    }
  }
}
