package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
  private static ElementoGraficoImagemFactory soleInstance;

  private ElementoGraficoImagemFactory() {
  }

  public static ElementoGraficoImagemFactory getSoleInstance() {
    if (ElementoGraficoImagemFactory.soleInstance == null) {
      ElementoGraficoImagemFactory.soleInstance = new ElementoGraficoImagemFactory();
    }
    return ElementoGraficoImagemFactory.soleInstance;
  }

  public Boneco getBoneco() {
    return BonecoImagemFactory.getSoleInstance().getBoneco();
  }

  public Letra getLetra(char codigo) {
    return LetraImagemFactory.getSoleInstance().getLetra(codigo);
  }

  public Letra getLetraEncoberta() {
    return LetraImagemFactory.getSoleInstance().getLetraEncoberta();
  }
}
