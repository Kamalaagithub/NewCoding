
import java.util.StringJoiner;
public class JoinerExample {
 
    public static void main(String[] args)
    {

       //StringJoiner sj2= new StringJoiner(",");
       StringJoiner sj1 = new StringJoiner(",");
       //StringJoiner sj2= new StringJoiner("y=");
    sj1.add("x=9");
    sj1.add("y=4");
System.out.println(sj1);
//System.out.println(sj2);


    }
}
