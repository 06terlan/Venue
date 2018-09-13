package bll.Rule;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Node;
import main.SignUp;
import ui.admin.controller.SignUpController;


final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Application>, RuleSet> map = new HashMap<>();
	static {
		 //map.put( SignUp.class, new SignUpRuleSet());
		//map.put(BookWindow.class, new BookRuleSet());
	}
	public static RuleSet getRuleSet(Node c) {
		Class<? extends Node> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
