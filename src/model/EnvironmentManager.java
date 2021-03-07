package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author duvan
 */
public class EnvironmentManager {

    private Sphere[] sphere;

    public EnvironmentManager(int numberSpheres) {
        sphere = new Sphere[numberSpheres];
        Random random = new Random();
        for (int i = 0; i < numberSpheres; i++) {
            sphere[i]= new Sphere(random.nextInt(10)+1,
                    new Location(random.nextDouble()*100, random.nextDouble()*100,
                            random.nextDouble()*100), random.nextInt(3)+1);
        }
    }

    public double calculateDistance() {
        double max = calculateAllDistances().get(0);
        for (int i = 0; i < calculateAllDistances().size(); i++) {
            if (max<calculateAllDistances().get(i)){
                max=calculateAllDistances().get(i);
            }
        }
        return max;
    }

    private ArrayList<Double> calculateAllDistances() {
        ArrayList<Double> aux = new ArrayList<>();
        for (int i = 0; i < sphere.length; i++) {
            for (int j = i + 1; j < sphere.length; j++) {
                aux.add((Math.sqrt(Math.pow((sphere[i].getLocation().getX() - sphere[j].getLocation().getX()), 2)
                        + Math.pow((sphere[i].getLocation().getY() - sphere[j].getLocation().getY()), 2)
                        + Math.pow(sphere[i].getLocation().getZ() - sphere[j].getLocation().getZ(), 2)))
                        -sphere[i].getSize()-sphere[j].getSize());
            }
        }
        return aux;
    }

    public double mostRepeat() {
        ArrayList<Double> aux = calculateAllDistances();
        ArrayList<Integer> repeated= searchMoreRepeated(aux);
        double mostRepeat=repeated.get(0);
        for (Integer integer : repeated) {
            if (mostRepeat < integer) {
                mostRepeat = integer;
            }
        }
        return mostRepeat;
    }

    private ArrayList<Integer> searchMoreRepeated(ArrayList<Double> distances) {
        ArrayList<Integer> repeated = new ArrayList<>();
        int count=0;
        for (int i = 0; i < distances.size() ;i++) {
            for (Double distance : distances) {
                if (distances.get(i).doubleValue() == distance.doubleValue()) {
                    count++;
                }
            }
            repeated.add(count);
        }
        return repeated;
    }

    public ArrayList<Double> sortDistances() {
        Collections.sort(calculateAllDistances());
        return calculateAllDistances();
    }

}
