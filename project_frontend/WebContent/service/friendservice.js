/**
 * 
 */app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.listofsuggestedusers=function(){
		return $http.get("http://localhost:8090/project2_backend/getsuggestedusers")
		
	}
	
	friendService.friendRequest=function(toId){
	return $http.post("http://localhost:8090/project2_backend/friendrequest/" +toId)
	
	}
	
	friendService.listOfPendingRequests=function(){
	return $http.get("http://localhost:8090/project2_backend/getpendingrequests/")
	
	}
	
	
	friendService.getUserDetails=function(fromId){
		return $http.get("http://localhost:8090/project2_backend/getuserdetails/"+fromId)
		
		}
	
	friendService.updatePendingRequest=function(pendingRequest){
		return $http.put("http://localhost:8090/project2_backend/updatependingrequest",pendingRequest)
		
	}
	
	friendService.listOfFriends=function(){
		return $http.get("http://localhost:8090/project2_backend/listoffriends")
	}
	
	return friendService;

})