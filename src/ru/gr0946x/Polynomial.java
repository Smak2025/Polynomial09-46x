package ru.gr0946x;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
public class Polynomial {
    private final Map<Integer, Double> coeffs;

    public Map<Integer, Double> getCoeffs(){
        return new TreeMap<>(coeffs);
    }


    public Polynomial(){
        coeffs = new TreeMap<>(
            (o1, o2) -> o2.compareTo(o1)
        );
        coeffs.put(0, 0.0);
    }

    public Polynomial(Map<Integer, Double> coeffs){
        this();
        this.coeffs.clear();
        this.coeffs.putAll(coeffs);
        filterCoeffs();
    }

    public Polynomial(double... coeffs){
        this();
        for(int i = 0; i < coeffs.length; i++){
            this.coeffs.put(i, coeffs[i]);
        }
        filterCoeffs();
    }

    private void filterCoeffs(){
        coeffs.entrySet().removeIf(
                entry ->
                        entry.getValue() == 0.0
                                ||entry.getKey() < 0
        );
        if (coeffs.isEmpty()) coeffs.put(0, 0.0);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (var entry : coeffs.entrySet()){
            if (entry.getValue() >= 0) {
                if (i != 0) sb.append("+");
            } else {
                sb.append("-");
            }
            if (Math.abs(entry.getValue()) != 1.0
                || entry.getKey() == 0
            ) {
                sb.append(Math.abs(entry.getValue()));
            }
            if (entry.getKey() != 0) sb.append("x");
            if (entry.getKey() > 1) {
                sb.append("^").append(entry.getKey());
            }
            i++;
        }
        return sb.toString();
    }

    public Polynomial plus(Polynomial other){
        var newCoeffs = new TreeMap<Integer, Double>(coeffs);
        for (var p:other.coeffs.entrySet()) {
            newCoeffs.put(p.getKey(), newCoeffs.getOrDefault(p.getKey(), 0.0)+ p.getValue());

        }
        return new Polynomial(newCoeffs);
    }

    public  void plusAssign(Polynomial other){
        for (var p: other.coeffs.entrySet()) {
            this.coeffs.put(p.getKey(), coeffs.getOrDefault(p.getKey(), 0.0) + p.getValue());
        }
    }

    public Polynomial minus(Polynomial other){
        var newCoeffs = new TreeMap<Integer, Double>(coeffs);
        for (var p:other.coeffs.entrySet()) {
            newCoeffs.put(p.getKey(), newCoeffs.getOrDefault(p.getKey(), 0.0) - p.getValue());
        }
        return new Polynomial(newCoeffs);
    }

    public Polynomial times(double v){
        var newCoeffs = new TreeMap<Integer, Double>();
        for (var p: coeffs.entrySet()) {
            newCoeffs.put(p.getKey(), p.getValue() * v);
        }
        return new Polynomial(newCoeffs);
    }

    public double invoke(double x){
        return 0.0;
    }
}
