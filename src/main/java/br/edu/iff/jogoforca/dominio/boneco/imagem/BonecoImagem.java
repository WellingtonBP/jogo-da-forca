package br.edu.iff.jogoforca.dominio.boneco.imagem;

public class BonecoImagem implements Boneco {
    private static BonecoImagem soleInstance;
    private long id;

    // Construtor privado
    private BonecoImagem() {
        this.id = 2; // Outro id para diferenciar
    }

    // Retorna a única instância de BonecoImagem
    public static BonecoImagem getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagem();
        }
        return soleInstance;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void exibir(Object contexto, int partes) {
        // Exemplo: pode-se implementar a exibição a partir de imagens.
        if (partes == 0) {
            System.out.println("sem boneco.");
        } else {
            System.out.println("o boneco está com " + partes);
        }
    }
}
// Exemplo de uso
// BonecoImagem boneco = BonecoImagem.getSoleInstance();
// boneco.exibir(null, 3); // Exibe: "o boneco está com: 3"
// System.out.println("ID do boneco: " + boneco.getId()); // Exibe: "ID do
// boneco: 2"
// BonecoImagem boneco2 = BonecoImagem.getSoleInstance();
// System.out.println(boneco == boneco2); // Exibe: "true", pois é a mesma
// instância
