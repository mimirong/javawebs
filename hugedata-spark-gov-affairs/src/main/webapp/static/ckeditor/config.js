/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	// TOOLBAR
	config.toolbar = [
		{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote',
		'-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock' ] },
		{ name: 'links', items : [ 'Link','Unlink' ] },
		{ name: 'insert', items : [ 'Image','Table','HorizontalRule' ] },
		'/',
		{ name: 'styles', items : [ 'Font','FontSize' ] },
		{ name: 'colors', items : [ 'TextColor','BGColor' ] },
		{ name: 'tools', items : [ 'Maximize' ] }
	];
	
	// FONTS
	console.log(config.font_names);
	config.font_names = '微软雅黑;宋体;黑体;楷体;仿宋;' + config.font_names;
	
	// FONT SIZES
	config.fontSize_sizes = "8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px";
	
	// REMOVE BOTTOM STATUS
	config.removePlugins = 'elementspath';

    //清空预览区域显示内容
    config.image_previewText = '';

	// FILE BROWSER UPLOAD URL
	config.filebrowserUploadUrl  = contextPath + '/cke/imageUpload';
};
