app.controller('BlogPostController',function(BlogPostService,$scope,$location)
		{
	$scope.blogPost={}
	BlogPostService.getBlogPostsWaitingForApproval().then(function(response){
		$scope.blogPostsWaitingForApproval=response.data;
	},function(response){
		
	if(response.status==401)
		$location.path('/login')
	})
	
	
	BlogPostService.getBlogPostsApproved().then(function(response){
		$scope.blogPostsApproved=response.data
	},function(response){
		if(response.status==401)
			$location.path('/login')
		
	})
	

	$scope.addBlogPost=function(){
		//sub();
		BlogPostService.addBlogPost($scope.blogPost).then(function(response){
			console.log(response.status)
			sub();
			alert('BlogPost added successfully.. It is waiting for approval')
			$location.path('/getallblogs')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$location.path('/saveblogpost')
		})
	}
})

function sub(){
	var x=document.getElementById('myFile').files[0];
	var data=new FormData();
	data.append('image',x);
	$.ajax({
		url:'http://localhost:8090/project2_backend/addBlogPicture',
		type:'POST',
		data:data,
		enctype:'multipart/form-data',
		cache:false,
		processData:false,
		contentType:false,
		success:function(res){
		//	alert(res.status);
		},
		error:function(res){
		//		alert(res.status);
		}
	});	
}