package edu.czjt.reggie.filter;

import com.alibaba.fastjson.JSON;
import edu.czjt.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "cuisineFilter", urlPatterns = "/*")
@Slf4j
public class CuisineFilter implements Filter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("本次拦截到请求：{}", requestURI);

        // 判断本次请求是否需要验证
        // 不需要验证的url
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout"
        };
        boolean check = check(urls, requestURI);

        // 如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求{}不需要进行菜品类型过滤", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 获取菜品类型参数
        String cuisineType = request.getParameter("cuisineType");

        // 判断菜品类型是否为空或与过滤器要求的菜品类型匹配
        if (cuisineType == null || cuisineType.equals("中餐")) {
            // 菜品类型为空或匹配，继续处理请求
            filterChain.doFilter(request, response);
        } else {
            // 菜品类型不匹配，返回错误结果
            log.info("请求的菜品类型不符合过滤器要求");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(R.error("请求的菜品类型不符合过滤器要求")));
        }
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

    // 省略其他方法...
}
//该过滤器使用@WebFilter注解标记为一个过滤器，并指定了过滤器的名称为cuisineFilter，以及过滤的URL模式为"/*"，即对所有请求进行过滤处理。
//过滤器实现了Filter接口，并实现了doFilter方法用于处理过滤逻辑。
//在doFilter方法中，首先获取当前请求的URI，并记录日志信息。
//然后判断当前请求是否需要进行验证，定义了一个不需要验证的URL数组，包含了"/employee/login"和"/employee/logout"两个URL。
//如果当前请求不需要验证，则直接放行，调用filterChain.doFilter方法将请求传递给下一个过滤器或目标资源。
//如果当前请求需要验证，则获取请求参数中的菜品类型(cuisineType)。
//判断菜品类型是否为空或与过滤器要求的菜品类型匹配。如果为空或匹配，继续处理请求，调用filterChain.doFilter方法。
//如果菜品类型不匹配，说明请求的菜品类型不符合过滤器要求，返回一个错误结果。设置响应的字符编码为UTF-8，并通过输出流方式向客户端页面返回一个JSON格式的错误信息。
//过滤器中还定义了一个辅助方法check，用于检查当前请求的URI是否需要放行，该方法通过路径匹配的方式进行判断。