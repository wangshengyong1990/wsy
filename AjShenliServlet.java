package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.derby.impl.store.raw.xact.EscalateContainerKey;

import com.dao.IndexRcDao;
import com.factory.DaoFactory;
import com.ibm.icu.util.BytesTrie.State;
import com.model.AnguanModel;
import com.model.ExamineModel;
import com.model.Examine_fileModel;
import com.model.IndexRcModel;
import com.model.Index_jjfModel;
import com.model.ShenliModel;
import com.model.T_ajsltj;
import com.model.T_bnb;
import com.model.T_cfryjb;
import com.model.T_dbxszzg;
import com.model.T_fxyj;
import com.model.T_gzlc;
import com.model.T_nbgl;
import com.model.T_pcbz;
import com.model.T_yzaj;
import com.model.T_zxsjb;
import com.util.ExcelUtil;
import com.util.JsonTransform;
import com.winter.db.DBService;


/**
 * 审理数据管理servlet
 *
 */
public class AnJianShenLiServlet extends HttpServlet {
	public AnJianShenLiServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (StringUtils.isNotEmpty(action)) {
			if(action.equals("addgzlc")) {
				addGzlc(request,response);
			}else if (action.equals("delgzlc")) {
				delgzlc(request, response);
			}else if (action.equals("delsgzlc")) {
				delsgzlc(request, response);
			}else if (action.equals("editgzlc")) {
				editgzlc(request, response);
			}else if(action.equals("addnbgl")){
				addNbgl(request,response);
			}else if(action.equals("editnbgl")){
				editNbgl(request,response);
			}else if(action.equals("delsnbgl")){
				delsNbgl(request,response);
			}else if(action.equals("delnbgl")){
				delNbgl(request,response);
			}else if(action.equals("addyzaj")){
				addYzaj(request,response);
			}else if(action.equals("edityzaj")){
				editYzaj(request,response);
			}else if(action.equals("delsyzaj")){
				delsYzaj(request,response);
			}else if(action.equals("delyzaj")){
				delYzaj(request,response);
			}else if(action.equals("addpcbz")){
				addPcbz(request,response);
			}else if(action.equals("editpcbz")){
				editPcbz(request,response);
			}else if(action.equals("delspcbz")){
				delsPcbz(request,response);
			}else if(action.equals("delpcbz")){
				delPcbz(request,response);
			}else if(action.equals("addcfryjb")){
				addCfryjb(request,response);
			}else if(action.equals("editcfryjb")){
				editCfryjb(request,response);
			}else if(action.equals("delscfryjb")){
				delsCfryjb(request,response);
			}else if(action.equals("delcfryjb")){
				delCfryjb(request,response);
			}else if(action.equals("addbnb")){
				addBnb(request,response);
			}else if(action.equals("editbnb")){
				editBnb(request,response);
			}else if(action.equals("delsbnb")){
				delsBnb(request,response);
			}else if(action.equals("delcbnb")){
				delBnb(request,response);
			}else if(action.equals("addajsltj")){
				addAjsltj(request,response);
			}else if(action.equals("editajsltj")){
				editAjsltj(request,response);
			}else if(action.equals("delsajsltj")){
				delsAjsltj(request,response);
			}else if(action.equals("delajsltj")){
				delAjsltj(request,response);
			}else if(action.equals("findTjByTime")){
				findTjByTime(request,response);
			}else if(action.equals("findCfryjbByTime")){
				findCfryjbByTime(request,response);
			}else if(action.equals("findBnbByTime")){
				findBnbByTime(request,response);
			}else if(action.equals("getBnbStatic")){
				getBnbStatic(request,response);
			}
		}
	}
	public void editgzlc(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_gzlc model = new T_gzlc();
		model.setId(Integer.parseInt(req.getParameter("id")));
		model.setFileName(req.getParameter("fileName"));
		model.setFilePath(req.getParameter("filePath"));
		model.setOldName(req.getParameter("oldName"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Boolean falg = insertT_gzlclogs(req.getParameter("id"), req);
		if(falg){
			int i = DaoFactory.getT_gzlcDao().update(model);
			if(i> 0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			pw.write("success");
		}
	}
	public void delsgzlc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		int count = ids.length;
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			for (int i = 0; i < ids.length; i++) {
				Boolean falg = insertT_gzlclogs(ids[i], request);
				if(falg){
					count--;
				}
			}
			if(count==0){
				int res = DaoFactory.getT_gzlcDao().batchDeleteByID(idList);
				if (res > 0) {
					pw.write("删除成功！");
				}else{
					pw.write("删除失败！");
				}
			}else{
				pw.write("删除成功,添加日志失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delgzlc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		Boolean falg = insertT_gzlclogs(id, request);
		if(falg){
			int res = DaoFactory.getT_gzlcDao().deleteByID(id);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else{
			pw.write("删除成功,添加日志失败！");
		}
		
	}
	public void addGzlc(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_gzlc model = new T_gzlc();
		model.setFileName(req.getParameter("fileName"));
		model.setFilePath(req.getParameter("filePath"));
		model.setOldName(req.getParameter("oldName"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Object obj = DaoFactory.getT_gzlcDao().insert(model);
		if(obj == null){
		 	pw.write("success");
		}else{
			pw.write("error");
		}
	}
	public void addNbgl(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_nbgl model = new T_nbgl();
		model.setFileName(req.getParameter("fileName"));
		model.setFilePath(req.getParameter("filePath"));
		model.setAjlx(req.getParameter("ajlx"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Object obj = DaoFactory.getT_nbglDao().insert(model);
		if(obj == null){
		 	pw.write("success");
		}else{
			pw.write("error");
		}
	}
	public void editNbgl(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_nbgl model = new T_nbgl();
		model.setId(Integer.parseInt(req.getParameter("id")));
		model.setFileName(req.getParameter("fileName"));
		model.setFilePath(req.getParameter("filePath"));
		model.setAjlx(req.getParameter("ajlx"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Boolean falg = insertT_nbgllogs(req.getParameter("id"), req);
		if(falg){
			int i = DaoFactory.getT_nbglDao().update(model);
			if(i> 0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			pw.write("error");
		}
		
	}
	public void delsNbgl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		int count = ids.length;
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			for (int i = 0; i < ids.length; i++) {
				Boolean falg = insertT_nbgllogs(ids[i], request);
				if(falg){
					count--;
				}
			}
			if(count==0){
				int res = DaoFactory.getT_nbglDao().batchDeleteByID(idList);
				if (res > 0) {
					pw.write("删除成功！");
				}else{
					pw.write("删除失败！");
				}
			}else{
				pw.write("删除成功,添加日志失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delNbgl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		Boolean falg = insertT_nbgllogs(id, request);
		if(falg){
			int res = DaoFactory.getT_nbglDao().deleteByID(id);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else{
			pw.write("删除失败！");
		}
		
	}
	
	public void addYzaj(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_yzaj model = new T_yzaj();
		model.setAjlx(req.getParameter("ajlx"));
		model.setAjmc(req.getParameter("ajmc"));
		model.setAqjj(req.getParameter("aqjj"));
		model.setAqjjPath(req.getParameter("aqjjPath"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		model.setFxdp(req.getParameter("fxdp"));
		model.setFxdpPath(req.getParameter("fxdpPath"));
		model.setNd(req.getParameter("nd"));
		Object obj = DaoFactory.getT_yzajDao().insert(model);
		if(obj == null){
		 	pw.write("success");
		}else{
			pw.write("error");
		}
	}
	public void editYzaj(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_yzaj model = new T_yzaj();
		model.setId(Integer.parseInt(req.getParameter("id")));
		model.setAjlx(req.getParameter("ajlx"));
		model.setAjmc(req.getParameter("ajmc"));
		model.setAqjj(req.getParameter("aqjj"));
		model.setAqjjPath(req.getParameter("aqjjPath"));
		model.setUser_id(req.getParameter("user_id"));
		model.setFxdp(req.getParameter("fxdp"));
		model.setFxdpPath(req.getParameter("fxdpPath"));
		model.setNd(req.getParameter("nd"));
		Boolean falg = insertT_yzajlogs(req.getParameter("id"), req);
		if(falg){
			int i = DaoFactory.getT_yzajDao().update(model);
			if(i> 0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			pw.write("error");
		}
		
	}
	public void delsYzaj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		int count = ids.length;
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					Boolean falg = insertT_yzajlogs(ids[i], request);
					if(falg){
						count--;
					}
				}
				if(count==0){
					int res = DaoFactory.getT_yzajDao().batchDeleteByID(idList);
					if (res > 0) {
						pw.write("删除成功！");
					}else{
						pw.write("删除失败！");
					}
				}else{
					pw.write("删除成功,添加日志失败！");
				}
			}
		}	
	}
	public void delYzaj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		Boolean falg = insertT_yzajlogs(id, request);
		if(falg){
			int res = DaoFactory.getT_yzajDao().deleteByID(id);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else{
			pw.write("删除失败！");
		}
	}
	public void addPcbz(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_pcbz model = new T_pcbz();
		model.setPcbz(req.getParameter("pcbz"));
		model.setPcbzPath(req.getParameter("pcbzPath"));
		model.setAjlx(req.getParameter("ajlx"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Object obj = DaoFactory.getT_pcbzDao().insert(model);
		if(obj == null){
		 	pw.write("success");
		}else{
			pw.write("error");
		}
	}
	public void editPcbz(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_pcbz model = new T_pcbz();
		model.setId(Integer.parseInt(req.getParameter("id")));
		model.setPcbz(req.getParameter("pcbz"));
		model.setPcbzPath(req.getParameter("pcbzPath"));
		model.setAjlx(req.getParameter("ajlx"));
		model.setCreateTime(DBService.getDateTime());
		model.setUser_id(req.getParameter("user_id"));
		Boolean falg = insertT_pcbzlogs(req.getParameter("id"), req);
		if(falg){
			int i = DaoFactory.getT_pcbzDao().update(model);
			if(i> 0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			pw.write("error");
		}
		
	}
	public void delsPcbz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		int count = ids.length;
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			for (int i = 0; i < ids.length; i++) {
				Boolean falg = insertT_pcbzlogs(ids[i], request);
				if(falg){
					count--;
				}
			}
			if(count==0){
				int res = DaoFactory.getT_pcbzDao().batchDeleteByID(idList);
				if (res > 0) {
					pw.write("删除成功！");
				}else{
					pw.write("删除失败！");
				}
			}else{
				pw.write("删除成功,添加日志失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delPcbz(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		Boolean falg = insertT_pcbzlogs(id, request);
		if(falg){
			int res = DaoFactory.getT_pcbzDao().deleteByID(id);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else{
			pw.write("删除失败！");
		}
	}
	public void addCfryjb(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_cfryjb model = new T_cfryjb();
		if(StringUtils.isNotEmpty(req.getParameter("kjr"))){
			model.setKjr(Integer.parseInt(req.getParameter("kjr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("kjj"))){
			model.setKjj(Integer.parseInt(req.getParameter("kjj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jggbj"))){
			model.setJggbj(Integer.parseInt(req.getParameter("jggbj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jggbr"))){
			model.setJggbr(Integer.parseInt(req.getParameter("jggbr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("syryj"))){
			model.setSyryj(Integer.parseInt(req.getParameter("syryj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("syryr"))){
			model.setSyryr(Integer.parseInt(req.getParameter("syryr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("czryj"))){
			model.setCzryj(Integer.parseInt(req.getParameter("czryj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("czryr"))){
			model.setCzryr(Integer.parseInt(req.getParameter("czryr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qtj"))){
			model.setQtj(Integer.parseInt(req.getParameter("qtj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qtr"))){
			model.setQtr(Integer.parseInt(req.getParameter("qtr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("user_id"))){
			model.setUser_id(req.getParameter("user_id"));
		}
		model.setCreateTime(DBService.getDateTime());
		T_cfryjb model2 = new T_cfryjb();
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			model.setNian(req.getParameter("nian"));
			model2.setNian(req.getParameter("nian"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("yue"))){
			model.setYue(req.getParameter("yue"));
			model2.setYue(req.getParameter("yue"));
		}
		List<T_cfryjb> listByPage = DaoFactory.getT_cfryjbDao().getListByPage(model2, 0, 100);
		if(listByPage!=null && listByPage.size()>0 ){
			pw.write("chongfu");
		}else{
			Object obj = DaoFactory.getT_cfryjbDao().insert(model);
			if(obj == null){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}
	}
	public void editCfryjb(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_cfryjb model = new T_cfryjb();
		String nian = "";
		String yue ="";
		String oldnian ="";
		String oldyue ="";
		if(StringUtils.isNotEmpty(req.getParameter("id"))){
			model.setId(Integer.parseInt(req.getParameter("id")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("kjr"))){
			model.setKjr(Integer.parseInt(req.getParameter("kjr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("kjj"))){
			model.setKjj(Integer.parseInt(req.getParameter("kjj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jggbj"))){
			model.setJggbj(Integer.parseInt(req.getParameter("jggbj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jggbr"))){
			model.setJggbr(Integer.parseInt(req.getParameter("jggbr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("syryj"))){
			model.setSyryj(Integer.parseInt(req.getParameter("syryj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("syryr"))){
			model.setSyryr(Integer.parseInt(req.getParameter("syryr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("czryj"))){
			model.setCzryj(Integer.parseInt(req.getParameter("czryj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("czryr"))){
			model.setCzryr(Integer.parseInt(req.getParameter("czryr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qtj"))){
			model.setQtj(Integer.parseInt(req.getParameter("qtj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qtr"))){
			model.setQtr(Integer.parseInt(req.getParameter("qtr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("user_id"))){
			model.setUser_id(req.getParameter("user_id"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			model.setNian(req.getParameter("nian"));
			nian = req.getParameter("nian");
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldnian"))){
			oldnian = req.getParameter("oldnian");
		}
		if(StringUtils.isNotEmpty(req.getParameter("yue"))){
			model.setYue(req.getParameter("yue"));
			yue = req.getParameter("yue");
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldyue"))){
			oldyue = req.getParameter("oldyue");
		}
		if(oldnian.equals(nian) && yue.equals(oldyue)){
			int i = DaoFactory.getT_cfryjbDao().update(model);
			if(i>0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			List<T_cfryjb> listByPage = DaoFactory.getT_cfryjbDao().getListByPage(model, 0, 100);
			if(listByPage!=null &&listByPage.size()>0){
				pw.write("该月份已有处分人员级别数据");
			}else{
				int i = DaoFactory.getT_cfryjbDao().update(model);
				if(i>0){
					pw.write("success");
				}else{
					pw.write("error");
				}
			}
		}
		
	}
	public void delsCfryjb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			int res = DaoFactory.getT_cfryjbDao().batchDeleteByID(idList);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delCfryjb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		int res = DaoFactory.getT_cfryjbDao().deleteByID(id);
		if (res > 0) {
			pw.write("删除成功！");
		}else{
			pw.write("删除失败！");
		}
	}
	public void addBnb(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_bnb model = new T_bnb();
		if(StringUtils.isNotEmpty(req.getParameter("jgcfrs"))){
			model.setJgcfrs(Integer.parseInt(req.getParameter("jgcfrs")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jcjdzx"))){
			model.setJcjdzx(Integer.parseInt(req.getParameter("jcjdzx")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("hfjy"))){
			model.setHfjy(Integer.parseInt(req.getParameter("hfjy")));
		}
		model.setCreateTime(DBService.getDateTime());
		T_bnb model2 = new T_bnb();
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			model.setNian(req.getParameter("nian"));
			model2.setNian(req.getParameter("nian"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("type"))){
			model.setType(Integer.parseInt(req.getParameter("type")));
			model2.setType(Integer.parseInt(req.getParameter("type")));
		}
		List<T_bnb> listByPage = DaoFactory.getT_bnbDao().getListByPage(model2, 0, 100);
		if(listByPage!=null && listByPage.size()>0 ){
			pw.write("chongfu");
		}else{
			Object obj = DaoFactory.getT_bnbDao().insert(model);
			if(obj == null){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}
	}
	public void editBnb(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_bnb model = new T_bnb();
		String nian = "";
		Integer type =0;
		String oldnian ="";
		Integer oldtype =0;
		if(StringUtils.isNotEmpty(req.getParameter("jgcfrs"))){
			model.setJgcfrs(Integer.parseInt(req.getParameter("jgcfrs")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("id"))){
			model.setId(Integer.parseInt(req.getParameter("id")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("jcjdzx"))){
			model.setJcjdzx(Integer.parseInt(req.getParameter("jcjdzx")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("hfjy"))){
			model.setHfjy(Integer.parseInt(req.getParameter("hfjy")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("user_id"))){
			model.setUser_id(req.getParameter("user_id"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			model.setNian(req.getParameter("nian"));
			nian = req.getParameter("nian");
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldnian"))){
			oldnian = req.getParameter("oldnian");
		}
		if(StringUtils.isNotEmpty(req.getParameter("type"))){
			model.setType(Integer.parseInt(req.getParameter("type")));
			type = Integer.parseInt(req.getParameter("type"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldtype"))){
			oldtype = Integer.parseInt(req.getParameter("oldtype"));
		}
		if(oldnian.equals(nian) && type.equals(oldtype)){
			int i = DaoFactory.getT_bnbDao().update(model);
			if(i>0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			List<T_bnb> listByPage = DaoFactory.getT_bnbDao().getListByPage(model, 0, 100);
			if(listByPage!=null &&listByPage.size()>0){
				pw.write("chongfu");
			}else{
				int i = DaoFactory.getT_bnbDao().update(model);
				if(i>0){
					pw.write("success");
				}else{
					pw.write("error");
				}
			}
		}
		
	}
	public void delsBnb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			int res = DaoFactory.getT_bnbDao().batchDeleteByID(idList);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delBnb(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		int res = DaoFactory.getT_bnbDao().deleteByID(id);
		if (res > 0) {
			pw.write("删除成功！");
		}else{
			pw.write("删除失败！");
		}
	}public void addAjsltj(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_ajsltj model = new T_ajsltj();
		if(StringUtils.isNotEmpty(req.getParameter("slajj"))){
			model.setSlajj(Integer.parseInt(req.getParameter("slajj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slajr"))){
			model.setSlajr(Integer.parseInt(req.getParameter("slajr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("xsshj"))){
			model.setXsshj(Integer.parseInt(req.getParameter("xsshj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ldsbj"))){
			model.setLdsbj(Integer.parseInt(req.getParameter("ldsbj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysslj"))){
			model.setYsslj(Integer.parseInt(req.getParameter("ysslj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slsyj"))){
			model.setSlsyj(Integer.parseInt(req.getParameter("slsyj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slsyr"))){
			model.setSlsyr(Integer.parseInt(req.getParameter("slsyr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsqwj"))){
			model.setQsqwj(Integer.parseInt(req.getParameter("qsqwj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsqwr"))){
			model.setQsqwr(Integer.parseInt(req.getParameter("qsqwr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsswj"))){
			model.setQsswj(Integer.parseInt(req.getParameter("qsswj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsswr"))){
			model.setQsswr(Integer.parseInt(req.getParameter("qsswr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("sjj"))){
			model.setSjj(Integer.parseInt(req.getParameter("sjj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("djcfj"))){
			model.setDjcfj(Integer.parseInt(req.getParameter("djcfj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("djcfr"))){
			model.setDjcfr(Integer.parseInt(req.getParameter("djcfr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("sjr"))){
			model.setSjr(Integer.parseInt(req.getParameter("sjr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysjcj"))){
			model.setYsjcj(Integer.parseInt(req.getParameter("ysjcj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysjcr"))){
			model.setYsjcr(Integer.parseInt(req.getParameter("ysjcr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slssj"))){
			model.setSlssj(Integer.parseInt(req.getParameter("slssj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slssr"))){
			model.setSlssr(Integer.parseInt(req.getParameter("slssr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("user_id"))){
			model.setUser_id(req.getParameter("user_id"));
		}
		model.setCreateTime(DBService.getDateTime());
		T_ajsltj model2 = new T_ajsltj();
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			model.setNian(req.getParameter("nian"));
			model2.setNian(req.getParameter("nian"));
		}
		if(StringUtils.isNotEmpty(req.getParameter("yue"))){
			model.setYue(req.getParameter("yue"));
			model2.setYue(req.getParameter("yue"));
		}
		List<T_ajsltj> listByPage = DaoFactory.getT_ajsltjDao().getListByPage(model2, 0, 100);
		if(listByPage!=null && listByPage.size()>0 ){
			pw.write("chongfu");
		}else{
			Object obj = DaoFactory.getT_ajsltjDao().insert(model);
			if(obj == null){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}
	}
	public void editAjsltj(HttpServletRequest req, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		T_ajsltj model = new T_ajsltj();
		String nian = "";
		String yue ="";
		String oldnian ="";
		String oldyue ="";
		if(StringUtils.isNotEmpty(req.getParameter("slajj"))){
			model.setSlajj(Integer.parseInt(req.getParameter("slajj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slajr"))){
			model.setSlajr(Integer.parseInt(req.getParameter("slajr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("xsshj"))){
			model.setXsshj(Integer.parseInt(req.getParameter("xsshj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ldsbj"))){
			model.setLdsbj(Integer.parseInt(req.getParameter("ldsbj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysslj"))){
			model.setYsslj(Integer.parseInt(req.getParameter("ysslj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slsyj"))){
			model.setSlsyj(Integer.parseInt(req.getParameter("slsyj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slsyr"))){
			model.setSlsyr(Integer.parseInt(req.getParameter("slsyr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsqwj"))){
			model.setQsqwj(Integer.parseInt(req.getParameter("qsqwj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsqwr"))){
			model.setQsqwr(Integer.parseInt(req.getParameter("qsqwr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsswj"))){
			model.setQsswj(Integer.parseInt(req.getParameter("qsswj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("qsswr"))){
			model.setQsswr(Integer.parseInt(req.getParameter("qsswr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("sjj"))){
			model.setSjj(Integer.parseInt(req.getParameter("sjj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("djcfj"))){
			model.setDjcfj(Integer.parseInt(req.getParameter("djcfj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("djcfr"))){
			model.setDjcfr(Integer.parseInt(req.getParameter("djcfr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("sjr"))){
			model.setSjr(Integer.parseInt(req.getParameter("sjr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysjcj"))){
			model.setYsjcj(Integer.parseInt(req.getParameter("ysjcj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("ysjcr"))){
			model.setYsjcr(Integer.parseInt(req.getParameter("ysjcr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slssj"))){
			model.setSlssj(Integer.parseInt(req.getParameter("slssj")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("id"))){
			model.setId(Integer.parseInt(req.getParameter("id")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("slssr"))){
			model.setSlssr(Integer.parseInt(req.getParameter("slssr")));
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldnian"))){
			oldnian = req.getParameter("oldnian");
		}
		if(StringUtils.isNotEmpty(req.getParameter("nian"))){
			nian = req.getParameter("nian");
			model.setNian(nian);
		}
		if(StringUtils.isNotEmpty(req.getParameter("yue"))){
			yue = req.getParameter("yue");
			model.setYue(yue);
		}
		if(StringUtils.isNotEmpty(req.getParameter("oldyue"))){
			oldyue = req.getParameter("oldyue");
		}
		if(oldnian.equals(nian) && yue.equals(oldyue)){
			int i = DaoFactory.getT_ajsltjDao().update(model);
			if(i>0){
				pw.write("success");
			}else{
				pw.write("error");
			}
		}else{
			List<T_ajsltj> listByPage = DaoFactory.getT_ajsltjDao().getListByPage(model, 0, 100);
			if(listByPage!=null &&listByPage.size()>0){
				pw.write("chongfu");
			}else{
				int i = DaoFactory.getT_ajsltjDao().update(model);
				if(i>0){
					pw.write("success");
				}else{
					pw.write("error");
				}
			}
		}
		
	}
	public void delsAjsltj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String[] ids = request.getParameterValues("ids");
		if (ids != null && ids.length > 0) {
			List<String> idList = Arrays.asList(ids);
			int res = DaoFactory.getT_ajsltjDao().batchDeleteByID(idList);
			if (res > 0) {
				pw.write("删除成功！");
			}else{
				pw.write("删除失败！");
			}
		}else {
			pw.write("删除失败！");
		}
	}
	public void delAjsltj(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");
		int res = DaoFactory.getT_ajsltjDao().deleteByID(id);
		if (res > 0) {
			pw.write("删除成功！");
		}else{
			pw.write("删除失败！");
		}
	}
	public void findTjByTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year_s = request.getParameter("year_s");
		String month_s = request.getParameter("month_s");
		String year_e = request.getParameter("year_e");
		String month_e = request.getParameter("month_e");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(year_s)){
			if(StringUtils.isNotEmpty(month_s)){
				map.put("year_s", year_s+"-"+month_s);
			}else{
				map.put("year_s", year_s+"-1");
			}
		}else{
			map.put(sdf.format(new Date())
			, year_s+"-1");
		}
		if(StringUtils.isNotEmpty(year_e)){
			map.put("year_e", year_e);
			if(StringUtils.isNotEmpty(month_e)){
				map.put("year_e", year_e+"-"+month_e);
			}else{
				map.put("year_e", year_e+"-4");
			}
		}else{
			map.put(sdf.format(new Date())
					, year_s+"-1");
		}
		
		
		//获取统计数据开始---begin----
		List<Map<String, String>> list = DaoFactory.getT_ajsltjDao().getAjsltjByTime(map);
		 
		//String ccwts = "",xm="";
		String[] title_arr=new String[11];
		 
	 
		//获取统计数据开始---end----
		
		//构建数据开始--begin--
		List<Map<String, String>> listMap =new ArrayList<Map<String,String>>();
		if(list!= null && list.size()>0){
			 
			Map<String, String> itemMap= list.get(0);
			//ccwts = itemMap.get("dyccwts")+"";
			
			title_arr[0]="受理案件";
			Map<String, String> map1=new HashMap<String, String>();
			map1.put("name", "受理案件");
			if(itemMap.get("slajj") != null){
				map1.put("value", itemMap.get("slajj")+"");
			}else{
				map1.put("value", "0");
			}
			if(itemMap.get("slajr") != null){
				map1.put("rs", itemMap.get("slajr")+"");
			}else{
				map1.put("rs", "0");
			}
			
			listMap.add(map1);
			title_arr[1]="形式审核件";
			Map<String, String> map2=new HashMap<String, String>();
			map2.put("name", "形式审核件");
			if(itemMap.get("xsshj") != null){
				map2.put("value", itemMap.get("xsshj")+"");
			}else{
				map2.put("value", "0");
			}
			map2.put("rs", "0");
			listMap.add(map2);
			title_arr[2]="科级别干部立案领导审批件";
			Map<String, String> map3=new HashMap<String, String>();
			map3.put("name", "科级别干部立案领导审批件");
			if(itemMap.get("ldsbj") != null){
				map3.put("value", itemMap.get("ldsbj")+"");
			}else{
				map3.put("value", "0");
			} 
			map3.put("rs", "0");
			listMap.add(map3);
			
			title_arr[3]="正式移送审理";
			Map<String, String> map4=new HashMap<String, String>();
			map4.put("name", "正式移送审理");
			if(itemMap.get("ysslj") != null){
				map4.put("value", itemMap.get("ysslj")+"");
			}else{
				map4.put("value", "0");
			}
			map4.put("rs", "0");
			listMap.add(map4);
			
			title_arr[4]="审理报告提交常委会或委务会审议";
			Map<String, String> map5=new HashMap<String, String>();
			map5.put("name", "审理报告提交常委会或委务会审议件");
			if(itemMap.get("gzjl_j") != null){
				map5.put("value", itemMap.get("slsyj")+"");
			}else{
				map5.put("value","0");
			}
			if(itemMap.get("slsyr") != null){
				map5.put("rs", itemMap.get("slsyr")+"");
			}else{
				map5.put("rs", "0");
			}
			listMap.add(map5);
			 
			title_arr[5]="请示区委";
			Map<String, String> map6=new HashMap<String, String>();
			map6.put("name", "请示区委");
			if(itemMap.get("qsqwj") != null){
				map6.put("value", itemMap.get("qsqwj")+"");
			}else{
				map6.put("value", "0");
			}
			if(itemMap.get("qsqwr") != null){
				map6.put("rs", itemMap.get("qsqwr")+"");
			}else {
				map6.put("rs", "0");
			}
			
			listMap.add(map6);
			
			title_arr[6]="请示市委";
			Map<String, String> map7=new HashMap<String, String>();
			map7.put("name", "请示市委");
			if(itemMap.get("qsswj") != null){
				map7.put("value", itemMap.get("qsswj")+"");
			}else{
				map7.put("value", "0");
			}
			if(itemMap.get("qsswr") != null){
				map7.put("rs", itemMap.get("qsswr")+"");
			}else {
				map7.put("rs", "0");
			}
			
			listMap.add(map7);
			
			title_arr[7]="审结";
			Map<String, String> map8=new HashMap<String, String>();
			map8.put("name", "审结");
			if(itemMap.get("sjj") !=null){
				map8.put("value", itemMap.get("sjj")+"");
			}else{
				map8.put("value", "0");
			}
			if(itemMap.get("sjr") != null){
				map8.put("rs", itemMap.get("sjr")+"");
			}else{
				map8.put("rs","0");
			}
			
			listMap.add(map8);
			
			title_arr[8]="党纪政务处分";
			Map<String, String> map9=new HashMap<String, String>();
			map9.put("name", "党纪政务处分");
			if(itemMap.get("djcfj") !=null){
				map9.put("value", itemMap.get("djcfj")+"");
			}else{
				map9.put("value", "0");
			}
			if(itemMap.get("djcfr") != null){
				map9.put("rs", itemMap.get("djcfr")+"");
			}else{
				map9.put("rs","0");
			}
			
			listMap.add(map9);
			
			title_arr[9]="移送监察机关审查公诉";
			Map<String, String> map10=new HashMap<String, String>();
			map10.put("name", "移送监察机关审查公诉");
			if(itemMap.get("ysjcj") !=null){
				map10.put("value", itemMap.get("ysjcj")+"");
			}else{
				map10.put("value", "0");
			}
			if(itemMap.get("ysjcr") != null){
				map10.put("rs", itemMap.get("ysjcr")+"");
			}else{
				map10.put("rs","0");
			}
			
			listMap.add(map10);
			
			title_arr[10]="审理诉讼";
			Map<String, String> map11=new HashMap<String, String>();
			map11.put("name", "审理诉讼");
			if(itemMap.get("slssj") !=null){
				map11.put("value", itemMap.get("slssj")+"");
			}else{
				map11.put("value", "0");
			}
			if(itemMap.get("slssr") != null){
				map11.put("rs", itemMap.get("slssr")+"");
			}else{
				map11.put("rs","0");
			}
			
			listMap.add(map11);
		} 
		
		//构建数据结束--end--
		Map jsonMap = new HashMap();
		jsonMap.put("name", "案件审理统计");
		jsonMap.put("sub_title", "");
		jsonMap.put("arr", listMap);
		jsonMap.put("title_arr", title_arr);
		String json = JsonTransform.map2json(jsonMap);
		PrintWriter out = response.getWriter();  
	    out.write(json);
	    out.flush();
	    out.close();
	}
	public void findCfryjbByTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year_s = request.getParameter("year_s");
		String month_s = request.getParameter("month_s");
		String year_e = request.getParameter("year_e");
		String month_e = request.getParameter("month_e");
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(year_s)){
			if(StringUtils.isNotEmpty(month_s)){
				map.put("year_s", year_s+"-"+month_s+"-01");
			}else{
				map.put("year_s", year_s+"-1-01");
			}
		}else{
			map.put("year_s",sdf.format(new Date())+"-1-01");
		}
		if(StringUtils.isNotEmpty(year_e)){
			if(StringUtils.isNotEmpty(month_e)){
				map.put("year_e", year_e+"-"+month_e+"-31");
			}else{
				map.put("year_e", year_e+"-12-31");
			}
		}else{
			map.put("year_e",sdf.format(new Date())+"-12-31");
		}
		
		
		//获取统计数据开始---begin----
		List<Map<String, Object>> list = DaoFactory.getT_cfryjbDao().getCfryjbByTime(map);
		 
		String[] title_arr=new String[5];
		 
		//获取统计数据开始---end----
		
		//构建数据开始--begin--
		List<Map<String, String>> listMap =new ArrayList<Map<String,String>>();
		if(list!= null && list.size()>0){
			 
			Map<String, Object> itemMap= list.get(0);
			title_arr[0]="科级人员";
			Map<String, String> map1=new HashMap<String, String>();
			map1.put("name", "科级人员");
			if(itemMap.get("kjj") != null){
				map1.put("value", itemMap.get("kjj")+"");
			}else{
				map1.put("value", "0");
			}
			if(itemMap.get("kjr") != null){
				map1.put("rs", itemMap.get("kjr")+"");
			}else{
				map1.put("rs", "0");
			}
			
			listMap.add(map1);
			title_arr[1]="机关干部";
			Map<String, String> map2=new HashMap<String, String>();
			map2.put("name", "机关干部");
			if(itemMap.get("jggbj") != null){
				map2.put("value", itemMap.get("jggbj")+"");
			}else{
				map2.put("value", "0");
			}
			if(itemMap.get("jggbr") != null){
				map2.put("rs", itemMap.get("jggbr")+"");
			}else{
				map2.put("rs", "0");
			}
			listMap.add(map2);
			title_arr[2]="事业人员";
			Map<String, String> map3=new HashMap<String, String>();
			map3.put("name", "事业人员");
			if(itemMap.get("syryj") != null){
				map3.put("value", itemMap.get("syryj")+"");
			}else{
				map3.put("value", "0");
			} 
			if(itemMap.get("syryr") != null){
				map2.put("rs", itemMap.get("syryr")+"");
			}else{
				map2.put("rs", "0");
			}
			listMap.add(map3);
			
			title_arr[3]="村组人员";
			Map<String, String> map4=new HashMap<String, String>();
			map4.put("name", "村组人员");
			if(itemMap.get("czryj") != null){
				map4.put("value", itemMap.get("czryj")+"");
			}else{
				map4.put("value", "0");
			}
			if(itemMap.get("czryr") != null){
				map2.put("rs", itemMap.get("czryr")+"");
			}else{
				map2.put("rs", "0");
			}
			listMap.add(map4);
			
			title_arr[4]="其他";
			Map<String, String> map5=new HashMap<String, String>();
			map5.put("name", "其他");
			if(itemMap.get("qtj") != null){
				map5.put("value", itemMap.get("qtj")+"");
			}else{
				map5.put("value","0");
			}
			if(itemMap.get("qtr") != null){
				map5.put("rs", itemMap.get("qtr")+"");
			}else{
				map5.put("rs", "0");
			}
			listMap.add(map5);
		} 
		
		//构建数据结束--end--
		Map jsonMap = new HashMap();
		jsonMap.put("name", "处分人员级别统计");
		jsonMap.put("sub_title", "");
		jsonMap.put("arr", listMap);
		jsonMap.put("title_arr", title_arr);
		String json = JsonTransform.map2json(jsonMap);
		PrintWriter out = response.getWriter();  
	    out.write(json);
	    out.flush();
	    out.close();
	}
	public void findBnbByTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year_s = request.getParameter("year_s");
		String month_s = request.getParameter("month_s");
		String year_e = request.getParameter("year_e");
		String month_e = request.getParameter("month_e");
		Map<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(year_s)){
			if(StringUtils.isNotEmpty(month_s)){
				map.put("year_s", year_s+"-"+month_s);
			}else{
				map.put("year_s", year_s+"-0");
			}
		}else{
			map.put("year_s",sdf.format(new Date())+"-0");
		}
		if(StringUtils.isNotEmpty(year_e)){
			if(StringUtils.isNotEmpty(month_e)){
				map.put("year_e", year_e+"-"+month_e);
			}else{
				map.put("year_e", year_e+"-1");
			}
		}else{
			map.put("year_",sdf.format(new Date())+"-1");
		}
		
		
		//获取统计数据开始---begin----
		List<Map<String, Object>> list = DaoFactory.getT_bnbDao().getBnbByTime(map);
		 
		String[] title_arr=new String[3];
		 
		//获取统计数据开始---end----
		
		//构建数据开始--begin--
		List<Map<String, String>> listMap =new ArrayList<Map<String,String>>();
		if(list!= null && list.size()>0){
			 
			Map<String, Object> itemMap= list.get(0);
			title_arr[0]="机关自办案件受处分人数";
			Map<String, String> map1=new HashMap<String, String>();
			map1.put("name", "机关自办案件受处分人数");
			map1.put("value", "0");
			if(itemMap.get("jgcfrs") != null){
				map1.put("rs", itemMap.get("jgcfrs")+"");
			}else{
				map1.put("rs", "0");
			}
			
			listMap.add(map1);
			title_arr[1]="监察处分决定执行情况人数";
			Map<String, String> map2=new HashMap<String, String>();
			map2.put("name", "监察处分决定执行情况人数");
			if(itemMap.get("jcjdzx") != null){
				map2.put("rs", itemMap.get("jcjdzx")+"");
			}else{
				map2.put("rs", "0");
			}
			map2.put("value", "0");
			listMap.add(map2);
			title_arr[2]="回访教育人数";
			Map<String, String> map3=new HashMap<String, String>();
			map3.put("name", "回访教育人数");
			if(itemMap.get("hfjy") != null){
				map3.put("rs", itemMap.get("hfjy")+"");
			}else{
				map3.put("rs", "0");
			} 
			map3.put("value", "0");
			listMap.add(map3);
			
		} 
		
		//构建数据结束--end--
		Map jsonMap = new HashMap();
		jsonMap.put("name", "半年报统计");
		jsonMap.put("sub_title", "");
		jsonMap.put("arr", listMap);
		jsonMap.put("title_arr", title_arr);
		String json = JsonTransform.map2json(jsonMap);
		PrintWriter out = response.getWriter();  
	    out.write(json);
	    out.flush();
	    out.close();
	}
	
	public Boolean insertT_gzlclogs(String id,HttpServletRequest req){
		Map user = (Map) req.getSession().getAttribute("user");
		String user_id ="";
		if(user!=null){
			user_id = (String) user.get("user_id");
		}
		T_gzlc model = new T_gzlc();
		model.setId(Integer.parseInt(id));
		List<T_gzlc> list = DaoFactory.getT_gzlcDao().getListByPage(model, 0, 1);
		if(list!=null && list.size()>0){
			T_gzlc t_gzlc = (T_gzlc)list.get(0);
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("gzlc_id", id);
			paramMap.put("fileName", t_gzlc.getFileName());
			paramMap.put("filePath", t_gzlc.getFilePath());
			paramMap.put("oldName", t_gzlc.getOldName());
			paramMap.put("gzlc_createTime", t_gzlc.getCreateTime());
			paramMap.put("gzlc_user_id", t_gzlc.getUser_id());
			paramMap.put("createTime", DBService.getTime());
			paramMap.put("user_id", user_id);
			int t_gzlclogs = DaoFactory.getT_logsDao().insertT_gzlclogs(paramMap);
			if(t_gzlclogs>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public Boolean insertT_nbgllogs(String id,HttpServletRequest req){
		Map user = (Map) req.getSession().getAttribute("user");
		String user_id ="";
		if(user!=null){
			user_id = (String) user.get("user_id");
		}
		T_nbgl model = new T_nbgl();
		model.setId(Integer.parseInt(id));
		List<T_nbgl> list = DaoFactory.getT_nbglDao().getListByPage(model, 0, 1);
		if(list!=null && list.size()>0){
			T_nbgl t_nbgl = (T_nbgl)list.get(0);
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("nbgl_id", id);
			paramMap.put("ajlx", t_nbgl.getAjlx());
			paramMap.put("fileName", t_nbgl.getFileName());
			paramMap.put("filePath", t_nbgl.getFilePath());
			paramMap.put("nbgl_createTime", t_nbgl.getCreateTime());
			paramMap.put("nbgl_userid", t_nbgl.getUser_id());
			paramMap.put("createTime", DBService.getTime());
			paramMap.put("user_id", user_id);
			int logs = DaoFactory.getT_logsDao().insertT_nbgllogs(paramMap);
			if(logs>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public Boolean insertT_pcbzlogs(String id,HttpServletRequest req){
		Map user = (Map) req.getSession().getAttribute("user");
		String user_id ="";
		if(user!=null){
			user_id = (String) user.get("user_id");
		}
		T_pcbz model = new T_pcbz();
		model.setId(Integer.parseInt(id));
		List<T_pcbz> list = DaoFactory.getT_pcbzDao().getListByPage(model, 0, 1);
		if(list!=null && list.size()>0){
			T_pcbz t_pcbz = (T_pcbz)list.get(0);
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("pcbz_id", id);
			paramMap.put("ajlx", t_pcbz.getAjlx());
			paramMap.put("pcbz", t_pcbz.getPcbz());
			paramMap.put("pcbzPath", t_pcbz.getPcbzPath());
			paramMap.put("pcbz_userid", t_pcbz.getUser_id());
			paramMap.put("pcbz_createTime", t_pcbz.getCreateTime());
			paramMap.put("createTime", DBService.getTime());
			paramMap.put("user_id", user_id);
			int logs = DaoFactory.getT_logsDao().insertT_pcbzlogs(paramMap);
			if(logs>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public Boolean insertT_yzajlogs(String id,HttpServletRequest req){
		Map user = (Map) req.getSession().getAttribute("user");
		String user_id ="";
		if(user!=null){
			user_id = (String) user.get("user_id");
		}
		T_yzaj model = new T_yzaj();
		model.setId(Integer.parseInt(id));
		List<T_yzaj> list = DaoFactory.getT_yzajDao().getListByPage(model, 0, 1);
		if(list!=null && list.size()>0){
			T_yzaj t_yzaj = (T_yzaj)list.get(0);
			HashMap<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("yzaj_id", id);
			paramMap.put("ajlx", t_yzaj.getAjlx());
			paramMap.put("ajmc", t_yzaj.getAjmc());
			paramMap.put("aqjj", t_yzaj.getAqjj());
			paramMap.put("aqjjPath", t_yzaj.getAqjjPath());
			paramMap.put("fxdpPath", t_yzaj.getFxdpPath());
			paramMap.put("fxdp", t_yzaj.getFxdp());
			paramMap.put("yzaj_userid", t_yzaj.getUser_id());
			paramMap.put("yzaj_createTime", t_yzaj.getCreateTime());
			paramMap.put("createTime", DBService.getTime());
			paramMap.put("user_id", user_id);
			int logs = DaoFactory.getT_logsDao().insertT_yzajlogs(paramMap);
			if(logs>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public void getBnbStatic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year_s = request.getParameter("year_s");
		String year_e = request.getParameter("year_e");
		String month_s = request.getParameter("month_s");
		String month_e = request.getParameter("month_e");
		HashMap<String, String> aMap = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(year_s)){
			if(StringUtils.isNotEmpty(month_s)){
				aMap.put("year_s", year_s+"-"+month_s);
			}else{
				aMap.put("year_s", year_s+"-0");
			}
		}else{
			aMap.put("year_s",sdf.format(new Date())+"-0");
		}
		if(StringUtils.isNotEmpty(year_e)){
			if(StringUtils.isNotEmpty(month_e)){
				aMap.put("year_e", year_e+"-"+month_e);
			}else{
				aMap.put("year_e", year_e+"-1");
			}
		}else{
			aMap.put("year_e",sdf.format(new Date())+"-1");
		}
		 
		//获取数据
		List<Map<String, Object>> list = DaoFactory.getT_bnbDao().getBnbByTime(aMap);
		int size = list.size();
		String[] orgnameStrs=new String[size]; 
		String[] jgcfrsvValues = new String[size];
		String[] jcjdzxValues = new String[size];
		String[] hfjyValues = new String[size];
		for(int i=0;i<list.size();i++){
			HashMap<String, Object> item = (HashMap<String, Object>) list.get(i);

			orgnameStrs[i] = "半年报统计";
			
			if(item.get("jgcfrs")!= null){
				jgcfrsvValues[i] = item.get("jgcfrs")+"";
			}else{
				jgcfrsvValues[i] = "0";
			}
			
			if(item.get("jcjdzx")!= null){
				jcjdzxValues[i] = item.get("jcjdzx")+"";
			}else{
				jcjdzxValues[i] = "0";
			}
			
			if(item.get("hfjy")!= null){
				hfjyValues[i] = item.get("hfjy")+"";
			}else{
				hfjyValues[i] = "0";
			}
		}
		//构建数据开始--begin--
		List<Map<String, Object>> listMap =new ArrayList<Map<String,Object>>();
		Map<String, Object> map1=new HashMap<String, Object>();
		map1.put("name", "机关自办案件受处分人数");
		map1.put("type", "bar");
		map1.put("data",  jgcfrsvValues);
		listMap.add(map1);
		
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("name", "检查处分决定执行情况人数");
		map2.put("type", "bar");
		map2.put("data",  jcjdzxValues);
		listMap.add(map2);
		
		Map<String, Object> map3=new HashMap<String, Object>();
		map3.put("name", "回访教育人数");
		map3.put("type", "bar");
		map3.put("data",  hfjyValues);
		listMap.add(map3);
		
		Map jsonMap = new HashMap();
		jsonMap.put("name", "半年报统计"); 
		jsonMap.put("title_arr",orgnameStrs);
		jsonMap.put("arr", listMap);
		 
		String json = JsonTransform.map2json(jsonMap);
		PrintWriter out = response.getWriter();  
	    out.write(json);
	    out.flush();
	    out.close();
	}
}

