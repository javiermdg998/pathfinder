package com.company;

import java.util.ArrayList;

public class Nodo {
    private int x;
    private int y;
    private boolean accesible;
    private int cost;
    private Nodo parent;

    //public static ArrayList<Nodo> path=new ArrayList<Nodo>();

    public Nodo() {
    }


    public Nodo(int y, int x, boolean accesible, int cost, Nodo parent) {
        this.x = x;
        this.y = y;
        this.accesible = accesible;
        this.cost = cost;
        this.parent = parent;
    }

    public Nodo(int y, int x, boolean accesible) {
        this.x = x;
        this.y = y;
        this.accesible = accesible;
    }

    public Nodo(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return (this.accesible) ? "\u26DA" : "\u26DD";
    }


    public void move(int parentcost) {
        setCost(parentcost + 1);
    }

    public boolean find(Nodo[][] lab, Nodo parent) {
        this.parent = parent;

        if (lab[this.y][this.x-1].isAccesible()) {

            if (!esMismoNodo(this.parent, lab[this.y][this.x-1])) {
                //find(lab, this);
                Main.marcar(lab[this.y][this.x-1]);
            }
        }

        return false;
    }

    public boolean esMismoNodo(Nodo a, Nodo b) {
        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            return true;
        }
        return false;
    }

    public boolean esMismoNodo(Nodo a) {

        return esMismoNodo(a, this);
    }

    public boolean find(Nodo[][] lab, Nodo end, Nodo begin) throws InterruptedException {
        Nodo n;
        Main.marcar(this);
        boolean seguir =false;
        ArrayList<Nodo> posibles=new ArrayList<Nodo>();


        if (lab[this.y-1][this.x].isAccesible()) {
            posibles.add(lab[y-1][x]);
            //Main.marcar(lab[y-1][x],Main.GREEN);

           // n =  lab[this.y-1][this.x];
            //seguir=flow(lab,n,end,begin);
        }

        if (lab[this.y+1][this.x].isAccesible()) {
            posibles.add(lab[this.y+1][this.x]);
            //Main.marcar(lab[this.y+1][this.x],Main.RED);
            //n =  lab[this.y+1][this.x];
            //seguir=flow(lab,n,end,begin);
    }

        if (lab[this.y][this.x-1].isAccesible()) {
            posibles.add(lab[this.y][this.x-1]);
            //Main.marcar(lab[this.y][this.x-1],Main.GREEN);
            // n =  lab[this.y][this.x-1];
            //    seguir=flow(lab,n,end,begin);
        }

        if (lab[this.y][this.x+1].isAccesible()) {
            posibles.add(lab[this.y][this.x+1]);
            //Main.marcar(lab[this.y][this.x+1]);
            //   n =  lab[this.y][this.x+1];
            // seguir=flow(lab,n,end,begin);

        }
        Nodo[] nodos=Main.ordenar(Main.toArray(posibles),end);

        for (int i = 0; i < nodos.length; i++) {
            Main.marcar(nodos[i]);
            seguir=flow(lab,nodos[i],end,begin);
        }
        return seguir;

    }

    private boolean flow(Nodo[][]lab, Nodo n,Nodo end,Nodo begin) throws InterruptedException {
        boolean found=false;
        if(n.getCost()==0 || n.getCost()>(this.cost+1)){
            System.out.println("costn"+(n.getCost()));
            System.out.println("costt"+(this.getCost()));
            if(!n.esMismoNodo(end)){
                if(!n.esMismoNodo(this.parent)){
                    n.setCost(this.cost+1);
                    n.setParent(this);
                }
            }


        }else{
            return false;
        }

        if(n.esMismoNodo(end)){
            System.out.println("end found, cost:"+cost);
            //javax.swing.JOptionPane.showMessageDialog(null,"camino encontrado");
            Main.found=true;
            Main.marcar(n,Main.BLUE);
            n.setParent(this);
            System.out.println(n.getX()+":"+n.getY());

            //System.out.println(this.parent.getX()+":"+this.parent.getY());

           getRecursivePath(this,begin);
            found= true;
        }
        if(!n.esMismoNodo(parent) &&!Main.found){//seguir buscando
            n.find(lab,end,begin);


            Main.marcar(n,Main.GREEN);
        }
        return found;
    }
    private void getRecursivePath(Nodo n,Nodo begin){
        if(!n.esMismoNodo(n.getParent())&&!n.esMismoNodo(begin)){
            Main.marcar(n);
            System.out.println(n.getX()+":"+n.getY());
            n.getRecursivePath(n.getParent(),begin);
        }
        Main.path.add(this);
        Main.marcar(begin);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAccesible() {
        return accesible;
    }

    public void setAccesible(boolean accesible) {
        this.accesible = accesible;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Nodo getParent() {
        return parent;
    }

    public void setParent(Nodo parent) {
        this.parent = parent;
    }
    public double distanceTo(Nodo a) {
        return distanceBetween(a,this);
    }
    public  double distanceBetween(Nodo a ,Nodo b){
        return Math.sqrt((Math.pow((b.getX()-a.getX()),2)+Math.pow(b.getY()-a.getY(),2)));
    }

}
