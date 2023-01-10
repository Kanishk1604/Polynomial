public class Comparable extends Polynomial{
    @Override
    public int compareTo(Polynomial q){
        if (this.coefficient(0) > q.coefficient(0)){
            return 1;
        }
        else if (this.coefficient(0) < q.coefficient(0)){
            return -1;
        }
        return 0;

    }
}