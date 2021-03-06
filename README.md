# Ohjelmistotekniikka-kurssin harjoitustyö

## Repositorio Ohjelmistotekniikka-kurssille
* harjoitustyö: FridgeApp (jääkaappi-sovellus)
* dokumentaatio
* laskarit yms materiaali omassa kansiossaan

## Harjoitustyö: FridgeApp (Jääkaappi)
* koodi [FridgeApp](https://github.com/terodotus/ot-harjoitustyo/tree/master/JaakaappiTietokantaApp)

### Documentation
* [Arkkitehtuurikuvaus](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/arkkitehtuuri.md)
* [tuntikirjanpito](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/tuntikirjanpito.md)
* [vaatimusmaarittely](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/vaatimusmaarittely.md)
* [Käyttöohje](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/kayttoohje.md)
* [Testausdokumentti](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/testaus.md)

### Release
* [week 5 release](https://github.com/terodotus/ot-harjoitustyo/releases/tag/week5)
* [week 6 release](https://github.com/terodotus/ot-harjoitustyo/releases/tag/week6)
* [week 7 release](https://github.com/terodotus/ot-harjoitustyo/releases/tag/week7)

### Komentorivitoiminnot
#### Testaus
Luokille laaditut testit suoritetaan komennolla:
```
mvn test
```

Testikattavuusraportti onnistuu komennolla:
```
mvn test jacoco:report
```

Checkstyle.xml tiedostoon koodatut tarkistukset ajetaan komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```

#### Jar-tiedoston generointi
Suoritettavan jar-tiedoston voi generoida target-hakemistoon komennolla:
```
mvn package
```
Silloin target kansioon syntyy tiedosto: "FridgeApp-1.0-SNAPSHOT.jar", jonka voi suorittaa komennolla:
```
java -jar FridgeApp-1.0-SNAPSHOT.jar
```

#### JavaDoc
JavaDoc documentation generation can be found in target/site/apidocs/index.html after running command:
```
mvn javadoc:javadoc
```




