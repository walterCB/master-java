    package servlets;

import javabeans.AlumnoForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Operations;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author miguel
 */
public class BuscarAlumno extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String NOTFOUND = "notfound";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String driver = this.getServlet().getServletContext().getInitParameter("driver");
        String cadenacon = this.getServlet().getServletContext().getInitParameter("cadenacon");
        Operations ops = new Operations(driver, cadenacon);
        AlumnoForm alumno = (AlumnoForm) form;
        AlumnoForm alumnofound = ops.getAlumno(alumno.getDni());
        String result = SUCCESS;
        
        if (alumnofound != null)
            request.setAttribute("alumno", alumnofound);
        else
        {
            request.setAttribute("alumno", alumno);
            result = NOTFOUND;
        }
        
        
        return mapping.findForward(result);
    }
}
