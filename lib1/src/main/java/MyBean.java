import org.apache.commons.lang3.StringUtils;

/**
 * @author Daniel Geißler, Salt-Solutions AG
 * @since 18.01.2021.
 */
public class MyBean {

    public String sayHello(String to){
        return StringUtils.join("Hello", to, " ");
    }
}
