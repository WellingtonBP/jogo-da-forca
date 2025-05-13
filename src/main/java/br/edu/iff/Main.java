package br.edu.iff;

import br.edu.iff.jogoforca.Aplicacao;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Aplicacao aplicacao = Aplicacao.getSoleInstance();
    aplicacao.configurar();

    Scanner input = new Scanner(System.in);

    String op = "";

    while (!op.equals("Finalizar")) {
      System.out.println(mainMenu());
      op = input.nextLine();
      switch (op) {
        case "Cadastrar tema":
          System.out.println("Digite o nome: ");
          String nomeTema = input.nextLine();
          aplicacao.getTemaFactory().getTema(nomeTema);
          break;

        case "Cadastrar palavra":
          System.out.println("Digite o tema: ");
          String tema = input.nextLine();
          var temaObj = aplicacao.getTemaFactory().getTema(tema);
          System.out.println("Digite a palavra: ");
          String palavra = input.nextLine();
          aplicacao.getPalavraFactory().getPalavra(palavra, temaObj);
          break;

        case "Cadastrar jogador":
          System.out.println("Digite o nome: ");
          String nomeJogador = input.nextLine();
          aplicacao.getJogadorFactory().getJogador(nomeJogador);
          break;

        case "Iniciar partida":
          System.out.println("Digite o nome do jogador: ");
          String nomeJogadorPartida = input.nextLine();
          var jogador = aplicacao.getJogadorFactory().getJogador(nomeJogadorPartida);
          var rodada = aplicacao.getRodadaFactory().getRodada(jogador);
          String op2 = "";
          while (!op2.equals("Finalizar")) {
            System.out.println(partidaMenu());
            op2 = input.nextLine();
            switch (op2) {
              case "Exibir itens":
                rodada.exibirItens(op2);
                break;

              case "Finalizar":
                System.out.println("Fim partida");
                break;
            }
          }
          break;

        case "Finalizar":
          System.out.println("Até mais");
          break;
      }
    }

    input.close();
  }

  public static String partidaMenu() {
    StringBuilder stringBuilder = new StringBuilder("Digite uma opção\n");
    stringBuilder.append("Exibir itens\n");
    stringBuilder.append("Exibir pontos\n");
    stringBuilder.append("Exibir boneco\n");
    stringBuilder.append("Tentar\n");
    stringBuilder.append("Arriscar\n");
    stringBuilder.append("Finalizar\n");
    return stringBuilder.toString();
  }

  public static String mainMenu() {
    StringBuilder stringBuilder = new StringBuilder("Digite uma opção\n");
    stringBuilder.append("Cadastrar tema\n");
    stringBuilder.append("Cadastrar palavra\n");
    stringBuilder.append("Cadastrar jogador\n");
    stringBuilder.append("Iniciar partida\n");
    stringBuilder.append("Finalizar\n");
    return stringBuilder.toString();
  }
}
