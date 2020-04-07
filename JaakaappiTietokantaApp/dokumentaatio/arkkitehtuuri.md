# Arkkitehtuurikuvaus

## Structure
Sovellus toimii kolmella tasolla ja lisäksi on tietenkin ohjelman käynnistävä pääohjelma main. Ylimpänä on käyttöliittymäluokka fridgeapp.UI, joka vastaa käyttöliittymästä. Sitä seuraa luokka fridgeapp.domain, jossa ovat ohjelman tärkeimmät toiminnalliset luokat, kuten käyttäjää kuvaava FridgeUser ja yksittäistä jääkaapissa säilytettävää tuotetta kuvaava FridgeItem. Alimpana on luokka dao, jossa ovat tietojen pysyväistallennuksesta vastaavat luokat.

fridgeapp.UI
 |
fridgeapp.domain
 |
fridgeapp.dao

## User Interface
Käyttöliittymä tarjoaa mahdollisuuden luoda käyttäjän, kirjautua ohjelmaan ja hallita oman jääkaapin sisältöä. Kukin näistä toiminnoista on omassa näkymässään.

## Sovelluslogiikka

## Tietojen pysyväistallennus
Tietojen pysyväistallennus on toistaiseksi hoidettu samassa kansiossa olevien tekstitiedostojen avulla. Saatetaan muuttaa H2 tietokannanhallintajärjestelmää hyödyntäväksi tietokannaksi.

## 
