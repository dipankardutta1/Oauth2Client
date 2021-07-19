
$(document).ready(function () {
	
	
	
	
	var counter = 0;

    $("#addrow").on("click", function () {
        var newRow = $("<tr>");
        var cols = "";
        
        cols += '<td class="col-sm-2"><input type="text" class="form-control country" name="country' + counter + '"/></td>';
        cols += '<td class="col-sm-2"><input type="text" class="form-control state" name="state' + counter + '"/></td>';
        cols += '<td class="col-sm-2"><input type="text" class="form-control city" name="city' + counter + '"/></td>';
        cols += '<td class="col-sm-5"><input type="text" class="form-control addressLine" name="address' + counter + '"/></td>';

        cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
        $("table.order-list").append(newRow);
        counter++;
    });



    $("table.order-list").on("click", ".ibtnDel", function (event) {
        $(this).closest("tr").remove();       
        counter -= 1
    });

	
	
    $("#addressBtn").on("click", function () {
    	
    	 $('#overlay').fadeIn();
    	
    	var addresses = new Array();
        $("#addressTable tbody tr").each(function () {
            var row = $(this);
            var addressObj = {};
            addressObj.country = row.find(".country").eq(0).val();
            addressObj.state = row.find(".state").eq(0).val();
            addressObj.city = row.find(".city").eq(0).val();
            addressObj.addressLine = row.find(".addressLine").eq(0).val();
           
            addresses.push(addressObj);
        });

        $.ajax({
            type: "POST",
            url: "/api/candidate/address/update",
            data: JSON.stringify(addresses),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                
                $('#overlay').fadeOut();
               // $('#address').modal('hide');
                if(data.httpStatus == "OK"){
                	
                	jQuery('#addressFragment div').html('');
                	 $.each(data.output, function(key,value) {
                		 $( "#addressFragment" ).append(
                         		'<div><div class="address"><div><h4><span style="font-size: 12px">'+value.country+'</span></h4></div>'+
         							'<span style="font-size: 12px">'+value.state+'</span>,<span style="font-size: 12px">'+value.city+'</span>'+
         							'<div><ul><li><i class="far fa-hand-point-right"></i> <span style="font-size: 12px">'+value.addressLine+'</span></li></ul></div></div></div>'
                         		 )
                   	}); 
                	 
                	 
                	 
                }
            },
            error: function (e) {

                /*  var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                      + e.responseText + "&lt;/pre&gt;";
                  $('#feedback').html(json);*/

                  console.log("ERROR : ", e.responseText);
                 // $("#btn-search").prop("disabled", false);
                  
                 
                  $('#overlay').fadeOut();
                 // $('#address').modal('hide');
              }
        });
    });
	
	//-----------------------------------------------workexperience--------------------------------------------------
    
    
    var counterWorkExp = 0;

    $("#addrowWorkExp").on("click", function () {
        var newRow = $("<tr>");
        var cols = "";
        
        cols += '<td class="col-sm-2"><input type="text" class="form-control title" name="title' + counterWorkExp + '"/></td>';
        cols += '<td class="col-sm-2"><input type="text" class="form-control startDate" name="startDate' + counterWorkExp + '"/></td>';
        cols += '<td class="col-sm-2"><input type="text" class="form-control endDate" name="endDate' + counterWorkExp + '"/></td>';
        cols += '<td class="col-sm-2"><input type="text" class="form-control company" name="company' + counterWorkExp + '"/></td>';
        cols += '<td class="col-sm-5"><input type="text" class="form-control industry" name="industry' + counterWorkExp + '"/></td>';
        cols += '<td class="col-sm-5"><input type="text" class="form-control summary" name="summary' + counterWorkExp + '"/></td>';
        
        cols += '<td class="col-sm-1"><input type="button" class="ibtnWorkExpDel btn btn-md btn-danger "  value="Delete"></td>';
        newRow.append(cols);
        $("#workExp table.order-list").append(newRow);
        counter++;
    });
    
    $("#workExp table.order-list").on("click", ".ibtnWorkExpDel", function (event) {
        $(this).closest("tr").remove();       
        counter -= 1
    });
    
    $("#workExperienceBtn").on("click", function () {
    	
   	 $('#overlay').fadeIn();
   	
   	var workExperienceList = new Array();
       $("#workExp tbody tr").each(function () {
           var row = $(this);
           var workExpObj = {};
           workExpObj.title = row.find(".title").eq(0).val();  
           workExpObj.startDate = new Date(row.find(".startDate").eq(0).val());
           workExpObj.endDate = new Date(row.find(".endDate").eq(0).val());  
           workExpObj.company = row.find(".company").eq(0).val();
           workExpObj.industry = row.find(".industry").eq(0).val();
           workExpObj.summary = row.find(".summary").eq(0).val();
             
           workExperienceList.push(workExpObj);
       });

       $.ajax({
           type: "POST",
           url: "/api/candidate/workExp/update",
           data: JSON.stringify(workExperienceList),
           contentType: "application/json; charset=utf-8",
           dataType: "json",
           success: function (data) {
               
               $('#overlay').fadeOut();
              // $('#workExp').modal('hide');
               if(data.httpStatus == "OK"){
               	
               	jQuery('#workExpFragment div').html('');
               	
               	 $.each(data.output, function(key,value) {
               		 $( "#workExpFragment" ).append(
                        		'<div><div class="work-exp"><div><h4><span style="font-size: 12px">'+value.title+'</span></h4></div>'+
        							'<span style="font-size: 12px">'+value.startDate+'</span>,<span style="font-size: 12px">'+value.endDate+'</span>'+
        							'<span style="font-size: 12px">'+value.company+'</span>/'+'<span style="font-size: 12px">'+value.industry+'</span>/'+
        							'<div><ul><li><i class="far fa-hand-point-right"></i> <span style="font-size: 12px">'+value.summary+'</span></li></ul></div></div></div>'
                        		 )
                  	}); 
               }
           },
           error: function (e) {

                 var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);

                 console.log("ERROR : ", e.responseText);
                // $("#btn-search").prop("disabled", false);
                 
                
                 $('#overlay').fadeOut();
                // $('#workExp').modal('hide');
             }
       });
   });

