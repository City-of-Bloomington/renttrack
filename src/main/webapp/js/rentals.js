clicked_button_id="";

var icons = {
    header:"ui-icon-circle-plus",
    activeHeader:"ui-icon-circle-minus"
};

$(".date").datepicker({
    nextText: "Next",
    prevText:"Prev",
    buttonText: "Pick Date",
    showOn: "both",
    navigationAsDateFormat: true,
    buttonImage: "/renttrack/js/calendar.gif"
});
// auto complete for owners 
$("#owner_name").autocomplete({
    source: APPLICATION_URL + "ownerService?format=json",
    minLength: 3,
		dataType:"json",
    delay: 50,
    select: function( event, ui ) {
        if(ui.item){
            $("#owner_id").val(ui.item.id);						
        }
    }
		})
$("#agent_name").autocomplete({
    source: APPLICATION_URL + "agentService?agentOnly=y",
    minLength: 3,
		dataType:"json",
    delay: 50,
    select: function( event, ui ) {
        if(ui.item){
            $("#agent_id").val(ui.item.id);						
        }
    }
		})
$("#address_text").autocomplete({
    source: APPLICATION_URL + "addressService?format=json",
    minLength: 3,
		dataType:"json",
    delay: 100,
    select: function( event, ui ) {
        if(ui.item){
            $("#address_id").val(ui.item.id);						
        }
    }
		})
$("#can_title").autocomplete({
    source: APPLICATION_URL + "canService?format=json",
    minLength: 3,
		dataType:"json",
    delay: 100,
    select: function( event, ui ) {
        if(ui.item){
            $("#can_type").val(ui.item.type);						
            $("#item1").val(ui.item.item1);
						$("#item2").val(ui.item.item2);
						$("#item3").val(ui.item.item3);
						$("#item4").val(ui.item.item4);
						$("#item5").val(ui.item.item5);
						$("#item6").val(ui.item.item6);
						$("#item7").val(ui.item.item7);
						$("#item8").val(ui.item.item8);
        }
    }
		})
//
// accept digits only
//
$("#tax_id_one").keypress(function(event) {
		var key = event.charCode || event.keyCode || 0;
		if( !(key == 8                                // backspace
					|| key == 46                              // delete
					|| (key >= 35 && key <= 40)     // arrow keys/home/end
					|| (key >= 48 && key <= 57)     // numbers on keyboard
					)   
      ){
        event.preventDefault();     // Prevent character input
    }
});
$("#tax_id_one").keyup(function(){		
		var xx = $(this).val();
		var len = xx.length;
		if(len == 24){
				var xx2 = $("#tax_id_multiple").val();
				if(xx2.length > 0){
						xx2 += " ";
				}
				xx2 += xx;
				$("#tax_id_multiple").val(xx2);
				$(this).val("");// start all over
		}
		else if(len == 2 || len == 5 || len == 8 || len == 12 || len == 20){
				$(this).val(xx+"-");
		}
		else if(len == 16){
				$(this).val(xx+".");
		}
});

$("#selection_id").change(function() {
		$("#action2").val("refresh");
    $("#form_id").submit();
});

$("#a_disabled").attr('disabled','disabled');
$(document).on('click', 'a', function(e) {
    if ($(this).attr('disabled') == 'disabled') {
        e.preventDefault();
    }
});

$(document).on("click","button", function (event) {
	clicked_button_id = event.target.id;
});

function doRefresh(){
		document.getElementById("action2").value="Refresh";		
		document.getElementById("form_id").submit();				
}
$('#show_info_button').click(function() {
    $('#show_info').hide();
    $('#hide_info').show();
		return false;
});
$('#hide_info_button').click(function() {
    $('#show_info').show();
    $('#hide_info').hide();
		return false;
});
var popupWin;
function popwit(url, name) {
		var args = "top=200,left=200,height=300,width=350,toolbar=0,menubar=0,location=0";
    if(typeof(popupWin) != "object" || popupWin.closed)  { 
        popupWin =  window.open(url, name, args); 
    } 
    else{ 
        popupWin.location.href = url; 
    }
		if (window.focus) {popupWin.focus()}
		return false;		
 }
