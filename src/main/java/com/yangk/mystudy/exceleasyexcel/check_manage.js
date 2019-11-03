(function($, window) {
	var table = '';
	var app = {
		//初始化
		init:function(){
			this.cacheElements();//缓存dom节点
			this.bindEvents();//绑定事件
			this.dateTimeInit('#generateStartTime','#generateEndTime',true);//时间插件初始化
			this.dateTimeInit('#completeStartTime','#completeEndTime',false);//时间插件初始化
            this.dataTableFunc();//dataTable,表格初始化
            $('#clearanceChannel').select2({width:'100%'});
		},
		//缓存dom节点
		cacheElements:function(){
			this.$body = $('body');
		},
		//绑定事件
		bindEvents:function(){
			this.$body.on('click','.search_btn',this.searchFunction);//查询
            this.$body.on('click','.export_btn',this.exportFunction);//导出excel
		},
        //导出excel
        exportFunction:function(){
            // 获取输入框的值
            var fpxTrackingNo = $('#fpxTrackingNo').val();
            var clearanceChannel = $('#clearanceChannel').val();
            var endDeliveryNo = $('#endDeliveryNo').val();
            var status = $('#status').val();
            var generateStartTime = $('#generateStartTime').val();
            var generateEndTime = $('#generateEndTime').val();
            var completeStartTime = $('#completeStartTime').val();
            var completeEndTime = $('#completeEndTime').val();
            if($('#generateStartTime').val()){
                generateStartTime = Date.parse($('#generateStartTime').val());
            }
            if ($('#generateEndTime').val()){
                generateEndTime = Date.parse($('#generateEndTime').val());
            }
            if($('#completeStartTime').val()){
                completeStartTime = Date.parse($('#completeStartTime').val());
            }
            if ($('#completeEndTime').val()){
                completeEndTime = Date.parse($('#completeEndTime').val());
            }

            var searchCondition ={
                fpxTrackingNo:fpxTrackingNo,
                clearanceChannel:clearanceChannel,
                endDeliveryNo:endDeliveryNo,
                status:status,
                createTimeBegin:generateStartTime,
                createTimeEnd:generateEndTime,
                finishTimeBegin:completeStartTime,
                finishTimeEnd:completeEndTime,
                // 暂时先设置为100000
                pageSize:100000
            };
            // 发送请求
            var form = $('<form method="POST" action="' + CHECK.EXPORT_URL + '">');
            $.each(searchCondition, function(k, v) {
                form.append($('<input type="hidden" name="' + k +
                    '" value="' + v + '">'));
            });
            $('#exportBody').append(form);
            form.submit(); //自动提交

            /*$.ajax({
                type: "POST",
                url: CHECK.EXPORT_URL,
                data: searchCondition,
                success: function(response, status, request) {
                    var disp = request.getResponseHeader('Content-Disposition');
                    if (disp && disp.search('attachment') != -1) {  //判断是否为文件
                        var form = $('<form method="POST" action="' + CHECK.EXPORT_URL + '">');
                        $.each(searchCondition, function(k, v) {
                            form.append($('<input type="hidden" name="' + k +
                                '" value="' + v + '">'));
                        });
                        $('#exportBody').append(form);
                        form.submit(); //自动提交
                    }
                }
            });*/
        },
		//查询
		searchFunction:function(){
			table.draw();
			// 或者  table.ajax.reload();
		},
		//dataTable,表格初始化
		dataTableFunc:function(){
			dataTableObj={
                "bFilter": false,
                "processing": true,
                "bServerSide": true,
                "bSort": false,
                "bPaginate": true,
                "bRetrieve": true,
                "bLengthChange": true,
                "lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
                "iDisplayLength": 10,
		        "ajax": {
                    type: "POST",
                    url: CHECK.LIST_URL,
                    data: function (data) {
                        data.pageNo = (data.start / data.length) + 1;
                        data.pageSize = data.length;
                        data.fpxTrackingNo = trim($('#searchForm').find('#fpxTrackingNo').val());
                        data.clearanceChannel = $('#searchForm').find('#clearanceChannel').val();
                        data.endDeliveryNo = trim($('#searchForm').find('#endDeliveryNo').val());
                        data.status = $('#searchForm').find('#status').val();
                        data.createTimeBegin = $('#searchForm').find('#generateStartTime').attr("data-day");
                        data.createTimeEnd = $('#searchForm').find('#generateEndTime').attr("data-day");
                        data.finishTimeBegin  = $('#searchForm').find('#completeStartTime').attr("data-day");
                        data.finishTimeEnd = $('#searchForm').find('#completeEndTime').attr("data-day");
                    }
                },
		        "columns":[]
		    };

		    
		    dataTableObj.columns=[
		    	{data: 'warehouseCode',title:'仓库',},
		    	// {data: 'destination',title:'目的地',},
		    	{data: 'fpxTrackingNo',title:'4px跟踪号',},
		    	{data: 'endDeliveryNo',title:'末端派送单号',},
		    	{data: 'productCode',title:'产品',},
		    	{data: 'clearanceChannel',title:'清关渠道',},
		    	{data: 'instockParcelType',title:'库内包裹类型',},
		    	{data: 'customerCode',title:'客户编码',},	
		    	{data: 'status',title:'作业单状态',
                    render: function (data) {
                        if (data) {
                            if (typeof(globalMsg['status.' + data]) === 'undefined') {
                                return data;
                            }
                            return globalMsg['status.' + data];
                        }
                        return '';
                    }},
			   	{data: 'createTime',title:'生成时间',
                    render: function (data) {
                        return data ? timeTransform(data) : '';
                    }},
			   	{data: 'finishTime',title:'完成时间',
                    render: function (data) {
                        return data ? timeTransform(data) : '';
                    }},
                {data: 'modifyId',title:'操作人员',}
		    ];
		    table=$('#table').DataTable(dataTableObj);
		    $('#table td').css('vertical-align', 'middle');
		},
		//时间插件初始化
		dateTimeInit:function(startEle,endEle,isinitVal){
			var start = {
	           // formatType:"date",
	            skinCell:"uedblue",                    //插件风格
	            isTime:true,                                //是否开启时间选择
	            isinitVal:isinitVal,
	            initDate:[{DD:-7},true],        //初始化时间
	            maxDate: $.nowDate({DD:0}), //最大日期
	            okfun: function(obj){
	                if(obj.val.split('/')[0].length != 4){
	                    //英文
	                    var arr = obj.val.split(' ')[0].split('/');
	                    end.minDate = arr[2]+'-'+arr[1]+'-'+arr[0]+' '+ obj.val.split(' ')[1];
	                }else {
	                    end.minDate = obj.val.replace(/\//g,'-'); //开始日选好后，重置结束日的最小日期
	                };
	               // end.trigger = false;
	                $(endEle).jeDate(end);
	            }
	        };
	        var end = {
	           // formatType:"date",
	            skinCell:"uedblue",                    //插件风格
	            isTime:true,                                //是否开启时间选择
	            isinitVal:isinitVal,                        //是否初始化时间，默认不初始化时间
	            // initDate:[{DD:"0"},false],        //初始化时间
	            minDate:  $.nowDate({DD:'-7'}), //设定最小日期
	            maxDate: '2099-06-16 23:59:59', //最大日期
	            okfun: function(obj){
	                if(obj.val.split('/')[0].length != 4){
	                    //英文
	                    var arr = obj.val.split(' ')[0].split('/');
	                    start.maxDate = arr[2]+'-'+arr[1]+'-'+arr[0]+' '+ obj.val.split(' ')[1];
	                }else {
	                    start.maxDate = obj.val.replace(/\//g,'-'); //开始日选好后，重置结束日的最小日期
	                };
	            }
	        };
	        $.jeDate(startEle,start);
	        $.jeDate(endEle,end);
		}
	};
	app.init();//初始化
})(jQuery, window);