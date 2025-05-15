package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;

public class Aplicacao {
  private static final String[] TIPOS_REPOSITORY_FACTORY = new String[] { "memoria", "relacional" };
  private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = new String[] { "texto", "imagem" };
  private static final String[] TIPOS_RODADA_FACTORY = new String[] { "sorteio" };
  private static Aplicacao soleInstance;

  private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
  private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
  private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

  private Aplicacao() {
  }

  public static Aplicacao getSoleInstance() {
    if (Aplicacao.soleInstance == null) {
      Aplicacao.soleInstance = new Aplicacao();
    }
    return Aplicacao.soleInstance;
  }

  public void configurar() {
    LetraFactory letraFactory = this.getLetraFactory();
    BonecoFactory bonecoFactory = this.getBonecoFactory();

    Rodada.setBonecoFactory(bonecoFactory);
    Palavra.setLetraFactory(letraFactory);

    PalavraRepository palavraRepository = this.getRepositoryFactory().getPalavraRepository();
    RodadaRepository rodadaRepository = this.getRepositoryFactory().getRodadaRepository();
    JogadorRepository jogadorRepository = this.getRepositoryFactory().getJogadorRepository();
    TemaRepository temaRepository = this.getRepositoryFactory().getTemaRepository();

    RodadaSorteioFactory.createSoleInstance(rodadaRepository, temaRepository, palavraRepository);
    PalavraFactoryImpl.createSoleInstance(palavraRepository);
    TemaFactoryImpl.createSoleInstance(temaRepository);
    PalavraFactoryImpl.createSoleInstance(palavraRepository);
    JogadorFactoryImpl.createSoleInstance(jogadorRepository);

    PalavraFactory palavraFactory = this.getPalavraFactory();
    RodadaFactory rodadaFactory = this.getRodadaFactory();

    RodadaAppService.createSoleInstance(rodadaFactory, rodadaRepository, jogadorRepository);
    PalavraAppService.createSoleInstance(temaRepository, palavraRepository, palavraFactory);
  }

  public String[] getTiposRepositoryFactory() {
    return Aplicacao.TIPOS_REPOSITORY_FACTORY;
  }

  public void setTipoRepositoryFactory(String tipo) {
    this.tipoRepositoryFactory = tipo;
    this.configurar();
  }

  public RepositoryFactory getRepositoryFactory() {
    RepositoryFactory repositoryFactory = null;
    switch (tipoRepositoryFactory) {
      case "memoria":
        repositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
        break;

      case "relacional":
        repositoryFactory = BDRRepositoryFactory.getSoleInstance();
        break;
    }
    return repositoryFactory;
  }

  public String[] getTiposElementoGraficoFactory() {
    return Aplicacao.TIPOS_ELEMENTO_GRAFICO_FACTORY;
  }

  public void setTipoElementoGraficoFactory(String tipo) {
    this.tipoElementoGraficoFactory = tipo;
    this.configurar();
  }

  private ElementoGraficoFactory getElementoGraficoFactory() {
    ElementoGraficoFactory elementoGraficoFactory = null;
    switch (tipoElementoGraficoFactory) {
      case "texto":
        elementoGraficoFactory = ElementoGraficoTextoFactory.getSoleInstance();
        break;

      case "imagem":
        elementoGraficoFactory = ElementoGraficoImagemFactory.getSoleInstance();
        break;
    }
    return elementoGraficoFactory;
  }

  public BonecoFactory getBonecoFactory() {
    return this.getElementoGraficoFactory();
  }

  public LetraFactory getLetraFactory() {
    return this.getElementoGraficoFactory();
  }

  public String[] getTiposRodadaFactory() {
    return Aplicacao.TIPOS_RODADA_FACTORY;
  }

  public void setTipoRodadaFactory(String tipo) {
    this.tipoRodadaFactory = tipo;
    this.configurar();
  }

  public RodadaFactory getRodadaFactory() {
    RodadaFactory rodadaFactory = null;

    switch (tipoRodadaFactory) {
      case "sorteio":
        rodadaFactory = RodadaSorteioFactory.getSoleInstance();
        break;
    }
    return rodadaFactory;
  }

  public TemaFactory getTemaFactory() {
    return TemaFactoryImpl.getSoleInstance();
  }

  public PalavraFactory getPalavraFactory() {
    return PalavraFactoryImpl.getSoleInstance();
  }

  public JogadorFactory getJogadorFactory() {
    return JogadorFactoryImpl.getSoleInstance();
  }
}
