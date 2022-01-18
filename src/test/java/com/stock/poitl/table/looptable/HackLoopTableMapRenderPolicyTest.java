package com.stock.poitl.table.looptable;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

@DisplayName("Example for HackLoop Table")
public class HackLoopTableMapRenderPolicyTest {

    String resource = "D:\\poi\\looptable.docx";
    Map<String,Object> data = new HashMap<>();

    @BeforeEach
    public void init() {
        List<Goods> goods = new ArrayList<>();
        Goods good = new Goods();
        good.setCount(4);
        good.setName("墙纸");
        good.setDesc("书房卧室");
        good.setDiscount(1500);
        good.setPrice(400);
        good.setTax(new Random().nextInt(10) + 20);
        good.setTotalPrice(1600);
        good.setPicture(Pictures.ofLocal("src/test/resources/earth.png").size(24, 24).create());
        goods.add(good);
        goods.add(good);
        goods.add(good);
        data.put("goods", goods);

        List<Map> labors = new ArrayList<>();
        Map<String, Object> labor = new HashMap<>();
        labor.put("category", "油漆工");
        labor.put("people", 2);
        labor.put("price", 400);
        labor.put("totalPrice", 1600);
        labors.add(labor);
        labors.add(labor);
        labors.add(labor);
        data.put("labors", labors);

        data.put("total", 1024);

        // same line
        data.put("goods2", goods);
        data.put("labors2", labors);

    }

    @Test
    public void testPaymentHackExample() throws Exception {
        HackLoopTableRenderPolicy hackLoopTableRenderPolicy = new HackLoopTableRenderPolicy();
        HackLoopTableRenderPolicy hackLoopSameLineTableRenderPolicy = new HackLoopTableRenderPolicy(true);
        Configure config = Configure.builder().bind("goods", hackLoopTableRenderPolicy)
                .bind("labors", hackLoopTableRenderPolicy).bind("goods2", hackLoopSameLineTableRenderPolicy)
                .bind("labors2", hackLoopSameLineTableRenderPolicy).build();
        XWPFTemplate template = XWPFTemplate.compile(resource, config).render(data);
        template.writeToFile("./doc/out_render_looprow.docx");
    }

}
