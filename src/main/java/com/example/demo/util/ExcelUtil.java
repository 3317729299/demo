package com.example.demo.util;

import com.example.demo.mybatisplus.annotation.ExportConfig;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Excel导入导出工具类
 * 实现功能说明:
 * 导出时传入list<T>,即可实现导出为一个excel,其中每个对象T为Excel中的一条记录.
 * 导入时读取excel,得到的结果是一个list<T>.T是自己定义的对象.
 * 需要导出的实体对象只需简单配置注解就能实现灵活导出,通过注解您可以方便实现下面功能:
 * 1.实体属性配置了注解就能导出到excel中,每个属性都对应一列.
 * 2.列名称可以通过注解配置.
 * 3.导出到哪一列可以通过注解配置.
 * 4.鼠标移动到该列时提示信息可以通过注解配置.
 * 5.用注解设置只能下拉选择不能随意填写功能.
 * 6.用注解设置是否只导出标题而不导出内容,这在导出内容作为模板以供用户填写时比较实用.
 *
 * @author chenj.
 * @date 2017/11/30.
 */
public class ExcelUtil<T> {


    private static final String XLS_SUFFIX = ".xls";
    private static final String XLS_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // vo导入导出实体class
    private Class<T> clazz = null;

    private HttpServletResponse response = null;
    private InputStream input = null;
    // 是否只导出模板(默认 否)
    private boolean isTemplate = false;

    /**
     * 主要用于创建Excel导出实例
     */
    public ExcelUtil(Class<T> clazz, HttpServletResponse response) {
        this.clazz = clazz;
        this.response = response;
    }

    /**
     * 主要用于创建Excel导出实例
     */
    public ExcelUtil(Class<T> clazz, HttpServletResponse response, boolean isTemplate) {
        this.clazz = clazz;
        this.response = response;
        this.isTemplate = isTemplate;
    }

    /**
     * 主要用于创建Excel导入实例
     */
    public ExcelUtil(Class<T> clazz, InputStream input) {
        this.clazz = clazz;
        this.input = input;
    }

    /**
     * 导入Excel
     *
     * @param sheetName 指定导入Excel文件的sheet名称
     * @return 导入Excel数据集
     */
    public List<T> importExcel(String sheetName) {
        requiredImportParams();  // 参数空验证
        List<T> list = new ArrayList<T>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(input);
            HSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheetName != null && !sheetName.trim().equals("")) {
                sheet = workbook.getSheet(sheetName);// 如果指定sheet名,则取指定sheet中的内容.
            }
            if (sheet == null) {
                sheet = workbook.getSheetAt(0); // 如果传入的sheet名不存在则默认指向第1个sheet.
            }
            int rows = sheet.getPhysicalNumberOfRows();

