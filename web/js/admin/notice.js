Ext.ns("admin.notice");

admin.notice.NoticePanel = Ext.extend(Ext.Panel, {

	border: false,

    initComponent: function() {
		
	    var config = {
	        id: 'noticePanel',
	        layout: 'border',
	        width: '100%',
	        height: 574,            
	        items: [new admin.notice.NoticeListPanel({region:'center'})]
	    };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.notice.NoticePanel.superclass.initComponent.call(this);
    },

    onRender:function() {
        admin.notice.NoticePanel.superclass.onRender.apply(this, arguments);
    }
});

// register xtype
Ext.reg('NoticePanel', admin.notice.NoticePanel);                  

admin.notice.NoticeListPanel = Ext.extend(Ext.Panel, {
    border: false,
    noticeListStore: null,
    noticeListGrid: null,
    initComponent: function() {

		// 공지사항  DataStore 생성
		noticeListStore = new Ext.data.JsonStore({
			storeId: 'noticeStore',
		    autoDestroy: true,
            totalProperty: 'total',
		    root: 'notices',
		    idProperty: 'id',
		    fields: [
		        'id',
		        'title',
		        'created',
		        'memberName',
		        'modified',
		        'modifierName',
		        'contents'
		    ],
		    proxy : new Ext.data.HttpProxy({
		    	url : 'notice/list',
                api: {
                    destroy : 'notice/del'
                }
            }),
            writer: new Ext.data.JsonWriter({
            }),
		    listeners: {
				loadexception: function(misc){
					console.log('공지사항 목록 데이터를 불러오는 중 오류가 발생했습니다.');
					console.log(misc);
				}
			}
		});

        // 공지사항 목록을 보여줄 Grid 생성
		noticeListGrid = new Ext.grid.GridPanel({
			store: noticeListStore,
			width: '100%',
        	columns: [
        	    {header: "id", dataIndex: 'id', hidden:true},
	            {header: "제목", dataIndex: 'title', sortable: true},
	            {header: "작성일", width: 35, dataIndex: 'created', sortable: true, align: 'center'},
	            {header: "작성자", width: 35, dataIndex: 'memberName', sortable: true, align: 'center'},
	            {header: "수정일", width: 35, dataIndex: 'modified', sortable: true, align: 'center'},
	            {header: "수정자", width: 35, dataIndex: 'modifierName', sortable: true, align: 'center'},
	            {header: "내용", dataIndex: 'contents', hidden:true}
	        ],
			sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
			viewConfig: {
				forceFit: true
			},
            loadMask: { msg: '공지사항 목록을 구성하는 중 입니다.' },

            // search bar on the top
            tbar:['->', {
	                ref: '../addBtn',
	                iconCls: 'icon-add',
	                text: '등록',
	                handler: function(btn, e) {
		            	var formPanel = new admin.notice.NoticeInsertFormPanel();
		                formPanel.render(noticeListGrid.getEl());
		                formPanel.show();
	                }
	            }, {
	                ref: '../delBtn',
	                iconCls: 'icon-del',
	                text: '삭제',
	                handler: function(btn, e) {
	            		if (noticeListGrid.getSelectionModel().getCount() > 0) {
	            			Ext.MessageBox.confirm('Confirm', '삭제하시겠습니까?', function(btn) {
			                	if (btn === "yes") {
			                		var rec = noticeListGrid.getSelectionModel().getSelected();
	                		    	noticeListStore.remove(rec);
	                		    	noticeListStore.storeAction = 'delete';
	                		    	noticeListStore.save();
			                	}
			                });
	            		} else {
            		        Ext.MessageBox.show({
            		           title: 'Warning',
            		           msg: '삭제할 공지사항을 선택해 주세요.',
            		           buttons: Ext.MessageBox.OK,
            		           icon: Ext.MessageBox.WARNING
            		       });
	            		}
//	            		
		                
		                
	                }
	            }
            ],
            
            //function showResult(btn){
             //   Ext.example.msg('Button Click', 'You clicked the {0} button', btn);
            //};

            // paging bar on the bottom
            bbar: new Ext.PagingToolbar({
                pageSize: 19,
                store: noticeListStore,
                displayInfo: true,
                displayMsg: 'Displaying topics {0} - {1} of {2}',
                emptyMsg: "조회된 공지사항이 없습니다."
            }),
            
            listeners: {
                rowdblclick: function(sender, rowIdx, event){
                    // sender.getSelectionModel().getSelected().data.id
                    var formPanel = new admin.notice.NoticeUpdateFormPanel();
                    formPanel.render(this.getEl());
                    Ext.getCmp('noticeUpdateFormPanel').getForm().loadRecord(sender.getStore().getAt(rowIdx));
                    formPanel.show();
                }
            }
        });

        var config = {
            id: 'NoticeListPanel',
            layout: 'fit',
            items: [noticeListGrid]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.notice.NoticeListPanel.superclass.initComponent.call(this);

        noticeListStore.load({params:{start:0, limit:19}});
    },
    onRender: function() {
        admin.notice.NoticeListPanel.superclass.onRender.apply(this, arguments);
    }
});

admin.notice.NoticeUpdateFormPanel = Ext.extend(Ext.Window, {
    initComponent: function() {

        var noticeUpdateFormPanel = new Ext.form.FormPanel({
            id: 'noticeUpdateFormPanel',
            border: false,
            layout:'border',
            reader: new Ext.data.JsonReader({
                fields: [
                    'id',
                    'title',
                    'contents'
                ]
            }),
            trackResetOnLoad: true,
            waitMsgTarget: true,
            labelAlign: 'top',
            items:[{
                region: 'center',
                layout: 'border',
                items:[{
	                baseCls:'x-plain',
	                region: 'center',
	                layout: 'form',
	                    defaults: {
	                        bodyStyle:'padding: 3px',
	                        baseCls:'x-plain'
	                    },
	                    items:[{
		                    	xtype:'textfield',
		                    	fieldLabel: '제목',
		                    	name:'title',
		                    	anchor:'100%'
		                    }, {
	                            name: 'contents',
	                            fieldLabel: '내용',
	                            xtype: 'htmleditor',
		                    	anchor:'100%', 
	                            height: 350
	                        }, {
	                        	xtype:'textfield',
	                        	name:'id', 
	                        	hidden:true
                        }]
                }]
            }],
            buttons: [{
                text: '수정',
                handler: function(){
                    Ext.getCmp('noticeUpdateFormPanel').getForm().submit({url:'notice/update', waitMsg:'공지사항을 저장중입니다.', submitEmptyText: false})
                }
            },{
                text: '닫기',
                handler: function(){
                    this.close();
                }.createDelegate(this)
            }],
            listeners: {
                actioncomplete: function(sender, action){
                    $.growlUI('공지사항 관리<br /> 공지사항을 수정했습니다.');
                    this.close();
                    noticeListStore.reload();
                }.createDelegate(this),
                actionfailed: function(sender, action){
                    $.growlUI('공지사항 관리<br /> 공지사항 수정 중 오류가 발생했습니다.');
                }
            }
        });

        var config = {
            modal: true,
            width: 900,
            height: 500,
            layout: 'fit',
            padding: 5,
            items: [noticeUpdateFormPanel]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.notice.NoticeUpdateFormPanel.superclass.initComponent.call(this);
    },
    onRender: function() {
        admin.notice.NoticeUpdateFormPanel.superclass.onRender.apply(this, arguments);
    }
});

admin.notice.NoticeInsertFormPanel = Ext.extend(Ext.Window, {
    initComponent: function() {

        var noticeInsertFormPanel = new Ext.form.FormPanel({
            id: 'noticeInsertFormPanel',
            border: false,
            layout:'border',
            reader: new Ext.data.JsonReader({
                fields: [
                    'id',
                    'title',
                    'contents'
                ]
            }),
            trackResetOnLoad: true,
            waitMsgTarget: true,
            labelAlign: 'top',
            items:[{
                region: 'center',
                layout: 'border',
                items:[{
	                baseCls:'x-plain',
	                region: 'center',
	                layout: 'form',
	                    defaults: {
	                        bodyStyle:'padding: 3px',
	                        baseCls:'x-plain'
	                    },
	                    items:[{
		                    	xtype:'textfield',
		                    	fieldLabel: '제목',
		                    	name:'title',
		                    	anchor:'100%'
		                    }, {
	                        	xtype:'checkbox',
	                        	name: 'notification',
	                        	boxLabel: '알림 여부',
	                        	hideLabel:true,
	                        	checked: true
	                        }, {
	                            name: 'contents',
	                            //fieldLabel: '내용',
	                            xtype: 'htmleditor',
	                            hideLabel:true,
		                    	anchor:'100%', 
	                            height: 350
	                        }, {
	                        	xtype:'textfield',
	                        	name:'id', 
	                        	hidden:true
                        }]
                }]
            }],
            buttons: [{
                text: '저장',
                handler: function(){
                    Ext.getCmp('noticeInsertFormPanel').getForm().submit({url:'notice/add', waitMsg:'공지사항을 저장중입니다.', submitEmptyText: false})
                }
            },{
                text: '닫기',
                handler: function(){
                    this.close();
                }.createDelegate(this)
            }],
            listeners: {
                actioncomplete: function(sender, action){
                    $.growlUI('공지사항 관리<br /> 공지사항을 등록했습니다.');
                    this.close();
                    noticeListStore.reload();
                }.createDelegate(this),
                actionfailed: function(sender, action){
                    $.growlUI('공지사항 관리<br /> 공지사항 등록 중 오류가 발생했습니다.');
                }
            }
        });

        var config = {
            modal: true,
            width: 900,
            height: 500,
            layout: 'fit',
            padding: 5,
            items: [noticeInsertFormPanel]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.notice.NoticeInsertFormPanel.superclass.initComponent.call(this);
    },
    onRender: function() {
        admin.notice.NoticeInsertFormPanel.superclass.onRender.apply(this, arguments);
    }
});