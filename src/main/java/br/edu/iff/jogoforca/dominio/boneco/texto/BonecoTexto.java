package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.Boneco;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance;

    private static String[] partesBoneco = new String[] {
            "Cabeça",
            "Olho esquerdo",
            "Olho direito",
            "Nariz",
            "Boca",
            "Tronco",
            "Braço esquerdo",
            "Braço direito",
            "Perna esqueda",
            "Perna direita"
    };

    private BonecoTexto() {
    }

    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTexto();
        }
        return soleInstance;
    }

    @Override
    public void exibir(Object ignore, int partes) {
        for (int i = 0; i < partes; i++) {
            System.out.println(partesBoneco[i]);
        }
    }
}