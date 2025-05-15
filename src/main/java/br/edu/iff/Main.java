package br.edu.iff;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.JogadorNaoEncontradoException;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Aplicacao aplicacao = Aplicacao.getSoleInstance();
    aplicacao.configurar();

    Scanner input = new Scanner(System.in);

    String op = "";

    while (!op.equals("6")) {
      System.out.println(mainMenu());
      op = input.nextLine();
      switch (op) {
        case "1":
          cadastrarTema(aplicacao, input);
          System.out.println();
          break;

        case "2":
          cadastrarPalavra(aplicacao, input);
          System.out.println();
          break;

        case "3":
          cadastrarJogador(aplicacao, input);
          System.out.println();
          break;

        case "4":
          partida(aplicacao, input);
          System.out.println();
          break;

        case "5":
          consultarPartida(aplicacao, input);
          System.out.println();
          break;

        case "6":
          System.out.println("Até mais");
          break;

        default:
          System.out.println("Opção inválida");
      }
    }

    input.close();
  }

  public static String partidaMenu() {
    StringBuilder stringBuilder = new StringBuilder("Digite uma opção: \n");
    stringBuilder.append("1 - Exibir itens\n");
    stringBuilder.append("2 - Exibir pontos\n");
    stringBuilder.append("3 - Exibir boneco\n");
    stringBuilder.append("4 - Tentar\n");
    stringBuilder.append("5 - Arriscar\n");
    stringBuilder.append("6 - Tentativas\n");
    stringBuilder.append("7 - Certas\n");
    stringBuilder.append("8 - Erradas\n");
    stringBuilder.append("9 - Finalizar\n");
    return stringBuilder.toString();
  }

  public static String mainMenu() {
    StringBuilder stringBuilder = new StringBuilder("Digite uma opção: \n");
    stringBuilder.append("1 - Cadastrar tema\n");
    stringBuilder.append("2 - Cadastrar palavra\n");
    stringBuilder.append("3 - Cadastrar jogador\n");
    stringBuilder.append("4 - Iniciar partida\n");
    stringBuilder.append("5 - Consultar partida\n");
    stringBuilder.append("6 - Finalizar\n");
    return stringBuilder.toString();
  }

  public static void cadastrarTema(Aplicacao aplicacao, Scanner input) {
    TemaRepository temaRepository = aplicacao.getRepositoryFactory().getTemaRepository();
    TemaFactory temaFactory = aplicacao.getTemaFactory();

    System.out.println("Digite o nome: ");
    String nomeTema = input.nextLine();

    Tema tema = temaFactory.getTema(nomeTema);

    try {
      temaRepository.inserir(tema);
    } catch (RepositoryException ex) {
      System.out.println("Tema existente");
    }
  }

  public static void cadastrarPalavra(Aplicacao aplicacao, Scanner input) {
    TemaRepository temaRepository = aplicacao.getRepositoryFactory().getTemaRepository();
    PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();
    TemaFactory temaFactory = aplicacao.getTemaFactory();

    System.out.println("Digite o tema: ");
    String nomeTema = input.nextLine();
    Tema tema = temaRepository.getPorNome(nomeTema);

    if (tema == null) {
      System.out.println("Tema não cadastrado, deseja cadastrar? (1 = sim, 2 = não)");
      String resposta = input.nextLine();
      if (resposta.equals("2"))
        return;
      try {
        tema = temaFactory.getTema(nomeTema);
        temaRepository.inserir(tema);
      } catch (RepositoryException e) {
        System.out.println("Erro ao cadastrar tema");
        return;
      }
    }
    System.out.println("Digite a palavra: ");
    String nomePalavra = input.nextLine();
    if (palavraAppService.novaPalavra(nomePalavra, tema.getId())) {
      System.out.println("Sucesso!");
    }
  }

  public static void cadastrarJogador(Aplicacao aplicacao, Scanner input) {
    JogadorRepository jogadorRepository = aplicacao.getRepositoryFactory().getJogadorRepository();
    JogadorFactory jogadorFactory = aplicacao.getJogadorFactory();

    System.out.println("Digite o nome: ");
    String nomeJogador = input.nextLine();

    Jogador jogador = jogadorFactory.getJogador(nomeJogador);

    try {
      jogadorRepository.inserir(jogador);
    } catch (RepositoryException ex) {
      System.out.println("Jogador existente");
    }
  }

  public static void consultarPartida(Aplicacao aplicacao, Scanner input) {
    RodadaRepository rodadaRepository = aplicacao.getRepositoryFactory().getRodadaRepository();
    JogadorRepository jogadorRepository = aplicacao.getRepositoryFactory().getJogadorRepository();

    System.out.println("1 - Consultar por id\n2 - Consultar por jogador");
    String resposta = input.nextLine();

    Rodada rodada = null;
    if (resposta.equals("1")) {
      System.out.println("Digite o id: ");
      String id = input.nextLine();
      rodada = rodadaRepository.getPorId(Long.parseLong(id));
    } else if (resposta.equals("2")) {
      System.out.println("Digite o nome: ");
      String nome = input.nextLine();
      Jogador jogador = jogadorRepository.getPorNome(nome);
      if (jogador == null) {
        System.out.println("Jogador não encontrado!");
        return;
      }
      rodada = rodadaRepository.getPorJogador(jogador);
    } else {
      System.out.println("Opção inválida!");
      return;
    }

    if (rodada == null) {
      System.out.println("Rodada não encontrado!");
      return;
    }

    exibirPalavrasRodada(rodada);
    exibirItensRodada(rodada);
    exibirQtdeAcertosRodada(rodada);
    exibirQtdeErrosRodada(rodada);
    exibirQtdeTentativasRodada(rodada);
    exibirQtdeTentativasRestantesRodada(rodada);
    exibirTentativasRodada(rodada);
    exibirErrosRodada(rodada);
    exibirAcertosRodada(rodada);
    exibirBonecoRodada(rodada);
    exibirPontosRodada(rodada);
  }

  public static void partida(Aplicacao aplicacao, Scanner input) {
    RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();

    System.out.println("Digite o nome do jogador: ");
    String nomeJogadorPartida = input.nextLine();
    Rodada rodada = null;
    try {
      rodada = rodadaAppService.novaRodada(nomeJogadorPartida);
    } catch (JogadorNaoEncontradoException e) {
      System.out.println("Jogador não encontrado");
      return;
    }
    String op = "";
    int qtdErros = 0;
    while (!op.equals("9")) {
      System.out.println(partidaMenu());
      op = input.nextLine();
      switch (op) {
        case "1":
          exibirItensRodada(rodada);
          System.out.println();
          break;

        case "2":
          exibirPontosRodada(rodada);
          System.out.println();
          break;

        case "3":
          exibirBonecoRodada(rodada);
          System.out.println();
          break;

        case "4":
          if (rodada.descobriu()) {
            System.out.println("Você já descobriu! ");
            break;
          }
          System.out.println("Digite o caracter: ");
          String tentativa = input.nextLine();
          rodada.tentar(tentativa.charAt(0));

          if (rodada.getQtdeErros() > qtdErros) {
            System.out.println("Você errou!");
            qtdErros++;
          } else {
            System.out.println("Você acertou!");
            if (rodada.descobriu()) {
              System.out.println("Parabéns, vc acertou a palavra!");
            }
          }
          break;

        case "5":
          if (rodada.descobriu()) {
            System.out.println("Você já descobriu! ");
            break;
          }
          String[] palavras = new String[rodada.getNumPalavras()];
          for (int i = 0; i < rodada.getNumPalavras(); i++) {
            System.out.println("Digite a " + (i + 1) + " palavra : ");
            palavras[i] = input.nextLine();
          }
          rodada.arriscar(palavras);
          if (rodada.descobriu()) {
            System.out.println("Parabéns, vc acertou as palavras!");
          }
          break;

        case "6":
          exibirTentativasRodada(rodada);
          System.out.println();
          break;

        case "7":
          exibirAcertosRodada(rodada);
          System.out.println();
          break;

        case "8":
          exibirErrosRodada(rodada);
          System.out.println();
          break;

        case "9":
          System.out.println("Deseja salvar rodada? (1 = sim)");
          String resposta = input.nextLine();
          if (resposta.equals("1")) {
            if (rodadaAppService.salvarRodada(rodada)) {
              System.out.println("Rodada salva com sucesso!");
            } else {
              System.out.println("Erro ao salvar rodada!");
            }
            ;
          }
          System.out.println("Até mais");
          break;

        default:
          System.out.println("Opção inválida");
      }
    }
  }

  public static void exibirPalavrasRodada(Rodada rodada) {
    System.out.println("Palavras: ");
    rodada.exibirPalavras(null);
  }

  public static void exibirItensRodada(Rodada rodada) {
    System.out.println("Itens: ");
    rodada.exibirItens(null);
  }

  public static void exibirQtdeAcertosRodada(Rodada rodada) {
    System.out.println("Qtde acertos: " + rodada.getQtdeAcertos());
  }

  public static void exibirAcertosRodada(Rodada rodada) {
    System.out.println("Acertos: ");
    for (var letra : rodada.getCertas()) {
      letra.exibir(null);
      System.out.println();
    }
  }

  public static void exibirQtdeErrosRodada(Rodada rodada) {
    System.out.println("Qtde erros: " + rodada.getQtdeErros());
  }

  public static void exibirErrosRodada(Rodada rodada) {
    System.out.println("Erros: ");
    for (var letra : rodada.getErradas()) {
      letra.exibir(null);
      System.out.println();
    }
  }

  public static void exibirQtdeTentativasRodada(Rodada rodada) {
    System.out.println("Qtde tentativas: " + rodada.getQtdeTentativas());
  }

  public static void exibirQtdeTentativasRestantesRodada(Rodada rodada) {
    System.out.println("Qtde tentativas restantes: " + rodada.getQtdeTentativasRestantes());
  }

  public static void exibirPontosRodada(Rodada rodada) {
    System.out.println("Pontos: " + rodada.calcularPontos());
  }

  public static void exibirBonecoRodada(Rodada rodada) {
    System.out.println("Boneco: ");
    rodada.exibirBoneco(null);
  }

  public static void exibirTentativasRodada(Rodada rodada) {
    System.out.println("Tentativas: ");
    for (var letra : rodada.getTentativas()) {
      letra.exibir(null);
      System.out.println();
    }
  }
}
