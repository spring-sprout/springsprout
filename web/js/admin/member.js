Ext.ns("admin.member");

admin.member.MemberMgtPanel = Ext.extend(Ext.Panel, {
    border: false,
    initComponent: function() {
        var config = {
            id: 'memberMgtPanel',
            layout: 'fit',
            items: [new admin.member.MemberListPanel()]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.member.MemberMgtPanel.superclass.initComponent.call(this);
    },
    onRender:function() {
        admin.member.MemberMgtPanel.superclass.onRender.apply(this, arguments);
    }
});

// register xtype
Ext.reg('MemberPanel', admin.member.MemberMgtPanel);

admin.member.MemberListPanel = Ext.extend(Ext.Panel, {
    border: false,
    initComponent: function() {

		// 사용자 목록  DataStore 생성
		var memberListStore = new Ext.data.JsonStore({
			storeId: 'memberStore',
            url: 'member/list',
            baseParams: {'name':'','email':'','allowedEmail':'','allowedGoogleTalk':''},
            autoDestroy: true,
            remoteSort: false,
            totalProperty: 'totalCount',
		    root: 'memberList',
		    idProperty: 'id',
		    fields: [
		        'id',
                'email',
                'name',
                'status',
                'joined',
                'outDate',
                'outReason',
                'blog',
                'isAllowedEmail',
                'isAllowedGoogleTalk',
                'avatar'
		    ],
		    listeners: {
				loadexception: function(misc){
					console.log('사용자 관리 목록 데이터를 불러오는 중 오류가 발생했습니다.');
					console.log(misc);
				}
			}
		});

        // 사용자 목록을 보여줄 Grid 생성
		var memberListGrid = new Ext.grid.GridPanel({
			store: memberListStore,
        	columns: [
	            {header: "이름", width: 40, dataIndex: 'name', sortable: true},
	            {header: "메일", dataIndex: 'email', sortable: true},
	            {header: "가입일자", width: 35, dataIndex: 'joined', sortable: true, align: 'center'},
	            {header: "블로그", dataIndex: 'blog', sortable: true},
	            {header: "eMail 수신", width: 30, dataIndex: 'isAllowedEmail', align: 'center',
                 renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                     return value.trim() === 'true' ? '수신' : '미수신';
                }},
	            {header: "GT 수신", width: 30, dataIndex: 'isAllowedGoogleTalk', align: 'center', 
                 renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                     return value.trim() === 'true' ? '수신' : '미수신';
                }},
                {header: "가입상태", width: 40, dataIndex: 'status', align: 'center',
                 renderer: function(value, metaData, record, rowIndex, colIndex, store) {
                    var valueText = '';
                    switch(parseInt(value)){
                        case 10:
                            valueText = '가입대기';
                            break;
                        case 20:
                            valueText = '가입';
                            break;
                        case 30:
                            valueText = '탈퇴';
                            break;
                        default:
                            valueText = '강제퇴출';

                    }
                    return valueText;
                }}
	        ],
			sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
			viewConfig: {
				forceFit: true
			},
            loadMask: { msg: '사용자 목록을 구성하는중 입니다.' },

            // search bar on the top
            tbar:[{
                text: 'Search:'
            },{
                id: 'cboMemberSearchType',
                xtype: 'combo',
                typeAhead: true,
                triggerAction: 'all',
                width: 50,
                editable: false,
                mode: 'local',
                displayField: 'desc',
                valueField: 'name',
                lazyInit: false,
                value: 'name',
                store: new Ext.data.ArrayStore({
                    fields: ['name', 'desc'],
                    data : [['name', '이름'],['email', '메일']]
                })
            }, ' ', {
                id: 'txtMemberSearchText',
                xtype: 'textfield',
                selectOnFocus: true,
                width: 200,
                listeners: {
                    'render': {fn:function(){
                        Ext.getCmp('txtMemberSearchText').getEl().on('keyup', function(event, sender){
                            if(event.getKey() === event.ENTER){
                                Ext.getCmp('btnMemberSearch').fireEvent('click');
                            }
                        }, this, {buffer:500});
                    }, scope:this}
                }
            }, '   ', {
                text: 'eMail 수신여부',
                menu: {
                    items: [
                        {text: '전체', checked: true, group: 'searchEmailCheckBox', handler: function(){ memberListStore.baseParams.allowedEmail = '';}},
                        {text: '수신', checked: false, group: 'searchEmailCheckBox', handler: function(){ memberListStore.baseParams.allowedEmail = 'true';}},
                        {text: '미수신', checked: false, group: 'searchEmailCheckBox', handler: function(){ memberListStore.baseParams.allowedEmail = 'false';}}
                    ]
                }
            }, '   ', {
                text: 'GT 수신여부',
                menu: {
                    items: [
                        {text: '전체', checked: true, group: 'searchGTCheckBox', handler: function(){ memberListStore.baseParams.allowedGoogleTalk = '';}},
                        {text: '수신', checked: false, group: 'searchGTCheckBox', handler: function(){ memberListStore.baseParams.allowedGoogleTalk = 'true';}},
                        {text: '미수신', checked: false, group: 'searchGTCheckBox', handler: function(){ memberListStore.baseParams.allowedGoogleTalk = 'false';}}
                    ]
                }
            }, '->', '-', {
                id: 'btnMemberSearch',
                xtype: 'button',
                text: '조회',
                width: 50,
                listeners: {
                    click: function(sender, event){
                        var searchWord = Ext.getCmp('txtMemberSearchText').getValue().trim();
                        if(Ext.getCmp('cboMemberSearchType').getValue() === 'name'){
                            memberListStore.baseParams.name = searchWord;
                            memberListStore.baseParams.email = '';
                        }
                        else{
                            memberListStore.baseParams.name = '';
                            memberListStore.baseParams.email = searchWord;
                        }
                        memberListStore.load({params:{start:0, limit:19}});
                    }
                }
            }],

            // paging bar on the bottom
            bbar: new Ext.PagingToolbar({
                pageSize: 19,
                store: memberListStore,
                displayInfo: true,
                displayMsg: '총 {2} 명의 회원이 조회되었습니다.',
                emptyMsg: "조회된 회원이 없습니다."
            }),

            // register event
            listeners: {
                rowdblclick: function(sender, rowIdx, event){
                    // sender.getSelectionModel().getSelected().data.id
                    var formPanel = new admin.member.MemberFormPanel();
                    formPanel.render(this.getEl());
                    Ext.getCmp('memberFormPanel').getForm().loadRecord(sender.getStore().getAt(rowIdx));
                    formPanel.show();
                }
            }
        });

        // hard config (can't be changed form ousdie)
        var config = {
            id: 'memberListPanel',
            layout: 'fit',
            items: [memberListGrid]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.member.MemberListPanel.superclass.initComponent.call(this);

        memberListStore.load({params:{start:0, limit:19}});
    },
    onRender: function() {
        admin.member.MemberListPanel.superclass.onRender.apply(this, arguments);
    }
});

