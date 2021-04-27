import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {

    private static final int UPPER_LIMIT = 100;
    
    public static final Random GEN = new Random();
    
    public static final IntSupplier RGB = () -> GEN.nextInt(256);
    
    public static void main(final String[] theArgs) {
        
        OptionalDouble sum = IntStream.range(0, 101)
                        .parallel()
                        .filter(i -> i % 2 != 0)
                        .map(x -> x * x)
                        .average();
        
     
        

      List<String> names = new ArrayList<>();
      
      names.add("Carl");
      names.add("Charles");
      names.add("arl");
      names.add("harles");
      names.add("rl");
      names.add("les");
      names.add("Farl");
      names.add("Barles");
      names.add("Dickon Tarley");
      names.add("Farles");
      
      Stream<String> nameStream = names.stream();
      
      
      OptionalDouble result = nameStream
                      .filter(s -> s.endsWith("arles"))
                      .mapToInt(String::length)
                      .average();
      
      nameStream.count();
      
      List<String> namesGreaterThan2 = names.stream()
          .filter(s -> s.length() > 2)
          .collect(Collectors.toList());
        
      System.out.println(result.orElse(0.0));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        System.out.println(averageLength("abc", "a", "abcdefg", "length", "1234567890"));
        System.out.println(averageLength("abc"));
        System.out.println(averageLength());
        String[] words = { "Charles", "Bryan", "UWT", "School of Engineering and Technology" };
        System.out.println(averageLength(words));

                
//        IntSummaryStatistics iss = 
//                        IntStream.range(0, 101).filter((x) -> x % 3 == 0).map((x) -> x * x).
//                        boxed().collect(Collectors.summarizingInt((x) -> x));
//        
//        System.out.println(iss.getCount());
//        System.out.println(iss.getAverage());
//        System.out.println(iss.getMax());
//        System.out.println(iss.getMin());
//        System.out.println(iss.getSum());
        
        
        
        
        
        
        
        

//        List<String> names = new ArrayList<>();
//        
//        names.add("Carl");
//        names.add("Charles");
//        names.add("arl");
//        names.add("harles");
//        names.add("rl");
//        names.add("les");
//        names.add("Farl");
//        names.add("Barles");
//        names.add("Dickon Tarley");
//        names.add("Farles");
//        
//        Stream<String> namesStream = names.stream();
//        
//        long count = namesStream.
//                        filter((x) -> x.toLowerCase().charAt(0) == 'c') 
//                        .count();
//        
//        namesStream.filter((x) -> x.toLowerCase().charAt(0) == 'c') ;
//        
//        System.out.println(count);
//        
//        int n = 1000;
////        IntStream is = IntStream.range(0, n);
//        int sum = IntStream.range(0, n)
//                        .filter((x) -> x % 2 == 0)
//                        .map(x -> x * x)
//                        .sum();
//                        
//        System.out.println(sum);
//        
////        
////         int sum = IntStream.range(1, 101)
////             .filter( x -> x % 2 == 0)
////             .map(x -> x * x)
////             .sum();
////         
////         OptionalDouble avg = IntStream.range(1, 101)
////         .filter( x -> x % 2 == 0)
////         .filter( x -> x % 2 == 1)
////         .map(x -> x * x)
////         .average();
////         
//         IntStream iStream = IntStream.of(39, 21, 60, 22, 22, 24, 15, 39);
////         
////         OptionalInt max = iStream.max();
////         iStream = IntStream.of(39, 21, 60, 22, 22, 24, 15, 39);
////         OptionalInt min = iStream.min();
////         
////         if (avg.isPresent()) {
////             System.out.println("The aveage is: " + avg);
////         } else {
////             System.out.println("EMPTY");
////         }
////         
////         System.out.println(sum);
////         
//        Stream<Color> cS = Stream.of(Color.BLACK, Color.BLUE, Color.CYAN, Color.RED, Color.GRAY);
//         
//        OptionalDouble avg = cS.mapToInt(x -> x.getRed() ).average();
//        double result;
//        if (avg.isPresent()) {
//            System.out.println(avg);
//            result = avg.getAsDouble();
//        } else {
//            System.out.println("none");
//        }
//         
//        cS = Stream.of(Color.BLACK, Color.BLUE, Color.CYAN, Color.RED, Color.GRAY);
//         
//        DoubleSummaryStatistics dss = cS.collect(Collectors.summarizingDouble(Color::getRed));
//         
//        System.out.println(dss.getAverage());
//        System.out.println(dss.getCount());
//        System.out.println(dss.getMin());
//        System.out.println(dss.getMax());
//        System.out.println(dss.getSum());
//        System.out.println();
//         
//        cS = Stream.generate(StreamExamples::getRandomColor).limit(1000);
//         
//        Color[] colors = (Color[]) cS.toArray();
//        
//        Point[] points = {new Point(), new Point() };
//        Point[] deepCopy = (Point[]) Stream.of(points).map(p -> (Point) p.clone()).toArray();     
    }
    
    public static Color getRandomColor() {
        return new Color(RGB.getAsInt(), RGB.getAsInt(), RGB.getAsInt());
    }
    
    public static double averageOfRandomInts(final int count) {
        return new Random().ints(count, 1, 100).average().orElse(0);
    }
    
    /**
     * Computes and returns the average length of the Strings found in 
     * theWords.
     * 
     * @param theWords the Strings to compute the average length on
     * @return the average length of the String in theWords
     */
    public static double averageLength(final String ... theWords) {
        return Stream.of(theWords)
                        .mapToInt(String::length)
                        .average()
                        .orElse(0.0);
    }
    
}
