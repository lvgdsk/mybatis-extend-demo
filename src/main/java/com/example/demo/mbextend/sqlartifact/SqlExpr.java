package com.example.demo.mbextend.sqlartifact;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/2/17 11:10
 */
@Data
@AllArgsConstructor
public class SqlExpr implements SqlField {
    private String sqlExpr;
    private String columnAlias;
    private List<Object> params;

    @Override
    public String getColumn() {
        return sqlExpr;
    }

    @Override
    public String getQualifyField() {
        return sqlExpr;
    }

    @Override
    public String getTableAlias() {
        return null;
    }

    /** 数学计算 */
    /** 加法 */
    public static SqlExpr add(Object param1,Object param2){
        return add(null,param1,param2);
    }
    /** 减法 */
    public static SqlExpr sub(Object param1,Object param2){
        return sub(null,param1,param2);
    }
    /** 乘法 */
    public static SqlExpr mul(Object param1,Object param2){
        return mul(null,param1,param2);
    }
    /** 除法 */
    public static SqlExpr div(Object param1,Object param2){
        return div(null,param1,param2);
    }
    /** 整除 */
    public static SqlExpr divInt(Object param1,Object param2){
        return divInt(null,param1,param2);
    }
    /** 求余 */
    public static SqlExpr modAri(Object param1,Object param2){
        return modAri(null,param1,param2);
    }
    /** 按位与 */
    public static SqlExpr bitAnd(Object param1,Object param2){
        return bitAnd(null,param1,param2);
    }
    /** 按位或 */
    public static SqlExpr bitOr(Object param1,Object param2){
        return bitOr(null,param1,param2);
    }
    /** 按位取反 */
    public static SqlExpr bitNeg(Object param){
        return bitNeg(null,param);
    }
    /** 按位异或 */
    public static SqlExpr bitXor(Object param1,Object param2){
        return bitXor(null,param1,param2);
    }
    /** 按位左移 */
    public static SqlExpr bitMoveLeft(Object param1,Object param2){
        return bitMoveLeft(null,param1,param2);
    }
    /** 按位右移 */
    public static SqlExpr bitMoveRight(Object param1,Object param2){
        return bitMoveRight(null,param1,param2);
    }
    /** 返回 p1 的绝对值 */
    public static SqlExpr abs(Object param1){
        return abs(null,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度），p1 为一个数值 */
    public static SqlExpr acos(Object param1,Object param2){
        return acos(null,param1,param2);
    }
    /** 求反正弦值（单位为弧度），p1 为一个数值*/
    public static SqlExpr asin(Object param1){
        return asin(null,param1);
    }
    /** 求反正切值（单位为弧度），p1 为一个数值*/
    public static SqlExpr atan(Object param1){
        return atan(null,param1);
    }
    /** 求反正切值（单位为弧度）*/
    public static SqlExpr atan2(Object param1,Object param2){
        return atan2(null,param1,param2);
    }
    /** 返回一个表达式的平均值，p1是一个字段*/
    public static SqlExpr avg(Object param1){
        return avg(null,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static SqlExpr ceil(Object param1){
        return ceil(null,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static SqlExpr ceiling(Object param1){
        return ceiling(null,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度），p1 为一个数值 */
    public static SqlExpr cos(Object param1){
        return cos(null,param1);
    }
    /** 求余切值(参数是弧度)*/
    public static SqlExpr cot(Object param1){
        return cot(null,param1);
    }
    /** 返回查询的记录总数，p1 参数是一个字段或者 * 号 */
    public static SqlExpr count(Object param1){
        return count(null,param1);
    }
    /** 将弧度转换为角度 */
    public static SqlExpr degrees(Object param1){
        return degrees(null,param1);
    }
    /** 返回 e 的 p1 次方 */
    public static SqlExpr exp(Object param1){
        return exp(null,param1);
    }
    /** 返回小于或等于 p1 的最大整数 */
    public static SqlExpr floor(Object param1){
        return floor(null,param1);
    }
    /** 返回p1的自然对数，以 e 为底。*/
    public static SqlExpr ln(Object param1){
        return ln(null,param1);
    }
    /** 返回以e为底的p1的对数 */
    public static SqlExpr loge(Object param1){
        return loge(null,param1);
    }
    /** 返回以p1为底的p2的对数 */
    public static SqlExpr log(Object param1,Object param2){
        return log(null,param1,param2);
    }
    /** 返回以 10 为底p1的对数*/
    public static SqlExpr log10(Object param1){
        return log10(null,param1);
    }
    /** 返回以 2 为底p1的对数*/
    public static SqlExpr log2(Object param1){
        return log2(null,param1);
    }
    /** 返回字段 p1 中的最大值*/
    public static SqlExpr max(Object param1){
        return max(null,param1);
    }
    /** 返回字段 p1 中的最小值*/
    public static SqlExpr min(Object param1){
        return min(null,param1);
    }
    /** 返回 p1 除以 p2 以后的余数*/
    public static SqlExpr mod(Object param1,Object param2){
        return mod(null,param1,param2);
    }
    /** 返回圆周率(3.141593）*/
    public static SqlExpr pi(){
        return pi(null);
    }
    /** 返回 p1 的 p2 次方 */
    public static SqlExpr pow(Object param1,Object param2){
        return pow(null,param1,param2);
    }
    /** 返回 p1 的 p2 次方 */
    public static SqlExpr power(Object param1,Object param2){
        return power(null,param1,param2);
    }
    /** 将角度转换为弧度 */
    public static SqlExpr radians(Object param1){
        return radians(null,param1);
    }
    /** 返回 0 到 1 的随机数 */
    public static SqlExpr rand(){
        return rand(null);
    }
    /** 返回离 p1 最近的整数 */
    public static SqlExpr round(Object param1){
        return round(null,param1);
    }
    /** 返回 p1 的符号，p1 是负数、0、正数分别返回 -1、0 和 1 */
    public static SqlExpr sign(Object param1){
        return sign(null,param1);
    }
    /** 求反正弦值（单位为弧度），p1 为一个数值*/
    public static SqlExpr sin(Object param1){
        return sin(null,param1);
    }
    /** 返回p1的平方根 */
    public static SqlExpr sqrt(Object param1){
        return sqrt(null,param1);
    }
    /** 返回指定字段的总和 */
    public static SqlExpr sum(Object param1){
        return sum(null,param1);
    }
    /** 求反正切值（单位为弧度），p1 为一个数值*/
    public static SqlExpr tan(Object param1){
        return tan(null,param1);
    }
    /** 返回数值 p1 保留到小数点后 p2 位的值（与 ROUND 最大的区别是不会进行四舍五入） */
    public static SqlExpr truncate(Object param1,Object param2){
        return truncate(null,param1,param2);
    }
    /** 将p1数字从p2进制转换成p3进制，CONV(15, 10, 2) 返回 1111 */
    public static SqlExpr conv(Object param1,Object param2,Object param3){
        return conv(null,param1,param2,param3);
    }
    /** 返回p1的二进制编码 */
    public static SqlExpr bin(Object param1){
        return bin(null,param1);
    }

    /** 字符串 */
    /** 返回字符串的第一个字符的ASCII码 */
    public static SqlExpr ascii(Object param1){
        return ascii(null,param1);
    }
    /** 返回字符串的长度 */
    public static SqlExpr charLength(Object param1){
        return charLength(null,param1);
    }
    /** 与charLength完全相同 */
    public static SqlExpr characterLength(Object param1){
        return characterLength(null,param1);
    }
    /** 拼接字符串 */
    public static SqlExpr concat(Object ... params){
        return concat(null,params);
    }
    /** 使用间隔字符串param1拼接字符串 */
    public static SqlExpr concatWs(Object ... params){
        return concatWs(null,params);
    }
    /** 返回param1字符串在列表中的位置 */
    public static SqlExpr field(Object ... params){
        return field(null,params);
    }
    /** 返回param1字符串在以","号分隔的param2字符串中的位置，从1开始 */
    public static SqlExpr findInSet(Object param1,Object param2){
        return findInSet(null,param1,param2);
    }
    /** 将param1数字格式化为【#,###.##】，param2指定保留的小数位（四舍五入） */
    public static SqlExpr format(Object param1,Object param2){
        return format(null,param1,param2);
    }
    /** 使用p4字符串替换p1字符串中从p2开始长度为p3的子串，p2为1，则从第1个字符开始 */
    public static SqlExpr insert(Object param1,Object param2,Object param3,Object param4){
        return insert(null,param1,param2,param3,param4);
    }
    /** p1字符串在p2字符串的开始位置 */
    public static SqlExpr locate(Object param1,Object param2){
        return locate(null,param1,param2);
    }
    /** 与LOCATE完全相同 */
    public static SqlExpr position(Object param1,Object param2){
        return position(null,param1,param2);
    }
    /** 在p1字符串的前端填充p3，使之长度达到p2 */
    public static SqlExpr lpad(Object param1,Object param2,Object param3){
        return lpad(null,param1,param2,param3);
    }
    /** 在p1字符串的后端填充p3，使之长度达到p2 */
    public static SqlExpr rpad(Object param1,Object param2,Object param3){
        return rpad(null,param1,param2,param3);
    }
    /** 从p1字符串的p2开始，长度为p3的子串，p2为1时指的是从第1个字符开始 */
    public static SqlExpr mid(Object param1,Object param2,Object param3){
        return mid(null,param1,param2,param3);
    }
    /** 与mid完全相同 */
    public static SqlExpr substr(Object param1,Object param2,Object param3){
        return substr(null,param1,param2,param3);
    }
    /** 与SUBSTR完全相同 */
    public static SqlExpr substring(Object param1,Object param2,Object param3){
        return substring(null,param1,param2,param3);
    }
    /** p1为“a,b,c”，p2为“,”，p3为2则返回“a,b”，p3为-2则返回“b,c” */
    public static SqlExpr substringIndex(Object param1,Object param2,Object param3){
        return substringIndex(null,param1,param2,param3);
    }
    /** 将p1字符串重复p2次 */
    public static SqlExpr repeat(Object param1,Object param2){
        return repeat(null,param1,param2);
    }
    /** 将p3字符串替换p1字符串中的p2子串 */
    public static SqlExpr replace(Object param1,Object param2,Object param3){
        return replace(null,param1,param2,param3);
    }
    /** 反转字符串 */
    public static SqlExpr reverse(Object param1){
        return reverse(null,param1);
    }
    /** 返回p1字符串的后p2个字符 */
    public static SqlExpr right(Object param1,Object param2){
        return right(null,param1,param2);
    }
    /** 返回p1字符串的前p2个字符 */
    public static SqlExpr left(Object param1,Object param2){
        return left(null,param1,param2);
    }
    /** 返回p1个空格 */
    public static SqlExpr space(Object param1){
        return space(null,param1);
    }
    /** 字符串比较，p1=p2返回0，p1>p2返回1，p1<p2返回-1 */
    public static SqlExpr strcmp(Object param1,Object param2){
        return strcmp(null,param1,param2);
    }
    /** 除掉字符串前后空格 */
    public static SqlExpr trim(Object param1){
        return trim(null,param1);
    }
    /** 除掉字符串后端空格 */
    public static SqlExpr rtrim(Object param1){
        return rtrim(null,param1);
    }
    /** 除掉字符串前端空格 */
    public static SqlExpr ltrim(Object param1){
        return ltrim(null,param1);
    }
    /** 将字符串转换成大写 */
    public static SqlExpr ucase(Object param1){
        return ucase(null,param1);
    }
    /** 将字符串转换成大写 */
    public static SqlExpr upper(Object param1){
        return upper(null,param1);
    }
    /** 将字符串转换成小写 */
    public static SqlExpr lcase(Object param1){
        return lcase(null,param1);
    }
    /** 将字符串转换成小写 */
    public static SqlExpr lower(Object param1){
        return lower(null,param1);
    }
    /** 使用逗号作为间隔符连接表字段多行值；聚合函数 */
    public static SqlExpr groupConcat(Object param1){
        return groupConcat(null,param1);
    }
    /** 将多个参数组合成一个json数组 */
    public static SqlExpr jsonArray(Object ... params){
        return jsonArray(null,params);
    }
    /** 将多个参数组合成一个json对象 */
    public static SqlExpr jsonObject(Object ... params){
        return jsonObject(null,params);
    }

    /** 日期时间 */
    /** p1日期加上一个p2间隔时间 , p3为间隔时间单位，类型为TimeField */
    public static SqlExpr dateAdd(Object param1,Object param2,Object param3){
        return dateAdd(null,param1,param2,param3);
    }
    /** 计算起始日期 p1 加上 p2 天的日期 */
    public static SqlExpr adddate(Object param1,Object param2){
        return adddate(null,param1,param2);
    }
    /** p1日期时间加上p2秒 */
    public static SqlExpr addtime(Object param1,Object param2){
        return addtime(null,param1,param2);
    }
    /** 返回当前日期 */
    public static SqlExpr curdate(){
        return curdate(null);
    }
    /** 返回当前日期 */
    public static SqlExpr currentDate(){
        return currentDate(null);
    }
    /** 返回当前时间 */
    public static SqlExpr currentTime(){
        return currentTime(null);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr currentTimestamp(){
        return currentTimestamp(null);
    }
    /** 返回当前时间 */
    public static SqlExpr curtime(){
        return curtime(null);
    }
    /** 从日期或日期时间表达式中提取日期值 */
    public static SqlExpr date(Object param1){
        return date(null,param1);
    }
    /** 计算日期 d1->d2 之间相隔的天数	 */
    public static SqlExpr datediff(Object param1,Object param2){
        return datediff(null,param1,param2);
    }
    /** 按表达式p2的要求显示日期p1 */
    public static SqlExpr dateFormat(Object param1,Object param2){
        return dateFormat(null,param1,param2);
    }
    /** 函数从p1日期减去p2(数值)和p3(单位TimeField)指定的时间间隔。 */
    public static SqlExpr dateSub(Object param1,Object param2,Object param3){
        return dateSub(null,param1,param2,param3);
    }
    /** 返回日期p1的日期部分 */
    public static SqlExpr day(Object param1){
        return day(null,param1);
    }
    /** 返回日期p1是星期几，如 Monday,Tuesday */
    public static SqlExpr dayname(Object param1){
        return dayname(null,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static SqlExpr dayofmonth(Object param1){
        return dayofmonth(null,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static SqlExpr dayofweek(Object param1){
        return dayofweek(null,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static SqlExpr dayofyear(Object param1){
        return dayofyear(null,param1);
    }
    /** 从日期p1中获取指定的值，p2(TimeField)指定返回的值。 */
    public static SqlExpr extract(Object param1,Object param2){
        return extract(null,param1,param2);
    }
    /** 计算从 0000 年 1 月 1 日开始p1天后的日期 */
    public static SqlExpr fromDays(Object param1){
        return fromDays(null,param1);
    }
    /** 返回p1(HH:mm:ss)中的小时值 */
    public static SqlExpr hour(Object param1){
        return hour(null,param1);
    }
    /** 返回给给定日期的那一月份的最后一天 */
    public static SqlExpr lastDay(Object param1){
        return lastDay(null,param1);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr localtime(){
        return localtime(null);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr localtimestamp(){
        return localtimestamp(null);
    }
    /** 使用p1(year)和p2(day-of-year)组合一个日期 */
    public static SqlExpr MAKEDATE(Object param1,Object param2){
        return MAKEDATE(null,param1,param2);
    }
    /** 组合时间，参数分别为小时、分钟、秒 */
    public static SqlExpr maketime(Object param1,Object param2,Object param3){
        return maketime(null,param1,param2,param3);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static SqlExpr microsecond(Object param1){
        return microsecond(null,param1);
    }
    /** 返回p1(HH:mm:ss)中的分钟值 */
    public static SqlExpr minute(Object param1){
        return minute(null,param1);
    }
    /** 返回日期当中的月份名称，如 November */
    public static SqlExpr monthname(Object param1){
        return monthname(null,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static SqlExpr month(Object param1){
        return month(null,param1);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr now(){
        return now(null);
    }
    /** 为[年月]组合日期添加一个时段，PERIOD_ADD(201703, 5) 返回 201708 */
    public static SqlExpr periodAdd(Object param1,Object param2){
        return periodAdd(null,param1,param2);
    }
    /** 返回两个[年月]日期的月份差值，PERIOD_DIFF(201710, 201703) 返回 7 */
    public static SqlExpr periodDiff(Object param1,Object param2){
        return periodDiff(null,param1,param2);
    }
    /** 返回日期p1是第几季节，返回 1 到 4 */
    public static SqlExpr quarter(Object param1){
        return quarter(null,param1);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static SqlExpr second(Object param1){
        return second(null,param1);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static SqlExpr secToTime(Object param1){
        return secToTime(null,param1);
    }
    /** 将p1字符串按p2格式转变为日期 */
    public static SqlExpr strToDate(Object param1,Object param2){
        return strToDate(null,param1,param2);
    }
    /** 日期 p1 减去 p2 天后的日期 */
    public static SqlExpr subdate(Object param1,Object param2){
        return subdate(null,param1,param2);
    }
    /** 时间 p1 减去 p2 秒的时间 */
    public static SqlExpr subtime(Object param1,Object param2){
        return subtime(null,param1,param2);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr sysdate(){
        return sysdate(null);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static SqlExpr time(Object param1){
        return time(null,param1);
    }
    /** 按p2格式显示p1时间 */
    public static SqlExpr timeFormat(Object param1,Object param2){
        return timeFormat(null,param1,param2);
    }
    /** 将时间转换为秒 */
    public static SqlExpr timeToSec(Object param1){
        return timeToSec(null,param1);
    }
    /** 计算p1时间与p2时间的时间差，p1要不小于p2，否则返回null；TIMEDIFF("13:10:11", "13:10:10") 返回 00:00:01 */
    public static SqlExpr timediff(Object param1,Object param2){
        return timediff(null,param1,param2);
    }
    /** 将p1日期时间加上p2时间，TIMESTAMP('2001-01-11','12:00:00')返回2001-01-11 12:12:12；TIMESTAMP('2001-01-11 12:00:00','2:00:00')返回2001-01-11 14:00:00 */
    public static SqlExpr timestamp(Object param1,Object param2){
        return timestamp(null,param1,param2);
    }
    /** 计算时间差，返回 p3 − p2 的时间差，p1指定时差单位 */
    public static SqlExpr timestampdiff(Object param1,Object param2,Object param3){
        return timestampdiff(null,param1,param2,param3);
    }
    /** 计算p1日期距离 0000 年 1 月 1 日的天数 */
    public static SqlExpr toDays(Object param1){
        return toDays(null,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static SqlExpr week(Object param1){
        return week(null,param1);
    }
    /** p1日期是星期几，0 表示星期一，1 表示星期二 */
    public static SqlExpr weekday(Object param1){
        return weekday(null,param1);
    }
    /** 计算p1日期是本年的第几个星期（从1开始；不存在第0周，因为被认为是上一年的最后一周），以周1作为一周的第一天，计算方式：过了几次周1则为第几周 */
    public static SqlExpr weekofyear(Object param1){
        return weekofyear(null,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static SqlExpr year(Object param1){
        return year(null,param1);
    }
    /** 返回年份和第几周（从1开始，计算方式：过了几次p2指定的星期则为第几周；不存在第0周，因为被认为是上一年的最后一周），如 YEARWEEK('2022-01-10',1) 返回 202202，p2用于指定以周几作为一周的开始，默认0,即以周日作为一周的第一天 */
    public static SqlExpr yearweek(Object param1){
        return yearweek(null,param1);
    }
    /** 判断表达式是否为 NULL，是返回1，不是返回0 */
    public static SqlExpr isnull(Object param1){
        return isnull(null,param1);
    }
    /** 如果p1的值不为 NULL，则返回p1，否则返回p2。 */
    public static SqlExpr ifnull(Object param1,Object param2){
        return ifnull(null,param1,param2);
    }
    /** 如果表达式p1成立，返回结果p2；否则，返回结果p3。 */
    public static SqlExpr ifExpr(Object param1,Object param2,Object param3){
        return ifExpr(null,param1,param2,param3);
    }

    /** 加法 */
    public static SqlExpr add(Object alias, Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.ADD,param1,param2);
    }
    /** 减法 */
    public static SqlExpr sub(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.SUB,param1,param2);
    }
    /** 乘法 */
    public static SqlExpr mul(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.MUL,param1,param2);
    }
    /** 除法 */
    public static SqlExpr div(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.DIV,param1,param2);
    }
    /** 整除 */
    public static SqlExpr divInt(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.DIV_INT,param1,param2);
    }
    /** 求余 */
    public static SqlExpr modAri(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.MOD,param1,param2);
    }
    /** 按位与 */
    public static SqlExpr bitAnd(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.BITAND,param1,param2);
    }
    /** 按位或 */
    public static SqlExpr bitOr(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.BITOR,param1,param2);
    }
    /** 按位取反 */
    public static SqlExpr bitNeg(Object alias,Object param){
        return SqlExprBuilder.build(alias, ExprEnum.BITNEG,param);
    }
    /** 按位异或 */
    public static SqlExpr bitXor(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.BITXOR,param1,param2);
    }
    /** 按位左移 */
    public static SqlExpr bitMoveLeft(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.BITMOVELEFT,param1,param2);
    }
    /** 按位右移 */
    public static SqlExpr bitMoveRight(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.BITMOVERIGHT,param1,param2);
    }
    /** 返回字符串的第一个字符的ASCII码 */
    public static SqlExpr ascii(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ASCII,param1);
    }
    /** 返回字符串的长度 */
    public static SqlExpr charLength(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.CHAR_LENGTH,param1);
    }
    /** 与CHAR_LENGTH完全相同 */
    public static SqlExpr characterLength(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.CHARACTER_LENGTH,param1);
    }
    /** 拼接字符串 */
    public static SqlExpr concat(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.CONCAT,params);
    }
    /** 使用间隔字符串p1拼接字符串 */
    public static SqlExpr concatWs(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.CONCAT_WS,params);
    }
    /** 返回p1字符串在列表中的位置 */
    public static SqlExpr field(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.FIELD,params);
    }
    /** 返回p1字符串在以","号分隔的p2字符串中的位置，从1开始 */
    public static SqlExpr findInSet(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.FIND_IN_SET,param1,param2);
    }
    /** 将p1数字格式化为”#,###.##“，p2指定保留的小数位（四舍五入） */
    public static SqlExpr format(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.FORMAT,param1,param2);
    }
    /** 使用p4字符串替换p1字符串中从p2开始长度为p3的子串，p2为1，则从第1个字符开始 */
    public static SqlExpr insert(Object alias,Object param1,Object param2,Object param3,Object param4){
        return SqlExprBuilder.build(alias, ExprEnum.INSERT,param1,param2,param3,param4);
    }
    /** p1字符串在p2字符串的开始位置 */
    public static SqlExpr locate(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.LOCATE,param1,param2);
    }
    /** 与LOCATE完全相同 */
    public static SqlExpr position(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.POSITION,param1,param2);
    }
    /** 在p1字符串的前端填充p3，使之长度达到p2 */
    public static SqlExpr lpad(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.LPAD,param1,param2,param3);
    }
    /** 在p1字符串的后端填充p3，使之长度达到p2 */
    public static SqlExpr rpad(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.RPAD,param1,param2,param3);
    }
    /** 从p1字符串的p2开始，长度为p3的子串，p2为1时指的是从第1个字符开始 */
    public static SqlExpr mid(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.MID,param1,param2,param3);
    }
    /** 与mid完全相同 */
    public static SqlExpr substr(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.SUBSTR,param1,param2,param3);
    }
    /** 与SUBSTR完全相同 */
    public static SqlExpr substring(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.SUBSTRING,param1,param2,param3);
    }
    /** p1为“a,b,c”，p2为“,”，p3为2则返回“a,b”，p3为-2则返回“b,c” */
    public static SqlExpr substringIndex(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.SUBSTRING_INDEX,param1,param2,param3);
    }
    /** 将p1字符串重复p2次 */
    public static SqlExpr repeat(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.REPEAT,param1,param2);
    }
    /** 将p3字符串替换p1字符串中的p2子串 */
    public static SqlExpr replace(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.REPLACE,param1,param2,param3);
    }
    /** 反转字符串 */
    public static SqlExpr reverse(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.REVERSE,param1);
    }
    /** 返回p1字符串的后p2个字符 */
    public static SqlExpr right(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.RIGHT,param1,param2);
    }
    /** 返回p1字符串的前p2个字符 */
    public static SqlExpr left(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.LEFT,param1,param2);
    }
    /** 返回p1个空格 */
    public static SqlExpr space(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SPACE,param1);
    }
    /** 字符串比较，p1=p2返回0，p1>p2返回1，p1<p2返回-1 */
    public static SqlExpr strcmp(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.strcmp,param1,param2);
    }
    /** 除掉字符串前后空格 */
    public static SqlExpr trim(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.TRIM,param1);
    }
    /** 除掉字符串后端空格 */
    public static SqlExpr rtrim(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.RTRIM,param1);
    }
    /** 除掉字符串前端空格 */
    public static SqlExpr ltrim(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LTRIM,param1);
    }
    /** 将字符串转换成大写 */
    public static SqlExpr ucase(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.UCASE,param1);
    }
    /** 将字符串转换成大写 */
    public static SqlExpr upper(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.UPPER,param1);
    }
    /** 将字符串转换成小写 */
    public static SqlExpr lcase(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LCASE,param1);
    }
    /** 将字符串转换成小写 */
    public static SqlExpr lower(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LOWER,param1);
    }
    /** 使用逗号作为间隔符连接表字段多行值；聚合函数 */
    public static SqlExpr groupConcat(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.GROUP_CONCAT, param1);
    }
    /** 将多个参数组合成一个json数组 */
    public static SqlExpr jsonArray(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.JSON_ARRAY, params);
    }
    /** 将多个参数组合成一个json对象 */
    public static SqlExpr jsonObject(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.JSON_OBJECT, params);
    }
    /** 返回 p1 的绝对值 */
    public static SqlExpr abs(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ABS,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度），p1 为一个数值 */
    public static SqlExpr acos(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.ACOS,param1,param2);
    }
    /** 求反正弦值（单位为弧度），p1 为一个数值*/
    public static SqlExpr asin(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ASIN,param1);
    }
    /** 求反正切值（单位为弧度），p1 为一个数值*/
    public static SqlExpr atan(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ATAN,param1);
    }
    /** 求反正切值（单位为弧度）*/
    public static SqlExpr atan2(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.ATAN2,param1,param2);
    }
    /** 返回一个表达式的平均值，p1是一个字段*/
    public static SqlExpr avg(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.AVG,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static SqlExpr ceil(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.CEIL,param1);
    }
    /** 返回大于或等于 p1 的最小整数*/
    public static SqlExpr ceiling(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.CEILING,param1);
    }
    /** 求 p1 的反余弦值（单位为弧度），p1 为一个数值 */
    public static SqlExpr cos(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.COS,param1);
    }
    /** 求余切值(参数是弧度)*/
    public static SqlExpr cot(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.COT,param1);
    }
    /** 返回查询的记录总数，p1 参数是一个字段或者 * 号 */
    public static SqlExpr count(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.COUNT,param1);
    }
    /** 将弧度转换为角度 */
    public static SqlExpr degrees(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DEGREES,param1);
    }
    /** 返回 e 的 p1 次方 */
    public static SqlExpr exp(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.EXP,param1);
    }
    /** 返回小于或等于 p1 的最大整数 */
    public static SqlExpr floor(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.FLOOR,param1);
    }
    /** 返回列表中的最大值 */
    public static SqlExpr greatest(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.GREATEST,params);
    }
    /** 返回列表中的最小值 */
    public static SqlExpr least(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.LEAST,params);
    }
    /** 返回p1的自然对数，以 e 为底。*/
    public static SqlExpr ln(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LN,param1);
    }
    /** 返回以e为底的p1的对数 */
    public static SqlExpr loge(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LOG,param1);
    }
    /** 返回以p1为底的p2的对数 */
    public static SqlExpr log(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.LOG_BASE,param1,param2);
    }
    /** 返回以 10 为底p1的对数*/
    public static SqlExpr log10(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LOG10,param1);
    }
    /** 返回以 2 为底p1的对数*/
    public static SqlExpr log2(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LOG2,param1);
    }
    /** 返回字段 p1 中的最大值*/
    public static SqlExpr max(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MAX,param1);
    }
    /** 返回字段 p1 中的最小值*/
    public static SqlExpr min(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MIN,param1);
    }
    /** 返回 p1 除以 p2 以后的余数*/
    public static SqlExpr mod(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.MOD_FUN,param1,param2);
    }
    /** 返回圆周率(3.141593）*/
    public static SqlExpr pi(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.PI);
    }
    /** 返回 p1 的 p2 次方 */
    public static SqlExpr pow(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.POW,param1,param2);
    }
    /** 返回 p1 的 p2 次方 */
    public static SqlExpr power(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.POWER,param1,param2);
    }
    /** 将角度转换为弧度 */
    public static SqlExpr radians(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.RADIANS,param1);
    }
    /** 返回 0 到 1 的随机数 */
    public static SqlExpr rand(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.RAND);
    }
    /** 返回离 p1 最近的整数 */
    public static SqlExpr round(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ROUND,param1);
    }
    /** 返回 p1 的符号，p1 是负数、0、正数分别返回 -1、0 和 1 */
    public static SqlExpr sign(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SIGN,param1);
    }
    /** 求反正弦值（单位为弧度），p1 为一个数值*/
    public static SqlExpr sin(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SIN,param1);
    }
    /** 返回p1的平方根 */
    public static SqlExpr sqrt(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SQRT,param1);
    }
    /** 返回指定字段的总和 */
    public static SqlExpr sum(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SUM,param1);
    }
    /** 求反正切值（单位为弧度），p1 为一个数值*/
    public static SqlExpr tan(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.TAN,param1);
    }
    /** 返回数值 p1 保留到小数点后 p2 位的值（与 ROUND 最大的区别是不会进行四舍五入） */
    public static SqlExpr truncate(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.TRUNCATE,param1,param2);
    }
    /** 将p1数字从p2进制转换成p3进制，CONV(15, 10, 2) 返回 1111 */
    public static SqlExpr conv(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.CONV,param1,param2,param3);
    }
    /** 返回p1的二进制编码 */
    public static SqlExpr bin(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.BIN,param1);
    }
    /** p1日期加上一个p2间隔时间 , p3为间隔时间单位，类型为TimeField */
    public static SqlExpr dateAdd(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.DATE_ADD,param1,param2,param3);
    }
    /** 计算起始日期 p1 加上 p2 天的日期 */
    public static SqlExpr adddate(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.ADDDATE,param1,param2);
    }
    /** p1日期时间加上p2秒 */
    public static SqlExpr addtime(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.ADDTIME,param1,param2);
    }
    /** 返回当前日期 */
    public static SqlExpr curdate(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.CURDATE);
    }
    /** 返回当前日期 */
    public static SqlExpr currentDate(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.CURRENT_DATE);
    }
    /** 返回当前时间 */
    public static SqlExpr currentTime(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.CURRENT_TIME);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr currentTimestamp(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.CURRENT_TIMESTAMP);
    }
    /** 返回当前时间 */
    public static SqlExpr curtime(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.CURTIME);
    }
    /** 从日期或日期时间表达式中提取日期值 */
    public static SqlExpr date(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DATE,param1);
    }
    /** 计算日期 d1->d2 之间相隔的天数	 */
    public static SqlExpr datediff(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.DATEDIFF,param1,param2);
    }
    /** 按表达式p2的要求显示日期p1 */
    public static SqlExpr dateFormat(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.DATE_FORMAT,param1,param2);
    }
    /** 函数从p1日期减去p2(数值)和p3(单位TimeField)指定的时间间隔。 */
    public static SqlExpr dateSub(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.DATE_SUB,param1,param2,param3);
    }
    /** 返回日期p1的日期部分 */
    public static SqlExpr day(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DAY,param1);
    }
    /** 返回日期p1是星期几，如 Monday,Tuesday */
    public static SqlExpr dayname(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DAYNAME,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static SqlExpr dayofmonth(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DAYOFMONTH,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static SqlExpr dayofweek(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DAYOFWEEK,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static SqlExpr dayofyear(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.DAYOFYEAR,param1);
    }
    /** 从日期p1中获取指定的值，p2(TimeField)指定返回的值。 */
    public static SqlExpr extract(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.EXTRACT,param1,param2);
    }
    /** 计算从 0000 年 1 月 1 日开始p1天后的日期 */
    public static SqlExpr fromDays(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.FROM_DAYS,param1);
    }
    /** 返回p1(HH:mm:ss)中的小时值 */
    public static SqlExpr hour(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.HOUR,param1);
    }
    /** 返回给给定日期的那一月份的最后一天 */
    public static SqlExpr lastDay(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.LAST_DAY,param1);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr localtime(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.LOCALTIME);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr localtimestamp(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.LOCALTIMESTAMP);
    }
    /** 使用p1(year)和p2(day-of-year)组合一个日期 */
    public static SqlExpr MAKEDATE(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.MAKEDATE,param1,param2);
    }
    /** 组合时间，参数分别为小时、分钟、秒 */
    public static SqlExpr maketime(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.MAKETIME,param1,param2,param3);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static SqlExpr microsecond(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MICROSECOND,param1);
    }
    /** 返回p1(HH:mm:ss)中的分钟值 */
    public static SqlExpr minute(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MINUTE,param1);
    }
    /** 返回日期当中的月份名称，如 November */
    public static SqlExpr monthname(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MONTHNAME,param1);
    }
    /** 计算日期p1是本月的第几天 */
    public static SqlExpr month(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.MONTH,param1);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr now(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.NOW);
    }
    /** 为[年月]组合日期添加一个时段，PERIOD_ADD(201703, 5) 返回 201708 */
    public static SqlExpr periodAdd(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.PERIOD_ADD,param1,param2);
    }
    /** 返回两个[年月]日期的月份差值，PERIOD_DIFF(201710, 201703) 返回 7 */
    public static SqlExpr periodDiff(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.PERIOD_DIFF,param1,param2);
    }
    /** 返回日期p1是第几季节，返回 1 到 4 */
    public static SqlExpr quarter(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.QUARTER,param1);
    }
    /** 返回日期参数所对应的微秒数，MICROSECOND("2017-06-20 09:34:00.000023"); 返回 23 */
    public static SqlExpr second(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SECOND,param1);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static SqlExpr secToTime(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.SEC_TO_TIME,param1);
    }
    /** 将p1字符串按p2格式转变为日期 */
    public static SqlExpr strToDate(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.STR_TO_DATE,param1,param2);
    }
    /** 日期 p1 减去 p2 天后的日期 */
    public static SqlExpr subdate(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.SUBDATE,param1,param2);
    }
    /** 时间 p1 减去 p2 秒的时间 */
    public static SqlExpr subtime(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.SUBTIME,param1,param2);
    }
    /** 返回当前日期和时间 */
    public static SqlExpr sysdate(Object alias){
        return SqlExprBuilder.build(alias, ExprEnum.SYSDATE);
    }
    /** 将以秒为单位的时间p1转换为HH:mm:ss的格式，SEC_TO_TIME(4320) 返回 01:12:00 */
    public static SqlExpr time(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.TIME,param1);
    }
    /** 按p2格式显示p1时间 */
    public static SqlExpr timeFormat(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.TIME_FORMAT,param1,param2);
    }
    /** 将时间转换为秒 */
    public static SqlExpr timeToSec(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.TIME_TO_SEC,param1);
    }
    /** 计算p1时间与p2时间的时间差，p1要不小于p2，否则返回null；TIMEDIFF("13:10:11", "13:10:10") 返回 00:00:01 */
    public static SqlExpr timediff(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.TIMEDIFF,param1,param2);
    }
    /** 将p1日期时间加上p2时间，TIMESTAMP('2001-01-11','12:00:00')返回2001-01-11 12:12:12；TIMESTAMP('2001-01-11 12:00:00','2:00:00')返回2001-01-11 14:00:00 */
    public static SqlExpr timestamp(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.TIMESTAMP,param1,param2);
    }
    /** 计算时间差，返回 p3 − p2 的时间差，p1指定时差单位 */
    public static SqlExpr timestampdiff(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.TIMESTAMPDIFF,param1,param2,param3);
    }
    /** 计算p1日期距离 0000 年 1 月 1 日的天数 */
    public static SqlExpr toDays(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.TO_DAYS,param1);
    }
    /** 日期p1今天是星期几，1 星期日，2 星期一，以此类推 */
    public static SqlExpr week(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.WEEK,param1);
    }
    /** p1日期是星期几，0 表示星期一，1 表示星期二 */
    public static SqlExpr weekday(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.WEEKDAY,param1);
    }
    /** 计算p1日期是本年的第几个星期（从1开始；不存在第0周，因为被认为是上一年的最后一周），以周1作为一周的第一天，计算方式：过了几次周1则为第几周 */
    public static SqlExpr weekofyear(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.WEEKOFYEAR,param1);
    }
    /** 计算日期p1是本年的第几天 */
    public static SqlExpr year(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.YEAR,param1);
    }
    /** 返回年份和第几周（从1开始，计算方式：过了几次p2指定的星期则为第几周；不存在第0周，因为被认为是上一年的最后一周），如 YEARWEEK('2022-01-10',1) 返回 202202，p2用于指定以周几作为一周的开始，默认0,即以周日作为一周的第一天 */
    public static SqlExpr yearweek(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.YEARWEEK,param1);
    }
    /** 判断表达式是否为 NULL，是返回1，不是返回0 */
    public static SqlExpr isnull(Object alias,Object param1){
        return SqlExprBuilder.build(alias, ExprEnum.ISNULL,param1);
    }
    /** 如果p1的值不为 NULL，则返回p1，否则返回p2。 */
    public static SqlExpr ifnull(Object alias,Object param1,Object param2){
        return SqlExprBuilder.build(alias, ExprEnum.IFNULL,param1,param2);
    }
    /** 如果表达式p1成立，返回结果p2；否则，返回结果p3。 */
    public static SqlExpr ifExpr(Object alias,Object param1,Object param2,Object param3){
        return SqlExprBuilder.build(alias, ExprEnum.IF,param1,param2,param3);
    }
    /** 返回参数中的第一个非空表达式（从左向右） */
    public static SqlExpr coalesce(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.COALESCE,params);
    }
    /** case表达式 */
    public static SqlExpr caseSwitch(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.CASE_SWITCH,params);
    }
    /** case表达式 */
    public static SqlExpr caseCondition(Object alias,Object ... params){
        return SqlExprBuilder.build(alias, ExprEnum.CASE_CONDITION,params);
    }

    /** 分组排序计算行级 */
    public static SqlExpr overRank(Object alias, List<SqlField> partitions,List<OrderItem> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.RANK ,partitions,orders);
    }
    /** 关联其它行 */
    public static SqlExpr overLag(Object alias,List<SqlField> partitions,List<OrderItem> orders,SqlField sqlField,Object param1,Object param2){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.LAG ,partitions,orders,sqlField,param1,param2);
    }
    /** 添加行号 */
    public static SqlExpr overRow(Object alias, List<SqlField> partitions,List<OrderItem> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.ROW_NUMBER ,partitions,orders);
    }
    /** 计算（行级/分区总行数） */
    public static SqlExpr overPercent(Object alias, List<SqlField> partitions,List<OrderItem> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.PERCENT_RANK ,partitions,orders);
    }
    /** 平均分配桶号 */
    public static SqlExpr overNtile(Object alias,List<SqlField> partitions,List<OrderItem> orders,Object param){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.NTILE ,partitions,orders,param);
    }
    /** 查询第n大的表达式 */
    public static SqlExpr overNthValue(Object alias,List<SqlField> partitions,List<OrderItem> orders,SqlField sqlField,Object param){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.NTH_VALUE ,partitions,orders,sqlField,param);
    }
    /** 关联其它行 */
    public static SqlExpr overLead(Object alias,List<SqlField> partitions,List<OrderItem> orders,SqlField sqlField,Object param1,Object param2){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.LEAD ,partitions,orders,sqlField,param1,param2);
    }
    /** 设置首行表达式 */
    public static SqlExpr overFirst(Object alias,List<SqlField> partitions,List<OrderItem> orders,SqlField sqlField){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.FIRST_VALUE ,partitions,orders,sqlField);
    }
    /** 设置尾行表达式 */
    public static SqlExpr overLast(Object alias,List<SqlField> partitions,List<OrderItem> orders,SqlField sqlField){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.LAST_VALUE ,partitions,orders,sqlField);
    }
    /** 分组排序计算行级，类似rank，区别是行级是连续的 */
    public static SqlExpr overDenseRank(Object alias, List<SqlField> partitions,List<OrderItem> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.DENSE_RANK ,partitions,orders);
    }
    /** 分组排序计算行级，类似rank，区别是行级是连续的 */
    public static SqlExpr overCumeDist(Object alias, List<SqlField> partitions,List<OrderItem> orders){
        return SqlExprBuilder.buildWindowFunctionExpr(alias, ExprEnum.CUME_DIST ,partitions,orders);
    }
}
