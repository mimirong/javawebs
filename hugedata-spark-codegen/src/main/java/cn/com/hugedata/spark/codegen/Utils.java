package cn.com.hugedata.spark.codegen;


public final class Utils {
    private Utils(){}
    
    public static String getEmptyStringIfNull(String str){
        return null == str ? "" : str;
    }
    
    public static String getDottedAlias(String str){
        if(null == str || "".equals(str)){
            return "";
        }
        return str + ".";
    }
}
