package com.hualu.main.java.util;

public class Status {

	/**
	 * common states
	 */
	public static final int SUCCESS = 1;
	public static final int ERROR = -1;
	public static final int TRUE = 1;
	public static final int FALSE = 0;
	/**
	 * password update error states
	 */
//	public static final int PASSWORD_NOT_MATCH = 2;
	public static final int ERROR_USER_EXIST = -2;
	/**
	 * device states
	 */
	public static final int DEVICE_DEFAULT = 1; // 未使用
	public static final int DEVICE_OCCUPATION = 2; // 已领用
	public static final int DEVICE_BOUND = 3; // 已绑定
	/**
	 * user states
	 */
	public static final int USER_GENDER_MALE = 1; // 男
	public static final int USER_GENDER_FEMALE = 2; // 女
	public static final int USER_GENDER_UNKNOWN = 3; // 未知

	public static final int USER_LEVEL_NORMAL = 1; // 普通
	public static final int USER_LEVEL_VIP = 2; // 贵宾

	public static final int USER_GROUP_FU = 1; // 副教授
	public static final int USER_GROUP_ZHENG = 2; // 正高级

	public static final int USER_STATUS_NOSERVICE = 0; // 未开通服务
	public static final int USER_STATUS_NORMAL = 1; // 已开通服务/正常
	public static final int USER_STATUS_FREEZED = 2; // 账号冻结（欠费）
	public static final int USER_STATUS_DELETED = 3; // 注销
	
	public static enum UserStatus {
		NO_SERVICE(0), NORMAL(1), FREEZED(2), REMOVED(3);
		private int value;

