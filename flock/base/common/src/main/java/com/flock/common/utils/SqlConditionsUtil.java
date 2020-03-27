package com.flock.common.utils;

import com.flock.common.pojo.ExpAtom;
import com.flock.common.pojo.ExpStatement;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Chunming_Wang
 */
public class SqlConditionsUtil {

    private static final Logger logger = LoggerFactory.getLogger(SqlConditionsUtil.class);

    private static final String CONDITION_SPLIT = "(and|or)";

    private static final String SQL_CONDITION_REG = "([\\s*\\(\\s*\\(\\s*]*)?([\\w\\(]*[\\`]*[\\w\\.]+[\\`]*[\\)]*)\\s*(?:(!=|<>|>=|<=|=|>|<|\\snot like\\s|\\slike\\s|\\snot in\\s*\\(\\s*[\\'\\\"\\,\\u4e00-\\u9fa5\\w\\s]*\\s*|\\sin\\s*\\(\\s*[\\'\\\"\\,\\u4e00-\\u9fa5\\w\\s]*\\s*|\\sbetween\\s[\\(\\)\\'\\\"\\.\\u4e00-\\u9fa5\\w]+\\s(?:and)\\s|\\sis not\\s|\\sis\\s))\\s*([\\w]*[\\(\\'\\\"].*?[\\'\\\"\\)]|[-]?\\d+|null)*([\\s*\\)\\s*\\)\\s*]*)?(?=[\\s])[\\s]*(and|or)?";

    public static final String ONE_SPACE = " ";

    private static final char UNDERLINE = '_';

    private SqlConditionsUtil() {}

    public static String parseWhereDesc(String where) {
        return parseWhereDesc(where, null);
    }

    /**
     * 通过正则表达式提取where相关信息
     * m.group(0) ：一个条件和相应的连接符 [格式形如：aaa=111 and/or]
     * m.group(1) ：括号优先级，左括号“(”，如果where中不存在括号优先级，则为null
     * m.group(2) ：条件的属性字段 [如m.group(0)中的 aaa]
     * m.group(3) ：where的连接符，包括=、!=、<>、>、<、>=、<=、like、in、not in、is null、is not null、between...and等
     * m.group(4) ：条件的属性值 [如m.group(0)中的 111]
     * m.group(5) ：对应的右括号“)”
     * m.group(6) ：条件的连接符，and/or
     * @param where
     * @param tableAlias
     * @return
     */
    public static String parseWhereDesc(String where, String tableAlias) {
        logger.info("accept where param :{}",where);
        if(StringUtils.isBlank(where)) {return where;}
        Matcher m = matchWhere(where);
        if(m == null) { return where;}
        StringBuilder sb = new StringBuilder();
        while(m.find()){
            //System.out.println(m.group(1) + "\t" + m.group(2) + "\t" + m.group(3) + "\t"
            //+ m.group(4) + "\t" + m.group(5) + "\t" + m.group(6) + "\t" + m.group(7));
            if(StringUtils.isNotBlank(m.group(1))) {
                sb.append(m.group(1).trim()).append(ONE_SPACE);
            }
            if(StringUtils.isNotBlank(tableAlias) && !m.group(2).contains("(")) {
                sb.append(tableAlias).append(".");
            }
            if("1".equalsIgnoreCase(m.group(2)) || m.group(2).contains("`") || m.group(2).contains(".") || m.group(2).contains("(")) {
                if(m.group(2).contains("(")) {
                    String prefix = m.group(2).split("\\(")[0];
                    String context = m.group(2).split("\\(")[1].split("\\)")[0];
                    sb.append(prefix).append("(");
                    if(StringUtils.isNotBlank(tableAlias)) {
                        sb.append(tableAlias).append(".");
                    }
                    sb.append(camelToUnderline(context)).append(")").append(ONE_SPACE)
                            .append(m.group(3).trim());
                } else {
                    sb.append(camelToUnderline(m.group(2))).append(ONE_SPACE)
                            .append(m.group(3).trim());
                }
            } else {
                sb.append("`").append(camelToUnderline(m.group(2))).append("`").append(ONE_SPACE)
                        .append(m.group(3).trim());
            }
            if(!m.group(3).contains("in")) {
                sb.append(ONE_SPACE);
            }
            if(m.group(4) != null) {
                sb.append(m.group(4).trim());
            }
            if(!m.group(3).contains("in")) {
                sb.append(ONE_SPACE);
            }
            if(StringUtils.isNotBlank(m.group(5))) {
                sb.append(m.group(5).trim()).append(ONE_SPACE);
            }
            if(StringUtils.isNotBlank(m.group(6))) {
                sb.append(m.group(6).trim()).append(ONE_SPACE);
            }
        }
        String result = sb.toString();
        logger.info("return where param :{}",result);
        return result;
    }

