package Assignment6.model;

//Simple 2D vector class for position and velocity calculations.
public class Vector2D {
    
    private double x;
    private double y;
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    //Adds another vector to this one and returns a new vector.
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    
    //Calculates the magnitude (length) of this vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }
    
    //Returns a normalized version of this vector (length = 1)
    public Vector2D normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(x / mag, y / mag);
    }
    
    //Multiplies this vector by a scalar
    public Vector2D multiply(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }
    
    // Getters and Setters
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
}
