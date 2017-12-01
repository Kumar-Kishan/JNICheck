import java.util.Random;
public class Hello{
    
    public native Location[] getRandomLocations(int number);

    public static double getRandomDouble(double rangeMin, double rangeMax)
    {
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();

    }

    public Location[] getNonNativeRandomLocations(int number)
    {
        Location[] locations = new Location[number];
        for(int i=0;i< number;i++)
        {
            locations[i] = new Location(getRandomDouble(0,100), getRandomDouble(100,200));
        }
        return locations;

    }

    public static void main(String[] args)
    {
        System.loadLibrary("Hello");
        Hello hello = new Hello();


        for(int i = 1; i< 10000000; i += 1000){
            long startTime = System.nanoTime();
            Location[] nativeLocations = hello.getRandomLocations(i);
            long midTime = System.nanoTime();
            Location[] locations = hello.getNonNativeRandomLocations(i);
            long endTime = System.nanoTime();
            //for(Location location: locations)
            //    System.out.println("Random Location: " + location.getLatitude() + " : " +  location.getLongitude());
            
            long nativeTime = midTime - startTime;

            long nonNativeTime = endTime - midTime;
            System.out.println("Native Call: \t" + (midTime - startTime));
            System.out.println("Non Native Call: \t" + (endTime - midTime));
            System.out.println("Ration: \t" + ((double) nativeTime /(double) nonNativeTime));
            break;
        }

    }
}