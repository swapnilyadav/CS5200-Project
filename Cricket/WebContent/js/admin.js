$(document).ready(function(){
		$("#addplayers").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'createplayers'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		$("#updateplayers").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'updateplayers'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		$("#createteams").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'createteams'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
					
				}
			});
		});
		
		
		$("#addupcomingmatches").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'addupcomingmatches'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		$("#addarchivedmatches").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'addarchivedmatches'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		$("#worldcuphistory").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'worldcuphistory'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		$("#addnews").click(function(){
			$('#spinner').fadeIn('fast');
			$.ajax({
				url : 'adminajax.jsp',
				data : {
					action:'addnews'
				},
				dataType: 'text',
				type: "POST",
				success:function(){
					$('#spinner').stop().fadeOut('fast');
				}
			});
		});
		
		

		
	});


