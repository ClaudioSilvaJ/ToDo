package org.example.configs;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // Converte ServletResponse em HttpServletResponse
        HttpServletResponse response = (HttpServletResponse) res;

        // Configurar os cabeçalhos CORS para permitir solicitações de qualquer origem
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Continuar com a cadeia de filtros
        chain.doFilter(req, response);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
