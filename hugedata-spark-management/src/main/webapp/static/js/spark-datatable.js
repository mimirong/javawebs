(function( factory ){
	factory( jQuery, window, document );
}(function( $, window, document, undefined ) {
'use strict';
var DataTable = $.fn.dataTable;


/* Set the defaults for DataTables initialisation */
$.extend( true, DataTable.defaults, {
	dom:
		"tr" +
		"<'page_v1'<'page_v1_inner'ip>>",
	renderer: 'hugedataSpark'
} );


/* Default class modification */
$.extend( DataTable.ext.classes, {
	sWrapper:      "datatable_wrapper",
	sFilterInput:  "",
	sLengthSelect: "",
	sProcessing:   "datatable_processing"
} );


/* Paging button renderer */
DataTable.ext.renderer.pageButton.hugedataSpark = function ( settings, host, idx, buttons, page, pages ) {
	var api     = new DataTable.Api( settings );
	var classes = settings.oClasses;
	var lang    = settings.oLanguage.oPaginate;
	var aria = settings.oLanguage.oAria.paginate || {};
	var btnDisplay, btnClass, counter=0;

	var attach = function( container, buttons ) {
		var i, ien, node, button;
		var clickHandler = function ( e ) {
			e.preventDefault();
			if ( !$(e.currentTarget).hasClass('disabled') && api.page() != e.data.action ) {
				api.page( e.data.action ).draw( 'page' );
			}
		};

		for ( i=0, ien=buttons.length ; i<ien ; i++ ) {
			button = buttons[i];

			if ( $.isArray( button ) ) {
				attach( container, button );
			} else {
				btnDisplay = '';
				btnClass = '';

				switch ( button ) {
					case 'ellipsis':
						btnDisplay = '&#x2026;';
						btnClass = 'disabled';
						break;

					case 'first':
						btnDisplay = lang.sFirst;
						btnClass = button + (page > 0 ? '' : ' disabled');
						break;

					case 'previous':
						//btnDisplay = lang.sPrevious;
						btnDisplay = null;
						btnClass = button + (page > 0 ? '' : ' disabled');
						break;

					case 'next':
						btnDisplay = lang.sNext;
						btnClass = button + (page < pages-1 ? '' : ' disabled');
						break;

					case 'last':
						btnDisplay = lang.sLast;
						btnClass = button + (page < pages-1 ? '' : ' disabled');
						break;

					default:
						btnDisplay = button + 1;
						btnClass = page === button ? 'active' : '';
						break;
				}

				if (btnDisplay) {
					if (page == button) {
						node = $('<span class="cur">').html(btnDisplay).appendTo(container);	
					} else {
						node = $('<a>', {
									'href': '#',
									'data-dt-idx': counter,
									'tabindex': settings.iTabIndex
								}).html(btnDisplay).appendTo(container);	
					}
					
					settings.oApi._fnBindAction(
						node, {action: button}, clickHandler
					);

					counter++;
				}
			}
		}
	};

	var attachGoto = function(container) {
		var html1 = '<span>到<input type="text" name="jump_url" />页</span>';
		var inputSpan = $(html1).appendTo(container);
		
		var clickHandler = function(e) {
			e.preventDefault();
			var page = parseInt(inputSpan.find("input").val());
			if (!isNaN(page)) {
				api.page(page - 1).draw('page');
			}
		};
		
		inputSpan.children("input").on("keydown", function(e) {
			if (e.keyCode == 13) {
				var page = parseInt(inputSpan.find("input").val());
				if (!isNaN(page)) {
					api.page(page - 1).draw('page');
				}
			}
		});
		
		var html2 = '<a href="javascript:;" class="pages-goto">跳转</a>';
		var gotoButton = $(html2).appendTo(container);
		
		settings.oApi._fnBindAction($(gotoButton), null, clickHandler);
	};

	// IE9 throws an 'unknown error' if document.activeElement is used
	// inside an iframe or frame. 
	var activeEl;

	try {
		// Because this approach is destroying and recreating the paging
		// elements, focus is lost on the select button which is bad for
		// accessibility. So we want to restore focus once the draw has
		// completed
		activeEl = $(host).find(document.activeElement).data('dt-idx');
	}
	catch (e) {}

	attach($(host).empty(), buttons);
	attachGoto($(host));

	if ( activeEl ) {
		$(host).find( '[data-dt-idx='+activeEl+']' ).focus();
	}
};


return DataTable;
}));