# Arkkitehtuurikuvaus

## Structure
Sovellus toimii kolmella tasolla ja lisäksi on tietenkin ohjelman käynnistävä pääohjelma main. Ylimpänä on käyttöliittymäluokka fridgeapp.UI, joka vastaa käyttöliittymästä. Sitä seuraa luokka fridgeapp.domain, jossa ovat ohjelman tärkeimmät toiminnalliset luokat, kuten käyttäjää kuvaava FridgeUser ja yksittäistä jääkaapissa säilytettävää tuotetta kuvaava FridgeItem. Alimpana on luokka dao, jossa ovat tietojen pysyväistallennuksesta vastaavat luokat.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/PakkausRakenne.jpg" width=400 >

## User Interface
Käyttöliittymä tarjoaa mahdollisuuden luoda käyttäjän, kirjautua ohjelmaan ja hallita omien jääkaappien sisältöjä. Kukin näistä toiminnoista on omassa näkymässään.
Katso tarkemmin alla "Sovelluslogiikka", sekä UI: [Vaatimusmäärittely](https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/vaatimusmaarittely.md)

## Sovelluslogiikka
Ohjelman tärkeimmät käsitteet ovat fridgeUser, fridge ja fridgeItem. FridgeUser kuvaa ohjelman käyttäjää, jolla on tiettyjä ominaisuuksia. Fridge kuvaa jääkaappia, joita voi yhdellä käyttäjällä olla useita. FridgeItem kuvaa yksittäistä tuotetta jääkaapissa, jolla on ominaisuuksia, kuten esimerkiksi määrä. Tuotteita voi olla monta käyttäjän monessa jääkaapissa.

### Luokat:
- Fridge: Jääkaappi, jolla on nimi (String). Jääkaappi liittyy tiettyyn käyttäjään ja jaakaapissa on tuotteita (fridgeItem)
- FridgeItem: Tuote, jolla on id (int), nimi (String), kayttäjä (FridgeUser), Jääkaappi (Fridge), ja määrä (int)
- FridgeUser: Käyttäjä, jolla on nimi (String) ja lista jääkaappeja (Fridge). Käyttäjällä voi olla yksi tai useampi jääkaappi. 

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeAppLuokkakaavio_2704.jpg" width=900 >

## Tietojen pysyväistallennus
Tietojen pysyväistallennus on toistaiseksi hoidettu samassa kansiossa olevien tekstitiedostojen avulla. Olisi mahdollista muuttaa H2 tietokannanhallintajärjestelmää hyödyntäväksi tietokannaksi, mitä tutkittiin, mutta todettiin, että on toistaiseksi tarpeetonta ja aiheuttaa monien järjestelmien käytön yhteydessä aika paljon hankaluuksia. Jos ohjelmaa laajennetaan tietokanta tulee kuitenkin tarpeelliseksi. Käyttäjästä tallennetaan username sekä eri jääkaappien nimet user.txt-tiedostoon. Fridge itemit tallennetaan fridgeitems.txt-tiedostoon, jonne tallentuu kunkin itemin id, content, amount, user, ja fridge eli missä jääkaapissa tuote on.

## Tiedostot
Users are saved to the users.txt file:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/userfile.jpg" width=500 >

All the fridge items are saved to the fridgeitems.txt file:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/fridgeitemsfile.jpg" width=500 >

## Testaus
Testien rivikattavuus on nyt noin 75%. Kattavuutta laajennetaan kaikilla tavoilla koko ajan. Tarkoitus olisi tehdä ns test driven mallilla, mutta siinä ei tällä hetkellä aivan olla. 

## Toiminnallisuuksia
Alla olevassa sekvenssikaaviokuvassa kuvataan tilanne, jossa käyttäjä:
- käynnistää FridgeApp -sovelluksen
- kirjautuu sisään käyttäjällä "John" ja oletuskaapilla "JohnsFridge"
- lisää JohnsFridgeen FridgeItemit: "milk, 2", sekä "butter, 1".
- käyttäjä lisää itselleen toisen jääkaapin: "JohnsFridge2"
- käyttäjä ("John") lisää toiseen jääkaappiinsa ("JohnsFridge2") FridgeItemin "milk, 2"

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/Sekvenssikaavio1.jpg" width=900 >

