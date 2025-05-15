package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

public class RodadaSorteioFactory extends RodadaFactoryImpl implements RodadaFactory {
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
    var existente = this.getRodadaRepository().getPorJogador(jogador);
    if (existente != null) {
      return existente;
    }

    var temas = this.getTemaRepository().getTodos();
    if (temas.length != 0) {
      var randomIndexTema = ThreadLocalRandom.current().nextInt(temas.length);
      var sortedTema = temas[randomIndexTema];
      var palavrasTema = this.getPalavraRepository().getPorTema(sortedTema);

      var qtdPalavras = ThreadLocalRandom.current().nextInt(Rodada.getMaxPalavras());
      if (qtdPalavras == 0) {
        qtdPalavras++;
      }
      Set<Integer> sortedPalavras = new HashSet<>();
      var palavrasSorteadas = new Palavra[qtdPalavras];
      int j = 0;
      for (int i = 0; i < qtdPalavras; i++) {
        Integer sorted = ThreadLocalRandom.current().nextInt(palavrasTema.length);
        while (sortedPalavras.contains(sorted)) {
          sorted = ThreadLocalRandom.current().nextInt(palavrasTema.length);
        }
        palavrasSorteadas[j] = palavrasTema[sorted];
        j++;
      }
      palavrasTema = palavrasSorteadas;
      var rodada = Rodada.criar(getProximoId(), palavrasTema, jogador);
      return rodada;
    }
    return null;
  }
}