    public static Matcher matchWhere(String where) {
        if(StringUtils.isBlank(where)) {return null;}
        //条件参数最后默认加一个空格，否则正则表达式无法识别最后一个条件（待优化）
        where = where + ONE_SPACE;
        //Pattern.CASE_INSENSITIVE 忽略大小写
        Pattern p = Pattern.compile(SQL_CONDITION_REG, Pattern.CASE_INSENSITIVE);
        return p.matcher(where);
    }

    /**
     * 解析方式二，优化中...
     * @param where
     * @return
     */
    @Deprecated
    private static String getCondition(String where){
        //Pattern.CASE_INSENSITIVE 忽略大小写
        Pattern p = Pattern.compile(CONDITION_SPLIT, Pattern.CASE_INSENSITIVE);
        // 用Pattern类的matcher()方法生成一个Matcher对象
        Matcher m = p.matcher(where);
        StringBuffer sb = new StringBuffer();
        // 使用循环找出模式匹配的内容替换之,再将内容加到sb里
        while (m.find()) {
            if(sb.indexOf("between") >= 0 && sb.indexOf("and") == -1) {
                sb.append(" ").append(m.group()).append(" ");
            } else {
                sb.setLength(0);
            }
            m.appendReplacement(sb, "");
            if(sb.indexOf("between") >= 0 && sb.indexOf("and") == -1) {
                continue;
            }
            System.out.println(sb.toString() + "----" + m.group());
        }
        sb.setLength(0);
        // 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
        m.appendTail(sb);
        System.out.println(sb.toString() + "----");
        return "    "+sb.toString();
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isBlank(param)) {
            return StringUtils.EMPTY;
        }
        param = param.trim();
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (i == 0) {
                sb.append(Character.toLowerCase(c));
            } else {
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE).append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //不支持含有between关键字的语句
    public static String buildConditions(String exp, String key) {
        ExpStatement es = parseStatement(exp, null);
        //System.out.println(es);
        StringBuilder result = new StringBuilder();
        if (es.getSingle() != null && CollectionUtils.isEmpty(es.getInSide())) {
            String atomExp = es.getSingle().getStatement();
            List<List<Map<String, Object>>> statements = analysisExp(atomExp);
            String singleStatement = filterStatements(key, statements);
            result.append("(").append(singleStatement).append(")");
        } else {
            buildConditionProcess(es, key, result);
        }
        String response = result.toString();
        if (StringUtils.isNotBlank(response)) {
            response = response.substring(1, response.length() - 1);
        }
        return response;
    }

