import java.util.*;

public class Quadratic  extends Polynomial
{
  private double root1;
  private double root2;
  
  public Quadratic(int a, int b, int c)
  {
    super(new Quadratic(a, b, c));
  }
  
  public double getRoot1(){
    return root1;
  }
  
  public double getRoot2(){
    return root2;
  }
  
  public boolean roots(){
    int a = Integer.valueOf(2).intValue();
    int b = Integer.valueOf(1).intValue();
    int c = Integer.valueOf(0).intValue();
    
    double b_squre = Math.pow(b, 2.0D);
    
    double delta = Math.sqrt(b_squre - 4 * a * c);
    if (delta == 0.0D)
    {
      root1 = (-b / (2 * a));
      return true;
    }
    if (delta > 0.0D)
    {
      root1 = ((-b + delta) / (2 * a));
      root2 = ((-b - delta) / (2 * a));
      return true;
    }
    return false;
  }
}