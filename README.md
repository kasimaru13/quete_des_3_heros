# A propos du jeu

## Comment lancer le jeu

Pour lancer le jeu, il suffit de télécharger le fichier quete_des3_heros.jar et d'exécuter la commande ```java -jar ./quete_des_3_heros.jar```.\
Sous windows, on peut également simplement double cliquer sur le fichier .jar pour lancer le jeu

## Comment jouer

Le jeu commence sur l'écran titre. Pour commencer le jeu, cliquez sur le bouton ```Commencer```.

Ensuite, une page de dialogue s'affiche. Celle-ci donne le contexte dans lequel se passe le jeu. C'est sur ce genre de fenêtre que l'histoire du jeu se trouve. Une fois ce texte lu, cliquez sur celui-ci pour passer à la phase de combat.

Dans cette phase, votre objectif est de battre tous vos ennemis (les monstres) pour passer à la phase suivante (un autre dialogue, ou l'écran de fin selon votre avancement dans le jeu).

Cette phase se déroule au tour par tour. L'ordre des tours des personnages se trouve sur la gauche de l'écran. Lorsque c'est au tour de l'un de vos personnages, vous pouvez effectuer 2 types d'actions, dans l'ordre suivant:

1. Déplacer votre personnage. Cette action est optionnelle, vous pouvez ne pas déplacer votre personnage si vous le souhaitez. Après avoir fait un déplacement, vous pouvez l'annuler avec le bouton en bas à droite de l'écran. Faites attention, car cette action est à effectuer en premier, vous ne pourrez plus vous déplacer si vous avez effectué l'une des actions suivantes

2. Attaquer/Défendre/Utiliser une technique/Utiliser un objet. Vous ne pouvez effectuer qu'une seule de ces actions par tour. En effet, effectuer l'une de ces actions passe votre tour (même si vous ne vous êtes pas déplacé). Détaillons chacune de ces actions:

    - Attaquer : Cette action permet d'attaquer un adversaire. Lorsque vous appuyez sur ce bouton, la portée d'attaque de votre personnage est affichée en rouge. Si vous cliquez sur une case vide ou à nouveau sur le bouton Attaque, cette action sera annulée.

    - Défendre : Cette action augmentera votre défense, vous permettant de vous protéger d'une éventuelle attaque ennemie.

    - Utiliser une technique : Similaire à l'attaque. Choisissez une technqiue dans le menu de droite et utilisez la pour attaquer (ou pour soigner selon la technique). Faites attention cependant, ces techniques utilisent des PM (Points de Magie). Si vous n'en avez pas assez, vous ne pourrez plus utiliser de technique !

    - Utiliser un objet : Cette action affichera dans le menu de droite votre inventaire. Il est commun à tous vos personnages. Utilisez des potions pour leurs effets, ou équipez-vous de meilleures armes ou armures !

\
Une fois tous vos ennemis abattus, vous passerez à la phase suivante.


# Organisation du code source du jeu :

## Diagramme de classe :

![Diagramme de classe](./Diagramme%20de%20classe%20projet%20Java.png)

## Organisation des packages :

Le code source du jeu est présent dans ```./src/main/java/com/quete_des_3_heros/```

- controller : Contient le contrôleur de combat, qui gère la partie back-end de la phase de combat. On y trouve aussi la classe gérant le système de path finding (avec l'algorithme A*) du jeu.

- element : Contient les classes définissant les Elements (ce que nous plaçons dans la grille de jeu pour gérer les intéractions) qui sont : les héros (personnages du joueur), les monstres (les ennemis) et les obstacles. Toute la logique de ces element est présente dans ce package.

- inventory : Contient la logique de l'inventaire et des objets.

- view : Contient l'interface graphique du jeu. Chaque sous package correspond à une phase du jeu (à l'exeption de components, qui correspond à des éléments graphiques repris plusieurs fois sur les différents écrans).


# Source des Images :
## Fonds d'écrans :
Fond de plaine verte: https://opengameart.org/content/grass-8
Fond de l’écran titre (château): https://urlich.art/projects/zOWqld 

## Sprites :

### Joueurs :

Sprite de guerrier: https://opengameart.org/content/animated-warrior \
Sprite de mage : https://br.pinterest.com/pin/438678819937102971/ \
Sprite de voleur : https://opengameart.org/content/wind-rogue

### Monstres :

Sprite de dragon : https://www.pinterest.fr/pin/671951206889656837/ \
Sprite de squelette : https://opengameart.org/content/lpc-skeleton \
Sprite de gobelin : https://opengameart.org/content/lpc-goblin

### Obstacles :

Arbre : https://opengameart.org/content/lpc-tree-recolors \
Rochers : https://opengameart.org/content/lpc-ore-and-forge

### Objets :

Potions : https://opengameart.org/content/16x16px-pixelart-potions