    private static void buildConditionProcess(ExpStatement es, String key, StringBuilder sb) {
        sb.append("(");
        boolean run = false;
        if (!CollectionUtils.isEmpty(es.getOutSide())) {
            ExpAtom last = es.getOutSide().get(es.getOutSide().size() - 1);
            if (last.getConnector() != null) {
                fillOutSideExp(es, key, sb);
            } else {
                run = true;
            }
        }
        if (!CollectionUtils.isEmpty(es.getInSide())) {
            List<ExpStatement> next = es.getNext();
            List<String> subExpList = next.stream()
                    .filter(st -> st.getSingle() != null)
                    .map(st -> st.getSingle().getStatement())
                    .collect(Collectors.toList());
            List<ExpStatement> subExpHasNext = next.stream()
                    .filter(st -> st.getSingle() == null && !CollectionUtils.isEmpty(st.getInSide()))
                    .collect(Collectors.toList());
            List<Map<String, Object>> tempList = new ArrayList<>();
            for (ExpAtom inAtom : es.getInSide()) {
                String s = inAtom.getStatement().substring(1, inAtom.getStatement().length() - 1);
                String connector = inAtom.getConnector();
                if (subExpList.contains(s)) {
                    List<List<Map<String, Object>>> statements = analysisExp(s);
                    String inStatement = filterStatements(key, statements);
                    if (StringUtils.isNotBlank(inStatement)) {
                        if (StringUtils.isNotBlank(connector)) {
                            sb.append("(").append(inStatement).append(") ").append(connector).append(" ");
                        } else {
                            sb.append("(").append(inStatement).append(") ");
                        }
                    }
                } else {
                    for (ExpStatement est : subExpHasNext) {
                        ExpAtom ea = est.getInSide().get(0);
                        if (s.contains(ea.getStatement())) {
                            Map<String, Object> temp = new HashMap<>();
                            temp.put("statement", est);
                            temp.put("connector", connector);
                            tempList.add(temp);
                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(tempList)) {
                for (Map<String, Object> map : tempList) {
                    ExpStatement expStatement = (ExpStatement) map.get("statement");
                    buildConditionProcess(expStatement, key, sb);
                    if (map.get("connector") != null && !"(".equals(sb.toString().trim())) {
                        if (sb.toString().endsWith(" ")) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        sb.append(" ").append(map.get("connector")).append(" ");
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(es.getOutSide()) && run) {
            fillOutSideExp(es, key, sb);
        }
        if (sb.lastIndexOf("(") == (sb.length() - 1)) {
            sb.delete(sb.length() - 1, sb.length());
        } else {
            if (sb.lastIndexOf(" and ") == (sb.length() - 5)) {
                sb.delete(sb.length() - 5, sb.length());
            } else if (sb.lastIndexOf(" or ") == (sb.length() - 4)) {
                sb.delete(sb.length() - 4, sb.length());
            }
            if (sb.toString().endsWith(" ")) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
        }
        if (sb.lastIndexOf(" and ") == (sb.length() - 5)) {
            sb.delete(sb.length() - 4, sb.length());
        } else if (sb.lastIndexOf(" or ") == (sb.length() - 4)) {
            sb.delete(sb.length() - 3, sb.length());
        }
    }

    private static void fillOutSideExp(ExpStatement es, String key, StringBuilder sb) {
        for (ExpAtom outAtom : es.getOutSide()) {
            List<List<Map<String, Object>>> statements = analysisExp(outAtom.getStatement());
            String outStatement = filterStatements(key, statements);
            if (StringUtils.isNotBlank(outStatement)) {
                if (outAtom.getConnector() == null) {
                    sb.append(outStatement).append(" ");
                } else {
                    sb.append(outStatement).append(" ").append(outAtom.getConnector()).append(" ");
                }
            }
        }
    }

    private static ExpStatement parseStatement(String exp, List<ExpStatement> nextList) {
        ExpStatement es = new ExpStatement();
        exp = exp.trim();
        List<String> subExp = bracketBoundary(exp);
        if (!CollectionUtils.isEmpty(subExp)) {
            List<ExpAtom> extraList = buildExtraExpList(exp, subExp);
            if (!CollectionUtils.isEmpty(extraList)) {
                es.setOutSide(extraList);
            }
            List<ExpAtom> inList = new ArrayList<>();
            List<ExpStatement> nextButOne = new ArrayList<>();
            for (String sub : subExp) {
                ExpAtom atom = buildInSideExp(exp, sub);
                inList.add(atom);
                String s = sub.trim().substring(1, sub.trim().length() - 1);
                parseStatement(s, nextButOne);
            }
            if (!CollectionUtils.isEmpty(inList)) {
                es.setInSide(inList);
            }
            if (!CollectionUtils.isEmpty(nextButOne)) {
                es.setNext(nextButOne);
            }
        } else {
            ExpAtom atom = new ExpAtom();
            atom.setStatement(exp);
            es.setSingle(atom);
        }
        if (nextList != null) {
            nextList.add(es);
        }
        return es;
    }

    private static List<String> bracketBoundary(String expression) {
        int stack = 0;
        int start = 0;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                if (stack == 0) {
                    start = i;
                }
                stack++;
            } else if (ch == ')') {
                stack--;
                if (stack == 0) {
                    if (start > 4) {
                        String front = expression.substring(start - 4, start);
                        if (!front.contains("in")) {
                            result.add(expression.substring(start, i + 1));
                        }
                    } else {
                        result.add(expression.substring(start, i + 1));
                    }
                }
            }
        }
        return result;
    }

    private static List<ExpAtom> buildExtraExpList(String exp, List<String> subExp) {
        List<ExpAtom> extraList = new ArrayList<>();
        for (int i = 0; !CollectionUtils.isEmpty(subExp) && i < subExp.size(); i++) {
            String sub = subExp.get(i);
            if (i == 0 && exp.indexOf(sub) > 0) {
                String firstExtraExp = exp.substring(0, exp.indexOf(sub)).trim();
                buildExtraExp(firstExtraExp, extraList);
            } else if (i > 0) {
                String preSub = subExp.get(i - 1);
                String centerExtraExp = exp.substring(exp.indexOf(preSub) + preSub.length(), exp.indexOf(sub)).trim();
                buildExtraExp(centerExtraExp, extraList);
            }
            if (i == subExp.size() - 1) {
                String lastExtraExp = exp.substring(exp.indexOf(sub) + sub.length(), exp.length()).trim();
                buildExtraExp(lastExtraExp, extraList);
            }
        }
        return extraList;
    }

    private static void buildExtraExp(String extraExp, List<ExpAtom> extraList) {
        ExpAtom atom = new ExpAtom();
        if (StringUtils.isNotBlank(extraExp) && !("and".equalsIgnoreCase(extraExp) || "or".equalsIgnoreCase(extraExp))) {
            String statement = extraExp;
            if (extraExp.endsWith(" and") || extraExp.endsWith(" or")) {
                statement = extraExp.substring(0, extraExp.length() - 3).trim();
            }
            if (statement.startsWith("and ") || statement.startsWith("or ")) {
                statement = statement.substring(3, statement.length()).trim();
            }
            if (StringUtils.isNotBlank(statement)) {
                atom.setStatement(statement);
                if (extraExp.endsWith(" and") || extraExp.endsWith(" or")) {
                    atom.setConnector(extraExp.substring(extraExp.length() - 3, extraExp.length()).trim());
                }
                extraList.add(atom);
            }
        }
    }

    private static ExpAtom buildInSideExp(String exp, String sub) {
        ExpAtom atom = new ExpAtom();
        atom.setStatement(sub);
        int start = exp.substring(0, exp.indexOf(sub) + sub.length()).length() + 1;
        int end = exp.length() - 1;
        if (start < end) {
            String str = exp.substring(start, end);
            String connector = str.trim().split("\\s+")[0];
            atom.setConnector(connector);
        }
        return atom;
    }

    private static List<List<Map<String, Object>>> analysisExp(String atomExp) {
        if (StringUtils.isBlank(atomExp)) return Collections.emptyList();
        //System.out.println("======================================");
        //System.out.println(">>> analysis: " + atomExp);
        List<List<Map<String, Object>>> outOrInAnd = new ArrayList<>();
        String[] orExps = atomExp.split("\\sor\\s");
        for (String exp : orExps) {
            String[] expressions = exp.split("\\sand\\s");
            List<Map<String, Object>> inAnd = new ArrayList<>();
            for (String expression : expressions) {
                Map<String, Object> map = new HashMap<>();
                String[] pointSeparator = expression.split("\\.");
                String key = ""; //属性所属的对象
                List<String> objectPath = new ArrayList<>();
                if (pointSeparator.length >= 2) {
                    key = pointSeparator[pointSeparator.length - 2];
                    for (int i = pointSeparator.length - 2; i >= 0; i--) {
                        objectPath.add(pointSeparator[i]);
                    }
                }
                Collections.reverse(objectPath);
                map.put("path", objectPath);
                if (StringUtils.isNotBlank(key)) {
                    map.put(key, pointSeparator[pointSeparator.length - 1]);
                } else {
                    //默认为用户属性
                    map.put("user", pointSeparator[pointSeparator.length - 1]);
                }
                inAnd.add(map);
            }
            outOrInAnd.add(inAnd);
        }
        //将“并”逻辑条件全部归置到第一个元素集合中
        for (int i = 1; i < outOrInAnd.size(); i++) {
            List<Map<String, Object>> inList = outOrInAnd.get(i);
            if (!CollectionUtils.isEmpty(inList) && inList.size() > 1) {
                for (int j = 1; j < inList.size(); j++) {
                    outOrInAnd.get(0).add(inList.get(j));
                }
            }
        }
        //去掉归置的“并”逻辑，后边的元素集合理论均为一个条件，为“或”逻辑关系
        for (int i = 1; i < outOrInAnd.size(); i++) {
            List<Map<String, Object>> inList = outOrInAnd.get(i);
            if (!CollectionUtils.isEmpty(inList) && inList.size() > 1) {
                for (int j = inList.size() - 1; j > 0; j--) {
                    inList.remove(j);
                }
            }
        }
        //System.out.println(outOrInAnd);
        return outOrInAnd;
    }

    private static String filterStatements(String key, List<List<Map<String, Object>>> statements) {
        boolean hasOr = false;
        StringBuilder orSb = new StringBuilder();
        for (List<Map<String, Object>> inList : statements) {
            boolean hasAnd = false;
            StringBuilder inSb = new StringBuilder();
            for (Map<String, Object> inMap : inList) {
                for (Map.Entry<String, Object> entry : inMap.entrySet()) {
                    if (key.equalsIgnoreCase(entry.getKey())) {
                        hasAnd = true;
                        inSb.append(entry.getValue()).append(" and ");
                    }
                }
            }
            if (inSb.length() > 5) {
                inSb.delete(inSb.length() - 5, inSb.length());
            }
            if (hasAnd) {
                hasOr = true;
                orSb.append(inSb.toString().trim()).append(" or ");
            }
        }
        if (hasOr) {
            orSb.delete(orSb.length() - 4, orSb.length());
        }
        return orSb.toString().trim();
    }

    public static void main(String[] arg) {
        String str = "ci_type = \"IBMFlash\" and ci_label = 'IBM Flash 存储' and resource_type in ('Client', 'Database', 'Oracle') and shen in(12, 345, 654) and  aaH=\"aa\" and bb>32 and ((cc = \"cc\" and nn=\"99\") or (dd < 123 and mm=\"55\")) and ee <>\"22\" and ff between 1 and 100 and INET_ATON(`address`) between INET_ATON('127.0.0.1') and INET_ATON('127.0.0.6')";
        String result = parseWhereDesc(str, "t1");
        System.out.println("================================");
        System.out.println(result);
        System.out.println();
        System.out.println("================================");
        String exp = "(user.aa = 123 or user.bb = 'wacher') and user.cc = 324 and user.person.dd = '123' or user.yy = '789' and (role.ee in (1,2,3) and user.role.ff in('11','22')) and (tt = 123) and group.gg in (6,7,8) or groupRole.hh < 20 and ((ii = 1 or jj = 2) and (kk = 3 or ll = 4)) and (mm = 1 and (nn = 3 or oo = 4)) and pp=1";
        //String exp = "(user.aa = 123 or user.bb = 'wacher') and user.cc = 324 and user.person.dd = '123' or user.yy = '789' and (role.ee in (1,2,3) and user.role.ff in('11','22')) and (tt = 123) and group.gg in (6,7,8) or groupRole.hh < 20 and ((ii = 1 or jj = 2) and (kk = 3 or ll = 4)) and (mm = 1 and (nn = 3 or oo = 4))";
        //String exp = "user.aa = 123 or user.bb = 'wacher' and user.cc = 324 and role.dd = 234 and role.ff = 968 and group.gg = 'wachergg'";
        //String exp = "1=1 and user.aa = 123";
        //String exp = "user.aa = 123 or (user.bb = 'bbb' and user.cc = 'ccc') and role.dd = 1";
        //String exp = "(user.aa = 123 or user.bb = 'wacher')";
        //String exp = "(user.aa = 123 or (user.bb = 'wacher' and (role.cc = 'ccc'))) and role.dd = 1";
        //各个对象之间的关系这里解析无法描述，使用的接口可扩展对象的“且”“或”关系
        String filter = buildConditions(exp, "user");
        System.out.println(filter);
    }

}
