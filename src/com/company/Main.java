package com.company;

import java.util.ArrayList;

public class Main {
    public static Nodo[][] lab = new Nodo[5][5];
    public static final String GREEN = "\033[32m";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\033[34m";
    public static final String BLACK = "\033[0;30m";
    public static final String WHITE = "\033[0;37m";
    public static final String RED = "\033[0;31m";
    public static ArrayList<Nodo> path = new ArrayList<>();
    public static  Nodo begin;
    public static  Nodo end;
    public static boolean found;

    public static void main(String[] args) throws InterruptedException {

        fill();
        begin = lab[3][1];
        begin.setParent(begin);
        begin.setCost(1);
        end=lab[2][3];
        //render(path);

        begin.find(lab,end,begin);
        if(found){
            System.out.println("mostrar camino");
            render(path);
        }else{
            System.out.println("no hay ruta posible");
        }

        //begin.find(lab, begin);
        //begin.expand(lab);
        // begin.find(lab, begin);
        //marcar(begin);
    }

    public static void fill() {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if ( (i == lab.length - 1 || j == lab[i].length - 1) || (i == 0 || j == 0)) {//generar laberinto TODO generar por carga de arrays
                    lab[i][j] = new Nodo(i,j, false);

                } else {
                    lab[i][j] = new Nodo(i, j, true);
                    lab[i][j].setCost(0);

                }
            }
        }

        lab[2][2].setAccesible(false);
       // lab[3][2].setAccesible(false);
        //lab[1][2].setAccesible(false);

    }

    public static void render() {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if (j == 1 && i == 3) {
                    System.out.print(BLUE);

                    System.out.format("%4s", lab[i][j]);
                    System.out.print(RESET);
                } else {
                    System.out.print((lab[i][j].isAccesible()) ? WHITE : BLACK);
                    System.out.format("%4s", lab[i][j]);

                    System.out.print(RESET);

                }
            }
            System.out.println();
        }
    }

    public static void render(ArrayList<Nodo> path) {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if (lab[i][j].esMismoNodo(begin)) {
                    System.out.print(BLUE);

                    System.out.format("%4s", lab[i][j]);
                    System.out.print(RESET);
                } else if (lab[i][j].esMismoNodo(end)) {
                    System.out.print(RED);

                    System.out.format("%4s", lab[i][j]);
                    System.out.print(RESET);
                }else {
                    System.out.print((lab[i][j].isAccesible()) ? ((contains(path, lab[i][j])) ? GREEN : WHITE) : BLACK);
                    System.out.format("%4s", lab[i][j]);

                    System.out.print(RESET);

                }
            }
            System.out.println();
        }
    }

    public static boolean contains(ArrayList<Nodo> path, Nodo nodo) {
        for (int i = 0; i < path.size(); i++) {
            if (nodo.esMismoNodo(nodo, path.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static void marcar(Nodo nodo) {
        marcar(nodo, RED);
    }

    public static void marcar(Nodo nodo, String color) {
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[i].length; j++) {
                if (lab[i][j].esMismoNodo(nodo)) {
                    System.out.print(color);

                    System.out.format("%4s", lab[i][j]);
                    System.out.print(RESET);
                } else {
                    System.out.print((lab[i][j].isAccesible()) ? WHITE : BLACK);
                    System.out.format("%4s", lab[i][j]);

                    System.out.print(RESET);

                }
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    public static Nodo[] ordenar(Nodo[] original,Nodo end){
        Nodo[] ordenado=original;

        Nodo aux;
        int k;
        for (int i = 1; i <ordenado.length ; i++) {
            k=i;
            while (k>=1 &&(ordenado[k].distanceTo(end)<ordenado[k-1].distanceTo(end))  ){

                aux=ordenado[k-1];
                ordenado[k-1]=ordenado[k];
                ordenado[k]=aux;
                k--;
            }
        }
        return ordenado;
    }

    public static Nodo[] toArray(ArrayList<Nodo> lista){
        Nodo[]array=new Nodo[lista.size()];
        for (int i = 0; i <lista.size() ; i++) {
            array[i]=lista.get(i);
        }
        return array;
    }
}
