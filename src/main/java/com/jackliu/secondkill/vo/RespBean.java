package com.jackliu.secondkill.vo;

/**
 * 公共返回对象
 *
 * @author: LC
 * @date 2022/3/2 1:50 下午
 * @ClassName: RespBean
 */
public class RespBean {

    private long code;
    private String message;
    private Object object;

    public static RespBean success() {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }
    //重载
    public static RespBean success(Object object) {
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), object);
    }

    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    public static RespBean error(RespBeanEnum respBeanEnum, Object object) {
        return new RespBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), object);
    }

    //全参构造和无惨构造，也可是使用注解@No。。。@All来定义
    public RespBean(long code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
    public RespBean(){

    }

    //使用注解@Data来代替这下get和set方法
    // @Data是Lombok库中的一个注解，它结合了类中常见的注解，例如@Getter、@Setter、@ToString、@EqualsAndHashCode以及
    // @RequiredArgsConstructor
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
