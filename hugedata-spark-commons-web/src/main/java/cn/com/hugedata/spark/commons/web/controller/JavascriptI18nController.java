package cn.com.hugedata.spark.commons.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;

import cn.com.hugedata.spark.commons.web.i18n.HugedataResourceBundleMessageSource;

@Controller(value = "commonsWebJavascriptI18nController")
@RequestMapping("/commons-web/jsi")
public class JavascriptI18nController {
    
    private static final String NEW_LINE = "\r\n";
    
    private static final String NEW_LINE_FORMAT = "\r\n";
    
    @Autowired
    private MessageSource messageSource;
    
    /**
     * 缓存的JS消息文件.
     */
    private ConcurrentHashMap<Locale, String> cachedJsFiles = new ConcurrentHashMap<Locale, String>();
    
    @RequestMapping(value = "")
    @ResponseBody
    public ResponseEntity<byte[]> jsi(
            HttpServletRequest request,
            @RequestParam(value = "reload", defaultValue = "false") boolean reload) {
        try {
            // 获取语言
            Locale locale = RequestContextUtils.getLocale(request);
            
            // 从缓存获取文本
            String content = cachedJsFiles.get(locale);
            if (content == null || reload) {
                // 获取MessageSource
                HugedataResourceBundleMessageSource zms = null;
                if (messageSource instanceof HugedataResourceBundleMessageSource) {
                    zms = (HugedataResourceBundleMessageSource) messageSource;
                } else if (messageSource instanceof DelegatingMessageSource) {
                    DelegatingMessageSource dms = (DelegatingMessageSource) messageSource;
                    if (dms.getParentMessageSource() instanceof HugedataResourceBundleMessageSource) {
                        zms = (HugedataResourceBundleMessageSource) dms.getParentMessageSource();
                    }
                }
                if (zms == null) {
                    content = "// NO MESSAGE SOURCE";
                    content += "function JSI() { return ''; }";
                }
                
                // 获取所有国际化文本
                Properties props = zms.getAllProperties(locale);
                
                // 输出JS文件
                StringBuilder js = new StringBuilder();
                
                // 输出JS文件: 首行注释
                String languageCode = messageSource.getMessage("commons.language_code", null, locale);
                js.append("// HUGEDATA JSI18N LANGUAGE: ").append(languageCode).append(NEW_LINE);
                
                // JS文件基本结构：
                //     var JSI = (function() {
                //         var m = {};
                //         m["code"] = "message_format";
                //
                //         function getMessage(code) {
                //             if (!code || code == '') {
                //                 return '';
                //             }
                //
                //             var ret = m[code];
                //             ret = (ret ? ret : '');
                //
                //             if (arguments.length == 1) {
                //                 return ret;
                //             }
                //
                //             for (var i = 1; i < arguments.length; i++) {
                //                 ret = ret.replace(new RegExp('\\{' + (i-1) + '\\}', 'g'), '' + arguments[i]);
                //             }
                //             return ret;
                //         }
                //         return getMessage;
                //     })();
                
                // 输出JS文件
                js.append("var JSI = (function() {");
                js.append(NEW_LINE_FORMAT);
                js.append("  var m = {};");
                js.append(NEW_LINE_FORMAT);
                for (Entry<Object, Object> e : props.entrySet()) {
                    if (!(e.getKey() instanceof String && e.getValue() instanceof String)) {
                        continue;
                    }
                    js.append("m['").append(e.getKey()).append("']=").append(JSON.toJSONString(e.getValue())).append(";");
                    js.append(NEW_LINE_FORMAT);
                }
                js.append("  function getMessage(code) {");
                js.append(NEW_LINE_FORMAT);
                js.append("    if (!code || code == '') { return ''; }");
                js.append(NEW_LINE_FORMAT);
                js.append("    var ret = m[code];");
                js.append(NEW_LINE_FORMAT);
                js.append("    ret = (ret ? ret : '');");
                js.append(NEW_LINE_FORMAT);
                js.append("    if (arguments.length == 1) { return ret; }");
                js.append(NEW_LINE_FORMAT);
                js.append("    for (var i = 1; i < arguments.length; i++) {");
                js.append(NEW_LINE_FORMAT);
                js.append("        ret = ret.replace(new RegExp('\\\\{' + (i-1) + '\\\\}', 'g'), '' + arguments[i]);");
                js.append(NEW_LINE_FORMAT);
                js.append("    }");
                js.append(NEW_LINE_FORMAT);
                js.append("    return ret;");
                js.append("  }");
                js.append(NEW_LINE_FORMAT);
                js.append("  return getMessage;");
                js.append(NEW_LINE_FORMAT);
                js.append("})();");
                js.append(NEW_LINE_FORMAT);
                
                content = js.toString();
                cachedJsFiles.putIfAbsent(locale, content);
            }

            // 输出JS文件
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/javascript;charset=UTF-8");
            return new ResponseEntity<byte[]>(content.getBytes("UTF-8"), headers, HttpStatus.OK);
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
