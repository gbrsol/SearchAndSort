/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

/**
 *
 * @author Aluno
 */
public class BPTree {
    private NoBP raiz;
    private static final int n = 4;

    public BPTree() {
        raiz = null;
    }

    public NoBP getRaiz() {
        return raiz;
    }

    public void setRaiz(NoBP raiz) {
        this.raiz = raiz;
    }
    
    
    public NoBP localizarPai(NoBP folha, int info) {
        NoBP pai;
        NoBP auxFolha = raiz;
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
    
    public void split(NoBP folha, NoBP pai, int info)
    {
        NoBP cx1,cx2;
        cx1 = new NoBP();
        cx2 = new NoBP();
        int poslig;
        if(folha.getvLig(0)==null)
            poslig=(int) Math.round((float)(n-1)/2);
        else
            poslig=(int)(Math.round((float) n/2)-1);
        for(int i=0;i<poslig;i++)
        {
            cx1.setvInfo(i,folha.getvInfo(i));
            cx1.setvLig(i,folha.getvLig(i));
        }
        cx1.setvLig(poslig,folha.getvLig(poslig));
        cx1.setTl(poslig);
        for(int i=poslig;i<folha.getTl();i++)
        {
            cx2.setvInfo(i-poslig, folha.getvInfo(i));
            cx2.setvLig(i-poslig, folha.getvLig(i));
        }
        cx2.setTl(folha.getTl()-poslig);
        cx2.setvLig(cx2.getTl(),folha.getvLig(folha.getTl()));
        
        if (pai==folha)
        {
            if(folha.getvLig(0)==null)
                cx1.setProx(cx2);
            else
                cx2.remanejar(0);
            folha.setvInfo(0,folha.getvInfo(poslig));
            folha.setvLig(0,cx1);
            folha.setvLig(1,cx2);
            folha.setTl(1);
        }
        else
        {
            info=folha.getvInfo(poslig);
            int pos=pai.procurarPosicao(info);
            pai.remanejar( pos);
            if(folha.getvLig(0)==null)
            {
                cx1.setProx(cx2);
                if(pos==pai.getTl())
                    pai.getvLig(pos-1).setProx(cx1);
                else
                    if(pos>0&&pos<pai.getTl())
                    {
                        pai.getvLig(pos-1).setProx(cx1);
                        cx2.setProx(pai.getvLig(pos+2));
                    }
            }
            else
                cx2.remanejar(0);
            pai.setTl(pai.getTl()+1);
            pai.setvInfo(pos, folha.getvInfo(poslig));
            pai.setvLig(pos, cx1);
            pai.setvLig(pos+1, cx2);
            //folha=null;
            if(pai.getTl()>n*2)
            {
                folha=pai;
                info=folha.getvInfo(poslig);
                pai=localizarPai(pai, info);
                split(folha, pai, info);
            }
        }
    }
    
    public NoBP navegarAteFolha(int info) {
        NoBP folha = raiz;
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
    
    
    public void split(NoBP folha, NoBP pai) {
        NoBP no1 = new NoBP(), no2 = new NoBP();
        int pos = 0, i;
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
        //ligacao de folhas
        if(pos == 0)
        {
            folha.setProx(pai.getvLig(pos+1));
            folha.getProx().setAnt(folha);
        }
        else if(pos == pai.getTl()-1)
        {
            folha.setAnt(pai.getvLig(pos-1));
        }
        else
        {
            folha.setProx(pai.getvLig(pos+1));
            folha.setAnt(pai.getvLig(pos-1));
        }
    }
    
    
    public void insere(int info)
    {
        NoBP folha=null, pai;
        int pos;
        if(raiz==null)
        {
            raiz=new NoBP();
            raiz.setvInfo(0, info);
            raiz.setTl(1);
        }
        else
        {
            folha=navegarAteFolha(info);
            pos=folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setvInfo(pos, info);
            folha.setTl(folha.getTl()+1);
            if(folha.getTl()> 2*n)
            {
                pai=localizarPai(folha,info);
                split(folha, pai, info);
            }
        }
    }
    public void in_ordem(NoBP t)
    {
        int i;
        if(t != null)
        {
            for(i = 0 ; i < t.getTl();i++)
            {
                in_ordem(t.getvLig(i));
                System.out.println(t.getvInfo(i) + " ");
            }
            in_ordem(t.getvLig(i));
        }
    }
    
    public void exibe()
    {
        NoBP aux = raiz;
        while(!aux.folha())
            aux = aux.getvLig(0);
        while(aux != null)
        {
            System.out.print("[");
            for(int i = 0; i < aux.getTl(); i++)
                System.out.print(aux.getvInfo(i) + " ");
            System.out.print("]");
            aux=aux.getProx();
        }
    }
}
