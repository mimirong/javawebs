package cn.com.hugedata.spark.commons.web.codeselector;

public class CodeInfo {

    private String code;
    
    private String name;
    
    private String parentCode;

    public CodeInfo() {
    }

    public CodeInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public CodeInfo(String code, String name, String parentCode) {
        this.code = code;
        this.name = name;
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    
}
