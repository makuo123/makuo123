package com.stock.springcomponent.canaldemo.dbchange;

import org.springframework.context.ApplicationEvent;

/**
 * 库存表事件
 */
public class FlkMaterialEvent extends ApplicationEvent {
	public FlkMaterialEvent(Object source) {
		super(source);
	}
}
