import Dessin.*;
import java.awt.Color;
import java.util.*;


public class AlgoJarvis {
    static final double PRECISION=1E-6;

    public static void main(Fenetre f, String [] args) {
        Random r = new Random();
        List<Point> l = new LinkedList<Point>();
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





    }
}
