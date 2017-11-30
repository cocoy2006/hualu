package com.hualu.main.java.util;

public class Constants {

	public static final long ONE_DAY = 86400000;
	public static final long TWO_DAYS = 172800000;
	public static final long THREE_DAYS = 259200000;
	public static final long ONE_WEEK = 604800000;
	public static final long TWO_WEEKS = 1209600000;
	public static final long THREE_WEEKS = 1814400000;
	public static final long ONE_MONTH = 2592000000L;
	public static final long TWO_MONTHS = 5184000000L;
	public static final long THREE_MONTHS = 7776000000L;
	
	public static final String SMS_WELCOME = "尊敬的用户您好，欢迎您成为“中航一米”健康管理平台的内测用户，我们将竭诚为您提供周到、温馨的服务。";
	@Deprecated
	public static final String SMS_RECORD_RECEIVED = "{0}先生（女士），您好！现已收到您于{1}上传的监测数据，我们会尽快把您的数据交给医生，医生分析完数据后我们会在第一时间回复您。谢谢！";
	public static final String SMS_RECORD_PROBLEM = "{0}先生（女士），您好！您于{1}上传的数据，经医生分析，{2}，请继续监测，以完善您的健康档案。感谢您的支持！";
	public static final String SMS_RECORD_NORMAL = "{0}先生（女士），您好！您于{1}上传的数据，经医生分析，结果均正常，请您继续保持，感谢您的支持！";
	public static final String SMS_BOOKING_EXAMINATION = "您好，{0}先生（女士），现帮您预约了{1}到贵航贵阳（三00）医院体检中心进行VIP体检。体检需要空腹进行，麻烦您在前一天晚上8点以后禁食，10点以后禁饮水，穿轻便的衣服，不要穿带金属的内衣裤。";
	public static final String SMS_RECORD_REMIND = "{0}先生（女士），您好！原定于您在{1}的监测数据，我们还没有收到，是否因为忙碌而没有测量呢？谢谢！";
	public static final String SMS_RECORD_REMIND_DAYS = "{0}先生（女士），我中心已有3日没有收到您的数据，请您坚持测量，感谢您的支持！";
	@Deprecated
	public static final String SMS_ECGWAVE_RECEIVED = "{0}先生（女士），您好！您于{1}测量的心电图，我们已收到，在医生判断之后，我们会第一时间给您回复，感谢您的使用，祝您生活愉快！";
	public static final String SMS_ECGWAVE_PROBLEM = "{0}先生（女士），您好！您于{1}测量的心电图，经医生分析，{2}，请您继续监测，感谢您的支持！";
	public static final String SMS_DATA_RECEIVED = "{0}先生（女士），您好！您于{1}上传的数据，结果为：{2}感谢您的支持！";

	public static final int ECGWAVE_POINTS_DEMO = 300;
	public static final int ECGWAVE_POINTS_REAL = 500;
	public static final int ECGWAVE_LINES = 7;
	public static final int ECGWAVE_SCALE = 2;
}
