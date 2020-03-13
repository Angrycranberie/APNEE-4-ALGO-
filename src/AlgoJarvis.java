import Dessin.*;

import javax.print.attribute.standard.OrientationRequested;
import java.awt.Color;
import java.util.*;


public class AlgoJarvis {
    static int Orientation(Point p, Point q, Point pj){
        int val = (q.y - p.y) * (pj.x - q.x) - (q.x - p.x) * (pj.y - q.y);
        if(val == 0) return 0;
        return (val > 0)? 1:2;
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


        //Algo Jarvis
        Point pini = l.get(0), pcourant, pprec,q;
        for(Point p : l){
            if(pini.x > p.x){
                pini = p;
            }
        }
        pcourant = pini;
       // pprec = new Point(pini.x -1, pini.y);
        // f.tracer(new Segment(pcourant.x, pcourant.y,pprec.x, pprec.y));
        do{
            //int x1, x2, y1, y2;
            //double angle = -2;
            ec.add(pcourant);

            q = l.get((l.indexOf(pcourant)+1)%l.size());
            for(Point p : l){

                /*if(p != pprec && p != pcourant && !ec.contains(p) ){
                    f.tracer(new Segment(pcourant.x, pcourant.y,p.x,p.y,Color.red));
                    x1 = pcourant.x - pprec.x;
                    y1 = pcourant.y - pprec.y;
                    x2 = p.x - pcourant.x;
                    y2 = p.y - pcourant.y;
                    double angletemp = Math.acos((x1*y1 + x2*y2)/(Math.sqrt(x1*x1 + y1*y1)*Math.sqrt(x2*x2 + y2*y2)));

                    if(angle < angletemp){
                        angle = angletemp;
                        psuiv = p;
                    }
                }*/
                f.tracer(new Segment(pcourant.x, pcourant.y,p.x,p.y,Color.red));

                if(Orientation(pcourant, q, p)==2){
                    q = p;
                }

                f.effacer(new Segment(pcourant.x, pcourant.y,p.x,p.y));
            }
            f.tracer(new Segment(pcourant.x, pcourant.y, q.x,q.y));
            pcourant = q;


        }while (pcourant != pini);





    }
}
