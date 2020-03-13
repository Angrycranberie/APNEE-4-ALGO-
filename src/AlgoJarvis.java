import Dessin.*;

import javax.print.attribute.standard.OrientationRequested;
import java.awt.Color;
import java.util.*;


public class AlgoJarvis {

    //Fonction qui nous donne le sens de parcours : sens horaire ou anti-horaire
    static int sens(Point pcourant, Point pj, Point psuiv){
        int s = (psuiv.y - pcourant.y) * (pj.x - psuiv.x) - (psuiv.x - pcourant.x) * (pj.y - psuiv.y);
        if(s == 0) return 0;
        return (s > 0)? 1:2;
    }

    public static void main(Fenetre f, String [] args) {
        Random r = new Random();
        List<Point> l = new LinkedList<Point>();
        List<Point> ec = new LinkedList<Point>();
        int nbPoints;
        // Reccuperation du nombre de points en argument (ou valeur par defaut)
        if (args.length > 0) {
            nbPoints = Integer.parseInt(args[0]);
        } else {
            nbPoints = 5;
        }

        // Generation du nuage avec une petite marge pour ne pas avoir de
        // points contre le bord de la fenetre
        for (int i=0; i<nbPoints; i++) {
            int x = r.nextInt(f.largeur()-20)+10;
            int y = r.nextInt(f.hauteur()-20)+10;
            Point p = new Point(x, y);
            l.add(p);
            f.tracerSansDelai(p);
        }

        //Test avec un nuage de point carré
        /*for(int i = 1; i<6;i++ ){
            for(int j = 1; j<6;j++){
                Point p = new Point(i*50,j*50);
                l.add(p);
                f.tracerSansDelai(p);
            }
        }*/


        //Algo Jarvis
        Point pini = l.get(0), pcourant,psuiv;

        //On récupère le point le plus à gauche
        for(Point p : l){
            if(pini.x > p.x){
                pini = p;
            }
        }
        //Initialisation
        pcourant = pini;

        do{
            //On ajoute le point courant à l'enveloppe convexe
            ec.add(pcourant);

            //Calcul du point suivant par rapport au point courant de tel sort que sens(pcourant,pj,psuiv) soit
            // anti-horaire. On cherche le point avec l'angle le plus grand (dans le sens anti-horaire)
            psuiv = l.get((l.indexOf(pcourant)+1)%l.size());

            for(Point p : l){

                //On affiche le segment que l'on test
                f.tracer(new Segment(pcourant.x, pcourant.y,p.x,p.y,Color.red));

                //Si le point p donne un meilleur résultat, on met à jour le point suicant
                if(sens(pcourant, p, psuiv)==2){
                    psuiv = p;
                }

                f.effacer(new Segment(pcourant.x, pcourant.y,p.x,p.y));
            }
            //On efface le segment testé
            f.tracer(new Segment(pcourant.x, pcourant.y, psuiv.x,psuiv.y));

            //On met à jour le point courant
            pcourant = psuiv;

            //Tant qu'on n'est pas revenu au début
        }while (pcourant != pini);

    }
}
