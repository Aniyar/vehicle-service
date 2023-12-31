package nu.swe.vehicleservice.security.authorities;

/**
 * Contains predefined authority checks for various roles and operations.
 * These checks are designed to be used with annotations such as {@code @PreAuthorize}.
 *
 * <p>The authority checks ensure that only specific roles or conditions can perform certain actions.</p>
 */
public class Authority {

    public static final String ADMIN = "hasAuthority('admin')";
    public static final String ADMIN_OR_MANAGER = "hasAnyAuthority('admin', 'manager')";

    public static final String ADMIN_OR_DRIVER = "hasAnyAuthority('admin', 'driver')";
    public static final String DRIVER_OR_STAFF= "hasAnyAuthority('staff', 'driver')";

    public static final String AUTHENTICATED = "hasAnyAuthority('admin', 'manager', 'driver', 'fuel', 'maintenance', 'staff')";

    public static final String DRIVER = "hasAuthority('driver')";

    public static final String FUEL = "hasAuthority('fuel')";
    public static final String ADMIN_OR_FUEL = "hasAnyAuthority('admin', 'fuel')";

    public static final String MAINTENANCE = "hasAuthority('maintenance')";
    public static final String ADMIN_OR_MAINTENANCE = "hasAnyAuthority('admin', 'maintenance')";

}