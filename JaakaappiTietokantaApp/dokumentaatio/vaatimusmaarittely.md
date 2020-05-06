# JaakaappiTietokantaApp Vaatimusmäärittely / FridgeApp software requirements specification document

## Sovelluksen tarkoitus
Jääkaapin sisällön ajantasaisuudesta huolehtiva sovellus on todellinen klassikko jo tietojenkäsittelyn ja tietokoneiden historian varhaisilta ajoilta asti. Kaupallisia tai toimivia sovelluksia on kuitenkin näkynyt vähän. Nyt, kun esineiden internet (IoT) on tullut osaksi arkipäiväisiä käyttötapauksia, on tullut aika saattaa jääkaappisovellus tuotantoon ja laajaan levitykseen. Tämä projekti tarjoaa tuotekehityksen ja varhaisen toteutuksen jääkaappisovelluksen laajemmalle kaupalliselle tai ei-kaupalliselle tuotannolle. Esineiden internet jää tässä vaiheessa tämän sovelluksen ulkopuolelle, joten itsestään päivittyvää sovellusta ei tässä projektissa vielä tehdä, mutta suunnittelussa pidetään mielessä tämän laajennuksen mahdollinen myöhempi lisääminen.

## Käyttäjät
Koska yhdellä jääkaapilla on yleensä varsin rajallinen määrä käyttäjia, on sovelluksella alkuvaiheessa vain yksi käyttäjärooli, eli normaali käyttäjä. Koska jääkaappien käyttäjillä kuitenkin saattaa olla tarvetta erilaisille rooleille tai vastuille, saatetaan sovellukselle myöhemmin lisätä pääkäyttäjä-rooli tai esimerkiksi eräänlainen katsoja-rooli, joka mahdollistaisi jääkaapin sisällön tarkastelun, mutta ei muutosten tekemistä sisältöön.

## Perusversion tarjoama toiminnallisuus
Jääkaapissa on tuotteita. Tuotteilla on ominaisuuksia, kuten määrä. Käyttäjä pystyy sovelluksesta käsin katsomaan kaapin sisällön. Käyttäjä pystyy päivittämään tuotteet ja tuotteiden tiedot. Käyttäjä voi lisätä ja poistaa jääkaappeja. Tiedot tallennetaan ja niihin voidaan jälleen palata uudelleen. 

### Luokat:
- Fridge: Jääkaappi, jolla on nimi (String). Jääkaappi liittyy tiettyyn käyttäjään ja jaakaapissa on tuotteita (fridgeItem)
- FridgeItem: Tuote, jolla on id (int), nimi (String), kayttäjä (FridgeUser), Jääkaappi (Fridge), ja määrä (int)
- FridgeUser: Käyttäjä, jolla on nimi (String) ja lista jääkaappeja (Fridge). Käyttäjällä voi olla yksi tai useampi jääkaappi. 

## Käyttöliittymäluonnos
Kun uusi käyttäjä luodaan on käyttäjä nimeää itselleen oletusjääkaapin (default fridge), jonka voi ottaa käyttöön ja kustomoida omanlaisekseen. Oletusjääkaapin voi kirjauduttuaan vaihtaa. Kirjauduttuaan ohjelmaan, käyttäjä voi myös lisätä jääkaappeja tarvittaessa (esimerkiksi kesämökin jääkaappi, tai työtilan jääkaappi, alakerran jääkaappi jne.). Näiden jääkaappien näkymien välillä voi siirtyä valitsemalla valikosta "open other fridge". Kaikkien jääkaappien listaus näkyy jääkaappinäkymässä vasemmalla ("Fridges", katso kuva alla). Avoinna oleva jääkaappi näkyy keskellä. Ylhäällä on tieto siitä, mikä jääkaappi on näkyvissa (esim. "John logged in JohnsFridge1"). 

Valikoista löytyy lisäksi seuraavat toiminnot:

### Login Scene:
- Käyttäjä voi kirjautua ohjelmaan olemassaolevalla käyttäjätunnuksella.
- Mikäli syöte on virheellinen, tulostuu käyttäjälle viesti: "user does not exist"
- Jos käyttäjätunnusta ei ole, käyttäjä voi siirtyä luomaan sellaisen, siirtymällä "New User Sceneen" napilla "create a new fridge user". 

