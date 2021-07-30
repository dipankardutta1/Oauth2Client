
	
$(function(){
	
	
	
	jQuery.validator.addMethod("noNumberAllowed", function(value, element) {
		// allow any non-whitespace characters as the host part
		return this.optional( element ) || /^[^0-9]+$/i.test( value );
	}, " Can not be Number");




	$("#summary-form").validate({

		rules:{
			firstName:{
				required:true,
				maxlength: 25,
				noNumberAllowed:true
			},
			lastName:{
				required:true,
				maxlength: 25,
				noNumberAllowed:true
			},
			profileTitle:{
				required:true,
				maxlength: 25
			},
			summary:{
				required:true,
				maxlength: 300
			}
		},
		messages:{
			firstName:{
				required:'First Name can not be blank',
				maxlength:'Length can not exceed 25 characters'
			},
			lastName:{
				required:'Last Name can not be blank',
				maxlength:'Length can not exceed 25 characters'
			},
			profileTitle:{
				required:'Profile Title can not be blank',
				maxlength:'Length can not exceed 25 characters'
			},
			summary:{
				required:'Summary  can not be blank',
				maxlength:'Length can not exceed 300 characters'
			}
		}


	});
	$("#profile-form").validate({

		rules:{
			aliasName:{
				required:true,
				maxlength: 15,
				noNumberAllowed:true
			},
			placeOfBirth:{
				required:true,
				maxlength: 25,

			},
			maritalStatus:{
				required:true,

			},
			birthDate:{
				required:true,

			}
		},
		messages:{
			aliasName:{
				required:'Alias Name can not be blank',
				maxlength:'Length can not exceed 15 characters'
			},
			placeOfBirth:{
				required:'Place Of Birth can not be blank',
				maxlength:'Length can not exceed 25 characters'
			},
			maritalStatus:{
				required:'Marital Status can not be blank',
			},
			birthDate:{
				required:'Birth Date  can not be blank',
			}
		}
	});

	$("#address-form").validate();
	
	$("#workExp-form").validate();
	
	$("#education-form").validate();
	
	$("#skill-form").validate();
	
	$("#contact-form").validate();
	$("#social-form").validate();
	$("#hobby-form").validate();
	
	
	
})
