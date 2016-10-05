var Employee = function() {
	return {
		init : function() {
			jQuery(".delete").click(function() {
				
				var rply = confirm("Are you sure you want to delete this record?");
				if(!rply){ return; }
				
				$.ajax({
					type: "POST",
					data: {
						op: "ttdemo_widgets.employee.employee",
						subop: "deleteEmployee",
						id: jQuery(this).attr("empId")
					},
					success: function(data) {
						if (data.isSuccess) {
							alert("Record successfully deleted.");
							location.reload();
						} else {
							alert ("failed");
						}
					}
				});
			});
			
			jQuery("#btnSave").click(function() {
				
				if(Employee.validateFields("#addModal")){
					return;
				}								
				
				Employee.ajaxSave();
				
			});
			
			jQuery("#btnUpdate").click(function() {
				
				if(Employee.validateFields("#updateModal")){
					return;
				}		
				
				Employee.ajaxUpdate();
			});
			
			jQuery(".glyphicon-edit").click(function() {
				
				var uuid = $(this).attr("uuid");	
				$("#hidUuid").val(uuid);
				$("#ename").val($("#tr"+uuid+" td.name").text());
				$("#eadd").val($("#tr"+uuid+" td.address").text());
				$("#ephn").val($("#tr"+uuid+" td.phone").text());
				$("#epos").val($("#tr"+uuid+" td.position").text());
				$("#edept").val($("#tr"+uuid+" td.department").text());
				
			});
		}, ajaxSave : function(){
			
			$.ajax({
				type: "POST",
				data: {
					op: "ttdemo_widgets.employee.employee",
					subop: "saveEmployee",
					name: jQuery("#name").val(),
					address: jQuery("#add").val(),
					phone: jQuery("#phn").val(),
					position: jQuery("#pos").val(),
					department: jQuery("#dept").val()					
				},
				success: function(data) {
					if (data.isSuccess) {
						alert("Record successfully saved.");
						location.reload();
					} else {
						alert ("failed");
					}
				}
			});
			
		}, ajaxUpdate : function(){
			
			$.ajax({
				type: "POST",
				data: {
					op: "ttdemo_widgets.employee.employee",
					subop: "updateEmployee",
					id: jQuery("#hidUuid").val(),
					name: jQuery("#ename").val(),
					address: jQuery("#eadd").val(),
					phone: jQuery("#ephn").val(),
					position: jQuery("#epos").val(),
					department: jQuery("#edept").val()					
				},
				success: function(data) {
					if (data.isSuccess) {
						alert("Record succesfully updated.");
						location.reload();
					} else {
						alert ("failed");
					}
				}
			});
			
		}, validateFields : function(modal){									
			var flag = false;
			$( modal+" .mandatory" ).each(function( i ) {
			  
			    if($.trim($(this).val()) == ""){
			    	$(this).addClass("errorMsg");
			    	flag = true;
			    } else {
			    	$(this).removeClass("errorMsg");
			    }
			   
			});
			return flag;
		}
	};
}();
jQuery(Employee.init());
