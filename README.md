# CALCULATOR
Calculator applikáció Főnix IT számára.

## Leírás

Az applikációban megvalósításra került egy egyszerű számológép, melyben elvégezhetők az `alapvető aritmetikai műveletek`
(összeadás, kivonás, szorzás, osztás), illetve képes visszaadni a `Fibonacci sorozat n-edik elemét`. 

Az egyes funkciók tesztelésre kerültek, a tesztek megtalálhatók a forráskód `"src/test/java/calculatorapp"` csomagjában.

---

## Felépítés

### Kontroller réteg

A kontroller réteget a `CalculatorController` osztály valósítja meg, melyben REST végpontokon, GET kéréseken keresztül
történik meg az egyes funkciók hívása.

Végpontok:

| HTTP metódus | Végpont                 	    | Leírás                                                                       	                  |
| ------------ | -------------------------------| ------------------------------------------------------------------------------------------------|
| GET          | `"/api/calculator/add"`   		| Összeadja a paraméterként megadott két valós számot, és visszatér az eredménnyel.               |
| GET          | `"/api/calculator/multiply"`  	| Összeszorozza a paraméterként megadott két valós számot, és visszatér az eredménnyel.           |
| GET          | `"/api/calculator/subtract"`   | Kivonja egymásból a paraméterként megadott két valós számot, és visszatér az eredménnyel.       |
| GET          | `"/api/calculator/divide"`     | Az első paraméterként megadott számot elosztja a második számmal, és visszatér az eredménnyel.  |	
| GET          | `"/api/calculator/fibonacci"`  | A Fibonacci sorozatból visszaadja a paraméterként megadott elemet. (Az első elem az 1-es.)      |

A végpontokban szükséges átadni azokat a számokat, melyeken a műveletet el kívánjuk végezni. A megadás az URL-ben `Query String` segítségével történik. 
Például: `/api/calculator/add?number=4&otherNumber=10.5`

### Szervíz réteg

A kontroller rétegben elküldött kéréseket a szervíz réteg metódusai hajtják végre, melyek a `CalculatorService` osztályban találhatók. Minden végpont esetében validálásra kerül,
hogy megfelelő paraméter került-e megadásra. A következő validálások történnek:

* Az aritmetikai műveleteknél szükséges mindkét szám megadása paraméterként. A szám lehet valós szám, az ABC karakterei és egyéb speciális karakterek viszont nem elfogadhatók.
* Osztás (divide) műveletnél a második paraméter (otherNumber) nem lehet nulla. 
* A Fibonacci sorozatnál egy pozitív természetes szám megadása kötelező. Nulla, negatív szám vagy tizedestört nem elfogadható.

A validálások megsértése esetén az alkalmazás az adott hibát tartalazó kivételt ad vissza. 

### Tesztesetek

Az alkalmazás mindkét rétege tesztelésre került. A szervíz réteghez unit tesztek készültek (JUnit), míg a kontroller réteghez integrációs tesztek
kerültek megírásra (JUnit és WebTestClient). Előbbiek a `CalculatorServiceTest`, utóbbiak a `CalculatorControllerIT` osztályban találhatók. 

Mind a szervíz, mind a kontroller réteg metódusaihoz az alábbi tesztek készültek el:

* Összeadás (addition) esetén: helyes bementek tesztelése, hiányzó bemenetek tesztelése, nem számot tartalmazó bemenetek tesztelése.
* Szorzás (multiplication) esetén: helyes bementek tesztelése, hiányzó bemenetek tesztelése, nem számot tartalmazó bemenetek tesztelése.
* Kivonás (subtraction) esetén: helyes bementek tesztelése, hiányzó bemenetek tesztelése, nem számot tartalmazó bemenetek tesztelése.
* Osztás (division) esetén: helyes bementek tesztelése, hiányzó bemenetek tesztelése, nem számot tartalmazó bemenetek tesztelése, nullával való osztás tesztelése.
* Fibonacci metódus esetén: helyes bemenet tesztelése, hiányzó bemenet tesztelése, nem egész számot tartalmazó bemenete tesztelése, nullát vagy negatív számot tartalmazó bemenet tesztelése.

Az alkalmazáshoz készült `src/test/resources/calculator.http` fájlban HTTP kérések találhatók, melyekkel szintén tesztelhető az alkalmazás működése.

## Technológiai részletek

Az alkalmazás 17-es JAVA verzióval készült, Spring Boot implementálásával. 

Az applikáció egy kétrétegű alkalmazás, melyben a funkciókat egy kontroller és egy szervíz réteg valósítja meg. Mivel adatok rögzítése adatbázisban nem történik a működés közben, ezért repozitori réteg nem készült. A kivitelezés `REST API` integrálásával
történt.

Az alkalmazáshoz belső dokumentáció is készült a SwaggerUI implementációjával, melynek segítségével az alkalmazás működése megtekinthető
a http://localhost:8080/swagger-ui.html címen (az alkalmazás futtatása közben).

A mellékelt Dockerfile segítségével `Docker image` generálható, így az alkalmazás Dockerből is futtatható. Továbbá az alkalmazás futtatható verzióját tartalmazó
docker image letölthető a Docker Hubról és futtatható a következő paranccsal: `docker run -d -p 8080:8080 --name calculator gdomjan83/test:calculator` 
Ezt követően az alkalmazás működés közben is megtekinthető a http://localhost:8080/swagger-ui.html címen.

---