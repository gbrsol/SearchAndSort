package trabalho.po.pkg1.bimestre;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;


public class Arquivo
{
  private String nomearquivo;
  private RandomAccessFile arquivo;
  private int comp, mov;

  public Arquivo(String nomearquivo) throws FileNotFoundException
  {
    try{
    this.nomearquivo = nomearquivo;
    arquivo = new RandomAccessFile(nomearquivo,"rw");
    }
    catch(FileNotFoundException e){}
  }
  public boolean eof() throws IOException
  {
      return ftell() == filesize()*2048;
  }
  public void copiaArquivo(RandomAccessFile arquivoOrigem)
  {
    Registro r = new Registro();
    try{
        r.leDoArq(arquivoOrigem);
    while(!eof())
    {
        r.gravaNoArq(arquivo);
      r.leDoArq(arquivoOrigem);
      
    }
    }
    catch(IOException e)
    {}
  }
  public RandomAccessFile getFile()
  {
    return arquivo;
  }
  public void truncate(long pos) throws IOException
  {
    arquivo.setLength(pos*Registro.length());
  }
  public long ftell() throws IOException
  {
    return arquivo.getFilePointer();
  }
  
  public void seekArq(int pos) throws IOException
  {
    arquivo.seek(pos*Registro.length());
  }
  public long filesize() throws IOException
  {
    return arquivo.length()/Registro.length();
  }

  public void initComp()
  {
    comp = 0;
  }

  public void initMov()
  {
    mov = 0;
  }

  public int getComp()
  {
    return comp;
  }
  public int getMov()
  {
    return mov;
  }

  public void insercaoDireta() throws IOException
  {
    Registro reg = new Registro(), lido = new Registro(), lido2 = new Registro();
    int pos = 0, i;
    try{

    seekArq((int) (filesize() - 1));
    reg.leDoArq(getFile()); // pega ultimo reg

    seekArq(pos++);
    lido.leDoArq(getFile());

    while(reg.getNumero() > lido.getNumero())
    {
      seekArq(pos++);
      lido.leDoArq(getFile());
      comp++;
      mov++;
    }

    i = (int) (ftell() -1);
    lido2.leDoArq(getFile());

    while(i < filesize())
    {
      seekArq(i++);
      lido.leDoArq(getFile());

      seekArq(i);
      lido2.leDoArq(getFile());

      seekArq(i);
      lido.gravaNoArq(getFile());
      lido2.gravaNoArq(getFile());
      mov++;
    }

    seekArq(pos);
    reg.gravaNoArq(getFile());
    mov++;
    }
    catch(IOException e)
    {}
  }

  public void selecaoDireta() throws IOException
  {
    Registro reg = new Registro(), aux = new Registro();
    int tl = (int) filesize(),pos;

    for(int i =0;i < tl; i++)
    {
      pos = i;
      seekArq(pos);
      reg.leDoArq(arquivo);

      for(int j = i+1; j<tl;j++)
      {
        seekArq(j);
        aux.leDoArq(getFile());

        if(reg.getNumero() > aux.getNumero())
        {
          pos = j;
          seekArq(j);
          reg.leDoArq(getFile());
        }
        comp++;
      }
      // pegar vPos i
      seekArq(i);
      aux.leDoArq(getFile());
      // gravar vPos pos
      seekArq(pos);
      aux.gravaNoArq(getFile());
      //gravar vPos i
      seekArq(i);
      reg.gravaNoArq(getFile());
      comp+=2;
    }
  }

  public int buscaBinaria(int valor) throws IOException
  {
    int fim = (int) (filesize() - 1), inicio = 0, meio = fim/2;
    Registro reg = new Registro();

    seekArq(meio);
    reg.leDoArq(getFile());

    comp++;// comparaÃ§Ã£o inicial de valor do meio
    while(inicio != fim && reg.getNumero() != valor)
    {
      if(reg.getNumero() < valor)
        inicio = meio;
      else fim = meio;
      meio = (inicio+fim)/2;

      seekArq(meio);
      reg.leDoArq(getFile());
      comp+=2; // faz com ambos os lados
    }

    seekArq(meio);
    reg.leDoArq(getFile());
    comp++;
    if(reg.getNumero() > valor)
      return meio+1;
    else
      return meio;
  }

