package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>        \n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n");
      out.write("        <title>Sube tus Videos</title>\n");
      out.write("        <link href=\"css/Estilos_login.css\" rel=\"stylesheet\"/>\n");
      out.write("        <link href=\"css/style_button.css\" rel=\"stylesheet\" media=\"all\"/> \n");
      out.write("        <script src=\"https://kit.fontawesome.com/4e646a13f9.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("        \n");
      out.write("        <style>\n");
      out.write("            body{\n");
      out.write("                margin: 0;\n");
      out.write("                padding: 0;     \n");
      out.write("                background-image: url(\"img/Fondo_video.jpg\");\n");
      out.write("                background-size: cover;\n");
      out.write("                background-position: center;\n");
      out.write("                font-family: sans-serif;    \n");
      out.write("                background-color: #32baf3;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            .container{\n");
      out.write("                background-color:silver;\n");
      out.write("                border-radius: 10px;\n");
      out.write("                padding:25px;\n");
      out.write("                margin-top: 80px\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>             \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <div class=\"container col-lg-3\">\n");
      out.write("            <form action = \"servletUsuarios\" method =\"get\"> \n");
      out.write("                <div class = \"form-group text-center\">\n");
      out.write("                    <div class=\"form-group title_header\">Sube tus Videos Ya!</div>\n");
      out.write("                    <p><strong><br/></strong></p>\n");
      out.write("                    <img src=\"img/usuario.jpg\" height=\"80\" width=\"80\" alt=\"\"/>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class = \"form-group\">                    \n");
      out.write("                    <label>Nombre de Usuario</label>\n");
      out.write("                    <input class=\"form-control text-center\" type=\"text\" name=\"txtnom\" placeholder=\"Ingrese usuario\">                    \n");
      out.write("                </div>\n");
      out.write("                <div class = \"form-groupr\">\n");
      out.write("                     <label for=\"passwordInput\" style=\"float: left\">Contraseña</label>\n");
      out.write("                     <input type=\"password\" name=\"password\" class=\"form-control text-center\" id=\"passwordInput\" placeholder=\"************\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class = \"form-group\"></div>\n");
      out.write("                <div class = \"form-group text-center\">\n");
      out.write("                    ");
      out.write("\n");
      out.write("                    <button type=\"submit\" name=\"accion\" value =\"Ingresar\" class=\"button\"><i class=\"fa-solid fa-door-open\"></i> Ingresar</button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("            \n");
      out.write("            <form action = \"registroUsu.jsp\">\n");
      out.write("                <div class = \"form-group text-center\">\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-link mb-2 text-center\">¿No estás registrado aún?</button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("        </div>\n");
      out.write("            <p> \n");
      out.write("                <br/>\n");
      out.write("                <br/>\n");
      out.write("            </p>\n");
      out.write("        \n");
      out.write("         <div>\n");
      out.write("            <form>\n");
      out.write("                <div class=\"project-made-text text-center\">Proyecto hecho por:</div>\n");
      out.write("                <div class=\"project-names-text text-center\">Victor Carhuaricra</div>\n");
      out.write("                <div class=\"project-names-text text-center\">Benny Pérez</div>\n");
      out.write("                    <br/>\n");
      out.write("                <div class=\"project-end-text text-center\"><strong>Laboratorio ISDCM Feb 2022</strong></div>\n");
      out.write("                    \n");
      out.write("                \n");
      out.write("            </form>\n");
      out.write("                 \n");
      out.write("        </div>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