admin.member.MemberFormTextFiled = Ext.extend(Ext.form.TextField, {
    width: 255,
    readOnly: true,
    initComponent: function() {
        var config = {
            style: {
                background: 'none repeat scroll 0 0 #DDDDDD'
            }
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.member.MemberFormTextFiled.superclass.initComponent.call(this);
    },
    onRender: function() {
        admin.member.MemberFormTextFiled.superclass.onRender.apply(this, arguments);
    }
});

// register xtype
Ext.reg('membertextfield', admin.member.MemberFormTextFiled);

admin.member.MemberFormPanel = Ext.extend(Ext.Window, {
    initComponent: function() {

        var memberFormPanel = new Ext.form.FormPanel({
            id: 'memberFormPanel',
            border: false,
            layout:'border',
            reader: new Ext.data.JsonReader({
                fields: [
                    'id',
                    'email',
                    'name',
                    'status',
                    'joined',
                    'outDate',
                    'outReason',
                    'blog',
                    'isAllowedEmail',
                    'isAllowedGoogleTalk',
                    'avatar'
                ]
            }),
            trackResetOnLoad: true,
            waitMsgTarget: true,
            items:[{
                region: 'north',
                layout: 'border',
                height: 80,
                border: false,
                items:[{
                    id: 'divMemberAvatar',
                    region: 'west',
                    width: 80,
                    border: true
                },{
                    region: 'center',
                    layout: 'table',
                    layoutConfig: {
                        columns: 3,
                        tableAttrs: {
                            style: {
                                width: '100%'
                            }
                        }
                    },
                    defaults: {
                        bodyStyle:'padding: 3px',
                        baseCls:'x-plain'
                    },
                    baseCls:'x-plain',
                    items:[
                        {html: '&nbsp;&nbsp;아이디'}, {xtype:'membertextfield',name:'id',width:219,colspan:2},
                        {html: '&nbsp;&nbsp;이름'}, {xtype:'membertextfield',name:'name',width:219,colspan:2},
                        {html: '&nbsp;&nbsp;상태'}, {
                            name: 'status',
                            hiddenName: 'status',
                            xtype: 'combo',
                            typeAhead: true,
                            triggerAction: 'all',
                            width: 80,
                            editable: false,
                            readOnly: true,
                            mode: 'local',
                            displayField: 'desc',
                            valueField: 'status',
                            submitValue: false,
                            lazyInit: false,
                            style: {
                                background: 'none repeat scroll 0 0 #DDDDDD'
                            },
                            store: new Ext.data.ArrayStore({
                                fields: ['status', 'desc'],
                                data : [[10, '가입대기'],[20, '가입'],[30, '탈퇴'],[40, '강제퇴출']]
                            }),
                            listeners: {
                                select: function(sender, record, index){
                                    var outReason = Ext.getCmp('memberFormPanel').getForm().findField('outReason');

                                    if(record.data.status === 40){
                                        // 강제퇴출시 사유를 입력받도록 수정
                                        outReason.getEl().setStyle('background', 'none repeat scroll 0 0 #FFFFFF');
                                        outReason.setReadOnly(false);
                                        outReason.focus();
                                    }
                                    else{
                                        outReason.getEl().setStyle('background', 'none repeat scroll 0 0 #DDDDDD');
                                        outReason.setReadOnly(true);
                                    }
                                }
                            }
                        }, {
                            xtype:'checkbox',
                            name: 'statusEdit',
                            hiddenName: 'statusEdit',
                            statusEdit: true,
                            boxLabel:'사용자 상태 수정',
                            handler: function(sender, checked){
                                var ststus = Ext.getCmp('memberFormPanel').getForm().findField('status');
                                ststus.setReadOnly(!checked);
                                ststus.getEl().setStyle('background',
                                    'none repeat scroll 0 0 #' + (checked ? 'FFFFFF' : 'DDDDDD')
                                );
                            }
                        }
                    ]
                }]
            },{
                region: 'center',
                baseCls:'x-plain',
                layout: 'table',
                    layoutConfig: {
                        columns: 2,
                        tableAttrs: {
                            style: {
                                width: '100%'
                            }
                        }
                    },
                    defaults: {
                        bodyStyle:'padding: 3px',
                        baseCls:'x-plain'
                    },
                    baseCls:'x-plain',
                    items:[
                        {html: '이메일'},{xtype:'membertextfield',name:'email'},
                        {html: '블로그'},{xtype:'membertextfield',name:'blog'},
                        {html: '이메일 수신여부'},{
                            name: 'isAllowedEmail',
                            hiddenName: 'isAllowedEmail',
                            xtype: 'combo',
                            typeAhead: true,
                            triggerAction: 'all',
                            width: 80,
                            editable: false,
                            mode: 'local',
                            displayField: 'desc',
                            valueField: 'name',
                            submitValue: false,
                            lazyInit: false,
                            store: new Ext.data.ArrayStore({
                                fields: ['name', 'desc'],
                                data : [['true', '수신'],['false', '미수신']]
                            })
                        },
                        {html: '구글토크 수신여부'},{
                            name: 'isAllowedGoogleTalk',
                            hiddenName: 'isAllowedGoogleTalk',
                            xtype: 'combo',
                            typeAhead: true,
                            triggerAction: 'all',
                            width: 80,
                            editable: false,
                            mode: 'local',
                            displayField: 'desc',
                            valueField: 'name',
                            submitValue: false,
                            lazyInit: false,
                            store: new Ext.data.ArrayStore({
                                fields: ['name', 'desc'],
                                data : [['true', '수신'],['false', '미수신']]
                            })
                        },
                        {html: '가입일자'}, {xtype:'membertextfield', name:'joined'},
                        {html: '탈퇴일자'}, {xtype:'membertextfield', name:'outDate'},
                        {html: '탈퇴사유'}, new Ext.form.TextArea({
                            name:'outReason',
                            width: 255,
                            height: 50,
                            maxLength: 100,
                            readOnly: true,
                            style: {
                                background: 'none repeat scroll 0 0 #DDDDDD'
                            },
                            validator: function(value){
                                if(!this.readOnly && "" === value.trim()) return "퇴출 사유를 입력해주세요.";
                                return true;
                            }
                        })
                    ]
            },{
                xtype: 'hidden',
                name: 'avatar'
            }],
            buttons: [{
                text: '저장',
                handler: function(){
                    Ext.getCmp('memberFormPanel').getForm().submit({url:'member/update', waitMsg:'사용자 정보를 저장하고 있습니다.', submitEmptyText: false})
                }
            },{
                text: '닫기',
                handler: function(){
                    this.close();
                }.createDelegate(this)
            }],
            listeners: {
                actioncomplete: function(sender, action){
                    $.growlUI('사용자 관리<br /> 사용자 정보를 저장했습니다.');
                },
                actionfailed: function(sender, action){
                    $.growlUI('사용자 관리<br /> 사용자 정보를 저장 중 오류가 발생했습니다.');
                }
            }
        });

        var config = {
            modal: true,
            width: 400,
            height: 360,
            layout: 'fit',
            padding: 5,
            items: [memberFormPanel],
            listeners: {
                show: function(sender){
                    var avatarUrl = memberFormPanel.getForm().findField('avatar').getRawValue();
                    var avatarHtml = '<img src="' + avatarUrl + '" width="80" height="80" border="0" />';
                    //
                    Ext.getCmp('divMemberAvatar').update(avatarHtml);
                }
            }
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        admin.member.MemberFormPanel.superclass.initComponent.call(this);
    },
    onRender: function() {
        admin.member.MemberFormPanel.superclass.onRender.apply(this, arguments);
    }
});