import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EvaluationContext接口在计算表达式时解析属性,方法,字段及提供类型转换
 * 有两个开箱即用的实现:
 * 1. SimpleEvaluationContext: 提供了部分SpEL配置项.
 *    它只支持SpEL的一个子集,它不支持JAVA类型引用,构造函数,及bean的引用.
 *    要求显式的配置对表达式中方法和属性的支持级别.默认情况下, 仅支持读取属性.
 *    但可以通过获取一个builder来具体配置所需要的支持: 如自定义PropertyAccessor(无反射), 数据绑定属性的只读访问或读写.
 *
 * 2. StandardEvaluationContext: 提供了全部的SpEL配置项
 *
 * @author Chunming_Wang
 */
public class EvaluationContextMain {

    public static void main(String[] args) {
        // 1. 类型转换
        // 默认情况下SpEL使用org.springframework.core.convert.ConversionService作为类型转换服务.
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        Simple simple = new Simple();
        simple.booleanList.add(true);

        ExpressionParser parser = new SpelExpressionParser();
        // 此处false是一个字符串. SpEL and the conversion service 将正确识别它并将其转换为Boolean型.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);

        // 2. 转换配置
        // 可以使用SpelParserConfiguration对象配置ExpressionParser.
        // 配置对象能控制一些表达式组件的行为.
        // 如对于数组或集合, 指定其某个元素为null, 则可以自动创建这个元素,
        // 如果索引超过了数组或列表的长度, 则会自动增加数组或列表以适应这个索引.

        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true,true);
        parser = new SpelExpressionParser(config);
        Expression expression = parser.parseExpression("list[3]");

        Demo demo = new Demo();
        Object o = expression.getValue(demo);
        System.out.println(o);
        // demo.list will now be a real collection of 4 entries
        // Each entry is a new empty String
    }

}

class Simple {
    public List<Boolean> booleanList = new ArrayList<Boolean>();
}

class Demo {
    public List<String> list;
}
