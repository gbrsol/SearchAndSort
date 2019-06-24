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
public class NoBP extends No{
    private NoBP prox, ant;
    private int n = 4;
    private NoBP[] vLig;
    NoBP()
    {
        super();
        vLig = new NoBP[n*2+2];
        prox = null;
        ant = null;
    }
    NoBP(int info)
    {
        super(info);
        prox = null;
        ant = null;
    }

    public NoBP getProx() {
        return prox;
    }

    public void setProx(NoBP prox) {
        this.prox = prox;
    }

    public NoBP getAnt() {
        return ant;
    }

    public void setAnt(NoBP ant) {
        this.ant = ant;
    }
    @Override
    public NoBP getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, NoBP lig) {
        this.vLig[pos] = lig;
    }
    
}
