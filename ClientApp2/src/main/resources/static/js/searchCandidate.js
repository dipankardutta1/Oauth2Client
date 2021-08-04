$(function(){
	

	$.validator.addMethod("noNumberAllowed", function(value, element) {
		// allow any non-whitespace characters as the host part
		return this.optional( element ) || /^[^0-9]+$/i.test( value );
	}, " Can not be Number");

	$("#candidateSearch-form").validate({

		rules:{
			firstName:{
				noNumberAllowed:true
			},
			lastName:{
				noNumberAllowed:true
			}
		},
		messages:{
			firstName:{
				noNumberAllowed:"No Number Please"
			},
			lastName:{
				noNumberAllowed:"No Number Please"
			}
		}
	});
	
	
	
	
	
});