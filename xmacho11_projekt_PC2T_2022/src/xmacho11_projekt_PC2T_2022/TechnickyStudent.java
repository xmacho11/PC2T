package xmacho11_projekt_PC2T_2022;

public class TechnickyStudent extends Student {
	
	TechnickyStudent(String jmeno, String prijmeni, int rok) { //volani nadrazene funkce pro vytvoreni noveho studenta
		super(jmeno, prijmeni, rok);
	}

	@Override
	public void dovednost() { //prepsani abstraktni funkce pro technickeho studenta

		if(rok % 4 == 0) { //matematicky vypocet prestupneho roku
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
	}
}
