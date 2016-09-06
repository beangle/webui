(function(bg, undefined) {
  function TableTree() {
    this.idComma = ".";

    this.findTreeTable = function(elm) {
      while (elm && elm.tagName != "TABLE") {
        elm = elm.parentNode;
      }
      return elm;
    }

    this.findTreeColumn = function(elm) {
      while (elm && elm.tagName != "TD") {
        elm = elm.parentNode;
      }
      var i = 0;
      while (elm && elm.previousElementSibling) {
        i += 1;
        elm = elm.previousElementSibling;
      }
      return i;
    }

    this.matchStart = function(target, pattern, matchDirectChildrenOnly) {
      var pos = target.indexOf(pattern);
      if (pos != 0) return false;
      if (!matchDirectChildrenOnly) return true;
      if (target.slice(pos + pattern.length, target.length).indexOf(
          this.idComma) >= 0) return false;
      return true;
    }

    this.countchar = function(id) {
      var cnt = 0;
      for (var i = 0; i < id.length; i++) {
        if (id.charAt(i) == this.idComma) cnt++;
      }
      return cnt + 1;
    }

    this.toggle = function(elm) {
      var rows = this.findTreeTable(elm).getElementsByTagName("TR");
      var treeColumn = this.findTreeColumn(elm);
      elm.className = "tree-folder";
      var newDisplay = "none";
      var thisID = elm.parentNode.parentNode.parentNode.id + this.idComma;
      // Are we expanding or contracting? If the first child is hidden, we
      // expand
      for (var i = 0; i < rows.length; i++) {
        var r = rows[i];
        if (this.matchStart(r.id, thisID, true)) {
          if (r.style.display == "none") {
            if (document.all)
              newDisplay = "block"; // IE4+ specific code
            else
              newDisplay = "table-row"; // Netscape and Mozilla
            elm.className = "tree-folder-open";
          }
          break;
        }
      }

      // When expanding, only expand one level. Collapse all desendants.
      var matchDirectChildrenOnly = (newDisplay != "none");

      for (var j = 0; j < rows.length; j++) {
        var s = rows[j];
        if (this.matchStart(s.id, thisID, matchDirectChildrenOnly)) {
          s.style.display = newDisplay;
          var cell = s.getElementsByTagName("td")[treeColumn];
          var tier = cell.getElementsByTagName("div")[0];
          var folder = tier.getElementsByTagName("a")[0];

          if (folder.getAttribute("onclick") != null) {
            folder.className = "tree-folder";
          }
        }
      }
    }
    /**
     * for tree select
     * 
     * @params ele 选择的行
     * @params toggleParent是否在选中时,级联选中父节点
     */
    this.select = function(elm, callback, toggleParent) {
      var rows = this.findTreeTable(elm).getElementsByTagName("tr");
      var thisID = elm.parentNode.parentNode.id;
      var checked = elm.checked;
      if (null == toggleParent) {
        toggleParent = true;
      }
      for (var i = 0; i < rows.length; i++) {
        var r = rows[i];
        if (r.id != ""
            && ((r.id.indexOf(thisID) == 0) || (thisID.indexOf(r.id) == 0))) {
          var cell = r.getElementsByTagName("td")[0];
          var input = cell.getElementsByTagName("input")[0];
          var fireCallback = false;
          if (thisID.indexOf(r.id) == 0) {
            if (checked && toggleParent) input.checked = true;
            if (thisID == r.id) fireCallback = true;
          } else {
            input.checked = checked;
            fireCallback = true;
          }
          if (fireCallback && callback) callback(input);
        }
      }
    }

    this.selectAll = function(elm, callback) {
      var rows = this.findTreeTable(elm).getElementsByTagName("tr");
      var thisID = elm.parentNode.parentNode.id;
      var checked = elm.checked;
      for (var i = 0; i < rows.length; i++) {
        var r = rows[i];
        if (r.id) {
          var cell = r.getElementsByTagName("td")[0];
          var inputs = cell.getElementsByTagName("input");
          if (inputs.length == 1) {
            var input = cell.getElementsByTagName("input")[0];
            input.checked = checked;
            if (callback) callback(input);
          }
        }
      }
    }

    // for collapse or display child nodes.
    this.collapseAllRows = function(tableId) {
      var rows = document.getElementById(tableId).getElementsByTagName("tr");
      for (var j = 0; j < rows.length; j++) {
        var r = rows[j];
        if (r.id.indexOf(this.idComma) > 1) {
          r.style.display = "none";
        }
      }
    }
    /**
     * added by chaostone for collapse special depth 2005-10-11
     */
    this.collapseAllRowsFor = function(tableId, depth) {
      var rows = document.getElementById(tableId).getElementsByTagName("tr");
      for (var j = 0; j < rows.length; j++) {
        var r = rows[j];
        if (this.countchar(r.id) > depth) {
          r.style.display = "none";
        }
        if (this.countchar(r.id) >= depth) {
          var rowFolder = document.getElementById(r.id + "_folder");
          if (rowFolder) {
            rowFolder.className = "tree-folder";
          }
        }
      }
    }
    /**
     * added by chaostone for display special depth. 2005-10-11
     */
    this.displayAllRowsFor = function(tableId, depth) {
      var rows = document.getElementById(tableId).getElementsByTagName("tr");
      for (var j = 0; j < rows.length; j++) {
        var r = rows[j];
        if (this.countchar(r.id) > depth) {
          r.style.display = "";
        }
        if (this.countchar(r.id) >= depth) {
          var rowFolder = document.getElementById(r.id + "_folder");
          if (rowFolder) {
            rowFolder.className = "tree-folder-open";
          }
        }
      }
    }
  }
  bg.tabletree = new TableTree();
})(beangle);