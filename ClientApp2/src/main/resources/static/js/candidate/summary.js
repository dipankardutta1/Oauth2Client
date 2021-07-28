$(document).on('click', '.iDelResume', function(){
	
	currentObj = $(this);
	
	title = $(this).closest("tr").find(".titleClazz").val();
	email = $(this).closest("tr").find(".emailClazz").val();
	
	$('#overlay').fadeIn();

	$.ajax({
		type: "post",
		contentType: 'application/json; charset=utf-8',
		url:  "/api/candidate/resume/delete?email="+email+"&title="+title,
		cache: false,
		timeout: 600000,
		success: function (data) {

			/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);*/


			if(data.httpStatus == "OK"){
				console.log("SUCCESS : ", data);

				currentObj.closest("tr").remove();       
				


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
			window.location.href="/candidate/refresh";

			$('#overlay').fadeOut();
			// $('#profile').modal('hide');
		}
	});
});


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

				//console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";
				// $('#address').modal('hide');
				//$('#refreshModel').modal('show');

				$('#overlay').fadeOut();
				// 



			}
		});
	});

	//-----------------------------------------------workexperience--------------------------------------------------


	var counterWorkExp = 0;

	$("#addrowWorkExp").on("click", function () {
		alert("ok");
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-1"><input type="text" class="form-control title" name="title' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-1"><input type="text" readonly="readonly" class="form-control startDate datepicker" name="startDate' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-1"><input type="text" readonly="readonly" class="form-control endDate datepicker" name="endDate' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-1"><input type="text" class="form-control company" name="company' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control industry" name="industry' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-5"><input type="text" class="form-control summary" name="summary' + counterWorkExp + '"/></td>';

		cols += '<td class="col-sm-1"><input type="button" class="ibtnWorkExpDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#workExp").append(newRow);
		counter++;
	});

	$("#workExp").on("click", ".ibtnWorkExpDel", function (event) {
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
			workExpObj.startDate = row.find(".startDate").eq(0).val();
			workExpObj.endDate = row.find(".endDate").eq(0).val();  
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

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				// console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";


				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});

