package com.swj.action.driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.swj.action.BaseAction;
import com.swj.model.driver.DriverBill;
import com.swj.model.driver.DriverInfo;
import com.swj.model.user.User;
import com.swj.model.user.UserAccount;
import com.swj.service.driver.DriverService;
import com.swj.service.user.UserService;

public class DriverAction extends BaseAction {

	private static final long serialVersionUID = 895222790788291556L;
	
	// ��Ϣ�б�
	private List<DriverInfo> list;
	
	// �û���Ʊ��ʷ
	private List<DriverBill> dbList;
	
	private DriverService driverService;
	
	private UserService userService;
	
	// ������Ϣ
	private DriverInfo driverInfo;
	
	// ��Ʊ����
	private String date;
	
	// ��һ�ν����־
	private String oneOnce;
	
	// ��Ʊʱ�ĳ���
	private String driverid;
	
	// Ʊ��
	private String billcode;
	
	@SuppressWarnings("unchecked")
	public String listDriverInfo() throws Exception{
		
		log.info("DriverAction.listDriverInfo");
		if(StringUtils.isBlank(oneOnce)){
			DriverInfo di = new DriverInfo();
			di = driverInfo;
			list = driverService.listDriverInfo(di);
		}
		return "listDriverInfo";
	}
	
	@SuppressWarnings("unchecked")
	public String buyBill() throws Exception{
		
		if(StringUtils.isNotBlank(oneOnce)){
			// �Ȳ�ѯ��Ʊ��driver_bill�����ѡ�����ڵĳ�Ʊ��������10Ʊ����Լ������򣬷�����ʾƱ������
			DriverBill driverBill = new DriverBill();
			driverBill.setCreatetime(date);
			driverBill.setDriverid(driverid);
			int count = driverService.selectDbCount(driverBill);
			if(count >= 20){
				// Ʊ������
				return "buyFailed";
			}else{
				// ������Թ�Ʊ�����ó��ε�Ʊ����ʾ��ҳ����
				this.getRequest().put("date", date);
				this.getRequest().put("driverid", driverid);
				DriverInfo di = new DriverInfo();
				di.setId(driverid);
				List list = driverService.listDriverInfo(di);
				if(list != null && list.size()>0){
					driverInfo = (DriverInfo)list.get(0);
				}
			}
		}else{
			// ��Ʊ�ɹ��������ݱ��浽��Ʊ����
			System.out.println(driverInfo.getPrice_first());
			System.out.println(driverInfo.getPrice_second());
			System.out.println(date);
			// ��ȡ��ǰ��¼�û���Ϣ
			Object pri = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(pri instanceof UserDetails){
				User u = userService.selectUser(((UserDetails)pri).getUsername());
				// ����Ʊ�ݣ�ͬʱ�۳����û��Ĺ�Ʊ��
				UserAccount userAccount = userService.viewAccount(u.getId());
				DriverBill db = new DriverBill();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				db.setBillcode(driverid+sdf.format(new Date()));
				db.setCreatetime(date);
				db.setDriverid(driverid);
				if(driverInfo.getPrice_first() != null){
					db.setPrice(driverInfo.getPrice_first());
					userAccount.setNowPrice(userAccount.getNowPrice()-driverInfo.getPrice_first());
					userAccount.setHistoryPrice(userAccount.getHistoryPrice()+driverInfo.getPrice_first());
				}
				if(driverInfo.getPrice_second() != null){
					db.setPrice(driverInfo.getPrice_second());
					userAccount.setNowPrice(userAccount.getNowPrice()-driverInfo.getPrice_second());
					userAccount.setHistoryPrice(userAccount.getHistoryPrice()+driverInfo.getPrice_second());
				}
				userService.plusAccount(userAccount);
				db.setUserid(u.getId());
				driverService.saveDriverBill(db);
			}
			return "buySuccess";
		}
		return "buyBill";
	}
	
	@SuppressWarnings("unchecked")
	public String showUserBill()throws Exception{
		
		// ��ȡ��ǰ��¼�û���Ϣ
		Object pri = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(pri instanceof UserDetails){
			User u = userService.selectUser(((UserDetails)pri).getUsername());
			DriverBill db = new DriverBill();
			db.setUserid(u.getId());
			dbList = driverService.viewUserBill(db);
		}
		return "showUserBill";
	}
	
	public String retreatBill() throws Exception{
		
		// ��ת����Ʊҳ��
		return "retreatBill";
	}
	
	public String retreatBillEnd() throws Exception{
		
		if(StringUtils.isNotBlank(billcode)){
			DriverBill driverBill = driverService.getDriverBill(billcode);
			if(driverBill != null){
				// ��ȡ��ǰ��¼�û���Ϣ
				Object pri = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if(pri instanceof UserDetails){
					User u = userService.selectUser(((UserDetails)pri).getUsername());
					UserAccount userAccount = userService.viewAccount(u.getId());
					userAccount.setHistoryPrice(userAccount.getHistoryPrice()-(driverBill.getPrice()*4/5));
					userAccount.setNowPrice(userAccount.getNowPrice()+(driverBill.getPrice()*4/5));
					userService.plusAccount(userAccount);
				}
				driverService.delDriverBill(driverBill.getId());
			}else{
				// Ʊ������
				return "retreatBillFailed";
			}
		}
		return "retreatBillSuccess";
	}
	
	public String getOneOnce() {
		return oneOnce;
	}

	public void setOneOnce(String oneOnce) {
		this.oneOnce = oneOnce;
	}

	public DriverService getDriverService() {
		return driverService;
	}

	public void setDriverService(DriverService driverService) {
		this.driverService = driverService;
	}

	public List<DriverInfo> getList() {
		return list;
	}

	public void setList(List<DriverInfo> list) {
		this.list = list;
	}
	
	public DriverInfo getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(DriverInfo driverInfo) {
		this.driverInfo = driverInfo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<DriverBill> getDbList() {
		return dbList;
	}

	public void setDbList(List<DriverBill> dbList) {
		this.dbList = dbList;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

}