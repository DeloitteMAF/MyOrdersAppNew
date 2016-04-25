//This is an event that fires when the user presses the device back button
document.addEventListener("deviceready", onDeviceReady, false);

$(".rotate").click(function(){
    $(this).toggleClass("down"); 
});

function onDeviceReady() {
	document.addEventListener("backbutton", backKeyDown, true);
	}

function backKeyDown()
	{
		//Check the device back button action happened in Employee.amx
		if ($('#sbpp1').length) {
			//Call the	java method in managed bean 
			adf.mf.api.invokeMethod("com.company.myorders.mobile.OrdersManagedBean", "closeSpringBoard", onInvokeSuccess, onFail);
			}
		else if ($('#detailpp1').length) {
			//Call the	java script nav directly
			adf.mf.api.amx.doNavigation("backToDashboard");
			}
		else if ($('#linespp1').length) {
			//Call the	java script nav directly
			adf.mf.api.amx.doNavigation("backToDetails");
			}
		else
			var cFirm = confirm("Are you sure you want to exit the application?");
			if (cFirm == true) {
				//Code to exit the application
				navigator.app.exitApp();
			}
	}

function onInvokeSuccess() {
	//To do code after success
	};

function onFail() {
	//To do code after failure
	};
