package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	private static List<Integer> objet = new LinkedList<>();
	public static void main(String[] args) {
	/*
		PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);

		pft.start();*/


		ArrayList<PrimeFinderThread> pft = new ArrayList<>();
		int a = 0;
		for (int i = 1 ; i <= 3 ; i++){
			pft.add(new PrimeFinderThread(a, 10000000*i, objet));
			a = 10000000*i;

		}
		for (PrimeFinderThread spft : pft) spft.start();

	}
	
}
