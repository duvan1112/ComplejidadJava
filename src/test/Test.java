package test;


import model.EnvironmentManager;
import model.Location;
import model.Sphere;

public class Test {
    public static void main(String[] args) {
        EnvironmentManager manager= new EnvironmentManager(5);
        manager.add(new Sphere(2,new Location(0,0,0),2));
        manager.add(new Sphere(2,new Location(10,10,10),5));
        manager.add(new Sphere(2,new Location(-200,-200,-200),6));
        manager.add(new Sphere(2,new Location(200,200,200),5));
        manager.add(new Sphere(2,new Location(10,50,150),6));

        System.out.println("Mayor Distancia: "+manager.calculateDistance()); //Resultado esperado: 688.820

        System.out.println("Mas repetido: "+manager.mostRepeat()); // Resultado esperado: 342.41

        for (int i = 0; i < manager.sortDistances().size(); i++) {
            System.out.println(manager.sortDistances().get(i));
        }

        System.out.println("Promedio en intervalo de tiempo :"+manager.calculateAverage(30));
        for (int i = 0; i < manager.getSpheres().length; i++) {
            System.out.println(""+manager.getSpheres()[i]);
        }
    }
}
