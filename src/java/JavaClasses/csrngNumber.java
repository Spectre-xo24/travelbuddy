package JavaClasses;

/**
 *
 * @author Darkness
 */
public class csrngNumber{
   
    private String status;
    private int min;
    private int max;
    private int random;

    public csrngNumber(String status, int min, int max, int random) { 
        this.status = status;
        this.min = min;
        this.max = max;
        this.random = random;
    }
    
    public String getStatus() {
        return status;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public int getRandom() {
        return random;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public void setRandom(int random) {
        this.random = random;
    }
}