package cn.cityworks.cms.utils;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.exception.BizException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * http工具类
 */
public class HttpTools {

    /**
     * get请求
     */
    public static String httpGetRequest(String url, List<Map<String , String>> list){
        //创建Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        StringBuffer param = new StringBuffer();
        try{
            param.append(url + "?");
            for(Map<String, String> map : list){
                param.append(map.get("paramKey") + "=");
                //字符串需要编码，防止错误地址
                param.append(URLEncoder.encode(map.get("paramValue"), "UTF-8"));
                param.append("&");
            }

        }catch (UnsupportedEncodingException ex){
            throw new BizException(Definition.RESPONSE_STATUS_FAIL, "GET请求失败!", "地址转码失败!");
        }

        //创建GET请求
        HttpGet httpGet = new HttpGet(param.substring(0, param.length() - 1));
        //响应模型
        CloseableHttpResponse response = null;
        try{
            RequestConfig requestConfig = RequestConfig.custom()
                    //设置连接超时时长(单位：毫秒)
                    .setConnectTimeout(8000)
                    //设置请求超时时间(单位：毫秒)
                    .setConnectionRequestTimeout(8000)
                    //socket读写超时时间(单位：毫秒)
                    .setSocketTimeout(8000)
                    //是否允许重定向，默认为true
                    .setRedirectsEnabled(true).build();

            //将配置写入get请求
            httpGet.setConfig(requestConfig);

            //由客户端执行Get请求
            response = httpClient.execute(httpGet);

            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            int responseCode = response.getStatusLine().getStatusCode();

            if(200 != responseCode && 201 != responseCode && 202 != responseCode){
                throw new BizException(Definition.RESPONSE_STATUS_FAIL, "GET请求失败!", "响应状态码不为成功响应!");
            }

            String result = null;
            if(null != responseEntity){
                result = EntityUtils.toString(responseEntity);
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();

            return null;
        }finally{       //关闭连接,释放资源
            try{
                if(null != httpClient){
                    httpClient.close();
                }

                if(null != response){
                    response.close();
                }
            }catch (Exception em){
                em.printStackTrace();
            }
        }
    }
}
