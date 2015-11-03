/** -----------------------table tree -----------------------------\
 |              from http://sstree.tigris.org/                     |
 |             introduce by chaostone 2005-10-11                   |
 |-----------------------------------------------------------------|
 |Usage:                                                           |
 |<link href="css/tableTree.css" rel="stylesheet" type="text/css"> |
 |<script language="JavaScript" type="text/JavaScript"             |
 |    src="scripts/tableTree.js"></script>                         |
 |<!--if your collapse column is not 0,here can be redefined       |
 |<script> defaultColumn=1;</script>                               |
 |.......you own html code....                                     |
 |<table>                                                          |
 |    <tr id="1">                                                  |
 |      <td>                                                       |
 |      <div class="tier1">	                                       |
 |	      <a href="#" class="folder"onclick="toggleRows(this)"></a>|
 |        other html code..                                        |
 |	    </div>                                                     |
 |      </td>                                                      |
 |      other td                                                   |
 |    </tr>                                                        |
 |    <tr id="1.1">                                                |
 |      <td>                                                       |
 |      <div class="tier2">	                                       |
 |	      <a href="#" class="doc" onclick="toggleRows(this)"></a>  |
 |        other html code..                                        |
 |	    </div>                                                     |
 |      </td>                                                      |
 |      other td                                                   |
 |    </tr>                                                        |
 |  </table>                                                       |
 \----------------------------------------------------------------*/
// default collapse column
var defaultColumn=0;
var treeIdComma=".";
// for collapse or display child nodes.
var treeImagePath=self.location.pathname.substring(0,self.location.pathname.substring(1).indexOf('/')+1)+"/static/themes/" + bg.uitheme + "/icons/16x16/tree/";
function toggleRows(elm) {
 var rows = document.getElementsByTagName("TR");
 elm.style.backgroundImage = "url("+treeImagePath+"plus.png)";
 var newDisplay = "none";
 var thisID = elm.parentNode.parentNode.parentNode.id + treeIdComma;
 // Are we expanding or contracting? If the first child is hidden, we expand
  for (var i = 0; i < rows.length; i++) {
   var r = rows[i];
   if (matchStart(r.id, thisID, true)) {

    if (r.style.display == "none") {
     if (document.all) newDisplay = "block"; //IE4+ specific code
     else newDisplay = "table-row"; //Netscape and Mozilla
     elm.style.backgroundImage = "url("+treeImagePath+"minus.png)";
    }
    break;
   }
 }

 // When expanding, only expand one level.  Collapse all desendants.
 var matchDirectChildrenOnly = (newDisplay != "none");
 
 for (var j = 0; j < rows.length; j++) {
   var s = rows[j];
   if (matchStart(s.id, thisID, matchDirectChildrenOnly)) {
     s.style.display = newDisplay;
     // chang 0 to defaultColumn by chaostone for add a checkbox before tree sometime
     var cell = s.getElementsByTagName("td")[defaultColumn]; 
     var tier = cell.getElementsByTagName("div")[0];
     var folder = tier.getElementsByTagName("a")[0];

     if (folder.getAttribute("onclick") != null) {
      folder.style.backgroundImage = "url("+treeImagePath+"plus.png)";
     }
   }
 }
}

function matchStart(target, pattern, matchDirectChildrenOnly) {
 var pos = target.indexOf(pattern);
 if (pos != 0) return false;
 if (!matchDirectChildrenOnly) return true;
 if (target.slice(pos + pattern.length, target.length).indexOf(treeIdComma) >= 0) return false;
 return true;
}

function collapseAllRows() {
 var rows = document.getElementsByTagName("tr");
 for (var j = 0; j < rows.length; j++) {
   var r = rows[j];
   if (r.id.indexOf(treeIdComma) >1 ) {
     r.style.display = "none";
   }
 }
}
/**
  added by chaostone for collapse special depth
  2005-10-11 
*/
function countchar(id){
	var cnt=0;
	for(var i=0;i<id.length;i++){
		if(id.charAt(i)==treeIdComma) cnt++;
	}
	return cnt+1;
}

function collapseAllRowsFor(depth) {
 var rows = document.getElementsByTagName("tr");
 for (var j = 0; j < rows.length; j++) {
   var r = rows[j];
   if (countchar(r.id) >depth ) {
     r.style.display = "none";
   }
   if(countchar(r.id) >=depth ) {
     var rowFolder=document.getElementById(r.id+"_folder");
     if(rowFolder){
     	rowFolder.style.backgroundImage = "url("+treeImagePath+"plus.png)";
     	rowFolder.className="folder";
     }
   }
 }
}
/**
  added by chaostone for display special depth.
  2005-10-11
*/
function displayAllRowsFor(depth) {
 var rows = document.getElementsByTagName("tr");
 for (var j = 0; j < rows.length; j++) {
   var r = rows[j];
   if (countchar(r.id) > depth) {
     r.style.display = "";
   }
   if (countchar(r.id) >= depth) {
     var rowFolder=document.getElementById(r.id+"_folder");
     if(rowFolder){
     	rowFolder.style.backgroundImage = "url("+treeImagePath+"minus.png)";
     	rowFolder.className="folder_open";
     }
   }
 }
}
    /**
     * for  tree select 
     * @params ele 选择的行
     * @params toggleParent是否在选中时,级联选中父节点
     */
    function treeToggle(elm,callback,toggleParent){
       var rows = document.getElementsByTagName("tr");
       var thisID = elm.parentNode.parentNode.id;
       var checked = elm.checked;
       if(null==toggleParent){
          toggleParent=true;
       }
       for (var i = 0; i < rows.length; i++) {
             var r = rows[i];
             if (r.id!=""&&((r.id.indexOf(thisID)==0)||(thisID.indexOf(r.id)==0))){
                 var cell = r.getElementsByTagName("td")[0];
                 var input = cell.getElementsByTagName("input")[0];
                 var fireCallback=false;
                 if(thisID.indexOf(r.id)==0){
                    if(checked&&toggleParent) input.checked=true;
                    if(thisID==r.id) fireCallback=true;
                 }else{
                    input.checked = checked;
                    fireCallback=true;
                 }
                 if(fireCallback && callback) callback(input);
             }
       }             
    }
    function treeToggleAll(elm,callback){
     var rows = document.getElementsByTagName("tr");
       var thisID = elm.parentNode.parentNode.id;
       var checked = elm.checked;
        for (var i = 0; i < rows.length; i++) {
             var r = rows[i];
             if (r.id){
                 var cell = r.getElementsByTagName("td")[0];
                 var inputs=cell.getElementsByTagName("input");
                 if(inputs.length==1){
                     var input = cell.getElementsByTagName("input")[0];
                     input.checked = checked;
                     if(callback) callback(input);
                 }
             }
        } 
    }
