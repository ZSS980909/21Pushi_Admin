package com.ershiyi.controller.questionDemo;//package com.ershiyi.controller.questionDemo;
//
//
//import com.ershiyi.dto.questionKnowledge;
//import com.ershiyi.service.IssueInspectService;
//import net.sf.json.JSONObject;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 爬取题目
// */
//public class questiontext1 {
//        //目标服务器地址
//        static String  targetUrl="http://tiku.yunxiao.com";
//        //目标模糊搜索接口
//        static String  joggleUrl="/kb_api/v2/questions/by_search";
//        static String  joggleUrl1="/user_api/v1/favorite/question/keys";
//        static  String  treeurl="/kb_api/v2/knowledge_trees/"; //树结构
//
//        @Autowired
//        private IssueInspectService questiontextservice;
//
//       // static String
//        public void  text1 (){
//            /**
//             * 循环获取语文所有知识点
//             */
//            Map map =new HashMap();
//                String s = get(joggleUrl1, map);
//                //  System.out.println(s);
//
//        }
//        public  void  text2(){
//                /**
//                 * 获取语文所有单选题 分页
//                 */
//        }
//        public void   text3(){
//
//        }
//
//
//        /**
//         * 发送GET请求
//         *
//         * @param url        目的地址
//         * @param parameters 请求参数，Map类型。
//         * @return 远程响应结果
//         */
//        public static String get(String url, Map<String, String> parameters) {
//                String result = "";
//
//                // 读取响应输入流
//                BufferedReader in = null;
//
//                // 存储参数
//                StringBuffer sb = new StringBuffer();
//
//                //编码之后的参数
//                String params = "";
//
//                try {
//                        // 编码请求参数
//                        if (parameters.size() == 1) {
//                                for (String name : parameters.keySet()) {
//                                        sb.append(name).append("=").append(
//                                                java.net.URLEncoder.encode(parameters.get(name),
//                                                        "UTF-8"));
//                                }
//                                params = sb.toString();
//                        } else if(parameters.size()==0){
//
//                        }else{
//                                for (String name : parameters.keySet()) {
//                                        sb.append(name).append("=").append(
//                                                java.net.URLEncoder.encode(parameters.get(name),
//                                                        "UTF-8")).append("&");
//                                }
//                                String temp_params = sb.toString();
//                                params = temp_params.substring(0, temp_params.length() - 1);
//                        }
//                        String full_url = url ; //+ "?" + params
//                      //  System.out.println(full_url);
//                        // 创建URL对象
//                        java.net.URL connURL = new java.net.URL(full_url);
//                        // 打开URL连接
//                        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
//                                .openConnection();
//                        // 设置通用属性
//                        httpConn.setRequestProperty("Accept", "application/json, text/plain, */*");
//                        httpConn.setRequestProperty("Connection", "Keep-Alive");
//                        httpConn.setRequestProperty("User-Agent",
//                                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.34 Safari/534.24");
//                        httpConn.setRequestProperty("Cookie","tiku-session-id=6719da848e0ec864081ea55f28ff77ab197f90829085338f8d5b7871aeae4f97535f0c99735cbe1c61a7f5593539143c3b7c45cec37eddeab63581010c69177e5c161b60baeb74892f4ebdef4f0a63c41e589f40d234898ac976c7ee5a4ee8656e74d670bba0f0f9bf7a2f357ac810c77f847b7ccb2112f8165661e2cf82182087c4dc4d8274d3255f7cf227d9bd80dd368a8a974705f06d2d24f0f9a349b0716263704c806ce555e08c0dc537fbf647bfd37fb83d0136ef5f11a6fd6c02537dac92f61e0842daee0f05b5be5cb7797d99d5d9a4b0e69c1740fb57361c0196db69ed46ce7cc6f0e982236a4ac2b14cf9a813af910d36947acb7770742261432b5e6212525ac0490d41307573cc459d0272fbdb2148bfa3dfdc69b786ab3f90ac99f0bd1120dad6c3d626e48357aa5689d79e8dbb77537e1b122f0030de93b0a30e47581ce53e11ea1630cbf587b79277; tiku-api-key=6719da848e0ec864081ea55f28ff77ab197f90829085338f8d5b7871aeae4f976d4dc058f9749edfcbff02b6dc848ed04e719621b17d2daf7344f9f5c0c15541c0cfe2b11b05b32c57cab92237383fac792c917e2bb4d426eb66c543f919ef106462af542a383d3cda90045207ea8bc852b7b7dca5cf572c6d23142b8343cc112d1f0bded21d28628041226d1d282b314c21514dacae9deb46a9adc9c23fa515e85694db1753c4ecf3c3fbdebdf7af784e3ea3d281638e67b4441a823b55ab9b; Hm_lvt_d9ce2e93fbe3e9d6109be3910c433855=1605411745,1605519088; Hm_lpvt_d9ce2e93fbe3e9d6109be3910c433855=1606025614; tiku-user-info=%7B%22id%22%3A2885120663650304%2C%22name%22%3A%22%E6%96%B0%E9%82%B5%E5%8E%BF%E7%AC%AC%E4%B8%80%E4%B8%AD%E5%AD%A6%E8%B6%85%E7%BA%A7%E7%AE%A1%E7%90%86%E5%91%98%22%2C%22role%22%3A%22%E6%95%99%E5%B8%88%22%2C%22avatar%22%3Anull%2C%22schoolId%22%3A27676%2C%22schoolName%22%3A%22%E6%96%B0%E9%82%B5%E5%8E%BF%E7%AC%AC%E4%B8%80%E4%B8%AD%E5%AD%A6%22%2C%22userId%22%3A2885120663650304%7D");
//
//                        // 建立实际的连接
//                        httpConn.connect();
//                        // 响应头部获取
//                        Map<String, List<String>> headers = httpConn.getHeaderFields();
//                        // 遍历所有的响应头字段
//                        for (String key : headers.keySet()) {
//                               // System.out.println(key + "\t：\t" + headers.get(key));
//                        }
//                        // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
//                        in = new BufferedReader(new InputStreamReader(httpConn
//                                .getInputStream(), "UTF-8"));
//                        String line;
//                        // 读取返回的内容
//                        while ((line = in.readLine()) != null) {
//                                result += line;
//                        }
//                } catch (Exception e) {
//                        e.printStackTrace();
//                } finally {
//                        try {
//                                if (in != null) {
//                                        in.close();
//                                }
//                        } catch (IOException ex) {
//                                ex.printStackTrace();
//                        }
//                }
//                return result;
//        }
//
//        /**
//         * 发送POST请求
//         *
//         * @param url        目的地址
//         * @param parameters 请求参数，Map类型。
//         * @return 远程响应结果
//         */
//        public static String post(String url, Map<String, String> parameters) {
//                String result = "";// 返回的结果
//                BufferedReader in = null;// 读取响应输入流
//                PrintWriter out = null;
//                StringBuffer sb = new StringBuffer();// 处理请求参数
//                String params = "";// 编码之后的参数
//                try {
//                        // 编码请求参数
//                        if (parameters.size() == 1) {
//                                for (String name : parameters.keySet()) {
//                                        sb.append(name).append("=").append(
//                                                java.net.URLEncoder.encode(parameters.get(name),
//                                                        "UTF-8"));
//                                }
//                                params = sb.toString();
//                        } else {
//                                for (String name : parameters.keySet()) {
//                                        sb.append(name).append("=").append(
//                                                java.net.URLEncoder.encode(parameters.get(name),
//                                                        "UTF-8")).append("&");
//                                }
//                                String temp_params = sb.toString();
//                                params = temp_params.substring(0, temp_params.length() - 1);
//                        }
//                        // 创建URL对象
//                        java.net.URL connURL = new java.net.URL(url);
//                        // 打开URL连接
//                        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
//                                .openConnection();
//                        // 设置通用属性
//                        httpConn.setRequestProperty("Accept", "*/*");
//                        httpConn.setRequestProperty("Connection", "Keep-Alive");
//                        httpConn.setRequestProperty("User-Agent",
//                                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
//                        // 设置POST方式
//                        httpConn.setDoInput(true);
//                        httpConn.setDoOutput(true);
//                        // 获取HttpURLConnection对象对应的输出流
//                        out = new PrintWriter(httpConn.getOutputStream());
//                        // 发送请求参数
//                        out.write(params);
//                        // flush输出流的缓冲
//                        out.flush();
//                        // 定义BufferedReader输入流来读取URL的响应，设置编码方式
//                        in = new BufferedReader(new InputStreamReader(httpConn
//                                .getInputStream(), "UTF-8"));
//                        String line;
//                        // 读取返回的内容
//                        while ((line = in.readLine()) != null) {
//                                result += line;
//                        }
//                } catch (Exception e) {
//                        e.printStackTrace();
//                } finally {
//                        try {
//                                if (out != null) {
//                                        out.close();
//                                }
//                                if (in != null) {
//                                        in.close();
//                                }
//                        } catch (IOException ex) {
//                                ex.printStackTrace();
//                        }
//                }
//                return result;
//        }
//
//        /**
//         * 主函数，测试请求
//         *
//         */
//       // public static void main(String[] args) {
//                 @Test
//                public void tes1(){
//                Map map =new HashMap();
//                String url =targetUrl+treeurl;
//                String s = get(url, map);
//                JSONObject jsonObject= JSONObject.fromObject(s);
//                Object data = jsonObject.get("data");
//                String s1 = data.toString();
//                JSONObject object= JSONObject.fromObject(s1);
//                Object knowledge_tree = object.get("knowledge_tree");
//                String s2 = knowledge_tree.toString();
//                JSONObject S21= JSONObject.fromObject(s2);
//                List<Object> children = (List<Object>) S21.get("children");
//                for(int i=0;i<children.size();i++){
//                        JSONObject jsonobject111 = (JSONObject) children.get(i);
//                        String name = (String) jsonobject111.get("name");
//                        String period= (String) jsonobject111.get("key");
//                        List<Object> children1 = (List<Object>) jsonobject111.get("children");
//                        for(int o=0;o<children1.size();o++){
//                                JSONObject o1 = (JSONObject) children1.get(o);
//                                /**
//                                 * 先拉取爱云校知识树，后拉去综合知识库
//                                 */
//                                List<Object> children2 = (List<Object>) o1.get("children");
//                                for(int  k=0;k<children2.size();k++){
//                                        JSONObject o2 = (JSONObject) children2.get(k);
//                                        Integer id = (Integer) o2.get("id");
//                                        String s4 = id.toString();
//                                        Map maps1=new HashMap();
//                                        /**
//                                         * 拉取科目题目
//                                         */
//                                        //2142175231  2019 高中 爱云校
//                                        //2141716479  2020 高中 爱云校
//                                        //2145189887  综合知识库
//                                        /**
//                                         * 发起请求
//                                         */
//                                        String s3 = get("http://tiku.yunxiao.com/kb_api/v2/knowledge_trees/"+s4, maps1);
//                                        JSONObject treeshu= JSONObject.fromObject(s3);
//                                        JSONObject data1 = (JSONObject) treeshu.get("data");
//                                       // System.out.println(data1);
//                                        if("小学".equals(data1.get("period"))){
//                                                JSONObject knowledge_tree111 = (JSONObject) data1.get("knowledge_tree");
//                                                List<Object> children123123 = (List<Object>) knowledge_tree111.get("children");
//                                                for (int l=0;l<children123123.size();l++){
//                                                        /**
//                                                         * 插入数据
//                                                         */
//                                                        JSONObject dantiao = (JSONObject) children123123.get(l);
//                                                        //字段 knowledge_name
//                                                         String knowledge_name="";
//                                                        //字段coursetype  if(爱云校==1,知识库==2)
//                                                        String coursetype="";
//                                                        if("综合知识库".equals(data1.get("name"))){
//                                                                coursetype ="2";
//                                                        }else{
//                                                                coursetype="1";
//                                                        }
//                                                       //字段 subject_name
//                                                        String subject_name = (String) data1.get("subject");
//                                                        //字段 periods
//                                                        String periods = (String) data1.get("period");
//                                                        //字段 children_no
//                                                        String children_no=(String) dantiao.get("name");
//
//                                                        JSONObject CHidl = (JSONObject) children123123.get(l);
//                                                        List<Object> chiaun = (List<Object>) CHidl.get("children");
//                                                        for(int a=0;a<chiaun.size();a++){
//                                                                JSONObject  aq = (JSONObject) chiaun.get(a);
//                                                             //   System.out.println(aq);
//                                                                String name1one = (String) aq.get("name");
//                                                                //字段 children_second
//                                                                String children_second =name1one;
//
//                                                                JSONObject o3 = (JSONObject) chiaun.get(a);
//                                                            List<Object> ca= (List<Object>) o3.get("children");
//                                                            if(ca!=null) {
//                                                                //System.out.println(ca.size());
//                                                                for (int q = 0; q < ca.size(); q++) {
//                                                                    System.out.println(q);
//                                                                    JSONObject va = (JSONObject) ca.get(q);
//                                                                    //字段 children_three
//                                                                    //字段 identification
//                                                                    String children_three = (String) va.get("name");
//                                                                    Integer S51 = (Integer) va.get("id");
//                                                                    String identification = S51.toString();
//                                                                    Object children3 = va.get("children");
//                                                                    if (children3 != null) {
//                                                                        List<Object> children31 = (List<Object>) children3;
//                                                                        for (int sQ = 0; sQ < children31.size(); sQ++) {
//                                                                            System.out.println("有多于三级目录");
//                                                                            JSONObject cs = (JSONObject) children31.get(sQ);
//                                                                            knowledge_name = (String) cs.get("name");
//                                                                            System.out.println("插入语句为" +
//                                                                                    "knowledge_name字段为" + knowledge_name +
//                                                                                    "~~~~~subject_name字段为" + subject_name +
//                                                                                    "~~~~~period字段为" + periods +
//                                                                                    "~~~~~identification字段为" + identification +
//                                                                                    "~~~~~children_no字段为" + children_no +
//                                                                                    "~~~~~children_econd字段为" + children_second +
//                                                                                    "~~~~~children_three字段为" + children_three+
//                                                                                    "~~~~~coursetype字段为"+coursetype);
//                                                                            questionKnowledge knowled=new questionKnowledge();
//                                                                            knowled.setChildren_no(children_no);
//                                                                            knowled.setChildren_seond(children_second);
//                                                                            knowled.setChildren_three(children_three);
//                                                                            knowled.setIdenification(identification);
//                                                                            knowled.setKnowledge_name(knowledge_name);
//                                                                            knowled.setPeriod(periods);
//                                                                            knowled.setSubject_name(subject_name);
//                                                                            knowled.setCoursetype(coursetype);
//                                                                            questiontextservice.insertabc(knowled);
//                                                                        }
//
//                                                                    } else {
//                                                                        System.out.println("没有多于三级目录");
//                                                                        knowledge_name = (String) va.get("name");
//
//                                                                        System.out.println("插入语句为" +
//                                                                                "knowledge_name字段为" + knowledge_name +
//                                                                                "~~~~~subject_name字段为" + subject_name +
//                                                                                "~~~~~period字段为" + periods +
//                                                                                "~~~~~identification字段为" + identification +
//                                                                                "~~~~~children_no字段为" + children_no +
//                                                                                "~~~~~children_econd字段为" + children_second +
//                                                                                "~~~~~children_three字段为" + children_three+
//                                                                                "~~~~~coursetype字段为"+coursetype);
//                                                                        questionKnowledge knowled=new questionKnowledge();
//                                                                        knowled.setChildren_no(children_no);
//                                                                        knowled.setChildren_seond(children_second);
//                                                                        knowled.setChildren_three(children_three);
//                                                                        knowled.setIdenification(identification);
//                                                                        knowled.setKnowledge_name(knowledge_name);
//                                                                        knowled.setPeriod(periods);
//                                                                        knowled.setSubject_name(subject_name);
//                                                                        knowled.setCoursetype(coursetype);
//                                                                        questiontextservice.insertabc(knowled);
//
//                                                                    }
//
//                                                                }
//                                                            }
//
//                                                        }
//                                                }
//                                        }else if("高中".equals(data1.get("period"))){
//                                            JSONObject knowledge_tree111 = (JSONObject) data1.get("knowledge_tree");
//                                            List<Object> children123123 = (List<Object>) knowledge_tree111.get("children");
//                                            for (int l=0;l<children123123.size();l++){
//                                                /**
//                                                 * 插入数据
//                                                 */
//                                                JSONObject dantiao = (JSONObject) children123123.get(l);
//                                                //字段 knowledge_name
//                                                String knowledge_name="";
//                                                //字段coursetype  if(爱云校==1,知识库==2)
//                                                String coursetype="";
//                                                if("综合知识库".equals(data1.get("name"))){
//                                                    coursetype ="2";
//                                                }else{
//                                                    coursetype="1";
//                                                }
//                                                //字段 subject_name
//                                                String subject_name = (String) data1.get("subject");
//                                                //字段 periods
//                                                String periods = (String) data1.get("period");
//                                                //字段 children_no
//                                                String children_no=(String) dantiao.get("name");
//
//                                                JSONObject CHidl = (JSONObject) children123123.get(l);
//                                                List<Object> chiaun = (List<Object>) CHidl.get("children");
//                                                for(int a=0;a<chiaun.size();a++){
//                                                    JSONObject  aq = (JSONObject) chiaun.get(a);
//                                                    //   System.out.println(aq);
//                                                    String name1one = (String) aq.get("name");
//                                                    //字段 children_second
//                                                    String children_second =name1one;
//
//                                                    JSONObject o3 = (JSONObject) chiaun.get(a);
//                                                    List<Object> ca= (List<Object>) o3.get("children");
//                                                    if(ca!=null) {
//                                                        //System.out.println(ca.size());
//                                                        for (int q = 0; q < ca.size(); q++) {
//                                                            System.out.println(q);
//                                                            JSONObject va = (JSONObject) ca.get(q);
//                                                            //字段 children_three
//                                                            //字段 identification
//                                                            String children_three = (String) va.get("name");
//                                                            Object children3 = va.get("children");
//                                                            String identification="";
//                                                            if (children3 != null) {
//                                                                List<Object> children31 = (List<Object>) children3;
//                                                                for (int sQ = 0; sQ < children31.size(); sQ++) {
//                                                                    System.out.println("有多于三级目录");
//                                                                    JSONObject cs = (JSONObject) children31.get(sQ);
//                                                                    knowledge_name = (String) cs.get("name");
//                                                                    if(cs.get("id")!=null){
//                                                                        Integer S51 = (Integer) cs.get("id");
//                                                                        identification = S51.toString();
//                                                                        System.out.println("插入语句为" +
//                                                                                "knowledge_name字段为" + knowledge_name +
//                                                                                "~~~~~subject_name字段为" + subject_name +
//                                                                                "~~~~~period字段为" + periods +
//                                                                                "~~~~~identification字段为" + identification +
//                                                                                "~~~~~children_no字段为" + children_no +
//                                                                                "~~~~~children_econd字段为" + children_second +
//                                                                                "~~~~~children_three字段为" + children_three);
//                                                                        questionKnowledge knowled=new questionKnowledge();
//                                                                        knowled.setChildren_no(children_no);
//                                                                        knowled.setChildren_seond(children_second);
//                                                                        knowled.setChildren_three(children_three);
//                                                                        knowled.setIdenification(identification);
//                                                                        knowled.setKnowledge_name(knowledge_name);
//                                                                        knowled.setPeriod(periods);
//                                                                        knowled.setSubject_name(subject_name);
//                                                                        knowled.setCoursetype(coursetype);
//                                                                        questiontextservice.insertabc(knowled);
//                                                                    }else{
//                                                                        /**
//                                                                         * 他们数据有个缺失 不规范
//                                                                         */
//                                                                        break;
//                                                                    }
//
//                                                                }
//
//                                                            } else {
//                                                                System.out.println("没有多于三级目录");
//                                                                //System.out.println("测试"+va);
//                                                                if(va.get("id")!=null){
//                                                                    Integer S51 = (Integer) va.get("id");
//                                                                    identification = S51.toString();
//                                                                }
//                                                                knowledge_name = (String) va.get("name");
//                                                            }
//                                                            System.out.println("插入语句为" +
//                                                                    "knowledge_name字段为" + knowledge_name +
//                                                                    "~~~~~subject_name字段为" + subject_name +
//                                                                    "~~~~~period字段为" + periods +
//                                                                    "~~~~~identification字段为" + identification +
//                                                                    "~~~~~children_no字段为" + children_no +
//                                                                    "~~~~~children_econd字段为" + children_second +
//                                                                    "~~~~~children_three字段为" + children_three);
//                                                            questionKnowledge knowled=new questionKnowledge();
//                                                            knowled.setChildren_no(children_no);
//                                                            knowled.setChildren_seond(children_second);
//                                                            knowled.setChildren_three(children_three);
//                                                            knowled.setIdenification(identification);
//                                                            knowled.setKnowledge_name(knowledge_name);
//                                                            knowled.setPeriod(periods);
//                                                            knowled.setSubject_name(subject_name);
//                                                            knowled.setCoursetype(coursetype);
//                                                            questiontextservice.insertabc(knowled);
//                                                        }
//                                                    }
//
//                                                }
//                                            }
//                                        }else{
//                                                //初中已有,不做处理
//                                        }
//
//                                        //System.out.println(s3);
//                                }
//
//                        }
//                        System.out.println("开始拉取下一个~~~~~~~~~~~~~~~~~~~~~~~");
//                }
//
//        }
//
//}
