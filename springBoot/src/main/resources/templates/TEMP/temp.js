let resulttable =Resources["InfoTableFunctions"].CreateInfoTableFromDataShape({
    infoTableName: "MAP_VENDOR_MATERIAL_Infotable" /* STRING */,
    dataShapeName: "MAP_VENDOR_MATERIAL_Shape" /* DATASHAPENAME */
});
let res = me.Import_Template_Check({
    Json: Json /* JSON */
});
let  phoneFormat =/^1[3|4|5|7|8]\d{9}$/;
if(res == 1){
    if(Json.array.length > 1){
        let data = JSON.parse(Json);
        for(let i = 1;i < data.array.length; i++){
            let ERROR_DESC = [];
            let newEntry = {
                VENDOR_ID:"",
                MATERIAL_NO:"",
                ERROR_STATUS:0,
                ERROR_DESC :""
            };
            // 值验证       不能为空
            if(data.array[i][2] == null || data.array[i][2].toString().trim() == ""){
                newEntry.ERROR_STATUS = 1;
                ERROR_DESC.push("物料号不能为空");
            }else{
                newEntry.MATERIAL_NO = data.array[i][2];
            }
            // 唯一性验证
            if(newEntry.VENDOR_CODE != null && newEntry.VENDOR_CODE != ""){

                let query = {
                    "filters":{
                        "type":"AND",
                        "filters":[
                            {
                                "type":"EQ",
                                "fieldName":"VENDOR_CODE",
                                "value":data.array[i][1]
                            },

                            {
                                "type":"EQ",
                                "fieldName":"MATERIAL_NO",
                                "value":data.array[i][2]
                            }
                        ]
                    }
                };
                let exit_table = me.SearchDataTableEntries({
                    maxItems: undefined /* NUMBER */,
                    searchExpression: undefined /* STRING */,
                    query: query /* QUERY */,
                });
                if(exit_table.length > 0 )
                {
                    newEntry.ERROR_STATUS = 1;
                    ERROR_DESC.push("该供应商代码已存在此物料号");
                }
            }

            // 其它验证
            // 表格编码重复验证
            if(newEntry.MATERIAL_NO != ""){
                // result: STRING
                for(let j = 1;j < data.array.length; j++){
                    if(i != j && data.array[j][2] == newEntry.MATERIAL_NO&&data.array[j][1] == newEntry.VENDOR_CODE){
                        newEntry.ERROR_STATUS = 1;
                        ERROR_DESC.push("导入文件中供应商代码存在重复");
                    }
                }
            }

            newEntry.VENDOR_ID = data.array[i][1];
            newEntry.ERROR_DESC = ERROR_DESC.join();
            resulttable.AddRow(newEntry);
        }
    }
}
result = resulttable;