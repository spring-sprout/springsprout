Ext.ns("admin.seminar");

admin.seminar.SeminarPanel = Ext.extend(Ext.Panel, {
    // soft config (can be changed from outside)
    border: false

    ,initComponent: function() {
        // hard config (can't be changed form ousdie)
        var config = {
            html : '세미나'
        };

        // apply config
        Ext.apply(this, config);
        Ext.apply(this.initialConfig, config);

        // Call Parent
        admin.seminar.SeminarPanel.superclass.initComponent.call(this);

        // after parent code here, e.g. install event handlers
    }

    ,onRender:function() {
        // before parent code

        // call parent
        admin.seminar.SeminarPanel.superclass.onRender.apply(this, arguments);

        // after parent code, e.g. install event handlers on rendered components
    }
});

// register xtype
Ext.reg('SeminarPanel', admin.seminar.SeminarPanel);
