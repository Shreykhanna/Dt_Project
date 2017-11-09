app.controller('FriendController',function($scope,$location,FriendService){
	/*
	 * data is for listsuggestedusers.html
	 */
	function listOfSuggestedUsers() {
		FriendService.listofsuggestedusers().then(function(response){
			$scope.suggestedusers=response.data
		},function(response)  {
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		
		})
	}
	/*
	 * data is for list of pendingrequests.html
	 */
	
	function listOfPendingRequests(){
		FriendService.listOfPendingRequests().then(function(response){
			$scope.pendingrequests=response.data;
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
			
		
		})
	}
	
	function listOfFriends(){
		FriendService.listOfFriends().then(function(response){
			$scope.friends=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		
		})
	}
	/*
	 * toId=user.username
	 */
	
	$scope.friendRequest=function(toId)
	{
		FriendService.friendRequest(toId).then(function(response){
			console.log(response.status)
			alert('Request has been sent successfully')
			
			listOfSuggestedUsers();
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		})
	}
	
	$scope.updatePendingRequest=function(pendingRequest,status){
		/*
		 * pendingRequest.status='P'
		 * pendingRequest.status=status -> assign 'A'/'D'
		 */
		console.log(pendingRequest.status)
		pendingRequest.status=status
		/*
		 * pendingRequest is an object of the type Friend
		 * id,fromId,toId and status (A/D)
		 */
		
		FriendService.updatePendingRequest(pendingRequest).then(function(response){
			listOfPendingRequests()
		},function(response){
			if(response.status==401)
				$location.path('/login')
				console.log(response.status)
		
		})	
		
	}
	/*
	 * for assigning data to $scope property
	 * data will be displayed in the HTML page
	 */
	
	listOfSuggestedUsers()
	listOfPendingRequests()
	listOfFriends()
})