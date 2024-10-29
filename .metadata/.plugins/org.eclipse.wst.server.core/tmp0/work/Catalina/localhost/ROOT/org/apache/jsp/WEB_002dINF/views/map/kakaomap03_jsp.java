/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.96
 * Generated at: 2024-10-25 05:49:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.map;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class kakaomap03_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>KakaoMap03(마커 표시)</title>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("	let lat = \"\";\r\n");
      out.write("	let lng = \"\";\r\n");
      out.write("	navigator.geolocation.getCurrentPosition(function(position){\r\n");
      out.write("		lat = position.coords.latitude;\r\n");
      out.write("		lng = position.coords.longitude;\r\n");
      out.write("		geo_maps(lat, lng);\r\n");
      out.write("	});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h2>Kakao Map 03</h2>\r\n");
      out.write("	<h2>내 위치 마커 표시, 인포윈도우 표시</h2>\r\n");
      out.write("	<a href=\"/index\"></a>\r\n");
      out.write("	\r\n");
      out.write("	<div id=\"map\" style=\"width: 100%; height: 350px;\"></div>\r\n");
      out.write("\r\n");
      out.write("	<script type=\"text/javascript\"\r\n");
      out.write("		src=\"//dapi.kakao.com/v2/maps/sdk.js?appkey=3ab528374e287b067bf7ce3808786127\"></script>\r\n");
      out.write("	<script>\r\n");
      out.write("		function geo_maps(lat, lng){\r\n");
      out.write("			let mapContainer = document.getElementById('map'), // 지도를 표시할 div \r\n");
      out.write("			mapOption = {\r\n");
      out.write("				center : new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표\r\n");
      out.write("				level : 3\r\n");
      out.write("				// 지도의 확대 레벨 (1 ~ 14 까지 가능)\r\n");
      out.write("			};\r\n");
      out.write("	\r\n");
      out.write("			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다\r\n");
      out.write("			let map = new kakao.maps.Map(mapContainer, mapOption);\r\n");
      out.write("			\r\n");
      out.write("			// 마커가 표시될 위치입니다 \r\n");
      out.write("			let markerPosition  = new kakao.maps.LatLng(lat, lng); \r\n");
      out.write("\r\n");
      out.write("			// 마커를 생성합니다\r\n");
      out.write("			let marker = new kakao.maps.Marker({\r\n");
      out.write("			    position: markerPosition\r\n");
      out.write("			});\r\n");
      out.write("\r\n");
      out.write("			// 마커가 지도 위에 표시되도록 설정합니다\r\n");
      out.write("			marker.setMap(map);\r\n");
      out.write("			\r\n");
      out.write("			// 마커가 표시될 위치입니다 \r\n");
      out.write("			let markerPosition2  = new kakao.maps.LatLng(33.450701, 126.570667); \r\n");
      out.write("\r\n");
      out.write("			// 마커를 생성합니다\r\n");
      out.write("			let marker2 = new kakao.maps.Marker({\r\n");
      out.write("			    position: markerPosition2\r\n");
      out.write("			});\r\n");
      out.write("\r\n");
      out.write("			// 마커가 지도 위에 표시되도록 설정합니다\r\n");
      out.write("			marker2.setMap(map);\r\n");
      out.write("\r\n");
      out.write("			\r\n");
      out.write("			let iwContent = '<div style=\"padding:5px;\">Hello World! <br><a href=\"https://ictedu.co.kr\" style=\"color:blue\" target=\"_blank\">ICT 인재 개발원</a> </div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다\r\n");
      out.write("			    iwPosition = new kakao.maps.LatLng(lat, lng); //인포윈도우 표시 위치입니다\r\n");
      out.write("\r\n");
      out.write("			// 인포윈도우를 생성합니다\r\n");
      out.write("			let infowindow = new kakao.maps.InfoWindow({\r\n");
      out.write("			    position : iwPosition, \r\n");
      out.write("			    content : iwContent \r\n");
      out.write("			});\r\n");
      out.write("			  \r\n");
      out.write("			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다\r\n");
      out.write("			infowindow.open(map, marker); \r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
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
