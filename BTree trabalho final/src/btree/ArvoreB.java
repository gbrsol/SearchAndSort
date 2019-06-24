/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

/**
 *
 * @author gabriel
 */
public class ArvoreB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Btree arvore = new Btree();
        BPTree arvore = new BPTree();
        for(int i = 0;i<5;i++)
            arvore.insere(i);
        //arvore.in_ordem(arvore.getRaiz());
        //arvore.excluir(2);
        //arvore.in_ordem(arvore.getRaiz());
        arvore.exibe();
    }
    
}
