package model;

import java.util.*;

/**
 * @author duvan
 */
public class EnvironmentManager {

    private Sphere[] spheres;

    public EnvironmentManager(int numberSpheres) {
        spheres = new Sphere[numberSpheres];
    }

    public void add(Sphere sphere){
        for (int i = 0; i < spheres.length; i++) {
            if (spheres[i] == null) {
                spheres[i]=sphere;
                break;
            }
        }
    }

    public double calculateAverage(int time){
        recalculate(time);
        ArrayList<Double> doubles=calculateAllDistances();
        double sum = 0;
        for (int i = 0; i < doubles.size(); i++) {
            sum += doubles.get(i);
        }
        return sum/(doubles.size());
    }

    private void recalculate(int time){
        for (int i = 0; i < spheres.length; i++) {
            Sphere sphere=spheres[i];
            sphere.setLocation(new Location(sphere.getLocation().getX()+
                    (sphere.getSpeed()*time),sphere.getLocation().getY(),sphere.getLocation().getZ()));
        }
    }

    public double calculateDistance() {
        double max = calculateAllDistances().get(0);
        for (int i = 1; i < calculateAllDistances().size(); i++) {
            if (max<calculateAllDistances().get(i)){
                max=calculateAllDistances().get(i);
            }
        }
        return max;
    }

    private ArrayList<Double> calculateAllDistances() {
        ArrayList<Double> distances = new ArrayList<>();
        for (int i = 0; i < spheres.length; i++) {
            for (int j = i + 1; j < spheres.length; j++) {
                distances.add((Math.sqrt(Math.pow((spheres[i].getLocation().getX() - spheres[j].getLocation().getX()), 2)
                        + Math.pow((spheres[i].getLocation().getY() - spheres[j].getLocation().getY()), 2)
                        + Math.pow(spheres[i].getLocation().getZ() - spheres[j].getLocation().getZ(), 2)))
                        -spheres[i].getSize()-spheres[j].getSize());
            }
        }
        return distances;
    }

    public double mostRepeat() {
        ArrayList<Double> distances = calculateAllDistances();
        HashMap<Double,Integer> repeated= searchMoreRepeated(distances);
        double quantity= repeated.get(distances.get(0));
        double distance=distances.get(0);
        Iterator<Map.Entry<Double, Integer>> iterator = repeated.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Double, Integer> data = iterator.next();
            if (quantity<data.getValue()){
                quantity=data.getValue();
                distance= data.getKey();
            }
        }
        return distance;
    }

    private HashMap<Double,Integer> searchMoreRepeated(ArrayList<Double> distances) {
        HashMap<Double,Integer> repeated = new HashMap<>();
        int count=0;
        for (int i = 0; i < distances.size() ;i++) {
            for (Double distance : distances) {
                if (distances.get(i).doubleValue() == distance.doubleValue()) {
                    count++;
                }
            }
            repeated.put(distances.get(i),count);
            count=0;
        }
        return repeated;
    }

    public ArrayList<Double> sortDistances() {
        ArrayList<Double> arrayList = calculateAllDistances();
        Collections.sort(arrayList);
        return arrayList;
    }

    public Sphere[] getSpheres() {
        return spheres;
    }
}
