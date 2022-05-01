package xmacho11_projekt_PC2T_2022;

public class KombinovanyStudent extends Student{

	KombinovanyStudent(String jmeno, String prijmeni, int rok) { //volani nadrazene funkce pro vytvoreni noveho studenta
		super(jmeno, prijmeni, rok);
	}

	@Override
	public void dovednost() { 	//prepsani abstraktni funkce pro kombinovaneho studenta
								//kopie funkci od technickeho a humanitniho studenta
		if(rok % 4 == 0) {
			if (rok % 100 == 0) {
				if (rok % 400 == 0)
					System.out.print("N");
				else
					System.out.print("Nen");
			}
			else
				System.out.print("N");
		}
		else
			System.out.print("Nen");
		System.out.println("arodil jsem se v prestupnem roce");
		
		
		int znameni = (rok % 12);
		System.out.print("Moje znameni horoskopu je ");
		switch(znameni) {
			case 0:
				System.out.println("opice");
				break;
			case 1:
				System.out.println("kohout");
				break;
			case 2:
				System.out.println("pes");
				break;
			case 3:
				System.out.println("vepr");
				break;
			case 4:
				System.out.println("krysa");
				break;
			case 5:
				System.out.println("buvol");
				break;
			case 6:
				System.out.println("tygr");
				break;
			case 7:
				System.out.println("zajic");
				break;
			case 8:
				System.out.println("drak");
				break;
			case 9:
				System.out.println("had");
				break;
			case 10:
				System.out.println("kun");
				break;
			case 11:
				System.out.println("koza");
				break;
		}		
	}

	@Override
	public int compareTo(Student o) {
		return 0;
	}
}
