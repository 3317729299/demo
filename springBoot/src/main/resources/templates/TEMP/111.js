let params = {
    infoTableName: "MD_PRODUCT_LINE_Infotable",
    dataShapeName: "MD_PRODUCT_LINE_Shape"
};
let paginatedResult = Resources["InfoTableFunctions"].CreateInfoTableFromDataShape(params);
let res = me.import_Template_Check({
    json: json /* JSON */
});

function isNull(str) {
    if (str == null || str.toString().trim() == "") {

        return true;
    } else {
        return false;
    }

}

//let  dateFormat =/^(\d{4})-(\d{2})-(\d{2})$/;
if (res == 1) {
    if (json.array.length > 1) {
        let data = JSON.parse(json);
        for (let i = 1; i < data.array.length; i++) {
            let ERROR_DESC = [];
            let newEntry = {
                PRODUCT_LINE_CODE: "",
                PRODUCT_LINE_NAME: "",
                PROD_LINE_FUNC_LOC: "",
                PRODUCT_LINE_TYPE: "",
                ERROR_STATUS: 0,
                ERROR_DESC: "",
            };
            // 空值验证
            if (isNull(data.array[i][1])) {
                newEntry.ERROR_STATUS = 1;
                ERROR_DESC.push("生产线编号不能为空");
            } else {
                newEntry.PRODUCT_LINE_CODE = data.array[i][1];
            }

            if (isNull(data.array[i][2])) {
                newEntry.ERROR_STATUS = 1;
                ERROR_DESC.push("生产线名称不能为空");
            } else {
                newEntry.PRODUCT_LINE_NAME = data.array[i][2]
            }

            if (isNull(data.array[i][3])) {
                newEntry.ERROR_STATUS = 1;
                ERROR_DESC.push("产线AKZ编码编码不能为空");
            } else {
                newEntry.PROD_LINE_FUNC_LOC = data.array[i][3]
            }

            if (isNull(data.array[i][4])) {
                newEntry.ERROR_STATUS = 1;
                ERROR_DESC.push("生产线类型不能为空");
            } else {

                //根据 code 取中文描述
                // result: STRING
                let code = Things["BAYER_DATA_DICT"].GET_CODE({
                    CODE_NAME: data.array[i][4] /* STRING */,
                    TYPE: "PRODUCT_LINE_TYPE" /* STRING */
                });

                newEntry.PRODUCT_LINE_TYPE =code;
                newEntry.PRODUCT_LINE_TYPE_DESC = data.array[i][4];

                logger.info(" PRODUCT_LINE_TYPE 的值data.array[i][4]=" + newEntry.PRODUCT_LINE_TYPE);
            }

            logger.info(" PRODUCT_LINE_CODE=" + newEntry.PRODUCT_LINE_CODE);
            //唯一性验证
            if (newEntry.PRODUCT_LINE_CODE != null && newEntry.PRODUCT_LINE_CODE != "") {
                let query = {
                    "filters": {
                        "type": "EQ",
                        "fieldName": "PRODUCT_LINE_CODE",
                        "value": newEntry.PRODUCT_LINE_CODE,
                    }
                };

                let exit_table = me.SearchDataTableEntries({
                    maxItems: undefined/*NUMBER*/,
                    searchExpression: undefined/*STRING*/,
                    query: query/*QUERY*/,
                });
                logger.info(" exit_table=" + exit_table.length );
                if (exit_table.length > 0) {
                    newEntry.ERROR_STATUS = 1;
                    ERROR_DESC.push("生产线编号已存在");
                    logger.info(" ERROR_DESC=" + ERROR_DESC );

                }
            }
            // 其它验证

            // 表格编码重复验证
            if (newEntry.PRODUCT_LINE_CODE != "") {
                // result: STRING
                for (let j = 1; j < data.array.length; j++) {
                    if (i != j && data.array[j][1] == newEntry.PRODUCT_LINE_CODE) {
                        newEntry.ERROR_STATUS = 1;
                        ERROR_DESC.push("导入文件中生产线编号存在重复");
                    }
                }
            }


            newEntry.ERROR_DESC = ERROR_DESC.join();
            paginatedResult.AddRow(newEntry);

        }
    }
}
result = paginatedResult;