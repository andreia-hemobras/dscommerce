package dev.andreia.dscommerce.projections;

public interface UserRoleProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();

}
