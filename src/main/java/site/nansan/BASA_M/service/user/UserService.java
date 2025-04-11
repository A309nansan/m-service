package site.nansan.BASA_M.service.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final HttpServletRequest request;

    public String getAuthenticatedUserId() {
        String userIdHeader = request.getHeader("X-User-Id");

        if (userIdHeader == null) {
            return null;
        }
        return userIdHeader;
    }

}
