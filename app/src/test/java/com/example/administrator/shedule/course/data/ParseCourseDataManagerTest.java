package com.example.administrator.shedule.course.data;

import com.example.administrator.shedule.bean.Course;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParseCourseDataManagerTest {

    private ParseCourseDataManager mManager;

    @Before
    public void setUp() throws Exception {
        mManager = new ParseCourseDataManager();
    }

    @Test
    public void parseDataHtml() {
        List<Course> list = mManager.parseDataHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "    │ <html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "    │     <head>\n" +
                "    │         <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    │         <title>教务管理系统</title>\n" +
                "    │         \n" +
                "    │ \n" +
                "    │ <script type=\"text/javascript\">\n" +
                "    │     var ssfwConfig = {\n" +
                "    │         contextPath : '/ssfw',\n" +
                "    │         comboboxUrl : '/ssfw/combobox.widgets',\n" +
                "    │         combotreeUrl : '/ssfw/combotree.widgets',\n" +
                "    │         combotableUrl : '/ssfw/combotable.widgets',\n" +
                "    │         photoDownloadUrl : '/ssfw/photo.widgets',\n" +
                "    │         photoUploadUrl : '/ssfw/photo.widgets',\n" +
                "    │         selectrangeUrl : '/ssfw/selectrange.widgets',\n" +
                "    │         combobox : ['pyjhxxxq','xz','hyzk','zyjszw','rxjj','jdfs','jxzs','zydm','kcsyfw','kcjb','pyfalx','rxfs','wyzl','jslx','pyfs','jxfs','rxklb','wyzldm','kcfl','bjh','xslb2','bylx','qxxxkdjzt','cjlrzt','dmgl_xq','xjzt','gjdq','cxcyfl','xslb','zsjd','xnxqdm','xqxx','djcjlx','zs','jtgx','lqlb','zjlx','ydxl','xqdm','xxxsdm','ksqhd','njdm','tsxslx','cjtsyy','xsly','jxlh','jash','xxfs','kclb','sqgx','tsxslxdm','jxxz','xjyd_shzt','zyfx','xkml','sf','yddl','hssxwfs','ksdm_bk','gat','kcxz','kspc','dwdm','ksxm','zzmm','cxcylb','lxbz','pyccm','kqlbdm','dmgl_zt','djcj','gbzwmc','zsksfs','xnxq','ksdm','kkdw','xbdm','ksdm_ck','mzdm','xdlx','sydw','skyz','jcxx','xxxs','zxjh','xldm','xndm','xw'],\n" +
                "    │         combotree : ['ssxxxx','kkdwtree','dsjbxx','xzqh'],\n" +
                "    │         combotable : [{\"config\":{\"title\":\"校标单位\",\"columns\":[{\"width\":\"60\",\"name\":\"dwdm\",\"header\":\"院系代码\"},{\"width\":\"120\",\"name\":\"dwmc\",\"header\":\"院系名称\"}]},\"name\":\"yxdmTable\"},{\"config\":{\"title\":\"请选择教材库\",\"columns\":[{\"width\":\"150\",\"name\":\"isbn\",\"header\":\"ISBN\"},{\"width\":\"300\",\"name\":\"sm\",\"header\":\"书名\"},{\"width\":\"180\",\"name\":\"csm\",\"header\":\"丛书名\"},{\"width\":\"120\",\"name\":\"zzz\",\"header\":\"著作者\"},{\"width\":\"60\",\"name\":\"jg\",\"header\":\"价格\"}]},\"name\":\"selectJcTable\"},{\"config\":{\"title\":\"校标单位\",\"columns\":[{\"width\":\"60\",\"name\":\"id\",\"header\":\"院系代码\"},{\"width\":\"120\",\"name\":\"name\",\"header\":\"院系名称\"}]},\"name\":\"nuistTable\"}]\n" +
                "    │     };\n" +
                "    │ </script>\n" +
                "    │ \n" +
                "    │ <link href=\"/ssfw/resources/js/ssfw/widgets/base/css/jquery-ui-1.9.1.custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    │ <link href=\"/ssfw/resources/lovey/style/css/lovey.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    │ <!-- 教务系统自定义的产品样式，主要用于覆盖loevy css，定义自己的css -->\n" +
                "    │ <link href=\"/ssfw/resources/common/css/custom.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "    │ <link href=\"/ssfw/resources/js/ssfw/widgets/base/css/base.css\" rel=\"stylesheet\"type=\"text/css\" />\n" +
                "    │ \n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery/jquery-1.8.2.min.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery-ui.js\"></script>\n" +
                "    │ \n" +
                "    │ \n" +
                "    │ <!-- js框架 -->\n" +
                "    │ <script src=\"/ssfw/resources/js/jquery-plugins/validate/jquery.metadata.js\" type=\"text/javascript\"></script>\n" +
                "    │ <script src=\"/ssfw/resources/js/jquery-plugins/validate/jquery.validate.js\" type=\"text/javascript\"></script>\n" +
                "    │ <script src=\"/ssfw/resources/js/jquery-plugins/validate/messages_cn.js\" type=\"text/javascript\"></script>\n" +
                "    │ <script src=\"/ssfw/resources/js/jquery-plugins/validate/validate_ex.js\" type=\"text/javascript\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.tooltip.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery-plugins/jstree/jquery.jstree.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery-plugins/jquery.form.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery-plugins/jquery.query-2.1.7.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/jquery-plugins/kindeditor/kindeditor-min.js\"></script>\n" +
                "    │ \n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.text.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/s\n" +
                "    │ sfw/widgets/base/jquery.ui.textButton.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.selectRange.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.attachment.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.util.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.combobox.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.combotree.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.date.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.textarea.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.photo.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.selectTable.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.multiText.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfw/widgets/base/jquery.ui.editableForm.js\"></script>\n" +
                "    │ <!-- ssfwUtil.js -->\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/js/ssfwUtil.js\"></script>\n" +
                "    │ <script type=\"text/javascript\" src=\"/ssfw/resources/lovey/js/easyloader.js\"></script>\n" +
                "    │ \n" +
                "    │         \n" +
                "    │ <!--这里填写google 分析器的js 代码-->\n" +
                "    │         \n" +
                "    │   \t <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    │   \t <link href=\"/ssfw/resources/css/jwCarlender.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    │   \t <link rel=\"stylesheet\" href=\"/ssfw/resources/css/module/query.css\" type=\"text/css\">\n" +
                "    │     \n" +
                "    │     \n" +
                "    │ \t    <script type=\"text/javascript\">\n" +
                "    │ \t      function printFunc() {\n" +
                "    │ \t      \t\twindow.open(\"/ssfw/print/pkgl/kcbxx/4/2018-2019-2.do\");\n" +
                "    │ \t      }\n" +
                "    │ \t      \n" +
                "    │ \t      function showTkComment(obj){\n" +
                "    │ \t          $(\"#detailMsg\").empty();\n" +
                "    │ \t          var tkids = $(obj).attr('tkid'); \n" +
                "    │ \t      \n" +
                "    │ \t          $(\"#detailMsg\").append($(\"#\"+tkids).html());\n" +
                "    │ \t          $(\"#detailMsg\").dialog({\n" +
                "    │ \t\t\t    \twidth: 330,\n" +
                "    │ \t\t\t    \theight: 240,\n" +
                "    │ \t\t\t        modal:true,\n" +
                "    │ \t\t\t        buttons : {\n" +
                "    │ \t\t\t            \"关闭\" : function() {\n" +
                "    │ \t\t\t                $(this).dialog(\"close\");\n" +
                "    │ \t\t\t            }\n" +
                "    │ \t\t\t        }\n" +
                "    │ \t\t\t    });\n" +
                "    │ \t\t\t    \n" +
                "    │ \t      }\n" +
                "    │ \t    </script>\n" +
                "    │     \n" +
                "    │     \n" +
                "    │      <script type=\"text/javascript\">\n" +
                "    │ \t      function refreshFunc() {\n" +
                "    │ \t      \t\tvar curXnxqdm = $(\"#xnxqdm\").val();\n" +
                "    │ \t      \t\tif(!curXnxqdm || curXnxqdm == '') return false;\n" +
                "    │ \t      \t\twindow.location.href = \"/ssfw/pkgl/kcbxx/4/\"+curXnxqdm+\".do\";\n" +
                "    │ \t      }\n" +
                "    │ \t </script>\n" +
                "    │ \t \n" +
                "    │ \t \n" +
                "    │ \t <style type=\"text/css\">\n" +
                "    │ \t    .commentAlign div{\n" +
                "    │ \t        width: 100%;\n" +
                "    │ \t        text-align: left;\n" +
                "    │ \t    }\n" +
                "    │ \t   .commentAlign{\n" +
                "    │ \t        text-align: left;\n" +
                "    │ \t    }\n" +
                "    │ \t    .sjsmdiv{\n" +
                "    │ \t        margin-left: 4px;\n" +
                "    │ \t    }\n" +
                "    │ \t    \n" +
                "    │ \t    .sjsmspan{\n" +
                "    │ \t       color: #FF8247;\n" +
                "    │ \t    }\n" +
                "    │ \t </style>\n" +
                "    │ \t    \n" +
                "    │   \n" +
                "    │         <!--[if IE 6]>\n" +
                "    │            <script type=\"text/javascript\" language=\"javascript\" src=\"/ssfw/resources/js/ie6png.js\"></script>\n" +
                "    │         <![endif]-->\n" +
                "    │         <style type=\"text/css\">\n" +
                "    │            .ul_fixed {\n" +
                "    │               position: fixed;\n" +
                "    │               width: 100%;\n" +
                "    │               z-index: 9999;\n" +
                "    │            }\n" +
                "    │            .div_body{\n" +
                "    │              margin-top: 30px;\n" +
                "    │            }\n" +
                "    │            .favi_img{\n" +
                "    │              cursor: pointer; \n" +
                "    │            }\n" +
                "    │            span.inf{\n" +
                "    │              font-size: 10px;\n" +
                "    │              color: #EE9A00;\n" +
                "    │            }\n" +
                "    │         </style>\n" +
                "    │     </head>\n" +
                "    │     <body>\n" +
                "    │ \t    <ul class=\"ui_nav_breadcrumb_style02 ul_fixed\">\n" +
                "    │ \t\t    <li><a href=\"#\" class=\"homepage\"></a><span class=\"divider\"></span></li>\n" +
                "    │ \t\t    \n" +
                "    │ \t\t  <!--    -->\n" +
                "    │ \t    </ul>\n" +
                "    │ \t    <div class=\"p10\">\n" +
                "    │ \t      <div class=\"div_body\">\n" +
                "    │           \t     \n" +
                "    │            <div class=\"tableGroup otherInfo\">\n" +
                "    │               <div class=\"NoPrint mainQuery\" style=\"text-align: right; padding: 2px 30px 2px 2px;\">  \n" +
                "    │                  学年学期:\n" +
                "    │      \n" +
                "    │             <select id=\"xnxqdm\" name=\"xnxqdm\">\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option selected value=\"2018-2019-2\" >2018-2019学年第二学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2018-2019-1\" >2018-2019学年第一学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2017-2018-3\" >2017-2018学年第三学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2017-2018-2\" >2017-2018学年第二学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2017-2018-1\" >2017-2018学年第一学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2016-2017-3\" >2016-2017学年第三学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2016-2017-2\" >2016-2017学年第二学期</option>\n" +
                "    │ \t                 \n" +
                "    │ \t                    <option  value=\"2016-2017-1\" >2016-2017学年第一学期</option>\n" +
                "    │ \t                 \n" +
                "    │                  </select>\n" +
                "    │                  &nbsp;&nbsp;<button class=\"ui_btn ui_btn_primary\"  onclick=\"refreshFunc()\"><i class=\"icon_search icon_white\"></i>查看</button>\n" +
                "    │                </div>\n" +
                "    │                \n" +
                "    │ \t               <div>\n" +
                "    │ \t                    <table name=\"\" id=\"1\" class=\"CourseFormTable\" style=\"\" >\n" +
                "    │ <caption>&nbsp;2018-2019学年第二学期 詹伟丽 同学课表 <span class=\"spanFloatRight\"><a style=\"cursor: pointer;\" onclick=\"javascript: try{printFunc();} catch(e){}\">打印</a></span></caption><tr><th class=\"firstHiddenTd\"><th>节次</th><th>星期一</th><th>星期二</th><th>星期三</th><th>星期四</th><th>星期五</th><th>星期六</th><th>星期日</th></tr>\n" +
                "    │ \t\t\t\t\t\t    <tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第1节<div>(08:00~08:45)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;编译原理[02] &nbsp;1-16周 &nbsp;(第1-2节)<br>刘凤连<br>综合 28-A209 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第2节<div>(08:55~09:40)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第3节<div>(10:10~10:55)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;Web应用程序设计与开发[02] &nbsp;1-13周 &nbsp;(第3-4节)<br>杨文军<br>综合 28-B105 \n" +
                "    │ </td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;计算机网络原理[03] &nbsp;1-16周 &nbsp;(第3-4节)<br>赵春蕾<br>综合 28-B102 \n" +
                "    │ </td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;Web应用程序设计与开发[02] &nbsp;1-13周 &nbsp;(第3-4节)<br>杨文军<br>综合 28-A409 \n" +
                "    │ </td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;数字图像处理[03] &nbsp;1-12周 &nbsp;(第3-4节)<br>杨淑莹<br>综合 28-A105 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第4节<div>(11:05~11:50)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第5节<div>(14:00~14:45)&nbsp;</div>&nb\n" +
                "    │ sp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;编译原理[02] &nbsp;1-16周 &nbsp;(第5-6节)<br>刘凤连<br>综合 28-A101 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;计算机网络原理[03] &nbsp;1-16周(双) &nbsp;(第5-6节)<br>赵春蕾<br>综合 28-B102 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第6节<div>(14:55~15:40)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第7节<div>(16:10~16:55)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;数字图像处理[03] &nbsp;1-12周 &nbsp;(第7-8节)<br>杨淑莹<br>综合 28-A105 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"2\">\n" +
                "    │ <div style=\"text-align:left;\"><div class=\"fcSpanDiv\"></div></div>&nbsp;创业基础[43] &nbsp;3-3周,6-6周,12-13周 &nbsp;(第7-8节)<br>王婷<br>6号楼 6-0203 \n" +
                "    │ </td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第8节<div>(17:05~17:50)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第9节<div>(18:30~19:15)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第10节<div>(19:25~20:10)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr><tr><td class=\"firstHiddenTd\"></td><td colspan=1 rowspan=\"1\" style=\"text-align:center;\">第11节<div>(20:20~21:05)&nbsp;</div>&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td><td colspan=1 rowspan=\"1\" >&nbsp;</td></tr>\n" +
                "    │ \t\t\t\t\t\t    \n" +
                "    │ \t\t\t\t\t\t    <tr><td class=\"firstHiddenTd\"></td><td colspan=\"9\" class=\"NotFixCourseTableTd\"><table class=\"NoFitCourse\" style=\"vertical-align:middle\" >\n" +
                "    │ <caption>&nbsp;未安排时间课程</caption><tr><th>课程名称</th><th>教学周次</th><th>上课老师</th><th>备注</th></tr><tr><td>&nbsp;信息系统设计[03]&nbsp;</td><td>&nbsp;</td><td>&nbsp;郝刚</td><td>&nbsp;</td></tr><tr><td>&nbsp;专业创新实践[03]&nbsp;</td><td>&nbsp;</td><td>&nbsp;王京辉</td><td>&nbsp;</td></tr><tr><td>&nbsp;网络综合设计[03]&nbsp;</td><td>&nbsp;</td><td>&nbsp;赵春蕾</td><td>&nbsp;</td></tr></table></td></tr>\n" +
                "    │ \t\t\t\t\t\t</table>\n" +
                "    │ \t               </div>\n" +
                "    │                \n" +
                "    │                \n" +
                "    │            </div>\n" +
                "    │            <div id=\"detailMsg\" title=\"调课记录明细\" style=\"display:none\"></div>\n" +
                "    │    \n" +
                "    │ \n" +
                "    │           </div>\n" +
                "    │         </div>  \n" +
                "    │     </body>\n" +
                "    │ </html>");
        for (Course temp : list) {
            System.out.println(temp);
        }

    }
}