# xmacho11_projekt_PC2T_2022

## Cíl projektu
Program sloužící na vytváření a editaci databáze studentů.

## Obecná funkčnost prjetku
Veěkeré data jsou uložena ve vnitřní paměti počítače, tyto data lze Uložit do SQL databáze, soubor ![DatabazeStudentu.db](xmacho11_projekt_PC2T_2022/DatabazeStudentu.db), nebo do .txt souboru dle vlastního názvu, vzorový soubor ![testovaci.txt](xmacho11_projekt_PC2T_2022/testovaci.txt). Ukladání do souboru nebo SQL databáze probíhá pomocí funkcí a tejně tak načítání z těchto cílů.


Program se se soubory chová analogicky jako například MS Word a .docx soubory. Program lze zapnout prázdný a začít novou databázi, nebo lze při zapnutí rovnou nahrát již existující data a pokračovat v jejich editaci.


Pomocí programu lze zapsat nového studenta, zapsat známku studentovi, vymazat studenta, vypsat informace o studentovi, zavolat dovendost studenta, abecední výpis všech studentů ve skupinách, výpis obecného studijního průměru skupin, výpis počtu studentů ve skupinách, uložení a nahrání dat ze souborů a SQL databáze.

## Funkce
||||
| :-: | :-: | :-: |
|1| Příjmutí studenta | uživatel je vyzván vybrání studijní skupiny nového studenta a následně zadání jména příjmení a roku narození|
|2| Zapsání nové známmky | uživatel vybere studenta pomocí jeho ID a zapíše novou známku|
|3| Propuštění studenta | uživatel vybere studenta pomocí jeho ID a následně se tento student smaže|
|4| Informace o studentovi | uživatel vybere studenta pomocí jeho ID a vypíšou se parametry ID, jméno, příjmení, rok narození a sudijní průměr vybraného studenta|
|5| Dovednost studenta | uživatel vybere studenta pomocí jeho ID a projeví se dovednost studenta podle jeho studijní skupiny|
|6| Abecední výpis studentů | vypíše se celá databáze studentů seřazena do studijních skupin a jednotlivé skupiny jsou seřazeny abecedně podle příjmení studenta|
|7| Výpis obecného studijního průměru | vypíše se obecný studijní průměr ve studijních skupinách|
|8| Výpis počtu studentů v oborech | vypíše se počet studentů v každé studijní skupině |
|9| Načtení databáze ze souboru | uživatel zadá název souboru ze kterého chce načíst data které se následě nahrají a mohou se dále editovat |
|10| Uložení databáze do souboru | uživatel zadá název suboru do kterého chce databázi uložit, lze přepsat již existující soubor |
|11| Načtení z SQL databáze | data se načtou z hlavní SQL databáze |
|12| Uložení do SQL databáze | data se uloží do hlavní SQL databáze |
|13| Konec | ukončení programu, veškerá data které nejsou uložena v SQL databázi nebo v souboru budou ztracena|

## Vnitřní parametry projektu
Data jsou uloženy ve 3 mapách řazených podle parametru ID který je jedinečný pro všechny 3 mapy dohromady.

Každý student má parametry: ID, jméno, příjmení, rok narození, studijní půměr a počet známek studenta. Studijní skupina studenta je rozlišena pomocí toho v jaké mapě je student uložen. Každý student má jestě jedinečnou dovednost podle toho do jaké studijní skupiny patří, techycká skupina pozná zda rok jejich narození byl přestupný rok, humanitní skupina zná své znamení Čínského horoskopu, kombinovaná skupina umí obě předcházející dovednosti.

Uživatel při přidání nového studenta vyplní jeho studijní obor, jméno, příjmení a rok narození a automaticky je studentovi přiřazeno jedinečné ID. tyto parametry jsou neměnné. Uživatel poté nasledovně může studentům zapisovat nové známky (od 1 do 5) ze kterých je automaticky počítán studijní průměr který lze zobrazit, parametr počet známek se nastavuje sám a uživatel nemá možnost přímo měnit.

### Zdroje
Přednášky a prezentace kurzu PC2T

Webové fórum https://stackoverflow.com/
