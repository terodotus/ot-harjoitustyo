# User guide

## Before and configuration
Download the jar file fridgeapp.jar.
In the same forder with the jar file, there should be a configuration file config.properties which defines the text files where all the users and all the fridge items will be stored. Content of the confic.properties file should look as follows:
````
userFile=users.txt
fridgeItemFile=fridgeitems.txt
````

## Starting the program
After having the fridgeapp.jar and the config.properties files, you may start the program by command:
````
java -jar fridgeapp.jar
````

## Log in
In log in scene, you may log in to the fridgeapp. That is possible after having a username. if you need a new username, please press the "create a new fridge user" -button.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/loginScene3Error.jpg" width="400">

After having a username, you may login with your username.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/loginScene2.jpg" width="400">


## Create a new user
You need to have a username to use the fridgeapp. This can be done in the create a new user -scene.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserScene2.jpg" width="400">

If you try to use illegal characters or too short username or fridge name, an error message is given:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserSceneError.jpg" width="400">

In case, you try to create a user that already exists, an error message is given as well:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserSceneUniqueUserName.jpg" width="400">



## Fridge scene
You may manage your fridge in the fridge scene. You can add and remove items and maange the amount of items in your fridge. 
You can have many fridges but first your default fridge is logged in:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene1.jpg" width="400">

If you need to add something to your fridge, use the input text fields on bottom of the scene:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene2.jpg width="400">
                                                                                                                                          


