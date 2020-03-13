import Dessin.*;
import java.awt.Color;
import java.util.*;


public class AlgoJarvis {
    static final double PRECISION=1E-6;

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


        //Algo Jarvis
        Point pini = l.get(0), pcourant, pprec, psuiv = null;
        for(Point p : l){
            if(pini.x > p.x){
                pini = p;
            }
        }
        ec.add(pini);
        pcourant = pini;
        pprec = new Point(pini.x -1, pini.y);

        do{
            int x1, x2, y1, y2;
            double Aire = 0;
            for(Point p : l){

                if(p != pprec && p != pcourant){
                    x1 = pcourant.x - pprec.x;
                    y1 = pcourant.y - pprec.y;
                    x2 = p.x - pcourant.x;
                    y2 = p.y - pcourant.y;
                    if(Aire < x1*y2-x2*y1){
                        Aire = x1*y2-x2*y1;
                        psuiv = p;
                    }
                }
            }
            pprec = pcourant;
            pcourant = psuiv;
            ec.add(pcourant);


        }while (pcourant != pini);





    }
}
