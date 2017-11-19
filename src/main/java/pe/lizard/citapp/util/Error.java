package pe.lizard.citapp.util;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class Error {
    private static final Logger LOG = Logger.getLogger(Error.class.getName());

    public static final String ERROR_STATUS_OK = "ok";

    public static void handler(HttpServletRequest request, Exception e) {
        request.setAttribute("error_status", ERROR_STATUS_OK);
        request.setAttribute("error_message", e.getMessage());
        LOG.warning(e.getMessage());
    }
}
