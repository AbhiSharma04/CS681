package edu.umb.cs681.hw07;

import java.util.LinkedList;
import java.util.List;

public class PrimeGenerator {

    LinkedList<Long> primes = new LinkedList<Long> ();
    protected long from,to;

    public PrimeGenerator (long from, long to) {
        if(from>=1 && to>from){
            this.from=from;
            this.to=to;
        }
        else {
            System.out.println ("Wrong input");
        }

    }

    public LinkedList<Long> GetPrimes(){
        return primes;
    }

    public void generatePrimes(){
        for (long i = from; i <= to; i++) {
            if( isPrime(i) ){
                primes.add(i);
            }
        }
    }

    public boolean isPrime(long n){
                if (n < 2) {
                    return false;
                }
                for (int i = 2; i <= Math.sqrt(n); i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }
                return true;
    }

}

