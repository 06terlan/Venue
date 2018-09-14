package bll.Rule;

import bll.model.LoginUser;;

public class LoginUserRule implements Rule<LoginUser> {

    private LoginUser loginUser;

    @Override
    public void validate(LoginUser data) throws RuleException {
    	loginUser = data;  
    	notEmptyFields();
    }

    private void notEmptyFields() throws RuleException {
        if(loginUser.getUserName().trim().isEmpty() ||
        		loginUser.getPassword().trim().isEmpty()) {
            throw new RuleException("All fields must not be empty!");
        }
    }


}
