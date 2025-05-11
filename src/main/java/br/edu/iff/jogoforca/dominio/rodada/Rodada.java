package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.boneco.BonecoFactory;
import br.edu.iff.bancodepalavras.dominio.boneco.Boneco;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.jogador.Jogador;
import br.edu.iff.bancodepalavras.dominio.ObjetoDominioImpl;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public class Rodada {

    // Atributos essenciais da rodada
    private long id;
    private Palavra[] palavras; // Conjunto de palavras sorteadas para a rodada
    private Jogador jogador; // Jogador que realizará a rodada

    // Dependência para criação do boneco (usada no construtor ou outras operações)
    private BonecoFactory bonecoFactory;

    // Configurações padrão da rodada
    private int pontosPorLetraEncoberta = 15;
    private int pontosQuandoDescobreTodasAsPalavras = 100;
    private int maxErros = 10;
    private int maxPalavras = 3;

    // Informações adicionais da rodada (atributos de entidade)
    private int pontuacao = 0;
    private String nome; // Pode ser utilizado para identificar ou descrever a rodada

    // Construtor: somente aceita palavras e jogador
    public Rodada(long id, Palavra[] palavras, Jogador jogador) {
        this.id = id;
        this.palavras = palavras;
        this.jogador = jogador;
        // No construtor, em uma implementação completa, pode-se instanciar itens
        // utilizando bonecoFactory
    }

    // Métodos estáticos para criação e reconstituição de uma rodada

    /**
     * Cria uma nova instância de Rodada com os parâmetros fornecidos.
     */
    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    /**
     * Reconstituí uma rodada a partir dos dados persistidos.
     */
    public static Rodada reconstituir(long id, Palavra[] palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    // Getters e Setters da dependência BonecoFactory

    public BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public void setBonecoFactory(BonecoFactory bonecoFactory) {
        this.bonecoFactory = bonecoFactory;
    }

    // Getters e Setters para os parâmetros de pontuação e limites da rodada

    public void setPontosPorLetraEncoberta(int pontos) {
        this.pontosPorLetraEncoberta = pontos;
    }

    public int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }

    public void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
        this.pontosQuandoDescobreTodasAsPalavras = pontos;
    }

    public int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosQuandoDescobreTodasAsPalavras;
    }

    public void setMaxErros(int max) {
        this.maxErros = max;
    }

    public int getMaxErros() {
        return maxErros;
    }

    public void setMaxPalavras(int max) {
        this.maxPalavras = max;
    }

    public int getMaxPalavras() {
        return maxPalavras;
    }

    // Métodos de entidade (conforme o modelo de domínio)

    /**
     * Atualiza a pontuação da rodada, somando os pontos informados.
     * Em uma implementação completa, é possível atualizar também a pontuação total
     * do Jogador.
     *
     * @param pontos pontos a serem adicionados à pontuação atual da rodada.
     */
    public void atualizarPontuacao(int pontos) {
        this.pontuacao += pontos;
        // Exemplo opcional: atualizar a pontuação do jogador
        // if (jogador != null) {
        // jogador.atualizarPontuacao(pontos);
        // }
    }

    /**
     * Retorna a pontuação atual da rodada.
     *
     * @return a pontuação acumulada nesta rodada.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define o nome ou título da rodada.
     *
     * @param nome nome da rodada.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o nome ou título da rodada.
     *
     * @return o nome da rodada.
     */
    public String getNome() {
        return nome;
    }

    // Getters para atributos adicionais (id, palavras e jogador) se necessário

    public long getId() {
        return id;
    }

    public Palavra[] getPalavras() {
        return palavras;
    }

    public Jogador getJogador() {
        return jogador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Rodada [id=").append(id)
                .append(", nome=").append(nome)
                .append(", pontuacao=").append(pontuacao)
                .append(", jogador=").append(jogador)
                .append("]");
        return sb.toString();
    }
}

// Exemplo de uso
// Palavra palavra1 = Palavra.criar(1, "cachorro", Tema.ANIMAIS);
// Palavra palavra2 = Palavra.criar(2, "gato", Tema.ANIMAIS);
// Palavra[] palavras = {palavra1, palavra2};
// Jogador jogador = new Jogador(1, "Jogador 1");
// Rodada rodada = Rodada.criar(1, palavras, jogador);
// rodada.setNome("Rodada 1");
// rodada.setPontosPorLetraEncoberta(10);
// rodada.setPontosQuandoDescobreTodasAsPalavras(50);
// rodada.setMaxErros(5);
// rodada.setMaxPalavras(2);
// rodada.atualizarPontuacao(20);
// System.out.println(rodada); // Exibe: Rodada [id=1, nome=Rodada 1,
// pontuacao=20, jogador=Jogador 1]
// System.out.println("Pontuação atual: " + rodada.getPontuacao()); // Exibe:
// Pontuação atual: 20
// System.out.println("Palavras na rodada: ");
// for (Palavra p : palavras) {
// System.out.println(p); // Exibe: Palavra [id=1, palavra=cachorro,
// tema=ANIMAIS]
// System.out.println(p); // Exibe: Palavra [id=2, palavra=gato, tema=ANIMAIS]
// }
// System.out.println("Jogador: " + jogador); // Exibe: Jogador [id=1,
// nome=Jogador 1]
// System.out.println("ID do jogador: " + jogador.getId()); // Exibe: ID do
// jogador: 1
// System.out.println("Nome do jogador: " + jogador.getNome()); // Exibe: Nome
// do jogador: Jogador 1
// System.out.println("ID da rodada: " + rodada.getId()); // Exibe: ID da
// rodada: 1
// System.out.println("Nome da rodada: " + rodada.getNome()); // Exibe: Nome da
// rodada: Rodada 1
// System.out.println("Pontuação da rodada: " + rodada.getPontuacao()); //
// Exibe: Pontuação da rodada: 20
// System.out.println("Palavras na rodada: ");
// for (Palavra p : palavras) {
// System.out.println(p); // Exibe: Palavra [id=1, palavra=cachorro,
// tema=ANIMAIS]
// System.out.println(p); // Exibe: Palavra [id=2, palavra=gato, tema=ANIMAIS]
// }
