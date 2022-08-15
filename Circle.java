public class Circle {  
  
   private double radius;
   private String color;
   
   public double getRadius() {
    return radius; 
  }

  public double getArea() {
    return radius*radius*Math.PI;
 }
   public Circle() {  
      radius = 1.0;
      color = "red";
   }
   
   
   public Circle(double e) {  
      radius = e;
      color = "red";
   }
}