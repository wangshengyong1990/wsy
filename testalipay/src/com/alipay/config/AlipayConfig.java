package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2018082361146357";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDSoxxOXEt/n29gkWjA2UDppZB1s9j2uni1ksMakdwUJjRcx5G8A9oj2qwm9yfHqcPGukKOMDmxC+JJS2U3YAPiDlvXv0D3SVc5to08a3c8Kujol/9gdH7VzndvRu+xNZpdjp05DzWyTnKXP9MFOKfiFCskQlxGRL42GkgcDInpQY6JRGI13JF4NSAhaCnKY5JXz/QrPctQbUMgiMMiocTHgOoL4Z8Kqo7hOUc35+MmN/JZuM39UHXNrY7P96v87lv+BHeDAiyHKXwvBJ86iQJs/2JxRGsxBqU1ZS5ZkkIJOXtqhg9IU7IQvw/js3Apf1kNH7id1d51e1RpXVc6ORVAgMBAAECggEAS2F2GVwdu4ausywkx1+fk+UFyG+JS4/ZUcAv/sTWl4nu8/Lgo2IZtrMBUr1qgoGa8XVF7/R97QEfOeL6tMooSMKyF1u2cY17ckepaVSJup0EL0N/UZJD+ccvFj0JvRJO+OZE0fBpbfjDEYkrm4cyRt6BU1NC1IjM98tpMJK6eT/YOGmbsm4xAT7/kqR2uySvNm2tEGWmp04C4mTheXdDYc0pN0yPHbx82XA6gPL6d17XCUFC2YMeXkohkNBGxfpJa+DUE04cQTxNlaTD3jSfssIMAqF908Wb96RaAVbBzpjhvY0A6fMvE5XqToCdbR9y9uy2/0ZKtvP93z6+jVlkgQKBgQC4kPStdvwHccBmMCybXpFGBgsbcvMKpbz+l8sSXEIBm3TaoxXOjxZ2UU2o3AYC1YG1+oCrdbwtuiOHjN/eOoglQkPC7t1FP82ZWPsLqxo71oIcoMOMoWj2FHrZvc5zxWKxobp/ZuhKRF6GL+51pwGZHDDNNu+qaHze2KIYOuZYjQKBgQC2Gwn908v9rZCSxOdyvXRs+v0IwdrADjjaYBax/vQLRTPnGuL5oboGpTG9E0i64RmDuMy3wOBH5E24XOJpu8K4r0fxgi03HAmNoJUe2zmJt8uy/vUNfFMB53caaPXH47ytdoi2UQK4G3dnFzL48vAudqk47C5fxazXlnRJ61Z86QKBgBnKd8Z9p/5KUP52oOUdM+O7GHY/e+LzAhP5ljD8n2SmGpAq1fNFX3gtKFL+XACtYTDSzoQnMaKaLjjU4LunJjs+pUVqJ5VzswFIw9v/juzprsjdVjYioA4zEm+27FCVNpNn7C29bZxjLWpG7GWYbsq8e71RffAsJbbmQtBr+qBJAoGBAIbEqfdMaBob+fN0f2j95ozEjsCNXotluxN5qzIdYZR5qXiuAC4ITzidxpqMC/Ypoo1GFVMkDZeG0THQo8rmJqwxJAgKc9XmDPfgUJueWA2glMKqRsatLG0rVIyaAtLkqnyhTXnHqECCKzKDC7kLaU6piIDU4VkFXO3LrdIKtV4JAoGADi+7B+nNL18XKjPesLchbAtfNWek23PBN4ZCPppXXFifIFFqPBeW/ZP3joplTWF3l+W9njf+9zE70YkBUVZlKEDN6Afcq/b/9JsbW/gXQvw5lw0I3q+vqbHP+w571w6HK4fMDb6sfnbSmhe1Ir61WGZXh/itaeCdtJdjW74WQkU=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiKRzQT4UOlm3yQkO7VuxuEgwtf+yEyNKBiQNuSgdoyF0jAURyiVcUPhpQHqKr2G+NvGI0srlljx/D+7R+fIbtvdnyM1rsIJOTvsi8UPybLZCpJai8ro1KxQdkDWcpgiTjYKyf0RR4lMmMmLrtSfxM/3tdmNnljsooq4QNKwtL4jIGG4Q9rm/6llrizRZL6VfFZ6YYPUJVKDbcxHTEFMXmObmme1YG8blMjplFCrLPUddI98I2ic4a2yXoc07lMN0prox6oVL9ofkVex7yyR/aBT/sO84w2Y7X3Tj+ufJNrRJAmJqRCndHMCNLWa+gue+V6HhluT1KUd4RQUWhZ7huwIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://45.40.242.68/testalipay/jsp/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://45.40.242.68:80/testalipay/jsp/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

