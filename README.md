# Quizz Cinéma
Projet de Web&amp;Cloud - Quizz Cinéma avec questions générées via DBpedia
<img src="https://raw.githubusercontent.com/BerliozLeChat/movies-game-project/master/src/main/webapp/Film-icon.png" alt="Logo" style="width: 128px; height:128px;"/>
[Movies Game](https://moviesgameoff.appspot.com/ "le site Movies Game")
[![Build Status](https://travis-ci.org/BerliozLeChat/movies-game-project.svg?branch=master)](https://travis-ci.org/BerliozLeChat/movies-game-project)

##Prérequis d'installation

Les outils suivants sont nécessaires pour installer movies game:
* [git](https://git-scm.com/) en version 1.9 ou supérieure
* [Maven](https://maven.apache.org/) en version 3.1 ou supérieure

Il est conseillé d'avoir une bonne connaissance de la plateforme [Google App Engine](https://cloud.google.com/appengine/docs).

##Installation

Clonez le projet avec la commande suivante :
```bash
git clone https://github.com/BerliozLeChat/movies-game-project.git
```
Placez vous dans le dossier movies-game-project, ouvrez le fichier *pom.xml* et remplacez la valeur *app.id* par votre id de projet.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
...

    <properties>
        <app.id>my-project</app.id>
        ...
    </properties>
...
</project>
```

Exécutez ensuite la commande suivante pour récupérer les dépendances nécessaires et compiler le projet
```bash
mvn clean install
```

Enfin déployez le  projet à l'aide de la commande
```bash
mvn appengine-update
```
Suivez les consignes pour vous authentifer avec votre compte Google et votre application sera déployé.
