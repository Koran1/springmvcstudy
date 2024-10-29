/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.96
 * Generated at: 2024-10-18 03:12:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.day04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajax_005fresult_005fdb_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Ajax - DB</title>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("	span{width: 150px; color: red;}\r\n");
      out.write("	input{border: 1px solid blue;}\r\n");
      out.write("	table{width: 80%; border-collapse: collapse; margin: 0px auto;}\r\n");
      out.write("	table, th, td{border: 1px solid gray; text-align: center;}\r\n");
      out.write("	h2{text-align: center;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<h2>Ajax 를 활용한 DB 처리</h2>\r\n");
      out.write("	<h2>회원 정보 입력하기</h2>\r\n");
      out.write("	<form method=\"post\" id=\"myForm\">\r\n");
      out.write("		<table>\r\n");
      out.write("			<thead>\r\n");
      out.write("				<tr><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th> </tr>\r\n");
      out.write("			</thead>\r\n");
      out.write("			<tbody>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td>\r\n");
      out.write("						<input type=\"text\" size=\"14\" name=\"m_id\" id=\"m_id\"><br><span>중복여부</span>\r\n");
      out.write("					</td>\r\n");
      out.write("					<td>\r\n");
      out.write("						<input type=\"password\" size=\"10\" name=\"m_pw\" id=\"m_pw\">\r\n");
      out.write("					</td>\r\n");
      out.write("					<td>\r\n");
      out.write("						<input type=\"text\" size=\"10\" name=\"m_name\" id=\"m_name\">\r\n");
      out.write("					</td>\r\n");
      out.write("					<td>\r\n");
      out.write("						<input type=\"number\" size=\"10\" name=\"m_age\" id=\"m_age\">\r\n");
      out.write("					</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</tbody>\r\n");
      out.write("			<tfoot>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<td colspan=\"4\" align=\"center\">\r\n");
      out.write("						<input type=\"button\" value=\"가입하기\" id=\"btn_join\" disabled>\r\n");
      out.write("					</td>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</tfoot>\r\n");
      out.write("		</table>\r\n");
      out.write("	</form>\r\n");
      out.write("	<br><br><br><br>\r\n");
      out.write("	<h2>회원 정보 보기</h2>\r\n");
      out.write("	<div>\r\n");
      out.write("		<table id=\"list\">\r\n");
      out.write("			<thead>\r\n");
      out.write("				<tr><th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th><th>가입일</th><th>삭제</th></tr>\r\n");
      out.write("			</thead>\r\n");
      out.write("			<tbody id=\"tbody\">\r\n");
      out.write("				\r\n");
      out.write("			</tbody>\r\n");
      out.write("		</table>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<script type=\"text/javascript\">\r\n");
      out.write("		// Ajax 이용해서 db 정보 가져오기\r\n");
      out.write("		// 1. xml\r\n");
      out.write("		function getList(){\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/ajax_db_list\",\r\n");
      out.write("				method : \"post\",\r\n");
      out.write("				dataType : \"xml\",\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let tbody = \"\";\r\n");
      out.write("					$(data).find(\"member\").each(function(i, v){\r\n");
      out.write("					// $.each((data).find(\"member\"), function(idx, obj){		=> $(this) 대신에 obj 쓰면 되긴함\r\n");
      out.write("						tbody += \"<tr>\";\r\n");
      out.write("						// tbody += \"<td>\" + $(this).find(\"m_idx\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + (i+1) +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + $(this).find(\"m_id\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + $(this).find(\"m_pw\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + $(this).find(\"m_name\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + $(this).find(\"m_age\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + $(this).find(\"m_reg\").text() +\"</td>\"\r\n");
      out.write("						tbody += \"<td><input type='button' value='삭제' id='member_del' name='\"+$(this).find(\"m_idx\").text()+\"'></td>\"\r\n");
      out.write("						tbody += \"</tr>\"\r\n");
      out.write("					})\r\n");
      out.write("					$(\"#tbody\").append(tbody);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"가져오기가 실패했슈\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		// 2. json\r\n");
      out.write("		// spring 에서는 json을 만들거나 파싱할 때 여러 가지 라이브러리를 사용할 수 있다\r\n");
      out.write("		// => gson 라는 라이브러리를 사용하자 (pom.xml에 등록)\r\n");
      out.write("		function getList2(){\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/ajax_db_list2\",\r\n");
      out.write("				method : \"post\",\r\n");
      out.write("				dataType : \"json\",\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let tbody = \"\";\r\n");
      out.write("					$.each(data, function(index, obj){\r\n");
      out.write("						tbody += \"<tr>\";\r\n");
      out.write("						tbody += \"<td>\" + obj.m_idx +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + obj.m_id +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + obj.m_pw +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" + obj.m_name +\" 님</td>\"\r\n");
      out.write("						tbody += \"<td>\" + obj.m_age +\"살</td>\"\r\n");
      out.write("						tbody += \"<td>\" + obj.m_reg +\"</td>\"\r\n");
      out.write("						tbody += \"<td>\" +\"삭제?\" +\"</td>\"\r\n");
      out.write("						tbody += \"</tr>\"\r\n");
      out.write("					});\r\n");
      out.write("					$(\"#tbody\").append(tbody);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"가져오기가 실패했슈2\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		}\r\n");
      out.write("		// 3. csv\r\n");
      out.write("		function getList3(){\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/ajax_db_list3\",\r\n");
      out.write("				method : \"post\",\r\n");
      out.write("				dataType : \"text\",\r\n");
      out.write("				success : function(data) {\r\n");
      out.write("					let rows = data.split(\"\\n\");\r\n");
      out.write("					let tbody = \"\";\r\n");
      out.write("					$.each(rows, function(index, row){\r\n");
      out.write("						// 헤더가 있으면 제외\r\n");
      out.write("						if(index === 0 || row.trim() === \"\") return true;\r\n");
      out.write("						\r\n");
      out.write("						tbody +=\"<tr>\"\r\n");
      out.write("						let cols = row.split(\",\");\r\n");
      out.write("						\r\n");
      out.write("						$.each(cols, function(i, col){\r\n");
      out.write("							tbody +=\"<td>\" + col + \"</td>\";\r\n");
      out.write("						})\r\n");
      out.write("						tbody += \"<td>삭제</td>\"\r\n");
      out.write("						tbody += \"</tr>\"\r\n");
      out.write("					})\r\n");
      out.write("					$(\"#tbody\").append(tbody);\r\n");
      out.write("				},\r\n");
      out.write("				error : function() {\r\n");
      out.write("					alert(\"가져오기가 실패했슈3\");\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		// 입력 체크용\r\n");
      out.write("		let isInputChk = false;\r\n");
      out.write("		\r\n");
      out.write("		function toggleJoinButton() {\r\n");
      out.write("			const passwordField = $(\"#m_pw\").val() !== \"\";\r\n");
      out.write("			const nameField = $(\"#m_name\").val() !== \"\";\r\n");
      out.write("			const ageField = $(\"#m_age\").val() !== \"\" && parseInt($(\"#m_age\").val()) > 0;\r\n");
      out.write("			if(isInputChk && passwordField && nameField && ageField){\r\n");
      out.write("				$(\"#btn_join\").prop(\"disabled\", false);\r\n");
      out.write("			}else{\r\n");
      out.write("				$(\"#btn_join\").prop(\"disabled\", true);\r\n");
      out.write("			}\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		// 아이디 중복 여부 확인\r\n");
      out.write("		$(\"#m_id\").keyup(function(){\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/ajax_db_chkid\",\r\n");
      out.write("				method : \"post\",\r\n");
      out.write("				data : \"m_id=\"+$(\"#m_id\").val(),			// 파라미터 1개일 때 사용\r\n");
      out.write("				dataType : \"text\",\r\n");
      out.write("				success : function(data){\r\n");
      out.write("					if(data == '0'){\r\n");
      out.write("						$(\"span\").text(\"사용가능\");\r\n");
      out.write("						$(\"span\").css(\"color\", \"blue\");\r\n");
      out.write("						isInputChk = true;\r\n");
      out.write("						is\r\n");
      out.write("					}else{\r\n");
      out.write("						$(\"span\").text(\"사용불가\");\r\n");
      out.write("						$(\"span\").css(\"color\", \"red\");\r\n");
      out.write("						isInputChk = false;\r\n");
      out.write("						\r\n");
      out.write("					}\r\n");
      out.write("					toggleJoinButton();\r\n");
      out.write("				},\r\n");
      out.write("				error : function(){\r\n");
      out.write("					alert(\"아이디 읽기 실패\")\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		$(\"#m_pw, #m_name\").keyup(toggleJoinButton);\r\n");
      out.write("		$(\"#m_age\").on(\"change keyup\", toggleJoinButton);\r\n");
      out.write("		\r\n");
      out.write("		// 회원정보 insert\r\n");
      out.write("		// 파라미터 여러 개는 직렬화 serialize() => form 태그 안에서 보통 사용\r\n");
      out.write("		$(\"#btn_join\").click(function () {\r\n");
      out.write("			let param = $(\"#myForm\").serialize();\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/ajax_member_join\",\r\n");
      out.write("				method : \"post\",\r\n");
      out.write("				// data : param,\r\n");
      out.write("				data : { m_id : $(\"#m_id\").val(), \r\n");
      out.write("						 m_pw : $(\"#m_pw\").val(),\r\n");
      out.write("						 m_name : $(\"#m_name\").val(),\r\n");
      out.write("						 m_age : $(\"#m_age\").val()\r\n");
      out.write("						},\r\n");
      out.write("				dataType : \"text\",\r\n");
      out.write("				success : function (data) {\r\n");
      out.write("					if(data == '0'){\r\n");
      out.write("						alert(\"가입실패\");\r\n");
      out.write("					}else{\r\n");
      out.write("						$(\"#tbody\").empty();\r\n");
      out.write("						getList();\r\n");
      out.write("						\r\n");
      out.write("						// 가입 창 초기화\r\n");
      out.write("						$(\"m_id\").val(\"\");\r\n");
      out.write("						$(\"m_pw\").val(\"\");\r\n");
      out.write("						$(\"m_name\").val(\"\");\r\n");
      out.write("						$(\"m_age\").val(\"\");\r\n");
      out.write("						\r\n");
      out.write("						// form 은 배열로 넘어온다!\r\n");
      out.write("						$(\"#myForm\")[0].reset();\r\n");
      out.write("						$(\"span\").text(\"중복여부\");\r\n");
      out.write("						$(\"span\").css(\"color\", \"red\");\r\n");
      out.write("						$(\"#btn_join\").prop(\"disabled\", true);\r\n");
      out.write("					}\r\n");
      out.write("				},\r\n");
      out.write("				error : function () {\r\n");
      out.write("					alert(\"읽기 실패\");\r\n");
      out.write("				}\r\n");
      out.write("				\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		// 동적쿼리 (동적 바인딩 변수 이므로 click 안됨 => on 사용)\r\n");
      out.write("		// on \"click\" - 동적 / click - 최초에 선언된 element에만 동작\r\n");
      out.write("		// #list = 부모 / member_del = 자식\r\n");
      out.write("		$(\"#list\").on(\"click\", \"#member_del\", function(){\r\n");
      out.write("			// alert($(this).attr(\"name\"));\r\n");
      out.write("			if(confirm(\"정말 삭제할까요?\")){\r\n");
      out.write("				$.ajax({\r\n");
      out.write("					url : \"/ajax_member_delete\",\r\n");
      out.write("					method : \"post\",\r\n");
      out.write("					data : \"m_idx=\"+ $(this).attr(\"name\"),\r\n");
      out.write("					dataType : \"text\",\r\n");
      out.write("					success : function(data) {\r\n");
      out.write("						if(data == '0'){\r\n");
      out.write("							alert(\"삭제 실패\");\r\n");
      out.write("						} else if(data == '1'){\r\n");
      out.write("							$(\"#tbody\").empty();\r\n");
      out.write("							getList();\r\n");
      out.write("						}\r\n");
      out.write("					},\r\n");
      out.write("					error : function() {\r\n");
      out.write("						alert(\"읽기 실패3\");\r\n");
      out.write("					}\r\n");
      out.write("				});\r\n");
      out.write("			} else{\r\n");
      out.write("				alert(\"삭제가 취소 되었습니다\");\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		getList();\r\n");
      out.write("	</script>\r\n");
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
