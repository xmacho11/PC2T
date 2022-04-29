package xmacho11_projekt_PC2T_2022;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;

public class Databaze extends SQL {
	
	private Map<Integer, TechnickyStudent> technickyStudent; //mapy pro 3 obory
	private Map<Integer, HumanitniStudent> humanitniStudent;
	private Map<Integer, KombinovanyStudent> kombinovanyStudent;
	
	Scanner sc = new Scanner(System.in);
	
	final String oddelovacPrvku = ","; //promene pro zapis a cteni ze souboru
	final String oddelovacOboru = "---";
		
	public Databaze() { //vytvoreni databaze
				technickyStudent = new HashMap<Integer, TechnickyStudent>();
				humanitniStudent = new HashMap<Integer, HumanitniStudent>();
				kombinovanyStudent = new HashMap<Integer, KombinovanyStudent>();
	}
	
	public void vycisteniDatabaze() { //funkce pro vycisteni vsech map
		technickyStudent.clear();
		humanitniStudent.clear();
		kombinovanyStudent.clear();
	}
	
	public boolean setStudent(int obor, int ID, String jmeno, String prijmeni, int rok) {
		switch(obor) { //vyber mapy pro zapsani
			case 1:
				if(technickyStudent.put(ID, new TechnickyStudent(jmeno, prijmeni, rok)) == null) //volani funkce + osetreni uspesneho vytvoreni
					return true;
				return false;
			case 2:
				if(humanitniStudent.put(ID, new HumanitniStudent(jmeno, prijmeni, rok)) == null)
					return true;
				return false;
			case 3:
				if(kombinovanyStudent.put(ID, new KombinovanyStudent(jmeno, prijmeni, rok)) == null)
					return true;
				return false;
		}
		return false;
	}
	
	public boolean addZnamka(int ID, int znamka) {
		if(znamka > 5 || znamka < 1) //podminka validni znamky pro zapsani
			return false;
		
		if(technickyStudent.get(ID) != null) //nalezeni existence studenta
			return technickyStudent.get(ID).addZnamka(znamka); //volani funkce
		else if(humanitniStudent.get(ID) != null)
			return humanitniStudent.get(ID).addZnamka(znamka);		
		else if(kombinovanyStudent.get(ID) != null)
			return kombinovanyStudent.get(ID).addZnamka(znamka);
		else
			return false;
	}
	
	public boolean propusteniStudenta(int ID) {
		if(technickyStudent.remove(ID) != null)
			return true;
		else if(humanitniStudent.remove(ID) != null)
			return true;
		else if(kombinovanyStudent.remove(ID) != null)
			return true;
		else;
			return false;
	}
	
	public Student getStudent(int ID) {
		if(technickyStudent.get(ID) != null) 
			return technickyStudent.get(ID); 
		else if(humanitniStudent.get(ID) != null)
			return humanitniStudent.get(ID);
		else if(kombinovanyStudent.get(ID) != null)
			return kombinovanyStudent.get(ID);
		else
			return null;
	}
		
	public boolean getDovednost(int ID) {
		if(technickyStudent.get(ID) != null) {
			technickyStudent.get(ID).dovednost();
			return true;
		}	
		else if(humanitniStudent.get(ID) != null) {
			humanitniStudent.get(ID).dovednost();
			return true;
		}
		else if(kombinovanyStudent.get(ID) != null) {
			kombinovanyStudent.get(ID).dovednost();
			return true;
		}
		else
			return false;
	}
	
