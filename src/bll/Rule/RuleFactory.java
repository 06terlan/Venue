package bll.Rule;

import java.util.HashMap;

import bll.model.Address;
import javafx.application.Application;
import javafx.scene.Node;
import main.SignUp;
import ui.admin.controller.SignUpController;
import ui.venue.controller.AddRoomController;
import ui.venue.controller.AddVenueController;
import ui.venue.controller.RoomsController;


final public class RuleFactory {

	private RuleFactory(){}
	static HashMap<Class<? extends Object>,Rule> map = new HashMap<>();
	static {
		 map.put(SignUpController.class, new SignUpRule());
		map.put(AddVenueController.class, new BuildingRule());
		map.put(AddRoomController.class, new RoomRule());
		map.put(Address.class,new AddressRule());
	}

	public static Rule getRule(Object object) {
		if(!map.containsKey(object)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(object);
	}
}
