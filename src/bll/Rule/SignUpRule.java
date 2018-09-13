package bll.Rule;


import bll.model.User;

import java.util.regex.Pattern;

public class SignUpRule implements Rule<User> {

    private User user;

    @Override
    public void validate(User data) throws RuleException {
        user = data;
        notEmptyField();
        passwordPolicy();
    }

    private void notEmptyField() throws RuleException {
        if(user.getFirstName().trim().isEmpty() ||
                user.getFirstName().trim().isEmpty() ||
                user.getLastName().trim().isEmpty() ||
                user.getUserName().trim().isEmpty() ||
                user.getPhoneNo().trim().isEmpty()
                ) {
            throw new RuleException("All fields must not be empty.");
        }
    }

    private void passwordPolicy() throws RuleException {
        String phoneNoPattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        Pattern pattern = Pattern.compile(phoneNoPattern);
        if(!pattern.matcher(user.getPhoneNo()).matches()) {
            throw new RuleException("Phone number is not a valid phone number!");
        }
    }



}
