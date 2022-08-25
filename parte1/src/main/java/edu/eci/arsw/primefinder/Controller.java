package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;



public class Controller {

    private final static int TMILISECONDS = 5000;
    boolean suspender; //Suspende un hilo cuando es true
    boolean pausar;    //Detiene un hilo cuando es true

    //Pausar el hilo
    synchronized void pausarhilo(){
        pausar=true;
        //lo siguiente garantiza que un hilo suspendido puede detenerse.
        suspender=false;
        notify();
    }

    //Renaudar un hilo
    synchronized void renaudarhilo(){
        suspender=false;
        notify();
    }

    public static void main(String[] args) {
        ArrayList<PrimeFinderThread> pft = new ArrayList<>();
        int a = 0;
        for (int i = 1 ; i <= 3 ; i++){
            pft.add(new PrimeFinderThread(a, 10000000*i));
            a = 10000000*i;

        }
        pft.get(0).start();
        try {
            pft.get(0).sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(entrada()){
            pft.get(0).pausarhilo();
        }






    }

    public boolean entrada(){
        boolean key = false;
        Scanner t = new Scanner(System.in);
        /* Entrada del Usuario */
        String enterkey = t.nextLine();
        /*Comparación para saber si se presiono Enter*/
        if (enterkey.isEmpty()) {
            key = true;
        }
        return key;
    }

    public void timer(){
        Timer timer = new Timer (TMILISECONDS, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                for (PrimeFinderThread spft : pft) spft.start();
            }
        });
    }
    static class RepeatedTask extends TimerTask {
        public void run() {
            ArrayList<PrimeFinderThread> pft = new ArrayList<>();
            int a = 0;
            for (int i = 1 ; i <= 3 ; i++){
                pft.add(new PrimeFinderThread(a, 10000000*i));
                a = 10000000*i;

            }
            for (PrimeFinderThread spft : pft) spft.start();
        }
    }



}
