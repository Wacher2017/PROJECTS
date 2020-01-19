/**
 * @author Chunming_Wang
 */
public class SplitTest {

    public static void main(String[] args) {
        String[] strs = "cpuNum desc, hostId asc".split(",");
        for (String str : strs) {
            System.out.println(str.trim().split("\\s")[0]);
            System.out.println(str.trim());
        }
    }

}
