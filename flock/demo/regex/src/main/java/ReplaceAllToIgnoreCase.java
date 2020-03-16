/**
 * replaceAll() 替换时，忽略大小写
 * @author Chunming_Wang
 */
public class ReplaceAllToIgnoreCase {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        String regex = "(?i)java";
        String context = "Java to JAVA to JAva to JaVa to jAvA to JavA to java etc.";
        String replacement = "Perfect";
        System.out.println(">>> 将所有的 Java 忽略大小写替换为 Perfect");
        System.out.println(">>> Input: " + context);
        System.out.println(">>> Output: " + context.replaceAll(regex, replacement));
        System.out.println("=============================================================");
    }

}