            if (rows > 0) {// 有数据时才处理
                Field[] allFields = clazz.getDeclaredFields();// 得到类的所有field.
                Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();// 定义一个map用于存放列的序号和field.
                for (Field field : allFields) {
                    // 将有注解的field存放到map中.
                    if (field.isAnnotationPresent(ExportConfig.class)) {
                        ExportConfig config = field.getAnnotation(ExportConfig.class);
                        int col = getExcelCol(config.column());// 获得列号
                        // System.out.println(col + "====" + field.getName());
                        field.setAccessible(true);// 设置类的私有字段属性可访问.
                        fieldsMap.put(col, field);
                    }
                }
                for (int i = 1; i < rows; i++) {// 从第2行开始取数据,默认第一行是表头.
                    HSSFRow row = sheet.getRow(i);
                    int cellNum = row.getPhysicalNumberOfCells();
                    T entity = null;
                    for (int j = 0; j < cellNum; j++) {
                        HSSFCell cell = row.getCell(j);
                        if (cell == null) {
                            continue;
                        }
                        String c = getStringValue(cell);
                        // System.out.println(c);
                        if (c.equals("")) {
                            continue;
                        }
                        entity = (entity == null ? clazz.newInstance() : entity);// 如果不存在实例则新建.
                        // System.out.println(cells[j].getContents());
                        Field field = fieldsMap.get(j);// 从map中得到对应列的field.
                        // 取得类型,并根据对象类型设置值.
                        Class<?> fieldType = field.getType();
                        if (String.class == fieldType) {
                            field.set(entity, String.valueOf(c));
                        } else if ((Integer.TYPE == fieldType)
                                || (Integer.class == fieldType)) {
                            field.set(entity, Integer.parseInt(c));
                        } else if ((Long.TYPE == fieldType)
                                || (Long.class == fieldType)) {
                            field.set(entity, Long.valueOf(c));
                        } else if ((Float.TYPE == fieldType)
                                || (Float.class == fieldType)) {
                            field.set(entity, Float.valueOf(c));
                        } else if ((Short.TYPE == fieldType)
                                || (Short.class == fieldType)) {
                            field.set(entity, Short.valueOf(c));
                        } else if ((Double.TYPE == fieldType)
                                || (Double.class == fieldType)) {
                            field.set(entity, Double.valueOf(c));
                        } else if (Character.TYPE == fieldType) {
                            if ((c != null) && (c.length() > 0)) {
                                field.set(entity, Character.valueOf(c.charAt(0)));
                            }
                        }

                    }
                    if (entity != null) {
                        list.add(entity);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单
     *
     * @param sheetName 工作表的名称
     * @param sheetSize 每个sheet中数据的行数,此数值必须小于65536
     */
    public boolean exportExcel(List<T> list, String sheetName, int sheetSize) {
        try {
            requiredExportParams();  // 参数空验证
            OutputStream output = response.getOutputStream();
            Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
            List<Field> fields = new ArrayList<Field>();
            int resourceSize = 1;
            // 得到所有field并存放到一个list中.
            for (Field field : allFields) {
                if (field.isAnnotationPresent(ExportConfig.class)) {
                    fields.add(field);
                }
            }

            // 数据源是否为空
            if (list != null && list.size() > 0) {
                resourceSize = list.size();
            }

            HSSFWorkbook workbook = new HSSFWorkbook();// 产生工作薄对象

            // excel2003中每个sheet中最多有65536行,为避免产生错误所以加这个逻辑.
            if (sheetSize > 65536 || sheetSize < 1) {
                sheetSize = 65536;
            }
            double sheetNo = Math.ceil(resourceSize / sheetSize);// 取出一共有多少个sheet.
            for (int index = 0; index <= sheetNo; index++) {
                HSSFSheet sheet = workbook.createSheet();// 产生工作表对象
                if (sheetNo == 0) {
                    workbook.setSheetName(index, sheetName);
                } else {
                    workbook.setSheetName(index, sheetName + index);// 设置工作表的名称.
                }
                HSSFRow row;
                HSSFCell cell;// 产生单元格

                row = sheet.createRow(0);// 产生一行
                // 写入各个字段的列头名称
                for (int i = 0, len = fields.size(); i < len; i++) {
                    Field field = fields.get(i);
                    ExportConfig config = field.getAnnotation(ExportConfig.class);
                    // int col = getExcelCol(config.column());// 获得列号
                    // cell = row.createCell(col);// 创建列

                    ///////////////
                    int col = i;
                    cell = row.createCell(i);// 创建列
                    /////////

                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置列中写入内容为String类型
                    // 设置表头名称
                    String thName = config.name();
                    if ("field".equals(thName)) {// 默认为字段名称
                        thName = field.getName();
                    }
                    cell.setCellValue(thName);// 写入列名
                    // 设置头部样式
                    CellStyle headStyle = headCellStyle(workbook, config.size());
                    if (headStyle != null) {
                        cell.setCellStyle(headStyle);
                    }

                    // 如果设置了提示信息则鼠标放上去提示.
                    if (!config.prompt().trim().equals("")) {
                        setHSSFPrompt(sheet, "", config.prompt(), 1, 65535, col, col);// 这里默认设了2-65535列提示.
                    }
                    // 如果设置了combo属性则本列只能选择不能输入
                    if (config.combo().length > 0) {
                        setHSSFValidation(sheet, config.combo(), 1, 65535, col, col);// 这里默认设了2-65535列只能选择不能输入.
                    }

                    // sheet.setColumnHidden(0,true);//设置第几列隐藏
                }
                if (list != null && list.size() > 0) {
                    // 重置每列样式
                    List<CellStyle> styleList = new ArrayList<>();
                    for (int i = 0, len = fields.size(); i < len; i++) {
                        Field field = fields.get(i);// 获得field.
                        field.setAccessible(true);// 设置实体类私有属性可访问
                        ExportConfig config = field.getAnnotation(ExportConfig.class);
                        // 设置主体样式
                        CellStyle contentStyle = workbook.createCellStyle();
                        contentStyle.setAlignment(config.align());
                        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                        contentStyle.setFillBackgroundColor(config.bgColor());
                        Font font = workbook.createFont();
                        font.setFontHeightInPoints(config.size());// 字体大小
                        font.setColor(config.color());// 字体颜色
                        contentStyle.setFont(font);
                        styleList.add(contentStyle);
                    }

                    int startNo = index * sheetSize;
                    int endNo = Math.min(startNo + sheetSize, resourceSize);
                    // 写入各条记录,每条记录对应excel表中的一行
                    for (int i = startNo; i < endNo; i++) {
                        row = sheet.createRow(i + 1 - startNo);
                        T vo = (T) list.get(i); // 得到导出对象.
                        for (int j = 0, len = fields.size(); j < len; j++) {

                            Field field = fields.get(j);// 获得field.
                            field.setAccessible(true);// 设置实体类私有属性可访问
                            ExportConfig config = field.getAnnotation(ExportConfig.class);

                            // 单元格填充值
                            String colValue = field.get(vo) == null ? "" : String.valueOf(field.get(vo));

                            // 是否需要将单元格的值替换为当前配置的值
                            if (!"".equals(config.replace())) {
                                colValue = config.replace();
                            }

                            // 设置单元格宽度
                            setColumnWidth(sheet, j, config.width(), colValue);

                            // 根据ExportConfig中设置情况决定是否导出,有些情况需要保持为空,希望用户填写这一列.
                            // 当isTemplate为true时只导出Excel模板，不用导出数据
                            if (config.isExport() && !isTemplate) {
                                cell = row.createCell(getExcelCol(config.column()));// 获取cell
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                cell.setCellValue(colValue);//如果数据存在就填入,不存在填入空格.
                                if (styleList != null && styleList.size() > 0) {
                                    cell.setCellStyle(styleList.get(j));
                                }
                            }
                        }
                    }
                }

            }

            writeByLocalOrBrowser(response, sheetName, workbook, output, isTemplate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static CellStyle getCellStyle(HSSFWorkbook workbook, CellStyle cellStyle) {
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
        }
        return cellStyle;
    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col
     */
    public static int getExcelCol(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0, len = cs.length; i < len; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,
                                          String promptContent, int firstRow, int endRow, int firstCol,
                                          int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint
                .createCustomFormulaConstraint("DD1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(
                regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textList 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textList, int firstRow, int endRow, int firstCol,
                                              int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textList);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }

    /**
     * 得到实体类所有通过注解映射了数据表的字段
     *
     * @param clazz
     * @param fields
     * @return
     */
    private List<Field> getMappedFiled(Class clazz, List<Field> fields) {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }

        Field[] allFields = clazz.getDeclaredFields();// 得到所有定义字段
        // 得到所有field并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(ExportConfig.class)) {
                fields.add(field);
            }
        }
        if (clazz.getSuperclass() != null
                && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), fields);
        }

        return fields;
    }

    /**
     * 获得字符类型数据
     *
     * @param cell
     * @return
     */
    public static String getStringValue(Cell cell) {
        String value = "";
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            value = cell.getStringCellValue().trim();
        }
        return value;
    }

