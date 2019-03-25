package com.example.emss.endpoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.emss.auth.UserAuthorities;
import com.example.emss.auth.UserAuthoritiesRepository;
import com.example.emss.auth.UserDetailsImpl;
import com.example.emss.auth.UserDetailsRepository;
import com.example.emss.dao.Employee;
import com.example.emss.dao.EmployeeRepository;
import com.example.emss.auth.Role;
import com.example.emss.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository empRepo;
  @Autowired
  RoleRepository roleRepo;
  @Autowired
  UserDetailsRepository userRepo;
  @Autowired
  UserAuthoritiesRepository authRepo;

  public List<Employee> getAllEmployees () {

    List<Employee> employees = new ArrayList<> ();
    empRepo.findAll ().forEach (employee -> employees.add (employee));
    return employees;
  }

  public Employee getEmployee (Long id) {

    return empRepo.findById (id).get ();
  }

  public Employee createEmployee (Employee employee) {

    if (userRepo.findByUserName (employee.getUserDetails ().getUsername ()) == null) {
      UserDetailsImpl userDetails = employee.getUserDetails ();
      Role eRole = roleRepo.getRoleByName ("ROLE_USER");
      userDetails.addAuthorities (eRole);
      authRepo.save (new UserAuthorities (userDetails.getUsername (), eRole.getAuthority ()));
      userRepo.save (userDetails);
    }
    return empRepo.save (employee);
  }

  public void deleteEmployee (Long id) {

    empRepo.deleteById (id);
  }

  public void addRole (Long id, String roleName) {

    Role role = roleRepo.getRoleByName (roleName);
    Employee employee = getEmployee (id);
    employee.getUserDetails ().addAuthorities (role);
    updateEmployee (id, employee);
    authRepo.save (new UserAuthorities (
        employee.getUserDetails ().getUsername (),
        role.getAuthority ()));
  }

  public Employee updateEmployee (Long id, Employee employee) {

    employee.setId (id);
    return empRepo.save (employee);
  }

  public List<Role> getRoles (Long id) {

    Employee employee = getEmployee (id);
    return new ArrayList<> (employee.getUserDetails ().getRoles ());
  }

  public void removeRole (Long id, String name) {

    Employee employee = getEmployee (id);
    Collection<Role> roles = employee.getUserDetails ().getRoles ();
    if (!roles.isEmpty ()) {
      Role result = roles.stream ()
          .filter (role -> ("ROLE_" + name).equals (role.getName ())).findFirst ().orElse (null);
      if (result != null) {
        roles.remove (result);
        userRepo.save (employee.getUserDetails ());
      }
    }
  }
}
