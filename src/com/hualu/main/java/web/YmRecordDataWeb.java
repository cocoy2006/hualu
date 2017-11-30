package com.hualu.main.java.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.hualu.main.java.entity.RecordTask;
import com.hualu.main.java.service.JDBCMySQLService;
import com.hualu.main.java.service.RecordTaskService;
import com.hualu.main.java.util.Constants;
import com.hualu.main.java.util.Hualu;
import com.hualu.test.java.component.BpComponent;
import com.hualu.test.java.component.RateComponent;
import com.hualu.test.java.component.RateOfFloatComponent;
import com.hualu.test.java.component.Record;

@Controller
@RequestMapping(value = "/ymRecordData")
public class YmRecordDataWeb implements ServletContextAware {
	
	private static final int SECONDS = 8; // 8 seconds data per page/每页显示8秒钟内容
	
	@Autowired
	private RecordTaskService recordTaskService;
	
	@RequestMapping(value = "/ecgwaveChart/{taskid}/{page}/")
	public ModelAndView ecgwaveChart(HttpServletRequest request,
			HttpSession session, @PathVariable int taskid, @PathVariable int page) throws Exception {
		ModelAndView mav = new ModelAndView("common/ecgwaveChart");
		// find record task
		RecordTask rt = recordTaskService.findById(taskid);
		// find ids
		String ids = rt.getEcgwave1ids();
		if(ids != null) {
			if(rt.getPieceids() != null) {
				ids = rt.getPieceids();
			}
			// calculate totalPages -- start
			String[] idss = ids.split(",");
			int size = idss.length;
			int totalPages = size / SECONDS;
			if(size % SECONDS != 0) {
				totalPages++;
			}
			// calculate totalPages -- end
			int diffRecordsCount = size - ((page - 1) * SECONDS);
			if(diffRecordsCount > SECONDS) {
				diffRecordsCount = SECONDS;
			}
			int startRecord = (page - 1) * SECONDS;
			List<String> ecgwaveTime = new ArrayList<String>();
			ArrayList<Integer> data1 = new ArrayList<Integer>(), data2 = new ArrayList<Integer>(), data3 = new ArrayList<Integer>(),
				data4 = new ArrayList<Integer>(), data5 = new ArrayList<Integer>(), data6 = new ArrayList<Integer>(), data7 = new ArrayList<Integer>();
			int base = Constants.ECGWAVE_POINTS_DEMO, ecgwaveLength = base;
			StringBuffer sb = new StringBuffer();
			for(int i = startRecord; i < startRecord + diffRecordsCount; i++) {
				sb.append(idss[i]).append(",");
			}
			// calculate the day
			String day = Hualu.getLiteDate(rt.getStarttime().getTime());
			for(Record record : JDBCMySQLService.findRecordTask(day, sb.toString())) {
				// ecgwave1 chart data -- start/心电图
				String ecgwave1 = record.getEcgwave1();
				if(ecgwave1 != null) {
					base = Constants.ECGWAVE_POINTS_DEMO;
					if(ecgwave1.length() == 14000) {
						base = Constants.ECGWAVE_POINTS_REAL;
						ecgwaveLength = base;
					}
					data1.addAll(parse(ecgwave1.substring(0, base * 4), 7));
					data2.addAll(parse(ecgwave1.substring(base * 4, base * 8), 6));
					data3.addAll(parse(ecgwave1.substring(base * 8, base * 12), 5));
					data4.addAll(parse(ecgwave1.substring(base * 12, base * 16), 4));
					data5.addAll(parse(ecgwave1.substring(base * 16, base * 20), 3));
					data6.addAll(parse(ecgwave1.substring(base * 20, base * 24), 2));
					data7.addAll(parse(ecgwave1.substring(base * 24, base * 28), 1));
					for(int j = 0; j < base; j++) {
						ecgwaveTime.add(Hualu.getStandardDatetime(new Date(record.getRdatetime().getTime())));
					}
				}
			}
			mav.addObject("ecgwaveLength", ecgwaveLength);
			mav.addObject("ecgwaveTime", ecgwaveTime);
			List<ArrayList<Integer>> ecgwaveData = new ArrayList<ArrayList<Integer>>();
			ecgwaveData.add(data1); ecgwaveData.add(data2); ecgwaveData.add(data3);
			ecgwaveData.add(data4); ecgwaveData.add(data5); ecgwaveData.add(data6); ecgwaveData.add(data7);
			mav.addObject("ecgwaveData", ecgwaveData); // ecgwave1/心电图
		}
		return mav;
	}
	