  public void insercaoBinaria() throws IOException
  {
    int pos, tl = (int) filesize();
    Registro reg = new Registro(),reg1 = new Registro() , reg2 = new Registro();

    for(int i = 1; i < tl;i++)
    {
      seekArq(i);
      reg.leDoArq(getFile());
      pos = buscaBinaria(reg1.getNumero());
      for(int j = i ; j > pos ; j--)
      {
        // permuta
        seekArq(j-1);
        reg1.leDoArq(getFile());
        reg2.leDoArq(getFile());

        seekArq(j-1);
        reg2.gravaNoArq(getFile());
        reg1.gravaNoArq(getFile());

        mov+=2;
      }
    }
  }
  public void bubbleSort() throws IOException
  {
    int tl = (int) filesize();
    Registro reg1, reg2;
    reg1 = new Registro();
    reg2 = new Registro();

    for(int i = 0 ; i < tl; i++)
    {
      for(int j = 0 ; j < tl; j++)
      {
        seekArq(i);
        reg1.leDoArq(getFile());
        reg2.leDoArq(getFile());

        comp++;
        if(reg1.getNumero() > reg2.getNumero())
        {
          seekArq(i);
          reg2.gravaNoArq(getFile());
          reg1.gravaNoArq(getFile());

          mov+=2;
        }
      }
    }
  }
  public void shakeSort() throws IOException
  {
    int inicio = 0, fim = (int) filesize(),i;
    Registro reg1 = new Registro() , reg2 = new Registro();

    for(;inicio < fim; inicio++, fim--)
    {
      for(i = inicio; i <fim-1;i++)//vai
      {
        seekArq(i);
        reg1.leDoArq(getFile());
        reg2.leDoArq(getFile());

        comp++;
        if(reg1.getNumero() > reg2.getNumero())
        {
          seekArq(i);
          reg2.gravaNoArq(getFile());
          reg1.gravaNoArq(getFile());
          mov+=2;
        }
      }

      for( ; i > inicio; i--) // volta
      {
        seekArq(i);
        reg1.leDoArq(getFile());
        reg2.leDoArq(getFile());

        comp++;
        if(reg1.getNumero() > reg2.getNumero())
        {
          seekArq(i);
          reg2.gravaNoArq(getFile());
          reg1.gravaNoArq(getFile());

          mov+=2;
        }
      }

    }
  }

  public void heapSort() throws IOException
  {
    int pai, fd, fe, maiorF, tl = (int) filesize();
    Registro regE = new Registro(), regD = new Registro(),regPai = new Registro(), regMaior = new Registro();

    while(tl > 1)
    {
      for(pai = tl/2 -1; pai>=0 ; pai--)
      {
        fe = pai+pai+1;
        fd = fe+1;
        maiorF = fe;

        //comparar filhos
        seekArq(fe);
        regE.leDoArq(getFile());

        seekArq(fd);
        regD.leDoArq(getFile());

        comp++;
        if(fd<tl && regE.getNumero() < regD.getNumero())
          maiorF = fd;

        //comparar pai e maiores
        seekArq(pai);
        regPai.leDoArq(getFile());

        seekArq(maiorF);
        regMaior.leDoArq(getFile());

        comp++;
        if(regMaior.getNumero() > regPai.getNumero())
        {
          seekArq(pai);
          regMaior.gravaNoArq(getFile());

          seekArq(maiorF);
          regPai.gravaNoArq(getFile());

          mov+=2;
        }
        //trocar primero com ultimo
        seekArq(0);
        regD.leDoArq(getFile());

        seekArq(tl-1);
        regE.leDoArq(getFile());

        seekArq(0);
        regE.gravaNoArq(getFile());

        seekArq(tl-1);
        regD.gravaNoArq(getFile());
        mov+=2;
      }

      tl--;
    }
  }

  public void shellSort() throws IOException
  {
    int i,j,k, dist = 4, tl = (int) filesize();
    Registro reg1 = new Registro(), reg2 = new Registro();

    for(; dist > 0 ; dist /=2)
      for(i = 0; i < dist; i++)
      {
        for(j = i; j+dist > tl-1;j+=dist)
        {
          seekArq(i);
          reg1.leDoArq(getFile());
          seekArq(j+dist);
          reg2.leDoArq(getFile());

          comp++;
          if(reg1.getNumero() > reg2.getNumero())
          {
            seekArq(i);
            reg2.gravaNoArq(getFile());
            seekArq(j+dist);
            reg1.gravaNoArq(getFile());
            mov+=2;

            seekArq(j);
            reg1.leDoArq(getFile());
            seekArq(j-dist);
            reg2.leDoArq(getFile());

            for(k = j; k>=0 && reg1.getNumero() < reg2.getNumero();k-=dist)
            {
              seekArq(k);
              reg2.gravaNoArq(getFile());
              seekArq(k+dist);
              reg1.gravaNoArq(getFile());
              comp++;
              mov+=2;
            }
          }
        }
      }

  }
  
