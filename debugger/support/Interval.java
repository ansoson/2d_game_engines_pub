package debugger.support;

public class Interval {

    public Double min;
    public Double max;

    public Interval(){
        min = null;
        max = null;
    }

    public void consider(double projection){
        if (min == null) {
            min = projection;
        }
        if (max == null) {
            max = projection;
        }
        if (projection < min) {
            min = projection;
        }
        if (projection > max) {
            max = projection;
        }
    }

    public Double returnMTV(Interval b){
        Double aRight = b.max - this.min;
        Double aLeft = this.max - b.min;
        if (aLeft < 0 || aRight < 0){
            return null;
        }
        if (aRight <= aLeft) {
            return aRight;
        } else {
            return -aLeft;
        }
    }

}
