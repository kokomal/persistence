package yuanjun.chen.dao.jpa.reading;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**   
 * @ClassName: ReaderHandlerMethodArgumentResolver   
 * @Description: 将参数转换成自定义的Reader，基类为Authentication  
 * @author: 陈元俊 
 * @date: 2018年10月18日 下午4:01:08  
 */
@Component
public class ReaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Reader.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication auth = (Authentication) webRequest.getUserPrincipal();
        Object ret = (auth != null && auth.getPrincipal() instanceof Reader) ? auth.getPrincipal() : null;
        return ret;
    }
}
