package br.edu.iff.boneco;
public interface BonecoFactory {
    Boneco getBoneco();
}

// Exemplo de uso
// BonecoFactory bonecoFactory = new BonecoTextoFactory();
// Boneco boneco = bonecoFactory.getBoneco();
// boneco.exibir(null, 3); // Exibe: "o boneco está com: 3"
// System.out.println("ID do boneco: " + boneco.getId()); // Exibe: "ID do boneco: 1"
// BonecoFactory bonecoFactory2 = new BonecoImagemFactory();
// Boneco boneco2 = bonecoFactory2.getBoneco();
// boneco2.exibir(null, 3); // Exibe: "o boneco está com: 3"    
// System.out.println("ID do boneco: " + boneco2.getId()); // Exibe: "ID do boneco: 2"
// BonecoFactory bonecoFactory3 = new BonecoTextoFactory();
// Boneco boneco3 = bonecoFactory3.getBoneco();
// System.out.println(boneco == boneco3); // Exibe: "false", pois são instâncias diferentes