package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class Controller {

    private static List<Integer> objet = new LinkedList<>();
    private final static ArrayList<PrimeFinderThread> pft = new ArrayList<>();



    public static void main(String[] args) {

        int a = 0;
        for (int i = 1; i <= 3; i++) {
            pft.add(new PrimeFinderThread(a, 10000000 * i, objet));
            a = 10000000 * i;

        }
        for (PrimeFinderThread spft : pft) spft.start();
        entrada();

    }

    public static void entrada() {
        boolean key = false;
        Scanner t = new Scanner(System.in);
        /* Entrada del Usuario */
        String enterkey = t.nextLine();
        /*Comparación para saber si se presiono Enter*/
        if (enterkey.isEmpty()) {
            synchronized(objet){
                objet.notifyAll();
            }

        }

    }


}




