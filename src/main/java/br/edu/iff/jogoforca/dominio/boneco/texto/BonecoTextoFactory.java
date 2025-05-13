package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoTextoFactory implements BonecoFactory {
  private static BonecoTextoFactory soleInstance;

  private BonecoTextoFactory() {
  }

  public static BonecoTextoFactory getSoleInstance() {
    if (BonecoTextoFactory.soleInstance == null) {
      BonecoTextoFactory.soleInstance = new BonecoTextoFactory();
    }
    return BonecoTextoFactory.soleInstance;
  }

  @Override
  public Boneco getBoneco() {
    return BonecoTexto.getSoleInstance();
  }

}
