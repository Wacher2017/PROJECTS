import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Chunming_Wang
 */
public class GregorianCalendarMain {

    //Sun Sep 09 22:51:10 CST 2018 其中CST表示的是中央标准时间，例如我这边的中央标准时间是北京时间22:51:10

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println(d);
//        大致地看了下，发现Date类现在没什么可以用的方法了，像什么getYear()这样系列的已经不能用了！
//        能用的就只有after()、before()、clone()。我怀疑就是让我们用字典函数，也可以将date格式化。
//        其中还有一个getTime方法使用来获取自1970年1月1日00:00时间以来的豪秒数！
//        不如用GregorianCalendar来的自在
        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//获取时间，参数是获取的时间类型，这个可以通过直接使用下面这句查看自己想要什么，然后当参数就行了！
        System.out.println(calendar.toString());//toString方法
        System.out.println(calendar.getTime());//直接返回一个Date对象，打印出来和第一个Date结果一样
        System.out.println(calendar.after(new GregorianCalendar(2018,9,30,0,0,0)));//判断时间是否在参数时间后
        System.out.println(calendar.before(new GregorianCalendar(2018,9,30,0,0,0)));//判断时间是否在参数时间前
        Calendar calendar1 = (Calendar) calendar.clone();//克隆方法
        System.out.println(calendar1);//一样
        System.out.println(calendar.compareTo(new GregorianCalendar(2018,9,30,0,0,0)));//比较，这个返回-1，即早-1晚1
        System.out.println(calendar.equals(new GregorianCalendar(2018,9,10)));//如果只设置了日期的话时间默认是网上0点整（参考下面这句），即时间不相等返回false
        System.out.println(new GregorianCalendar(2018,9,10));
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));//这个是根据时间获得最大值，如本月日期最大为30（现在9月）
        System.out.println(calendar.getMaximum(Calendar.DAY_OF_MONTH));//而月的日期的最大能是31了（即在大月的时候！）
//      获得最小值的示例略
//      getDisplayName和getDisplayNames暂时好像没什么用！不展示了
        System.out.println(calendar.getFirstDayOfWeek());//有些地方以星期一为第一天，有些则以周日，这个是获取当地第一天的星期数！
        System.out.println(calendar.getGreatestMinimum(Calendar.DAY_OF_YEAR));//网上解释说最高最小值，凌乱了！

        /*
         * 鬼知道有什么用，文档说：
         * Gets the Gregorian Calendar change date.  This is the point when the
         * switch from Julian dates to Gregorian dates occurred. Default is
         * October 15, 1582 (Gregorian). Previous to this, dates will be in the Julian
         * calendar.
         * @return the Gregorian cutover date for this <code>GregorianCalendar</code> object.
         */
        System.out.println(((GregorianCalendar) calendar).getGregorianChange());
        System.out.println(calendar.getLeastMaximum(Calendar.DAY_OF_MONTH));//该又是返回最大值中的最小值吧！没用！
        System.out.println(calendar.getTimeInMillis());//返回毫秒数啰！
        System.out.println(calendar.getTimeZone());//显示时区是上海sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null]
        System.out.println(((GregorianCalendar) calendar).isLeapYear(2008));//判断某一年是否为闰年
        //下面这个是获取宽容度值对于宽容度，网上是这样解释的：
        //如果设置了宽容度即lenient为true，则在设置日期时可以接受比其生成日期的范围还要大的值，例如我们设置日期为1月32号，在get的时候计算日期就会将其计算成1月1号
        //而如果为false的话就不能。（注意：这里设置的时候只是将值赋予了相关参数，所以设置的时候值不符合规范不会报错，而是在get计算日期的时候报错！
        System.out.println(calendar.isLenient());
    }

}
