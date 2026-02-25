package ru.gr0946x;

import java.util.HashMap;
import java.util.Map;

public class Lagrange extends Polynomial{

    private Map<Double, Double> points;
    public Lagrange(Map<Double, Double> points){
        this.points = new HashMap<>(points);
        createLagrange();
    }

    private void createLagrange() {
        var ln = new Polynomial();
        for (var p :points.entrySet()){
           ln= ln.plus( createFundamental(p.getKey()).times(p.getValue()));
        }
        coeffs.clear();
        coeffs.putAll(ln.coeffs);
    }
    private Polynomial createFundamental(double xk){
        var lk = new Polynomial(1.0);
        for (var xj: points.keySet()){
            if (xj ==xk) continue;
            var p1 = new Polynomial(-xj, 1.0).div(xk-xj);
            lk=lk.times(p1);
        }
        return lk;
    }
}
