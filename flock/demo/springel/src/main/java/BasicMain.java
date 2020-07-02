import entity.Inventor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * @author Chunming_Wang
 */
public class BasicMain {

    public static void main(String[] args) {
        // ExpressionParser 接口负责解析表达式字符串(用单引号包裹起来)
        ExpressionParser parser = new SpelExpressionParser();
        // Expression接口负责计算表达式字符串
        // SpEL支持多种功能,如调用方法, 访问属性,调用构造函数

        // 1. 调用方法实例
        Expression expCallMethod = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) expCallMethod.getValue();
        System.out.println(message);

        // 2.1 调用属性实例,此时会调用getBytes()方法
        Expression expCallProperty = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) expCallProperty.getValue();
        System.out.println(Arrays.toString(bytes));
        // 2.2 调用嵌套属性实例,会调用 getBytes().length
        Expression expCallNestProperty = parser.parseExpression("'Hello World'.bytes.length");
        int len = (int) expCallNestProperty.getValue();
        System.out.println(len);

        // 3. 调用构造函数实例
        Expression expCallConstructor = parser.parseExpression("new String('hello world').toUpperCase()");
        // 使用 public <T> T getValue(Class<T> clazz); 方法可以无需强转.
        // 但是如果结果转换为T类型失败,则会抛出EvaluationException.
        String msg = expCallConstructor.getValue(String.class);
        System.out.println(msg);

        // SpEL最常见的用法是根据一个特定的对象实例(称为根对象)求值.
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
        System.out.println(name);
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        System.out.println(result);

    }

}
