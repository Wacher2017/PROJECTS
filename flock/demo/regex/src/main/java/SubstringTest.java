/**
 * @author Chunming_Wang
 */
public class SubstringTest {

    public static void main(String[] args) {
        /*String str = "disk[*]";
        String prestring = str.substring(0, str.indexOf("["));
        String substring = str.substring(str.indexOf("[")+1, str.indexOf("]"));
        System.out.println(prestring);
        System.out.println(substring);*/

        String str1 = "cpuNum desc";
        String prestring1 = str1.substring(str1.split("\\s")[0].length(), str1.length());
        //String substring1 = str1.substring(str1.indexOf("\\s")+1, str1.length());
        System.out.println(prestring1);
        //System.out.println(substring1);
    }

}
