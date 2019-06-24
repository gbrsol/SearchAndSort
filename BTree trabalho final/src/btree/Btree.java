package btree;

public class Btree {

    private No raiz;
    private No cabeca;
    private static int n = 2;

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public No navegarAteFolha(int info) {
        No folha = raiz;
        int pos;
        while (folha.getvLig(0) != null) {
            pos = 0;
            while (pos < folha.getTl() && info > folha.getvInfo(pos)) {
                pos++;
            }
            folha = folha.getvLig(pos);
        }
        return folha;
    }

    public No localizarPai(No folha, int info) {
        No pai;
        No auxFolha = raiz;
        pai = auxFolha;
        int pos;
        while (auxFolha != folha) {
            pos = 0;
            while (pos < auxFolha.getTl() && info > auxFolha.getvInfo(pos)) {
                pos++;
            }

            pai = auxFolha;
            auxFolha = auxFolha.getvLig(pos);
        }
        return pai;
    }

    public void split(No folha, No pai) {
        No no1 = new No(), no2 = new No();
        int pos, i;
        for (i = 0; i < n; i++) {
            no1.setvInfo(i, folha.getvInfo(i));
            no1.setvPos(i, folha.getvPos(i));
            no1.setvLig(i, folha.getvLig(i));
        }
        no1.setvLig(n, folha.getvLig(n));
        no1.setTl(n);

        // arrumar isso aqui
        for (i = n + 1; i < 2 * n + 1; i++) {
            no2.setvInfo(i - n - 1, folha.getvInfo(i));
            no2.setvPos(i - n - 1, folha.getvPos(i));
            no2.setvLig(i - n - 1, folha.getvLig(i));
        }
        no2.setvLig(n, folha.getvLig(n * 2 + 1));
        no2.setTl(n);

        if (pai == folha) {
            folha.setvInfo(0, folha.getvInfo(n));
            folha.setvPos(0, folha.getvPos(n));
            folha.setvLig(0, no1);
            folha.setvLig(1, no2);
            folha.setTl(1);
        } else {
            pos = pai.procurarPosicao(folha.getvInfo(n));
            pai.remanejar(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setvInfo(pos, folha.getvInfo(n));
            pai.setvPos(pos, folha.getvPos(n));
            pai.setvLig(pos, no1);
            pai.setvLig(pos + 1, no2);
            if (pai.getTl() > 2 * n) {
                folha = pai;
                pai = localizarPai(folha, pai.getvInfo(pos));
                split(folha, pai);
            }
        }
    }

    public void split2(No folha, No pai) {
        No cx1, cx2;
        int pos;
        cx1 = new No();
        cx2 = new No();
        for (int i = 0; i < n; i++) // separa até metade em cx1
        {
            cx1.setvInfo(i, folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(i, folha.getvLig(i));
        }
        cx1.setvLig(n, folha.getvLig(n));
        cx1.setTl(n);
        for (int i = n + 2; i < 2 * n + 1; i++) // separa da metade até fim em cx2
        {
            cx2.setvInfo(i - n + 1, folha.getvInfo(i));
            cx2.setvPos(i - n + 1, folha.getvPos(i));
            cx2.setvLig(i - n + 1, folha.getvLig(i));
        }
        cx2.setvLig(2 * n + 1, folha.getvLig(n));
        cx2.setTl(n);
        if (pai == folha) // se pai se tornou folha
        {
            folha.setvInfo(0, folha.getvInfo(n));
            folha.setvPos(0, folha.getvPos(n));
            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);
            folha.setTl(1);
        } else // split duplo
        {
            pos = pai.procurarPosicao(folha.getvInfo(n)); // pega posicao livre no pai
            pai.remanejar(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setvInfo(folha.getvInfo(n), pos);
            pai.setvPos(folha.getvPos(n), pos);

            //fazer pai apontar pra cx1 e cx2
            pai.setvLig(pos, cx1);
            pai.setvLig(pos + 1, cx2);
            if (pai.getTl() > 2 * n) // se pai enchar, faz outro split
            {
                folha = pai;
                pai = localizarPai(folha, pai.getvInfo(pos));
                split2(folha, pai);
            }
        }
    }

    public void insere(int info) {
        No folha, pai;
        int i, pos;

        if (raiz == null) {
            raiz = new No(info);
        } else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setTl(folha.getTl() + 1);
            folha.setvInfo(pos, info);
            if (folha.getTl() > 2 * n) {
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void in_ordem(No t) {
        int i;
        if (t != null) {
            for (i = 0; i < t.getTl(); i++) {
                in_ordem(t.getvLig(i));
                System.out.println(t.getvInfo(i) + " ");
            }
            in_ordem(t.getvLig(i));
        }
    }

    public No buscaNo(int info) {
        No aux = raiz;
        while (!aux.folha()) {
            for (int i = 0; i < aux.getTl() && aux.getvInfo(i) != info; i++) {
                aux = aux.getvLig(i);
            }
        }
        return aux;
    }

    public No buscaSubE(No no, int pos) {
        No pai = localizarPai(no, no.getvInfo(pos));
        if (pos != 0) {
            return pai.getvLig(pos - 1);
        } else {
            return no;
        }
    }

    public No buscaSubD(No no, int pos) {
        No pai = localizarPai(no, no.getvInfo(pos));
        if (pos != pai.getTl() - 1) {
            return pai.getvLig(pos + 1);
        } else {
            return no;
        }
    }

    public void excluir(int info) {
        No no, subD, subE, folha, pai, vizE, vizD;
        int pos;

        no = buscaNo(info);
        if (no != null) {
            pos = no.procurarPosicao(info);
            if (!no.folha()) {
                subE = buscaSubE(no, pos);
                subD = buscaSubD(no, pos + 1);
                if (subE.getTl() > n || subD.getTl() == n) {
                    no.setvInfo(pos, subE.getvInfo(subE.getTl() - 1));
                    no.setvPos(pos, subE.getvPos(subE.getTl() - 1));
                    subE.setTl(subE.getTl() - 1);
                    folha = subE;
                } else {
                    no.setvInfo(pos, subD.getvInfo(0));
                    no.setvPos(pos, subD.getvPos(0));
                    subD.remanejar(0);
                    subD.setTl(subD.getTl() - 1);
                    folha = subD;
                }
            } else {
                folha = no;
                folha.remanejar(pos);
                folha.setTl(folha.getTl() - 1);
            }
            if (folha.getTl() < n) {
                pai = localizarPai(folha, folha.getvInfo(0));
                pos = pai.procurarPosicao(folha.getvInfo(0));
                if (pos > 0) {
                    vizE = pai.getvLig(pos - 1);
                } else {
                    vizE = null;
                }

                if (pos < pai.getTl()) {
                    vizD = pai.getvLig(pos + 1);
                } else {
                    vizD = null;
                }
                if (vizE != null && vizE.getTl() > n) {
                    int pos2 = folha.procurarPosicao(pai.getvInfo(pos - 1));
                    folha.remanejar(pos2);
                    folha.setvInfo(pos2, pai.getvInfo(pos - 1));
                    folha.setvPos(pos2, pai.getvPos(pos - 1));
                    folha.setTl(folha.getTl() + 1);
                    pai.setvInfo(pos - 1, vizE.getvInfo(vizE.getTl() - 1));
                    pai.setvPos(pos - 1, vizE.getvPos(vizE.getTl() - 1));
                    vizE.setTl(vizE.getTl() - 1);
                } else if (vizD != null && vizD.getTl() > n) {
                    int pos2 = folha.procurarPosicao(pai.getvInfo(pos - 1));
                    folha.remanejar(pos2);
                    folha.setvInfo(pos2, pai.getvInfo(pos - 1));
                    folha.setvPos(pos2, pai.getvPos(pos - 1));
                    folha.setTl(folha.getTl() + 1);
                    pai.setvInfo(pos - 1, vizD.getvInfo(vizD.getTl() - 1));
                    pai.setvPos(pos - 1, vizD.getvPos(vizD.getTl() - 1));
                    vizD.setTl(vizD.getTl() - 1);
                } else {
                    fusao(pai, folha, pos, info);
                }
            }
        }
    }

    public void fusao(No pai, No folha, int pos, int chave) {
        No aux;
        int lig;
        if (pos < pai.getTl()) {
            if (pai.getvLig(pos + 1) != null) {
                aux = pai.getvLig(pos + 1);
                int i = folha.getTl(), j = 0;
                folha.setvInfo(i, pai.getvInfo(pos));
                folha.setTl(folha.getTl() + 1);
                lig = folha.getTl();
                for (i = folha.getTl(); j < aux.getTl(); i++) {
                    folha.setvInfo(i, aux.getvInfo(j++));
                }
                folha.setTl(i);
                pai.setvLig(pos, folha);
                for (int k = pos; k < pai.getTl(); k++) {
                    pai.setvInfo(k, pai.getvInfo(k + 1));
                    pai.setvLig(k + 1, pai.getvLig(k + 2));
                }
                pai.setTl(pai.getTl() - 1);
                if (folha.getvLig(0) != null) // se for a segunda chamada 
                {
                    if (this.raiz == pai) {
                        this.raiz = folha;
                    }
                    pai = folha;
                    int k = 0;
                    for (i = lig; i <= pai.getTl(); i++) {
                        pai.setvLig(i, aux.getvLig(k));
                        pai.setvPos(i, pai.getvPos(k++));
                    }
                }
            }
        } else {
            if (pai.getvLig(pos - 1) != null) {
                aux = pai.getvLig(pos - 1);
                int i = aux.getTl(), j = 0;
                aux.setvInfo(i, pai.getvInfo(pos - 1));
                aux.setTl(aux.getTl() + 1);
                lig = aux.getTl();
                for (i = aux.getTl(); j < folha.getTl(); i++) {
                    aux.setvInfo(i, folha.getvInfo(j++));
                }
                aux.setTl(i);
                pai.setvLig(pos, folha);
                for (int k = pos; k < pai.getTl(); k++) {
                    pai.setvInfo(k, pai.getvInfo(k + 1));
                    pai.setvLig(k + 1, pai.getvLig(k + 2));
                }
                pai.setTl(pai.getTl() - 1);
                if (aux.getvLig(0) != null) {
                    pai = aux;
                    int k = 0;
                    for (i = lig; i <= pai.getTl(); i++) {
                        pai.setvLig(i, aux.getvLig(k));
                        pai.setvPos(i, pai.getvPos(k++));
                    }
                }
            }
        }
        if (pai.getTl() < n) {
            No pp = localizarPai(pai, pai.getvInfo(pos));
            int i = pai.retornaPosLig(pai.getvInfo(pai.getTl() - 1));
            fusao(pp, pai, i, chave);
        }
    }

}