		private UserStatus(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	/**
	 * operator states
	 */
	public static final int OPERATOR_DM = 1; // Device Manager设备管理员
	public static final int OPERATOR_NURSE = 2; // 客服
	public static final int OPERATOR_NURSE_LEADER = 3; // 客服组长
	public static final int OPERATOR_NURSE_MANAGER = 4; // 客服主管
	public static final int OPERATOR_DOCTOR = 5; // 医生
	public static final int OPERATOR_ADMIN = 6; // 管理员
	
	public static enum OperatorRole {
		DM(1), NURSE(2), NURSE_LEADER(3), NURSE_MANAGER(4), DOCTOR(5), ADMIN(6);
		private int value;

		private OperatorRole(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum OperatorStatus {
		NO_SERVICE(0), NORMAL(1), REMOVED(2);
		private int value;

		private OperatorStatus(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}

	public static final int OPERATOR_ONLINE = 1; // 在线
	public static final int OPERATOR_OFFLINE = 2; // 离线
	public static final int OPERATOR_RESTING = 3; // 休息中
	/**
	 * record task states
	 */
	public static final int RECORD_TASK_RUNNING = 1; // 待检
	public static final int RECORD_TASK_DONE = 2; // 已完成
	public static final int RECORD_TASK_RECALL = 3; // 撤回中
	public static final int RECORD_TASK_WAITING_NURSE = 4; // 等待客服
	public static final int RECORD_TASK_REJECT = 5; // 驳回中
	public static final int RECORD_TASK_WAITING_DOCTOR = 6; // 等待医生
	// only for doctor
	public static final int RECORD_TASK_DRAFT = 3; // 草稿箱
	
	public static enum RecordTask {
		INIT(1, "初始化"), WAITING_NURSE(2, "等待客服"), NURSE_PROCESSING(3, "客服处理"), 
		WAITING_DOCTOR(4, "等待医生"), DOCTOR_PROCESSING(5, "医生处理"), DOCTOR_DRAFT(6, "医生草稿"), 
		REJECT(7, "驳回中"), RECALL(8, "撤回中"), MANAGER_PROCESSING(9, "客服主管处理"), 
		DONE(10, "已完成"), REMOVED(11, "已删除");
		private int value;
		private String info;

		private RecordTask(int value) {
			this.value = value;
		}
		
		private RecordTask(int value, String info) {
			this.value = value;
			this.info = info;
		}

		public int getInt() {
			return value;
		}
		
		public String getInfo() {
			return info;
		}
	}
	
	public static enum BPLevel {
		LOW(1, "建议日常生活、饮食及运动三方面加强养生保健。"), 
		FINE(2, "血压正常，请继续保持。"), 
		NORMAL(3, "血压正常，注意清淡饮食，多食水果蔬菜，适量体育锻炼，加强养生保健。"), 
		MILD(4, "建议低盐饮食、戒咖啡类饮料；加强锻炼、减重；注意休息、不宜过度睡眠；有助于降低血压，坚持早中晚监测，及时掌握血压变化，必要时就医咨询。"), 
		MEDIUM(5, "建议就医治疗，遵医嘱用药；坚持早中晚监测，及时掌握血压变化，控制血压。"), 
		SEVERE(6, "心血管疾病、心脏急症、肾病、脑卒中风险增加；增加测量频率、及时掌握血压变化。"), 
		EXTREME(7, "易发生脑出血、心力衰竭、肾功能衰竭等病变，随时可能发生生命危险。");
		private int value;
		private String advice;

		private BPLevel(int value, String advice) {
			this.value = value;
			this.advice = advice;
		}

		public int getInt() {
			return value;
		}
		
		public String getAdvice() {
			return advice;
		}
	}

	/**
	 * health task states
	 */
	public static final int HEALTH_TASK_RUNNING = 1; // 待处理
	public static final int HEALTH_TASK_DONE = 2; // 已完成
	// only for doctor
	public static final int HEALTH_TASK_DRAFT = 3; // 草稿箱

	public static enum HealthTask {
		RUNNING(1, "待处理"), DONE(2, "已完成"), DRAFT(3, "草稿箱"), REMOVED(4, "已删除");
		private int value;
		private String info;

		private HealthTask(int value) {
			this.value = value;
		}
		
		private HealthTask(int value, String info) {
			this.value = value;
			this.info = info;
		}

		public int getInt() {
			return value;
		}
		
		public String getInfo() {
			return info;
		}
	}
	/**
	 * report task states
	 */
	public static final int REPORT_TASK_RUNNING = 1; // 待处理
	public static final int REPORT_TASK_DONE = 2; // 已完成
	public static final int REPORT_TASK_DRAFT = 3; // 草稿箱
	
	public static enum ReportTask {
		DOCTOR_PROCESSING(1), DOCTOR_DRAFT(2), DONE(3), REMOVED(4);
		private int value;

		private ReportTask(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum ReportType {
		MONTH(1), QUARTER(2), ANNUAL(3);
		private int value;

		private ReportType(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	/**
	 * remind & plan common states
	 */
	public static final int CYCLE_EVERYDAY = 1; // 每一天
	public static final int CYCLE_EVERY_TWO_DAYS = 2; // 每两天
	public static final int CYCLE_EVERY_THREE_DAYS = 3; // 每三天
	public static final int CYCLE_EVERYWEEK = 7; // 每周
	
	public static final int WAY_WEB = 1;
	public static final int WAY_SMS = 2;
	public static final int WAY_WEIXIN = 3;
	public static final int WAY_APP = 4;
	public static final int WAY_EMAIL = 5;

	public static final int VALID = 1; // 有效
	public static final int INVALID = 0; // 无效
	
	public static final int UNSENT = 1; // 未发送
	public static final int SENT = 2; // 已发送
	
	/**
	 * remind states
	 */
	public static final int REMIND_CYCLE_EVERYDAY = 1; // 每一天
	public static final int REMIND_CYCLE_EVERY_TWO_DAYS = 2; // 每两天
	public static final int REMIND_CYCLE_EVERY_THREE_DAYS = 3; // 每三天
	public static final int REMIND_CYCLE_EVERYWEEK = 7; // 每周
	
	public static final int REMIND_WAY_WEB = 1;
	public static final int REMIND_WAY_SMS = 2;
	public static final int REMIND_WAY_WEIXIN = 3;
	public static final int REMIND_WAY_APP = 4;
	public static final int REMIND_WAY_EMAIL = 5;
	
	public static final int REMIND_INVALID = 0; // 无效
	public static final int REMIND_UNREAD = 1; // 未读
	public static final int REMIND_READ = 2; // 已读
	
	public static final int REMIND_UNSENT = 1; // 未发送
	public static final int REMIND_SENT = 2; // 已发送
	
	/**
	 * plan states
	 */
//	public static final int PLAN_CYCLE_EVERYDAY = 1; // 每一天
//	public static final int PLAN_CYCLE_EVERY_TWO_DAYS = 2; // 每两天
//	public static final int PLAN_CYCLE_EVERY_THREE_DAYS = 3; // 每三天
//	public static final int PLAN_CYCLE_EVERYWEEK = 7; // 每周
//	
//	public static final int PLAN_WAY_WEB = 1;
//	public static final int PLAN_WAY_SMS = 2;
//	public static final int PLAN_WAY_WEIXIN = 3;
//	public static final int PLAN_WAY_APP = 4;
//	public static final int PLAN_WAY_EMAIL = 5;
//	
//	public static final int PLAN_INVALID = 0; // 无效
//	public static final int PLAN_UNREAD = 1; // 未读
//	public static final int PLAN_READ = 2; // 已读
//	
//	public static final int PLAN_UNSENT = 1; // 未发送
//	public static final int PLAN_SENT = 2; // 已发送
	
	public static final int PLAN_PERIOD_ONE_WEEK = 1; // 一周
	public static final int PLAN_PERIOD_TWO_WEEKS = 2; // 两周
	public static final int PLAN_PERIOD_THREE_WEEKS = 3; // 三周
	public static final int PLAN_PERIOD_ONE_MONTH = 4; // 一个月
	public static final int PLAN_PERIOD_TWO_MONTHS = 5; // 两个月
	public static final int PLAN_PERIOD_THREE_MONTHS = 6; // 三个月
	
	public static enum OperationType {
		INSERT(1), DELETE(2), UPDATE(3), QUERY(4), BOUND(5), UNBOUND(6), REJECT(7), RECALL(8);
		private int value;

		private OperationType(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
	public static enum TargetType {
		RECORD(1), REPORT(2), HEALTH(3), REMIND(4), USER(5), DEVICE(6), COMPANY(7), PACK(8), PLAN(9);
		private int value;

		private TargetType(int value) {
			this.value = value;
		}

		public int getInt() {
			return value;
		}
	}
	
}