  public void fusao(int ini, int meio, int fim) throws FileNotFoundException, IOException
  {
      Arquivo auxarq = new Arquivo("AuxF.dat");
      Registro reg1 = new Registro(), reg2 = new Registro();
      
      seekArq(0);
      auxarq.seekArq(0);
      for(int i = ini; i<=fim;i++)
      {
          seekArq(i);
          reg1.leDoArq(getFile());
          reg1.gravaNoArq(auxarq.getFile());
          reg1.gravaNoArq(auxarq.getFile());
          
      }
      
      int i = ini, j = meio+1, k = ini;
      while(i<=meio && j<=fim)
      {
          auxarq.seekArq(i);
          reg1.leDoArq(auxarq.getFile());
          reg2.leDoArq(auxarq.getFile());
          if(reg1.getNumero() < reg2.getNumero())
          {
              seekArq(k);
              reg1.gravaNoArq(getFile());
              i++;
          }
          else
          {
              seekArq(k);
              reg2.gravaNoArq(getFile());
              j++;
          }
          k++;
      }
      while(j<=fim)
      {
          seekArq(k);
          reg2.leDoArq(getFile());
          j++;
          k++;
          reg2.gravaNoArq(getFile());
      }
     
  }
  public void mergeSortII(int ini, int fim) throws IOException
  {
      if(ini<fim && ini>=0 && fim<filesize() && filesize() != 0)
      {
          int meio = (fim+ini)/2;
          mergeSortII(ini,meio);
          mergeSortII(meio+1,fim);
          fusao(ini,meio,fim);
      }
  }

  
  //rafa falta fazer
  public void particao(Arquivo L1,Arquivo L2)
{
	Registro reg = new Registro();
        try{
	int i, meio = (int) (filesize()/2);
	seekArq(0);
	for(i=0; i<meio; i++)
	{
		reg.leDoArq(getFile());
		reg.gravaNoArq(L1.getFile());
		seekArq(meio+i);
		reg.leDoArq(getFile());
		reg.gravaNoArq(L2.getFile());
	}
        }
        catch(IOException e){}
}

public void fusao(Arquivo L1, Arquivo L2, int seq)
{
	int i,j,k, auxseq = seq;
        try{
	Arquivo res = new Arquivo("res.dat");
	i = j = k = 0;
	Registro reg1 = new Registro();
	Registro reg2 = new Registro();
	while( k < filesize())
	{
		while(i < seq && j < seq)
		{
			L2.seekArq(0);
			L1.seekArq(0);
			reg2.leDoArq(L2.getFile());
			reg1.leDoArq(L1.getFile());
			if( reg2.getNumero()< reg1.getNumero())
			{
				reg2.gravaNoArq(res.getFile());
				reg2.leDoArq(L2.getFile());
				j++;
			}
			else
			{
				reg1.gravaNoArq(res.getFile());
				reg1.leDoArq(L1.getFile());
				i++;
			}	
		}
		while(i < seq){
			reg1.gravaNoArq(res.getFile());
			reg1.leDoArq(L1.getFile());
			i++;
		}
		while(j < seq){
			reg2.gravaNoArq(res.getFile());
			reg2.leDoArq(L2.getFile());
			j++;
		}
		seq = seq+auxseq;
	}
        }
        catch(IOException e)
        {}
}

    public void mergeSortI()
    {
            int seq = 1;try{
            Arquivo L1 = new Arquivo("L1.dat");
            Arquivo L2 = new Arquivo("L2.dat");

            while(seq <= (int) (filesize()/2)) // ou seq < tl
            {
                    particao(L1,L2);
                    fusao(L1,L2,seq);
                    seq *=2;
            }
            }
            catch(IOException e){}
    }
  
