package btree;


public class No {
    private static final int n = 2;
    private No vLig[];
    private int vPos[];
    private int vInfo[];
    private int tl;
    
    No()
    {
        vLig = new No[n*2+2];
        vPos = new int[n*2+1];
        vInfo = new int[n*2+1];
    }
    No(int info)
    {
        vLig = new No[n*2+2];
        vPos = new int[n*2+1];
        vInfo = new int[n*2+1];
        vInfo[0] = info;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No lig) {
        this.vLig[pos] = lig;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int pos, int info) {
        this.vPos[pos] = info;
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        this.vInfo[pos] = info;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }
    
    public int procurarPosicao(int info)
    {
        int i = 0;
        while(i < tl && vInfo[i] < info)
            i++;
        return i;
    }
    
    public void remanejar(int pos)
    {
        int n;
        for(int i = tl; i > pos;i--)
        {
            setvInfo(i,getvInfo(i-1));
        }
    }
    
    public boolean folha()
    {
        return vLig[0] == null;
    }
    
    public int retornaPosLig(int info)
    {
        int i =0;
        while( i < tl && vInfo[i] != info)
           i++;
        return i;
    }
}
