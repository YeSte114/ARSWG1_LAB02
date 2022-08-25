package edu.eci.arsw.primefinder;

import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	boolean supender = true;
	
	private List<Integer> primes=new LinkedList<Integer>();

	private final static int TMILISECONDS = 1000;
	private List<Integer> objet;
	private long TACTUAL=0;
	
	public PrimeFinderThread(int a, int b, List<Integer> objet) {
		super();
		this.objet = objet;
		this.a = a;
		this.b = b;
	}

	public void run(){

		if(TACTUAL == 0){
			TACTUAL = System.currentTimeMillis();
		}
		for (int i=a;i<=b;i++){
			if (isPrime(i)){
				primes.add(i);
			}
			if((System.currentTimeMillis()-TACTUAL >= TMILISECONDS) && supender ){
				System.out.println(primes.size());
				try {
					supender=false;
					pausarhilo();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}



		}
		System.out.println(primes.size());
		
		
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

	private void pausarhilo() throws InterruptedException {
		synchronized(objet){
			objet.wait();
		}

	}
	
	
}
