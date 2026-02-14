package ru.gr0946x;

import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private final Map<Integer, Double> coeffs;

    public Polynomial(){
        coeffs = new TreeMap<>(
            (k1, k2) -> -k1.compareTo(k2)
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
                        entry.getValue() == 0
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
}