    /**
     * 获得日期类型数据
     *
     * @param cell
     * @return
     */
    public static Date getDateValue(Cell cell) {
        Date value = null;
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            value = cell.getDateCellValue();
        }
        return value;
    }

    /**
     * 获得浮点型数据
     *
     * @param cell
     * @return
     */
    public static Float getFloatValue(Cell cell) {
        Float value = 0f;
        if (cell != null) {
            Double doubleValue = cell.getNumericCellValue();
            if (doubleValue != null) {
                value = Float.parseFloat(doubleValue.toString());
            }
        }
        return value;
    }

    /**
     * 获得double浮点型数据
     *
     * @param cell
     * @return
     */
    public static Double getDoubleValue(Cell cell) {
        Double value = 0.0;
        if (cell != null) {
            value = cell.getNumericCellValue();
        }
        return value;
    }

    /**
     * 写入
     *
     * @param response
     * @param fileName
     * @param wb
     * @param out
     * @throws Exception
     */
    public static void writeByLocalOrBrowser(HttpServletResponse response, String fileName, HSSFWorkbook wb,
                                             OutputStream out, boolean isTemplate) throws Exception {
        if (response != null) {
            if (isTemplate) {
                fileName = fileName + "导入模板";
            } else {
                fileName = fileName + "_" + System.currentTimeMillis();
            }
            // response对象不为空,响应到浏览器下载
            response.setContentType(XLS_CONTENT_TYPE);
            response.setHeader("Content-disposition", "attachment; filename=" +
                    URLEncoder.encode(String.format("%s%s", fileName,
                            XLS_SUFFIX), "UTF-8"));
            if (out == null) {
                out = response.getOutputStream();
            }
        }
        wb.write(out);
        out.flush();
        out.close();
    }

    /**
     * 设置头部样式
     *
     * @param wb
     * @param fontSize 正文单元格字体大小
     * @return
     */
    public static CellStyle headCellStyle(HSSFWorkbook wb, short fontSize) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);// 前景颜色
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);// 填充模式
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框为细边框
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框为细边框
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框为细边框
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框为细边框
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
        cellStyle.setWrapText(true);// 自动换行
        Font font = wb.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 加粗
        font.setFontHeightInPoints((short) (fontSize + 1));// 字体大小
        font.setColor(HSSFColor.WHITE.index);// 字体颜色
        // 应用标题字体到标题样式
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 设定单元格宽度 (手动/自动)
     *
     * @param sheet 工作薄对象
     * @param index 单元格索引
     * @param width 指定宽度,-1为自适应
     * @param value 自适应需要单元格内容进行计算
     */
    public static void setColumnWidth(HSSFSheet sheet, int index, short width, String value) {
        if (width == -1 && value != null && !"".equals(value)) {
            if (checkNumLetter(value)) {
                width = (short) (value.length() * 512 * 2 / 3);
            } else {
                width = (short) (value.length() * 512);
            }

        } else {
            width = width == -1 ? 200 : width;
            width = (short) (width * 25.7);
        }
        if (width < 5 * 512) {
            width = 5 * 512;
        } else if (width > 20 * 512) {
            width = 20 * 512;
        }
        // 获取该行的宽度
        int _width = sheet.getColumnWidth(index);
        sheet.setColumnWidth(index, width > _width ? width : _width);
    }

    private void requiredExportParams() {
        if (clazz == null || response == null) {
            throw new IllegalArgumentException(
                    "请先使用new ExcelUtil(Class<T> clazz,HttpServletResponse response)构造器初始化参数。");
        }

    }

    private void requiredImportParams() {
        if (clazz == null || input == null) {
            throw new IllegalArgumentException(
                    "请先使用new ExcelUtil(Class<T> clazz,InputStream input)构造器初始化参数。");
        }

    }

    /**
     * 验证字符是否由数字或字母组成
     *
     * @param str
     * @return
     */
    public static boolean checkNumLetter(String str) {
        String regEx = "^[0-9A-Za-z]+$";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(str).matches();
    }
}
