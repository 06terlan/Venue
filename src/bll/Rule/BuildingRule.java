package bll.Rule;

import bll.model.Building;

public class BuildingRule implements Rule<Building> {

    private Building building;

    @Override
    public void validate(Building data) throws RuleException {
        this.building = data;
        notEmptyFields();
    }

    private void notEmptyFields() throws RuleException {
        if(building.getBuildingNumber().trim().isEmpty()) {
            throw new RuleException("Building number must be specified!");
        }
    }
}
