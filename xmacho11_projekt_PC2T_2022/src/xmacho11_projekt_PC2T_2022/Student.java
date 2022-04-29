package xmacho11_projekt_PC2T_2022;

public abstract class Student implements Comparable<Student> {
	private String jmeno;		//deklarace promenych
	private String prijmeni;
	protected int rok;
	private double prumer;
	private int pocetZnamek;
	
	Student(String jmeno, String prijmeni, int rok) { //funkce pro vytvoreni noveho studenta
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.rok = rok;
		prumer = 0;
		pocetZnamek = 0;
	}
	
	public String getJmeno() { //gettery pro promene
		return jmeno;
	}
	
	public String getPrijmeni() {
		return prijmeni;
	}
	
	public int getRok() {
		return rok;
	}
	
	public double getPrumer() {
		return prumer;
	}
	
	public int getPocetZnamek() {
		return pocetZnamek;
	}
	
	public void setPrumer(double prumer) { //setery promenych pro nacitani ze souboru
		this.prumer = prumer;
	}
	
	public void setPocetZnamek(int pocetZnamek) {
		this.pocetZnamek = pocetZnamek;
	}
	
	public boolean addZnamka(int znamka) { //funkce pro zapsani nove znamky studentovi

		setPrumer( (((prumer * ((double)pocetZnamek) + (double)znamka)) / (pocetZnamek+1)));
		pocetZnamek++;
		
		return true;
	}
	
	public abstract void dovednost(); //deklarace abstraktni funkce
	
}
