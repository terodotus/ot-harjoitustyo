# Arkkitehtuurikuvaus

## Structure
Sovellus toimii kolmella tasolla ja lisäksi on tietenkin ohjelman käynnistävä pääohjelma main. Ylimpänä on käyttöliittymäluokka fridgeapp.UI, joka vastaa käyttöliittymästä. Sitä seuraa luokka fridgeapp.domain, jossa ovat ohjelman tärkeimmät toiminnalliset luokat, kuten käyttäjää kuvaava FridgeUser ja yksittäistä jääkaapissa säilytettävää tuotetta kuvaava FridgeItem. Alimpana on luokka dao, jossa ovat tietojen pysyväistallennuksesta vastaavat luokat.

fridgeapp.UI
 |
fridgeapp.domain
 |
fridgeapp.dao

## User Interface
Käyttöliittymä tarjoaa mahdollisuuden luoda käyttäjän, kirjautua ohjelmaan ja hallita omien jääkaappien sisältöjä. Kukin näistä toiminnoista on omassa näkymässään.
Status: osin toteutettu. Vasta erittäin yksinkertainen käyttöliittymä.

## Sovelluslogiikka
Ohjelman tärkeimmät käsitteet ovat fridgeUser, fridge ja fridgeItem. FridgeUser kuvaa ohjelman käyttäjää, jolla on tiettyjä ominaisuuksia. Fridge kuvaa jääkaappia, joita voi yhdellä käyttäjällä olla useita. FridgeItem kuvaa yksittäistä tuotetta jääkaapissa, jolla on ominaisuuksia, kuten esimerkiksi määrä. Tuotteita voi olla monta käyttäjän monessa jääkaapissa.


## Tietojen pysyväistallennus
Tietojen pysyväistallennus on toistaiseksi hoidettu samassa kansiossa olevien tekstitiedostojen avulla. Saatetaan muuttaa H2 tietokannanhallintajärjestelmää hyödyntäväksi tietokannaksi. Käyttäjästä tallennetaan username sekä eri jääkaappien nimet user.txt-tiedostoon. Fridge itemit tallennetaan fridgeitems.txt-tiedostoon, jonne tallentuu kunkin itemin id, content, amount, user, ja fridge eli missä jääkaapissa tuote on.

## Tiedostot
Users are saved to the users.txt file:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/userfile.jpg" width=500 >

All the fridge items are saved to the fridgeitems.txt file:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/fridgeitemsfile.jpg" width=500 >

## Testaus
Testien rivikattavuus on nyt noin 40%. Kattavuutta laajennetaan kaikilla tavoilla koko ajan. Tarkoitus olisi tehdä ns test driven mallilla, mutta siinä ei tällä hetkellä aivan olla. 

