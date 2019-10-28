var Dialog = (function() {
	var dialog = {};
	
	dialog.show = function(id) {
		$('#' + id).modal({ backdrop:"static" });
	};
		
	dialog.hide = function(id) {
		$('#' + id).modal('hide');
	};

	dialog.setTitle = function(id, title) {
		$('#' + id + '_title').html(title);
	};
	
	dialog.bindOnShow = function(id, fn) {
		$('#' + id).on('shown.bs.modal', fn);
	};
	
	dialog.bindOnHide = function(id, fn) {
		$('#' + id).on('hidden.bs.modal', fn);
	};

	dialog.showAlert = function(message, title) {
		$('#defaultAlertDialogContent').html(message);
		if (title && title != '') {
			Dialog.setTitle(title);
		}
		Dialog.show('defaultAlertDialog');
	};
	
	dialog.showConfirm = function(message, callback, title) {
		$('#defaultConfirmDialogContent').html(message);
		if (title && title != '') {
			Dialog.setTitle(title);
		}
		$('#btnDefaultConfirmDialogConfirm').click(function() {
			console.log("trigger confirm");
			Dialog.hide('defaultConfirmDialog');
			callback();
		});
		Dialog.show('defaultConfirmDialog');
	};
	
	return dialog;
})();

