# Arkkitehtuurikuvaus

## Structure
Sovellus toimii kolmella tasolla ja lisäksi on tietenkin ohjelman käynnistävä pääohjelma main. Ylimpänä on käyttöliittymäluokka fridgeapp.UI, joka vastaa käyttöliittymästä. Sitä seuraa luokka fridgeapp.domain, jossa ovat ohjelman tärkeimmät toiminnalliset luokat, kuten käyttäjää kuvaava FridgeUser ja yksittäistä jääkaapissa säilytettävää tuotetta kuvaava FridgeItem. Alimpana on luokka dao, jossa ovat tietojen pysyväistallennuksesta vastaavat luokat.

fridgeapp.UI
 |
fridgeapp.domain
 |
fridgeapp.dao

Status: osin toteutettu.

## User Interface
Käyttöliittymä tarjoaa mahdollisuuden luoda käyttäjän, kirjautua ohjelmaan ja hallita oman jääkaapin sisältöä. Kukin näistä toiminnoista on omassa näkymässään.
Status: osin toteutettu. Vasta erittäin yksinkertainen käyttöliittymä.

## Sovelluslogiikka
Ohjelman tärkeimmät käsitteet ovat fridgeUser ja fridgeItem. FridgeUser kuvaa ohjelman käyttäjää, jolla on tiettyjä ominaisuuksia. FridgeItem kuvaa yksittäistä tuotetta jääkaapissa, jolla on ominaisuuksia, kuten esimerkiksi määrä.
Status: osin toteutettu. Tämänhetkinen toteutus mahdollistaa vasta yhden jääkaapin kullekin käyttäjälle ja kullakin jääkaapilla on vain yksi käyttäjä.

## Tietojen pysyväistallennus
Tietojen pysyväistallennus on toistaiseksi hoidettu samassa kansiossa olevien tekstitiedostojen avulla. Saatetaan muuttaa H2 tietokannanhallintajärjestelmää hyödyntäväksi tietokannaksi.
Status: tietoja voi jo tallettaa tämänhetkisen sovelluslogiikan mukaan. Jos sovelluslogiikka muuttuu, pitää myös tietojen tallennusta miettiä uudelleen.

## Testaus
Testien rivikattavuus on nyt noin 28%. Kattavuutta laajennetaan kaikilla tavoilla koko ajan. Tarkoitus olisi tehdä ns test driven mallilla, mutta siinä ei tällä hetkellä aivan olla. 