//-------------------------------------------------------workExperience ends here----------------------------------------	
	$.fn.serializeFormJSON = function () {
		 
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
	
	

    $("#summary-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

       // var formData = JSON.stringify($("#summary-form").serializeArray());
        var json = $(this).serializeFormJSON();
        //alert(JSON.stringify(data));
        summary_ajax_submit(json);
     });
    
    
    
    $("#profile-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

       // var formData = JSON.stringify($("#summary-form").serializeArray());
        var json = $(this).serializeFormJSON();
        //alert(JSON.stringify(data));
        profile_ajax_submit(json);
      });
   });




function summary_ajax_submit(json) {

    var search = {}
   
    $('#overlay').fadeIn();
    
    $.ajax({
        type: "post",
        contentType: 'application/json; charset=utf-8',
        url:  "/api/candidate/summary/update",
        data: JSON.stringify(json),
        cache: false,
        timeout: 600000,
        success: function (data) {

           /* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);*/

            
            if(data.httpStatus == "OK"){
            	console.log("SUCCESS : ", data);
            	
            	$("#firstNameAvatar").text(data.output.firstName);
            	$("#lastNameAvatar").text(data.output.lastName);
            	$("#profileTitleAvatar").text(data.output.profileTitle);
            	
            	$("#firstNameView").text(data.output.firstName);
            	$("#lastNameView").text(data.output.lastName);
            	$("#profileTitleView").text(data.output.profileTitle);
            	$("#summaryView").text(data.output.summary);
            	
            	
            	
            }else{
            	console.log("Error");
            }
            $('#overlay').fadeOut();
           // $('#summary').modal('hide');
            //$('#summary').modal('toggle');
          

        },
        error: function (e) {

          /*  var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);*/

            console.log("ERROR : ", e.responseText);
           // $("#btn-search").prop("disabled", false);
            
           
            $('#overlay').fadeOut();
           // $('#summary').modal('toggle');
        }
    });

}



function profile_ajax_submit(json) {

    var search = {}
   
    $('#overlay').fadeIn();
    
    $.ajax({
        type: "post",
        contentType: 'application/json; charset=utf-8',
        url:  "/api/candidate/profile/update",
        data: JSON.stringify(json),
        cache: false,
        timeout: 600000,
        success: function (data) {

           /* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);*/

            
            if(data.httpStatus == "OK"){
            	console.log("SUCCESS : ", data);
            	
            	
            	
            	$("#aliasNameView").text(data.output.aliasName);
            	$("#interViewModeView").text(data.output.interViewMode);
            	$("#interviewStatusesView").text(data.output.interviewStatuses);
            	$("#birthDateView").text(data.output.birthDate);
            	$("#workExperienceView").text(data.output.workExperience);
            	$("#releventExperienceView").text(data.output.releventExperience);
            	$("#hiringTypeView").text(data.output.hiringType);
            	
            	
            }else{
            	console.log("Error");
            }
            $('#overlay').fadeOut();
           // $('#profile').modal('hide');
          

        },
        error: function (e) {

          /*  var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
            $('#feedback').html(json);*/

            console.log("ERROR : ", e.responseText);
           // $("#btn-search").prop("disabled", false);
            
           
            $('#overlay').fadeOut();
           // $('#profile').modal('hide');
        }
    });

}


function calculateRow(row) {
    var price = +row.find('input[name^="price"]').val();

}

function calculateGrandTotal() {
    var grandTotal = 0;
    $("table.order-list").find('input[name^="price"]').each(function () {
        grandTotal += +$(this).val();
    });
    $("#grandtotal").text(grandTotal.toFixed(2));
}
