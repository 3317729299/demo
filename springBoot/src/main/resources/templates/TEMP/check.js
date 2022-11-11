result = 0;
let CurrentUser = Resources["CurrentSessionInfo"].GetCurrentUser();
let date  = new Date();
// 排序条件
function getID(){
    let sort = {
        name: "ROOM_ID",
        ascending: false
    };
    //获取OD_VENDOR_AUDIT表最大的ID
    let table =  me.SearchDataTableEntries();
    table.Sort(sort);
    let ID = 1;
    if(table.length > 0){
        ID = parseInt(table[0].ROOM_ID)+1;
    }
    return ID;
}

for(let i = 0; i < info.length ; i++){
    if(info[i].ERROR_STATUS == 0){
        // Values: INFOTABLE dataShape: ""
        // 删除临时表数据
        let tempQuery = {
            "filters":{
                "type":"EQ",
                "fieldName": "ROOM_CODE",
                "value": info[i].ROOM_CODE,
            }
        };
        Things["TEMP_MD_HVAC_ALARM"].DeleteDataTableEntriesWithQuery({
            query: tempQuery /* QUERY */,
        });
        //查询是否有正式表中是否存在ROOM_CODE
        let query = {
            "filters":{
                "type":"EQ",
                "fieldName": "ROOM_CODE",
                "value": info[i].ROOM_CODE
            }
        };
        let table = me.SearchDataTableEntries({
            query: query /* QUERY */,
        });
        let Values = me.CreateValues();
        if(table.length > 0){
            Values.ROOM_ID = table[0].ROOM_ID
            Values.LAST_MODIFY_USER = CurrentUser
            Values.LAST_MODIFT_TIME = date
        }else{
            Values.ROOM_ID = getID();
            Values.CREATE_USER = CurrentUser;
            Values.CREATE_TIME = date;
        }
        Values.ROOM_CODE = info[i].ROOM_CODE
        Values.ROOM_NAME = info[i].ROOM_NAME
        Values.HVAC_ID = info[i].HVAC_ID
        Values.WORKSHOP_NAME = info[i].WORKSHOP_NAME
        Values.PRODUCT_LINE_CODE = info[i].PRODUCT_LINE_CODE

        // id: STRING
        let id = me.AddOrUpdateDataTableEntry({
            values: Values /* INFOTABLE */,
        });
        result += 1;
    }
}