### New User Scene:
- Käyttäjä voi luoda itselleen käyttäjänimen ja ensimmäisen jääkaapin, oletusjääkaapin, eli default fridgen. Käyttäjä täyttää usernamen ja fridge namen ja painaa "create". Ohjelma palaa login sceneen, jonne tulostuu "new user created"
- Mikäli käyttäjä yrittää luoda tunnuksen, joka on jo olemassa, tulostuu viesti: "Username has to be unique".
- Mikäli käyttäjänimi on lian lyhyt tai sisältää laittomia merkkejä, tulostuu "username or fridge name too short or contains illegal characters (%#;&)
- Mikäli käyttäjä huomaa virheellisesti tulleensa new user sceneen (muistaakin tunnuksensa tms), eikä haluakaan luoda uutta käyttäjää, pääsee sieltä pois napilla "back" ilman, että tarvitsee luoda uutta käyttäjää.


### Fridge Scene:
- avoinna oleva jääkaappi voidaan muuttaa oletusjääkaapiksi (default fridge) napista "make this default fridge". Jos kyseinen kaappi on jo oletuskaappi, tulostetaan viesti "already default fridge", muussa tapauksessa kyseisestä kaapista tehdään default fridge.
- jääkaappi voidaan poistaa napista "remove this fridge". Mikäli jääkaappeja on vain yksi, sitä ei poisteta ja viestinä annetaan "cannot remove if only one fridge".
- näkyvissä olevaan jääkaappiin voidaan lisätä tuotteita alhaalta "create item" - osiossa. Käyttäjä antaa itemin nimen (String) ja määrän (int). Mikäli käyttäjä antaa vääränlaisen syötteen (liian lyhyt nimi (<3), nimessä kiellettyjä mekkejä (esim ";"), int ei ole int, tms.), tulostuu käyttäjälle viesti: "item input illegal"
- Käyttäjä voi lisätä ja nimetä uuden jääkaapin antamalla sen nimen (String), ja painamalla nappia "add new fridge". Ohjelma lisää jääkaapin ja näyttää sen listauksessa vasemmalla (fridges). Mikäli käyttäjä antaa virheellisen syötteen (liian lyhyt nimi, jääkaapilla sama nimi kuin jo olevalla jääkaapilla, etc). Tulostuu käyttäjälle viesti: "fridge name illegal (%#;&)"
- Näkyvillä olevassa jääkaapissa voi tuotteen määrää paivittää suoraan "set amount"-napilla lisäämällä uuden määrän tekstikenttään. Määrä päivitetään käyttäjän toiveiden mukaan. Mikäli syöte on vääränlainen (ei int), tulostuu "amount change message", eli esimerkiksi "item input illegal (%;&)".
- Käyttäjä voi kirjautua ulos jääkaapista painamalla "logout"-nappia. Ohjelma palaa "login"-nakymään. Koko ohjelman voi sitten sulkea oikean yläkulman "x"-napista.

<img src = "https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/UserInterface_06052020.jpg" width=900 >

## Jatkokehitysideoita
Koska uudemmat jääkaapit voidaan rakentaa niin sanotuiksi monitoimilaitteiksi, eli niihin voidaan integroida esimerkiksi kameroita, äänitunnistusta, lukitusjärjestelmiä, ruuan pilaantumista tunnistavia järjestelmiä, parasta ennen -päiväyksen tunnistavia järjestelmiä, viivakoodin lukujärjestelmia yms., jää sovellukselle paljon jatkokehityspotentiaalia. Nämä upotetut järjestelmät vaativat tietenkin omat ohjaus- ja hallintasovelluksensa (embedded software), sekä integroidut sensorit (lämpömittari, kamera, hahmontunnistus, yms). JaakaappiTietokantaApp-sovelluksen on taivuttava näihin myöhempiin lisäyksiin sitten, kun niiden aika on, ei vielä tässä projektissa.

