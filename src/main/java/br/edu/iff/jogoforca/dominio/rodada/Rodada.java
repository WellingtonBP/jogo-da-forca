package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl {
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;
    private static BonecoFactory bonecoFactory;

    private Boneco boneco;
    private Palavra[] palavras;
    private Jogador jogador;
    private Item[] items;
    private Letra[] erradas;
    private Letra[] certas;
    private Tema tema;

    private Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);
        this.palavras = palavras;
        this.jogador = jogador;
        this.boneco = Rodada.bonecoFactory.getBoneco();
        this.items = new Item[this.palavras.length];
        this.tema = this.palavras.length != 0 ? this.palavras[0].getTema() : null;
        for (int i = 0; i < this.palavras.length; i++) {
            this.items[i] = Item.criar((long) i, this.palavras[i]);
        }
    }

    private Rodada(long id, Item[] items, Letra[] erradas, Jogador jogador) {
        super(id);
        this.items = items;
        this.erradas = erradas;
        this.jogador = jogador;
        this.boneco = Rodada.bonecoFactory.getBoneco();
    }

    public static BonecoFactory getBonecoFactory() {
        return Rodada.bonecoFactory;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory) {
        Rodada.bonecoFactory = bonecoFactory;
    }

    public static int getMaxPalavras() {
        return Rodada.maxPalavras;
    }

    public static int getMaxErros() {
        return maxErros;
    }

    public static void setMaxErros(int max) {
        Rodada.maxErros = max;
    }

    public static void setMaxPalavras(int max) {
        Rodada.maxPalavras = max;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return Rodada.pontosQuandoDescobreTodasAsPalavras;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) {
        Rodada.pontosQuandoDescobreTodasAsPalavras = pontos;
    }

    public static int getPontosPorLetraEncoberta() {
        return Rodada.pontosPorLetraEncoberta;
    }

    public static void setPontosPorLetraEncoberta(int pontos) {
        Rodada.pontosPorLetraEncoberta = pontos;
    }

    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
        if (Rodada.bonecoFactory != null) {
            return new Rodada(id, palavras, jogador);
        }
        return null;
    }

    public static Rodada reconstituir(long id, Item[] items, Letra[] erradas, Jogador jogador) {
        if (Rodada.bonecoFactory != null) {
            return new Rodada(id, items, erradas, jogador);
        }
        return null;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public Tema getTema() {
        return this.tema;
    }

    public Palavra[] getPalavras() {
        return palavras;
    }

    public int getNumPalavras() {
        return palavras.length;
    }

    public void tentar(char codigo) {
        var item = items[this.palavras.length - 1];

        if (!item.tentar(codigo)) {
            int letraErradaPos = 0;
            if (this.erradas == null) {
                this.erradas = new Letra[1];
            } else {
                Letra[] novo = new Letra[this.erradas.length];
                letraErradaPos = this.erradas.length;
                System.arraycopy(this.erradas, 0, novo, 0, this.erradas.length);
                this.erradas = novo;
            }
            this.erradas[letraErradaPos] = Palavra.getLetraFactory().getLetra(codigo);
        } else {
            int letraCertaPos = 0;
            if (this.certas == null) {
                this.certas = new Letra[1];
            } else {
                Letra[] novo = new Letra[this.certas.length];
                letraCertaPos = this.certas.length;
                System.arraycopy(this.certas, 0, novo, 0, this.certas.length);
                this.certas = novo;
            }
            this.certas[letraCertaPos] = Palavra.getLetraFactory().getLetra(codigo);
        }
    }

    public void arriscar(String[] palavras) {
        for (int i = 0; i < this.items.length; i++) {
            this.items[i].arriscar(palavras[i]);
        }
    }

    public void exibirItens(Object contexto) {
        for (var item : this.items) {
            item.exibir(contexto);
        }
    }

    public void exibirBoneco(Object contexto) {
        this.boneco.exibir(contexto, this.erradas != null ? this.erradas.length : 0);
    }

    public void exibirPalavras(Object contexto) {
        for (var palavra : this.palavras) {
            palavra.exibir(contexto);
        }
    }

    public void exibirLetrasErradas(Object contexto) {
        for (var letraErrada : this.erradas) {
            letraErrada.exibir(contexto);
        }
    }

    public Letra[] getTentativas() {
        Letra[] tentativas = new Letra[this.getQtdeTentativas()];

        System.arraycopy(this.certas, 0, tentativas, 0, this.certas.length);
        System.arraycopy(this.erradas, 0, tentativas, this.certas.length, this.erradas.length);

        return tentativas;
    }

    public Letra[] getCertas() {
        return this.getCertas().clone();
    }

    public Letra[] getErradas() {
        return this.getErradas().clone();
    }

    public int calcularPontos() {
        int pontos = 0;
        if (this.descobriu()) {
            pontos += 100;
            for (var item : items) {
                pontos += item.getLetrasEncobertas().length * Rodada.pontosPorLetraEncoberta;
            }
        }
        return pontos;
    }

    public boolean encerrou() {
        return this.arriscou() || this.descobriu() || this.erradas.length == Rodada.maxErros;
    }

    public boolean descobriu() {
        boolean descobriu = true;
        for (var item : this.items) {
            descobriu = descobriu && item.descobriu();
        }
        return descobriu;
    }

    public boolean arriscou() {
        boolean arriscou = false;
        for (var item : this.items) {
            arriscou = arriscou || item.arriscou();
        }
        return arriscou;
    }

    public int getQtdeTentativasRestantes() {
        return Rodada.maxErros - this.erradas.length;
    }

    public int getQtdeErros() {
        return this.erradas.length;
    }

    public int getQtdeAcertos() {
        int qtd = 0;
        for (var item : items) {
            if (item.acertou()) {
                qtd++;
            }
        }
        return qtd;
    }

    public int getQtdeTentativas() {
        return this.erradas.length + this.certas.length;
    }
}
