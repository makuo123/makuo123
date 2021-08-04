package com.stock.springcomponent.canaldemo.dbchange.listener;

import com.alibaba.fastjson.JSONObject;
import com.stock.springcomponent.canaldemo.canal.DBChangeBaseListenerSync;
import com.stock.springcomponent.canaldemo.dbchange.FlkMaterialReceiveEvent;
import org.springframework.stereotype.Component;


@Component
public class FlkMaterialReceive_Warning extends DBChangeBaseListenerSync<FlkMaterialReceiveEvent> {

	@Override
	public void onInsert(JSONObject data) {
		String name = data.getString("name");
		if (name == null) {
			return;
		}

		if (name.equals("药卷")) {
			System.out.println("预警：有人领取药卷啦！！！" + data.toJSONString());
		}
	}

	@Override
	public void onDelete(JSONObject data) {

	}

	@Override
	public void onUpdate(JSONObject data, JSONObject old) {

	}

}
