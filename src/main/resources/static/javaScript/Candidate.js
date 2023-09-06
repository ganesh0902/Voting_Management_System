$(document).ready(function () { 
	
	var userId=$("#uid").val();
	
	var candidateId=""
	
	$(".vt").click(function(e){
		
		var checkboxId = $(this).attr("id");
		 candidateId=checkboxId; 
		
		$("#cid").val(candidateId);
		
	});
	
	$("#sub").click(function(e){
				
		if(candidateId!="")
		{
		$.ajax({
                url: '/vote/addVote', // URL of your Spring Boot endpoint
                type: 'POST',
                data:{
					candidateId:candidateId,
					userId:userId
					
				},
                success: function(data) {
					$('#sub').prop('disabled', true);
                    alert("Vote Added Successfully");
                },
                error: function() {
                    $('#result').text('Error fetching data');
                }
            });	
            }
            else{				
				alert("Please select  One Candidate");
			}
            	
		return false;
	});
	
	
	$("#submit").click(function(){
		
		alert(" user has already voted");
		return false;
	});	
})

function logout()
	{
		//window.history.forward();
	}
	
	//setTimeout("logout()",0);
	
	//window.onunload=function(){null};