$(document).on("click",".datepicker",function(){        

	$(this).datepicker({                        
		changeMonth: true,
		changeYear: true,
		maxDate : 0,
		dateFormat : 'mm/dd/yy'                    
	}).datepicker("show");
});

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

	var counter = $('#addressTable tbody tr').length;
	var counterWorkExp = $('#workExp tbody tr').length;
	var counterEducation = $('#educationTable tbody tr').length;
	var counterSkill = $('#skillTable tbody tr').length;
	var mobleCounter=$('#contactTable tbody tr').length;
	var socialCounter=$('#socialTable tbody tr').length;
	var hobbyCounter=$('#hobbyTable tbody tr').length;
	var languageCounter=$('#languageTable tbody tr').length;
	
	var validation = $('#address-form').validate();
	var workExpValidation = $('#workExp-form').validate();
	var educationValidation=$("#education-form").validate();
	var skillValidation=$("#skill-form").validate();
	var mobileValidation=$("#contact-form").validate();
	var socialValidation=$("#social-form").validate();
	var hobbyValidation=$("#hobby-form").validate();
	
	var languageValidation=$("#language-form").validate();
	
	var rules = {};
	var messages = {};
	var rules1 = {};
	var messages1 = {};


	var workExpRules={};
	var workExpRules1={};
	var workExpMessages={};
	var workExpMessages1={};

	var educationRules={};
	var educationRules1={};
	var educationMessages={};
	var educationMessages1={};

	var skillRules={};
	var skillRules1={};
	var skillMessages={};
	var skillMessages1={};

	var mobileRules={};
	var mobileRules1={};
	var mobileMessages={};
	var mobileMessages1={};

	var socialRules={};
	var socialRules1={};
	var socialMessages={};
	var socialMessages1={};

	var hobbyRules={};
	var hobbyRules1={};
	var hobbyMessages={};
	var hobbyMessages1={};

	var languageRules={};
	var languageRules1={};
	var languageMessages={};
	var languageMessages1={};
	
	$("table.order-list").on("click", ".ibtnDel", function (event) {
		$(this).closest("tr").remove();       
		//counter -= 1
	});


	$("#addrow").on("click", function () {


		counter++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-2"><input type="text" class="form-control country" name="country' + counter + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control state" name="state' + counter + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control city" name="city' + counter + '"/></td>';
		cols += '<td class="col-sm-5"><input type="text" class="form-control addressLine" name="address' + counter + '"/></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#addressTable").append(newRow);

		//$('#address-form').removeData('validator');
		$("#address-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			rules1[name] = {};
			messages1[name] = {};
			if(name.startsWith("add")){
				rules1[name] = {required: true,maxlength:100}; 
				messages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 100 length"};
			}else{
				rules1[name] = {required: true,maxlength:25}; 
				messages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 25 length"};

			}
		});
		validation.resetForm();
		validation.settings.rules =rules1;
		validation.settings.messages = messages1;

	});



	$("#address-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		rules[name] = {};
		messages[name] = {};
		if(name.startsWith("add")){
			rules[name] = {required: true,maxlength:100}; 
			messages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 100 length"};
		}else{
			rules[name] = {required: true,maxlength:25}; 
			messages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 25 length"};

		}
		//rules[name] = {required: true,maxlength:25}; 
		//messages[name]={required:"Please provide valid value",maxlength:"Can not Exceed 25 length"};
	});


	validation.resetForm();
	validation.settings.rules =rules;
	validation.settings.messages = messages;


	$("#address-form").on("submit", function (event) {

		event.preventDefault();


		//$('#overlay').fadeIn();
		if($("#address-form").valid()){
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

					//$('#overlay').fadeOut();
					// $('#address').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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

			});//end of ajax submit of address
		}
	});

	//end of form submit
