package areaTriangulo;

public class Triangulo {
	
	//variables miembro
	private double area;		//area del triangulo
	private double base;		//base del triangulo
	private double altura;		//altura del triangulo
	
	//constructor
	public Triangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
		setArea(base, altura); 
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(double base, double altura) {
		
		this.area = (base * altura)/2;
		
	}

	/**
	 * @return the base
	 */
	public double getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(double base) {
		this.base = base;
	}

	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	

}
