package com.bizoss.trade.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.language.bm.Rule.Phoneme;

import com.bizoss.frame.QueryList;
import com.bizoss.frame.util.Config;
import com.winter.datetime.DateTime;

public class CopyOfMisSend {

	/**
	 * @param args
	 */
	
	public void send(String phone,String content){
	
		    String url = "http://58.18.141.200:8081/mis/ws/gateway/mt.do?username=nmgcf&password=111111&productId=2756";
			try {
				url = url+"&phones=" + phone + "&content="
						+ URLEncoder.encode(content, "UTF-8");
				URL oracle = new URL(url);
				URLConnection yc = oracle.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yc
						.getInputStream(), "UTF-8"));
				String inputLine = null;
				StringBuilder jsons = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					jsons.append(inputLine);
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		Map  map = new HashMap();
		/*map.put("phone", "18604763583");
		map.put("content", "18604763583测试短信请见谅");
		list.add(map);
		map.put("phone", "18647612526");
		map.put("content", "18647612526测试短信请见谅");
		list.add(map);*/
		map.put("phone", "18604787892");
		map.put("content", "18604787892测试短信请见谅");
		list.add(map);
		/*Map  map1 = new HashMap();
		map1.put("phone", "18647615603");
		map1.put("content", "18647615603测试短信请见谅");
		list.add(map1);
		Map  map2 = new HashMap();
		map2.put("phone", "18647615256");
		map2.put("content", "18647615256测试短信请见谅");
		list.add(map2);
		Map  map3 = new HashMap();
		map3.put("phone", "18647615601");
		map3.put("content", "18647615601测试短信请见谅");
		list.add(map3);
		Map  map4 = new HashMap();
		map4.put("phone", "18647612120");
		map4.put("content", "18647612120测试短信请见谅");
		list.add(map4);
		Map  map5 = new HashMap();
		map5.put("phone", "18647615657");
		map5.put("content", "18647615657测试短信请见谅");
		list.add(map5);
		Map  map6 = new HashMap();
		map6.put("phone", "18647615567");
		map6.put("content", "18647615567测试短信请见谅");
		list.add(map6);
		Map  map7 = new HashMap();
		map7.put("phone", "18647615227");
		map7.put("content", "18647615227测试短信请见谅");
		list.add(map7);
		Map  map8 = new HashMap();
		map8.put("phone", "18647612586");
		map8.put("content", "18647612586测试短信请见谅");
		list.add(map8);
		Map  map9 = new HashMap();
		map9.put("phone", "18647612369");
		map9.put("content", "18647612369测试短信请见谅");
		list.add(map9);
		Map  map10 = new HashMap();
		map10.put("phone", "18647615755");
		map10.put("content", "18647615755测试短信请见谅");
		list.add(map10);
		Map  map11 = new HashMap();
		map11.put("phone", "18647613052");
		map11.put("content", "18647613052测试短信请见谅");
		list.add(map11);
		Map  map12 = new HashMap();
		map12.put("phone", "18647611268");
		map12.put("content", "18647611268测试短信请见谅");
		list.add(map12);
		Map  map13 = new HashMap();
		map13.put("phone", "18647612563");
		map13.put("content", "18647612563测试短信请见谅");
		list.add(map13);
		Map  map14 = new HashMap();
		map14.put("phone", "18647615680");
		map14.put("content", "18647615680测试短信请见谅");
		list.add(map14);
		Map  map15 = new HashMap();
		map15.put("phone", "18647615501");
		map15.put("content", "18647615501测试短信请见谅");
		list.add(map15);
		Map  map16 = new HashMap();
		map16.put("phone", "18647612109");
		map16.put("content", "18647612109测试短信请见谅");
		list.add(map16);
		Map  map17 = new HashMap();
		map17.put("phone", "18647613164");
		map17.put("content", "18647613164测试短信请见谅");
		list.add(map17);
		Map  map18 = new HashMap();
		map18.put("phone", "18647615013");
		map18.put("content", "18647615013测试短信请见谅");
		list.add(map18);
		Map  map19 = new HashMap();
		map19.put("phone", "18647612760");
		map19.put("content", "18647612760测试短信请见谅");
		list.add(map19);
		Map  map20 = new HashMap();
		map20.put("phone", "18604793711");
		map20.put("content", "18604793711测试短信请见谅");
		list.add(map20);
		Map  map21 = new HashMap();
		map21.put("phone", "18647615505");
		map21.put("content", "186476155051测试短信请见谅");
		list.add(map21);
		Map  map22 = new HashMap();
		map22.put("phone", "18647615503");
		map22.put("content", "18647615503测试短信请见谅");
		list.add(map22);
		Map  map23 = new HashMap();
		map23.put("phone", "18647615111");
		map23.put("content", "18647615111测试短信请见谅");
		list.add(map23);
		Map  map24 = new HashMap();
		map24.put("phone", "18647615631");
		map24.put("content", "18647615631测试短信请见谅");
		list.add(map24);
		Map  map25 = new HashMap();
		map25.put("phone", "18647612828");
		map25.put("content", "18647612828测试短信请见谅");
		list.add(map25);
		Map  map26 = new HashMap();
		map26.put("phone", "18647615623");
		map26.put("content", "18647615623测试短信请见谅");
		list.add(map26);
		Map  map27 = new HashMap();
		map27.put("phone", "18647612811");
		map27.put("content", "18647612811测试短信请见谅");
		list.add(map27);
		Map  map28 = new HashMap();
		map28.put("phone", "18647613888");
		map28.put("content", "18647613888测试短信请见谅");
		list.add(map28);
		Map  map29 = new HashMap();
		map29.put("phone", "18647615627");
		map29.put("content", "18647615627测试短信请见谅");
		list.add(map29);
		Map  map30 = new HashMap();
		map30.put("phone", "18647612161");
		map30.put("content", "18647612161测试短信请见谅");
		list.add(map30);
		Map  map31 = new HashMap();
		map31.put("phone", "18647615968");
		map31.put("content", "18647615968测试短信请见谅");
		list.add(map31);
		Map  map32 = new HashMap();
		map32.put("phone", "18647615305");
		map32.put("content", "18647615305测试短信请见谅");
		list.add(map32);
		Map  map33 = new HashMap();
		map33.put("phone", "18647615615");
		map33.put("content", "18647615615测试短信请见谅");
		list.add(map33);*/
		CopyOfMisSend misSend = new CopyOfMisSend();
		for(int i=0;i<list.size();i++){
			Map maps = (HashMap)list.get(i);
			String phone = (String)maps.get("phone");
			String content = (String)maps.get("content");
			System.out.println(content);
			misSend.send(phone, content);
		}
		//misSend.send("【并联网】您好，您的验证码为：123456，请及时输入，您正在进行用户注册操作，如非本人操作，请忽略，工作人员不会向您索要，请勿向任何人提供您收到的验证码 （绿悠优健康食品旗舰店）", "18604787892", "1111", "buyer","");

	}

}