//	-----------------------------------------------workexperience--------------------------------------------------




	$("#addrowWorkExp").on("click", function () {

		counterWorkExp++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-1"><input type="text" class="form-control title" name="title' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly" class="form-control startDate datepicker" name="startDate' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly" class="form-control endDate datepicker" name="endDate' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-1"><input type="text" class="form-control company" name="company' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control industry" name="industry' + counterWorkExp + '"/></td>';
		cols += '<td class="col-sm-3"><input type="text" class="form-control summary" name="summary' + counterWorkExp + '"/></td>';

		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#workExp").append(newRow);



		$("#workExp-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			workExpRules1[name] = {};
			workExpMessages1[name] = {};
			if(name.startsWith("summ")){
				workExpRules1[name] = {required: true,maxlength:100}; 
				workExpMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 100 length"};
			}else{
				workExpRules1[name] = {required: true,maxlength:25}; 
				workExpMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 25 length"};
			}
		});
		workExpValidation.resetForm();
		workExpValidation.settings.rules =workExpRules1;
		workExpValidation.settings.messages = workExpMessages1;

	});



	$("#workExp-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		workExpRules[name] = {};
		workExpMessages[name] = {};
		if(name.startsWith("summ")){
			workExpRules[name] = {required: true,maxlength:100}; 
			workExpMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 100 length"};
		}else{
			workExpRules[name] = {required: true,maxlength:25}; 
			workExpMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 25 length"};

		}
	});
	workExpValidation.resetForm();
	workExpValidation.settings.rules =workExpRules;
	workExpValidation.settings.messages = workExpMessages;



	$("#workExp-form").on("submit", function (event) {

		event.preventDefault();

		if($("#workExp-form").valid()){

			var workExperienceList = new Array();

			$("#workExp tbody tr").each(function () {
				var row = $(this);

				var workExpObj = {};
				workExpObj.title = row.find(".title").eq(0).val();  

				workExpObj.startDate =row.find(".startDate").eq(0).val();

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


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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

		}//end of valid check if



		//$('#overlay').fadeIn();
	});//end of form submit

//	-------------------------------------------------------workExperience ends here----------------------------------------	

	//---------------------------------------------------Education -------------------------------------------------------


	$("#addrowEducation").on("click", function () {
		counterEducation++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-2"><input type="text" class="form-control degree" name="degree' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control school" name="school' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" class="form-control fieldOfStudy" name="fieldOfStudy' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly" class="form-control startDate datepicker" name="startDate' + counterEducation + '"/></td>';
		cols += '<td class="col-sm-2"><input type="text" readonly="readonly"  class="form-control endDate datepicker" name="endDate' + counterEducation + '"/></td>';

		cols += '<td class="col-sm-2"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#educationTable").append(newRow);
		counter++;


		$("#education-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			educationRules1[name] = {};
			educationMessages1[name] = {};
			educationRules1[name] = {required: true,maxlength:20}; 
			educationMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 20 length"};

		});
		educationValidation.resetForm();
		educationValidation.settings.rules =educationRules1;
		educationValidation.settings.messages = educationMessages1;

	});



	$("#education-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		educationRules[name] = {};
		educationMessages[name] = {};
		educationRules[name] = {required: true,maxlength:20}; 
		educationMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 20 length"};

	});
	educationValidation.resetForm();
	educationValidation.settings.rules =educationRules;
	educationValidation.settings.messages = educationMessages;



	$("#education-form").on("submit", function (event) {

		event.preventDefault();
		//$('#overlay').fadeIn();
		if($("#education-form").valid()){
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


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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

		}	//end of education form valid check
	});
