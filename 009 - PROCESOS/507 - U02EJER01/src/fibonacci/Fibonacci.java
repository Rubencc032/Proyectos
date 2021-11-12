package fibonacci;

public class Fibonacci extends Thread {

	//variables miembro
	private int num;

	public Fibonacci(int num) {
		this.num = num;
	}
	
	
	
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public void run() {
		
		calculoFibonacci(this.getNum());
		
	}
	
	public void calculoFibonacci(int num) {
		
		int num1 = 0;
		int num2 = 1;
		int temp;
		
				
		if (num == 1) System.out.print("Fibonacci: " + num1);
		if (num == 2) System.out.print("Fibonacci: " + num1 );
		else {
			System.out.print("Fibonacci: " + num1 + " " + num2);
		}
			for(int i = 3; i <= num; i++) { 
				
				System.out.print(" " + (num1+num2));
				temp = num1+num2;
				num1 = num2;
				num2 = temp;
		}
		
	}
}
