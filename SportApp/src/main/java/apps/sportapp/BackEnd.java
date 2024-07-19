package apps.sportapp;
import java.util.ArrayList;
public class BackEnd {

    public static ArrayList<String> stringList = new ArrayList<>();
    //public String[] a = {"1", "123", "123", "13.07.2024", "Bettsport"};

    //public String[] getA(){
        //return a;
   // }

        public static void saveString(String s) {
            stringList.add(s);
            System.out.println("Saved String: " + s);
            System.out.println("Array: " + stringList);
        }
        public static void deleteString(int index) {
            stringList.remove(index);
            System.out.println("Array: " + stringList);
        }

}

