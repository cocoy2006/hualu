--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COMMENT '厂商名称',
  `code` varchar(20) COMMENT '厂商代码',
  `address` varchar(100) COMMENT '厂商地址',
  `spec` varchar(500) COMMENT '简介',
  `date` date COMMENT '签约时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备厂商表';

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `did` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dsn` varchar(40) NOT NULL COMMENT '设备id',
  `sim` varchar(40) COMMENT 'SIM卡信息',
  `ssid` varchar(40) COMMENT 'WIFI信息（SSID）',
  `passwd` varchar(40) COMMENT 'WIFI信息（密码）',
  `dstatus` tinyint(1) NOT NULL COMMENT '1未使用 2已领用 3已绑定',
  `packid` int(11) NOT NULL COMMENT '设备批次id',
  `ddatetime` datetime,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备基本信息表';

--
-- Table structure for table `device_package`
--

DROP TABLE IF EXISTS `device_package`;
CREATE TABLE `device_package` (
  `did` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dmodel` varchar(20) COMMENT '型号',
  `dname` varchar(40) COMMENT '设备名称',
  `dip` varchar(20) COMMENT '设备指向ip',
  `dinfo` varchar(400) COMMENT '设备描述',
  `dpic` varchar(80) COMMENT '设备图片',
  `comid` int(11) NOT NULL COMMENT '设备厂商',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备批次信息表';

--
-- Table structure for table `device_receive`
--

