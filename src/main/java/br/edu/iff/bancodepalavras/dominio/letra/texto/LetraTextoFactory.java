package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraTextoFactory extends LetraFactoryImpl {
  private static LetraTextoFactory soleInstance;

  private LetraTextoFactory() {
  }

  public static LetraTextoFactory getSoleInstance() {
    if (LetraTextoFactory.soleInstance == null) {
      LetraTextoFactory.soleInstance = new LetraTextoFactory();
    }
    return LetraTextoFactory.soleInstance;
  }

  @Override
  protected Letra criarLetra(char codigo) {
    return new LetraTexto(codigo);
  }

}
