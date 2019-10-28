package cn.com.hugedata.spark.commons.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于创建错误页面的工具类.
 * 使用方法:
 *     <code>
 *         return new ErrorPageBuilder().setActiveNavTab("user")
 *                                      .setErrorTitle("错误标题")
 *                                      .setErrorMessage("错误消息")
 *                                      .addErrorHint(ErrorPageBuilder.Hints.RETURN_TO_HOME)
 *                                      .create();
 *     </code>
 * 
 * @author 高鹏
 */
public class ErrorPageBuilder {
    
    /** 保存Spring MessageSource用于获取消息. */
    private MessageSource messageSource;
    
    /** 保存设置的导航栏活动标签. */
    private String activeNavTab;
    
    /** 保存设置的错误标题. */
    private String errorTitle;
    
    /** 保存设置的错误消息. */
    private String errorMessage;
    
    /** 保存设置的提示操作列表. */
    private List<ErrorHint> hints;

    /**
     * 创建ErrorPageBuilder用于生成显示错误信息的ModelAndView.
     */
    public ErrorPageBuilder() {
    }

    /**
     * 创建ErrorPageBuilder用于生成显示错误信息的ModelAndView.
     * @param messageSource Spring Message Source
     */
    public ErrorPageBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    /**
     * 创建用于Controller返回的ModelAndView.
     * @return ModelAndView
     */
    public ModelAndView create() {
        if (StringUtils.isEmpty(errorTitle)) {
            throw new RuntimeException("Property errorTitle must not be empty.");
        }
        if (StringUtils.isEmpty(errorMessage)) {
            throw new RuntimeException("Property errorMessage must not be empty.");
        }
        
        ModelAndView mv = new ModelAndView("/error/error");
        mv.addObject("activeNavTab", activeNavTab);
        mv.addObject("errorTitle", errorTitle);
        mv.addObject("errorMessage", errorMessage);
        mv.addObject("hints", hints);
        return mv;
    }

    
    /**
     * 设置错误页面活动的导航标签.
     * @param tab 导航标签ID：{@link ServerConstants.Navigation}
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setActiveNavTab(String tab) {
        this.activeNavTab = tab;
        return this;
    }

    /**
     * 设置错误标题文本.
     * @param title 错误标题文本
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorTitleText(String title) {
        this.errorTitle = title;
        return this;
    }

    /**
     * 从MessageSource读取并设置错误标题文本.
     * @param code 错误标题文本key
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorTitleTextCode(String code) {
        this.errorTitle = messageSource.getMessage(code, null, null);
        return this;
    }

    /**
     * 从MessageSource读取并设置错误标题文本.
     * @param code 错误标题文本key
     * @param args 错误标题文本参数
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorTitleTextCode(String code, Object[] args) {
        this.errorTitle = messageSource.getMessage(code, args, null);
        return this;
    }

    /**
     * 设置错误消息.
     * @param message 错误消息
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorMessageText(String message) {
        this.errorMessage = message;
        return this;
    }

    /**
     * 从MessageSource读取并设置错误消息.
     * @param code 错误消息key
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorMessageTextCode(String code) {
        this.errorMessage = messageSource.getMessage(code, null, null);
        return this;
    }

    /**
     * 从MessageSource读取并设置错误消息.
     * @param code 错误消息key
     * @param args 错误消息参数
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder setErrorMessageTextCode(String code, Object[] args) {
        this.errorMessage = messageSource.getMessage(code, args, null);
        return this;
    }

    
    /**
     * 增加建议的操作.
     * @param hint 预定义的操作信息
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder addHint(ErrorHint hint) {
        if (hints == null) {
            hints = new ArrayList<ErrorHint>();
        }
        hints.add(hint);
        return this;
    }
    
    /**
     * 增加建议的操作.
     * @param message 操作消息文本
     * @param link    操作链接 
     * @return ErrorPageBuilder
     */
    public ErrorPageBuilder addHint(String message, String link) {
        if (hints == null) {
            hints = new ArrayList<ErrorHint>();
        }
        
        ErrorHint hi = new ErrorHint();
        hi.message = message;
        hi.link = link;
        hints.add(hi);
        return this;
    }
    
    
    
    //------------------------------------------------------------------------------------------------------------------
    // class HintInfo
    //------------------------------------------------------------------------------------------------------------------

    /**
     * 保存一条提示操作的信息.
     */
    public static class ErrorHint {
        /** 提示消息，其中{}包含的文本将显示为链接. */
        private String message;
        
        /** 链接URL. */
        private String link;
        
        /**
         * 创建一个错误提示.
         */
        public ErrorHint() {
			super();
		}

        /**
         * 使用指定的错误消息和链接创建错误提示.
         * @param message 错误消息
         * @param link    错误消息的链接
         */
		public ErrorHint(String message, String link) {
			super();
			this.message = message;
			this.link = link;
		}

		/**
         * 获取链接之前的文本.
         * @return 链接之前的文本
         */
        public String getMessagePartBeforeLink() {
            int pos = message.indexOf('{');
            if (pos < 0) {
                return "";
            }
            return message.substring(0, pos);
        }
        
        /**
         * 获取链接之后的文本.
         * @return 链接之后的文本
         */
        public String getMessagePartAfterLink() {
            int pos = message.indexOf('}');
            if (pos < 0) {
                return "";
            }
            return message.substring(pos + 1);
        }

        /**
         * 获取链接文本.
         * @return 链接文本
         */
        public String getLinkText() {
            int pos1 = message.indexOf('{');
            int pos2 = message.indexOf('}');
            if (pos1 < 0 || pos2 < 0) {
                return "";
            }
            return message.substring(pos1 + 1, pos2);
        }

        public String getMessage() {
            return message;
        }

        public String getLink() {
            return link;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
