package com.concurrent.program.in.action;

/**
 * @author 加多，微信公众号：技术原始积累
 * Created on 2020-08-29
 */
//public class HelloWorldExample extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    static class LocalVariable {
//        private Long[] a = new Long[1024 * 1024 * 100];
//    }
//
//    //(1)
//    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        //(2)
//        localVariable.set(new LocalVariable());
//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//
//        out.println("<html>");
//        out.println("<head>");
//
//        out.println("<title>" + "title" + "</title>");
//        out.println("</head>");
//        out.println("<body bgcolor=\"white\">");
//        //(3)
//        out.println(this.toString());
//        //(4)
//        out.println(Thread.currentThread().toString());
//
//        out.println("</body>");
//        out.println("</html>");
//    }
//}
