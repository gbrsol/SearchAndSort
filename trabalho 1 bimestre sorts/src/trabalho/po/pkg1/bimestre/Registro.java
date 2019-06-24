
package trabalho.po.pkg1.bimestre;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Registro
{
  public final int tf=1022;
  private int numero; //4 bytes
  private char lixo[] = new char[tf]; //2044 bytes

  public Registro()
  {
    this.numero=0;
    for (int i=0 ; i<tf ; i++)
    lixo[i]='X';
  }
  public Registro(int numero)
  {
    this.numero=numero;
    for (int i=0 ; i<tf ; i++)
    lixo[i]='X';
  }

  public int getNumero()
  {
    return numero;
  }

  public void setNumero(int n)
  {
    numero = n;
  }

  public void gravaNoArq(RandomAccessFile arquivo)
  {
    try
    {
    arquivo.writeInt(numero);
    for(int i=0 ; i<tf ; i++)
      arquivo.writeChar(lixo[i]);
    }catch(IOException e){}
  }
  public void leDoArq(RandomAccessFile arquivo)
  {
    try
    {
      numero = arquivo.readInt();
      for(int i=0 ; i<tf ; i++)
        lixo[i]=arquivo.readChar();
    }catch(IOException e){}
  }
  static int length()
  {
    return(2048);
  }
}

