package xmacho11_projekt_PC2T_2022;

import java.util.Scanner;

/**
 * 
 * @author Radim Macho 221666
 * @version 1.4.2
 * 
 * Projekt kurzu Pocitace a programovani 2
 * 
 * Dokumentace k projektu v dokumentace_xmacho11_projekt_PC2T_2022.txt
 * 
 */

public class Main {
	
	public static int pouzeCelaCisla(Scanner sc) //filtr na cele cisla pro volbu v menu, ziskane ze cviceni
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu " + e.toString());
			System.out.println("Zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	public static void main(String[] args) {
		
		int ID = 0; //deklarace promenych
		String jmeno;
		String prijmeni;
		int rok;
		int znamka;
		int volba;
		boolean cyklus = true;
		Scanner sc = new Scanner(System.in);
		
		Databaze databaze = new Databaze(); //vytvoreni databaze
		
		System.out.println("Vitejte v databazi studentu."); //uvodni zapnnuti programu s moznosti otevreni ulozene databaze
		System.out.println("1 .. Zacit novou databazi");
		System.out.println("2 .. Nahrat databazi ze souboru");
		System.out.println("3 .. Nahrat databazi z SQL");
		volba = pouzeCelaCisla(sc);
		if(volba == 2) { //moznost nasict databazi ze souboru nebo SQL pri zapnuti programu
			ID = databaze.nacteniZeSouboru();
			if (ID == -1) {
				System.out.println("Databazi nebylo mozne nacist");
				ID = 0;
				databaze.vycisteniDatabaze();
			}
			else
				System.out.println("Databaze byla nactena");
		}
		else if(volba == 3) {
			ID = databaze.nacteniSQL();
			if (ID == -1) {
				System.out.println("Databazi nebylo mozne nacist");
				ID = 0;
				databaze.vycisteniDatabaze();
			}
			else
				System.out.println("Databaze byla nactena");
		}
		
		while(cyklus) { //nekonecny cyklus s moznosti ukonceni
			System.out.println("1 .. Prijmuti studenta  \t \t|8 ... Vypis postu studentu v oborech");
			System.out.println("2 .. Zapsani nove znamky studentovi \t|9 ... Nacteni databaze ze souboru ");
			System.out.println("3 .. Propusteni studenta \t \t|10 .. Ulozeni databaze do souboru");
			System.out.println("4 .. Informace o studentovi \t \t|11 .. Nacteni z SQL databaze");
			System.out.println("5 .. Dovednost studenta \t \t|12 .. Ulozeni do SQL databaze");
			System.out.println("6 .. Abecedni vypis studentu \t \t|13 .. Konec");
			System.out.println("7 .. Vypis obecneho studijniho prumeru");
		
			volba = pouzeCelaCisla(sc); //nacteni volby uzivatele
			switch(volba) {
				case 1:
					System.out.println("Zadejte do jakeho oboru chcete pridat studenta");
					System.out.println("1 .. Technicky obor");
					System.out.println("2 .. Humanitni obor");
					System.out.println("3 .. Kombinovane studium");
					volba = pouzeCelaCisla(sc); //vyber oboru noveho studenta

					System.out.println("Zadejte jmeno, prijmeni a rok narozeni noveho studenta. format: Karel Novak 1999");
					jmeno = sc.next(); //nacteni vstupnich dat o studentovi
					prijmeni = sc.next();
					rok = Main.pouzeCelaCisla(sc);
					
					if(!databaze.setStudent(volba, ID, jmeno, prijmeni, rok)) //volani funkce pro vytvoreni studenta + podminka uspesneho zapsani
						System.out.println("ERROR: studenta nebylo mozne zapsat, zkontrolujte vstupni data.");
					else {
						System.out.println("Student " + jmeno + " " + prijmeni + ", rok narozeni: " + rok + " byl uspesne pridan do databaze s ID: " + ID);
					}
					
					ID++; //zvyseni ID pro osetreni duplikatnich ID				
					
					break;
				case 2:
					System.out.println("Zadejte ID studenta a jakou znamku mu chcete zapsat. fotmat: 42 2");
					volba = pouzeCelaCisla(sc);
					znamka = pouzeCelaCisla(sc);
					
					if(!databaze.addZnamka(volba, znamka))
						System.out.println("ERROR: znamka nebyla zapsana, zkontrolujte spravnost zadaneho ID a znamky");
					else {
						System.out.println("Studentovi s ID: " + volba + " byla zapsana znamka: " + znamka);
					}
					
					break;
				case 3:
					System.out.println("Zadejte ID studenta ktereho chcete propustit");
					volba = pouzeCelaCisla(sc);
					
					if(!databaze.propusteniStudenta(volba))
						System.out.println("ERROR: student nebyl propusten, zkontrolujte spravnost zadaneho ID");
					else
						System.out.println("Student s ID: " + volba + " byl uspesne propusten.");
					
					break;
				case 4:
					System.out.println("Zadejte ID studenta o kterem chcete vypsat informace");
					volba = pouzeCelaCisla(sc);	
					
					Student data = null;
					data = databaze.getStudent(volba);
					if(data == null)
						System.out.println("ERROR: takovyto student v databazi neexistuje, zkontrolujte spravnost zadaneho ID");
					else
						System.out.println("Jmeno: " + data.getJmeno() + " " + data.getPrijmeni() + "\t rok narozeni: " + data.getRok() + "\t studijni prumer: " + data.getPrumer());
					
					break;
				case 5:
					System.out.println("Zadejte ID studenta od ktereho chcete predvest dovednost");
					volba = pouzeCelaCisla(sc);	
					
					if(!databaze.getDovednost(volba))
						System.out.println("ERROR: takovyto student v databazi neexistuje, zkontrolujte spravnost zadaneho ID");
					
					break;
				case 6:
					databaze.getSeznam();	
					break;
				case 7:
					databaze.getObecnyPrumer();
					break;
				case 8:
					databaze.getPocetStudentu();
					break;
				case 9:
					ID = databaze.nacteniZeSouboru();
					if (ID == -1) {
						System.out.println("Databazi nebylo mozne nacist");
						ID = 0;
						databaze.vycisteniDatabaze();
					}
					else
						System.out.println("Databaze byla nactena");
					break;
				case 10:
					if (databaze.ulozeniDoSouboru(ID))
						System.out.println("Databaze byla ulozena");
					else
						System.out.println("Databazi nebylo mozne ulozit");
					break;
				case 11:
					ID = databaze.nacteniSQL();
					if (ID == -1) {
						System.out.println("Databazi nebylo mozne nacist");
						ID = 0;
						databaze.vycisteniDatabaze();
					}
					else
						System.out.println("Databaze byla nactena");
					break;
				case 12:
					if(databaze.ulozeniSQL(ID))
						System.out.println("Databaze byla ulozena");
					else
						System.out.println("Databazi nebylo mozne ulozit");
					break;
				case 13:
					System.out.println("Jste si jisty ze chcete program ukoncit? Veskere neulozene data budou ztracena.");
					System.out.println("1 .. ano");
					System.out.println("2 .. ne");
					volba = pouzeCelaCisla(sc); //osetreni nechteneho vypnuti programu
					if(volba == 1)
						cyklus = false;
					break;
			}
		}
	}
}
