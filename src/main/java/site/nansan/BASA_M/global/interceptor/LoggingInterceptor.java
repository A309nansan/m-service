package site.nansan.BASA_M.global.interceptor;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.nansan.BASA_M.global.log.TraceContext;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    private static final String START = "start";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object h) {

        String userId = request.getHeader("X-User-Id");
        String traceId = getTraceId(request);

        request.setAttribute(START, System.nanoTime());

        MDC.put("traceId", traceId);
        MDC.put("userId", userId);
        MDC.put("uri", request.getRequestURI());
        MDC.put("method", request.getMethod());

        LoggerFactory.getLogger("REQ").info("START");
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object h, Exception ex) {

        long ms = (System.nanoTime() - (Long) request.getAttribute(START)) / 1_000_000;
        MDC.put("latency", String.valueOf(ms));

        LoggerFactory.getLogger("REQ").info("END status={} latencyMs={}", response.getStatus(), ms);

        MDC.clear();

    }

    private String getTraceId(HttpServletRequest request){

        String traceId = request.getHeader("X-Trace-Id");
        if (traceId == null || traceId.isBlank()) traceId = UUID.randomUUID().toString();

        return traceId;

    }

}
