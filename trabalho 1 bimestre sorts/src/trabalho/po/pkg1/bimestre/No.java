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
public class No {
    private int info;
    private No prox, ant;

    public No(int info)
    {
        this.info = info;
        prox = null;
        ant = null;
    }
    
    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
    
    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
    
    
}
