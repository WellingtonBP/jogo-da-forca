package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.embdr.BDRPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.embdr.BDRTemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.embdr.BDRJogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.emmemoria.MemoriaJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;
import br.edu.iff.jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.embdr.BDRRodadaRepository;

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
    RodadaFactory rodadaFactory = null;
    RodadaRepository rodadaRepository = null;
    JogadorRepository jogadorRepository = null;
    TemaRepository temaRepository = null;
    PalavraRepository palavraRepository = null;
    LetraFactory letraFactory = null;
    BonecoFactory bonecoFactory = null;
    PalavraFactory palavraFactory = PalavraFactoryImpl.getSoleInstance();

    switch (tipoRepositoryFactory) {
      case "memoria":
        rodadaRepository = MemoriaRodadaRepository.getSoleInstance();
        jogadorRepository = MemoriaJogadorRepository.getSoleInstance();
        temaRepository = MemoriaTemaRepository.getSoleInstance();
        palavraRepository = MemoriaPalavraRepository.getSoleInstance();
        break;

      case "relacional":
        rodadaRepository = BDRRodadaRepository.getSoleInstance();
        jogadorRepository = BDRJogadorRepository.getSoleInstance();
        temaRepository = BDRTemaRepository.getSoleInstance();
        palavraRepository = BDRPalavraRepository.getSoleInstance();
        break;
    }

    switch (tipoRodadaFactory) {
      case "sorteio":
        rodadaFactory = RodadaSorteioFactory.createSoleInstance(rodadaRepository, temaRepository, palavraRepository);
        break;
    }

    switch (tipoElementoGraficoFactory) {
      case "texto":
        letraFactory = LetraTextoFactory.getSoleInstance();
        bonecoFactory = BonecoTextoFactory.getSoleInstance();
        break;

      case "imagem":
        letraFactory = LetraImagemFactory.getSoleInstance();
        bonecoFactory = BonecoImagemFactory.getSoleInstance();
        break;
    }

    RodadaAppService.createSoleInstance(rodadaFactory, rodadaRepository, jogadorRepository);
    JogadorFactoryImpl.createSoleInstance(jogadorRepository);
    PalavraFactoryImpl.createSoleInstance(palavraRepository);
    TemaFactoryImpl.createSoleInstance(temaRepository);
    Rodada.setBonecoFactory(bonecoFactory);
    Palavra.setLetraFactory(letraFactory);
    PalavraAppService.createSoleInstance(temaRepository, palavraRepository, palavraFactory);
    RodadaAppService.createSoleInstance(rodadaFactory, rodadaRepository, jogadorRepository);
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

  public ElementoGraficoFactory getElementoGraficoFactory() {
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
    BonecoFactory bonecoFactory = null;
    switch (tipoElementoGraficoFactory) {
      case "texto":
        bonecoFactory = BonecoTextoFactory.getSoleInstance();
        break;

      case "imagem":
        bonecoFactory = BonecoImagemFactory.getSoleInstance();
        break;
    }
    return bonecoFactory;
  }

  public LetraFactory getLetraFactory() {
    LetraFactory letraFactory = null;
    switch (tipoElementoGraficoFactory) {
      case "texto":
        letraFactory = LetraTextoFactory.getSoleInstance();
        break;

      case "imagem":
        letraFactory = LetraImagemFactory.getSoleInstance();
        break;
    }
    return letraFactory;
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
    RodadaRepository rodadaRepository = null;
    TemaRepository temaRepository = null;
    PalavraRepository palavraRepository = null;

    switch (tipoRepositoryFactory) {
      case "memoria":
        rodadaRepository = MemoriaRodadaRepository.getSoleInstance();
        temaRepository = MemoriaTemaRepository.getSoleInstance();
        palavraRepository = MemoriaPalavraRepository.getSoleInstance();
        break;

      case "relacional":
        rodadaRepository = BDRRodadaRepository.getSoleInstance();
        temaRepository = BDRTemaRepository.getSoleInstance();
        palavraRepository = BDRPalavraRepository.getSoleInstance();
        break;
    }

    switch (tipoRodadaFactory) {
      case "sorteio":
        rodadaFactory = RodadaSorteioFactory.createSoleInstance(rodadaRepository, temaRepository, palavraRepository);
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
