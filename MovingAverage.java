import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
  
public class MovingAverage{
  private static int min = 0;
  private static int max = 0;

  private LinkedList<Integer> values;
  private int windowSize;
  private double sum;
  
  public MovingAverage()
  {
    this.values = new LinkedList<>();
    this.windowSize = 0;
    this.sum = 0.0;
  }

  public MovingAverage(int N)
  {
    if(N <= 0 || N >= 1000)
        throw new IllegalArgumentException("Window size must be a positive integer less than 1000");
    this.values = new LinkedList<>();
    this.windowSize = N;
    this.sum = 0.0;
  }

  public static int min(){
    return min;
  }

  public static int max(){
    return max;
  }

  public void add(int x)
  {
    if(values.isEmpty())
    {
      min = x;
      max = x;
    }
    else{
      min= Math.min(min, x);
      max = Math.max(max, x);
    }
      if (windowSize > 0 && values.size() >= windowSize)
              sum -= values.removeFirst();

          values.addLast(x);
          sum += x;
      }

  public double avg() {
      if (values.isEmpty())
          return 0.0;

      return sum / values.size();
  }
  
  public static void main(String[] args) {
    MovingAverage cma = new MovingAverage();
    MovingAverage sma10 = new MovingAverage(10);
    MovingAverage sma1 = new MovingAverage(1);

    for (int i = 1; i <= 100; i++) {
      cma.add(i);
      sma10.add(i);
      sma1.add(i);
    }

    System.out.println(cma.avg());
    System.out.println(sma10.avg());
    System.out.println(sma1.avg());
    System.out.println(MovingAverage.min());
    System.out.println(MovingAverage.max());

    sma1.add(500);
    System.out.println(sma1.avg());
    System.out.println(MovingAverage.max());
  }

}