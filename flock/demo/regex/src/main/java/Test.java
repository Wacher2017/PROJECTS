import java.util.regex.Pattern;

/**
 * @author Chunming_Wang
 */
public class Test {

    public static void main(String[] args) {
        final String pattern = "(\\d+\\.\\w+[\\[\\],:*\\w+]*\\.[*\\w+]*[,\\w+]*[.\\-\\w+]*(\\s[\\sand]*\\s)*){1,}[\\s]*";
        String content = "125.cpu[*].name,size.90 and 126.memory.last5s and 127.disk[c:].size.10";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
        /*Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }*/
    }

}
