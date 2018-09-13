package bll.Rule;

import bll.model.Address;

public class AddressRule implements Rule<Address> {

    private Address address;

    @Override
    public void validate(Address data) throws RuleException {
        address = data;
        notEmptyFields();
        zipCodeNumeric();
    }

    private void notEmptyFields() throws RuleException {
        if(address.getCity().trim().isEmpty() ||
                address.getState().trim().isEmpty() ||
                address.getStreet().trim().isEmpty() ||
                address.getZip().trim().isEmpty()) {
            throw new RuleException("Address fields must not be empty!");
        }
    }

    private void zipCodeNumeric() throws RuleException {
        String zip = address.getZip();
        try {
            Integer.parseInt(zip);
        }catch (Exception e) {
            throw new RuleException("ZipCode must be numeric!");
        }
    }

}
