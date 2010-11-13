Ext.onReady(function() {
    Ext.BLANK_IMAGE_URL =  '../ext/resources/images/default/s.gif';
    
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    var layout = new Ext.TabPanel({
        renderTo: 'content',
        activeTab: 0,
        height: 600,
        plain:true,

        stateId: 'layout',
        stateEvents:['tabchange'],
        getState:function() {
            return {
                activeTab:this.items.indexOf(this.getActiveTab())
            };
        },

        defaults: {
          layout: 'fit',
          border:false
        },
        
        items: [{
            title: '사용자 관리',
            items: {xtype:'MemberPanel'}
        },{
            title: '보안 관리',
            items: {xtype:'SecurityPanel'}
        },{
            title: '스터디 관리',
            items: {xtype:'StudyPanel'}
        },{
            title: '세미나 관리',
            items: {xtype:'SeminarPanel'}
        },{
            title: '공지 사항 관리',
            items: {xtype:'NoticePanel'}
        }]
    });
});
