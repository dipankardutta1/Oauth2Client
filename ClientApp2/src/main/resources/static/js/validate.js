$(function(){
	$("#summary-form").validate({
		
		rules:{
			firstName:{
				required:true
			},
			lastName:{
				required:true
			},
			profileTitle:{
				required:true
			},
			summary:{
				required:true
			}
		},
		messages:{
			firstName:{
				required:'First Name can not be blank'
			},
			lastName:{
				required:'Last Name can not be blank'
			},
			profileTitle:{
				required:'Profile Title can not be blank'
			},
			summary:{
				required:'Summary  can not be blank'
			}
		}
		 

	})
})
