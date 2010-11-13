Ext.ns("admin.study");

admin.study.StudyPanel = Ext.extend(Ext.Panel, {
    // soft config (can be changed from outside)
    border: false
    ,limit: 19   
    ,initComponent: function() {
        var store = new Ext.data.JsonStore({
            //스토어 설정
            url: 'study',
            storedId: 'studyStore',
            restful: true,
            remoteSort: true,
            
            //리더 설정
            root: 'studys',
            idProperty: 'id',
            totalProperty: 'total',
            fields: ['studyName', 'meetingCount','memberCount', 'id', 'status',
                {name: 'startDay', convert: function(v) { return new Date(v)}},
                {name: 'endDay', convert: function(v) { return new Date(v)}}
            ]
        });

        var grid = {
            xtype: 'grid',
            layout: 'fit',
            border: false,
            
            ref: '../grid',
            store: store,
            columns: [
                {header: "스터디명", dataIndex: 'studyName', sortable: true},
                {header: "모임 수", dataIndex: 'meetingCount', sortable: true},
                {header: "참석자 수", dataIndex: 'memberCount', sortable: true},
                {header: "시작일", dataIndex: 'startDay', sortable: true, renderer: Ext.util.Format.dateRenderer('Y/m/d')},
                {header: "종료일", dataIndex: 'endDay', sortable: true, renderer: Ext.util.Format.dateRenderer('Y/m/d')},
                {header: "상태", dataIndex: 'status', sortable: true}
            ],
			sm: new Ext.grid.RowSelectionModel({singleSelect: true}),

            loadMask: { msg: 'Loading....' },
            
			viewConfig: {
				forceFit: true
			},

            bbar: new Ext.PagingToolbar({
                pageSize: this.limit,
                store: store,
                displayInfo: true,
                displayMsg: 'Displaying studies {0} - {1} of {2}'
            }),

            listeners: {
                rowdblclick: function(grid, rowIndex, e) {
                    var id = grid.store.getAt(rowIndex).get('id');
                    var url = "../study/view/" + id;
                    window.open(url);
                }
            }
        };
        
        // hard config (can't be changed form ousdie)
        var config = {
            layout: 'fit',
            items: grid
        };

        // apply config
        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        // Call Parent
        admin.study.StudyPanel.superclass.initComponent.call(this);

        // after parent code here, e.g. install event handlers        
    }

    ,onRender:function() {
        // before parent code

        // call parent
        admin.study.StudyPanel.superclass.onRender.apply(this, arguments);

        // after parent code, e.g. install event handlers on rendered components
        this.get(0).store.load({
            params : {
                start: 0,
                limit: this.limit
            }
        });
    }
});

// register xtype
Ext.reg('StudyPanel', admin.study.StudyPanel);
