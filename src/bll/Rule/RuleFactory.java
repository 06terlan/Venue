package bll.Rule;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Node;
import main.SignUp;
import ui.admin.controller.SignUpController;


final public class RuleFactory {

	private RuleFactory(){}
	static HashMap<Class<? extends Object>,Rule> map = new HashMap<>();
	static {
		 map.put(SignUpController.class, new SignUpRule());
//		map.put(BookWindow.class, new BookRuleSet());
	}

	public static Rule getRule(Object object) {
		if(!map.containsKey(object)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(object);
	}
}
