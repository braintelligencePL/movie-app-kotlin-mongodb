package pl.braintelligence.requirement.task.domain.values;

public enum Category {

    business,
    entertainment,
    general,
    health,
    science,
    sports,
    technology,
    INVALID;

    public boolean isInvalid() {
        return this == INVALID;
    }

}
