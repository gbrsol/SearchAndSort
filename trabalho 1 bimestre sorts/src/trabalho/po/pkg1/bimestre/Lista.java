/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.pkg1.bimestre;

/**
 *
 * @author Aluno
 */
public class Lista {
    private int tl;
    private No inicio,fim;
    
    //Construtores
    public Lista()
    {
        tl = 0;
        inicio = null;
        fim = null;
    }
    
    //Gets, sets e afins
    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }
    
    public No getPosNode(No start,int pos)
    {
        No aux = start;
        int i = 0;
        if(i < tl-1 && aux != null)
            while(i <= pos)
            {
                aux = aux.getProx();
                i++;
            }
        return aux;
    }
    public void insereFim(No n)
    {
        if(fim != null)
        {
            fim.setProx(n);
             n.setAnt(fim);
            fim = n;
        }
        
    }
    
    public int getIndex(No no)
    {
        No aux = inicio;
        int index = 0;
        while(aux != null && aux != no)
        {
            aux = aux.getProx();
            index++;
        }
        if(aux != null)
            return index;
        else return -1;
    }
    
    public void permuta(No no1, No no2)
    {
        int aux = no1.getInfo();
        no1.setInfo(no2.getInfo());
        no2.setInfo(aux);
    }
    
    //Ordenação
    public void insercaoDireta(int valor)
    {
        No novo = new No(valor);
        if(inicio == null )
            inicio = fim = novo;
        else
        {    
            if(inicio.getInfo() > novo.getInfo())
            {
                novo.setProx(inicio);
                inicio = novo;
            }
            else if(fim.getInfo() < novo.getInfo())
            {
                fim.setProx(novo);
                fim = novo;
            }
            else
            {
                No aux = inicio;
                while(aux != null && novo.getInfo() > aux.getInfo())
                {
                    aux = aux.getProx();
                }
                novo.setAnt(aux.getAnt());
                novo.setProx(aux.getAnt().getProx());
                novo.getAnt().setProx(novo);
            }
        }
        tl++;
    }
    
    public No buscaBinaria(int n) 
    {
        No fin = fim;
        No ini = inicio, meio = getPosNode(ini, tl/2);
        
        while(ini != fin && meio.getInfo() != n)
        {
            if(meio.getInfo() < n)
                ini = meio;
            else fin = meio;
            meio = getPosNode(inicio, (getIndex(fin)+getIndex(ini))/2);
        }
        if(meio.getInfo() == n)
            return meio;
        else if(n > meio.getInfo())
            return getPosNode(inicio, getIndex(meio)+1);
        else return getPosNode(inicio, getIndex(meio) -1);
    }
    
    public int buscaBinariaIndex(int n) 
    {
        No fin = fim;
        No ini = inicio, meio = getPosNode(ini, tl/2);
        
        while(ini != fin && meio.getInfo() != n)
        {
            if(meio.getInfo() < n)
                ini = meio;
            else fin = meio;
            meio = getPosNode(inicio, (getIndex(fin)+getIndex(ini))/2);
        }
        if(meio.getInfo() == n)
            return getIndex(meio);
        else if(n > meio.getInfo())
            return getIndex(getPosNode(inicio, getIndex(meio)+1));
        else return getIndex(getPosNode(inicio,getIndex(meio) -1));
    }
    public void insercaoBinaria(int n)
    {
        int index = buscaBinariaIndex(n);
        No aux = getPosNode(inicio, index);
        No novo = new No(n);
        if(n == aux.getInfo())
            System.out.println("Valor já existente");
        else if(n > aux.getInfo())
        {
            aux.getProx().setAnt(novo);
            aux.setProx(novo);
        }
        else
        {
            aux.getAnt().setProx(novo);
            aux.setAnt(novo);
        }
    }
    
    // Seleção direta
    public No menor(No in)
    {
       No menor = null;
       while (in != null)
       {
          if(menor.getInfo() < in.getInfo())
              menor = in;
          in = in.getProx();
       }
       return in;
    }
    
    public void selecaoDireta()
    {
        No menor;
        No aux = inicio;
        //achar o menor
        while(aux != null)
        {
            menor = menor(aux);
            permuta(aux, menor);
            aux = aux.getProx();
        }
    }

    // BubbleSort
    
    public void bubbleSort()
    {
        No noi = inicio;
        No noj = inicio.getProx(), auxAndar = noi;
        
        while(auxAndar != fim)
        {
            while(noj != fim)
            {
                permuta(noj, noi);
                noi = noj;
                noj = noj.getProx();
            }
            auxAndar = auxAndar.getProx();
        }
    }
    
    public void shakeSort()
    {
        int flag = 0;
        No noi = inicio;
        No nof = fim;
        int ini = getIndex(noi), fin = getIndex(nof);
        while(ini < fin)
        {
            for(int i = ini; i < fin; i ++)
            {
                if(noi.getInfo() > noi.getProx().getInfo())
                    permuta(noi, noi.getProx());
                noi = noi.getProx();
            }
            fin--;
            
            for(int i = fin ; i > ini; i--)
            {
                if(nof.getInfo() < nof.getAnt().getInfo())
                    permuta(nof, nof.getAnt());
                nof = nof.getAnt();
            }
            ini++;
            fin--;
            noi = getPosNode(inicio, ini);
            nof = getPosNode(inicio, fin);
        }
    }
    
    public void shellSort()
    {
        int dist,i,j, aux,k;
        for(dist = 4; dist >0 ; dist/=2)
        {
            for(i = 0; i < dist;i++)
            {
                for(j = i; j+dist >= tl-1;j+=dist)
                {
                    if(getPosNode(inicio,i).getInfo() > getPosNode(inicio,j+dist).getInfo())
                    {
                        aux = getPosNode(inicio,j).getInfo();
                        getPosNode(inicio,j).setInfo(getPosNode(inicio,j+dist).getInfo());
                        getPosNode(inicio,j+dist).setInfo(aux);
                        for(k = j; k >=0 && getPosNode(inicio,k).getInfo() < getPosNode(inicio,k - dist).getInfo(); k-=dist)
                        {
                            aux = getPosNode(inicio,k).getInfo();
                            getPosNode(inicio,k).setInfo(getPosNode(inicio,k+dist).getInfo());
                            getPosNode(inicio,k+dist).setInfo(aux);
                        }
                    }
                }
            }
        }
    }
    
    
    //Quicksort
    
    public void quickSortSemPivo(No ini, No fin)
    {
        No i = ini;
        No j = fin;
        
        while(i != j)
        {
            while(i.getInfo() <= j.getInfo() && i != j)
                i = i.getProx();
            permuta(i, j);
            
            while(j.getInfo() >= i.getInfo() && i != j)
                j = j.getAnt();
            permuta(i, j);
        }
        if(ini != i.getAnt())
            quickSortSemPivo(ini,i.getAnt());
        if(j.getProx() != fin)
            quickSortSemPivo(j.getProx(),fin);
    }
    
    public void quickSortComPivo(No ini, No fin)
    {
        No pivo = getPosNode(inicio, (getIndex(ini)+getIndex(fin))/2);
        No i = ini, j = fin;
        
        while(i != j)
        {
            while(i.getInfo() < pivo.getInfo())
                i = i.getProx();
            while(j.getInfo() > pivo.getInfo())
                j = j.getAnt();
            if(i != j)
            {
                permuta(i, j);
                i = i.getProx();
                j = j.getAnt();
            }
        }
        if(ini != j)
            quickSortComPivo(ini, j);
        if(i != fin)
            quickSortComPivo(i, fin);
    }
    
    // MergeSort( tem umas partinhas já feitas)
    
    public void mergeSortPrimeira(){
        int seq = 1, aux_seq = seq, aux_seq1 = seq;
        int i, meio = tl / 2, k, j;
        No[] vet1 = new No[tl / 2];
        No[] vet2 = new No[tl / 2];
        while(seq < tl)
        {
            for(i = 0; i < meio; i++)
            {
                if(i == 0)
                    vet1[i] = inicio;
                else
                    vet1[i] = getPosNode(inicio, i);
                
                vet2[i] = getPosNode(inicio, i + meio);
            }
            
            aux_seq = aux_seq1 = seq;
            i = k = j = 0;
            inicio = null;
            while(aux_seq < tl)
            {
                while(i < aux_seq && j < aux_seq)
                {
                    if(vet1[i].getInfo() < vet2[j].getInfo())
                    {
                        insereFim(vet1[i]);
                        i++;
                    }
                    else
                    {
                        insereFim(vet2[j]);
                        j++;
                    }
                }
                
                while(i < aux_seq)
                {
                    insereFim(vet1[i]);
                    i++;
                }
                
                while(j < aux_seq)
                {
                    insereFim(vet2[j]);
                    j++;
                }
                
                aux_seq += aux_seq1;
            }
            
            seq += seq;
        }
    }
    
      public void mergeSortSegunda(Lista l, No ini, No fim)
    {
        Lista aux = new Lista();
        No meio;
        if(getIndex(ini) > getIndex(fim)){
            meio = getPosNode(inicio,(getIndex(ini) - getIndex(ini.getProx())) / 2);
            
            mergeSortSegunda(aux, ini, meio);
            mergeSortSegunda(aux, meio.getProx(), fim);
            
            fusao(aux, ini, meio, meio.getProx(), fim);
        }
    }
   
    
    public void fusao(Lista aux, No ini1, No fim1, No ini2, No fim2)
    {
        No x;
        No aux1 = ini1, aux2 = ini2;
        
        while(aux1 != fim1 && aux2 != fim2)
        {
            if(aux1.getInfo() > aux2.getInfo())
            {
                aux.insereFim(ini1);
                aux1 = aux1.getProx();
            }else
            {
                aux.insereFim(aux2);
                aux2 = aux2.getProx();
            }
        }
        
        while(aux1 != fim1)
        {
            aux.insereFim(aux1);
            aux1 = aux1.getProx();
        }
        
        while(aux2 != fim2)
        {
            aux.insereFim(aux2);
            aux2 = aux2.getProx();
        }
        
        x = aux.getInicio();
        for(int i = 0; x != aux.getFim(); i++)
        {
            permuta(x, getPosNode(inicio, getIndex(ini1) + i));
            x = x.getProx();
        }
    }
