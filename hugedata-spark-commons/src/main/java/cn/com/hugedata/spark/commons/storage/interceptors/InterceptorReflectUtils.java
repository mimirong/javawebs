package cn.com.hugedata.spark.commons.storage.interceptors;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类.
 */
public class InterceptorReflectUtils {
    /**
     * logger.
     */
    private static Logger logger = LoggerFactory.getLogger(InterceptorReflectUtils.class);

    /**
     * 需要排除的以get开头的方法，如getClass()，这些方法不作为属性处理.
     */
    private static Map<String, Object> excludedGetters = new HashMap<String, Object>();

    /**
     * 初始化.
     */
    static {
        excludedGetters.put("getClass", new Object());
        excludedGetters.put("getTag", new Object());
        excludedGetters.put("getTableId", new Object());
    }

    /**
     * 设置和获取属性值.
     * 
     * @param obj
     *            需要设置属性的对象
     * @param fieldName
     *            属性名
     * @param fieldVal
     *            属性值
     * @param type
     *            类型("set", "get")
     * @return 属性值
     */
    private static Object operate(Object obj, String fieldName, Object fieldVal, String type) {
        Object ret = null;
        try {
            // 获得对象类型
            Class<? extends Object> classType = obj.getClass();
            // 获得对象的所有属性

            Field fields[] = classType.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.getName().equals(fieldName)) {

                    String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和属性对应的getXXX()方法的名字

                    if ("set".equals(type)) {
                        String setMethodName = "set" + firstLetter + fieldName.substring(1); // 获得和属性对应的getXXX()方法
                        Method setMethod = classType.getMethod(setMethodName, new Class[] { field.getType() }); // 调用原对象的getXXX()方法
                        ret = setMethod.invoke(obj, new Object[] { fieldVal });
                    }
                    if ("get".equals(type)) {
                        String getMethodName = "get" + firstLetter + fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字

                        Method getMethod = classType.getMethod(getMethodName, new Class[] {});
                        ret = getMethod.invoke(obj, new Object[] {});
                    }
                    return ret;
                }
            }
        } catch (Exception e) {
            logger.warn("reflect error:" + fieldName, e);
        }
        return ret;
    }

    /**
     * 获取属性值.
     * 
     * @param obj
     *            需要设置属性的对象
     * @param fieldName
     *            属性名称
     * 
     * @return 属性值
     * 
     */
    public static Object getVal(Object obj, String fieldName) {
        return operate(obj, fieldName, null, "get");
    }

    /**
     * 设置属性值.
     * 
     * @param obj
     *            需要设置属性的对象
     * @param fieldName
     *            属性名称
     * 
     * @param fieldVal
     *            属性值
     * 
     */
    public static void setVal(Object obj, String fieldName, Object fieldVal) {
        operate(obj, fieldName, fieldVal, "set");
    }

    /**
     * 从一个object获取方法信息.
     * 
     * @param object
     *            需要获取方法的对象
     * @param methodName
     *            方法名
     * 
     * @param parameterTypes
     *            参数类型
     * @return 方法信息
     */
    private static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                // superClass.getMethod(methodName, parameterTypes);
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                logger.error("Method not found.", e);
            }
        }

        return null;
    }

    /**
     * 将字段设为可改变(public).
     * 
     * @param field
     *            字段
     */
    private static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 获取指定对象的字段信息.
     * 
     * @param object
     *            需要获取字段信息的对象
     * @param filedName
     *            字段名
     * 
     * @return 字段信息
     */
    private static Field getDeclaredField(Object object, String filedName) {
        for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
                logger.debug("Field not found");
            }
        }
        return null;
    }

    /**
     * 调用方法.
     * 
     * @param object
     *            需要调用方法的对象
     * @param methodName
     *            方法名
     * 
     * @param parameterTypes
     *            参数类型
     * @param parameters
     *            参数
     * @return 返回值
     * 
     * @throws InvocationTargetException
     *             调用失败
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters)
            throws InvocationTargetException {
        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }

        method.setAccessible(true);

        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException e) {
            logger.error("Failed to invoke method.", e);
        }

        return null;
    }

    /**
     * 设置字段值.
     * 
     * @param object
     *            需要设置字段值的对象.
     * @param fieldName
     *            字段名.
     * @param value
     *            字段值.
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字段值.
     * 
     * @param object
     *            需要获取字段值的对象
     * @param fieldName
     *            字段名
     * 
     * @return 字段值
     * 
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 判断一个方法是否为一个属性的getter方法.
     * 
     * @param m
     *            需要判断的方法
     * @return 是否为getter方法
     */
    public static boolean isValidGetter(Method m) {
        // 不以get开头的方法不是getter方法
        if (!m.getName().startsWith("get")) {
            return false;
        }

        // 包含参数的get方法不是getter方法
        if (m.getParameterTypes().length > 0) {
            return false;
        }

        // 部分特殊的get方法不是属性，如getClass()等
        // 修改静态初始化增加排除的方法列表
        if (excludedGetters.containsKey(m.getName())) {
            return false;
        }

        return true;
    }
}