DROP TABLE IF EXISTS `device_receive`;
CREATE TABLE `device_receive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceid` int(11) unsigned NOT NULL COMMENT '设备id',
  `userid` int(11) unsigned COMMENT '用户id',
  `nurseid` int(11) unsigned NOT NULL COMMENT '客服id',
  `occupationtime` datetime NOT NULL COMMENT '领用时间',
  `address` varchar(40) COMMENT '领用地点',
  `boundtime` datetime COMMENT '绑定时间',
  `memo` char(10) COMMENT '备注',
  `status` int(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态，0正常，1解绑或维修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户流水号',
  `loginname` varchar(20) NOT NULL COMMENT '登录用户名',
  `passwd` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(20) NOT NULL COMMENT '用户姓名',
  `dstr` varchar(20) COMMENT '用户病历号',
  `createtime` datetime NOT NULL COMMENT '用户注册时间',
  `gender` int(1) unsigned DEFAULT 3 COMMENT '用户性别，1男，2女，3未知',
  `birthday` date COMMENT '用户出生年月',
  `age` int(3) COMMENT '根据用户所填生日计算',
  `photourl` varchar(200) COMMENT '头像地址',    
  `userlevel` int(1) unsigned DEFAULT 1 COMMENT '1普通，2贵宾',
  `usergroup` int(1) unsigned DEFAULT 1 COMMENT '1副教授，2正高级',
  `phone` varchar(20) COMMENT '手机号',
  `email` varchar(100) COMMENT '邮箱',
  `homeaddress` varchar(200) COMMENT '家庭地址',  
  `duetime1` date COMMENT '服务有效期开始',
  `duetime2` date COMMENT '服务有效期结束',
  `nurseid` int(11) unsigned COMMENT '客服id',
  `doctorid` int(11) unsigned COMMENT '主治医师',
  `ssnum` varchar(20) COMMENT '社保编号',
  `companyname` varchar(200) COMMENT '现任职公司名称',
  `companyaddress` varchar(200) COMMENT '公司地址',
  `wechatid` varchar(40) COMMENT '微信唯一id，微信绑定后，记录用户微信id，该id为唯一标识',
  `wechatname` varchar(40) COMMENT '微信昵称',
  `status` int(1) unsigned DEFAULT 1 COMMENT '用户状态，1已开通服务/正常，0未开通服务，2账号冻结（欠费），3注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户_用户基本信息表';

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `loginname` varchar(20) NOT NULL COMMENT '医生手机号，护士坐席号（医护）',
  `name` varchar(20) NOT NULL COMMENT '姓名（医护）',
  `genda` tinyint(1) unsigned COMMENT '性别（医护）',
  `uphotourl` varchar(200) COMMENT '头像',
  `hospital` varchar(40) COMMENT '所在医院',
  `depart` varchar(40) COMMENT '所在科室',
  `title` varchar(40) COMMENT '职称',
  `status` tinyint(1) unsigned DEFAULT 1 COMMENT '服务状态，0未服务 1正常服务 2注销（医护）',
  `isonline` tinyint(1) unsigned DEFAULT 2 COMMENT '1在线 2离线 3休息中',
  `createtime` datetime NOT NULL COMMENT '签约时间（医护）',
  `mobile` varchar(20) COMMENT '手机号（医护）',
  `memo` varchar(100) COMMENT '备注',
  `wechat_id` varchar(40) COMMENT '微信唯一id',
  `wechat_name` varchar(40) COMMENT '微信昵称',
  `role` tinyint(1) unsigned NOT NULL COMMENT '角色：1库管 2客服 3客服组长 4客服主管 5医生 6管理员',
  `passwd` varchar(40) NOT NULL COMMENT '密码，长度6-20',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='医生护士基本信息表';

--
-- Table structure for table `record_task`
--

DROP TABLE IF EXISTS `record_task`;
CREATE TABLE `record_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自检记录id',
  `userid` int(11) unsigned NOT NULL COMMENT '用户uid',
  `currentid` int(11) unsigned COMMENT '当前操作员id',
  `starttime` datetime COMMENT '测量时间起点',
  `endtime` datetime COMMENT '测量时间终点',
  `piecestarttime` datetime COMMENT '片段时间起点',
  `pieceendtime` datetime COMMENT '片段时间终点',
  `ids` longtext COMMENT '已排序的原始序列',
  `pieceids` longtext COMMENT '已排序的片段序列',
  `ecgwave1ids` longtext COMMENT '心电图的序列',
  `heartrate` float COMMENT '心率的平均值',
  `heartrateresult` int(2) COMMENT '心率等级划分结果',
  `heartrateids` longtext COMMENT '心率的序列',
  `breathrate` float COMMENT '呼吸率的平均值',
  `breathrateresult` int(2) COMMENT '呼吸率等级划分结果',
  `breathrateids` longtext COMMENT '呼吸率的序列',
  `spo2value` float COMMENT '血氧的平均值',
  `spo2valueresult` int(2) COMMENT '血氧等级划分结果',
  `spo2valueids` longtext COMMENT '血氧的序列',
  `pluserate` float COMMENT '脉率的平均值',
  `pluserateresult` int(2) COMMENT '脉率等级划分结果',
  `pluserateids` longtext COMMENT '脉率的序列',
  `bphigh` float COMMENT '高压的平均值',
  `bplow` float COMMENT '低压的平均值',
  `bpavg` float COMMENT '平均压的平均值',
  `bpresult` int(2) COMMENT '血压等级划分结果',
  `bpids` longtext COMMENT '血压的序列',
  `bprate` float COMMENT '血压脉率的平均值',
  `bprateresult` int(2) COMMENT '血压脉率等级划分结果',
  `bprateids` longtext COMMENT '血压脉率的序列',
  `temp1` float COMMENT '体温的平均值',
  `temp1result` int(2) COMMENT '体温等级划分结果',
  `temp1ids` longtext COMMENT '体温的序列',
  `hour` int(2) COMMENT '测量时间段，以开始测量为准，取值范围是0-23',
  `status` int(2) unsigned DEFAULT 1 COMMENT '1待检 2等待客服/组长 3客服/组长待检 4等待医生 5医生待检 6医生草稿 7驳回中 8撤回中 9客服主管处理 10已完成',
  `isdelete` int(2) unsigned DEFAULT 0 COMMENT '风险控制，管理员才能删除。0显示，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自检记录任务表';

--
-- Table structure for table `record_daily`
--

DROP TABLE IF EXISTS `record_daily`;
CREATE TABLE `record_daily` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自检记录日统计表流水号',
  `userid` int(11) unsigned NOT NULL COMMENT '用户uid',
  `date` date COMMENT '日期',
  `ids` longtext COMMENT '自检记录ID序列，逗号为分隔符',
  `hours` longtext COMMENT '测量时间段序列，逗号为分隔符',  
  `heartrate` float COMMENT '心率的平均值',
  `heartrateresult` int(2) COMMENT '心率等级划分结果',
  `breathrate` float COMMENT '呼吸率的平均值',
  `breathrateresult` int(2) COMMENT '呼吸率等级划分结果',
  `spo2value` float COMMENT '血氧的平均值',
  `spo2valueresult` int(2) COMMENT '血氧等级划分结果',
  `pluserate` float COMMENT '脉率的平均值',
  `pluserateresult` int(2) COMMENT '脉率等级划分结果',
  `bphigh` float COMMENT '高压的平均值',
  `bplow` float COMMENT '低压的平均值',
  `bpavg` float COMMENT '平均压的平均值',
  `bpresult` int(2) COMMENT '血压等级划分结果',
  `bprate` float COMMENT '血压脉率的平均值',
  `bprateresult` int(2) COMMENT '血压脉率等级划分结果',
  `temp1` float COMMENT '体温的平均值',
  `temp1result` int(2) COMMENT '体温等级划分结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自检记录日统计表';

--
-- Table structure for table `nurse_record_task`
--

DROP TABLE IF EXISTS `nurse_record_task`;
CREATE TABLE `nurse_record_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `nurseid` int(11) unsigned NOT NULL COMMENT '客服id',
  `taskid` int(11) unsigned NOT NULL COMMENT '任务信息id',
  `advice` varchar(255) COMMENT '自检摘要',
  `recalladvice` varchar(255) COMMENT '撤回理由',
  `endtime` datetime COMMENT '任务完成时间',
  `status` int(2) unsigned DEFAULT 1 COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服_自检记录工作任务表';

--
-- Table structure for table `manager_record_task`
--

DROP TABLE IF EXISTS `manager_record_task`;
CREATE TABLE `manager_record_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `managerid` int(11) unsigned NOT NULL COMMENT '客服id',
  `taskid` int(11) unsigned NOT NULL COMMENT '任务信息id',
  `endtime` datetime COMMENT '任务完成时间',
  `status` int(2) unsigned DEFAULT 1 COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服主管_自检记录工作任务表';

--
-- Table structure for table `doctor_record_task`
--

DROP TABLE IF EXISTS `doctor_record_task`;
CREATE TABLE `doctor_record_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `doctorid` int(11) unsigned NOT NULL COMMENT '医生id',
  `taskid` int(11) unsigned NOT NULL COMMENT '自检记录任务表id',
  `advice` varchar(255) COMMENT '医生给出的建议',
  `rejectadvice` varchar(255) COMMENT '驳回理由',
  `endtime` datetime COMMENT '医生确定时间',  
  `status` int(2) unsigned DEFAULT 1 COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生_自检记录工作任务表';


--
-- Table structure for table `health_task`
--

DROP TABLE IF EXISTS `health_task`;
CREATE TABLE `health_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `userid` int(11) NOT NULL COMMENT '用户uid',
  `nurseid` int(11) unsigned NOT NULL COMMENT '客服id',
  `doctorid` int(11) unsigned COMMENT '医生id',  
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `disname` varchar(200) COMMENT '疾病名称',
  `disscript` varchar(200) COMMENT '病情描述',
  `donetreat` varchar(200) COMMENT '已采取的措施',
  `desireaid` varchar(200) COMMENT '希望得到的帮助',
  `cloudrecordid` varchar(200) COMMENT '提供的云病历',
  `status` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1待处理 2已完成',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='健康咨询信息表';

--
-- Table structure for table `doctor_health_task`
--

DROP TABLE IF EXISTS `doctor_health_task`;
CREATE TABLE `doctor_health_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `taskid` int(11) NOT NULL COMMENT '健康咨询信息表流水号',  
  `doctorid` int(11) unsigned NOT NULL COMMENT '医生id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `advice` varchar(255) COMMENT '医生给出的建议',
  `endtime` datetime COMMENT '医生确定时间',
  `status` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1待处理，2已完成，3草稿',  
  `isdelete` int(1) unsigned DEFAULT 0 COMMENT '0-显示 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生_健康咨询建议表';

--
-- Table structure for table `report_picture`
--

DROP TABLE IF EXISTS `report_picture`;
CREATE TABLE `report_picture` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `userid` int(11) unsigned NOT NULL COMMENT '用户id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `boundtime` datetime NOT NULL COMMENT '绑定时间，月初1日1:00，季初1日1:00，年初1日1:00',
  `type` int(3) unsigned NOT NULL DEFAULT 1 COMMENT '1月报 2季报 3年报',
  `url` varchar(200) COMMENT '存储地址',   
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合报告_静态体检报告';

--
-- Table structure for table `report_task`
--

DROP TABLE IF EXISTS `report_task`;
CREATE TABLE `report_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `userid` int(11) unsigned NOT NULL COMMENT '用户id',
  `doctorid` int(11) unsigned COMMENT '医生id',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `starttime` datetime NOT NULL COMMENT '开始时间',
  `endtime` datetime NOT NULL COMMENT '结束时间',
  `type` int(3) unsigned NOT NULL DEFAULT 1 COMMENT '1月报 2季报 3年报',
  `advice` varchar(255) COMMENT '医生给出的建议',
  `dailyids` longtext COMMENT '自检记录日统计表序列',
  `ids` longtext COMMENT '自检记录序列',
  `heartrate` float COMMENT '心率的平均值',
  `heartrateresult` int(2) COMMENT '心率等级划分结果，只存储出现次数最多的状态',
  `breathrate` float COMMENT '呼吸率的平均值',
  `breathrateresult` int(2) COMMENT '呼吸率等级划分结果，只存储出现次数最多的状态',
  `spo2value` float COMMENT '血氧的平均值',
  `spo2valueresult` int(2) COMMENT '血氧等级划分结果，只存储出现次数最多的状态',
  `pluserate` float COMMENT '脉率的平均值',
  `pluserateresult` int(2) COMMENT '脉率等级划分结果，只存储出现次数最多的状态',
  `bphigh` float COMMENT '高压的平均值',
  `bplow` float COMMENT '低压的平均值',
  `bpavg` float COMMENT '平均压的平均值',
  `bpresult` int(2) COMMENT '血压等级划分结果，只存储出现次数最多的状态',
  `bprate` float COMMENT '血压脉率的平均值',
  `bprateresult` int(2) COMMENT '血压脉率等级划分结果，只存储出现次数最多的状态',
  `temp1` float COMMENT '体温的平均值',
  `temp1result` int(2) COMMENT '体温等级划分结果，只存储出现次数最多的状态',
  `hour` int(2) COMMENT '测量时间段，以开始测量为准，取值范围是0-23',
  `status` int(3) unsigned NOT NULL DEFAULT 1 COMMENT '1待处理 2草稿 3已完成',
  `isdelete` int(3) unsigned DEFAULT 0 COMMENT '0显示 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='综合报告任务表';

--
-- Table structure for table `doctor_report_task`
--
/**
DROP TABLE IF EXISTS `doctor_report_task`;
CREATE TABLE `doctor_report_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `taskid` int(11) NOT NULL COMMENT '综合报告id',  
  `doctorid` int(11) unsigned NOT NULL COMMENT '医生id',
  `advice` varchar(255) COMMENT '医生给出的建议',
  `endtime` datetime COMMENT '医生确定时间',
  `status` int(3) unsigned COMMENT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生_综合报告建议表';
**/
--
-- Table structure for table `remind`
--

DROP TABLE IF EXISTS `remind`;
CREATE TABLE `remind` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `fromid` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '发起方id，预留0为系统消息',  
  `toid` int(11) unsigned NOT NULL COMMENT '接收方id',
  `createtime` datetime COMMENT '创建时间',
  `starttime` datetime COMMENT '提醒开始时间',
  `endtime` datetime COMMENT '提醒结束时间，预留，暂不使用',
  `cycle` int(1) unsigned NOT NULL COMMENT '提醒周期，1每天 2每两天 3每三天 7每周',
  `way` varchar(20) NOT NULL DEFAULT 1 COMMENT '提醒方式，1web 2sms 3weixin 4app 5email',
  `content` varchar(255) COMMENT '提醒内容',
  `status` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1未读 2已读 0无效',
  `sent` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1未发送 2已发送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提醒表';

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `fromid` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '发起方id，预留0为系统消息',  
  `toid` int(11) unsigned NOT NULL COMMENT '接收方id',
  `type` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '计划类型，1测量计划 2运动计划',
  `createtime` datetime COMMENT '创建时间',
  `starttime` datetime COMMENT '开始时间',
  `period` int(3) unsigned NOT NULL DEFAULT 1 COMMENT '计划周期，1一周 2两周 3三周 4一个月 5两个月 6三个月',
  `endtime` datetime COMMENT '结束时间',
  `cycle` int(1) unsigned NOT NULL COMMENT '提醒周期，1每天 2每两天 3每三天 7每周',
  `way` varchar(20) NOT NULL DEFAULT 1 COMMENT '提醒方式，1web 2sms 3weixin 4app 5email',
  `content` varchar(255) COMMENT '计划内容',
  `status` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1有效 0无效',
  `sent` int(1) unsigned NOT NULL DEFAULT 1 COMMENT '1未发送 2已发送',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='计划表';

--
-- Table structure for table `signlog`
--

DROP TABLE IF EXISTS `signlog`;
CREATE TABLE `signlog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `operatorid` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '操作方id，预留0为系统消息', 
  `signintime` datetime NOT NULL COMMENT '登录时间',
  `signouttime` datetime COMMENT '登出时间',
  `onlinetime` int(13) unsigned COMMENT '在线时长，单位毫秒MS',
  `ip` varchar(25) COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录登出日志表';

--
-- Table structure for table `operationlog`
--

DROP TABLE IF EXISTS `operationlog`;
CREATE TABLE `operationlog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `operatorid` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '操作方id，预留0为系统',
  `operatorrole` int(3) unsigned NOT NULL COMMENT '角色:1库管 2客服 3客服组长 4客服主管 5医生 6管理员',
  `operationtype` int(3) unsigned NOT NULL DEFAULT 1 COMMENT '操作类型:新建1 删除2 修改/处理3 查询4 绑定5 解绑定6 驳回7 撤回8', 
  `targetid` int(11) unsigned COMMENT '对象id',
  `targettype` int(3) unsigned NOT NULL COMMENT '对象类型:自检记录record1 综合报告report2 健康咨询health3 健康干预remind4 用户管理5 设备管理6 厂家管理7 批次管理8 健康计划plan9',
  `time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录日志表';
