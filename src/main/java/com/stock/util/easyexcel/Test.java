package com.stock.util.easyexcel;

import lombok.AllArgsConstructor;

/**
 * @Description
 * @Author makuo
 * @Date 2023/9/7 17:42
 **/
@AllArgsConstructor
//@Component
public class Test {

   /* private final ISrCustomerIndustryService industryService;

//    @PostConstruct
    public void testReadExcel() {
        // 读取的excel文件路径
        String filename = "C:\\Users\\makuo\\Desktop\\所属行业分类.xlsx";
        List<SrCustomerIndustry> industries = new ArrayList<>();
        // 读取excel
        EasyExcel.read(filename, DataDemo.class, new AnalysisEventListener<DataDemo>() {

            Long one = 0l;
            Long two = 0l;
            Long three = 0l;
            Long four = 0l;
            // 每解析一行数据,该方法会被调用一次
            @Override
            public void invoke(DataDemo demoData, AnalysisContext analysisContext) {
                SrCustomerIndustry srCustomerIndustry = new SrCustomerIndustry();
                if (Func.isNotEmpty(demoData.getCategory())) {
                    if (Func.isNotEmpty(industries)) {
                        industryService.saveBatch(industries);
                        industries.clear();
                    }
                    one = IdWorker.getId();
                    srCustomerIndustry.setLevel(1);
                    srCustomerIndustry.setParentId(0L);
                    srCustomerIndustry.setId(one);
                }else if (Func.isNotEmpty(demoData.getBig())){
                    two = IdWorker.getId();
                    srCustomerIndustry.setLevel(2);
                    srCustomerIndustry.setParentId(one);
                    srCustomerIndustry.setId(two);
                }else if (Func.isNotEmpty(demoData.getMid())){
                    three = IdWorker.getId();
                    srCustomerIndustry.setLevel(3);
                    srCustomerIndustry.setParentId(two);
                    srCustomerIndustry.setId(three);
                }else if (Func.isNotEmpty(demoData.getSmall())){
                    four = IdWorker.getId();
                    srCustomerIndustry.setLevel(4);
                    srCustomerIndustry.setParentId(three);
                    srCustomerIndustry.setId(four);
                }
                srCustomerIndustry.setName(demoData.getName());
                srCustomerIndustry.setStatus(1);
                srCustomerIndustry.setIsDeleted(0);
                industries.add(srCustomerIndustry);
                System.out.println("解析数据为:" + demoData.toString());
            }

            // 全部解析完成被调用
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("解析完成...");
                // 可以将解析的数据保存到数据库
                if (Func.isNotEmpty(industries)){
                    industryService.saveBatch(industries);
                }
            }
        }).sheet().doRead();
    }
*/
    public static void main(String[] args) {
        //testReadExcel();

    }
}
