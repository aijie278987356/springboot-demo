package org.aijie.helloworld.capital.service.impl;

import org.aijie.helloworld.capital.dao.CapitalDao;
import org.aijie.helloworld.capital.entity.Capital;
import org.aijie.helloworld.capital.service.CapitalService;
import org.aijie.util.IdCardService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (Capital)表服务实现类
 *
 * @author makejava
 * @since 2020-11-10 21:17:41
 */
@Service("capitalService")
public class CapitalServiceImpl implements CapitalService {
    @Resource
    private CapitalDao capitalDao;

    @Override
    public int addCapital(MultipartFile file) throws Exception {
        int result = 0;
//		存放excel表中所有
        List<Capital> capitalList = new ArrayList<>();
        /**
         *
         * 判断文件版本
         */
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream ins = file.getInputStream();
        Workbook wb = null;
        if (suffix.equals("xlsx")) {
            wb = new XSSFWorkbook(ins);
        } else {
            wb = new HSSFWorkbook(ins);
        }
        /**
         * 获取excel表单
         */
        Sheet sheet = wb.getSheetAt(0);

        List<String[]> list = new ArrayList<String[]>();
        if(null != sheet){
            for(int line = 1; line <= sheet.getLastRowNum();line++){
                Row row = sheet.getRow(line);
                if(null == row){
                    continue;
                }
                /**获得当前行的开始列*/
                int firstCellNum = row.getFirstCellNum();
                /**获得当前行的結束列数*/
                int lastCellNum = row.getLastCellNum();
                String[] cells = new String[lastCellNum];
                /**循环当前行*/
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    cells[cellNum] = getCellValue(cell);
                }
                list.add(cells);
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (String[] strs : list) {
            String id = strs[0];
            String contract = strs[1];
            String name = strs[2];
            String date = strs[3];
            String money = strs[4];
            String term = strs[5];
            String company = strs[6];
            String car = strs[7];

            Date dateStart = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dateStart);
            rightNow.add(Calendar.MONTH, Integer.parseInt(term.substring(0,2)));
            Date dt1 = rightNow.getTime();
            String dateEnd = sdf.format(dt1);
            String notes = null;
            if(StringUtils.isEmpty(company)){
                notes = "登记车辆为承租人（身份证号："+id+"）在"+date+"到"+dateEnd+"的期间内租赁凯京融资租赁（上海）有限公司实际所有的租赁车辆，约定登记挂靠在个人名下";
            }else{
                notes = "登记车辆为承租人（身份证号："+id+"）在"+date+"到"+dateEnd+"的期间内租赁凯京融资租赁（上海）有限公司实际所有的租赁车辆，约定登记挂靠在"+company+"名下";
            }
            Capital capital = new Capital();
            capital.setIdCard(id);
            capital.setContract(contract);
            capital.setName(name);
            capital.setDate(date);
            capital.setMoney(money);
            capital.setTerm(term);
            capital.setCompany(company);
            capital.setCar(car);
            capital.setNotes(notes);
            String nativePlace= IdCardService.getIdCardAtt(id);
            capital.setAddress(nativePlace);
            capital.setOperDate(new Date());
            capital.setOper("aijie");

            Calendar rightNow2 = Calendar.getInstance();
            rightNow2.setTime(new Date());
            rightNow2.add(Calendar.MONTH, Integer.parseInt(term.substring(0,2)));
            Date dt2 = rightNow2.getTime();
            String dueDate = sdf.format(dt2);

            capital.setDueDate(dueDate);
            StringBuilder code = new StringBuilder();
            code.append("$(\"#regtimelimit\").val(").append(term.substring(0,2)).append(");");
            code.append("$(\"#countRealexpiredateDiv\").text('").append(dueDate).append("');");
            code.append("$(\"#personfillarchiveno\").val(").append(contract).append(");$(\"#addMyselfBtn\").click();");
            code.append("$(\"#maincontractno\").val(").append(contract).append(");");
            code.append("$(\"#maincontractcurrency\").val('CNY');");
            code.append("$(\"#maincontractsum\").val(").append(money).append(");");
            code.append("$(\"#collateraldescribe\").val('").append(notes).append("');");
            code.append("$(\"#identificationcode\").val('").append(car).append("');$(\"#addccbmBtn\").click();");
            code.append("$(\"#leaseMode\").val('02');$(\"#leasedtype\").val('05');$(\"#leasedtype\").change();$(\"#leasedtype2\").val('0501');$(\"#addzlwBtn\").click();");
            code.append("$('#addOutPeople').modal('show');resetForm('addOutPeopleForm');$('#outModalTitle').html('添加');$('#debtortype').val('04');");
            code.append("showCRRInput('addOutPeople');$('#certificatetype').val('01');showCRRInputByCardType('addOutPeople');");
            code.append("$(\"#certificatecode\").val('").append(id).append("');$('#nationality').val('CHN');");
            code.append("changeCRRCountry('addOutPeople');$(\"#address\").val('").append(nativePlace).append("');");
            capital.setCode(code.toString());
            this.capitalDao.insert(capital);
        }
        return 0;
    }
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        DecimalFormat df = new DecimalFormat("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                String dataFormat = cell.getCellStyle().getDataFormatString();    // 单元格格式
                boolean isDate = DateUtil.isCellDateFormatted(cell);
                if ("General".equals(dataFormat)) {
                    cellValue = df.format(cell.getNumericCellValue());
                } else if (isDate) {
                    cellValue = sdf.format(cell.getDateCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param contract 主键
     * @return 实例对象
     */
    public Capital queryById(String contract) {
        return this.capitalDao.queryById(contract);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Capital> queryAllByLimit(int offset, int limit) {
        return this.capitalDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param capital 实例对象
     * @return 实例对象
     */
    @Override
    public Capital insert(Capital capital) {
        this.capitalDao.insert(capital);
        return capital;
    }

    /**
     * 修改数据
     *
     * @param capital 实例对象
     * @return 实例对象
     */
    @Override
    public Capital update(Capital capital) {
        this.capitalDao.update(capital);
        return this.queryById(capital.getContract());
    }

    /**
     * 通过主键删除数据
     *
     * @param contract 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer contract) {
        return this.capitalDao.deleteById(contract) > 0;
    }
}