//	-------------------------------------------------------------Education ends here------------------------------------------


	//---------------------------------------------------Skill -------------------------------------------------------


	$("#addrowSkill").on("click", function () {

		counterSkill++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-8"><input type="text" class="form-control name " name="name'+ counterSkill + '"/></td>';
		cols += '<td class="col-sm-3"><input type="text" class="form-control proficientLevel" name="proficientLevel'+ counterSkill + '"/></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#skillModel table.order-list").append(newRow);



		$("#skill-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			skillRules1[name] = {};
			skillMessages1[name] = {};
			if(name.startsWith("pro")){
				skillRules1[name] = {required: true,range:[1,100]}; 
				skillMessages1[name]= {required:"Please provide valid value",range:"Range [1-100] "};

			}else{
				skillRules1[name] = {required: true,maxlength:20}; 
				skillMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 20 length"};
			}

		});
		skillValidation.resetForm();
		skillValidation.settings.rules =skillRules1;
		skillValidation.settings.messages = skillMessages1;

	});





	$("#skill-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		skillRules[name] = {};
		skillMessages[name] = {};
		if(name.startsWith("pro")){
			skillRules[name] = {required: true,range:[1,100]}; 
			skillMessages[name]= {required:"Please provide valid value",range:"Range [1-100] "};

		}else{
			skillRules[name] = {required: true,maxlength:20}; 
			skillMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 20 length"};

		}

	});
	skillValidation.resetForm();
	skillValidation.settings.rules =skillRules;
	skillValidation.settings.messages = skillMessages;


	$("#skill-form").on("submit", function (event) {

		event.preventDefault();
		//$('#overlay').fadeIn();
		if($("#skill-form").valid()){
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


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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
		}//valid checks ends here

	});//end of submit
//	-------------------------------------------------------------Skill ends here------------------------------------------

	//---------------------------------------------------contact -------------------------------------------------------


	$("#addrowContact").on("click", function () {
		contactTable++;
		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><input type="text" class="form-control countryCode" name="countryCode'+contactTable+'"/></td>';
		cols += '<td class="col-sm-8"><input type="text" class="form-control mobileNumber" name="mobileNumber'+contactTable+'"/></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#contactModel table.order-list").append(newRow);



		$("#contact-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			mobileRules1[name] = {};
			mobileMessages1[name] = {};
			if(name.startsWith("mob")){
				mobileRules1[name] = {required: true,maxlength:15,number:true}; 
				mobileMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 15 length",number:"Only Integral Number"};

			}else{
				mobileRules1[name] = {required: true,maxlength:6,number:true}; 
				mobileMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 6 length",number:"Only Integral Number"};

			}


		});
		mobileValidation.resetForm();
		mobileValidation.settings.rules =mobileRules1;
		mobileValidation.settings.messages =mobileMessages1;

	});



	$("#contact-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		mobileRules[name] = {};
		mobileMessages[name] = {};
		if(name.startsWith("mob")){
			mobileRules[name] = {required: true,maxlength:15,number:true}; 
			mobileMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 15 length",number:"Only Integral Number"};

		}else{
			mobileRules[name] = {required: true,maxlength:6,number:true}; 
			mobileMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 6 length",number:"Only Integral Number"};

		}

	});
	mobileValidation.resetForm();
	mobileValidation.settings.rules =mobileRules;
	mobileValidation.settings.messages = mobileMessages;



	$("#contact-form").on("submit", function (event) {

		event.preventDefault();
		//$('#overlay').fadeIn();

		if($("#contact-form").valid()){

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

						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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




		}//valid checks end here

	});//form submit end here
