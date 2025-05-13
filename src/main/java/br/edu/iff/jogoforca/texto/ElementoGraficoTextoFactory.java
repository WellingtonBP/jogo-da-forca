package br.edu.iff.jogoforca.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {
  private static ElementoGraficoTextoFactory soleInstance;

  private ElementoGraficoTextoFactory() {
  }

  public static ElementoGraficoTextoFactory getSoleInstance() {
    if (ElementoGraficoTextoFactory.soleInstance == null) {
      ElementoGraficoTextoFactory.soleInstance = new ElementoGraficoTextoFactory();
    }
    return ElementoGraficoTextoFactory.soleInstance;
  }

  public Boneco getBoneco() {
    return BonecoTextoFactory.getSoleInstance().getBoneco();
  }

  public Letra getLetra(char codigo) {
    return LetraTextoFactory.getSoleInstance().getLetra(codigo);
  }

  public Letra getLetraEncoberta() {
    return LetraTextoFactory.getSoleInstance().getLetraEncoberta();
  }
}
