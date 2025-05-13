package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.factory.EntityFactory;

public abstract class RodadaFactoryImpl extends EntityFactory {
  private final RodadaRepository rodadaRepository;
  private final TemaRepository temaRepository;
  private final PalavraRepository palavraRepository;

  protected RodadaFactoryImpl(RodadaRepository rodadaRepository, TemaRepository temaRepository,
      PalavraRepository palavraRepository) {
    super(rodadaRepository);
    this.rodadaRepository = rodadaRepository;
    this.temaRepository = temaRepository;
    this.palavraRepository = palavraRepository;
  }

  protected RodadaRepository getRodadaRepository() {
    return this.rodadaRepository;
  }

  protected TemaRepository getTemaRepository() {
    return this.temaRepository;
  }

  protected PalavraRepository getPalavraRepository() {
    return this.palavraRepository;
  }
}
