package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.concurrent.ThreadLocalRandom;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

public class RodadaSorteioFactory extends RodadaFactoryImpl {
  private static RodadaSorteioFactory soleInstance;

  private RodadaSorteioFactory(RodadaRepository rodadaRepository, TemaRepository temaRepository,
      PalavraRepository palavraRepository) {
    super(rodadaRepository, temaRepository, palavraRepository);
  }

  public static RodadaSorteioFactory createSoleInstance(RodadaRepository rodadaRepository,
      TemaRepository temaRepository, PalavraRepository palavraRepository) {
    RodadaSorteioFactory.soleInstance = new RodadaSorteioFactory(rodadaRepository, temaRepository, palavraRepository);
    return RodadaSorteioFactory.soleInstance;
  }

  public static RodadaSorteioFactory getSoleInstance() {
    return RodadaSorteioFactory.soleInstance;
  }

  public Rodada getRodada(Jogador jogador) {
    return this.getRodadaRepository().getPorJogador(jogador);
    // var existente = this.getRodadaRepository().getPorJogador(jogador);
    // if (existente != null) {
    // return existente;
    // }

    // var temas = this.getTemaRepository().getTodos();
    // if (temas.length != 0) {
    // int randomIndexTema = ThreadLocalRandom.current().nextInt(temas.length);
    // var sortedTema = temas[randomIndexTema];
    // var palavras = this.getPalavraRepository().getPorTema(sortedTema);
    // int randomIndexPalavra =
    // ThreadLocalRandom.current().nextInt(palavras.length);
    // var sortedPalavra = palavras[randomIndexPalavra];
    // }
    // return null;
  }
}