  public void quickSortSemPivo(int ini, int fim) throws IOException
  {
    int i = ini, j = fim;
    Registro reg1 = new Registro(), reg2 = new Registro();
    while(i < j)
    {
      seekArq(i);
      reg1.leDoArq(getFile());
      seekArq(j);
      reg2.leDoArq(getFile());

      while(reg1.getNumero() <= reg2.getNumero() && i < j)
      {
        seekArq(i);
        reg1.leDoArq(getFile());
        i++;
        comp++;
      }
      seekArq(i);
      reg2.gravaNoArq(getFile());
      seekArq(j);
      reg1.gravaNoArq(getFile());
      mov+=2;

      comp++;
      while(reg2.getNumero() >= reg1.getNumero() && i < j)
      {
        seekArq(j);
        reg2.leDoArq(getFile());
        j--;
        comp++;
      }
      seekArq(i);
      reg2.gravaNoArq(getFile());
      seekArq(j);
      reg1.gravaNoArq(getFile());
      mov+=2;

      if(ini <i-1)
        quickSortSemPivo(ini,i-1);
      if(j+1<fim)
        quickSortSemPivo(j+1,fim);
    }
  }

  public void quickSortComPivo(int ini, int fim) throws IOException
  {
    Registro reg1 = new Registro(), reg2 = new Registro();
    int pivo,i = ini, j = fim;

    seekArq((ini+fim)/2);
    reg1.leDoArq(getFile());
    pivo = reg1.getNumero(); //#O método leDoArq() retorna um void, não dá pra fazer um .getNumero() nele.

    seekArq(0);
    while(i < j)
    {
      seekArq(i);
      reg1.leDoArq(getFile());
      seekArq(j);
      reg2.leDoArq(getFile());

      while(reg1.getNumero()<pivo)
      {
        reg1.leDoArq(getFile()); //#O método leDoArq() não aceita ints como argumento.
        i++;
        comp++;
      }

      while(reg2.getNumero() >pivo)
      {
        reg2.leDoArq(getFile());//#O método leDoArq() não aceita ints como argumento.
        j--;
        comp++;
      }
      if(i <=j)
      {
        seekArq(i);
        reg2.gravaNoArq(getFile());
        seekArq(j);
        reg1.gravaNoArq(getFile());
        mov+=2;
      }
      if(ini < j)
        quickSortComPivo(ini,j);
      if(i < fim)
        quickSortComPivo(i,fim);
    }
  }

  public void exibeArquivo()
  {
     Registro reg = new Registro();
     try{
     reg.leDoArq(getFile());
    while(!eof()) //#O método eof() não aceita arguentos
      System.out.println(reg.getNumero() + "   "); //#Reg não é uma classe pra ser instanciado desse jeito.
     }
     catch(IOException e)
     {}
  }
  //outros metodos
  public void geraArquivoOrdenado()
  {
      for(int i =0; i<1024; i++){
          new Registro(i).gravaNoArq(arquivo);
      }
  }
  public void geraArquivoReverso()
  {
      for(int i = 1023; i>=0; i--){
          new Registro(i).gravaNoArq(arquivo);
      }
  }
  public void geraArquivoRandomico()
  {
      Random rnd = new Random();
      for(int i = 0; i<1024; i++){
          new Registro(rnd.nextInt(1000000)).gravaNoArq(arquivo);
      }      
  }
  
  //rafa
  
  public void countSort() throws IOException {
        int TL = (int) filesize(), i, aux;
        Registro reg = new Registro();
        Arquivo auxc = new Arquivo("auxF.dat");
        auxc.truncate(TL);
        int count[] = new int[10];

        for (i = 0; i < TL; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            count[reg.getNumero()]++;
        }

        for (i = 0; i < 10 - 1; i++) {
            count[i + 1] += count[i];
        }

        for (i = TL - 1; i >= 0; i--) {
            seekArq(i);
            reg.leDoArq(arquivo);
            auxc.seekArq(--count[reg.getNumero()]);
            reg.gravaNoArq(auxc.getFile());
        }

        seekArq(0);
        auxc.seekArq(0);
        for (i = 0; i < TL; i++) {
            mov++;
            reg.leDoArq(auxc.getFile());
            reg.gravaNoArq(arquivo);
        }
        auxc.truncate(0);
    }
        
