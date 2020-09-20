function followupCounts(){
	var userTypeId=$("#propsectUser").val();
	//alert(userTypeId);
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/followupReportsCounts?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			$('.todayUpdatedCount').text(data.todayUpdateCount);
			$('.todayNotUpdatedCount').text(data.todayNotUpdateCount);
			$('.weeklyUpdatedCount').text(data.weeklyUpdateCount);
			$('.weeklyNotUpdatedCount').text(data.weeklyNotUpdateCount);
			$('.monthlyUpdatedCount').text(data.monthlyUpdateCount);
			$('.monthlyNotUpdatedCount').text(data.monthlyNotUpdateCount);
			$('.dateRangeUpdatedCount').text(data.dateRangeUpdateCount);
			$('.dateRangeNotUpdatedCount').text(data.dateRangeNotUpdateCount);
			$('#startDate1').val(data.startDate);
			$('#endDate1').val(data.endDate);
			$("#distabl").hide();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
function dateRangeFollowupCounts(){
	var userTypeId=$("#propsectUser").val();
	var startDate=$('#startDate1').val();
	var endDate=$('#endDate1').val();
	var params = {
			userTypeId : userTypeId ,
			startDate : startDate ,
			endDate : endDate,
	}
	var queryParam = jQuery.param( params );
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/dateRangefollowupReportsCounts",
		data : queryParam,
		success : function(data) {
			$('.dateRangeUpdatedCount').text(data.dateRangeUpdateCount);
			$('.dateRangeNotUpdatedCount').text(data.dateRangeNotUpdateCount);
			$('#startDate1').val(data.startDate);
			$('#endDate1').val(data.endDate);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function todayUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/todayUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
function todayNotUpdatedFollowupReports(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/todayNotUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function weeklyUpdatedFollowupReports(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/weeklyUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function weeklyNotUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/weeklyNotUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function monthlyUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/monthlyUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function monthlyNotUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/monthlyNotUpdatedFollowupReportsList?userTypeId="+userTypeId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}

function dateRangeUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var startDate=$('#startDate1').val();
	var endDate=$('#endDate1').val();
	var params = {
			userTypeId : userTypeId ,
			startDate : startDate ,
			endDate : endDate,
	}
	var queryParam = jQuery.param( params );
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/dateRangeUpdatedFollowupReportsList",
		data : queryParam,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function dateRangeNotUpdatedFollowupReportsList(){
	$('#datatable1-responsive').dataTable().fnDestroy();
	var userTypeId=$("#propsectUser").val();
	var startDate=$('#startDate1').val();
	var endDate=$('#endDate1').val();
	var params = {
			userTypeId : userTypeId ,
			startDate : startDate ,
			endDate : endDate,
	}
	var queryParam = jQuery.param( params );
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/dateRangeNotUpdatedFollowupReportsList",
		data : queryParam,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdon="";
				$('#followupReportsList').html("");
				$.each(data,function(index, item) {
					var createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdon[0]+ "</td>"
						+ "<td>"+ item.stage+ "</td>"
						+ "<td>"+ item.meetingType+ "</td>"
						+ "<td>"+ item.nextFollowup+ "</td>"
						+ "<td>"+ item.createdBy+ "</td>"
						+ "<td>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#followupReportsList').append(eachrow);
				});
				$('#datatable1-responsive').DataTable();
				
			}else {
				$('#datatable1-responsive').dataTable().fnDestroy();
				$('#followupReportsList').html("");
				$('#datatable1-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
$( "#todayUpdatedFollowupReportsList" ).click(function() {
	  showDiv('distabl');
	  $("#todayContacted").css("border-bottom" ,"3px solid #06F");
	  $("#todayNotContacted").css("border-bottom" ,"");
	  $("#weeklyContacted").css("border-bottom" ,"");
	  $("#weeklyNotContacted").css("border-bottom" ,"");
	  $("#monthlyContacted").css("border-bottom" ,"");
	  $("#monthlyNotContacted").css("border-bottom" ,"");
	  $("#dateRangeContacted").css("border-bottom" ,"");
	  $("#dateRangeNotContacted").css("border-bottom" ,"");
	  todayUpdatedFollowupReportsList();
	});
$( "#todayNotUpdatedFollowupReports" ).click(function() {
	  showDiv('distabl');
	  $("#todayNotContacted").css("border-bottom" ,"3px solid #06F");
	  $("#todayContacted").css("border-bottom" ,"");
	  $("#weeklyContacted").css("border-bottom" ,"");
	  $("#weeklyNotContacted").css("border-bottom" ,"");
	  $("#monthlyContacted").css("border-bottom" ,"");
	  $("#monthlyNotContacted").css("border-bottom" ,"");
	  $("#dateRangeContacted").css("border-bottom" ,"");
	  $("#dateRangeNotContacted").css("border-bottom" ,"");
	  todayNotUpdatedFollowupReports();
	});
$( "#weeklyUpdatedFollowupReports" ).click(function() {
	  showDiv('distabl'); 
	  $("#weeklyContacted").css("border-bottom" ,"3px solid #06F");
	  $("#todayContacted").css("border-bottom" ,"");
	  $("#todayNotContacted").css("border-bottom" ,"");
	  $("#weeklyNotContacted").css("border-bottom" ,"");
	  $("#monthlyContacted").css("border-bottom" ,"");
	  $("#monthlyNotContacted").css("border-bottom" ,"");
	  $("#dateRangeContacted").css("border-bottom" ,"");
	  $("#dateRangeNotContacted").css("border-bottom" ,"");
	  weeklyUpdatedFollowupReports();
	});
$( "#weeklyNotUpdatedFollowupReportsList" ).click(function() {
	showDiv('distabl'); 
	$("#weeklyNotContacted").css("border-bottom" ,"3px solid #06F");
	$("#todayContacted").css("border-bottom" ,"");
	$("#todayNotContacted").css("border-bottom" ,"");
    $("#weeklyContacted").css("border-bottom" ,"");
	$("#monthlyContacted").css("border-bottom" ,"");
	$("#monthlyNotContacted").css("border-bottom" ,"");
	$("#dateRangeContacted").css("border-bottom" ,"");
	$("#dateRangeNotContacted").css("border-bottom" ,"");	
	weeklyNotUpdatedFollowupReportsList();
	});
$( "#monthlyUpdatedFollowupReportsList" ).click(function() {
	showDiv('distabl'); 
	$("#monthlyContacted").css("border-bottom" ,"3px solid #06F");
	$("#todayContacted").css("border-bottom" ,"");
    $("#todayNotContacted").css("border-bottom" ,"");
	$("#weeklyContacted").css("border-bottom" ,"");
	$("#weeklyNotContacted").css("border-bottom" ,"");
	$("#monthlyNotContacted").css("border-bottom" ,"");
    $("#dateRangeContacted").css("border-bottom" ,"");
	$("#dateRangeNotContacted").css("border-bottom" ,"");
	monthlyUpdatedFollowupReportsList();
	});
$( "#monthlyNotUpdatedFollowupReportsList" ).click(function() {
	showDiv('distabl'); 
	$("#monthlyNotContacted").css("border-bottom" ,"3px solid #06F");
	$("#todayContacted").css("border-bottom" ,"");
	$("#todayNotContacted").css("border-bottom" ,"");
    $("#weeklyContacted").css("border-bottom" ,"");
    $("#weeklyNotContacted").css("border-bottom" ,"");
	$("#monthlyContacted").css("border-bottom" ,"");
	$("#dateRangeContacted").css("border-bottom" ,"");
	$("#dateRangeNotContacted").css("border-bottom" ,"");
	monthlyNotUpdatedFollowupReportsList();
	});
$( "#dateRangeUpdatedFollowupReportsList" ).click(function() {
	showDiv('distabl'); 
	$("#dateRangeContacted").css("border-bottom" ,"3px solid #06F");
	$("#todayContacted").css("border-bottom" ,"");
	$("#todayNotContacted").css("border-bottom" ,"");
    $("#weeklyContacted").css("border-bottom" ,"");
    $("#weeklyNotContacted").css("border-bottom" ,"");
	$("#monthlyContacted").css("border-bottom" ,"");
	$("#monthlyNotContacted").css("border-bottom" ,"");
	$("#dateRangeNotContacted").css("border-bottom" ,"");
	dateRangeUpdatedFollowupReportsList();
	});
$( "#dateRangeNotUpdatedFollowupReportsList" ).click(function() {
	showDiv('distabl'); 
	$("#dateRangeNotContacted").css("border-bottom" ,"3px solid #06F");
	$("#todayContacted").css("border-bottom" ,"");
	$("#todayNotContacted").css("border-bottom" ,"");
    $("#weeklyContacted").css("border-bottom" ,"");
    $("#weeklyNotContacted").css("border-bottom" ,"");
	$("#monthlyContacted").css("border-bottom" ,"");
	$("#monthlyNotContacted").css("border-bottom" ,"");
	$("#dateRangeContacted").css("border-bottom" ,"");
	dateRangeNotUpdatedFollowupReportsList();
	});
		