//	-------------------------------------------------------workExperience ends here----------------------------------------	

	//---------------------------------------------------Education -------------------------------------------------------
	var counterEducation = 0;

	$("#addrowEducation").on("click", function () {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-2"><input type="text" class="form-control degree" name="degree' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control school" name="school' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control fieldOfStudy" name="fieldOfStudy' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly" class="form-control startDate datepicker" name="startDate' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly"  class="form-control endDate datepicker" name="endDate' + counterEducation + '"/></td>';

		cols += '<td class="col-sm-2"><input type="button" class="ibtnEducationDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#educationTable").append(newRow);
		counter++;
	});

	$("#educationTable").on("click", ".ibtnEducationDel", function (event) {
		$(this).closest("tr").remove();       
		counter -= 1
	});

	$("#EducationBtn").on("click", function () {

		$('#overlay').fadeIn();

		var educationList = new Array();
		$("#educationTable tbody tr").each(function () {
			var row = $(this);
			var educationObj = {};
			educationObj.degree = row.find(".degree").eq(0).val();  
			educationObj.school = row.find(".school").eq(0).val();
			educationObj.fieldOfStudy = row.find(".fieldOfStudy").eq(0).val();
			educationObj.startDate = row.find(".startDate").eq(0).val();
			educationObj.endDate = row.find(".endDate").eq(0).val();  


			educationList.push(educationObj);
		});

		$.ajax({
			type: "POST",
			url: "/api/candidate/education/update",
			data: JSON.stringify(educationList),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
				if(data.httpStatus == "OK"){

					jQuery('#educationFragment div').html('');

					$.each(data.output, function(key,value) {
						$( "#educationFragment" ).append(
								'<div><ul><li class="col-md-6">'+
								'<span>'+ value.degree +'</span> <br>' + 
								'<span>'+ value.fieldOfStudy +'</span>-<span>'+ value.school +'</span> <br>'+
								'<span>'+value.startDate+'</span> -<span>'+value.endDate+'</span> <br></li></ul></div>'
						)
					}); 
				}
			},
			error: function (e) {

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				// console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);

				window.location.href="/candidate/refresh";
				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});
//	-------------------------------------------------------------Education ends here------------------------------------------


	//---------------------------------------------------Skill -------------------------------------------------------


	$("#addrowSkill").on("click", function () {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-8"><input type="text" class="form-control name" /></td>';
		cols += '<td class="col-sm-3"><input type="text" class="form-control proficientLevel" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnSkillDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#skillModel table.order-list").append(newRow);

	});

	$("#skillModel table.order-list").on("click", ".ibtnSkillDel", function (event) {
		$(this).closest("tr").remove();       

	});

	$("#skillBtn").on("click", function () {

		$('#overlay').fadeIn();

		var skillList = new Array();
		$("#skillModel tbody tr").each(function () {
			var row = $(this);
			var skillObj = {};
			skillObj.name = row.find(".name").eq(0).val();  
			skillObj.proficientLevel = row.find(".proficientLevel").eq(0).val();



			skillList.push(skillObj);
		});

		$.ajax({
			type: "POST",
			url: "/api/candidate/skill/update",
			data: JSON.stringify(skillList),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
				if(data.httpStatus == "OK"){

					jQuery('#skillFragment').html('');

					$.each(data.output, function(key,value) {
						$( "#skillFragment" ).append(
								'<div class="col-md-6">'+
								'<div class="row prog-row">' +
								'<div class="col-sm-6"><span>'+value.name+'</span></div>' +
								'<div class="col-sm-6">' +
								'<div class="progress">' +
								'<div class="progress-bar" role="progressbar" style="width: '+value.proficientLevel+'%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>' +
								'</div></div></div></div>'
						)
					}); 
				}
			},
			error: function (e) {

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				//console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});
//	-------------------------------------------------------------Skill ends here------------------------------------------

	//---------------------------------------------------contact -------------------------------------------------------


	$("#addrowContact").on("click", function () {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><input type="text" class="form-control countryCode" /></td>';
		cols += '<td class="col-sm-8"><input type="text" class="form-control mobileNumber" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnContactDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#contactModel table.order-list").append(newRow);

	});

	$("#contactModel table.order-list").on("click", ".ibtnContactDel", function (event) {
		$(this).closest("tr").remove();       

	});

	$("#contactBtn").on("click", function () {

		$('#overlay').fadeIn();

		var contactList = new Array();
		$("#contactModel tbody tr").each(function () {
			var row = $(this);
			var contactObj = {};
			contactObj.countryCode = row.find(".countryCode").eq(0).val();  
			contactObj.mobileNumber = row.find(".mobileNumber").eq(0).val();



			contactList.push(contactObj);
		});

		$.ajax({
			type: "POST",
			url: "/api/candidate/mobile/update",
			data: JSON.stringify(contactList),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
				if(data.httpStatus == "OK"){

					jQuery('#contactFragment').html('');

					$.each(data.output, function(key,value) {
						$( "#contactFragment" ).append(
								'<div>' +
								'<span>'+value.mobileNumber+'</span>' +
								'</div>'
						)
					}); 
				}
			},
			error: function (e) {

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				//console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});
//	-------------------------------------------------------------contact ends here------------------------------------------     


	//---------------------------------------------------Social Profile -------------------------------------------------------


	$("#addrowSocial").on("click", function () {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><select class="form-control type"><option value="facebook">Facebook</option><option value="twitter">Twitter</option><option value="linkedin">Linked-in</option><option value="github">Github</option><option value="other">Other</option></select></td>';
		cols += '<td class="col-sm-8"><input type="text" class="form-control url" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnSocialDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#socialModel table.order-list").append(newRow);

	});

	$("#socialModel table.order-list").on("click", ".ibtnSocialDel", function (event) {
		$(this).closest("tr").remove();       

	});

	$("#socialBtn").on("click", function () {

		$('#overlay').fadeIn();

		var socialList = new Array();
		$("#socialModel tbody tr").each(function () {
			var row = $(this);
			var socialObj = {};
			socialObj.type = row.find(".type").eq(0).val();  
			socialObj.url = row.find(".url").eq(0).val();



			socialList.push(socialObj);
		});

		$.ajax({
			type: "POST",
			url: "/api/candidate/social/update",
			data: JSON.stringify(socialList),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
				if(data.httpStatus == "OK"){

					jQuery('#socialFragment').html('');

					$.each(data.output, function(key,value) {

						var socialTag ="";

						if(value.type =='facebook'){
							socialTag = '<i class="fab fa-facebook-f"></i>'
						}else if(value.type=='twitter'){
							socialTag = '<i class="fab fa-twitter"></i>'
						}else if(value.type=='linkedin'){
							socialTag = '<i class="fab fa-linkedin-in"></i>'
						}else if(value.type=='github'){
							socialTag = '<i class="fab fa-github"></i>'
						}else{
							socialTag = '<i class="fas fa-exclamation"></i>'
						}

						$( "#socialFragment" ).append(
								'<li>' + 

								socialTag+

								'<span style="margin-left: 10px">'+ value.url +'</span></li>'
						)
					}); 
				}
			},
			error: function (e) {

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				//console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});
//	-------------------------------------------------------------social ends here------------------------------------------      



//	---------------------------------------------------hobby -------------------------------------------------------


	$("#addrowHobby").on("click", function () {
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><select class="form-control type"><option value="writing" >Writing</option><option value="cycling" >Cycling</option><option value="football" >Football</option><option value="movies" >Movies</option><option value="travel" >Travel</option><option value="games" >Games</option><option value="other" >Other</option></select></td>';
		cols += '<td class="col-sm-8"><input type="text" class="form-control hobby" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnHobbyDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#hobbyModel table.order-list").append(newRow);

	});

	$("#hobbyModel table.order-list").on("click", ".ibtnHobbyDel", function (event) {
		$(this).closest("tr").remove();       

	});

	$("#hobbyBtn").on("click", function () {

		$('#overlay').fadeIn();

		var hobbyList = new Array();
		$("#hobbyModel tbody tr").each(function () {
			var row = $(this);
			var hobbyObj = {};
			hobbyObj.type = row.find(".type").eq(0).val();  
			hobbyObj.hobby = row.find(".hobby").eq(0).val();



			hobbyList.push(hobbyObj);
		});

		$.ajax({
			type: "POST",
			url: "/api/candidate/hobby/update",
			data: JSON.stringify(hobbyList),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (data) {

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
				if(data.httpStatus == "OK"){

					jQuery('#hobbyFragment').html('');

					$.each(data.output, function(key,value) {

						var hobbyTag ="";

						if(value.type =='writing'){
							hobbyTag = '<i class="fas fa-pencil-alt"></i>'
						}else if(value.type=='cycling'){
							hobbyTag = '<i class="fas fa-bicycle"></i>'
						}else if(value.type=='football'){
							hobbyTag = '<i class="fas fa-futbol"></i>'
						}else if(value.type=='movies'){
							hobbyTag = '<i class="fas fa-film"></i>'
						}else if(value.type=='travel'){
							hobbyTag = '<i class="fas fa-plane-departure"></i>'
						}else if(value.type=='games'){
							hobbyTag = '<i class="fas fa-gamepad"></i>'
						}else{
							hobbyTag = '<i class="fas fa-exclamation"></i>'
						}



						$( "#hobbyFragment" ).append(
								'<li>' + 

								hobbyTag+

								'<br> <span>'+ value.hobby +'</span>'
						)
					}); 
				}
			},
			error: function (e) {

				/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                     + e.responseText + "&lt;/pre&gt;";
                 $('#feedback').html(json);*/

				//console.log("ERROR : ", e.responseText);
				// $("#btn-search").prop("disabled", false);
				window.location.href="/candidate/refresh";

				$('#overlay').fadeOut();
				// $('#workExp').modal('hide');
			}
		});
	});
//	-------------------------------------------------------------hobby ends here------------------------------------------      
	/*
    $("#avatarBtn").on("click", function () {

      	 $('#overlay').fadeIn();



      	let formData = new FormData(); 
        formData.append("file", avatarUpload.files[0]);
        let response = await fetch('/api/candidate/avatar/update', {
          method: "POST", 
          body: formData
        }); 

        if (response.status == 200) {
          alert("File successfully uploaded.");
        }
      });   
	 */





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

		if($("#summary-form").valid()){
			var json = $(this).serializeFormJSON();
			//alert(JSON.stringify(data));
			summary_ajax_submit(json);
		}


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

			// console.log("ERROR : ", e.responseText);
			// $("#btn-search").prop("disabled", false);
			window.location.href="/candidate/refresh";

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
				$("#placeOfBirthView").text(data.output.placeOfBirth);
				$("#maritalStatusView").text(data.output.maritalStatus);
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

			//console.log("ERROR : ", e.responseText);
			// $("#btn-search").prop("disabled", false);
			window.location.href="/candidate/refresh";

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





async function uploadFile() {
	$('#overlay').fadeIn();

	let formData = new FormData(); 
	formData.append("file", avatarUpload.files[0]);
	let response = await fetch('/api/candidate/avatar/update', {
		method: "POST", 
		body: formData
	}); 

	if (response.status == 200) {
		$('#avatarUpload').val('');  
		let json = await response.json();

		if(json.httpStatus == "OK"){
			$("#avatarImage").attr("src",'data:image/jpg;base64,'+ json.output.image);
		}


	}else{
		window.location.href="/candidate/refresh";
	}

	$('#overlay').fadeOut();
}




async function uploadResume() {
	$('#overlay').fadeIn();

	let formData = new FormData(); 
	formData.append("file", resumeUpload.files[0]);
	let response = await fetch('/api/candidate/resume/update', {
		method: "POST", 
		body: formData
	}); 

	if (response.status == 200) {
		$('#resumeUpload').val('');  
		let json = await response.json();

		if(json.httpStatus == "OK"){
			//$("#avatarImage").attr("src",'data:image/jpg;base64,'+ json.output.image);
			
			
				$( "#downloadResumeTable tbody" ).append(
						
						'<tr ><td class="col-sm-9"><input type="hidden" class="titleClazz" value="'+json.output.title+'" /><input type="hidden" class="emailClazz" value="'+json.output.candidateId+'" /><span>'+json.output.fileName+'</span></td>' +
						
						'<td class="col-sm-2"><a href="/candidateService/downloadResume?email='+json.output.candidateId+'&title='+json.output.title+'"><i class="fa fa-download" aria-hidden="true"></i> Click Here</a></td>' +

						'<td class="col-sm-1"><button type="button" class="iDelResume btn fa fa-trash"></button></td></tr>'
						
						
				)
				
				
			 
		}


	}else{
		window.location.href="/candidate/refresh";
	}

	$('#overlay').fadeOut();
}




function deleteResume(email,title, obj){
	
	//window.location.href="/candidateService/downloadResume?email="+email+"&title="+title;
	
	
	var search = {}

	$('#overlay').fadeIn();

	$.ajax({
		type: "post",
		contentType: 'application/json; charset=utf-8',
		url:  "/api/candidate/resume/delete?email="+email+"&title="+title,
		cache: false,
		timeout: 600000,
		success: function (data) {

			/* var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
            $('#feedback').html(json);*/


			if(data.httpStatus == "OK"){
				console.log("SUCCESS : ", data);

				$(obj).closest("tr").remove();       
				


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
			window.location.href="/candidate/refresh";

			$('#overlay').fadeOut();
			// $('#profile').modal('hide');
		}
	});
	
	
	
	
}