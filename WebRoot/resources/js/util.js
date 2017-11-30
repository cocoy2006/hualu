var c = "label-danger", d = "label-default";

if(typeof RecordTask == "undefined") {
	var RecordTask = {};
	RecordTask.INIT = 1;
	RecordTask.WAITING_NURSE = 2;
	RecordTask.NURSE_PROCESSING = 3;
	RecordTask.WAITING_DOCTOR = 4;
	RecordTask.DOCTOR_PROCESSING = 5;
	RecordTask.DOCTOR_DRAFT = 6;
	RecordTask.REJECT = 7;
	RecordTask.RECALL = 8;
	RecordTask.MANAGER_PROCESSING = 9;
	RecordTask.DONE = 10;
}

if(typeof ReportTask == "undefined") {
	var ReportTask = {};
	ReportTask.DOCTOR_PROCESSING = 1;
	ReportTask.DOCTOR_DRAFT = 2;
	ReportTask.DONE = 3;
}

var period = 180000;
function loadSpecificPage(page) {
	$("#" + page).load(host + page + "/");
}

function modal(page) {
	$(".modal").on("show.bs.modal", function(e) {
		top.window.isModalPop = true;
	}).on("shown.bs.modal", function(e) {
		$(this).on("hidden.bs.modal", function (e) {
			loadSpecificPage(page);
			top.window.isModalPop = false;
		});
//		top.window.isModalPop = true;
	}).modal("show");
	
	
}

function dispDataTable(className) {
	var table = $("." + className);
	table.css("font-size", "12px");
	return table.dataTable({
//		"destroy": true,
		"language": {
            "info": " 从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
            "infoEmpty": "  ", // 暂时没有记录
            "infoFiltered": "(全部记录数 _MAX_  条)",
            "lengthMenu": "  ", // 显示_MENU_条
            "loadingRecords": " 加载中... ",  
            "search": " 模糊搜索 ",
            "paginate": {
				"first": 	" 首页 ",
				"previous": " < ",
				"next":     " > ",
				"last":     " 末页 "
            },
            "processing": " 查询中... ", 
            "zeroRecords": " 暂时没有记录 ",
        },
		"ordering": false,
//		"pagingType": "full_numbers",
		"retrieve": true,
		"stateSave": true
	});
}

function dispDatatableWithPagination(container, ajaxSource, columns, countSpan, callbackMethod) {
	var table = $(container);
	return table.dataTable({
//        "processing": true,
        "serverSide": true,
        "stateSave": true,
        "displayLength": 10,
        "displayStart": 0,
        "ajaxSource": ajaxSource,
        "drawCallback": function(settings) {
			var iTotal = settings.fnRecordsTotal();
			$(countSpan).html(iTotal);
			table.next().find("span.totalPages").html(settings.oInstance.fnPagingInfo().iTotalPages);
			if(iTotal > 0 && callbackMethod && (callbackMethod  instanceof Function)){
				callbackMethod();
			}
		},
        "language": {
            "info": " 从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条 ",
            "infoEmpty": "  ", // 暂时没有记录
            "infoFiltered": "(全部记录数 _MAX_  条)",
            "lengthMenu": "  ", // 显示_MENU_条
//            "loadingRecords": " 加载中... ",  
            "search": " 模糊搜索 ",
            "paginate": {
				"first": 	" 首页 ",
				"previous": " < ",
				"next":     " > ",
				"last":     " 末页 "
            },
//            "processing": " 查询中... ", 
            "zeroRecords": " 暂时没有记录 ",
        },
		"ordering": false,
		"retrieve": true,
        "columns": columns
    });
}

function sendSms(url) {
	$.ajax({
		url: url,
		dataType: "json",
		success: function(json) {
			if(json == "0") {
				messager(" 已发送成功 ", "success", 2000);
			} else {
				messager(" 发送失败 ", "danger", 2000);
			}
		}
	});
}

function parseDate(date) {
	return Date.parse(date.replace(new RegExp(/-/g),'/'));
}

function isPhone(no) {
	var pattern = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}$/;
	return pattern.test(no);
}