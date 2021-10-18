package game;

/*
Enumération des actions possibles d'un fantôme
*/
public enum GhostAction {
    FORWARD,     // avance d'un carreau
    TURN_LEFT,   // tourne à gauche
    TURN_RIGHT,  // tourne à droite
    REINIT       // replace le fantôme à sa position initiale,
                 // utile pour les séquences de déplacement ne formant pas une boucle,
                 // dans ce cas, mettre cette action REINIT en fin de telles séquences
};
