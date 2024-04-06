import java.util.ArrayList;

public class MovingAverage {
  private int windowSize;
  private double sum;
  private static int min;
  private static int max;
  private static int count;
  private static boolean used = false;
  private ArrayList<Integer> values;

  public MovingAverage() {
    this.windowSize = 0;
    this.sum = 0.0;
    this.values = new ArrayList<Integer>();
  }

  public MovingAverage(int N) {
    if (N <= 0 || N >= 1000)
      throw new IllegalArgumentException("Window size must be a positive integer less than 1000");
    this.windowSize = N;
    this.values = new ArrayList<Integer>();
  }

  public static int min() {
    return min;
  }

  public static int max() {
    return max;
  }

  public void add(int x) {
    if (!used) {
      min = x;
      max = x;
      used = true;
    } else {
      min = Math.min(x, min);
      max = Math.max(x, max);
    }
    if (x <= 0 || x >= 1000)
      throw new IllegalArgumentException("X must be a positive integer less than 1000");
    else {
      sum += x;
      count++;
      values.add(x);
    }
  }

  public double avg() {
    if (count == 0)
      return 0.0;
    if (windowSize == 0 || count < windowSize) {
      return (double) sum / count;
    } else {
      double windowSum = 0.0;
      int startIndex = Math.max(0, count - windowSize);
      for (int i = startIndex; i < count; i++) {
        windowSum += values.get(i);
      }
      return windowSum / windowSize;
    }
  }

  // public double avg() {
  // if (count == 0)
  // return 0.0;
  // if (windowSize == 0 || count < windowSize) {
  // return (double) sum / count;
  // } else {
  // double windowSum = 0.0;
  // for (int i = count - windowSize; i < count; i++) {
  // windowSum += values.get(i);
  // }
  // return windowSum / windowSize;
  // }
  // }

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
