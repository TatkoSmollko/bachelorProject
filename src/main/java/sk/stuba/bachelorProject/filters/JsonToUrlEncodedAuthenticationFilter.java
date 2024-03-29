package sk.stuba.bachelorProject.filters;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.io.ByteStreams;


/**
 * Used for the extraction of the login credentials from json.
 *
 * @author MHR
 *
 */
@Component
public class JsonToUrlEncodedAuthenticationFilter extends OncePerRequestFilter {



    @SuppressWarnings("unchecked")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (Objects.equals(request.getServletPath(), "/oauth/token")
                && Objects.equals(request.getContentType(), "application/json")) {
            byte[] json = ByteStreams.toByteArray(request.getInputStream());

            Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);
            Map<String, String[]> parameters = jsonMap.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> new String[] { e.getValue() }));

            HttpServletRequest requestWrapper = new RequestWrapper(request, parameters);
            filterChain.doFilter(requestWrapper, response);

        } else {
            filterChain.doFilter(request, response);
        }

    }

    private class RequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String[]> params;

        RequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
            super(request);
            this.params = params;
        }

        @Override
        public String getParameter(String name) {
            if (this.params.containsKey(name)) {
                return this.params.get(name)[0];
            }
            return "";
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return this.params;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Enumerator<>(params.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }
    }


}

