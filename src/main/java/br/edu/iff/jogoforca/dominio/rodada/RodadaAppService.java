package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;

public class RodadaAppService {
  private static RodadaAppService soleInstance;

  private JogadorRepository jogadorRepository;
  private RodadaRepository rodadaRepository;
  private RodadaFactory rodadaFactory;

  private RodadaAppService(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository,
      JogadorRepository jogadorRepository) {
    this.jogadorRepository = jogadorRepository;
    this.rodadaRepository = rodadaRepository;
    this.rodadaFactory = rodadaFactory;
  }

  public static RodadaAppService createSoleInstance(RodadaFactory rodadaFactory, RodadaRepository rodadaRepository,
      JogadorRepository jogadorRepository) {
    RodadaAppService.soleInstance = new RodadaAppService(rodadaFactory, rodadaRepository, jogadorRepository);
    return RodadaAppService.soleInstance;
  }

  public static RodadaAppService getSoleInstance() {
    return RodadaAppService.soleInstance;
  }

  public Rodada novaRodada(long idJogador) {
    var jogador = this.jogadorRepository.getPorId(idJogador);
    var rodada = this.rodadaFactory.getRodada(jogador);
    return rodada;
  }

  public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
    var jogador = this.jogadorRepository.getPorNome(nomeJogador);
    if (jogador == null) {
      throw new JogadorNaoEncontradoException(nomeJogador);
    }
    var rodada = this.rodadaFactory.getRodada(jogador);
    return rodada;
  }

  public boolean salvarRodada(Rodada rodada) {
    try {
      this.rodadaRepository.inserir(rodada);
      return true;
    } catch (RepositoryException e) {
      return false;
    }
  }
}
