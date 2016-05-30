// JavaScript Document
var rootUrl="http://localhost:8080/laptopshop/rest/inventory";
var findAll=function(){
	$.ajax({
		   type:'GET',
		   url: rootUrl,
		   dataType:"json",
		   success:renderList
		   });
};

var renderList=function(data){
	$.each(data, function(index,wine){
	$('#wineList').append('<li><a href="#" id="'+wine.id+
						  '">'+wine.name+'</a></li>');
						  });
}
// Waiting for the DOM to be ready - DOM Has been built
// Like on Load()
$(document).ready(function(){
	findAll();
});