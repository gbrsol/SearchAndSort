/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.pkg1.bimestre;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal
{
  private Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand;
  private int tini,tfim,movO,compO,movR,compR,movRev,compRev,ttotalO,ttotalR,ttotalRev;
  private FileWriter fwriter;
  private PrintWriter pwriter;
  

  public void gravaLinha(int compP,int compE,int movP,int movE,int tempo)
  {
      pwriter.printf("\t\t|"+compP+"\t\t|"+compE+"\t\t|"+movP+"\t\t|"+movE+"\t\t|"+tempo+"\t\t|");
  }
  
  public Principal()
  {
      try{
      arqOrd = new Arquivo("Ordenado.dat");
      arqRev = new Arquivo("Reverso.dat");
      arqRand = new Arquivo("Random.dat");
      fwriter = new FileWriter("tab.txt"); // cria o arq
      auxRev = new Arquivo("auxRev.dat");
      auxRand = new Arquivo("auxRand.dat");
      pwriter = new PrintWriter(fwriter); // manipula arq
      }
      catch(IOException e)
      {}
  }
  public void insercaoDireta()
  {
      pwriter.print("\n|Inserção direta\t\t");
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.insercaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int)(arqOrd.filesize() * (Math.log((double)arqOrd.filesize()) + 0.577216f)) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.insercaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int)(arqRev.filesize() * (Math.log((double)arqRev.filesize()) + 0.577216f)) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.insercaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int)(arqRand.filesize() * (Math.log((double)arqRand.filesize()) + 0.577216f)) , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void selecaoDireta()
  {
      pwriter.print("\n|Seleção direta\t\t");
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.selecaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int )(Math.pow(arqOrd.filesize(), 2) / 4 + (3 * (arqOrd.filesize() - 1))) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.selecaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int )(Math.pow(arqRev.filesize(), 2) / 4 + (3 * (arqRev.filesize() - 1))) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.selecaoDireta();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int )(Math.pow(arqRand.filesize(), 2) / 4 + (3 * (arqRand.filesize() - 1))) , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void insercaoBinaria()
  {
      pwriter.print("\n|Inserção binária\t\t");
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.insercaoBinaria();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int )(Math.pow(arqOrd.filesize(), 2) + 3 * arqOrd.filesize() - 4) / 2 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.insercaoBinaria();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int )(Math.pow(arqRev.filesize(), 2) + 3 * arqRev.filesize() - 4) / 2, ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.insercaoBinaria();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int )(Math.pow(arqRand.filesize(), 2) + 3 * arqRand.filesize() - 4) / 2 , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void bubbleSort()
  {
      pwriter.print("\n|Bubble Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.bubbleSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int )(arqOrd.filesize() * (Math.log((double) arqOrd.filesize()) + 0.577216f)) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.bubbleSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int )(arqRev.filesize() * (Math.log((double) arqRev.filesize()) + 0.577216f)), ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.bubbleSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int )(arqRand.filesize() * (Math.log((double) arqRand.filesize()) + 0.577216f)) , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void shakeSort()
  {
      pwriter.print("\n|Shake Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.shakeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int )(3 * (Math.pow(arqOrd.filesize(), 2) - arqOrd.filesize()) / 2) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.shakeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int )(3 * (Math.pow(arqRev.filesize(), 2) - arqRev.filesize()) / 2), ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.shakeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int )(3 * (Math.pow(arqRand.filesize(), 2) - arqRand.filesize()) / 2) , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void shellSort()
  {
      pwriter.print("\n|Shell Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.shellSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,(int )(Math.pow(arqOrd.filesize(), 2) / (4 + 3 * (arqOrd.filesize() - 1))) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.shellSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,(int )(Math.pow(arqRev.filesize(), 2) / (4 + 3 * (arqRev.filesize() - 1))) , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.shellSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,(int )(Math.pow(arqRand.filesize(), 2) / (4 + 3 * (arqRand.filesize() - 1)))  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void heapSort()
  {
      pwriter.print("\n|Heap Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.heapSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.heapSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.heapSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1 , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void quickSemPivo()
  {
      pwriter.print("|Quick I\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.quickSortSemPivo(0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.quickSortSemPivo(0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.quickSortSemPivo(0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void quickComPivo()
  {
      pwriter.print("|Quick II\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.quickSortComPivo(0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1, ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.quickSortComPivo(0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.quickSortComPivo( 0,(int)arqOrd.filesize()-1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1 , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  public void mergeSortI() 
  {
      pwriter.print("|Merge I\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    arqOrd.mergeSortI();
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
    auxRev.mergeSortI();
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
    auxRand.mergeSortI();
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  
  public void mergeSortII()
  {
      pwriter.print("|Merge II\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.mergeSortII(0,(int)arqOrd.filesize() - 1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1, ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.mergeSortII(0,(int)arqOrd.filesize() - 1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.mergeSortII(0,(int)arqOrd.filesize() - 1);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1 , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void countingSort()
  {
      pwriter.print("|Counting\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.countSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.countSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.countSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void bucketSort()
  {
      pwriter.print("|Bucket Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.bucketSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.bucketSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.bucketSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void radixSort()
  {
      pwriter.print("|Radix Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.radixSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.radixSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.radixSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void combSort()
  {
      pwriter.print("|Comb Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.combSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.combSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.combSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void gnomeSort()
  {
      pwriter.print("|Gnome Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.gnomeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.gnomeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.gnomeSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
 
  public void timSort()
  {
      pwriter.print("|Tim Sort\t\t");
    
    
    arqOrd.initComp();
    arqOrd.initMov();
    tini=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
      try {
          arqOrd.timSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
    compO=arqOrd.getComp();
    movO=arqOrd.getMov();
    ttotalO=tfim-tini;
      try {
          gravaLinha(compO, (int)((Math.pow((int)arqOrd.filesize(), 2)-(int)arqOrd.filesize())/2),movO ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Reverso
    auxRev.copiaArquivo(arqRev.getFile()); // faz uma cópia do arquivo de arqRev
    //para auxRev para preservar o original
    auxRev.initComp();
    auxRev.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRev.timSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalRev=tfim-tini;
    compRev=auxRev.getComp();
    movRev= auxRev.getMov();
    try {
          gravaLinha(compRev, (int)((Math.pow((int)arqRev.filesize(), 2)-(int)arqRev.filesize())/2),movRev ,-1 , ttotalO);
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    //Arquivo Randomico
    auxRand.copiaArquivo(arqRand.getFile()); // faz uma cópia do arquivo de arqRand
    //para auxRand para preservar o original
    auxRand.initComp();
    auxRand.initMov();
    tini=(int) System.currentTimeMillis();
      try {
          auxRand.timSort();
      } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
      }
    tfim=(int) System.currentTimeMillis();
    ttotalR=tfim-tini;
    compR=auxRand.getComp();
    movR=auxRand.getMov();
    try {
          gravaLinha(compR, (int)((Math.pow((int)arqRand.filesize(), 2)-(int)arqRand.filesize())/2),movR ,-1  , ttotalO);
        } catch (IOException ex) {
          Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public void geraTabela() throws IOException
  {
    arqOrd.geraArquivoOrdenado();
    arqRev.geraArquivoReverso();
    arqRand.geraArquivoRandomico();
    pwriter.print("Métodos de ordenação\t\tArquivo Ordenado\t\tArquivo Reverso\t\tArquivo Randômico\n\n"
    +             "\t\tComp prog\t\tComp Equa\t\tMov Prog\t\tMov Equa\t\tTempo\t\t"+
                  "\t\tComp prog\t\tComp Equa\t\tMov Prog\t\tMov Equa\t\tTempo\t\t"+
                  "\t\tComp prog\t\tComp Equa\t\tMov Prog\t\tMov Equa\t\tTempo\t\t");
    insercaoDireta();
    insercaoBinaria();
    selecaoDireta();
    bubbleSort();
    shakeSort();
    shellSort();
    heapSort();
    quickSemPivo();
    quickComPivo();
    mergeSortI();
    mergeSortII();
    countingSort();
    bucketSort();
    radixSort();
    combSort();
    gnomeSort();
    timSort();
    fwriter.close();
  }

  
}