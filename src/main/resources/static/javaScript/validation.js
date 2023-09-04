$(document).ready(function () { 
	
	$("#submit").click(function(){
		
		let fname=$("#fname").val();
		let email=$("#email").val();
		let password=$("#password").val();
		let phone=$("#phone").val();		
		
		let found=true;
				
		if(fname=="" || email=="" || password=="" || phone=="")
		{
			alert("All Field Are mandatory")
			found=false;
		}
		else{
													
				alert("Record Save Successfully");
			
		}
		
		return found;
	});
})