	public void getSeznam() {
		System.out.println("Studenti technickeho oboru");
		Set<Entry<Integer, TechnickyStudent>> entrySetT = technickyStudent.entrySet(); //nacteni seznamu klicu mapy
		List<Entry<Integer, TechnickyStudent>> listT = new ArrayList<>(entrySetT); //vytvoreni listu studentu
		Collections.sort(listT, new Comparator<Entry<Integer, TechnickyStudent>>(){ //serazeni listu studentu
			@Override 
			public int compare(Entry<Integer, TechnickyStudent> o1, Entry<Integer, TechnickyStudent> o2) {
				return o1.getValue().getPrijmeni().compareTo(o2.getValue().getPrijmeni());
			}	
		});
		listT.forEach(e->{ //cyklus pro vypsani celeho listu, nazelen na stackoverflow
			System.out.println("ID: " + e.getKey()+ " .. " + technickyStudent.get(e.getKey()).getJmeno() + " " + technickyStudent.get(e.getKey()).getPrijmeni() + "\t rok narozeni: " + technickyStudent.get(e.getKey()).getRok() + "\t studijni prumer: " + technickyStudent.get(e.getKey()).getPrumer());
		});
		
		System.out.println("Studenti humanitniho oboru");
		Set<Entry<Integer, HumanitniStudent>> entrySetH = humanitniStudent.entrySet();
		List<Entry<Integer, HumanitniStudent>> listH = new ArrayList<>(entrySetH);
		Collections.sort(listH, new Comparator<Entry<Integer, HumanitniStudent>>(){
			@Override
			public int compare(Entry<Integer, HumanitniStudent> o1, Entry<Integer, HumanitniStudent> o2) {
				return o1.getValue().getPrijmeni().compareTo(o2.getValue().getPrijmeni());
			}	
		});
		listH.forEach(e->{
			System.out.println("ID: " + e.getKey()+ " .. " + humanitniStudent.get(e.getKey()).getJmeno() + " " + humanitniStudent.get(e.getKey()).getPrijmeni() + "\t rok narozeni: " + humanitniStudent.get(e.getKey()).getRok() + "\t studijni prumer: " + humanitniStudent.get(e.getKey()).getPrumer());
		});
		
		System.out.println("Studenti kombinovaneho studia");
		Set<Entry<Integer, KombinovanyStudent>> entrySetK = kombinovanyStudent.entrySet();
		List<Entry<Integer, KombinovanyStudent>> listK = new ArrayList<>(entrySetK);
		Collections.sort(listK, new Comparator<Entry<Integer, KombinovanyStudent>>(){
			@Override
			public int compare(Entry<Integer, KombinovanyStudent> o1, Entry<Integer, KombinovanyStudent> o2) {
				return o1.getValue().getPrijmeni().compareTo(o2.getValue().getPrijmeni());
			}	
		});
		listK.forEach(e->{
			System.out.println("ID: " + e.getKey()+ " .. " + kombinovanyStudent.get(e.getKey()).getJmeno() + " " + kombinovanyStudent.get(e.getKey()).getPrijmeni() + "\t rok narozeni: " + kombinovanyStudent.get(e.getKey()).getRok() + "\t studijni prumer: " + kombinovanyStudent.get(e.getKey()).getPrumer());
		});	
	}
	
	public void getObecnyPrumer() {
		
		double obecnyPrumer = 0; //promena pro ulozeni celkoveho prumeru
		
		Set <Integer> keyT=technickyStudent.keySet(); //set klicu mapy
		for (Integer ID:keyT) //cyklus pro nacteni studijniho prumeru vsech studentu ve skupine
			obecnyPrumer = obecnyPrumer + technickyStudent.get(ID).getPrumer();
		obecnyPrumer = obecnyPrumer/(float)technickyStudent.size();
		System.out.println("Prumer studentu technickeho oboru: " + obecnyPrumer); //vypsani prumeru do konzole
		obecnyPrumer = 0; //reset promene pro dalsi cast funkce
		
		Set <Integer> keyH=humanitniStudent.keySet();
		for (Integer ID:keyH)
			obecnyPrumer = obecnyPrumer + humanitniStudent.get(ID).getPrumer();
		obecnyPrumer = obecnyPrumer/(float)humanitniStudent.size();
		System.out.println("Prumer studentu humanitniho oboru: " + obecnyPrumer);
		obecnyPrumer = 0;
		
		Set <Integer> keyK=kombinovanyStudent.keySet();
		for (Integer ID:keyK)
			obecnyPrumer = obecnyPrumer + kombinovanyStudent.get(ID).getPrumer();
		obecnyPrumer = obecnyPrumer/(float)kombinovanyStudent.size();
		System.out.println("Prumer studentu kombinovaneho studia: " + obecnyPrumer);
		obecnyPrumer = 0;
	}
	
	public void getPocetStudentu() { 
		System.out.println("Pocet studentu technickeho oboru: " + technickyStudent.size()); //vypis do  konzole + volani funkce
		System.out.println("Pocet studentu humanitniho oboru: " + humanitniStudent.size());
		System.out.println("Pocet studentu kombinovaneho studia: " + kombinovanyStudent.size());
	}
	
