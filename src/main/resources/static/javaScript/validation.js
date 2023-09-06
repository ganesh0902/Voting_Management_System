$(document).ready(function () { 
	
	$("#submit").click(function(){
		
		let fname=$("#fname").val();
		let email=$("#email").val();
		let password=$("#password").val();
		let phone=$("#phone").val();
		
		let msg=$("#error").val();
		
				alert(msg);
		console.log(phone.length);		
		
		let found=true;
				
		if(fname=="" || email=="" || password=="" || phone=="")
		{
			alert("All Field Are mandatory")
			found=false;
		}
		else{
				if(phone.length==10)
				{									
				alert("Record Save Successfully");
				}
				else{
					
					alert("Number Must be 10 Digit")
					found=false;					
				}
							
		}
		
		return found;
	});
})