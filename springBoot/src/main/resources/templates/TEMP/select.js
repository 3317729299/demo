let result = 0;
let CurrentUser = Resources["CurrentSessionInfo"].GetCurrentUser();
let date  = new Date();
for(let i = 0; i < row.length ; i++){
    if(row[i].ERROR_STATUS == 0){
        // 查询 正式表 有就新增 赋值id 没有就修改
        let query = {
            "filters":{
                "type":"AND",
                "filters":[
                    {
                        "type":"EQ",
                        "fieldName":"VENDOR_CODE",
                        "value":row.VENDOR_CODE
                    },
                    {
                        "type":"EQ",
                        "fieldName":"MATERIAL_NO",
                        "value":row.MATERIAL_NO
                    }
                ]
            }
        };


        Things["TEMP_MAP_VENDOR_MATERIAL"].DeleteDataTableEntriesWithQuery({
            query: query /* QUERY */,
        });
        let table = me.SearchDataTableEntries({
            query: query /* QUERY */,
        });
        let Values = me.CreateValues();
        if(table.length > 0){
            Values.MAP_ID = table[0].MAP_ID;
            Values.LAST_MODIFY_USER = CurrentUser;
            Values.LAST_MODIFY_TIME = date;
        }else{
            Values.MAP_ID =me.GET_MAX_ID();
            Values.CREATE_USER = CurrentUser;
            Values.CREATE_TIME = date;
        }
        Values.VENDOR_CODE = row[i].VENDOR_CODE;
        Values.VENDOR_ID = row[i].VENDOR_ID;
        Values.MATERIAL_NO = row[i].MATERIAL_NO;


        Values.SUBMITTER = CurrentUser;
        Values.SUBMIT_TIME = date;
        let id = me.AddOrUpdateDataTableEntry({
            values: Values /* INFOTABLE */,
        });
        result += 1;
    }
}