	public boolean ulozeniDoSouboru(int ID) {
		String nazevCileUlozeni; //deklarace promenych
		String potvrzeni;
		
		System.out.println("Zadejte nazev souboru do ktereho chcete ulozit databazi. format: mojeDatabaze.txt");
		nazevCileUlozeni = sc.next(); //nacteni vstupu uzivatele
		
		if(nazevCileUlozeni.lastIndexOf(".txt")<0) { //autooprava chybejici pripony souboru
			nazevCileUlozeni = nazevCileUlozeni + ".txt";
		}
				
		try {
			File pomocnySoubor = new File(nazevCileUlozeni); //omezeni nechteneho prepsani existujiciho souboru
			boolean exists = pomocnySoubor.exists(); //zjisteni existence souboru
			if(exists == true) {
				System.out.println("Soubor s nazvem " + nazevCileUlozeni + " jiz existuje, chcete jej prepsat?");
				System.out.println("1 .. ANO \n2 .. NE");
				potvrzeni = sc.next();
				if(!potvrzeni.equals("1")) {
					return false;	
				}
			}
			
			FileWriter fw = new FileWriter(nazevCileUlozeni); //zapisovace do souboru
			BufferedWriter out = new BufferedWriter(fw);
			
			Set<Entry<Integer, TechnickyStudent>> entrySetT = technickyStudent.entrySet(); //listy a klice k nim
			List<Entry<Integer, TechnickyStudent>> listT = new ArrayList<>(entrySetT);
			Set<Entry<Integer, HumanitniStudent>> entrySetH = humanitniStudent.entrySet();
			List<Entry<Integer, HumanitniStudent>> listH = new ArrayList<>(entrySetH);
			Set<Entry<Integer, KombinovanyStudent>> entrySetK = kombinovanyStudent.entrySet();
			List<Entry<Integer, KombinovanyStudent>> listK = new ArrayList<>(entrySetK);
			
			out.write(new String("" + ID)); //zapsani nejvyssiho id pro zpetne nacteni ze souboru
			out.newLine();
			
			out.write(new String(oddelovacOboru + "Technicky obor" + oddelovacOboru));
			out.newLine();
			listT.forEach(e->{ //cyklus pro zapsani celeho listu do souboru, nazelen na stackoverflow
				try {
					out.write(new String(e.getKey()+ oddelovacPrvku + technickyStudent.get(e.getKey()).getJmeno() + oddelovacPrvku + technickyStudent.get(e.getKey()).getPrijmeni() + oddelovacPrvku + technickyStudent.get(e.getKey()).getRok() + oddelovacPrvku + technickyStudent.get(e.getKey()).getPrumer() + oddelovacPrvku + technickyStudent.get(e.getKey()).getPocetZnamek()) + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

			out.write(new String(oddelovacOboru + "Humanitni obor" + oddelovacOboru));
			out.newLine();
			listH.forEach(e->{
				try {
					out.write(new String(e.getKey()+ oddelovacPrvku + humanitniStudent.get(e.getKey()).getJmeno() + oddelovacPrvku + humanitniStudent.get(e.getKey()).getPrijmeni() + oddelovacPrvku + humanitniStudent.get(e.getKey()).getRok() + oddelovacPrvku + humanitniStudent.get(e.getKey()).getPrumer() + oddelovacPrvku + humanitniStudent.get(e.getKey()).getPocetZnamek()) + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			
			out.write(new String(oddelovacOboru + "Kombinovane studium" + oddelovacOboru));
			out.newLine();
			listK.forEach(e->{
				try {
					out.write(new String(e.getKey()+ oddelovacPrvku + kombinovanyStudent.get(e.getKey()).getJmeno() + oddelovacPrvku + kombinovanyStudent.get(e.getKey()).getPrijmeni() + oddelovacPrvku + kombinovanyStudent.get(e.getKey()).getRok() + oddelovacPrvku + kombinovanyStudent.get(e.getKey()).getPrumer() + oddelovacPrvku + kombinovanyStudent.get(e.getKey()).getPocetZnamek()) + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			out.write(new String(oddelovacOboru + oddelovacOboru + oddelovacOboru + oddelovacOboru + oddelovacOboru + oddelovacOboru + oddelovacOboru));
			
			out.close(); //uzavreni zapisovacu
			fw.close();
			return true;
		}
		catch (IOException e) {
			return false;
		}
		
	}
	
	public int nacteniZeSouboru() {
		String nazevCileNacteni; //deklarace promennych
		int newID = 0;
		int obor = 0;
		
		FileReader cteckaSouboru = null; //ctecky ze souboru
		BufferedReader vstupZeSouboru = null;
		
		System.out.println("Zadejte nazev souboru ze ktereho chcete nacist databazi. format: mojeDatabaze.txt");
		nazevCileNacteni = sc.next();
		
		if(nazevCileNacteni.lastIndexOf(".txt")<0) { 
			nazevCileNacteni = nazevCileNacteni + ".txt";
		}


		try {
			cteckaSouboru = new FileReader(nazevCileNacteni); 
			vstupZeSouboru = new BufferedReader(cteckaSouboru);
			
			String radek = vstupZeSouboru.readLine();
			String[] text = radek.split(oddelovacPrvku);
			newID = Integer.valueOf(text[0]); //nahrani nejvyssiho ID pro osetreni duplikatnich ID
			
			vycisteniDatabaze();
			
			while(obor != 4) { //cyklus pro nacteni celeho souboru
				radek = vstupZeSouboru.readLine(); //nacteni radku
				text = radek.split(oddelovacPrvku); //rozdeleni radku na jenotlive prvky
				
				if(text[0].contains(oddelovacOboru) == true) //podminka nalezeni oddelovaciho prvku mezi obory
					obor++;
				
				if(text.length == 6) { //nacteni pouze 6 prvkoveho radku, jen radky s daty
					switch(obor) {
					case 1:
						technickyStudent.put(Integer.valueOf(text[0]), new TechnickyStudent(text[1], text[2], Integer.valueOf(text[3]))); //zapsani dat prez funkci do databaze
						if(technickyStudent.get(Integer.valueOf(text[0])) != null) { //podminka pri supesnem prvnim zapsani dopsani dat
							technickyStudent.get(Integer.valueOf(text[0])).setPrumer(Double.valueOf(text[4])); //dopsani technickych dat ktere uzivatel nemuze na primo prepsat
							technickyStudent.get(Integer.valueOf(text[0])).setPocetZnamek(Integer.valueOf(text[5]));
						}
						break;
					case 2:
						humanitniStudent.put(Integer.valueOf(text[0]), new HumanitniStudent(text[1], text[2], Integer.valueOf(text[3])));
						if(humanitniStudent.get(Integer.valueOf(text[0])) != null) {
							humanitniStudent.get(Integer.valueOf(text[0])).setPrumer(Double.valueOf(text[4]));
							humanitniStudent.get(Integer.valueOf(text[0])).setPocetZnamek(Integer.valueOf(text[5]));
						}
						break;
					case 3:
						kombinovanyStudent.put(Integer.valueOf(text[0]), new KombinovanyStudent(text[1], text[2], Integer.valueOf(text[3])));
						if(kombinovanyStudent.get(Integer.valueOf(text[0])) != null) {
							kombinovanyStudent.get(Integer.valueOf(text[0])).setPrumer(Double.valueOf(text[4]));
							kombinovanyStudent.get(Integer.valueOf(text[0])).setPocetZnamek(Integer.valueOf(text[5]));
						}
						break;
					}
				}
			}
	
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
			return -1;
		}
		
		finally //finalni uzavreni souboru nacitani
		{
			try {
				if (vstupZeSouboru != null) {
					vstupZeSouboru.close();
					cteckaSouboru.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
				return -1;
			} 
		}
		return newID;
	}
	
	public boolean ulozeniSQL(int ID) {
				
		if(!connect()) //podminka pripojeni do databaze SQL
			return false;
		
		vytvoreniTabulek(); //vytvoreni tabulek pokud by neexitovali
		vycisteniSQL(); //vycisteni exitujici databaze pro uvolneni mista pro novou
		
		
		for(int i = 0 ; i <= ID ; i++) { //nahrani cele databaze do SQL
			if(technickyStudent.get(i) != null)
				nahraniZaznamu(i, 1, technickyStudent.get(i).getJmeno(), technickyStudent.get(i).getPrijmeni(), technickyStudent.get(i).getRok(), technickyStudent.get(i).getPrumer(), technickyStudent.get(i).getPocetZnamek());
			else if(humanitniStudent.get(i) != null)
				nahraniZaznamu(i, 2, humanitniStudent.get(i).getJmeno(), humanitniStudent.get(i).getPrijmeni(), humanitniStudent.get(i).getRok(), humanitniStudent.get(i).getPrumer(), humanitniStudent.get(i).getPocetZnamek());
			else if(kombinovanyStudent.get(i) != null)
				nahraniZaznamu(i, 3, kombinovanyStudent.get(i).getJmeno(), kombinovanyStudent.get(i).getPrijmeni(), kombinovanyStudent.get(i).getRok(), kombinovanyStudent.get(i).getPrumer(), kombinovanyStudent.get(i).getPocetZnamek());
		}
		disconnect();
		return true;
	}
	
	public int nacteniSQL() {
		int newID = 0;

		if(!connect())
			return -1;
		
		vycisteniDatabaze();
		
		
		
		
		//technickyStudent.put(1, new TechnickyStudent("a", "a", 1));
		//if(technickyStudent.get(Integer.valueOf(text[0])) != null) { //podminka pri supesnem prvnim zapsani dopsani dat
			//technickyStudent.get(Integer.valueOf(text[0])).setPrumer(Double.valueOf(text[4])); //dopsani technickych dat ktere uzivatel nemuze na primo prepsat
			//technickyStudent.get(Integer.valueOf(text[0])).setPocetZnamek(Integer.valueOf(text[5]));
		
		
		disconnect();
		return newID;
	}
}