    public void bucketSort() throws IOException {
        int numreg = (int) filesize();
        Registro mat[][] = new Registro[10][numreg];
        int[] index = new int[10];
        Registro reg;
        int i, tl = (int) filesize(), j, k;

        for (i = 0; i < tl; i++) {
            reg = new Registro();
            seekArq(i);
            reg.leDoArq(arquivo);
            mat[((reg.getNumero() / numreg) * 10)][index[((reg.getNumero() / numreg) * 10)]++] = reg;
        }

        for (i = 0; i < 10; i++) {
            j = 1;
            while (j < index[i]) {
                k = j;
                comp++;
                while (mat[i][k].getNumero() < mat[i][k - 1].getNumero()) {
                    reg = mat[i][k];
                    mat[i][k] = mat[i][k - 1];
                    mat[i][k - 1] = reg;
                    comp++;
                }
                j++;
            }

            seekArq(0);
            for (i = 0; i < 10; i++) {
                for (j = 0; j < index[i]; j++) {
                    mov++;
                    mat[i][j].gravaNoArq(arquivo);
                }
            }
        }
    }

    public void radixSort() throws FileNotFoundException, IOException {
        int i, j, max = 10, TL = (int) filesize();
        Registro reg = new Registro();
        int count[];
        Arquivo aux = new Arquivo("auxF.dat");
        for (i = 1; i < max; i *= 10) {
            aux.truncate(TL);
            aux.seekArq(0);
            count = new int[10];

            seekArq(0);
            for (j = 0; j < TL; j++) {
                reg.leDoArq(arquivo);
                count[(reg.getNumero() / i) % 10]++;
            }

            for (j = 0; j < 9; j++) {
                count[j + 1] += count[j];
            }

            for (j = TL - 1; j >= 0; j--) {
                seekArq(j);
                reg.leDoArq(arquivo);
                aux.seekArq(--count[(reg.getNumero() / i) % 10]);
                reg.gravaNoArq(aux.getFile());
            }

            aux.seekArq(0);
            seekArq(0);
            for (j = 0; j < TL; j++) {
                mov++;
                reg.leDoArq(aux.getFile());
                reg.gravaNoArq(arquivo);
            }
        }
        aux.truncate(0);
    }

    public void gnomeSort() throws IOException {
        int i, tl = (int) filesize(), j;
        Registro reg1 = new Registro(), reg2 = new Registro();

        for (i = 0; i < tl - 1; i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            reg2.leDoArq(arquivo);

            comp++;
            if (reg1.getNumero() > reg2.getNumero()) {
                j = i;

                comp++;
                while (j >= 0 && reg2.getNumero() < reg1.getNumero()) {
                    mov += 2;
                    seekArq(j);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);

                    j--;
                    if (j >= 0) {
                        seekArq(j);
                        reg1.leDoArq(arquivo);
                        reg2.leDoArq(arquivo);
                        comp++;
                    }
                }
            }
        }
    }

    public void combSort() throws IOException {
        int i = 0, tl = (int) filesize(), fator = (int) (tl / 1.3);
        Registro reg1 = new Registro(), reg2 = new Registro();

        while (fator > 0 && i != tl - 1) {
            i = 0;
            while (i + fator < tl) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(fator + i);
                reg2.leDoArq(arquivo);

                comp++;
                if (reg1.getNumero() > reg2.getNumero()) {
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(fator + i);
                    reg1.gravaNoArq(arquivo);
                }
                i++;
            }
            fator = (int) (fator / 1.3);
        }
    }

    public void insercaoBinaria2(int ini, int fim) throws IOException {
        int pos;
        Registro reg = new Registro(), reg1 = new Registro(), reg2 = new Registro();

        for (int i = ini; i <= fim; i++) {
            seekArq(i);
            reg.leDoArq(getFile());
            pos = buscaBinaria(reg1.getNumero());
            for (int j = i; j > pos; j--) {
                // permuta
                seekArq(j - 1);
                reg1.leDoArq(getFile());
                reg2.leDoArq(getFile());

                seekArq(j - 1);
                reg2.gravaNoArq(getFile());
                reg1.gravaNoArq(getFile());

                mov += 2;
            }
        }
    }

    public void timSort() throws IOException {

        int i, tl = (int) filesize(), run = 32, size, esq, dir, meio;
        Arquivo aux = new Arquivo("auxF.dat");
        aux.truncate(tl);

        for (i = 0; i < tl; i += run) {
            if (i + run < tl) {
                insercaoBinaria2(i, i + run);
            } else {
                insercaoBinaria2(i, tl);
            }
        }

        for (size = run; size < tl; size *= 2) {
            for (esq = 0; esq < tl; esq += 2 * size) {
                if (esq + 2 * size < tl) {
                    dir = esq + 2 * size - 1;
                } else {
                    dir = tl - 1;
                }

                meio = (esq + dir) / 2;

                fusao(esq, meio, dir);
            }
        }
    }

}




