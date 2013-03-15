package com.siriusif.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.siriusif.model.Order;
import com.siriusif.model.User;
import com.siriusif.model.Workshift;

@Component
public class OrderProcess {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProcess.class);
	
	@Autowired
	private WorkshiftProcess workshiftProcess;
	
	public Order createOrder(User whoCreates){
		Workshift workshift = workshiftProcess.getOpenedWorkshift();
		if (workshift == null) {
			LOGGER.warn("There isno opened workshifts to create order");
			return null;
		} else {
			Order order = new Order();
			order.setAuthor(whoCreates);
//			order.setWorkShift(workShift);
//			order.setDailyId(dailyId)
//			order.setDate(date)
			//	TODO SB : Not implemented
			return order;
		}
	}

}