	private ArrayList<Integer> parse(String ecgwave, int base) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int begin = 0, end = 0, length = ecgwave.length();
		do {
			end += 4;
			String hex = ecgwave.substring(begin, end);
			hex = hex.substring(2, 4).concat(hex.substring(0, 2));
			int value = Integer.valueOf(hex, 16);
			if(value != 0) {
				value -= 32768;
				if(length == Constants.ECGWAVE_POINTS_REAL * 4) {
					value = (int) Hualu.setEcgAccuracy(value * 4096 / 204 / 65536D);
				} else if(length == Constants.ECGWAVE_POINTS_DEMO * 4) {
					value = (int) Hualu.setEcgAccuracy(value * 256 / 120 / 65536D);
				}
			}
			value += base * 150; // addBase()
			list.add(value);
			begin = end;
		} while(end < length);
		return list;
	}
	
	
	@RequestMapping(value = "/detail/{taskid}/")
	public ModelAndView detail(HttpSession session, @PathVariable int taskid) throws Exception {
		ModelAndView mav = new ModelAndView("common/record_detail");
		List<RateComponent> heartrateData = new ArrayList<RateComponent>();
		List<RateComponent> breathrateData = new ArrayList<RateComponent>();
		List<RateComponent> spo2valueData = new ArrayList<RateComponent>();
		List<RateComponent> pluserateData = new ArrayList<RateComponent>();
		List<RateComponent> bprateData = new ArrayList<RateComponent>();
		List<RateOfFloatComponent> temp1Data = new ArrayList<RateOfFloatComponent>();
		List<BpComponent> bpData = new ArrayList<BpComponent>();
		// find record task
		RecordTask task = recordTaskService.findById(taskid);
		mav.addObject("task", task);
		// calculate the day
		String day = Hualu.getLiteDate(task.getStarttime().getTime());
		Stack<Record> heartrateStack = new Stack<Record>(), breathrateStack = new Stack<Record>(),
			spo2valueStack = new Stack<Record>(), pluserateStack = new Stack<Record>(),
			bpStack = new Stack<Record>(), bprateStack = new Stack<Record>(),
			temp1Stack = new Stack<Record>();
		for(Record record : JDBCMySQLService.findRecordTask(day, task.getIds())) {
			// heartrate chart data -- start/心率
			Integer heartrate = record.getHeartrate();
			if(!heartrateStack.isEmpty() && 
					(heartrate == null || heartrate.compareTo(heartrateStack.peek().getHeartrate()) != 0)) {
				heartrateData.add(getHeartrateComponent(heartrateStack));
				heartrateStack.clear();
			}
			if(Hualu.isLegalData(heartrate)) {
				heartrateStack.push(record);
			}
			// breathrate chart data -- start/呼吸率
			Integer breathrate = record.getBreathrate();
			if(!breathrateStack.isEmpty() && 
					(breathrate == null || breathrate.compareTo(breathrateStack.peek().getBreathrate()) != 0)) {
				breathrateData.add(getBreathrateComponent(breathrateStack));
				breathrateStack.clear();
			}
			if(Hualu.isLegalData(breathrate)) {
				breathrateStack.push(record);
			}
			// spo2value chart data -- start/血氧
			Integer spo2value = record.getSpo2value();
			if(!spo2valueStack.isEmpty() && 
					(spo2value == null || spo2value.compareTo(spo2valueStack.peek().getSpo2value()) != 0)) {
				spo2valueData.add(getSpo2valueComponent(spo2valueStack));
				spo2valueStack.clear();
			}
			if(Hualu.isLegalData(spo2value)) {
				spo2valueStack.push(record);
			}
			// pluserate chart data -- start/脉率
			Integer pluserate = record.getPluserate();
			if(!pluserateStack.isEmpty() && 
					(pluserate == null || pluserate.compareTo(pluserateStack.peek().getPluserate()) != 0)) {
				pluserateData.add(getPluserateComponent(pluserateStack));
				pluserateStack.clear();
			}
			if(Hualu.isLegalData(pluserate)) {
				pluserateStack.push(record);
			}
			// bphigh&bplow chart data -- start/血压
			Integer bphigh = record.getBphigh(), bplow = record.getBplow();
			if(!bpStack.isEmpty() && (bphigh == null || bplow == null ||
					(bphigh.compareTo(bpStack.peek().getBphigh()) != 0 &&
							bplow.compareTo(bpStack.peek().getBplow()) != 0))) {
				bpData.add(getBpComponent(bpStack));
				bpStack.clear();
			}
			if(Hualu.isLegalData(bphigh) && Hualu.isLegalData(bplow)) {
				bpStack.push(record);
			}
			// bprate chart data -- start/血压脉率
			Integer bprate = record.getBprate();
			if(!bprateStack.isEmpty() && 
					(bprate == null || bprate.compareTo(bprateStack.peek().getBprate()) != 0)) {
				bprateData.add(getBprateComponent(bprateStack));
				bprateStack.clear();
			}
			if(Hualu.isLegalData(bprate)) {
				bprateStack.push(record);
			}
			// temp1 chart data -- start/体温
			Float temp1 = record.getTemp1();
			if(!temp1Stack.isEmpty() && 
					(temp1 == null || temp1.compareTo(temp1Stack.peek().getTemp1()) != 0)) {
				temp1Data.add(getTemp1Component(temp1Stack));
				temp1Stack.clear();
			}
			if(Hualu.isLegalData(temp1)) {
				temp1Stack.push(record);
			}
		}
		if(!heartrateStack.isEmpty()) {
			heartrateData.add(getHeartrateComponent(heartrateStack));
			heartrateStack.clear();
		}
		if(!breathrateStack.isEmpty()) {
			breathrateData.add(getBreathrateComponent(breathrateStack));
			breathrateStack.clear();
		}
		if(!spo2valueStack.isEmpty()) {
			spo2valueData.add(getSpo2valueComponent(spo2valueStack));
			spo2valueStack.clear();
		}
		if(!pluserateStack.isEmpty()) {
			pluserateData.add(getPluserateComponent(pluserateStack));
			pluserateStack.clear();
		}
		if(!bpStack.isEmpty()) {
			bpData.add(getBpComponent(bpStack));
			bpStack.clear();
		}
		if(!bprateStack.isEmpty()) {
			bprateData.add(getBprateComponent(bprateStack));
			bprateStack.clear();
		}
		if(!temp1Stack.isEmpty()) {
			temp1Data.add(getTemp1Component(temp1Stack));
			temp1Stack.clear();
		}
		// ecgwave1 chart data -- end
		mav.addObject("heartrateData", heartrateData); // heartrate/心率
		// heartrate chart data -- end
		mav.addObject("breathrateData", breathrateData); // breathrate/呼吸率
		// breathrate chart data -- end
		mav.addObject("spo2valueData", spo2valueData); // spo2value/血氧
		// spo2value chart data -- end
		mav.addObject("pluserateData", pluserateData); // pluserate/脉率
		// pluserate chart data -- end
		mav.addObject("bprateData", bprateData); // bprate/血压脉率
		// bprate chart data -- end
		mav.addObject("temp1Data", temp1Data); // temp1/体温
		// temp1 chart data -- end
		mav.addObject("bpData", bpData);
		// bphigh&bplow chart data -- end //
		// page information -- start
		Object pageObj = session.getAttribute("page");
		int page = 1;
		if(pageObj != null) {
			page = Integer.valueOf(pageObj.toString());
		}
		mav.addObject("page", page);
		String ids = task.getEcgwave1ids();
		if(ids != null) {
			if(task.getPieceids() != null) {
				ids = task.getPieceids();
			}
			int size = ids.split(",").length;
			int totalPages = size / SECONDS;
			if(size % SECONDS != 0) {
				totalPages++;
			}
			// calculate totalPages -- end
			mav.addObject("totalPages", totalPages);
		}
		return mav;
	}
	
	private RateComponent getHeartrateComponent(Stack<Record> stack) {
		RateComponent rc = getRateComponent(stack);
		rc.setValue(stack.peek().getHeartrate());
		return rc;
	}
	
	private RateComponent getBreathrateComponent(Stack<Record> stack) {
		RateComponent rc = getRateComponent(stack);
		rc.setValue(stack.peek().getBreathrate());
		return rc;
	}
	
	private RateComponent getSpo2valueComponent(Stack<Record> stack) {
		RateComponent rc = getRateComponent(stack);
		rc.setValue(stack.peek().getSpo2value());
		return rc;
	}
	
	private RateComponent getPluserateComponent(Stack<Record> stack) {
		RateComponent rc = getRateComponent(stack);
		rc.setValue(stack.peek().getPluserate());
		return rc;
	}
	
	private RateComponent getBprateComponent(Stack<Record> stack) {
		RateComponent rc = getRateComponent(stack);
		rc.setValue(stack.peek().getBprate());
		return rc;
	}
	
	private RateComponent getRateComponent(Stack<Record> stack) {
		RateComponent rc = new RateComponent();
		DateFormat noDayFormat = new SimpleDateFormat("HH:mm:ss");
		String starttime = noDayFormat.format(stack.firstElement().getRdatetime());
		String endtime = noDayFormat.format(stack.lastElement().getRdatetime());
		rc.setStartTime(starttime);
		rc.setEndTime(endtime);
		return rc;
	}
	
	private BpComponent getBpComponent(Stack<Record> stack) {
		BpComponent bc = new BpComponent();
		DateFormat noDayFormat = new SimpleDateFormat("HH:mm:ss");
		String starttime = noDayFormat.format(stack.firstElement().getRdatetime());
		String endtime = noDayFormat.format(stack.lastElement().getRdatetime());
		bc.setStartTime(starttime);
		bc.setEndTime(endtime);
		bc.setBphigh(stack.peek().getBphigh());
		bc.setBplow(stack.peek().getBplow());
		return bc;
	}
	
	private RateOfFloatComponent getTemp1Component(Stack<Record> stack) {
		RateOfFloatComponent rc = new RateOfFloatComponent();
		DateFormat noDayFormat = new SimpleDateFormat("HH:mm:ss");
		String starttime = noDayFormat.format(stack.firstElement().getRdatetime());
		String endtime = noDayFormat.format(stack.lastElement().getRdatetime());
		rc.setStartTime(starttime);
		rc.setEndTime(endtime);
		rc.setValue(stack.peek().getTemp1());
		return rc;
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}