public void heap()
    {
        int n, tam = 0;
        No pai;
        No fd, fe, fmaior;
        No fin = inicio;
        
        while(fin.getProx() != null)
        {
            fin = fin.getProx();
            tl++;
        }
        tl ++;
        
        while(fin != inicio)
        {
            for(n = tl / 2 - 1; n >= 0; n--)
            {
                if(n == 0)
                    pai = inicio;
                else                    
                    pai = getPosNode(inicio, n);
                fe = getPosNode(inicio, n + n + 1);
                fd = getPosNode(fe, 1);

                fmaior = fe;

                if(n + n + 2 < tl && fd.getInfo() > fe.getInfo())
                    fmaior = fd;

                if(fmaior.getInfo() > pai.getInfo())
                    permuta(pai, fmaior);
            }
            
            permuta(inicio, fin);
            
            tl--;
            fin = fin.getAnt();
        }
    }
    //---------------------------------------------
    public void countSort(int mag){
        int[] cvet = new int[10];
        No aux = inicio;
        Lista auxvet = new Lista();
        No[] temp = new No[getTl()];
        int div = 1;
        for(int i=0; i<mag; i++){
            div*=10;
        }
        while(aux != fim){
            cvet[aux.getInfo()%div]++;
        }
        for(int i=9; i>0; i--){
            cvet[i] += cvet[i-1];
        }
        aux = inicio;
        for(int i=0; i<tl; i++){
            temp[cvet[aux.getInfo()%div]]= new No(aux.getInfo());
        }
        aux = inicio;
        for(int i=0; i< tl; i++){
            permuta(aux, temp[i]);
            aux = aux.getProx();
        }

    }
    
    public void radixSort(int max){
        int mag = (int) (Math.log10(max) + 1);
        
        for(int i = mag; i> 0; i--){
            countSort(i);
        }
    }
  
    public void tempInsercaoVetor(No[] vet){
        int pos;
		No aux;
        for(int i=1; i<vet.length; i++){
            pos = i;
            aux = vet[i];
            while(pos>0 && aux.getInfo() < vet[pos-1].getInfo()){
                permuta(vet[pos], vet[pos-1]);
                pos--;
            }
            vet[pos]=aux;
        }
    }
    
    public void bucketSort(){
        No[][] mataux = new No[10][tl];
        int[] maxpos = new int[10];
        No aux = inicio;
        int dig;
        while(aux!= fim){
            dig = aux.getInfo()%10;
            mataux[dig][maxpos[dig]++] = aux;
        }
        for(int i=0; i<10; i++){
            tempInsercaoVetor(mataux[i]);
        }
        
        aux = inicio;
        for(int i=0; i < 10; i++){
            for(int j=0; j<maxpos[i]; j++){
                permuta(aux, mataux[i][j]);
            }
        }
    }
    
    public void timSort(){
        
    }
    
    public void combSort(){
        No aux = inicio;
        No aux2;
        boolean troca = false;
        int fator = (int) (tl/1.3);
        do{
            while(aux != fim){
                aux2 = getPosNode(inicio, getIndex(aux)+fator);
                if(aux.getInfo() < aux2.getInfo()){
                    permuta(aux, aux2);
                    troca = true;
                }
            }
            fator = (int) (fator / 1.3);
        }while(troca);
    }
    
    public void gnomeSort(){
        No aux = inicio;
        while(aux.getProx()!=null){
            if(aux.getInfo()> aux.getProx().getInfo()){
                permuta(aux, aux.getProx());
                aux = aux.getAnt();
            }
            else{
                aux = aux.getProx();
            }
        }
    }
}


