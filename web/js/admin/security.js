Ext.ns("admin.security");
admin.security.RoleMgt = function() {
    var proxy = new Ext.data.HttpProxy({
        api: {
            read    : 'roles/list',
            create : {url:'roles/add',    method:"POST"},
            update : {url:'roles/update', method:"POST"},
            destroy: 'roles/delete'
        }
    });
    var RoleRecord = Ext.data.Record.create([
        {
            name: 'name',
            type: 'string'
        },
        {
            name: 'descr',
            type: 'string'
        },
        {
            name: 'id',
            type: 'number'
        }
    ]);
    var roleWriter = new Ext.data.JsonWriter({
    });
    var store = new Ext.data.JsonStore({
        //스토어 설정
        proxy : proxy,
        storedId: 'roleStore',
        writer: roleWriter,
        //리더 설정
        root: 'roles',
        idProperty: 'id',
        fields: RoleRecord,
        autoSave : false,
        listeners: {
            write: function(store, action, result, res, rec) {
                if(res.success){
                    $.growlUI('보완 관리<br /> 롤 정보를 수정하였습니다.');
                }else{
                    $.growlUI('보완 관리<br /> 롤 정보를 수정에 실패했습니다.');
                }
            }
        }
    });
    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Save',
        listeners: {
            afteredit:function(row, changes, r, rowIndex){
                row.grid.store.save();
            }
        }

    });
    var grid = {
        xtype:'grid',
        store: store,
        layout:'fit',
        region:'center',
        //        margins: '0 5 5 5',
        autoExpandColumn: 'name',
        plugins: [editor],
        viewConfig: {
            forceFit: true
        },
        columns: [
            {
                header: "롤명",
                dataIndex: 'name',
                sortable: true,
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            },
            {
                header: "롤설명",
                dataIndex: 'descr',
                sortable: true,
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            },
        ],
        sm: new Ext.grid.RowSelectionModel({singleSelect: true}),
        tbar: [
            {
                ref: '../addBtn',
                iconCls: 'icon-add',
                text: 'Add Role',
                handler: function(btn, e) {
                    var roleGrid = btn.refOwner;
                    var role = new RoleRecord({
                        name: '',
                        descr: ''
                    });
                    editor.stopEditing();
                    store.insert(0, role);
                    roleGrid.getView().refresh();
                    roleGrid.getSelectionModel().selectRow(0);
                    editor.startEditing(0);
                }
            },
            {
                ref: '../removeBtn',
                iconCls: 'icon-del',
                text: 'Remove Role',
                disabled: true,
                handler: function() {
                    editor.stopEditing();
                    var s = grid.getSelectionModel().getSelections();
                    for (var i = 0, r; r = s[i]; i++) {
                        store.remove(r);
                    }
                }
            },
            {
                ref: '../refreshBtn',
                iconCls: 'icon-delete',
                text: 'Refresh Role',
                disabled: false,
                handler: function() {
                    store.load();
                }
            }
        ]
    };
    return grid;
}();

admin.security.SecurityPanel = Ext.extend(Ext.Panel, {
    // soft config (can be changed from outside)
    border: false

    ,initComponent: function() {
        // hard config (can't be changed form ousdie)
        var config = {
            id: 'SecurityPanel',
            layout: 'table',
            baseCls:'x-plain',
            layoutConfig: {columns:2},
            width: 964,
            height: 574,
            defaults: {
                layout: 'fit',
                width:477,
                height:281
            },
            items:[
                {
                    title:'롤관리',
                    items:admin.security.RoleMgt
                },
                {
                    title:'권한관리'
                },
                {
                    title:'롤_권한_맵핑',
                    width: 964,
                    colspan:2
                }
            ]
        };

        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        // Call Parent
        admin.security.SecurityPanel.superclass.initComponent.call(this);

        // after parent code here, e.g. install event handlers
    }

    ,onRender:function() {
        // before parent code

        // call parent
        admin.security.SecurityPanel.superclass.onRender.apply(this, arguments);
        admin.security.RoleMgt.store.load();
        // after parent code, e.g. install event handlers on rendered components
    }
});
// register xtype
Ext.reg('SecurityPanel', admin.security.SecurityPanel);