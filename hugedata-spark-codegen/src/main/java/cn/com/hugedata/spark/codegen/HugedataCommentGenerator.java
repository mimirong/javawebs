package cn.com.hugedata.spark.codegen;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class HugedataCommentGenerator implements CommentGenerator {

    @Override
    public void addConfigurationProperties(Properties properties) {
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable tbl, IntrospectedColumn col) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + field.getName());
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        //{@link #platformId}
        
        String mname = method.getName();
        if(mname.startsWith("get")){
            mname = mname.substring("get".length());
            mname = mname.substring(0, 1).toLowerCase() + mname.substring(1);
        }else if(mname.startsWith("is")){
            mname = mname.substring("is".length());
            mname = mname.substring(0, 1).toLowerCase() + mname.substring(1);
        }
        
        method.addJavaDocLine("/**");
        
        StringBuilder buf = new StringBuilder();
        buf.append(" * ");
        buf.append("{@link #");
        buf.append(mname);
        buf.append("}");
        method.addJavaDocLine(buf.toString());
        
        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        String mname = method.getName();
        if(mname.startsWith("set")){
            mname = mname.substring("set".length());
            mname = mname.substring(0, 1).toLowerCase() + mname.substring(1);
        }
        
        method.addJavaDocLine("/**");
        
        StringBuilder buf = new StringBuilder();
        buf.append(" * ");
        buf.append("{@link #");
        buf.append(mname);
        buf.append("}");
        method.addJavaDocLine(buf.toString());
        
        method.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        
    }

}
