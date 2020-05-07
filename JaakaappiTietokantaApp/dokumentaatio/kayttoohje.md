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
You need to have a username to use the fridgeapp. This can be done in the create a new user -scene. In case you notice that you already have username, you may just press the "back"-button, and go back to login scene.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserScene2_07052020.jpg" width="400">

If you try to use illegal characters or too short username or fridge name, an error message is given:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserSceneError_07052020.jpg" width="400">

In case, you try to create a user that already exists, an error message is given as well:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/newUserSceneUniqueUserName_07052020.jpg" width="400">



## Fridge scene
You may manage your fridge in the fridge scene. You can add and remove items and manage the amount of items in your fridge. 
You can have many fridges but first your default fridge is logged in:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene1_2804.jpg" width="600">

If you need to add something to your fridge, use the input text fields on bottom of the scene:

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene2_2804.jpg" width="600">
 
You may also need to update the amount of your item time to time. You may use the "set amount" -button, or even the create button with change amount.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene3_2804.jpg" width="600">

if you have many fridges to manage, you may add them to the app by using the "add fridge" -button. Put the name of the new fridge to the text field to the left from the "add fridge" -button. They are all visible on the right.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene4_2804.jpg" width="600">

You can have as many fridges as you need. They are all visible on the right.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene5_2804.jpg" width="600">

You can add all your items to the fridge app.

<img src="https://github.com/terodotus/ot-harjoitustyo/blob/master/JaakaappiTietokantaApp/dokumentaatio/Kuvat/FridgeScene7_2804.jpg" width="600">

When you want to close the fridge app, press the "logout" -button in the fridge scene. You will end up to the login scene where you can close the fridge app by pressing the "x" in the upper right corner of the scene. 
