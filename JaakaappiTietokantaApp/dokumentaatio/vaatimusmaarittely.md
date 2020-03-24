# JaakaappiTietokantaApp Vaatimusmäärittely

## Sovelluksen tarkoitus
Jääkaapin sisällön ajantasaisuudesta huolehtiva sovellus on todellinen klassikko jo tietojenkäsittelyn ja tietokoneiden historian varhaisilta ajoilta asti. Kaupallisia taikka toimivia sovelluksia on kuitenkin näkynyt vähän. Nyt, kun esineiden internet (IoT) on tullut osaksi arkipäiväisiä käyttötapauksia, on tullut aika saattaa jääkaappisovellus tuotantoon ja laajaan levitykseen. Tämä projekti tarjoaa tuotekehityksen ja varhaisen toteutuksen jääkaappisovelluksen laajemmalle kaupalliselle tai ei-kaupalliselle tuotannolle. Esineiden internet jää tämän sovelluksen ulkopuolelle, joten itsestään päivittyvää sovellusta ei tässä projektissa vielä tehdä, mutta suunnittelussa pidetään mielessä tämän laajennuksen mahdollinen myöhempi lisääminen.

## Käyttäjät
Koska yhdellä jääkaapilla on yleensä varsin rajallinen määrä käyttäjia, on sovelluksella alkuvaiheessa vain yksi käyttäjärooli, eli normaali käyttäjä. Koska jääkaappien käyttäjillä kuitenkin saattaa olla tarvetta erilaisille rooleille tai vastuille, saatetaan sovellukselle myöhemmin lisätä pääkäyttäjä-rooli tai esimerkiksi eräänlainen katsoja-rooli, joka mahdollistaisi jääkaapin sisällön tarkastelun, mutta ei muutosten tekemistä sisältöön.

## Käyttöliittymäluonnos
Käyttäjälle on tarjolla oletusjääkaappi, jonka voi ottaa käyttöön ja kustomoida omanlaisekseen. 
<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/Jaakaappi_UI.jpg" width="750">

## Perusversion tarjoama toiminnallisuus
Jääkaapissa on tuotteita. Tuotteilla on ominaisuuksia, kuten tuoteryhmä tai määrä. Käyttäjä pystyy sovelluksesta käsin katsomaan kaapin sisällön. Käyttäjä pystyy päivittämään tuotteet ja tuotteiden tiedot. Tiedot tallentuvat tietokantaan.

## Jatkokehitysideoita
Koska uudemmat jääkaapit voidaan rakentaa niin sanotuiksi monitoimilaitteiksi, eli niihin voidaan integroida esimerkiksi kameroita, äänitunnistusta, lukitusjärjestelmiä, ruuan pilaantumista tunnistavia järjestelmiä, parasta ennen -päiväyksen tunnistavia järjestelmiä, viivakoodin lukujärjestelmia yms., jää sovellukselle paljon jatkokehityspotentiaalia. Nämä upotetut järjestelmät vaativat tietenkin omat ohjaus- ja hallintasovelluksensa (embedded software). JaakaappiTietokantaApp-sovelluksen on taivuttava näihin myöhempiin lisäyksiin sitten, kun niiden aika on, ei vielä tässä projektissa.

