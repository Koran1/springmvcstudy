/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.96
 * Generated at: 2024-10-17 11:53:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.day04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajax_005fresult_005fjson_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Ajax - JSON</title>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("	table{width: 100%; border-collapse: collapse;}\r\n");
      out.write("	table, th, td{border: 1px solid black; text-align: center;}\r\n");
      out.write("	td{padding: 5px;}\r\n");
      out.write("	img{width: 100px; height: 100px;}\r\n");
      out.write("	#taMin{color: blue;}\r\n");
      out.write("	#taMax{color: red;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<button id=\"btn1\">json 테스트 (수제)</button>\r\n");
      out.write("    <button id=\"btn2\">json 테스트 (makeup)</button>\r\n");
      out.write("    <button id=\"btn3\">json 테스트 (공공데이터)</button>\r\n");
      out.write("    <hr>\r\n");
      out.write("    \r\n");
      out.write("    <div id=\"result\"></div>\r\n");
      out.write("    \r\n");
      out.write("	<script type=\"text/javascript\">\r\n");
      out.write("		$(\"#btn1\").click(function() {\r\n");
      out.write("			$(\"#result\").empty();\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/test05\",		// 서버 주소\r\n");
      out.write("				method: \"post\",			// 전달 방식\r\n");
      out.write("				dataType: \"json\",		// 가져오는 결과 데이터 타입\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let table = \"<table>\";\r\n");
      out.write("					table += \"<thead><tr><th>Name</th><th>Scope</th></tr></thead>\";\r\n");
      out.write("					table += \"<tbody>\"\r\n");
      out.write("					$.each(data, function(index, obj){\r\n");
      out.write("						let name = obj.name;\r\n");
      out.write("						let scope = obj.scope;\r\n");
      out.write("						\r\n");
      out.write("						table += \"<tr>\"\r\n");
      out.write("						table += \"<td>\" + name + \"</td>\";\r\n");
      out.write("						table += \"<td>\" + scope + \"</td>\";\r\n");
      out.write("	   					table += \"</tr>\"\r\n");
      out.write("					});\r\n");
      out.write("					\r\n");
      out.write("					table += \"</tbody>\"\r\n");
      out.write("					table += \"</table>\";\r\n");
      out.write("					$(\"#result\").append(table);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"읽기 실패!\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		$(\"#btn2\").click(function() {\r\n");
      out.write("			$(\"#result\").empty();\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/test06\",		// 서버 주소\r\n");
      out.write("				method: \"post\",			// 전달 방식\r\n");
      out.write("				dataType: \"json\",		// 가져오는 결과 데이터 타입\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let table = \"<table>\";\r\n");
      out.write("					table += \"<thead><tr><th>ID</th><th>Brand</th><th>Name</th><th>Price</th>\"+\r\n");
      out.write("					\"<th>Image</th><th>Description</th></tr></thead>\";\r\n");
      out.write("					table += \"<tbody>\"\r\n");
      out.write("					$.each(data, function(index, obj){\r\n");
      out.write("						// 최대 개수 제한\r\n");
      out.write("						if(index > 10) return false;\r\n");
      out.write("						\r\n");
      out.write("						let id = obj.id;\r\n");
      out.write("						let brand = obj.brand;\r\n");
      out.write("						let name = obj.name;\r\n");
      out.write("						let price = obj.price;\r\n");
      out.write("						let img = obj.image_link;\r\n");
      out.write("						let desc = obj.description;\r\n");
      out.write("						let prodLink = obj.product_link;\r\n");
      out.write("						\r\n");
      out.write("						table += \"<tr>\"\r\n");
      out.write("						table += \"<td>\" + id + \"</td>\";\r\n");
      out.write("						table += \"<td>\" + brand + \"</td>\";\r\n");
      out.write("						table += \"<td><a href=\\\"\" + prodLink + \"\\\">\" + name + \"</a></td>\";\r\n");
      out.write("						table += \"<td>$\" + price + \"</td>\";\r\n");
      out.write("						table += \"<td><img alt=\\\"img\\\" src=\\\"\" + img + \"\\\"></td>\";\r\n");
      out.write("						table += \"<td>\" + desc + \"</td>\";\r\n");
      out.write("	   					table += \"</tr>\"\r\n");
      out.write("					});\r\n");
      out.write("					\r\n");
      out.write("					table += \"</tbody>\"\r\n");
      out.write("					table += \"</table>\";\r\n");
      out.write("					$(\"#result\").append(table);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"읽기 실패!\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		$(\"#btn3\").click(function() {\r\n");
      out.write("			$(\"#result\").empty();\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/test07\",		// 서버 주소\r\n");
      out.write("				method: \"post\",			// 전달 방식\r\n");
      out.write("				dataType: \"json\",		// 가져오는 결과 데이터 타입\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let item = data.response.body.items.item;\r\n");
      out.write("					let table = \"<table>\"\r\n");
      out.write("					table += \"<thead><tr><th>날짜</th><th>예상최저기온</th><th>예상최고기온</th></tr></thead>\";\r\n");
      out.write("					\r\n");
      out.write("					table += \"<tbody>\";\r\n");
      out.write("					\r\n");
      out.write("					$.each(item, function(index, obj) {\r\n");
      out.write("						for (let i = 3; i < 11; i++) {\r\n");
      out.write("							let f_Date = new Date();\r\n");
      out.write("							f_Date.setDate(f_Date.getDate() + (i));\r\n");
      out.write("							\r\n");
      out.write("							// 날짜 형식을 YYYY-MM-DD로 변환\r\n");
      out.write("							let r_date = f_Date.toISOString().split('T')[0];\r\n");
      out.write("							\r\n");
      out.write("							table += \"<tr>\";\r\n");
      out.write("							table += \"<td>\" + r_date + \"</td>\";\r\n");
      out.write("							table += \"<td id=\\\"taMin\\\">\" + obj[\"taMin\"+i]+ \"</td>\";\r\n");
      out.write("							table += \"<td id=\\\"taMax\\\">\" + obj[\"taMax\"+i]+ \"</td>\";\r\n");
      out.write("							table += \"</tr>\";\r\n");
      out.write("						}\r\n");
      out.write("					});\r\n");
      out.write("					table += \"</tbody>\";\r\n");
      out.write("					\r\n");
      out.write("					table += \"</table>\"\r\n");
      out.write("					$(\"#result\").append(\"<h2>서울 날씨</h2>\");\r\n");
      out.write("					$(\"#result\").append(table);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"읽기 실패!\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("	</script>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}