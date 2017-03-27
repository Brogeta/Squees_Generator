package squees_generator.common.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon.O'Donnell on 2/22/2017.
 */
public class MathHelper extends CommonHelper{

    public static List<Integer> listDigits(int number){
        List<Integer> digits = new ArrayList<>();
        int hold ;


        while(number != 0){
            hold =  number % 10;

            number = (number-hold)/10;
            digits.add(hold);
        }

        return digits;
    }

    public static float percentToDecimal(int percent){
        return (float) percent / 100;
    }

    public static double meanDouble(List<Double> list) {
        double sum = 0;

        for (int x = 0; x < list.size(); ++x) {
            sum += list.get(x);
        }

        return sum / list.size();
    }

}
