package nu.swe.vehicleservice.user.enums;

import java.util.Arrays;
import java.util.List;

/**
 * This enum represents user roles in system.
 */
public enum UserRole {

    admin, manager, driver, gas, maintenance, staff;

    /**
     * This method return this enum's values string names.
     *
     * @return {@link List} of string representations
     */
    public static List<String> names() {
        return Arrays.stream(UserRole.values()).map(Enum::name).toList();
    }

    /**
     * Checks if this role is within the specified list of {@link UserRole} values.
     *
     * @param roles The list of roles to check against.
     * @return {@code true} if this role is within the list, {@code false} otherwise.
     */
    public boolean in(UserRole... roles) {
        return Arrays.stream(roles)
                .anyMatch(role -> role == this);
    }
}
