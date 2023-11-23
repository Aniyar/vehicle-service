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

    public static final String AUTHENTICATED = "hasAnyAuthority('admin', 'manager', 'driver', 'gas', 'maintenance', 'staff')";

    public static final String DRIVER = "hasAuthority('driver')";
}