//	-------------------------------------------------------------contact ends here------------------------------------------     


	//---------------------------------------------------Social Profile -------------------------------------------------------


	$("#addrowSocial").on("click", function () {

		socialCounter++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><select name="domain'+ socialCounter + '"class="form-control type"><option value="facebook">Facebook</option><option value="twitter">Twitter</option><option value="linkedin">Linked-in</option><option value="github">Github</option><option value="other">Other</option></select></td>';
		cols += '<td class="col-sm-8"><input type="text" class="form-control profile" name="profileDomain'+ socialCounter + '" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#socialModel table.order-list").append(newRow);



		$("#social-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			
			socialRules1[name] = {};
			mobileMessages1[name] = {};
			
			if(name.startsWith("pro")){
				socialRules1[name] = {required: true,maxlength:50}; 
				socialMessages1[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 50 length"};
			}else{
				socialRules1[name] = {required: true}; 
				socialMessages1[name]= {required:"Please provide valid value"};
			}
		//	socialRules1[name] = {required: true}; 
			//socialMessages1[name]= {required:"Please provide valid value"};

		});
		socialValidation.resetForm();
		socialValidation.settings.rules =socialRules1;
		socialValidation.settings.messages =socialMessages1;



	});




	$("#social-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		socialRules[name] = {};
		socialMessages[name] = {};

		if(name.startsWith("pro")){
			socialRules[name] = {required: true,maxlength:50}; 
			socialMessages[name]= {required:"Please provide valid value",maxlength:"Can not Exceed 50 length"};
		}else{
			socialRules[name] = {required: true}; 
			socialMessages[name]= {required:"Please provide valid value"};
		}
		//socialRules[name] = {required: true}; 
		//socialMessages[name]= {required:"Please provide valid value"};

	});
	socialValidation.resetForm();
	socialValidation.settings.rules =socialRules;
	socialValidation.settings.messages =socialMessages;

	$("#social-form").on("submit", function (event) {

		//$('#overlay').fadeIn();

		event.preventDefault();
		//alert()
		if($('#social-form').valid()){

			var socialList = new Array();
			$("#socialModel tbody tr").each(function () {
				var row = $(this);
				var socialObj = {};
				socialObj.type = row.find(".type").eq(0).val();  
				socialObj.url = row.find(".profile").eq(0).val();
				alert(socialObj.url);

				socialList.push(socialObj);
			});

			$.ajax({
				type: "POST",
				url: "/api/candidate/social/update",
				data: JSON.stringify(socialList),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (data) {


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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
					alert(e);
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

		}//end of valid check
	});//end of form submit
//	-------------------------------------------------------------social ends here------------------------------------------      



//	---------------------------------------------------hobby -------------------------------------------------------



	$("#addrowHobby").on("click", function () {

		hobbyCounter++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><select name="hobby' + hobbyCounter + '"class="form-control type"><option value="writing" >Writing</option><option value="cycling" >Cycling</option><option value="football" >Football</option><option value="movies" >Movies</option><option value="travel" >Travel</option><option value="games" >Games</option><option value="other" >Other</option></select></td>';
		cols += '<td class="col-sm-8"><input name="hobbyDes' + hobbyCounter + '"type="text" class="form-control hobby" /></td>';


		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#hobbyModel table.order-list").append(newRow);




		$("#hobby-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			hobbyRules1[name] = {};
			hobbyMessages1[name] = {};
			hobbyRules1[name] = {required: true,maxlength:10}; 
			hobbyMessages1[name]= {required:"Please provide valid value",maxlength:"Cant Exceed 10 length "};

		});
		hobbyValidation.resetForm();
		hobbyValidation.settings.rules =hobbyRules1;
		hobbyValidation.settings.messages =hobbyMessages1;


	});




	$("#hobby-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		hobbyRules[name] = {};
		hobbyMessages[name] = {};
		hobbyRules[name] = {required: true,maxlength:10}; 
		hobbyMessages[name]= {required:"Please provide valid value",maxlength:"Cant Exceed 10 length "};

	});
	hobbyValidation.resetForm();
	hobbyValidation.settings.rules =hobbyRules;
	hobbyValidation.settings.messages =hobbyMessages;



	$("#hobby-form").on("submit", function (event) {


		//$('#overlay').fadeIn();
		event.preventDefault();

		if($("#hobby-form").valid()){


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


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
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

		}//end of valid checking

	});//end of form submit
//	-------------------------------------------------------------hobby ends here------------------------------------------      
	//------------------------------------------------------------Language start here-------------------------------------
	
	$("#addrowLanguage").on("click", function () {

		languageCounter++;

		var newRow = $("<tr>");
		var cols = "";

		cols += '<td class="col-sm-3"><input name="language' + languageCounter + '"type="text" class="form-control language"> </td>';
		cols += '<td class="col-sm-2"><select name="read' + languageCounter + '"class="form-control read"><option value="YES" >YES</option><option value="NO" >NO</option></select></td>';
		cols += '<td class="col-sm-2"><select name="write' + languageCounter + '"class="form-control write"><option value="YES" >YES</option><option value="NO" >NO</option></select></td>';
		cols += '<td class="col-sm-2"><select name="speak' + languageCounter + '"class="form-control speak"><option value="YES" >YES</option><option value="NO" >NO</option></select></td>';
		

		cols += '<td class="col-sm-1"><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
		newRow.append(cols);
		$("#languageModel table.order-list").append(newRow);

		var languageRules={};
		var languageRules1={};
		
		var languageMessages={};
		var languageMessages1={};
		$("#language-form").find($("input:text")).each(function(){ 
			var name = $(this).attr('name');
			languageRules1[name] = {};
			languageMessages1[name] = {};
			languageRules1[name] = {required: true}; 
			languageMessages1[name]= {required:"Please provide valid value"};

		});
		languageValidation.resetForm();
		languageValidation.settings.rules =languageRules1;
		languageValidation.settings.messages =languageMessages1;


	});




	$("#language-form").find($("input:text")).each(function(){ 
		var name = $(this).attr('name');
		languageRules[name] = {};
		languageMessages[name] = {};
		languageRules[name] = {required: true}; 
		languageMessages[name]= {required:"Please provide valid value"};

	});
	languageValidation.resetForm();
	languageValidation.settings.rules =languageRules;
	languageValidation.settings.messages =languageMessages;



	$("#language-form").on("submit", function (event) {


		//$('#overlay').fadeIn();
		event.preventDefault();

		if($("#language-form").valid()){


			var languageList = new Array();
			$("#languageModel tbody tr").each(function () {
				var row = $(this);
				var languageObj = {};
				languageObj.language = row.find(".language").eq(0).val();  
				languageObj.read = row.find(".read").eq(0).val();
				languageObj.write = row.find(".write").eq(0).val();  
				languageObj.speak = row.find(".speak").eq(0).val();
				
				languageList.push(languageObj);
			});

			$.ajax({
				type: "POST",
				url: "/api/candidate/language/update",
				data: JSON.stringify(languageList),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (data) {


					// $('#workExp').modal('hide');
					if(data.httpStatus == "OK"){
						$('#overlay').fadeIn();
						$('#overlay').fadeOut();
						jQuery('#languageFragment').html('');

						$.each(data.output, function(key,value) {

						

							$( "#languageFragment" ).append(
									'<span>'+ value.language +'</span>'+' --> &nbsp;'+'<span>'+ value.read +'</span>'+
									'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+'<span>'
									+ value.write +'</span>'+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+'<span>'+ value.speak +'</span>'+'<br>'
							)
						}); 
					}
				},
				error: function (e) {

					 var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
	                     + e.responseText + "&lt;/pre&gt;";
	                 $('#feedback').html(json);

					//console.log("ERROR : ", e.responseText);
					// $("#btn-search").prop("disabled", false);
					window.location.href="/candidate/refresh";

					$('#overlay').fadeOut();
					// $('#workExp').modal('hide');
				}
			});

		}//end of valid checking

	});//end of form submit
//-------------------------------------------------------------------------language ends here-------------------------------------------
	
	
	
	
	
	
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
		if($("#profile-form").valid()){
			// var formData = JSON.stringify($("#summary-form").serializeArray());
			var json = $(this).serializeFormJSON();
			//alert(JSON.stringify(data));
			profile_ajax_submit(json);

		}

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


	$("#avatar-form").validate();
	if($("#avatar-form").valid()){
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

	}//end of valid check

}//end of click method




async function uploadResume() {


	$("#resume-form").validate();
	if($("#resume-form").valid()){
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

	}//end of valid check

}//end of click method




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