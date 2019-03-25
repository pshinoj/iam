package com.example.emss.endpoint;

import java.util.List;

import com.example.emss.auth.Role;
import com.example.emss.auth.UserDetailsImpl;
import com.example.emss.dao.Employee;
import com.example.emss.dao.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeService empService;

  @GetMapping ({"/basic/employees", "/bearer/employees"})
  public List<Employee> getEmployees () {

    return empService.getAllEmployees ();
  }

  @GetMapping ({"/basic/employees/{id}", "/bearer/employees/{id}"})
  public Employee getEmployee (@PathVariable("id") Long id) {

    return empService.getEmployee (id);
  }

//  @PreAuthorize ("hasRole('ROLE_ADMIN')")
  @PostMapping (value = {"/basic/employees", "/bearer/employees"}, consumes =
      MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
  public Employee createEmployee (@RequestBody
      EmployeeRequest employee) {

    Employee request = new Employee ();
    request.setFirstName (employee.getFirstName ());
    request.setLastName (employee.getLastName ());
    request.setType (employee.getType ());
    request.setUserDetails (new UserDetailsImpl (employee.getUserName (), employee.getPassword ()));
    return empService.createEmployee (request);
  }

  @DeleteMapping ({"/basic/employees/{id}", "/bearer/employees/{id}"})
  public void deleteEmployee (@PathVariable("id") Long id) {

    empService.deleteEmployee (id);
  }

  @PutMapping (value = "/basic/employees/{id}/roles")
  public void addRoles (@PathVariable("id") Long id, @RequestParam("name") String roleName) {

    empService.addRole (id, "ROLE_"+roleName);
  }

  @GetMapping (value = "/basic/employees/{id}/roles")
  public List<Role> getRoles (@PathVariable("id") Long id) {

    return empService.getRoles (id);
  }

  @DeleteMapping (value = "/basic/employees/{id}/roles/{name}")
  public void deleteRole (@PathVariable("id") Long id, @PathVariable("name") String name) {

    empService.removeRole (id, name);
  }
}
