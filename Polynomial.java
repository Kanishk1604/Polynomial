
import java.util.*;


public class Polynomial {
	private Map<Integer,Integer> polynomial;
	private int degree;

    //default constructor
	public Polynomial() {
		//Initializing the map to have descending order
		polynomial = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		degree = -1;
	}
	
    //second constructor
	public Polynomial(int coefficient,int power) { //receives two integers
		//Initializing the map to have descending order
		polynomial = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		//If Coeffcient is 0 
		if(coefficient == 0)
			return ;
		try {
			//if power is -ve then throw the exception
			if(power < 0) {
				throw new IllegalArgumentException("Negative powers cannot be accepted.");
			}
			polynomial.put(power, coefficient);
			degree = power;
		}
		catch(Exception e){
			System.out.println("IllegalArgumentException : "+e.getMessage());
		}
	}
	
    //third constructor
	public Polynomial(Map<Integer,Integer> polynomial){
		polynomial = new TreeMap<Integer,Integer>(Collections.reverseOrder());
	
	}

	//copy constructor
	Polynomial(Polynomial p){
		polynomial = p.getMap();
	}

	//add
	public void add(Polynomial p) {
		Map<Integer,Integer> map = p.getMap();
		
		//get the map of second polynomial
		//add terms
		for(Map.Entry<Integer,Integer> e : map.entrySet()) {
			if(polynomial.containsKey(e.getKey())) {
				// if term already exists
				int value = e.getValue()+polynomial.get(e.getKey());
				if(value==0){
					polynomial.remove(e.getKey());
					continue;
				}
				else
					polynomial.put(e.getKey(), e.getValue()+polynomial.get(e.getKey()));
			}
			else {
				//term not present
				if(e.getValue()!=0) {
					polynomial.put(e.getKey(),e.getValue());
					continue;
				}
			}
		}

		TreeMap<Integer,Integer> nmap = (TreeMap<Integer,Integer>)polynomial;
		this.degree = nmap.firstKey();
	}

	//overload add
	public static Polynomial add(Polynomial p1, Polynomial p2){
		Map<Integer,Integer> P1 = p1.getMap();
		Map<Integer,Integer> P2 = p2.getMap();

		for (Map.Entry<Integer,Integer> e : P1.entrySet()){
			if(P2.containsKey(e.getKey())) {
				// if term already exists
				int value = e.getValue()+P2.get(e.getKey());
				if(value==0){
					P2.remove(e.getKey());
					continue;
				}
				else
					P2.put(e.getKey(), e.getValue()+P2.get(e.getKey()));
			}
			else {
				if(e.getValue()!=0) {
					P2.put(e.getKey(),e.getValue());
					continue;
				}
			}
		}

		Polynomial sum = new Polynomial(P2);
		return sum;
	}
	

	//subtract
	public void subtract(Polynomial p) {
		Map<Integer,Integer> map = p.getMap();

		for(Map.Entry<Integer,Integer> e : map.entrySet()) {
			int mpower = e.getKey();
			int mcoeff = e.getValue();
			this.add(new Polynomial(-1*mcoeff,mpower));
		}
		
		TreeMap<Integer,Integer> nmap = (TreeMap<Integer,Integer>)map;
		this.degree = nmap.firstKey();
	}

	//overload subtract
	public static Polynomial subtract(Polynomial p1, Polynomial p2) {
		Map<Integer,Integer> P2 = p2.getMap();

		for(Map.Entry<Integer,Integer> e : P2.entrySet()) {
			int mpower = e.getKey();
			int mcoeff = e.getValue();
			p1.add(new Polynomial(-1*mcoeff,mpower));
		}
		Map<Integer,Integer> P1 = p1.getMap();

		Polynomial diff = new Polynomial(P1);
		return diff;
	}

	//multiply
	public Polynomial multiply(Polynomial p) {
		Polynomial product = new Polynomial();
		
		Map<Integer,Integer> nmap = p.getMap();
		for(Map.Entry<Integer,Integer> e : nmap.entrySet()) {
			int qpower = e.getKey();
			int qcoeff = e.getValue();
			
			for(Map.Entry<Integer,Integer> f : polynomial.entrySet()) {
				int ppower = f.getKey();
				int pcoeff = f.getValue();
				product.add(new Polynomial(pcoeff*qcoeff,ppower+qpower));
			}
		}
		return product;
	}
	
	//degree
	public int degree() {
		return degree;
	}
	
	//coefficient 
	public int coefficient(int power) {
		return polynomial.get(power);
	}

	//changeCoefficient
	public void changeCoefficient(int power, int newCoefficient){
		polynomial.replace(power, polynomial.get(power), newCoefficient);
	}

    //removeTerm
	public void removeTerm(int power){
		polynomial.remove(power);
	}


	//evaluate
	public double evaluate(double num) {
		double poln = 0;
		for(Map.Entry<Integer, Integer> e : polynomial.entrySet()) {
			int power = e.getKey();
			int coeff = e.getValue();
			
			poln = poln + Math.pow(num, power)*coeff;
		}
		return poln;
	}


	//equals
	@Override
	public boolean equals(Object object) {
		Polynomial p = (Polynomial)object;

		//comparing size
		if(this.polynomial.size()!=p.polynomial.size())
			return false;

		for(Map.Entry<Integer, Integer> e : p.getMap().entrySet()) {
			int power = e.getKey();
			int coeff = e.getValue();
			
			if(polynomial.containsKey(power) && this.polynomial.get(power)!=coeff)
				return false;
			
			else if(!polynomial.containsKey(power))
				return false;
			// else if(!p.equals(object))
				// return false;
		}
		return true;
	}

	public String toString(Polynomial p1) {
		String ans = "";
		Map<Integer,Integer> map = p1.getMap();

		for(Map.Entry<Integer, Integer> e : map.entrySet()) {
			int power = e.getKey();
			int coeff = e.getValue();
			
			 
			String sign ="+";
			String p="";
			String c="";
			
			if(coeff<0)
				sign="-";
			
			if(power==0)
				p="";
			else if(power==1)
				p="x";
			else
				p="x"+Math.abs(power)+"";
			
			if(Math.abs(coeff)==1)
				c="";
			else
				c=Math.abs(coeff)+"";
			
			if(ans.trim()=="") {
				if(sign=="-")
					ans=sign+" "+c+p+" ";
				else
					ans=c+p+" ";
			}
			else
				ans=ans+sign+" "+c+p+" ";
		}
		return ans;
	}
	
	public Map<Integer,Integer> getMap() {

		return this.polynomial;
	}

	public int compareTo(Polynomial p){
		return 0;
	}

}
